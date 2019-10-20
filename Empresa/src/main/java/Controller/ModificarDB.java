package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Laboral.DB;

/**
 * Servlet implementation class ModificarDB
 */
public class ModificarDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarDB() {
        super();
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dni = request.getParameter("dni");
		RequestDispatcher rd;
		try {
			DB con = new DB();
			con.actualizarNombre(dni, request.getParameter("nombre"));
			con.actualizarSexo(dni, request.getParameter("sexo").charAt(0));
			con.actualizarCategoria(dni, Integer.parseInt(request.getParameter("categoria")));
			con.actualizarAnyos(dni, Integer.parseInt(request.getParameter("anyos")));
			con.actualizarSueldoTodos();
			rd = request.getRequestDispatcher("Exito.html");
		} catch (Exception e) {
			e.printStackTrace();
			rd = request.getRequestDispatcher("Error.html");
		}
		rd.forward(request, response);
		
	}
}