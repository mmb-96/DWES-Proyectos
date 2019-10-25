package accion;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Facade {
	
	public String ejecutar(ServletContext sc, HttpServletRequest request, HttpServletResponse response);

}
