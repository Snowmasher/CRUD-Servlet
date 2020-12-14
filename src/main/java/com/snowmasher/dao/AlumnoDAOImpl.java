package com.snowmasher.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.snowmasher.model.Alumno;

public class AlumnoDAOImpl implements AlumnoDAO {
	
	private List<Alumno> listAlumnos;
	private String url = "jdbc:postgresql://localhost:5432/snowmasher";
	private String user = "snowmasher";
	private String pass = "snowmasher";
	private Connection conn;
	
	public AlumnoDAOImpl() throws SQLException {
		try {
			connect();
		} catch (ClassNotFoundException e) {
			System.out.println("Error al conectar a la base de datos.");
		}
	}
	
	private void connect() throws ClassNotFoundException{
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, pass);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Conexión a postgresql realizada");
	}
	
	
	
	
	

	@Override
	public List<Alumno> getAll() {
		List<Alumno> alumList = new ArrayList<Alumno>();
		try {
			//Dejamos preparado statement, aunque en este ejemplo podriamos hacerlo en menos
			PreparedStatement ps = conn.prepareStatement("select * from alumnos;");
			//Preparamos nuestro ResultSet para ir recogiendo datos
			ResultSet rs = ps.executeQuery();
			//Verificamos que existe un dato en el siguiente registro
			while(rs.next()) {
				Alumno alum = new Alumno();
				
				alum.setName(rs.getString("nombre"));
				alum.setLastName(rs.getString("apellido"));
				alum.setAge(rs.getInt("edad"));
				alum.setGroup(rs.getString("grupo"));

				alumList.add(alum);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return alumList;
	}
	
	@Override
	public void addAlumno(Alumno a) {
		
		try {
			PreparedStatement ps = conn.prepareStatement("insert into alumnos (nombre, apellido, edad, grupo) values (?,?,?,?) ;");
			ps.setString(1, a.getName());
			ps.setString(2, a.getLastName());
			ps.setInt(3, a.getAge());
			ps.setString(4, a.getGroup());
			
			int lineas = ps.executeUpdate();
			
			System.out.println("Lineas insertadas: " + lineas );
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAlumno(String nombre, String apellido, String grupo) throws SQLException {
		
		try {
			
			PreparedStatement ps = conn.prepareStatement("delete from alumnos where nombre = ? and apellido = ? and grupo = ?;");
			
			ps.setString(1, nombre);
			ps.setString(2, apellido);
			ps.setString(3, grupo);
			
			int lineas = ps.executeUpdate();
			
			System.out.println("Lineas eliminadas: " + lineas );
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void end() throws SQLException {
		if(conn == null) {return;}
		conn.close();
		
	}

	
}
