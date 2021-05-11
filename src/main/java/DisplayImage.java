/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import javax.servlet.ServletContext;
import java.awt.image.*;
import javax.imageio.ImageIO;

/**
 *
 * @author ndinh
 */
public class DisplayImage extends HttpServlet {
    
//    public final String imagesBase = "C:\\Users\\ndinh\\Documents\\INF 124 Internet Application Engineering\\Code\\Project 2\\PA2\\pics/";

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
        
        /*
            Important: Put images inside PA2\src\main\webapp\images before building
        */
        
        String URLAfterWebDomain = request.getRequestURI();        
        if(URLAfterWebDomain.startsWith("/PA2/images/") == false)   
            return;
        
        ServletOutputStream out;  
        out = response.getOutputStream();

        ServletContext servletContext = request.getSession().getServletContext();
        String base = servletContext.getRealPath("/images/");
        String relativeImagePath = URLAfterWebDomain.substring("/PA2/images/".length());

//        response.setContentType("text/html");
//        PrintWriter writer = response.getWriter();
//        writer.println("<Html> <body>");
//        writer.println("<p>");
//        writer.println(base);
//        writer.println("</p>");
//        writer.println("<br/>");
//        writer.println(relativeImagePath);
//        writer.println("<br/>");
//        writer.println(URLAfterWebDomain);
//        writer.println("<p>asd</p>");
//        writer.println("</body> </Html> ");
        
        response.setContentType("image/jpeg");
        ServletOutputStream outStream;
        outStream = response.getOutputStream();
        FileInputStream fin = new FileInputStream(base + relativeImagePath);
        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(outStream);
        int ch =0; ;
        while((ch=bin.read())!=-1)
            bout.write(ch);

        bin.close();
        fin.close();
        bout.close();
        outStream.close();
//    }
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
