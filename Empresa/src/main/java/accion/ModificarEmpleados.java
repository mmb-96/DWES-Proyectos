package accion;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpleadoDao;

public class ModificarEmpleados implements Facade {
	EmpleadoDao empDao;
	
	public ModificarEmpleados() {
		empDao = new EmpleadoDao();
	}

	@Override
	public String ejecutar(ServletContext sc, HttpServletRequest request, HttpServletResponse response) {
		String pagSiguiente;
		String dni = request.getParameter("dni");
		empDao.actualizarNombre(dni, request.getParameter("nombre"));
		empDao.actualizarSexo(dni, request.getParameter("sexo").charAt(0));
		empDao.actualizarCategoria(dni, Integer.parseInt(request.getParameter("categoria")));;
		empDao.actualizarAnyos(dni, Integer.parseInt(request.getParameter("anyos")));
		empDao.actualizarSueldo(dni);
		pagSiguiente = "Exito.html";
		return pagSiguiente;
	}

}
