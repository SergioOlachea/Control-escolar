package model;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Asignatura {
	private int id;
	private String nombre;
	private String descripcion;
	private ArrayList<Grupo> grupos;
	
	public Asignatura() {
		
	}
	
	public Asignatura(int id, String nombre, String descripcion, ArrayList<Grupo> grupos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.grupos = grupos;
	}

	public Asignatura(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public ArrayList<Grupo> getGrupos() {
		return grupos;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setGrupos(ArrayList<Grupo> grupos) {
		this.grupos = grupos;
	}

	@Override
	public String toString() {
		String gruposStr= grupos.isEmpty()
	    ? "Sin grupos asignados"
	    : grupos.stream()
	            .map(Grupo::getNombre)
	            .collect(Collectors.joining(", "));
		return "Asignatura [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", grupos=" + gruposStr
				+ "]";
	}	
	
}
