package Laboral;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class CalculaNominas {
	
	private static Scanner number;
	private static Scanner letras;
	private static DB db;

	public static void main(String[] args) throws FileNotFoundException {
//		Empleado ep1 = null;
//		try {
//			ep1 = new Empleado("Jamer Cosling", "32000032G", 'M', 4, 7);
//		} catch (DatosNoCorrectoException e) {
//			e.printStackTrace();
//		}
//		Empleado ep2 = new Empleado("Ada Lovelace", "32000031R", 'F');
//
//		System.out.println(escribe(ep1, ep2));
//
//		ep2.incAnyo();
//		try {
//			ep1.setCategoria(9);
//		} catch (DatosNoCorrectoException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println(escribe(ep1, ep2));
//
//		try {
//			ep1.setCategoria(20);
//		} catch (DatosNoCorrectoException e) {
//			e.printStackTrace();
//		}

			File archivo = new File("empleados.txt");
			FileOutputStream sueldo = new FileOutputStream("salarios.dat");
			Empleado emp1 = null;
			Empleado emp2 = null;
			Connection con = null;
			String sql = null;

			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nomina", "manu", "621996");
			} catch (ClassNotFoundException e2) {
				e2.printStackTrace();
			}catch (SQLException e2) {
				e2.printStackTrace();
			}
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(archivo));
				String datosFichero = br.readLine();
				String empleado[] = datosFichero.split(",");
				char edad = empleado[2].charAt(0);
				int categoria = Integer.parseInt(empleado[3]);
				int anyos = Integer.parseInt(empleado[4]);	
				
				try {
					sql = "INSERT INTO Empleados values(?, ?, ?, ?, ?)";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setString(1, empleado[0]);
					pstmt.setString(2, empleado[1]);
					pstmt.setString(3, empleado[2]);
					pstmt.setInt(4, categoria);
					pstmt.setInt(5, anyos);
					pstmt.execute();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					emp1 = new Empleado(empleado[0], empleado[1], edad, categoria, anyos);
				} catch (DatosNoCorrectoException e) {
					e.printStackTrace();
				}
				
				String datosFichero2 = br.readLine();
				String empleado2[] = datosFichero2.split(",");
				char edad2 = empleado2[2].charAt(0);
				br.close();

				emp2 = new Empleado(empleado2[0], empleado2[1], edad2);
				
				try {
					sql = "INSERT INTO Empleados values(?, ?, ?, ?, ?)";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setString(1, empleado2[0]);
					pstmt.setString(2, empleado2[1]);
					pstmt.setString(3, empleado2[2]);
					pstmt.setInt(4, emp2.getCategoria());
					pstmt.setInt(5, emp2.anyos);
					pstmt.execute();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			System.out.println(escribe(emp1, emp2));
			
			emp2.incAnyo();
			try {
				emp1.setCategoria(9);
			} catch (DatosNoCorrectoException e) {
				e.printStackTrace();
			}
			
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
				bw.write(escribeSinNomina(emp1, emp2));
				bw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				sql = "Update Empleados set categoria = ? where dni = ?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(2, emp1.dni);
				pstmt.setInt(1, emp1.getCategoria());
				pstmt.execute();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
			try {
				sql = "Update Empleados set anyos = ? where dni = ?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(2, emp2.dni);
				pstmt.setInt(1, emp2.anyos);
				pstmt.execute();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			System.out.println(escribe(emp1, emp2));
			try {
				@SuppressWarnings("resource")
				DataOutputStream binario = new DataOutputStream(sueldo);
				binario.writeUTF(escribeDNISueldo(emp1, emp2));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				sql = "INSERT INTO Nominas values(?, ?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, emp1.dni);
				pstmt.setInt(2, Nomina.sueldo(emp1));
				pstmt.execute();
				pstmt.clearParameters();
				pstmt.setString(1, emp2.dni);
				pstmt.setInt(2, Nomina.sueldo(emp2));
				pstmt.execute();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			altaEmpleado("Manuel", "30235421N", 'M');
			
			File nuevoEmpleado = new File("empleadoNuevo.txt");
			altaEmpleado(nuevoEmpleado);		
			
			try {
				db = new DB();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			number = new Scanner(System.in);
			letras = new Scanner(System.in);
			int opc;
			do {
				System.out.println("1. Muestra la informacion de todos los empleados.\n"
								 + "2. Muesta el salario de un empleado por su dni.\n"
								 + "3. Modificar datos de empleados.\n"
								 + "4. Recalcular y actualizar el sueldo de un empleado.\n"
								 + "5. Recalcular y actualizar los sueldos de todos los empleados.\n"
								 + "6. Realizar copia de seguridad.\n"
								 + "7. Salir del programa.");
				System.out.println("Seleccione un opcion:");
				opc = number.nextInt();
				System.out.println("");
				switch (opc) {
				case 1:
					try {
						List<Empleado> empleado = new ArrayList<Empleado>();
						empleado = db.mostarDatosTodos();
						for (Empleado e : empleado) {
							System.out.println(e.imprime());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case 2:
					try {
						System.out.println(db.salaraioDni("30235421N"));
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					break;
				case 3:
					System.out.println("AVISO: Si se realiza un modificacion de categoria o de anyos, se deberia utilizar la opcion 4 o 5, para actualizar el salario.\n");
					try {
						List<Empleado> empleado = new ArrayList<Empleado>();
						empleado = db.mostarDatosTodos();
						for (Empleado e : empleado) {
							System.out.println(e.imprime());
						}
						System.out.println("");
						System.out.println("Introduzca el dni del empleado al realizar modificaciones.");
						String dni = letras.next();
						do {
							System.out.println("1. Actualizar nombre.\n"
											 + "2. Actualizar sexo.\n"
											 + "3. Actualizar categoria.\n"
											 + "4. Actualizar años.\n"
											 + "5. Salir.");
							System.out.println("Seleccione un opcion:");
							opc = number.nextInt();
							switch (opc) {
							case 1:
								System.out.println("Introduca el nuevo nommbre:");
								String nombre = letras.nextLine();
								if(db.actualizarNombre(dni, nombre)) {
									System.out.println("Se ha producido un error, actualizacion no realizada.\n");
								}
								break;
							case 2:
								System.out.println("Introduca el nuevo sexo:");
								char sexo = letras.next().charAt(0);
								if(db.actualizarSexo(dni, sexo)) {
									System.out.println("Se ha producido un error, actualizacion no realizada.\n");
								}
								break;
							case 3:
								System.out.println("Introduca la nueva categoria:");
								int cat = number.nextInt();
								if(db.actualizarCategoria(dni, cat)) {
									System.out.println("Se ha producido un error, actualizacion no realizada.\n");
								}
								break;
							case 4:
								System.out.println("Introduca la nueva antiguedad:");
								int anyos = number.nextInt();
								if(db.actualizarAnyos(dni, anyos)) {
									System.out.println("Se ha producido un error, actualizacion no realizada.\n");
								}
								break;
							case 5:
								System.out.println("Saliendo del submenu, estas ahora mismo en el menu principal.\n");
								break;
							default:
								System.out.println("Numero no valido.\n");
								break;
							}
						}while(opc!=5);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					break;
				case 4:
					if(db.actualizarSueldo("30235421N")) {
						System.out.println("Se ha producido un error, actualizacion no realizada.\n");
					}
					break;
				case 5:
					if(db.actualizarSueldoTodos()) {
						System.out.println("Se ha producido un error, actualizacion no realizada.\n");
					}
					break;
				case 6:
					System.out.println("Realizando copia de seguridad...");
					if (!db.backup(archivo, sueldo)) {
						System.out.println("Se ha producido un error, copia de seguridad no realizada.\n");
					}				
					break;
				case 7:
					System.out.println("Saliendo de la aplicacion... .... ...");
					break;
				default:
					System.out.println("Numero no valido.\n");
					break;
				}			
			}while(opc != 7);
			

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
		
		private static String escribeSinNomina(Empleado e1, Empleado e2) {
			String empleados = null;
			empleados = e1.imprime()+"\n";
			empleados += e2.imprime()+"\n";
			return empleados;
			
		}
		
		private static String escribeDNISueldo(Empleado e1, Empleado e2) {
			String nominaEmple;
			String empleados = null;
			empleados = e1.dni;
			nominaEmple = String.valueOf(Nomina.sueldo(e1));
			empleados += "," + nominaEmple+"\n";
			empleados += e2.dni;
			nominaEmple = String.valueOf(Nomina.sueldo(e2));
			empleados += "," + nominaEmple+"\n";
			return empleados;
		}
		
		private static void altaEmpleado(String nombre, String dni, char sexo) {
			Empleado emp3 = new Empleado(nombre, dni, sexo);
			try {
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nomina", "manu", "621996");
				String sql = "INSERT INTO Empleados values(?, ?, ?, ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, emp3.nombre);
				pstmt.setString(2, emp3.dni);
				pstmt.setString(3, String.valueOf(emp3.sexo));
				pstmt.setInt(4, emp3.getCategoria());
				pstmt.setInt(5, emp3.anyos);
				pstmt.execute();
				pstmt.clearBatch();
				sql = "INSERT INTO Nominas values(?, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, emp3.dni);
				pstmt.setInt(2, Nomina.sueldo(emp3));
				pstmt.execute();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
		
		private static void altaEmpleado(File fichero) {
			BufferedReader br;
			String datosFichero;
			String empleado[] = null;
			char edad = 0;
			try {
				br = new BufferedReader(new FileReader(fichero));
				datosFichero = br.readLine();
				empleado = datosFichero.split(",");
				edad = empleado[2].charAt(0);
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Empleado emp4 = new Empleado(empleado[0], empleado[1], edad);
			try {
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nomina", "manu", "621996");
				String sql = "INSERT INTO Empleados values(?, ?, ?, ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, emp4.nombre);
				pstmt.setString(2, emp4.dni);
				pstmt.setString(3, String.valueOf(emp4.sexo));
				pstmt.setInt(4, emp4.getCategoria());
				pstmt.setInt(5, emp4.anyos);
				pstmt.execute();
				pstmt.clearBatch();
				sql = "INSERT INTO Nominas values(?, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, emp4.dni);
				pstmt.setInt(2, Nomina.sueldo(emp4));
				pstmt.execute();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}


}
