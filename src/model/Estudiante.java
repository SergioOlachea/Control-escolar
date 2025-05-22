package model;

import java.awt.Image;
import java.util.Date;
import java.util.List;

public class Estudiante {
	private int id;
	private long numeroControl;
	private String nombres;
	private String apellidos;
	private Date fechaNacimiento;
	private String genero;
	private int grado;
	private String domicilio;
	private String correo;
	private String telefono;
	private String curp;
	private Image foto;
	private String grupos;
	
	public Estudiante(int id, long numeroControl, String nombres, String apellidos, Date fechaNacimiento,
		String genero, int grado, String domicilio, String correo, String telefono, String curp, Image foto, String grupos) {
		super();
		this.id = id;
		this.numeroControl = numeroControl;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.genero = genero;
		this.grado = grado;
		this.domicilio = domicilio;
		this.correo = correo;
		this.telefono = telefono;
		this.curp = curp;
		this.foto = foto;
		this.grupos = grupos;
	}
	
	public int getId() {
		return id;
	}
	public long getNumeroControl() {
		return numeroControl;
	}
	public String getNombres() {
		return nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public String getGenero() {
		return genero;
	}
	public int getGrado() {
		return grado;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public String getCorreo() {
		return correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public String getCurp() {
		return curp;
	}
	public Image getFoto() {
		return foto;
	}
	
	public String getGrupo() {
		return grupos;
	}

	@Override
	public String toString() {
		return "Estudiante [id=" + id + ", numeroControl=" + numeroControl + ", nombres=" + nombres + ", apellidos="
				+ apellidos + ", fechaNacimiento=" + fechaNacimiento + ", genero=" + genero + ", grado=" + grado
				+ ", Domicilio=" + domicilio + ", correo=" + correo  + ", telefono=" + telefono + ", curp=" + curp + ", foto=" + foto + ", grupos="
				+ grupos + "]";
	}
}
