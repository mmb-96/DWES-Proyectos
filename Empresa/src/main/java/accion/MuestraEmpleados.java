/**
 * 
 */
package accion;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpleadoDao;
import laboral.Empleado;

/**
 * @author manu
 *
 */
public class MuestraEmpleados implements Facade {
	EmpleadoDao empDao;

	@Override
	public String ejecutar(ServletContext sc, HttpServletRequest request, HttpServletResponse response) {
		String pagSiguiente = "ListaEmpleados.jsp";
		List<Empleado> lista;
		try {
			lista = empDao.mostarDatosTodos();
			request.setAttribute("empleados", lista);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return pagSiguiente;
	}

}
