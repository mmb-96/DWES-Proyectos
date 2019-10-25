package Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Laboral.DB;
import Laboral.Empleado;

/**
 * Servlet implementation class EmpleadosController
 */
public class EmpleadosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpleadosController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String action = request.getParameter("action");
	        if ("listaTotal".equals(action)) {
	        	try {
					this.listaTotal(request, response);					
				} catch (Exception e) {
					e.printStackTrace();
				}
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		String dni = request.getParameter("dni");
		int sueldo = 0;
		DB con;
		try {
			con = new DB();
			sueldo = con.salaraioDni(dni);
			con.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("dni", dni);
		request.setAttribute("salario", sueldo);
		rd = request.getRequestDispatcher("SalarioEmpleado.jsp");
		rd.forward(request, response);
	}
	
	private void listaTotal(HttpServletRequest request, HttpServletResponse response) throws Exception {
			DB conn = new DB();
	        List<Empleado> lista = conn.mostarDatosTodos();
	        conn.disconnect();
	        request.setAttribute("empleados", lista);
	        RequestDispatcher rd;
	        rd = request.getRequestDispatcher("ListaEmpleados.jsp");
	        rd.forward(request, response);
	}
}