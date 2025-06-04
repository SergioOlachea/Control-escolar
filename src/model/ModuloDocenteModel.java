package model;

import java.awt.Image;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import model.exception.UniqueKeyViolationException;

public class ModuloDocenteModel {
	
	public ArrayList<Docente> getDocentes() {
		ArrayList<Docente> docentes = new ArrayList<Docente>();
		String query = "SELECT * FROM teachers";
		try (
				Connection con = ConexionBD.getConnection();
				PreparedStatement stmt = con.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()
			) {
			while(rs.next()) {
				int id = rs.getInt("id");
				long numeroControl = rs.getLong("control_number");
				String nombres = rs.getString("first_name");
				String apellidos = rs.getString("last_name");;
				Date fechaNacimiento = rs.getDate("birthday");
				String genero = rs.getString("gender");
				String gradoEstudios = rs.getString("degree");
				String domicilio = rs.getString("address");
				String correo = rs.getString("email");
				String telefono = rs.getString("phone_number");
				String curp = rs.getString("curp");
				InputStream blob = rs.getBinaryStream("picture");
				Image foto = null;
				if(blob!=null) {					
					foto = ImageIO.read(blob);
				}
				
				Docente docente = new Docente(id, numeroControl, nombres, apellidos, fechaNacimiento,
						genero, gradoEstudios,domicilio, 
						correo, telefono, curp, foto);
				docentes.add(docente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return docentes;
	}
	
	public boolean add(Docente docente) throws UniqueKeyViolationException {
		String query = "INSERT INTO teachers (first_name, last_name, gender, birthday, "
				+ 				"phone_number, email, degree, "
				+ "				curp, address, picture)"
				+ "		VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		try (
				Connection conn = ConexionBD.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
			){
			stmt.setString(1, docente.getNombres());
			stmt.setString(2, docente.getApellidos());
			stmt.setString(3, docente.getGenero());
			java.sql.Date fechaConvertida =  new java.sql.Date(docente.getFechaNacimiento().getTime());
			stmt.setDate(4, fechaConvertida);
			stmt.setString(5, docente.getTelefono());
			stmt.setString(6, docente.getCorreo());
			stmt.setString(7, docente.getGradoEstudios());
			stmt.setString(8, docente.getCurp());
			stmt.setString(9, docente.getDomicilio());
			stmt.setBytes(10, Utils.toByte(Utils.toBufferedImage(docente.getFoto())));
			
			int rs = stmt.executeUpdate();
			if(rs>0) {
				return true;
			}
		} catch (SQLException e) {
			if(e.getErrorCode() == 1062) {
				//Se introdujo un email o CURP que ya existe
				throw UniqueKeyViolationException.fromSQLException(e);
			}
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
