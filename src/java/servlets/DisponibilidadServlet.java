package servlets;

import modelo.dao.to.DisponibilidadDocente;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.dao.CursoDAO;
import modelo.dao.dao.DisponibilidadDocenteDAO;
import modelo.dao.to.Curso;
import utilitarios.DisponibilidadDocenteAuxiliar;
import modelo.dao.to.Docente;
import sesion.AdministradorSesion;

@WebServlet(urlPatterns = {"/registrardisponibilidad"})
public class DisponibilidadServlet extends HttpServlet {
    private Docente docente = null;
    private DisponibilidadDocenteAuxiliar disponibilidadDocente = null;
    
    private CursoDAO cursoDAO;
    @Override
    public void init() {
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/registrodisponibilidad.jsp");
        
        String registrar = request.getParameter("registrar");
                
        if(AdministradorSesion.enSesion(request)){
            if(docente == null)
                docente = (Docente) request.getAttribute("docente");

            if(disponibilidadDocente == null)
                disponibilidadDocente = (DisponibilidadDocenteAuxiliar) request.getAttribute("disponibilidadDocente");

            DisponibilidadDocente generadorDisponibilidad = new DisponibilidadDocente(disponibilidadDocente, docente.getId());
            
            if(registrar == null){

                Map<String, List<Curso>> cursos = cursoDAO.obtenerPorEscuelaPorId(disponibilidadDocente.getCursosSeleccionados());

                request.setAttribute("horario", generadorDisponibilidad.getHorario());
                request.setAttribute("cursos", cursos);
            }else{
                DisponibilidadDocenteDAO disponibilidadDAO = new DisponibilidadDocenteDAO();
                if(disponibilidadDAO.guardar(generadorDisponibilidad) > 0){
                    AdministradorSesion.Cerrar(request);
                    dispatcher = request.getRequestDispatcher("/index.jsp");
                }
                
            }
        }else{
            
            docente = null;
            disponibilidadDocente = null;
            cursoDAO = null;
            
            dispatcher = request.getRequestDispatcher("/vistas/index.jsp");
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
