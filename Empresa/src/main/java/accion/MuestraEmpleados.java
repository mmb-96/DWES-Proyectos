/**
 * 
 */
package accion;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Laboral.Empleado;
import dao.EmpleadoDao;

/**
 * @author manu
 *
 */
public class MuestraEmpleados implements Facade {
	EmpleadoDao empDao;

	public MuestraEmpleados() {
		empDao = new EmpleadoDao();
	}
	@Override
	public String ejecutar(ServletContext sc, HttpServletRequest request, HttpServletResponse response) {
		String pagSiguiente;
		List<Empleado> lista;
		try {
			lista = empDao.mostarDatosTodos();
			request.setAttribute("empleados", lista);
			pagSiguiente = "ListaEmpleados.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			pagSiguiente = "Error.html";
		}
		return pagSiguiente;
	}

}
