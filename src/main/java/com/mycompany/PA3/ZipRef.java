package com.mycompany.PA3;


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

HashMap<int[], String> map = new HashMap<int[], String>();
//Zip codes follow these rules: http://www.structnet.com/instructions/zip_min_max_by_state.html
    public void init (ServletConfig config)
    {
        map.put(new int[]{99501, 99950}, "AK,");
        map.put(new int[]{35004, 36925}, "AL,");
        map.put(new int[]{71601, 72959}, "AR,");
        map.put(new int[]{75502, 75502}, "AR,");
        map.put(new int[]{85001, 86556}, "AZ,");
        map.put(new int[]{90001, 96162}, "CA,");
        map.put(new int[]{80001, 81658}, "CO,");
        map.put(new int[]{6001, 6389}, "CT,");
        map.put(new int[]{6401, 6928}, "CT,");
        map.put(new int[]{20001, 20039}, "DC,");
        map.put(new int[]{20042, 20599}, "DC,");
        map.put(new int[]{20799, 20799}, "DC,");
        map.put(new int[]{19701, 19980}, "DE,");
        map.put(new int[]{32004, 34997}, "FL,");
        map.put(new int[]{30001, 31999}, "GA,");
        map.put(new int[]{39901, 39901}, "GA,");
        map.put(new int[]{96701, 96898}, "HI,");
        map.put(new int[]{50001, 52809}, "IA,");
        map.put(new int[]{68119, 68120}, "IA,");
        map.put(new int[]{83201, 83876}, "ID,");
        map.put(new int[]{60001, 62999}, "IL,");
        map.put(new int[]{46001, 47997}, "IN,");
        map.put(new int[]{66002, 67954}, "KS,");
        map.put(new int[]{40003, 42788}, "KY,");
        map.put(new int[]{70001, 71232}, "LA,");
        map.put(new int[]{71234, 71497}, "LA,");
        map.put(new int[]{1001, 2791}, "MA,");
        map.put(new int[]{5501, 5544}, "MA,");
        map.put(new int[]{20331, 20331}, "MD,");
        map.put(new int[]{20335, 20797}, "MD,");
        map.put(new int[]{20812, 21930}, "MD,");
        map.put(new int[]{3901, 4992}, "ME,");
        map.put(new int[]{48001, 49971}, "MI,");
        map.put(new int[]{55001, 56763}, "MN,");
        map.put(new int[]{63001, 65899}, "MO,");
        map.put(new int[]{38601, 39776}, "MS,");
        map.put(new int[]{71233, 71233}, "MS,");
        map.put(new int[]{59001, 59937}, "MT,");
        map.put(new int[]{27006, 28909}, "NC,");
        map.put(new int[]{58001, 58856}, "ND,");
        map.put(new int[]{68001, 68118}, "NE,");
        map.put(new int[]{68122, 69367}, "NE,");
        map.put(new int[]{3031, 3897}, "NH,");
        map.put(new int[]{7001, 8989}, "NJ,");
        map.put(new int[]{87001, 88441}, "NM,");
        map.put(new int[]{88901, 89883}, "NV,");
        map.put(new int[]{6390, 6390}, "NY,");
        map.put(new int[]{10001, 14975}, "NY,");
        map.put(new int[]{43001, 45999}, "OH,");
        map.put(new int[]{73001, 73199}, "OK,");
        map.put(new int[]{73401, 74966}, "OK,");
        map.put(new int[]{97001, 97920}, "OR,");
        map.put(new int[]{15001, 15001}, "PA,");
        map.put(new int[]{0, 0}, "PR,");
        map.put(new int[]{2801, 2940}, "RI,");
        map.put(new int[]{29001, 29948}, "SC,");
        map.put(new int[]{57001, 57799}, "SD,");
        map.put(new int[]{37010, 38589}, "TN,");
        map.put(new int[]{73301, 73301}, "TX,");
        map.put(new int[]{75001, 75501}, "TX,");
        map.put(new int[]{75503, 79999}, "TX,");
        map.put(new int[]{88510, 88589}, "TX,");
        map.put(new int[]{84001, 84784}, "UT,");
        map.put(new int[]{20040, 20041}, "VA,");
        map.put(new int[]{20040, 20167}, "VA,");
        map.put(new int[]{20042, 20042}, "VA,");
        map.put(new int[]{22001, 24658}, "VA,");
        map.put(new int[]{5001, 5495}, "VT,");
        map.put(new int[]{5601, 5907}, "VT,");
        map.put(new int[]{98001, 99403}, "WA,");
        map.put(new int[]{53001, 54990}, "WI,");
        map.put(new int[]{24701, 26886}, "WV,");
        map.put(new int[]{82001, 83128}, "WY,");

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String zip = request.getParameter("zip");
        int zipI = Integer.parseInt(zip);
        response.setContentType("text/html;charset=UTF-8");

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