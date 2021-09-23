package com.shaunthomas999;

import java.util.*;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import com.shaunthomas999.model.Todo;
import com.shaunthomas999.service.TodoService;

public class AddTodoFunction {

  @FunctionName("AddTodoFunction")
  public HttpResponseMessage run(
      @HttpTrigger(
          name = "req",
          methods = {HttpMethod.POST},
          authLevel = AuthorizationLevel.FUNCTION,
          route = "todos")
          HttpRequestMessage<Optional<Todo>> request,
      final ExecutionContext context) {
    context.getLogger().info("Java HTTP trigger processed a request.");

    Todo todo = request.getBody().get();
    TodoService todoService = new TodoService();
    todoService.addTodo(todo);

    return request.createResponseBuilder(HttpStatus.CREATED).build();
  }
}
