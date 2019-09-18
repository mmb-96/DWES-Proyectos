package Laboral;

public class CalculaNominas {

	public static void main(String[] args) {
		Empleado ep1 = null;
		try {
			ep1 = new Empleado("Jamer Cosling", "32000032G", 'M', 4, 7);
		} catch (DatosNoCorrectoException e) {
			e.printStackTrace();
		}
		Empleado ep2 = new Empleado("Ada Lovelace", "32000031R", 'F');

		System.out.println(escribe(ep1, ep2));

		ep2.incAnyo();
		try {
			ep1.setCategoria(9);
		} catch (DatosNoCorrectoException e) {
			e.printStackTrace();
		}

		System.out.println(escribe(ep1, ep2));

		try {
			ep1.setCategoria(20);
		} catch (DatosNoCorrectoException e) {
			e.printStackTrace();
		}

	}

	private static String escribe(Empleado e1, Empleado e2) {
		String nominaEmple;
		String empleados = null;
		empleados = e1.imprime();
		nominaEmple = String.valueOf(Nomina.sueldo(e1));
		empleados += " " + nominaEmple+"\n";
		empleados += e2.imprime();
		nominaEmple = String.valueOf(Nomina.sueldo(e2));
		empleados += " " + nominaEmple+"\n";
		return empleados;

	}


}
