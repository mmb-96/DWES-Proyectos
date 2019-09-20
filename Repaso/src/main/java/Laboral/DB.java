package Laboral;


import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DB {
	private Connection con;
	private String sql;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DB() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nomina", "manu", "621996");
	}
	
	public List<Empleado> mostarDatosTodos() throws Exception {
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		sql = "Select * From Empleados";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while (rs.next()){
			empleados.add(new Empleado(rs.getString(1), rs.getString(2), rs.getString(3).charAt(0), rs.getInt(4), rs.getInt(5)));
		}
		
		return empleados;
	}
	
	public int salaraioDni(String dni) throws Exception {
		int sueldo = 0;
		sql = "Select Sueldo From Nominas Where dni = ? ";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, dni);
		rs = pstmt.executeQuery();
		
		while (rs.next()){
			sueldo = rs.getInt(1);
		}
		return sueldo;
	}
	
	public boolean actualizarSueldo(String dni) {
		boolean error = false;
		try {
			sql = "Select * From Empleados Where dni = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dni);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				Empleado a = new Empleado(rs.getString(1), rs.getString(2), rs.getString(3).charAt(0), rs.getInt(4), rs.getInt(5));
				sql = "UPDATE Nominas set sueldo = ? where dni = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(2, a.dni);
				pstmt.setInt(1, Nomina.sueldo(a));
				pstmt.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
			error = true;
		}

		return error;
	}
	
	public boolean actualizarSueldoTodos() {
		boolean error = false;
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		try {
			empleados = (ArrayList<Empleado>) mostarDatosTodos();
			for (Empleado emp : empleados) {
				sql = "UPDATE Nominas set sueldo = ? where dni = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(2, emp.dni);
				pstmt.setInt(1, Nomina.sueldo(emp));
				pstmt.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
			error = true;
		}
		return error;
	}
	
	public boolean backup(File empleados, FileOutputStream nominas) {
		boolean correcto = true;
		try {
			sql = "Select * From Nominas";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			DataOutputStream binario = new DataOutputStream(nominas);
			
			while (rs.next()){
				binario.writeUTF(rs.getString(1) + "," + rs.getInt(2)+"\n");
			}
			
			pstmt.clearBatch();
			sql = "Select * From Empleados";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			BufferedWriter bwe = new BufferedWriter(new FileWriter(empleados));
			
			while (rs.next()){
				bwe.write(rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3)+ "," + rs.getInt(4)+ "," + rs.getInt(5));
				bwe.newLine();
			}
			bwe.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			correcto = false;
		}catch (IOException e) {
			e.printStackTrace();
			correcto = false;
		}
		return correcto;
	}
	
	public boolean actualizarNombre(String Dni, String nombre) {
		boolean error = false;
		try {
			sql = "Update Empleados set nombre = ? where dni = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(2, Dni);
			pstmt.setString(1, nombre);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			error = true;
		}
		return error;
	}
	
	public boolean actualizarSexo(String Dni, char sexo) {
		boolean error = false;
		try {
			sql = "Update Empleados set sexo = ? where dni = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(2, Dni);
			pstmt.setString(1, Character.toString(sexo));
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			error = true;
		}
		return error;
	}
	
	public boolean actualizarCategoria(String Dni, int cat) {
		boolean error = false;
		try {
			sql = "Update Empleados set categoria = ? where dni = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(2, Dni);
			pstmt.setInt(1, cat);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			error = true;
		}
		return error;
	}
	
	public boolean actualizarAnyos(String Dni, int anyos) {
		boolean error = false;
		try {
			sql = "Update Empleados set anyos = ? where dni = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(2, Dni);
			pstmt.setInt(1, anyos);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			error = true;
		}
		return error;
	}
}
