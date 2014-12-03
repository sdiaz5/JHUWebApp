/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package emedina.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import emedina.resultBeans.User;

/**
 *
 * @author larva
 */
public class StoreBrowser extends HttpServlet {

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
        String url = "index.jsp";        //Default page to go if there is a logical error.
        StringBuffer error = null;
        
        try (PrintWriter out = response.getWriter()) {
            
            //If it's a get request go straight to index.page.
            if( request.getMethod() == "POST"){
                //As of right now, a POST should never happen if so then
                //go to index page instead
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }else{
                //Validate parameters and then request from DB.
                String type = request.getParameter("type");
                String start_time  = request.getParameter("start_time");
                String end_time = request.getParameter("end_time");
                
                if(type != null){
                    //Call worker bean to validate the type, and date request
                    
                    //The following conditional logic will be put into a worker bean
                    //If type == "motorcycle"
                        //call DB interface for Products[] getMotorcycles(startDate, endDate)
                        //set URL to products.jsp
                    //else if (type == "helmet")
                        //call DB interface for Products[] getHelmets(startDate, endDate)   
                        //set URL to accessorys.jsp
                    //else if (type == "jacket")
                        //call DB interface for Products[] getJackets(startDate, endDate)
                        //set URL to accessorys.jsp
                    //else if (type == "glove")
                        //call DB interface for Products[] getGloves() 
                        //set URL to accessorys.jsp
                    //else
                        //This is a logical error and request.setAttribute("error") 
                        //when forwarded to index.JSP the error message will appear there.
                    
                    //put resulting products[] into request
                    //request.setAttributes("products", products);
                }else{
                        //This is a logical error and request.setAttribute("error") 
                        //when forwarded to index.JSP the error message will appear there.
                }
                
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
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
