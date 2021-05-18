package com.mycompany.PA3;


import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/TaxRef"})
public class TaxRef extends HttpServlet {

    HashMap<String, String> map = new HashMap<String, String>();

    //Zip codes follow these rules: http://www.structnet.com/instructions/zip_min_max_by_state.html
    public void init(ServletConfig config) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\azhon\\IdeaProjects\\Project-3\\src\\main\\webapp\\tax_rates2.csv"));
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
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String state = request.getParameter("state");
//        int state = Integer.parseInt(zip);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            for (String s : map.keySet()) {
                if (state.equals(s)) {
                    out.write(map.get(s));
                    break;
                }
            }
            // out.write("null");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
    }
}

