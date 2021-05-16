package com.mycompany.PA2;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/ZipRef"})
public class ZipRef extends HttpServlet{

//    HashMap<String, String> map = new HashMap<String, String>();
HashMap<int[], String> map = new HashMap<int[], String>();

    public void init (ServletConfig config)
    {
        map.put(new int[]{350, 369}, "AL,");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String zip = request.getParameter("zip");
        int zipI = Integer.parseInt(zip);
        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            if (map.containsKey(zip)) {
//                System.out.println(map.get(zip));
//                out.write(map.get(zip));
//            }else
//                out.write(" , ");
//        }
        try (PrintWriter out = response.getWriter()) {
            for (int[] pair : map.keySet()) {
                if (zipI >= pair[0] && zipI <= pair[1] ) {
                    if (map.containsKey(pair)) {
                        out.write(map.get(pair));
                    }
                }
            }
            out.write(" , ");
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