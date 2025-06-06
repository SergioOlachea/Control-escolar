package model;

import java.awt.Image;
import java.util.Date;

public class Docente {
	private int id;
	private long numeroControl;
	private String nombres;
	private String apellidos;
	private Date fechaNacimiento;
	private String genero;
	private String gradoEstudios;
	private String domicilio;
	private String correo;
	private String telefono;
	private String curp;
	private Image foto;
	
	public Docente() {
		
	}
	
	public Docente(int id, long numeroControl, String nombres, String apellidos, Date fechaNacimiento,
		String genero, String gradoEstudios, String domicilio, String correo, String telefono, String curp, Image foto) {
		super();
		this.id = id;
		this.numeroControl = numeroControl;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.genero = genero;
		this.gradoEstudios = gradoEstudios;
		this.domicilio = domicilio;
		this.correo = correo;
		this.telefono = telefono;
		this.curp = curp;
		this.foto = foto;
	}
	
	public Docente(String nombres, String apellidos, Date fechaNacimiento, String genero, String gradoEstudios,
			String domicilio, String correo, String telefono, String curp, Image foto) {
		super();
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.genero = genero;
		this.gradoEstudios = gradoEstudios;
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
	public String getGradoEstudios() {
		return gradoEstudios;
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

	public void setGradoEstudios(String gradoEstudios) {
		this.gradoEstudios = gradoEstudios;
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

	@Override
	public String toString() {
		return "Docente [id=" + id + ", numeroControl=" + numeroControl + ", nombres=" + nombres + ", apellidos="
				+ apellidos + ", fechaNacimiento=" + fechaNacimiento + ", genero=" + genero + ", gradoEstudios=" + gradoEstudios
				+ ", Domicilio=" + domicilio + ", correo=" + correo  + ", telefono=" + telefono + ", curp=" + curp + ", foto=" + foto +
				"]";
	}
}
