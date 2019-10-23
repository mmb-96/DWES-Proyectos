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

import Laboral.DB;
import Laboral.Empleado;

/**
 * Servlet implementation class EmpresaController
 */
public class EmpresaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DB con = null;
    /**
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public EmpresaController() throws ClassNotFoundException, SQLException {
        super();
        con = new DB();
    }
    
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		 try {
			con = new DB();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		con.disconnect();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		String action = request.getParameter("action");
		try {
			switch (action) {
				case "Muestra empleados":
					this.listaTotal(request, response);
				    rd = request.getRequestDispatcher("ListaEmpleados.jsp");
					break;
				case "Muestra sueldo":
					this.mostarSueldo(request, response);
					rd = request.getRequestDispatcher("SalarioEmpleado.jsp");
					break;
				case "Recuperar empleados":
					this.obtenerEmpleado(request, response);
					rd = request.getRequestDispatcher("ModificarEmpleado.jsp");
					break;
				case "Modificar empleados":
					this.modificarEmpleado(request, response);
					rd = request.getRequestDispatcher("Exito.html");					
					break;
				default:
					System.out.println(action);
					rd = request.getRequestDispatcher("Error.html");
					break;
			}
		} catch (Exception e) {
			System.out.println(e);
			rd = request.getRequestDispatcher("Error.html");
		}
		rd.forward(request, response);
	}
	
	private void listaTotal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Empleado> lista = con.mostarDatosTodos();
        request.setAttribute("empleados", lista);
	}
	
	private void mostarSueldo (HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(request.getParameter("dni"));
		String dni = request.getParameter("dni");
		int	sueldo = con.salaraioDni(dni);
		request.setAttribute("dni", dni);
		request.setAttribute("salario", sueldo);
	}
	
	private void obtenerEmpleado (HttpServletRequest request, HttpServletResponse response) throws Exception {
		Empleado empl = con.persona(request.getParameter("dni"));
		request.setAttribute("empleado", empl);
	}
	
	private void modificarEmpleado (HttpServletRequest request, HttpServletResponse response) throws Exception {
		String dni = request.getParameter("dni");
		con.actualizarNombre(dni, request.getParameter("nombre"));
		con.actualizarSexo(dni, request.getParameter("sexo").charAt(0));
		con.actualizarCategoria(dni, Integer.parseInt(request.getParameter("categoria")));;
		con.actualizarAnyos(dni, Integer.parseInt(request.getParameter("anyos")));
		con.actualizarSueldo(dni);
	}
}
