/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ndinh
 */
@WebServlet(urlPatterns = {"/HandleRating"})
public class HandleRating extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        /*  doGet method for debugging  */
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
//                    + "petstore", "root", "root");
//            Statement stmt = con.createStatement();
//            
//            String pet_id = request.getParameter("pet_id");
//            String user_id = request.getParameter("user_id");
//            String stars = request.getParameter("stars");
//
//            String sql = "DELETE FROM `Ratings` WHERE user_id = " + user_id + " AND pet_id = '" + pet_id + "'; ";
//            stmt.executeUpdate(sql);
//            stmt.close();
//            
//            stmt = con.createStatement();
//            sql = "INSERT INTO `Ratings` VALUES (" + user_id + ", '" + pet_id + "', " + stars + ");";
//            stmt.executeUpdate(sql);
//            stmt.close();
//            con.close();
//            
//            PrintWriter writer = response.getWriter();
//            writer.println("<div>");
//            writer.println("hi");
//            writer.println("</div>");
//            writer.println("<div>");
//            writer.println(sql);
//            writer.println("</div>");
//            writer.println("<Html> <body>");
//            writer.println("<div>");
//            writer.println("user_id = " + user_id);
//            writer.println("</div>");
//            writer.println("<div>");
//            writer.println("pet_id = " + pet_id);
//            writer.println("</div>");
//            writer.println("<div>");
//            writer.println("stars = " + stars);
//            writer.println("</div>");
//            writer.println("</body> </Html> ");
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                    + "petstore", "root", "root");
            Statement stmt = con.createStatement();

            String pet_id = request.getParameter("pet_id");
            String user_id = request.getParameter("user_id");
            String stars = request.getParameter("stars");

            String sql = "DELETE FROM `Ratings` WHERE user_id = " + user_id + " AND pet_id = '" + pet_id + "'; ";
            stmt.executeUpdate(sql);
            stmt.close();

            stmt = con.createStatement();
            sql = "INSERT INTO `Ratings` VALUES (" + user_id + ", '" + pet_id + "', " + stars + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}