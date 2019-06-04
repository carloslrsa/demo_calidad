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
import modelo.dao.dao.DisponibilidadDocenteDAO;
import modelo.dao.dao.DocenteDAO;
import modelo.dao.dao.SolicitudReingresoDAO;
import modelo.dao.to.Curso;
import modelo.dao.to.DisponibilidadDocente;
import modelo.dao.to.Docente;
import modelo.dao.to.SolicitudReingreso;
import sesion.AdministradorSesion;
import utilitarios.DisponibilidadDocenteAuxiliar;

@WebServlet(urlPatterns = {"/docente"})
public class MenuDocenteServlet extends HttpServlet {

    DisponibilidadDocenteDAO disponibilidadDAO;
    DocenteDAO docenteDAO;
    CursoDAO cursoDAO;
    @Override
    public void init() {
        disponibilidadDAO = new DisponibilidadDocenteDAO();
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/docente.jsp");
        Docente docente = null;
        if(AdministradorSesion.enSesion(request)){
            
            String solicitar = request.getParameter("solicitar");
            String motivo = request.getParameter("motivo");
            docente = docenteDAO.obtener((String) AdministradorSesion.ObtenerUsuarioActivo(request).getDNI());
            DisponibilidadDocente disponibilidad = disponibilidadDAO.obtener(docente.getId());

            if(disponibilidad == null)
                dispatcher = request.getRequestDispatcher("/seleccionarhorario");
            else{
                Map<String, List<Curso>> cursos = cursoDAO.obtenerPorEscuelaPorId(disponibilidad.getCursos());

                request.setAttribute("docente", docente);
                request.setAttribute("horario", disponibilidad.getHorario());
                request.setAttribute("cursos", cursos);
            }
            if(solicitar != null){
                if(motivo != null){
                    if(!motivo.isEmpty()){
                        SolicitudReingresoDAO solicitudDAO = new SolicitudReingresoDAO();
                        SolicitudReingreso solicitud = new SolicitudReingreso(0,docente,motivo);
                        if(solicitudDAO.guardar(solicitud)){
                            request.setAttribute("message", "Se envió la solicitud con éxito");
                        }else{
                            request.setAttribute("message", "Usted ya cuenta con una solicitud pentiente");
                        }
                    }else{
                        request.setAttribute("message", "Debe agregar un motivo");
                    }
                }else{
                    request.setAttribute("message", "Debe agregar un motivo");
                }
            }
        }else{
            disponibilidadDAO = null;
            docenteDAO = null;
            docente = null;
            
            dispatcher = request.getRequestDispatcher("index.jsp");
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
