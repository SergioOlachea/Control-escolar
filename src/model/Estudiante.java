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
	
	public Estudiante() {
		
	}
	
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
	
	//Constructor para insertar estudiantes
	public Estudiante(String nombres, String apellidos, Date fechaNacimiento, String genero, int grado,
			String domicilio, String correo, String telefono, String curp, Image foto) {
		super();
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

	public void setId(int id) {
		this.id = id;
	}

	public void setNumeroControl(long numeroControl) {
		this.numeroControl = numeroControl;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setGrado(int grado) {
		this.grado = grado;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public void setFoto(Image foto) {
		this.foto = foto;
	}

	public void setGrupos(String grupos) {
		this.grupos = grupos;
	}

	@Override
	public String toString() {
		return "Estudiante [id=" + id + ", numeroControl=" + numeroControl + ", nombres=" + nombres + ", apellidos="
				+ apellidos + ", fechaNacimiento=" + fechaNacimiento + ", genero=" + genero + ", grado=" + grado
				+ ", Domicilio=" + domicilio + ", correo=" + correo  + ", telefono=" + telefono + ", curp=" + curp + ", foto=" + foto + ", grupos="
				+ grupos + "]";
	}
}
