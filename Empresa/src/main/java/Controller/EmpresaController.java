package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Laboral.Empleado;
import accion.Facade;
import accion.ModificarEmpleados;
import accion.MuestraEmpleados;
import accion.MuestraSueldo;
import accion.RecuperarEmpleados;

/**
 * Servlet implementation class EmpresaController
 */
public class EmpresaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public EmpresaController() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		Facade ac = null;
		String pagSiguiente = null;
		String action = request.getParameter("action");
		try {
			switch (action) {
				case "Muestra empleados":
					ac = new MuestraEmpleados();
					pagSiguiente = ac.ejecutar(getServletContext(), request, response);
					break;
				case "Muestra sueldo":
					ac = new MuestraSueldo();
					pagSiguiente = ac.ejecutar(getServletContext(), request, response);
					break;
				case "Recuperar empleados":
					ac = new RecuperarEmpleados();
					pagSiguiente = ac.ejecutar(getServletContext(), request, response);
					break;
				case "Modificar empleados":
					ac = new ModificarEmpleados();
					pagSiguiente = ac.ejecutar(getServletContext(), request, response);
	    	 			pagSiguiente = "Exito.html";
					break;
				default:
					pagSiguiente = "Error.html";
					break;
			}
		} catch (Exception e) {
			pagSiguiente = "Error.html";
		}
		rd = request.getRequestDispatcher(pagSiguiente);
		rd.forward(request, response);
	}
}