/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.controller;


import com.daniel.dao.UserDao;
import com.daniel.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Labing I5
 */
public class Login extends HttpServlet {

   

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
        HttpSession sesionUsuario = request.getSession();
        User _sesionUsuario = (User)sesionUsuario.getAttribute("usuario");
        if(_sesionUsuario!=null){
          sesionUsuario.invalidate();
          response.sendRedirect("indexUser.jsp");
        }
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
        UserDao userDao = null;
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        //Validaciones que deben ser realizadas
/*        
         Parameters should be validated against positive specs:
        – Data type (string, integer, real, etc…)
        – Minimum and maximum length
        – Whether null is allowed
        – Whether the parameter is required or not
        – Numeric range
        – Specific patterns (regular expressions)
        - Modify default error pages (404, 401, etc.)
        - Error Handling - Measures
        - Use strong authentication mechanisms:
                – Password policies (strength, use, change control, storage). – Secure transport (SSL).
                – Carefully implement forgotten password functionality.
                – Remove default usernames
        https://www.owasp.org/images/8/83/Securing_Enterprise_Web_Applications_at_the_Source.pdf
        
*/
        
        User datosUsuario = new User();
        datosUsuario.setLogin(login);
        datosUsuario.setPass(pass);
        
        //Validaciones
        
        try {
            userDao = new UserDao();
        } catch (URISyntaxException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        User sesion = userDao.validar(datosUsuario);
        HttpSession sesionUsuario = request.getSession();
        User _sesionUsuario = (User)sesionUsuario.getAttribute("usuario");
        if(_sesionUsuario==null){
         //El usuario no a creado la sesion
          if(sesion != null){
            sesionUsuario.setAttribute("usuario", sesion);
            sesionUsuario.setMaxInactiveInterval(20);
            response.sendRedirect("index.jsp");
          }else{
             response.sendRedirect("error.jsp");
          }
         
        }else{
          response.sendRedirect("indexUser.jsp");
        }        
        
        if(sesion !=null){
           
        }else{
          request.setAttribute("Error", "Revisar usuario/ pass");
          RequestDispatcher rq =  request.getRequestDispatcher("index.jsp");
          rq.forward(request, response);
        }
        
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
