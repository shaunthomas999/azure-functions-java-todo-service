package com.shaunthomas999;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.shaunthomas999.service.TodoService;

import java.util.Optional;

public class ListTodosFunction {

  @FunctionName("ListTodosFunction")
  public HttpResponseMessage run(
      @HttpTrigger(
          name = "req",
          methods = {HttpMethod.GET},
          authLevel = AuthorizationLevel.ANONYMOUS,
          route = "todos")
          HttpRequestMessage<Optional<String>> request,
      final ExecutionContext context) {
    context.getLogger().info("Java HTTP trigger processed a request.");

    return request.createResponseBuilder(HttpStatus.OK)
        .body(new TodoService().getTodos())
        .build();
  }
}
