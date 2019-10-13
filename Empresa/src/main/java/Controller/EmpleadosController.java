package Controller;

import java.io.IOException;
import java.sql.SQLException;
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
		 List<Empleado> lista = null;
		try {
			DB conn = new DB();
			lista = conn.mostarDatosTodos();
	        conn.disconnect();
		}catch (Exception e) {
		}
        request.setAttribute("empleados", lista);
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("ListaEmpleados.jsp");
        rd.forward(request, response);
        System.out.println(request.getAttribute("empleados"));
		 /*String action = request.getParameter("action");
	        if ("listaTotal".equals(action)) {
	        	try {
					this.listaTotal(request, response);
					System.out.println("action 1");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
	        }
	        System.out.println(request.toString());
	        System.out.println(response.toString());*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sueldo = 0;
		try {
			DB con = new DB();
			sueldo = con.salaraioDni(request.getParameter("dni"));
			con.disconnect();
		} catch (Exception e) {
		}
		request.setAttribute("dni", request.getParameter("dni"));
		request.setAttribute("salario", sueldo);
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("SalarioEmpleado.jsp");
		rd.forward(request, response);
		System.out.println("sueldo " + sueldo);
		/*String action = request.getParameter("action");
		if ("salario".equals(action)) {
        	try {
        		String dni = request.getParameter("dni");
				this.salario(request, response, dni);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	System.out.println("action 2");
        }
        System.out.println(request.toString());
        System.out.println(response.toString());*/
	}
	
	private void listaTotal(HttpServletRequest request, HttpServletResponse response) throws Exception {
			DB conn = new DB();
	        List<Empleado> lista = conn.mostarDatosTodos();
	        conn.disconnect();
	        request.setAttribute("empleados", lista);
	        RequestDispatcher rd;
	        rd = request.getRequestDispatcher("ListaEmpleados.jsp");
	        rd.forward(request, response);
	        System.out.println("listar total " + lista.toString());
	}
	
	private void salario(HttpServletRequest request, HttpServletResponse response, String dni) throws Exception {
		DB con = new DB();
		int sueldo = con.salaraioDni(dni);
		con.disconnect();
		request.setAttribute("salario", sueldo);
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("SalarioEmpleado.jsp");
		rd.forward(request, response);
		System.out.println("sueldo " + sueldo);

	}

}
