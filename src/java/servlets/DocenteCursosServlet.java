/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.dao.CursoDAO;
import modelo.dao.dao.DocenteDAO;
import modelo.dao.to.Curso;
import utilitarios.DisponibilidadDocenteAuxiliar;
import modelo.dao.to.Docente;
import sesion.AdministradorSesion;
import validadores.ValidadorCursos;

@WebServlet(urlPatterns = {"/seleccionarcursos"})
public class DocenteCursosServlet extends HttpServlet {
    private DisponibilidadDocenteAuxiliar disponibilidadDocente = null;
    DocenteDAO docenteDAO;
    CursoDAO cursoDAO;
    @Override
    public void init() {
        docenteDAO = new DocenteDAO();
        cursoDAO = new CursoDAO();
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/docentecursos.jsp");
        
        if(AdministradorSesion.enSesion(request)){
            if(disponibilidadDocente == null)
                disponibilidadDocente = (DisponibilidadDocenteAuxiliar) request.getAttribute("disponibilidadDocente");

            Docente docente = docenteDAO.obtener(AdministradorSesion.ObtenerUsuarioActivo(request).getDNI());
            Map<String,List<Curso>> cursosPorEscuela = cursoDAO.obtenerPorEscuela();

            String[] cursosSeleccionados = new String[docente.getCategoria().getCursosCan()];
            for(int i = 0; i<docente.getCategoria().getCursosCan(); i++){
                cursosSeleccionados[i] = request.getParameter("cursoseleccionado"+(i+1));
            }

            if(cursosSeleccionados[0] != null){
                if(ValidadorCursos.validar(cursosSeleccionados, docente)){
                    dispatcher = request.getRequestDispatcher("registrardisponibilidad");
                    disponibilidadDocente.setCursosSeleccionados(cursosSeleccionados);
                    request.setAttribute("disponibilidadDocente", disponibilidadDocente);
                }else{     
                    request.setAttribute("cursosporescuela", cursosPorEscuela);
                    request.setAttribute("primerescuela", cursosPorEscuela.keySet().toArray()[0]); 

                    request.setAttribute("errorMessage","¡Recuerde que debe seleccionar dos cursos que llevó anteriormente y no puede repetir cursos!");
                }
            }else{           
                request.setAttribute("cursosporescuela", cursosPorEscuela);
                request.setAttribute("primerescuela", cursosPorEscuela.keySet().toArray()[0]); 
            }
            request.setAttribute("docente", docente);
        }else{
            disponibilidadDocente = null;
            docenteDAO = null;
            cursoDAO = null;
            dispatcher = request.getRequestDispatcher("index.jsp");
            //response.sendRedirect("/vistas/index.jsp");
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
