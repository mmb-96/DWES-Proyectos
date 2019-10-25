/**
 * 
 */
package accion;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpleadoDao;

/**
 * @author manu
 *
 */
public class MuestraSueldo implements Facade {
	EmpleadoDao empDao;

	public MuestraSueldo() {
		empDao = new EmpleadoDao();
	}
	
	@Override
	public String ejecutar(ServletContext sc, HttpServletRequest request, HttpServletResponse response) {
		String dni = request.getParameter("dni");
		String pagSiguiente;
		int sueldo;
		try {
			sueldo = empDao.salaraioDni(dni);
			request.setAttribute("dni", dni);
			request.setAttribute("salario", sueldo);
			pagSiguiente = "SalarioEmpleado.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			pagSiguiente = "Error.html";
		}
		return pagSiguiente;
	}

}
