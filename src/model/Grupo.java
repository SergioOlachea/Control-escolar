package model;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class Grupo {
	private int id;
	private String nombre;
	private Asignatura asignatura;
	private Docente docente;
	private ArrayList<Estudiante> estudiantes;
	
	public Grupo() {
		
	}
	
	public Grupo(int di,String nombre,Asignatura asignatura, Docente docente, ArrayList<Estudiante> estudiantes) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.asignatura = asignatura;
		this.docente = docente;
		this.estudiantes = estudiantes;
	}
	public Grupo(String nombre,Asignatura asignatura, Docente docente, ArrayList<Estudiante> estudiantes) {
		super();
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

	@Override
	public String toString() {
		String asignaturaNombre = Optional.ofNullable(asignatura)
				.map(Asignatura::getNombre)
				.orElse("No asignado");
		String docenteNombre = Optional.ofNullable(docente)
				.map(d -> d.getNombres() + " " + d.getApellidos())
				.orElse("No asignado");
	    String listaNombreEstudiantes = Optional.ofNullable(estudiantes)
	            .filter(es -> !es.isEmpty())
	            .map(es -> es.stream()
	                    .map(e -> e.getNombres() + " " + e.getApellidos())
	                    .collect(Collectors.joining(", ")))
	            .orElse("Sin estudiantes asignados");
		return "Grupo [id=" + id + ", nombre=" + nombre + ", asignatura=" + asignaturaNombre + ", docente=" + docenteNombre
				+ ", estudiantes=" + listaNombreEstudiantes + "]";
	}
	
	
}
