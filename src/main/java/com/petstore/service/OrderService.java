package com.petstore.service;

import com.petstore.db.DatabaseConnector;
import com.petstore.db.DatabaseUtils;
import com.petstore.model.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final static String ORDERS_QUERY = "SELECT * FROM Orders";
    private final static String ALL_PETS_QUERY = "SELECT * FROM Pet";

    public static boolean checkUserID(String user_id) {
        //Get a new connection object before going forward with the JDBC invocation.
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ORDERS_QUERY + " WHERE user_id = '" + user_id + "'");
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
    public static List<Order> getRatingByID(String user_id) {
        //Get a new connection object before going forward with the JDBC invocation.
//        if (checkUserID(user_id)) {
            Connection connection = DatabaseConnector.getConnection();
            ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ORDERS_QUERY + " WHERE user_id = '" + user_id + "'");

            List<Order> orders = new ArrayList<Order>();

            if (resultSet != null) {
                try {
                    while (resultSet.next()) {
                        Order order = new Order();

                        order.setOrder_id(resultSet.getInt("order_id"));
                        order.setUser_id(resultSet.getInt("user_id"));
                        order.setPet_id(resultSet.getString("pet_id"));
                        order.setQty(resultSet.getInt("qty"));
                        order.setPrice(resultSet.getFloat("price"));
                        order.setName_first(resultSet.getString("name_first"));
                        order.setName_last(resultSet.getString("name_last"));
                        order.setEmail(resultSet.getString("email"));
                        order.setAddress_zipcode(resultSet.getString("address_zipcode"));
                        order.setAddress_state(resultSet.getString("address_state"));
                        order.setAddress(resultSet.getString("address"));
                        order.setCard_number(resultSet.getString("card_number"));
                        order.setExpiration_MM(resultSet.getString("expiration_MM"));
                        order.setExpiration_YY(resultSet.getString("expiration_YY"));
                        order.setShipping_method(resultSet.getString("shipping_method"));
                        order.setPaid(resultSet.getBoolean("paid"));
                        order.setPhone_number(resultSet.getString("phone_number"));

                        orders.add(order);
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
                if (orders.isEmpty())
                    return null;
                return orders;
            }
            return null;
//        } else {
//            return null;
//        }
    }

}
