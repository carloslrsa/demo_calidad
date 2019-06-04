package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.dao.AdministradorDAO;
import modelo.dao.dao.SolicitudReingresoDAO;
import modelo.dao.to.Administrador;
import modelo.dao.to.SolicitudReingreso;
import sesion.AdministradorSesion;

@WebServlet(urlPatterns = {"/administrador"})
public class MenuAdministradorServlet extends HttpServlet {
    SolicitudReingresoDAO solicitudDAO;
    AdministradorDAO administradorDAO;
    
    @Override
    public void init() {
        solicitudDAO = new SolicitudReingresoDAO();
        administradorDAO = new AdministradorDAO();
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/administrador.jsp");
        
        Administrador administrador = administradorDAO.obtener(AdministradorSesion.ObtenerUsuarioActivo(request).getDNI());
        
        String atendida = request.getParameter("solicitudAtendida");
        
        if(atendida != null){
            if(!atendida.isEmpty()){
                if(solicitudDAO.actualizar(Integer.parseInt(atendida), administrador.getId())){
                    request.setAttribute("message", "Se atendió la solicitud seleccionada");
                }else{
                    request.setAttribute("message", "No se pudo atender la solicitud seleccionada");
                }
            }
        }
        
        List<SolicitudReingreso> solicitudes = solicitudDAO.obtenerPendientes();
        
        request.setAttribute("solicitudes", solicitudes);
        request.setAttribute("administrador", administrador);
        

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
