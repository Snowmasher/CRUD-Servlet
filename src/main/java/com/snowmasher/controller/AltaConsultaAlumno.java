package com.snowmasher.controller;

import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.snowmasher.model.Alumno;
import com.snowmasher.redis.ContadorRedis;
import org.postgresql.*;
import com.snowmasher.dao.*;


@WebServlet("/AltaConsultaAlumno")
public class AltaConsultaAlumno extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String qString;
	AlumnoDAOImpl alumnoDao;
	
	//Instancia del DAO
	{
		try {
			alumnoDao = new AlumnoDAOImpl();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	ContadorRedis contador = new ContadorRedis();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Contador redis
		
		contador.incContador(); //incrementa el contador
		
		qString = request.getQueryString();
		RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
		
		System.out.println("//////////////////////");
		System.out.println("QUERY_STRING: " + qString);
		
		//Valor del action del primer formulario
		String value = request.getParameter("action");
		
		//Datos del alta
		String name = request.getParameter("name");
		String lastName = request.getParameter("lastName");
		String age = request.getParameter("age");
		String group = request.getParameter("group");
		
		//Grupo al que se consulta
		String cGroup = request.getParameter("cGroup");
		
		//Datos de baja de alumno
		String dname = request.getParameter("dname");
		String dlastName = request.getParameter("dlastName");
		String dgroup = request.getParameter("dgroup");
		
		List<Alumno> listAlum = new ArrayList<Alumno>();
		List<Alumno> listAlumSalida = new ArrayList<Alumno>();
		
		
		if(qString != null && !qString.equals("")) {
			//Comprueba si lleva al formulario de alta o de consulta
			if(value != null) {
				if(value.equals("alta")) {
			
					System.out.println("Monstrando formulario de alta");
			
					view = request.getRequestDispatcher("/altaAlumno.jsp");
					
					request.setAttribute("contador", contador.getContador());
					
					view.forward(request, response);	
				}
				else if(value.equals("consulta")) {
				
					System.out.println("Monstrando formulario de consulta");
					
					view = request.getRequestDispatcher("/consultaAlumno.jsp");
					
					request.setAttribute("contador", contador.getContador());
					
					view.forward(request, response);
					
				}
				else if(value.equals("eliminar")) {
					
					System.out.println("Monstrando formulario de baja");
					
					view = request.getRequestDispatcher("/bajaAlumno.jsp");
					
					request.setAttribute("contador", contador.getContador());
					
					view.forward(request, response);
					
				}
			
				else {
				view.forward(request, response);
				}
			}
			//comprueba si se ha realizado el alta
			if(name != null && lastName != null && age != null && group != null) {
				if(!name.equals("") && !lastName.equals("") && !age.equals("") && !group.equals("")) {
				
					//Creando alumno y añadiendo a un fichero de texto
					Alumno alum = new Alumno(name,lastName,Integer.parseInt(age),group);
					System.out.println(alum.toString());
				
					alumnoDao.addAlumno(alum);
					
					
					view = request.getRequestDispatcher("/index.jsp");
					
					request.setAttribute("contador", contador.getContador());
					
					view.forward(request, response);
				}
				
				
			
			}
			//comprueba si se consulta un grupo
			if(cGroup != null) {
				if(cGroup.equals("1DAW") || cGroup.equals("2DAW") || cGroup.equals("1ASIR") || cGroup.equals("2ASIR") ) {
					
					listAlum = alumnoDao.getAll();
					
					for(Alumno a : listAlum) {
						if(a.getGroup().equals(cGroup)) {
							listAlumSalida.add(a);
						}
					}
					
					
					request.setAttribute("alumnos", listAlumSalida/*.get(0).toString()*/);
					request.setAttribute("contador", contador.getContador());
					
					view = request.getRequestDispatcher("/final.jsp");
					
					request.setAttribute("contador", contador.getContador());
					
					view.forward(request, response);
					
				}
			}
			//Comprueba si hay una baja
			if(dname != null && dlastName != null && dgroup != null) {
				try {
					alumnoDao.deleteAlumno(dname, dlastName, dgroup);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				view = request.getRequestDispatcher("/index.jsp");
				
				request.setAttribute("contador", contador.getContador());
				
				view.forward(request, response);
			}
			
		
		}//Fin de la comprobacion de que qString no sea null
		
		//Si no realiza ninguna accion
		else{
			request.setAttribute("contador", contador.getContador());
			view.forward(request, response);
		}
		
		System.out.println("---------------------------");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
}
