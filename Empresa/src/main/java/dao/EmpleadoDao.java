/**
 * 
 */
package dao;

import java.sql.SQLException;
import java.util.List;

import Laboral.DatosNoCorrectoException;
import Laboral.Empleado;

/**
 * @author manu
 *
 */
public class EmpleadoDao implements DAO {
	
	EmpleadoServicio empServi;

	public EmpleadoDao() {
		try {
			empServi = new EmpleadoServicio();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
	@Override
	public List<Empleado> mostarDatosTodos() throws Exception {
		List<Empleado> empleados;
		empleados = empServi.mostarDatosTodos();
		return empleados;
	}

	@Override
	public int salaraioDni(String dni) throws Exception {
		int sueldo;
		sueldo = empServi.salaraioDni(dni);
		return sueldo;
	}

	@Override
	public boolean actualizarSueldo(String dni) {
		Boolean vbol;
		vbol = empServi.actualizarSueldo(dni);
		return vbol;
	}

	@Override
	public boolean actualizarSueldoTodos() {
		Boolean vbol;
		vbol = empServi.actualizarSueldoTodos();
		return vbol;
	}

	@Override
	public boolean actualizarNombre(String Dni, String nombre) {
		Boolean vbol;
		vbol = empServi.actualizarNombre(Dni, nombre);
		return vbol;
	}

	@Override
	public boolean actualizarSexo(String Dni, char sexo) {
		Boolean vbol;
		vbol = empServi.actualizarSexo(Dni, sexo);
		return vbol;
	}

	@Override
	public boolean actualizarCategoria(String Dni, int cat) {
		Boolean vbol;
		vbol = empServi.actualizarCategoria(Dni, cat);
		return vbol;
	}

	@Override
	public boolean actualizarAnyos(String Dni, int anyos) {
		Boolean vbol;
		vbol = empServi.actualizarAnyos(Dni, anyos);
		return vbol;
	}

	@Override
	public Empleado persona(String dni) throws SQLException, DatosNoCorrectoException {
		Empleado emp;
		emp = empServi.persona(dni);
		return emp;
	}

}