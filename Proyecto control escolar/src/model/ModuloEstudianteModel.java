package model;

import java.awt.Image;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import javax.imageio.ImageIO;

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
}
