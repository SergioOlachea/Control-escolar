package model;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import javax.imageio.ImageIO;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

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
				//System.out.println(estudiante);
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
	
	public boolean delete(int id) {
		String query = "DELETE FROM students WHERE id = ?";
		
		try (
				Connection conn = ConexionBD.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
			){
			stmt.setInt(1, id);
			int rs = stmt.executeUpdate();
			return rs > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void descargarInformacion(String ruta, Estudiante estudiante) {
		try (Document documento = new Document(new PdfDocument(new PdfWriter(ruta)))) {
			
            PdfFont almarai = PdfFontFactory.createFont("Fonts/Almarai-Regular.ttf");
            PdfFont almaraiBold = PdfFontFactory.createFont("Fonts/Almarai-Bold.ttf");
			
			Locale local = Locale.forLanguageTag("es");
			DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(local);
			
			Date fecha = estudiante.getFechaNacimiento();
			LocalDateTime fechaLocal;
			
			if(fecha instanceof java.sql.Date) {				
				fechaLocal = ((java.sql.Date) fecha)
						.toLocalDate().atStartOfDay();
			} else {
				fechaLocal = estudiante.getFechaNacimiento().toInstant()
						.atZone(ZoneId.systemDefault()).toLocalDateTime();
			}
			
			Paragraph titulo = new Paragraph("Información de estudiante")
					.setFont(almaraiBold)
					.setFontSize(18f)
					.setTextAlignment(TextAlignment.CENTER)
					.setHorizontalAlignment(HorizontalAlignment.CENTER);
			
			SolidLine linea = new SolidLine(5);
			
			Table tabla;
		    Paragraph informacion = new Paragraph().setFontSize(14f)
		    	    .add(new Text("Nombre(s): ").setFont(almaraiBold))
		    	    .add(new Text(estudiante.getNombres()).setFont(almarai))
		    	    .add(new Text("\nApellidos: ").setFont(almaraiBold))
		    	    .add(new Text(estudiante.getApellidos()).setFont(almarai))
		    	    .add(new Text("\nNumero control: ").setFont(almaraiBold))
		    	    .add(new Text(String.valueOf(estudiante.getNumeroControl())).setFont(almarai))
		    	    .add(new Text("\nFecha Nacimiento: ").setFont(almaraiBold))
		    	    .add(new Text(formatter.format(fechaLocal)).setFont(almarai))
		    	    .add(new Text("\nGenero: ").setFont(almaraiBold))
		    	    .add(new Text(estudiante.getGenero()).setFont(almarai))
		    	    .add(new Text("\nTelefono: ").setFont(almaraiBold))
		    	    .add(new Text(estudiante.getTelefono()).setFont(almarai))
		    	    .add(new Text("\nGrado: ").setFont(almaraiBold))
		    	    .add(new Text(String.valueOf(estudiante.getGrado())).setFont(almarai))
		    	    .add(new Text("\nDomicilio: ").setFont(almaraiBold))
		    	    .add(new Text(estudiante.getDomicilio()).setFont(almarai))
		    	    .add(new Text("\nCorreo Electronico: ").setFont(almaraiBold))
		    	    .add(new Text(estudiante.getCorreo()).setFont(almarai))
		    	    .add(new Text("\nCurp: ").setFont(almaraiBold))
		    	    .add(new Text(estudiante.getCurp()).setFont(almarai));

			if(estudiante.getFoto()!=null) {
				tabla = new Table(UnitValue.createPercentArray(new float[]{4f,1f}))
						.useAllAvailableWidth();
				
				byte[] imageBytes = Utils.toByte(Utils.toBufferedImage(estudiante.getFoto()));
				ImageData data = ImageDataFactory.create(imageBytes);
				com.itextpdf.layout.element.Image img = new com.itextpdf.layout.element.Image(
						data)
						.setWidth(150)
						.setHeight(150);
			    
			    tabla.addCell(new Cell().add(informacion)
			    		.setBorder(Border.NO_BORDER)
			    		.setTextAlignment(TextAlignment.LEFT)
			    		.setHorizontalAlignment(HorizontalAlignment.LEFT)
			    		.setVerticalAlignment(VerticalAlignment.MIDDLE));
			    tabla.addCell(new Cell().add(img)
			    		.setBorder(Border.NO_BORDER)
			    		.setTextAlignment(TextAlignment.RIGHT)
			    		.setHorizontalAlignment(HorizontalAlignment.RIGHT)
			    		.setVerticalAlignment(VerticalAlignment.TOP));
			} else {
			    tabla = new Table(1).useAllAvailableWidth();
			    
			    tabla.addCell(new Cell().add(informacion)
			    		.setBorder(Border.NO_BORDER)
			    		.setVerticalAlignment(VerticalAlignment.MIDDLE));
			}
			
			documento.add(titulo);
			documento.add(new LineSeparator(linea));
			documento.add(tabla);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Documento listo");
	}
}
