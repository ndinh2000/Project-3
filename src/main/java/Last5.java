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
import javax.servlet.http.HttpSession;

/**
 *
 * @author ndinh
 */
@WebServlet(name = "Last5", value="/Last5")
public class Last5 extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                    + "petstore", "root", "anqizhong1999.");
            Statement stmt = con.createStatement();
            HttpSession session = request.getSession(true);
            String user_id = (session.getAttribute("user_id")).toString();
            PrintWriter writer = response.getWriter();

            String sql = "SELECT DISTINCT p.pet_id, p.name, p.profile_picture, p.price, r.rating " +
                    "FROM orders o, (pet p LEFT JOIN ratings r ON p.pet_id = r.pet_id AND r.user_id = " + user_id + ")" +
                    "WHERE p.pet_id = o.pet_id AND o.user_id = " + user_id + " LIMIT 5;";

            ResultSet rs = stmt.executeQuery(sql);

            writer.println("<head>");
            writer.println("<p>");
            writer.println("</p>");
            writer.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() + "/myStyle.css' />\n");
            writer.println("<style>");
            writer.println(".rating {\n" +
                    "    display: flex;\n" +
                    "    flex-direction: row-reverse;\n" +
                    "    justify-content: center\n" +
                    "}\n" +
                    "\n" +
                    ".rating>input {\n" +
                    "    display: none\n" +
                    "}\n" +
                    "\n" +
                    ".rating>label {\n" +
                    "    position: relative;\n" +
                    "    width: 1em;\n" +
                    "    font-size: 6vw;\n" +
                    "    color: #FFD600;\n" +
                    "    cursor: pointer\n" +
                    "}\n" +
                    "\n" +
                    ".rating>label::before {\n" +
                    "    content: \"\\2605\";\n" +
                    "    position: absolute;\n" +
                    "    opacity: 0\n" +
                    "}\n" +
                    "\n" +
                    ".rating>label:hover:before,\n" +
                    ".rating>label:hover~label:before {\n" +
                    "    opacity: 1 !important\n" +
                    "}\n" +
                    "\n" +
                    ".rating>input:checked~label:before {\n" +
                    "    opacity: 1\n" +
                    "}\n" +
                    "\n" +
                    ".rating:hover>input:checked~label:before {\n" +
                    "    opacity: 0.4\n" +
                    "}\n" +
                    "\n" +
                    ".rate-container{\n" +
                    "    display: flex;\n" +
                    "    flex-direction: row-reverse;\n" +
                    "    justify-content: center;\n" +
                    "}\n" +
                    "\n" +
                    ".rate-container > i {\n" +
                    "    color: grey;\n" +
                    "}\n" +
                    "\n" +
                    ".rate-container > i:HOVER,\n" +
                    ".rate-container > i:HOVER ~ i {\n" +
                    "    color: gold;\n" +
                    "}\n" +
                    "\n" +
                    ".rate-container-fixed {\n" +
                    "    display: flex;\n" +
                    "    flex-direction: row-reverse;\n" +
                    "    justify-content: center;\n" +
                    "}\n" +
                    "\n" +
                    ".rate-container-fixed > i {\n" +
                    "    color: grey;\n" +
                    "}\n");
            writer.println("</style>");
            writer.println("<link rel='stylesheet' type='text/css' href='https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css' />\n");
            writer.println("</head>");
            writer.println("<Html> <body>");
            writer.println("<div id=\"main\">");
            writer.println("<h3 style=\"text-align: left;font-size: 1.5em;margin-left: 34px;margin-bottom: 0;\">" +
                    "Latest Purchases:</h3>");
//
            writer.println("<script>\n" +
                    "            function handleRating(user_id, pet_id, stars) {\n" +
                    "                let str = \"\";\n" +
                    "                for (let i = 5; i > stars; --i) {\n" +
                    "                    str = str + \"<i class=\\\"fa fa-star \\\" style=\\\"font-size:24px; padding: 5px;\\\" onclick=\\\"handleRating(\" + user_id + \", this.parentNode.id,\" + i + \")\\\"></i>\\n\";\n" +
                    "                }\n" +
                    "                for (let i = stars; i > 0; --i) {\n" +
                    "                    str = str + \"<i class=\\\"fa fa-star\\\" style=\\\"color: gold; font-size:24px; padding: 5px;\\\" onclick=\\\"handleRating(\" + user_id + \", this.parentNode.id,\" + i + \")\\\"></i>\\n\";\n" +
                    "                }\n" +
                    "                document.getElementById(pet_id).innerHTML = str;\n" +
                    "                let destination = \"/PA2/HandleRating?\";\n" +
                    "                destination = destination + \"user_id=\" + user_id + \"&\";\n" +
                    "                destination = destination + \"pet_id=\" + pet_id + \"&\";\n" +
                    "                destination = destination + \"stars=\" + stars;\n" +
                    "\n" +
                    "                fetch(destination, {\n" +
                    "                    method: \"POST\",\n" +
                    "                });\n" +
                    "window.href.location = \"/PA2\"" +
                    "            }\n" +
                    "        </script>");
//
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

                String pet_id = rs.getString("pet_id");
                int stars = rs.getInt("rating");
                writer.println("<div>");
//                writer.println("pet_id = " + pet_id + "\n");
//                writer.println("stars = " + stars + "\n");
                writer.println("</div>");
                writer.println("<div class=\"rate-container\" id=\"" + pet_id + "\">");
                for (int i = 5; i > stars; --i) {
                    writer.println("<i class=\"fa fa-star \" style=\"font-size:24px; padding: 5px;\" onclick=\"handleRating(" + user_id + ", '" + pet_id + "', " + i + ")\"></i>");
                }
                for (int i = stars; i > 0; --i) {
                    writer.println("<i class=\"fa fa-star \" style=\"font-size:24px; padding: 5px; color: gold\" onclick=\"handleRating(" + user_id + ", '" + pet_id + "', " + i + ")\"></i>");
                }
                writer.println("</div>");

                /* Static stars, for reference */
//                writer.println("<div class=\"rate-container\" id=\"" + pet_id + "\">\n" +
//                        "            <i class=\"fa fa-star \" style=\"font-size:24px; padding: 5px;\" onclick=\"handleRating(0, '" + pet_id + "', 5)\"></i>\n" +
//                        "            <i class=\"fa fa-star \" style=\"font-size:24px; padding: 5px;\" onclick=\"handleRating(0, '" + pet_id + "', 4)\"></i>\n" +
//                        "            <i class=\"fa fa-star \" style=\"font-size:24px; padding: 5px;\" onclick=\"handleRating(0, '" + pet_id + "', 3)\"></i>\n" +
//                        "            <i class=\"fa fa-star \" style=\"font-size:24px; padding: 5px;\" onclick=\"handleRating(0, '" + pet_id + "', 2)\"></i>\n" +
//                        "            <i class=\"fa fa-star \" style=\"font-size:24px; padding: 5px;\" onclick=\"handleRating(0, '" + pet_id + "', 1)\"></i>\n" +
//                        "        </div>");

                writer.println("<hr class=\"solid\">");
                writer.println("</div>");
            }
            writer.println("</div>"); //for class=row
            writer.println("</div>"); //for id= main
            writer.println("</body> </Html> ");
            stmt.close();

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