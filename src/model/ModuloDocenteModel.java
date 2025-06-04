package model;

import java.awt.Image;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

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
}
