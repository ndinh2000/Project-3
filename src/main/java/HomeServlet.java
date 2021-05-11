
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "HomeServlet", value="/HomeServlet")
public class HomeServlet extends HttpServlet {

    private static int id = 0;

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            getID(req, resp);

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                    + "petstore", "root", "root");
            Statement stmt = con.createStatement();
            String sql = "SELECT name, age, gender, price, message, profile_picture FROM petstore.pet";
            ResultSet rs = stmt.executeQuery(sql);


            PrintWriter writer = resp.getWriter();
            resp.setContentType("text/html;charset=UTF-8");
            writer.println("<!DOCTYPE html><html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>The Pet Shop | Home</title>\n");
            writer.println("<link rel='stylesheet' type='text/css' href='" + req.getContextPath() + "/myStyle.css' />\n");
            writer.println("</head>" +
                    "<body>\n" +
                    "    <div id=\"header\"><h1><img src=\"./images/Logo/shopLogo.png\"></h1></div>\n" +
                    "    <div id = \"top-nav-bar\">\n" +
                    "        <ul>\n" +
                    "            <li><a class=\"active\" href=\"./\"><h3> Home </h3></a></li>\n" +
                    "            <li><a href=./DogsServlet><h3> Dogs </h3></a></li>\n" +
                    "            <li><a href=./CatsServlet><h3> Cats </h3></a></li>\n" +
                    "            <li><a href=./ContactServlet><h3> Contact </h3></a></li>\n" +
                    "        </ul>\n" +
                    "    </div>\n" +
                    "\n");

            writer.println("</html>");

            String url1 = "/Last5";
            String url2 = "/Products";
            RequestDispatcher rd = req.getRequestDispatcher(url1);
            rd.include(req, resp);
            rd = req.getRequestDispatcher(url2);
            rd.include(req, resp);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private synchronized void getID(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        Integer user_id = (Integer) session.getAttribute("user_id");

        String heading;

        if (user_id == null) {
            session.setAttribute("user_id", this.id);
            ++this.id;
            heading = "Welcome, New-Comer, your ID is "
                    + session.getAttribute("user_id")
                    + ", nextID is " + this.id;
        } else {
            heading = "Welcome Back, your ID is "
                    + session.getAttribute("user_id")
                    + ", nextID is " + this.id;
        }

        /*  Print out heading for debugging  */
        PrintWriter out = response.getWriter();
        out.println(heading);
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