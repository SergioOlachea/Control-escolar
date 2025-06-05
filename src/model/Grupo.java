package model;

import java.util.ArrayList;

public class Grupo {
	private int id;
	private String nombre;
	private Asignatura asignatura;
	private Docente docente;
	private ArrayList<Estudiante> estudiantes;
	
	public Grupo() {
		
	}
	
	public Grupo(int id, String nombre,Asignatura asignatura, Docente docente, ArrayList<Estudiante> estudiantes) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.asignatura = asignatura;
		this.docente = docente;
		this.estudiantes = estudiantes;
	}
	
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
	
	public Asignatura getAsignatura() {
		return asignatura;
	}

	public Docente getDocente() {
		return docente;
	}

	public ArrayList<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}
	
	
}
