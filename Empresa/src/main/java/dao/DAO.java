/**
 * 
 */
package dao;

import java.sql.SQLException;
import java.util.List;

import laboral.DatosNoCorrectoException;
import laboral.Empleado;

/**
 * @author manu
 *
 */
public interface DAO {
	
	public List<Empleado> mostarDatosTodos() throws Exception;
	
	public int salaraioDni(String dni) throws Exception;
	
	public boolean actualizarSueldo(String dni);
	
	public boolean actualizarSueldoTodos();
	
	public boolean actualizarNombre(String Dni, String nombre);
	
	public boolean actualizarSexo(String Dni, char sexo);
	
	public boolean actualizarCategoria(String Dni, int cat);
	
	public boolean actualizarAnyos(String Dni, int anyos);
	
	public Empleado persona(String dni) throws SQLException, DatosNoCorrectoException;

}
