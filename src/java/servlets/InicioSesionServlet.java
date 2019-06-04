/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sesion.AdministradorSesion;
import sesion.Usuario;

@WebServlet(urlPatterns = {"/iniciosesion"})
public class InicioSesionServlet extends HttpServlet {
    @Override
    public void init() {
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
        doPost(request,response);
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
        RequestDispatcher dispatcher;
        
        String DNI = request.getParameter("dni");
        String password = request.getParameter("pass");
        
        int tipo = 0;
        if(DNI != null && password != null && !DNI.isEmpty() && !password.isEmpty())
            tipo = AdministradorSesion.Iniciar(DNI, password, request);
        
        if(tipo > 0){
        
            request.setAttribute("DNI", DNI);
            
            if(tipo == 1){
                //Administrador
                dispatcher = request.getRequestDispatcher("/administrador");
            }else{
                //Docente
                dispatcher = request.getRequestDispatcher("/docente");
            }
            //dispatcher = request.getRequestDispatcher("/seleccionarhorario");
        }else{
            dispatcher = request.getRequestDispatcher("/index.jsp");
            
            request.setAttribute("errorMessage", "Credenciales inválidas");
        }

        dispatcher.forward(request, response);   
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
