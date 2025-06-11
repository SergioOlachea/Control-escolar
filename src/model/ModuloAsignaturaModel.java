package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			e.printStackTrace();
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
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update(int id, Asignatura asignatura) {
		String query = "UPDATE courses "
				+ "		   SET name=?, "
				+ "			   description=? "
				+ "		WHERE  id=?";
		try (
				Connection conn = ConexionBD.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
			){
			stmt.setString(1, asignatura.getNombre());
			stmt.setString(2, asignatura.getDescripcion());
			stmt.setInt(3, id);
			int rs = stmt.executeUpdate();
			return rs > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(int id) {
		String query = "DELETE FROM courses WHERE id = ?";
		
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
	
	public void descargarPdf(String ruta, Asignatura asignatura, int idGrupo) {
		Grupo grupo = new ModuloGrupoModel().getGrupoById(idGrupo);
		if(grupo!=null) {
		try(Document documento = new Document(new PdfDocument(new PdfWriter(ruta)))) {
            PdfFont almarai = PdfFontFactory.createFont("Fonts/Almarai-Regular.ttf");
            PdfFont almaraiBold = PdfFontFactory.createFont("Fonts/Almarai-Bold.ttf");
            
            Asignatura asignaturaDefault = new Asignatura();
            asignaturaDefault.setNombre("No asignado");
            
            Docente docenteDefault = new Docente();
            docenteDefault.setNombres("No");
            docenteDefault.setApellidos("Asignado");
            docenteDefault.setCorreo("No hay docente asignado");
            
            Docente docente = Optional.ofNullable(grupo.getDocente()).orElse(docenteDefault);
            ArrayList<Estudiante> estudiantes = grupo.getEstudiantes();
            
			Paragraph titulo = new Paragraph("Información de Asignatura")
					.setFont(almaraiBold)
					.setFontSize(18f)
					.setTextAlignment(TextAlignment.CENTER)
					.setHorizontalAlignment(HorizontalAlignment.CENTER);
			
			SolidLine linea = new SolidLine(5);
			
			Paragraph nombreAsignatura = new Paragraph().setFontSize(14f)
					.add(new Text("Nombre: ").setFont(almaraiBold))
					.add(new Text(asignatura.getNombre()).setFont(almarai))
					.setMarginTop(20f);
			Paragraph descripcion = new Paragraph(asignatura.getDescripcion())
					.setFontSize(14f)
					.setFont(almarai);
			
			Table informacion = new Table(UnitValue.createPercentArray(new float[]{1f,1f}))
					.useAllAvailableWidth();
			
		    Paragraph nombreGrupo = new Paragraph().setFontSize(14f)
		    	    .add(new Text("Grupo: " ).setFont(almaraiBold))
		    	    .add(new Text(grupo.getNombre()).setFont(almarai));
		    
			
			String nombreCompletoDocente = docente.getNombres() + " " + docente.getApellidos();
		    Paragraph nombreDocente = new Paragraph().setFontSize(14f)
		    	    .add(new Text("Docente: ").setFont(almaraiBold))
		    	    .add(new Text(nombreCompletoDocente).setFont(almarai));
		    
		    informacion.addCell(new Cell().add(nombreGrupo)
		    		.setBorder(Border.NO_BORDER)
		    		.setVerticalAlignment(VerticalAlignment.TOP));
		    informacion.addCell(new Cell().add(nombreDocente)
		    		.setBorder(Border.NO_BORDER)
		    		.setVerticalAlignment(VerticalAlignment.TOP));
		    
		    Paragraph correoDocente = new Paragraph().setFontSize(14f)
		    	    .add(new Text("Correo: ").setFont(almaraiBold))
		    	    .add(new Text(docente.getCorreo()).setFont(almarai))
		    	    .setMarginBottom(20f);
			
		    
			
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
			documento.add(nombreAsignatura);
			documento.add(descripcion);
			documento.add(informacion);
			documento.add(correoDocente);
			documento.add(tituloTabla);
			documento.add(listaEstudiantes);
			
			System.out.println("Documento listo");	
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}
	
}
