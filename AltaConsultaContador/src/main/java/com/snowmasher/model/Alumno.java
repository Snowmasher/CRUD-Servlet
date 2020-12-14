package com.snowmasher.model;

public class Alumno {
	
	private String name;
	private String lastName;
	private int age;
	private String group;
	
	
	public Alumno() {
		super();
	}


	public Alumno(String name, String lastName, int age, String group) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.group = group;
	}
	public Alumno(String[] params) {
		super();
		this.name = params[0];
		this.lastName = params[1];
		this.age = Integer.parseInt(params[2]);
		this.group = params[3];
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getGroup() {
		return group;
	}


	public void setGroup(String group) {
		this.group = group;
	}


	@Override
	public String toString() {
		return name + "/" + lastName + "/" + age + "/" + group;
	}
	
}
