package com.petstore.service;

import com.petstore.db.DatabaseConnector;
import com.petstore.db.DatabaseUtils;
import com.petstore.model.Rating;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RatingService {

    private final static String ALL_PETS_QUERY = "SELECT * FROM Ratings";

    /*
        Cloned version with id as String
     */
    public static List<Rating> getRatingByID(String pet_id) {
        //Get a new connection object before going forward with the JDBC invocation.
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_PETS_QUERY + " WHERE pet_id = '" + pet_id + "'");

        List<Rating> ratings = new ArrayList<Rating>();

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Rating rating = new Rating();

                    rating.setUser_id(resultSet.getInt("user_id"));
                    rating.setPet_id(resultSet.getString("pet_id"));
                    rating.setRating(resultSet.getInt("rating"));

                    ratings.add(rating);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {

                    // We will always close the connection once we are done interacting with the Database.
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return ratings;
        }

        return null;


    }

//    public static List<Todo> getAllTodos() {
//        List<Todo> todos = new ArrayList<Todo>();
//
//        Connection connection = DatabaseConnector.getConnection();
//        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_TODOS_QUERY);
//
//        if (resultSet != null) {
//            try {
//                while (resultSet.next()) {
//                    Todo todo = new Todo();
//
//                    todo.setId(resultSet.getInt("TODO_ID"));
//                    todo.setDescription(resultSet.getString("TODO_DESC"));
//                    todo.setSummary(resultSet.getString("TODO_SUMMARY"));
//
//                    todos.add(todo);
//
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return todos;
//    }

//    public static boolean AddTodo(Todo todo) {
//
//        String sql = "INSERT INTO TODOS  (TODO_SUMMARY, TODO_DESC)" +
//                "VALUES (?, ?)";
//
//        Connection connection = DatabaseConnector.getConnection();
//        return DatabaseUtils.performDBUpdate(connection, sql, todo.getSummary(), todo.getDescription());
//
//    }

//    public static boolean updateTodo(Todo todo) {
//
//        String sql = "UPDATE TODOS SET TODO_SUMMARY=?, TODO_DESC=? WHERE TODO_ID=?;";
//
//        Connection connection = DatabaseConnector.getConnection();
//
//        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, todo.getSummary(), todo.getDescription(),
//                String.valueOf(todo.getId()));
//
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return updateStatus;
//
//    }

//    public static boolean deleteTodo(Todo retrievedTodo) {
//
//        String sql = "DELETE FROM TODOS WHERE TODO_ID=?;";
//
//        Connection connection = DatabaseConnector.getConnection();
//
//        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, String.valueOf(retrievedTodo.getId()));
//
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return updateStatus;
//    }
}
