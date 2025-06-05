package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public class ModuloAsignaturaModel {

	public ArrayList<Asignatura> getAsignaturas() {
		HashMap<Integer, Asignatura> asignaturas = new HashMap<Integer, Asignatura>();
		String query = "SELECT c.*, g.id AS group_id, g.name AS grupo "
				+ "FROM courses c "
				+ "LEFT JOIN groups_entity g "
				+ "ON c.id = g.course_id";
		try ( 	
				Connection con = ConexionBD.getConnection();
				PreparedStatement stmt = con.prepareStatement(query);
				ResultSet rs = stmt.executeQuery(query)
			){
			while(rs.next()) {
				int id = rs.getInt("id");
				Asignatura asignatura = asignaturas.get(id);
				
				if(asignatura == null) {
					String nombre = rs.getString("name");
					String descripcion = rs.getString("description");
					asignatura = new Asignatura(id, nombre, 
							descripcion, new ArrayList<Grupo>());
					asignaturas.put(id, asignatura);
				}
				
				int group_id = rs.getInt("group_id");
				if(!rs.wasNull()) {
					String nombre = rs.getString("grupo");
					Grupo grupo = new Grupo();
					grupo.setId(group_id);
					grupo.setNombre(nombre);
					asignatura.getGrupos().add(grupo);
				}
			}
		} catch (Exception e) {
		}
		
		return new ArrayList<Asignatura>(asignaturas.values());
	}
	
	public boolean add(Asignatura asignatura) {
		String query = "INSERT INTO courses (name, description)"
				+ "		VALUES (?, ?)";
		
		try (
				Connection conn = ConexionBD.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
			){
			stmt.setString(1, asignatura.getNombre());
			stmt.setString(2, asignatura.getDescripcion());
			
			int rs = stmt.executeUpdate();
			return rs > 0;
		} catch (Exception e) {
			return false;
		}
	}
}
