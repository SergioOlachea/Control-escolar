package model;

import java.awt.Image;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import javax.imageio.ImageIO;

import model.exception.UniqueKeyViolationException;


public class ModuloEstudianteModel {
	
	public ArrayList<Estudiante> getEstudiantes() {
		ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
		String query = "SELECT s.*, GROUP_CONCAT(g.name ORDER BY g.name SEPARATOR ', ') AS grupos "
				+ 		"FROM students s "
				+ 		"LEFT JOIN group_assignment ga ON ga.student_id = s.id "
				+ 		"LEFT JOIN groups_entity g ON ga.group_id = g.id GROUP BY s.id;";
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
				int grado = rs.getInt("semester");
				String domicilio = rs.getString("address");
				String correo = rs.getString("email");
				String telefono = rs.getString("phone_number");
				String curp = rs.getString("curp");
				String grupos = Optional.ofNullable(rs.getString("grupos")).orElse("N/A");
				InputStream blob = rs.getBinaryStream("picture");
				Image foto = null;
				if(blob!=null) {					
					foto = ImageIO.read(blob);
				}
				
				Estudiante estudiante = new Estudiante(id, numeroControl, nombres, apellidos,
						fechaNacimiento, genero, grado, 
						domicilio, correo, telefono,curp, foto, grupos);
				System.out.println(estudiante);
				estudiantes.add(estudiante);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estudiantes;
	}
	
	public boolean add(Estudiante estudiante) throws UniqueKeyViolationException {
		String query = "INSERT INTO students (first_name, last_name, gender, birthday, "
				+ 				"phone_number, email, semester, "
				+ "				curp, address, picture)"
				+ "		VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		try (
				Connection conn = ConexionBD.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
			){
			stmt.setString(1, estudiante.getNombres());
			stmt.setString(2, estudiante.getApellidos());
			stmt.setString(3, estudiante.getGenero());
			java.sql.Date fechaConvertida =  new java.sql.Date(estudiante.getFechaNacimiento().getTime());
			stmt.setDate(4, fechaConvertida);
			stmt.setString(5, estudiante.getTelefono());
			stmt.setString(6, estudiante.getCorreo());
			stmt.setInt(7, estudiante.getGrado());
			stmt.setString(8, estudiante.getCurp());
			stmt.setString(9, estudiante.getDomicilio());
			stmt.setBytes(10, Utils.toByte(Utils.toBufferedImage(estudiante.getFoto())));
			
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
	
	public boolean update(int id, Estudiante estudiante) throws UniqueKeyViolationException {
		String query = "UPDATE students "
				+ "		   SET first_name=?, "
				+ "			   last_name=?, "
				+ "	           gender=?, "
				+ "			   birthday=?, "
				+ "			   phone_number=?, "
				+ "			   email=?, "
				+ "			   semester=?, "
				+ "			   curp=?, "
				+ "            address=?, "
				+ "            picture=? "
				+ "		WHERE  id=?";
		
		try (
				Connection conn = ConexionBD.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
			){
			stmt.setString(1, estudiante.getNombres());
			stmt.setString(2, estudiante.getApellidos());
			stmt.setString(3, estudiante.getGenero());
			java.sql.Date fechaConvertida =  new java.sql.Date(estudiante.getFechaNacimiento().getTime());
			stmt.setDate(4, fechaConvertida);
			stmt.setString(5, estudiante.getTelefono());
			stmt.setString(6, estudiante.getCorreo());
			stmt.setInt(7, estudiante.getGrado());
			stmt.setString(8, estudiante.getCurp());
			stmt.setString(9, estudiante.getDomicilio());
			stmt.setBytes(10, Utils.toByte(Utils.toBufferedImage(estudiante.getFoto())));
			stmt.setInt(11, id);
			
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
