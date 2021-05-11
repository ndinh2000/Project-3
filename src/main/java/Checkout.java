/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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
@WebServlet(urlPatterns = {"/Checkout"})
public class Checkout extends HttpServlet {

//    private Object ClassNotFoundException;
//    private Object SQLException;

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
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        Integer curID = (Integer)session.getAttribute("user_id");
        HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
//        if (cart == null) {
//            // Add the newly created ArrayList to session, so that it could be retrieved next time
////            cart = new ArrayList<>();
//            cart = new HashMap<String, Integer>();
//            session.setAttribute("cart", cart);
//        }
//        synchronized (cart) {
//
//        }
        synchronized (cart) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                        + "petstore", "root", "anqizhong1999.");
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String phone = request.getParameter("phone");
                String email = request.getParameter("clientEmail");
                String creditCard = request.getParameter("credit-card");
                String expireMM = request.getParameter("expireMM");
                String expireYY = request.getParameter("expireYY");
                String address = request.getParameter("address");
                String state = request.getParameter("state");
                String zip = request.getParameter("zip");
                String shippingMethod = request.getParameter("shipping-method");

//                PrintWriter writer = response.getWriter();
//                writer.println(curID);
//                writer.println(fname);
//                writer.println(lname);
//                writer.println(phone);
//                writer.println(email);
//                writer.println(creditCard);
//                writer.println(expireMM);
//                writer.println(expireYY);
//                writer.println(address);
//                writer.println(state);
//                writer.println(zip);
//                writer.println(shippingMethod);

//                Statement stmt = con.createStatement();
                for(String item: cart.keySet())
                {
//                    writer.println("\n"+item);
                    String sql = "SELECT price" +
                            " FROM pet" +
                            " WHERE pet_id = '" + item + "';";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    Integer qty = cart.get(item);
//                    String p_sql = "update Order set user_id=? , pet_id=?, qty=?, price=?,name_first=?,name_last=?";
                    PreparedStatement pstmt = con.prepareStatement("INSERT INTO `Orders`(user_id,pet_id,qty,price,name_first,name_last,email,address_zipcode,address_state,address,card_number,expiration_MM,expiration_YY,shipping_method) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                    pstmt.setInt(1,curID);
                    pstmt.setString(2,item);
                    pstmt.setInt(3,qty);
                    while(rs.next()) {
                        pstmt.setFloat(4, rs.getFloat("price"));
                    }
                    pstmt.setString(5,fname);
                    pstmt.setString(6,lname);
                    pstmt.setString(7,email);
                    pstmt.setString(8,zip);
                    pstmt.setString(9,state);
                    pstmt.setString(10,address);
                    pstmt.setString(11,creditCard);
                    pstmt.setString(12,expireMM);
                    pstmt.setString(13,expireYY);
                    pstmt.setString(14,shippingMethod);

                    pstmt.executeUpdate();

                    stmt.close();
                    pstmt.close();

//                    String sql = "SELECT price" +
//                            " FROM pet" +
//                            " WHERE pet_id = '" + item + "';";
//                    Statement stmt = con.createStatement();
//                    ResultSet rs = stmt.executeQuery(sql);
////                    float price = rs.getFloat("price");
////                    stmt.close();
////                    stmt = con.createStatement();
//                    Integer qty = cart.get(item);
//                    while(rs.next())
//                    {
////                        stmt.executeUpdate("INSERT INTO `Order`(user_id,pet_id,qty,price,name_first,name_last) VALUE ('"+curID+"','"+rs.getString("pet_id")+"','"+qty+"','"+
////                                rs.getFloat("price")+"','"+fname+"','"+lname+"')");
//                        stmt.executeUpdate("INSERT INTO `Order`(user_id,pet_id,qty,price,name_first,name_last) VALUE ('"+curID+"','"+item+"','"+qty+"','"+
//                                "2','"+fname+"','"+lname+"')");
//
//                    }
////                    stmt.close();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        RequestDispatcher rd=request.getRequestDispatcher("/OrderDetail");
        rd.forward(request, response);

//        String url = "/OrderDetail";
//        RequestDispatcher rd = request.getRequestDispatcher(url);
//        rd.forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        String url = "/OrderDetail";
//        RequestDispatcher rd = request.getRequestDispatcher(url);
//        rd.forward(request, response);
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
