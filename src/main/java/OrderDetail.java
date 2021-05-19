/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ndinh
 */
@WebServlet(name = "OrderDetail", value="/OrderDetail")
public class OrderDetail extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                    + "petstore", "root", "root");
            HttpSession session = request.getSession(true);
            Integer curID = (Integer)session.getAttribute("user_id");

            Statement stmt = con.createStatement();
            String sql = "SELECT user_id,pet_id,qty,price,name_first,name_last,email,address_zipcode,address_state,address,card_number,expiration_MM,expiration_YY,shipping_method,phone_number" +
                    " FROM orders" +
                    " WHERE user_id = '"+curID+"' AND paid=false;";
//            String sql = "SELECT pet_id FROM orders WHERE user_id='1'";
            ResultSet rs = stmt.executeQuery(sql);

            PrintWriter writer = response.getWriter();
            writer.println("<!DOCTYPE html><html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>The Pet Shop | Home</title>\n");
            writer.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() + "/myStyle.css' />\n");
            writer.println("</head>" +
                    "<body>\n" +
                    "    <div id=\"header\"><h1><img src=\"./images/Logo/shopLogo.png\"></h1></div>\n" +
                    "    <div id = \"top-nav-bar\">\n" +
                    "        <ul>\n" +
                    "            <li><a href=./><h3> Home </h3></a></li>\n" +
                    "            <li><a href=./DogsServlet><h3> Dogs </h3></a></li>\n" +
                    "            <li><a href=./CatsServlet><h3> Cats </h3></a></li>\n" +
                    "            <li><a href=./ContactServlet><h3> Contact </h3></a></li>\n" +
                    "        </ul>\n" +
                    "    </div></div>\n" +
                    "\n");

//            writer.println("</html>");

//            writer.println("<head>");
//            writer.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() + "/myStyle.css' />\n");
//            writer.println("</head>");
//            writer.println("<Html> <body>");
//            writer.println("<div id=\"main\">");
//            writer.println("<h3 style=\"text-align: left;font-size: 1.5em;margin-left: 34px;margin-bottom: 0;\">" +
//                    "Latest Purchases:</h3>");
//            writer.println("<div class=\"row\" style=\"padding-top: 0;\">");
//            String imgPath = "";

            String state = "";
            writer.println("<div style='padding-left:20px'>");
            writer.println("<div><h1>Confirmation Page<h1></div>");
            while(rs.next()) {
                state = rs.getString("address_state");
                writer.println("<div>User Id: " + rs.getInt("user_id") + "</div>");
                writer.println("<div>Name: " + rs.getString("name_first") + " " + rs.getString("name_last") + "</div>");
                writer.println("<div>Phone: " + rs.getString("phone_number") + "</div>");
                writer.println("<div>Email: " + rs.getString("email") + "</div>");
                writer.println("<div>Address: " + rs.getString("address") + " , " + rs.getString("address_state") + ", " + rs.getString("address_zipcode") + "</div>");
                writer.println("<div>Card Number: " + rs.getString("card_number") + "</div>");
                writer.println("<div>Expiration Date: " + rs.getString("expiration_MM") + "/" + rs.getString("expiration_YY") + "</div>");
                writer.println("<div>Shipping Method: " + rs.getString("shipping_method") + "</div>");
                writer.println("<div><h2>Products</h2></div>");
                break;
            }
            float price_total = 0;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                writer.println("<div>Pet Id: "+rs.getString("pet_id")+"\n"+
                        "Quantity: "+ rs.getInt("qty")+"\n"+
                        "Price: $" + rs.getFloat("price")+"\n"+
                        "</div>");
                price_total += rs.getInt("qty")*rs.getFloat("price");

                String updateProductStatus = "update orders set paid=true where user_id='"+ curID +"' and pet_id='"+rs.getString("pet_id")+"';";
                Statement new_stmt = con.createStatement();
                new_stmt.executeUpdate(updateProductStatus);
                new_stmt.close();
            }

            HashMap<String, String> map = new HashMap<String, String>();
            try {
                BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ndinh\\Documents\\INF 124 Internet Application Engineering\\Code\\Project 3\\src\\main\\webapp\\tax_rates2.csv"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    String str[] = line.split(",");
                    map.put(str[0], str[3]);//state,tax
                }
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("IOException not found");
                e.printStackTrace();
            }

//            String tax="0";
            float tax = 0;
            for(String m_state: map.keySet())
            {
                if(state.equals(m_state))
                {
                    tax = Float.parseFloat(map.get(m_state));
                    break;
                }
            }
//            float total_cost = price_total+(price_total*Float.parseFloat(tax));
            float total_cost = price_total+(price_total*tax);
            writer.println("<div>" +
                    "<h3>Item Cost: $"+price_total+"</h3>"+
                    "<h3>Tax:"+tax*100+"%</h3>"+
                    "<h3>Total Cost: $"+total_cost+"</h3>" +
                    "</div>");
            writer.println("</div></body> </Html> ");
            stmt.close();



            HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
            cart.clear();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}