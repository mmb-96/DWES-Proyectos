package accion;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Laboral.DatosNoCorrectoException;
import Laboral.Empleado;
import dao.EmpleadoDao;

public class RecuperarEmpleados implements Facade {
	EmpleadoDao empDao;
	
	public RecuperarEmpleados() {
		empDao = new EmpleadoDao();
	}

	@Override
	public String ejecutar(ServletContext sc, HttpServletRequest request, HttpServletResponse response) {
		String pagSiguiente;
		Empleado empl;
		try {
			empl = empDao.persona(request.getParameter("dni"));
			request.setAttribute("empleado", empl);
			pagSiguiente = "ModificarEmpleado.jsp";
		} catch (SQLException | DatosNoCorrectoException e) {
			e.printStackTrace();
			pagSiguiente = "Error.html";
		}
		return pagSiguiente;
	}

}
