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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ndinh
 */
@WebServlet(urlPatterns = {"/ProductDetail"})
public class ProductDetail extends HttpServlet {

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
        String pet_id = request.getParameter("pet_id");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                    + "petstore", "root", "anqizhong1999.");
            Statement stmt = con.createStatement();
            String sql = "SELECT name, age,gender,price,pet_id,message,profile_picture FROM pet "
                    + "WHERE pet_id = '" + pet_id + "';";
//            writer.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            PrintWriter writer = response.getWriter();
            writer.println("<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>The Pet Shop</title>\n");
            writer.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() +  "/myStyle.css' />"+
                    "<script src='"+request.getContextPath()+"/script.js'></script>"+"</head>");

            response.setContentType("text/html;charset=UTF-8");
            String url1 = "/titleHeader";
            RequestDispatcher rd = request.getRequestDispatcher(url1);
            rd.include(request, response);


            writer.println("<Html> <body>");
//            writer.println("<h2>" + petID + "</h2>");

            String imgPath = "";
            while (rs.next()) {
                writer.println("<div id='main'>");
                writer.println("<div class=\"row\" style=\"text-align: left\">\n" +
                        "            <div class=\"col-3 col-s-5\">\n" +
                        "                <div class=\"profile\">");
                imgPath = rs.getString("profile_picture");
                writer.println("<img src='"+imgPath+"'>");
                writer.println( "</div>\n" +
                        "            </div>\n" +
                        "            <div class=\"col-3 col-s-5\" style=\"text-align: left; padding-top: 35px;\">" +
                        "                <p>Name: "+ rs.getString("name")+"</p>\n" +
                        "                <p>Age: "+rs.getInt("age")+"</p>\n" +
                        "                <p>Gender: "+rs.getString("gender")+"</p>\n" +
                        "                <p>Price: $"+rs.getFloat("price")+"</p>\n" +
                        "                <p>ID: "+rs.getString("pet_id")+"</p>\n" +
                        "                <p>"+rs.getString("name")+"'s Message: </p>\n" +
                        "                <p>&emsp; "+rs.getString("message")+"</p>\n" +
                        "            </div>\n" +
                        "        </div>");
                writer.println("<form action=/PA2/Cart method='post'>");
                writer.println("<div class='addToCartButton'><button name='pet_id' value='" + pet_id + "'>Add to Cart</button></div>");
                writer.println("</form>");
                writer.println("</div>");

//                writer.println(rs.getString("name"));
//                writer.println("</div>");
//                imgPath = rs.getString("profile_picture");
//                writer.println("<div>");
//                writer.println("<img style='width: 200px;' src='" + imgPath + "' />");
////                writer.println("<img style='width: 200px;' src='./images/CatImages/cat1.jfif>");
//                writer.println("</div>");
//                writer.println("</br>");
//                writer.println("<button action='/AddToCart/" + petID + "'>Add to Cart</button>");
            }
            writer.println("</body> </Html> ");
            stmt.close();
//
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
