
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
@WebServlet(urlPatterns = {"/GetRating"})
public class GetRating extends HttpServlet {

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
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                    + "petstore", "root", "anqizhong1999.");
            Statement stmt = con.createStatement();
            String pet_id = request.getParameter("pet_id");
            String sql = "SELECT rating FROM ratings WHERE pet_id = '" + pet_id + "';";
            ResultSet rs = stmt.executeQuery(sql);

            PrintWriter writer = response.getWriter();
            int total = 0, count = 0;
            while (rs.next()) {
                total += rs.getFloat("rating");
                ++count;
            }

            writer.println("<div class=\"rate-container-fixed\" style=\"height:35px; display: flex; flex-direction: row-reverse; justify-content: center;\">");
            if (count > 0) {
                int stars = (int) Math.round((double) total / (double) count);
                for (int i = 5; i > stars; --i) {
                    writer.println("<i class=\"fa fa-star \" style=\"font-size:24px; padding: 5px; color: grey\"></i>");
                }
                for (int i = stars; i > 0; --i) {
                    writer.println("<i class=\"fa fa-star \" style=\"color: gold; font-size:24px; padding: 5px;\"></i>");
                }
            } else {
                writer.println("No Review Yet");
            }
            writer.println("</div>");
            writer.println("</body> </Html>");
            stmt.close();

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