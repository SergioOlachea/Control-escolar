package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

public class ModuloGrupoModel {
	
	public ArrayList<Grupo> getGrupos() {
		String query = 
			    "SELECT " +
			    "  g.id, " +
			    "  g.name AS group_name, " +
			    "  c.name AS course_name, " +
			    "  t.control_number AS teacher_control_number, " +
			    "  t.first_name AS teacher_first_name, " +
			    "  t.last_name AS teacher_last_name, " +
			    "  t.email AS teacher_email, " +
			    "  s.control_number AS student_control_number, " +
			    "  s.first_name AS student_first_name, " +
			    "  s.last_name AS student_last_name, " +
			    "  s.email AS student_email " +
			    "FROM groups_entity g " +
			    "  LEFT JOIN courses c ON g.course_id = c.id " +
			    "  LEFT JOIN teachers t ON g.teacher_id = t.id " +
			    "  LEFT JOIN group_assignment ga ON ga.group_id = g.id " +
			    "  LEFT JOIN students s ON ga.student_id = s.id;";
		HashMap<Integer, Grupo> grupos = new HashMap<Integer, Grupo>();
		
		try (
				Connection con = ConexionBD.getConnection();
				PreparedStatement stmt = con.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
			){
			
			while(rs.next()) {
				int id = rs.getInt("id");
				Grupo grupo = grupos.get(id);
				if(grupo==null) {
					String nombreGrupo = rs.getString("group_name");
					
					grupo = new Grupo();
					grupo.setId(id);
					grupo.setNombre(nombreGrupo);
					
					
					String nombreAsignatura = rs.getString("course_name");
					if(!rs.wasNull()) {
						Asignatura asignatura = new Asignatura();
						asignatura.setNombre(nombreAsignatura);
						
						grupo.setAsignatura(asignatura);
					}
					
					long numeroControlDocente = rs.getLong("teacher_control_number");
					if(!rs.wasNull()) {
						String nombresDocente = rs.getString("teacher_first_name");
						String apellidosDocente = rs.getString("teacher_last_name");
						String correoDocente = rs.getString("teacher_email");

						Docente docente = new Docente();
						docente.setNumeroControl(numeroControlDocente);
						docente.setNombres(nombresDocente);
						docente.setApellidos(apellidosDocente);
						docente.setCorreo(correoDocente);
						
						grupo.setDocente(docente);
					}
					
					grupo.setEstudiantes(new ArrayList<Estudiante>());
					grupos.put(id, grupo);
				}
				
				long numeroControlEstudiante = rs.getLong("student_control_number");
				if(!rs.wasNull()) {
					String nombresEstudiante = rs.getString("student_first_name");
					String apellidosEstudiante = rs.getString("student_last_name");
					String correoEstudiante = rs.getString("student_email");
					
					Estudiante estudiante = new Estudiante();
					estudiante.setNumeroControl(numeroControlEstudiante);
					estudiante.setNombres(nombresEstudiante);
					estudiante.setApellidos(apellidosEstudiante);
					estudiante.setCorreo(correoEstudiante);
					
					grupo.getEstudiantes().add(estudiante);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ArrayList<Grupo>(grupos.values());
		
	}
	
	//Solo es necesario el id de asignatura, docente y estudiantes
	public boolean add(Grupo grupo) {
		String query = 
				"INSERT INTO groups_entity (name, course_id, teacher_id) " +
				"VALUES (?, ?, ?)";
		String assignmentQuery = 
				"INSERT INTO group_assignment (student_id, group_id) " +
				"VALUES (?, ?)";
		try (
				Connection con = ConexionBD.getConnection();
				PreparedStatement stmt = con.prepareStatement(query, 
						Statement.RETURN_GENERATED_KEYS);
				PreparedStatement assignmentStmt = con.prepareStatement(assignmentQuery);
			){

			stmt.setString(1, grupo.getNombre());

			if (grupo.getAsignatura() != null) {
				stmt.setInt(2, grupo.getAsignatura().getId());
			} else {
				stmt.setNull(2, Types.INTEGER);
			}

			if (grupo.getDocente() != null) {
				stmt.setInt(3, grupo.getDocente().getId());
			} else {
				stmt.setNull(3, Types.INTEGER);
			}
			
	        int rs = stmt.executeUpdate();

	        if (rs > 0) {
	            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                	int groupId = generatedKeys.getInt(1);
	                	if(grupo.getEstudiantes()!=null) {	                		
	                		for (Estudiante estudiante : grupo.getEstudiantes()) {
	                			assignmentStmt.setLong(1, estudiante.getId());
	                			assignmentStmt.setInt(2, groupId);
	                			assignmentStmt.addBatch();
	                		}
	                		assignmentStmt.executeBatch();
	                	}
	                }
	            }
	            return true;
	        }
	        return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update(int id, Grupo grupo) {
		String query = 
				"UPDATE groups_entity SET" +
				"		name = ?, " +
				"		course_id = ?, " +
				"		teacher_id = ?" +		
				" WHERE id = ?";
		String assignmentQuery = 
				"INSERT INTO group_assignment (student_id, group_id) " +
				"VALUES (?, ?) " +
				"	 ON DUPLICATE KEY UPDATE student_id=student_id";
		try (
				Connection con = ConexionBD.getConnection();
				PreparedStatement stmt = con.prepareStatement(query, 
						Statement.RETURN_GENERATED_KEYS);
				PreparedStatement assignmentStmt = con.prepareStatement(assignmentQuery);
			){

			stmt.setString(1, grupo.getNombre());

			if (grupo.getAsignatura() != null) {
				stmt.setInt(2, grupo.getAsignatura().getId());
			} else {
				stmt.setNull(2, Types.INTEGER);
			}

			if (grupo.getDocente() != null) {
				stmt.setInt(3, grupo.getDocente().getId());
			} else {
				stmt.setNull(3, Types.INTEGER);
			}
			
			stmt.setInt(4, id);
	        int rs = stmt.executeUpdate();
	        
	        if(grupo.getEstudiantes()!=null) {	                		
	        	for (Estudiante estudiante : grupo.getEstudiantes()) {
	        		assignmentStmt.setLong(1, estudiante.getId());
	        		assignmentStmt.setInt(2, id);
	        		assignmentStmt.addBatch();
	        	}
	        	assignmentStmt.executeBatch();
	        }
	        return rs > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(int id) {
		String query = "DELETE FROM groups_entity WHERE id = ?";
		
		try (
				Connection conn = ConexionBD.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
			){
			stmt.setInt(1, id);
			int rs = stmt.executeUpdate();
			return rs > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
