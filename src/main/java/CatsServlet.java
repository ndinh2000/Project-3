import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebListener
//class Config implements ServletContextListener {
//    doGet();
//}

@WebServlet(name = "CatsServlet", value="/CatsServlet")
public class CatsServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                    + "petstore", "root", "root");
            Statement stmt = con.createStatement();
            String sql = "SELECT pet_id,name, age, gender, price, SUBSTRING(message, 1, 65) AS message, " +
                    "profile_picture FROM petstore.pet WHERE pet_id LIKE 'C%'";
            ResultSet rs = stmt.executeQuery(sql);

            PrintWriter writer = resp.getWriter();
            writer.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>The Pet Shop | Home</title>\n");
            writer.println("<link rel='stylesheet' type='text/css' href='" + req.getContextPath() +  "/myStyle.css' />\n");
            writer.println("<link rel='stylesheet' type='text/css' href='https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css' />\n");
            writer.println("</head>"+
                    "<body>\n" +
                    "    <div id=\"header\"><h1><img src=\"./images/Logo/shopLogo.png\"></h1></div>\n" +
                    "    <div id = \"top-nav-bar\">\n" +
                    "        <ul>\n" +
                    "            <li><a href=/PA3><h3> Home </h3></a></li>\n" +
                    "            <li><a href=./DogsServlet><h3> Dogs </h3></a></li>\n" +
                    "            <li><a class=\"active\" href=\"./CatsServlet\"><h3> Cats </h3></a></li>\n" +
                    "            <li><a href=./ContactServlet><h3> Contact </h3></a></li>\n" +
                    "        </ul>\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <div id=\"main\">\n" +
                    "        <!-- <div class=\"featuredPets\"> -->\n" +
                    "            <div class=\"row\">");


            String imgPath = "";
            while (rs.next()) {
                writer.println("<div class=\"col-3 col-s-5 featuredPets\">");
                writer.println("<a href=\"/PA3/ProductDetail?pet_id=" + rs.getString("pet_id") + "\">");
                writer.println("<div style=\"height: 275px;\">");
                imgPath = rs.getString("profile_picture");
                writer.println("<img src="+ imgPath +">");
                writer.println("</div>"); //for style=height
                writer.println("<h3>" + rs.getString("name") +
                        " - $" + rs.getString("price") + "</h3>");
                writer.println("</a>");
                writer.println("<p>"+ rs.getString("message") +"...</p>");
                String url = "/GetRating?pet_id=" + rs.getString("pet_id");
                RequestDispatcher rd = req.getRequestDispatcher(url);
                rd.include(req, resp);
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
}