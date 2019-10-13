package Controller;

import java.io.IOException;
import java.sql.SQLException;

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
		try {
			DB con = new DB();
			con.actualizarNombre(request.getParameter("dni"), request.getParameter("categoria"));
			con.actualizarSexo(request.getParameter("dni"), request.getParameter("sexo").charAt(0));
			con.actualizarCategoria(request.getParameter("dni"), Integer.parseInt(request.getParameter("categoria")));
			con.actualizarAnyos(request.getParameter("dni"), Integer.parseInt(request.getParameter("anyos")));
			con.actualizarSueldo(request.getParameter("dni"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}