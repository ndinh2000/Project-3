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

    private final static String RATINGS_QUERY = "SELECT * FROM Ratings";
    private final static String ALL_PETS_QUERY = "SELECT * FROM Pet";

    public static boolean checkPetID(String pet_id) {
        //Get a new connection object before going forward with the JDBC invocation.
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_PETS_QUERY + " WHERE pet_id = '" + pet_id + "'");
        try {
            if (!resultSet.next()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /*
        Cloned version with id as String
     */
    public static List<Rating> getRatingByID(String pet_id) {
        //Get a new connection object before going forward with the JDBC invocation.
        if (checkPetID(pet_id)) {
            Connection connection = DatabaseConnector.getConnection();
            ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, RATINGS_QUERY + " WHERE pet_id = '" + pet_id + "'");

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
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                return ratings;
            }
            return null;
        } else {
            return null;
        }
    }

}
