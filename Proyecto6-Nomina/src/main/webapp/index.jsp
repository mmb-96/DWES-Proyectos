<html>
<body>
<h2>Nomina</h2>
	<% out.println("1. Muestra la informacion de todos los empleados.<br>"
								 + "2. Muesta el salario de un empleado por su dni.<br>"
								 + "3. Modificar datos de empleados.<br>"
								 + "4. Recalcular y actualizar el sueldo de un empleado.<br>"
								 + "5. Recalcular y actualizar los sueldos de todos los empleados.<br>"
								 + "6. Realizar copia de seguridad.<br>"
								 + "7. Salir del programa.<br>");%>
				<% out.println("Seleccione un opcion:<br>");%>
				<%-- opc = number.nextInt();
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
			}while(opc != 7); --%>
</body>
</html>
