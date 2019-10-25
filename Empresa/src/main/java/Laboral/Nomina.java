/**
 * 
 */
package laboral;

/**
 * @author manu
 *
 */
public class Nomina {
	
		private static final int SUELDO_BASE[] = {50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000, 230000};

		/**
		 * @param Empleado
		 * @return
		 */
		public static int sueldo(Empleado ep) {
			int categoria = ep.getCategoria()-1;
			return SUELDO_BASE[categoria]+5000*ep.getAnyos();
			
		}

}
