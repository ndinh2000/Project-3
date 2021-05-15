/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ndinh
 */
public class ShowPet extends HttpServlet {

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
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbName = "petstore";
            String userName = System.getProperty("RDS_USERNAME");
            String password = System.getProperty("RDS_PASSWORD");
            String hostname = System.getProperty("RDS_HOSTNAME");
            String port = System.getProperty("RDS_PORT");
            String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
            Connection con = DriverManager.getConnection(jdbcUrl);
            Statement stmt = con.createStatement();

            String sql = "SELECT name, profile_picture FROM petstore.pet";
            ResultSet rs = stmt.executeQuery(sql);

            PrintWriter writer = response.getWriter();
            writer.println("<Html> <body>");
            String imgPath = "";
            while (rs.next()) {
                writer.println(rs.getString("name"));
                imgPath = rs.getString("profile_picture");
                writer.println("</br>");
            }
//            writer.println("<img src='./images/1.png'>");
//            writer.println("<img src='./images/cat1.jpg' >");
            writer.println("<img src='./images/CatImages/cat1.jfif' >");
//            writer.println("<img src=\"../../../../pics/1.png\">");
//            writer.println("<img src=\"../../../pics/1.png\">");
//            writer.println("<img src=\"../../pics/1.png\">");
//            writer.println("<img src=\"../pics/1.png\">");
//            writer.println("<img src=\"pics/1.png\">");

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
