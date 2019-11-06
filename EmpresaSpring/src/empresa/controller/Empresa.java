/**
 * 
 */
package empresa.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Laboral.DatosNoCorrectoException;
import Laboral.Empleado;
import dao.EmpleadoDao;

/**
 * @author manu
 *
 */
@Controller
public class Empresa {
	
	private  EmpleadoDao empDao = new EmpleadoDao();
	private String pagSiguiente = null;
	

	@RequestMapping("/ListaEmpleados")
	public ModelAndView listaEmpleados() {
		List<Empleado> lista = null;
		try {
			lista = empDao.mostarDatosTodos();
			pagSiguiente = "ListaEmpleados";
		} catch (Exception e) {
			pagSiguiente = "Error";
			e.printStackTrace();
		}
		return new ModelAndView(pagSiguiente, "empleados", lista);
	}
	
	@RequestMapping("/DniSalario")
	public ModelAndView dniSalario() {
			pagSiguiente = "DniSalario";
		return new ModelAndView(pagSiguiente);
	}
	
	@RequestMapping("/BuscarEmpleado")
	public ModelAndView buscarEmpleado() {
			pagSiguiente = "BuscarEmpleado";
		return new ModelAndView(pagSiguiente);
	}
	
	@RequestMapping(value = "/SalarioEmpleado", method = RequestMethod.POST)
	public ModelAndView salarioEmpleado(HttpServletRequest request) {
		String dni = request.getParameter("dni");
		int sueldo = 0;
		try {
			sueldo = empDao.salaraioDni(dni);
			request.setAttribute("dni", dni);
			request.setAttribute("salario", sueldo);
			pagSiguiente = "SalarioEmpleado";
		} catch (Exception e) {
			pagSiguiente = "Error";
			e.printStackTrace();
		}
		return new ModelAndView(pagSiguiente, "salario", sueldo);
	}
	
	@RequestMapping(value = "/EmpleadoModificado", method = RequestMethod.POST)
	public ModelAndView modificarEmpleado(HttpServletRequest request) {
		String dni = request.getParameter("dni");
		empDao.actualizarNombre(dni, request.getParameter("nombre"));
		empDao.actualizarSexo(dni, request.getParameter("sexo").charAt(0));
		empDao.actualizarCategoria(dni, Integer.parseInt(request.getParameter("categoria")));;
		empDao.actualizarAnyos(dni, Integer.parseInt(request.getParameter("anyos")));
		empDao.actualizarSueldo(dni);
		pagSiguiente = "Exito";
		return new ModelAndView(pagSiguiente);
	}
	
	@RequestMapping(value = "/ModificarEmpleado", method = RequestMethod.POST)
	public ModelAndView recuperarEmpleados(HttpServletRequest request) {
		Empleado empl = null;
		try {
			empl = empDao.persona(request.getParameter("dni"));
			pagSiguiente = "ModificarEmpleado";
		} catch (SQLException | DatosNoCorrectoException e) {
			e.printStackTrace();
			pagSiguiente = "Error";
		}
		return new ModelAndView(pagSiguiente, "empleado", empl);
	}
}
