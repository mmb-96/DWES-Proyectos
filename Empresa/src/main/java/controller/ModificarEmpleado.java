package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import laboral.DB;
import laboral.Empleado;

/**
 * Servlet implementation class ModificarEmpleado
 */
public class ModificarEmpleado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarEmpleado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Empleado empl = null;
		RequestDispatcher rd;
		try {
			DB con = new DB();
			empl = con.persona(request.getParameter("dni"));
			con.disconnect();
		} catch (Exception e) {
		e.printStackTrace();
		rd = request.getRequestDispatcher("Error.html");
		}
		request.setAttribute("empleado", empl);
		rd = request.getRequestDispatcher("ModificarEmpleado.jsp");
		rd.forward(request, response);
	}

}
