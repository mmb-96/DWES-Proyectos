package Laboral;

public class Persona {
	
	public String nombre;
	public String dni;
	public char sexo;
	
	/**
	 * @param nombre
	 * @param dni
	 * @param sexo
	 */
	public Persona(String nombre, String dni, char sexo) {
		this.nombre = nombre;
		this.dni = dni;
		this.sexo = sexo;
	}

	/**
	 * @param nombre
	 * @param sexo
	 */
	public Persona(String nombre, char sexo) {
		super();
		this.nombre = nombre;
		this.sexo = sexo;
	}
	
	/**
	 * @param dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	/**
	 * @return
	 */
	public String imprime() {
		return "nombre:" + nombre + ", dni:" + dni;
	}
	
	

}
