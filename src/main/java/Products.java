/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.OutputStream;
import javax.servlet.ServletOutputStream;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 *
 * @author ndinh
 */
public class Products extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                    + "petstore", "root", "root");
            Statement stmt = con.createStatement();
            String sql = "SELECT pet_id,name, age, gender, price, SUBSTRING(message, 1, 65) AS message, profile_picture " +
                    "FROM petstore.pet;";
            ResultSet rs = stmt.executeQuery(sql);

            PrintWriter writer = response.getWriter();
//            writer.println(sql);
            writer.println("<Html> <body>");
            writer.println("<div id=\"main\">");
            writer.println("<h3 style=\"text-align: left;font-size: 1.5em;margin-left: 34px;margin-bottom: 0;\">" +
                    "Available Pets:</h3>");
            writer.println("<div class=\"row\" style=\"padding-top: 0;\">");
            String imgPath = "";
            while (rs.next()) {
                writer.println("<div class=\"col-3 col-s-5 featuredPets\">");
                writer.println("<a href=\"/PA2/ProductDetail?pet_id=" + rs.getString("pet_id") + "\">");
                writer.println("<div style=\"height: 275px;\">");
                imgPath = rs.getString("profile_picture");
                writer.println("<img src="+ imgPath +">");
                writer.println("</div>"); //for style=height
                writer.println("<h3>" + rs.getString("name") +
                        " - $" + rs.getString("price") + "</h3>");
                writer.println("</a>");
                writer.println("<p>"+ rs.getString("message") +"...</p>");
                String url = "/GetRating?pet_id=" + rs.getString("pet_id");
//                writer.println(url);
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.include(request, response);
                writer.println("<hr class=\"solid\">");
                writer.println("</div>");
            }
            writer.println("</div>"); //for class=row
            writer.println("</div>"); //for id= main
            writer.println("</body> </Html> ");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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