
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
//import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

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
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);
        System.out.println(request);
        WebTarget target = client.target("http://localhost:8088/PA3_Rest/v1/api/ratings/" + request.getParameter("pet_id"));
        String jsonResponse =
                target.
                        request(). //send a request
                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                        get(String.class);
        System.out.println(jsonResponse);

        ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library

        List<Rating> ratings = objectMapper.readValue(jsonResponse, new TypeReference<List<Rating>>(){});

        int total = 0, count = 0;
        for (Rating e : ratings) {
            total += e.getRating();
            ++count;
        }

        PrintWriter writer = response.getWriter();
        writer.println("<div class=\"rate-container-fixed\" style=\"height:35px; display: flex; flex-direction: row-reverse; justify-content: center;\">");
        if (count > 0) {
            int stars = (int) Math.round((double) total / (double) count);
            for (int i = 5; i > stars; --i) {
                writer.println("<i class=\"fa fa-star \" style=\"font-size:24px; padding: 5px; color: grey\"></i>");
            }
//            writer.println("<p>asdf</p>");
            for (int i = stars; i > 0; --i) {
                writer.println("<i class=\"fa fa-star \" style=\"color: gold; font-size:24px; padding: 5px;\"></i>");
            }
        } else {
            writer.println("No Review Yet");
        }
        writer.println("</div>");
        writer.println("</body> </Html>");

//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
//                    + "petstore", "root", "root");
//            Statement stmt = con.createStatement();
//            String pet_id = request.getParameter("pet_id");
//            String sql = "SELECT rating FROM ratings WHERE pet_id = '" + pet_id + "';";
//            ResultSet rs = stmt.executeQuery(sql);
//
//            PrintWriter writer = response.getWriter();
//            int total = 0, count = 0;
//            while (rs.next()) {
//                total += rs.getFloat("rating");
//                ++count;
//            }
//
//            writer.println("<div class=\"rate-container-fixed\" style=\"height:35px; display: flex; flex-direction: row-reverse; justify-content: center;\">");
//            if (count > 0) {
//                int stars = (int) Math.round((double) total / (double) count);
//                for (int i = 5; i > stars; --i) {
//                    writer.println("<i class=\"fa fa-star \" style=\"font-size:24px; padding: 5px; color: grey\"></i>");
//                }
//                for (int i = stars; i > 0; --i) {
//                    writer.println("<i class=\"fa fa-star \" style=\"color: gold; font-size:24px; padding: 5px;\"></i>");
//                }
//            } else {
//                writer.println("No Review Yet");
//            }
//            writer.println("</div>");
//            writer.println("</body> </Html>");
//            stmt.close();
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
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