package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

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
			    "  s.id AS student_id, " +
			    "  s.control_number AS student_control_number, " +
			    "  s.first_name AS student_first_name, " +
			    "  s.last_name AS student_last_name, " +
			    "  s.email AS student_email " +
			    "FROM groups_entity g " +
			    "   LEFT JOIN courses c ON g.course_id = c.id " +
			    "   LEFT JOIN teachers t ON g.teacher_id = t.id " +
			    "   LEFT JOIN group_assignment ga ON ga.group_id = g.id " +
			    "   LEFT JOIN students s ON ga.student_id = s.id ";
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
					int idEstudiante = rs.getInt("student_id");
					String nombresEstudiante = rs.getString("student_first_name");
					String apellidosEstudiante = rs.getString("student_last_name");
					String correoEstudiante = rs.getString("student_email");
					
					Estudiante estudiante = new Estudiante();
					estudiante.setId(idEstudiante);
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
	
	public Grupo getGrupoById(int id) {
		String query = 
			    "SELECT " +
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
			    "  LEFT JOIN students s ON ga.student_id = s.id " +
			    "WHERE g.id = ?";
		
		Grupo grupo = null;
		try (
				Connection con = ConexionBD.getConnection();
				PreparedStatement stmt = con.prepareStatement(query);
			){
			stmt.setInt(1, id);
			
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
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
						
						long noControlDocente = rs.getLong("teacher_control_number");
						if(!rs.wasNull()) {						
							String nombreDocente = rs.getString("teacher_first_name");
							String apellidoDocente = rs.getString("teacher_last_name");
							String emailDocente = rs.getString("teacher_email");
							
							Docente docente = new Docente();
							docente.setNumeroControl(noControlDocente);
							docente.setNombres(nombreDocente);
							docente.setApellidos(apellidoDocente);
							docente.setCorreo(emailDocente);
							grupo.setDocente(docente);
						}
						
						grupo.setEstudiantes(new ArrayList<Estudiante>());
					}
					
					long noControlEstudiante = rs.getLong("student_control_number");
					if(!rs.wasNull()) {
						String nombreEstudiante = rs.getString("student_first_name");
						String apellidoEstudiante = rs.getString("student_last_name");
						String emailEstudiante = rs.getString("student_email");
						
						Estudiante estudiante = new Estudiante();
						estudiante.setNumeroControl(noControlEstudiante);
						estudiante.setNombres(nombreEstudiante);
						estudiante.setApellidos(apellidoEstudiante);
						estudiante.setCorreo(emailEstudiante);
						grupo.getEstudiantes().add(estudiante);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return grupo;
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
				"VALUES (?, ?) ";
		String deleteAssignmentQuery = 
				"DELETE FROM group_assignment " +
				"WHERE  group_id=?";
		try (
				Connection con = ConexionBD.getConnection();
				PreparedStatement stmt = con.prepareStatement(query);
				PreparedStatement assignmentStmt = con.prepareStatement(assignmentQuery);
				PreparedStatement deleteStmt = con.prepareStatement(deleteAssignmentQuery);
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
	        
	        deleteStmt.setInt(1, id);
	        int r = deleteStmt.executeUpdate();

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
			System.out.println(e.getMessage());
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
	
	public void descargarPdf(String ruta, Grupo grupo) {
		try(Document documento = new Document(new PdfDocument(new PdfWriter(ruta)))) {
            PdfFont almarai = PdfFontFactory.createFont("Fonts/Almarai-Regular.ttf");
            PdfFont almaraiBold = PdfFontFactory.createFont("Fonts/Almarai-Bold.ttf");
            
            Asignatura asignaturaDefault = new Asignatura();
            asignaturaDefault.setNombre("No asignado");
            
            Docente docenteDefault = new Docente();
            docenteDefault.setNombres("No");
            docenteDefault.setApellidos("Asignado");
            docenteDefault.setCorreo("No hay docente asignado");
            
            Asignatura asignatura = Optional.ofNullable(grupo.getAsignatura()).orElse(asignaturaDefault);
            Docente docente = Optional.ofNullable(grupo.getDocente()).orElse(docenteDefault);
            ArrayList<Estudiante> estudiantes = grupo.getEstudiantes();
            
			Paragraph titulo = new Paragraph("Información de Grupo")
					.setFont(almaraiBold)
					.setFontSize(18f)
					.setTextAlignment(TextAlignment.CENTER)
					.setHorizontalAlignment(HorizontalAlignment.CENTER);
			
			SolidLine linea = new SolidLine(5);
			
			Table informacion = new Table(UnitValue.createPercentArray(new float[]{1f,1f}))
					.useAllAvailableWidth()
					.setMarginTop(20f)
					.setMarginBottom(20f);
			
		    Paragraph nombreGrupo = new Paragraph().setFontSize(14f)
		    	    .add(new Text("Nombre: ").setFont(almaraiBold))
		    	    .add(new Text(grupo.getNombre()).setFont(almarai));
		    Paragraph nombreAsignatura = new Paragraph().setFontSize(14f)
		    	    .add(new Text("Asignatura: ").setFont(almaraiBold))
		    	    .add(new Text(asignatura.getNombre()).setFont(almarai));
		    
			informacion.addCell(new Cell().add(nombreGrupo)
					.setBorder(Border.NO_BORDER)
					.setVerticalAlignment(VerticalAlignment.TOP));
			informacion.addCell(new Cell().add(nombreAsignatura)
					.setBorder(Border.NO_BORDER)
					.setVerticalAlignment(VerticalAlignment.TOP));
			
			
			String nombreCompletoDocente = docente.getNombres() + " " + docente.getApellidos();
		    Paragraph nombreDocente = new Paragraph().setFontSize(14f)
		    	    .add(new Text("Docente: ").setFont(almaraiBold))
		    	    .add(new Text(nombreCompletoDocente).setFont(almarai));
		    Paragraph correoDocente = new Paragraph().setFontSize(14f)
		    	    .add(new Text("Correo: ").setFont(almaraiBold))
		    	    .add(new Text(docente.getCorreo()).setFont(almarai));
			
			informacion.addCell(new Cell().add(nombreDocente)
					.setBorder(Border.NO_BORDER)
					.setVerticalAlignment(VerticalAlignment.TOP));
			informacion.addCell(new Cell().add(correoDocente)
					.setBorder(Border.NO_BORDER)
					.setVerticalAlignment(VerticalAlignment.TOP));
		    
			Paragraph tituloTabla = new Paragraph("Estudiantes")
					.setFont(almaraiBold)
					.setFontSize(18f)
					.setTextAlignment(TextAlignment.CENTER)
					.setHorizontalAlignment(HorizontalAlignment.CENTER);

			IBlockElement listaEstudiantes = null;
			if(estudiantes != null && !estudiantes.isEmpty()) {				
				Table tablaEstudiantes = new Table(UnitValue.createPercentArray(new float[]{1f,3f,2f}))
						.useAllAvailableWidth();
				
				tablaEstudiantes.addCell(new Cell()
						.add(new Paragraph("No. Control").setFontSize(14f).setFont(almaraiBold))
						.setVerticalAlignment(VerticalAlignment.MIDDLE));
				tablaEstudiantes.addCell(new Cell()
						.add(new Paragraph("Nombre").setFontSize(14f).setFont(almaraiBold))
						.setVerticalAlignment(VerticalAlignment.MIDDLE));
				tablaEstudiantes.addCell(new Cell()
						.add(new Paragraph("Correo").setFontSize(14f).setFont(almaraiBold))
						.setVerticalAlignment(VerticalAlignment.MIDDLE));
				
				for(Estudiante e: estudiantes) {
					String nombreCompletoEstudiante = e.getNombres() + " " + e.getApellidos();
					tablaEstudiantes.addCell(new Cell()
							.add(new Paragraph(String.valueOf(e.getNumeroControl())).setFontSize(14f).setFont(almarai))
							.setVerticalAlignment(VerticalAlignment.MIDDLE));
					tablaEstudiantes.addCell(new Cell()
							.add(new Paragraph(nombreCompletoEstudiante).setFontSize(14f).setFont(almarai))
							.setVerticalAlignment(VerticalAlignment.MIDDLE));
					tablaEstudiantes.addCell(new Cell()
							.add(new Paragraph(e.getCorreo()).setFontSize(14f).setFont(almarai))
							.setVerticalAlignment(VerticalAlignment.MIDDLE));	
				}
				listaEstudiantes = tablaEstudiantes;
			} else {
				Paragraph texto = new Paragraph("No hay estudiantes asignados a este grupo")
						.setFontSize(14f)
						.setFont(almarai);
				listaEstudiantes = texto;
			}
			
			documento.add(titulo);
			documento.add(new LineSeparator(linea));
			documento.add(informacion);
			documento.add(tituloTabla);
			documento.add(listaEstudiantes);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Documento listo");
	}
}
