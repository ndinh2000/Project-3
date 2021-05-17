package com.petstore;

import com.petstore.model.Order;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * This class contains the methods that will respond to the various endpoints that you define for your RESTful API Service.
 *
 */

@Path("/orders")
public class OrderResource {
    @Path("{user_id}")
    @GET
    @Produces( { MediaType.APPLICATION_JSON }) //This provides only JSON responses
    public Response getRatingByID(@PathParam("user_id") String user_id/* The {id} placeholder parameter is resolved */) {
//    public Response getTodoById() {
        //invokes the DB method which will fetch a todo_list item object by id
//        Pet pet = TodoService.getPetByID("D101");
        List<Order> orders = com.petstore.service.OrderService.getRatingByID(user_id);

        //Respond with a 404 if there is no such todo_list item for the id provided
        if(orders == null) {
            String result = "database query returned null!!1!1!!";
            return Response.status(201).entity(result).build();
//            return Response.status(Response.Status.NOT_FOUND).build();
        }

        //Respond with a 200 OK if you have a todo_list_item object to return as response
        return Response.ok(orders).build();
    }

    //This method represents an endpoint with the URL /todos and a GET request.
    // Since there is no @PathParam mentioned, the /todos as a relative path and a GET request will invoke this method.
//    @GET
//    @Produces( { MediaType.APPLICATION_JSON })
//    public Response getAllTodos() {
//        List<Todo> todoList = TodoService.getAllTodos();
//
//        if(todoList == null || todoList.isEmpty()) {
//
//        }
//
//        return Response.ok(todoList).build();
//    }

    //This method represents an endpoint with the URL /todos and a POST request.
    // Since there is no @PathParam mentioned, the /todos as a relative path and a POST request will invoke this method.
//    @POST
//    @Consumes({MediaType.APPLICATION_JSON}) //This method accepts a request of the JSON type
//    public Response addTodo(Todo todo) {
//
//        //The todo object here is automatically constructed from the json request. Jersey is so cool!
//        if(TodoService.AddTodo(todo)) {
//            return Response.ok().entity("TODO Added Successfully").build();
//        }
//
//        // Return an Internal Server error because something wrong happened. This should never be executed
//        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//
//
//    }

    //Similar to the method above.
//    @POST
//    @Consumes({MediaType.APPLICATION_FORM_URLENCODED}) //This method accepts form parameters.
//    //If you were to send a POST through a form submit, this method would be called.
//    public Response addTodo(@FormParam("summary") String summary,
//                            @FormParam("description") String description) {
//        Todo todo = new Todo();
//        todo.setSummary(summary);
//        todo.setDescription(description);
//
//        System.out.println(todo);
//
//        if(TodoService.AddTodo(todo)) {
//            return Response.ok().entity("TODO Added Successfully").build();
//        }
//
//        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//
//
//    }

    //This method represents a PUT request where the id is provided as a path parameter and the request body is provided in JSON
//    @PUT
//    @Path("{id}")
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response updateTodo(@PathParam("id") int id, Todo todo) {
//
//        // Retrieve the todo that you will need to change
//        Todo retrievedTodo = TodoService.getTodoById(id);
//
//        if(retrievedTodo == null) {
//            //If not found then respond with a 404 response.
//            return Response.status(Response.Status.NOT_FOUND).
//                    entity("We could not find the requested resource").build();
//        }
//
//        // This is the todo_object retrieved from the json request sent.
//        todo.setId(id);
//
//        // if the user has provided null, then we set the retrieved values.
//        // This is done so that a null value is not passed to the todo object when updating it.
//        if(todo.getDescription() == null) {
//            todo.setDescription(retrievedTodo.getDescription());
//        }
//
//        //Same as above. We only change fields in the todo_resource when the user has entered something in a request.
//        if (todo.getSummary() == null) {
//            todo.setSummary(retrievedTodo.getSummary());
//        }
//
//        //This calls the JDBC method which in turn calls the the UPDATE SQL command
//        if(TodoService.updateTodo(todo)) {
//
//            return Response.ok().entity(todo).build();
//        }
//
//        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//
//
//    }

    //This method represents a DELETE request where the id is provided as a path parameter and the request body is provided in JSON
//    @DELETE
//    @Path("{id}")
//    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
//    public Response deleteTodo(@PathParam("id") int id) {
//
//        //Retrieve the todo_object that you want to delete.
//        Todo retrievedTodo = TodoService.getTodoById(id);
//
//        if(retrievedTodo == null) {
//            //If not found throw a 404
//            return Response.status(Response.Status.NOT_FOUND).
//                    entity("We could not find the requested resource").build();
//        }
//
//        // This calls the JDBC method which in turn calls the DELETE SQL command.
//        if(TodoService.deleteTodo(retrievedTodo)) {
//            return Response.ok().entity("TODO Deleted Successfully").build();
//        }
//
//        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//
//
//    }

}
