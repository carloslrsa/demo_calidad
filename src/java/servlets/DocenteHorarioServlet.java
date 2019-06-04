package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.to.DisponibilidadDocente;
import java.io.PrintWriter;
import modelo.dao.dao.CursoDAO;
import modelo.dao.dao.DocenteDAO;
import utilitarios.DisponibilidadDocenteAuxiliar;
import modelo.dao.to.Docente;
import sesion.AdministradorSesion;
import validadores.ValidadorHorario;

@WebServlet(urlPatterns = {"/seleccionarhorario"})
public class DocenteHorarioServlet extends HttpServlet {
    private Docente docente = null;
    
    DisponibilidadDocente generadorHorarioDocente;
    CursoDAO cursoDao;
    DocenteDAO docenteDao;
    
    private boolean primera = true;
    
    @Override
    public void init() {
        generadorHorarioDocente = new DisponibilidadDocente();
        cursoDao = new CursoDAO();
        docenteDao = new DocenteDAO();
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/docentehorario.jsp");
    
        if(AdministradorSesion.enSesion(request)){    
            docente = docenteDao.obtener(AdministradorSesion.ObtenerUsuarioActivo(request).getDNI());
            request.setAttribute("horario", generadorHorarioDocente.getHorario());
            request.setAttribute("docente", docente);

            String[] lunes = request.getParameterValues("lunes");
            String[] martes = request.getParameterValues("martes");
            String[] miercoles = request.getParameterValues("miercoles");
            String[] jueves = request.getParameterValues("jueves");
            String[] viernes = request.getParameterValues("viernes");
            String[] sabado= request.getParameterValues("sabado");

            if(!primera){
                if(lunes != null || martes != null || miercoles != null || jueves != null || viernes != null || sabado != null){
                    boolean valido = ValidadorHorario.validar(lunes, martes, miercoles, jueves, viernes, sabado, docente);
                    if(valido){
                        dispatcher = request.getRequestDispatcher("seleccionarcursos");
                        request.setAttribute("disponibilidadDocente", new DisponibilidadDocenteAuxiliar(lunes,martes,miercoles,jueves,viernes,sabado,null));
                        primera = true;
                    }else{
                        //Cantidad de horas inválida

                        //Test Message
                        request.setAttribute("errorMessage", "¡Debe llenar de "+docente.getCategoria().getHorasLleMin()+" a "+docente.getCategoria().getHorasLleMax()+" horas!");
                    }
                }else{
                    //Debe llenar XX horas como mínimo y XX como máximo
                    request.setAttribute("errorMessage", "¡Debe llenar de "+docente.getCategoria().getHorasLleMin()+" a "+docente.getCategoria().getHorasLleMax()+" horas!");
                }
            }else{
                primera = false;
            }
            
        }else{
            docente = null;
            generadorHorarioDocente = null;
            cursoDao = null;
            docenteDao = null;
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
