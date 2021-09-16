package com.shaunthomas999.service;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.shaunthomas999.model.Todo;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class TodoService {

  private static final MongoClientURI MONGO_CLIENT_URI = new MongoClientURI(System.getenv("COSMOS_DB_MONGO_CLIENT_URI"));
  private static final String MONGO_DATABASE_NAME = System.getenv("COSMOS_DB_MONGO_DATABASE_NAME");
  private static final String MONGO_COLLECTION_NAME = "todos-collection";
  private static final CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
      fromProviders(PojoCodecProvider.builder().automatic(true).build()));

  public List<Todo> getTodos() {

    try (MongoClient mongoClient = new MongoClient(MONGO_CLIENT_URI)) {
      MongoDatabase database = mongoClient.getDatabase(MONGO_DATABASE_NAME).withCodecRegistry(pojoCodecRegistry);
      MongoCollection<Todo> collection = database.getCollection(MONGO_COLLECTION_NAME, Todo.class);
      return collection.find().into(new ArrayList<>());
    }
  }

  public void addTodo(Todo todo) {

    try (MongoClient mongoClient = new MongoClient(MONGO_CLIENT_URI)) {
      MongoDatabase database = mongoClient.getDatabase(MONGO_DATABASE_NAME).withCodecRegistry(pojoCodecRegistry);
      MongoCollection<Todo> collection = database.getCollection(MONGO_COLLECTION_NAME, Todo.class);
      collection.insertOne(todo);
    }
  }
}
