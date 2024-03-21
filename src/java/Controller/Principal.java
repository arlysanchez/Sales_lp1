/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Person;
import Model.PersonDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author tpp
 */
public class Principal extends HttpServlet {

    PersonDao pDao = new PersonDao();
    Person p = new Person();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion.equals("Ingresar")) {
            String user = request.getParameter("txt_user");
            String passw = request.getParameter("txt_passw");
            p = pDao.validate(user, passw);
            if (p.getUser() != null) {
                // Obtener la sesión actual o crear una nueva si no existe
                HttpSession session = request.getSession(true);
                // Guardar los datos del usuario en la sesión
                session.setAttribute("usuario", p);
                request.getRequestDispatcher("Controller?op=dashboard").forward(request, response);
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else if (accion.equals("Salir")) {
            // Obtener la sesión actual
            HttpSession session = request.getSession(false);
            if (session != null) {
                // Invalidar la sesión si existe
                session.invalidate();
            }
            // Redirigir de vuelta a la página de inicio de sesión
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
