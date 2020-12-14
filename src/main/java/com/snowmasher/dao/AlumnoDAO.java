package com.snowmasher.dao;

import java.sql.SQLException;
import java.util.List;

import com.snowmasher.model.Alumno;

public interface AlumnoDAO {
	public List<Alumno> getAll();
	
	public void addAlumno(Alumno a);
	
	public void end() throws SQLException;

	public void deleteAlumno(String nombre, String apellido, String grupo) throws SQLException;
}
