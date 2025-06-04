package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
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

import javax.imageio.ImageIO;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
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
	
	public boolean update(int id, Docente docente) throws UniqueKeyViolationException {
		String query = "UPDATE teachers "
				+ "		   SET first_name=?, "
				+ "			   last_name=?, "
				+ "	           gender=?, "
				+ "			   birthday=?, "
				+ "			   phone_number=?, "
				+ "			   email=?, "
				+ "			   degree=?, "
				+ "			   curp=?, "
				+ "            address=?, "
				+ "            picture=? "
				+ "		WHERE  id=?";
		
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
		String query = "DELETE FROM teachers WHERE id = ?";
		
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
	
	public void descargarInformacion(String ruta, Docente docente) {
		try (Document documento = new Document(new PdfDocument(new PdfWriter(ruta)))) {
			
            PdfFont almarai = PdfFontFactory.createFont("Fonts/Almarai-Regular.ttf");
            PdfFont almaraiBold = PdfFontFactory.createFont("Fonts/Almarai-Bold.ttf");
			
			Locale local = Locale.forLanguageTag("es");
			DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(local);
			
			Date fecha = docente.getFechaNacimiento();
			LocalDateTime fechaLocal;
			
			if(fecha instanceof java.sql.Date) {				
				fechaLocal = ((java.sql.Date) fecha)
						.toLocalDate().atStartOfDay();
			} else {
				fechaLocal = docente.getFechaNacimiento().toInstant()
						.atZone(ZoneId.systemDefault()).toLocalDateTime();
			}
			
			Paragraph titulo = new Paragraph("Información de docente")
					.setFont(almaraiBold)
					.setFontSize(18f)
					.setTextAlignment(TextAlignment.CENTER)
					.setHorizontalAlignment(HorizontalAlignment.CENTER);
			
			SolidLine linea = new SolidLine(5);
			
			Table tabla;
		    Paragraph informacion = new Paragraph().setFontSize(14f)
		    	    .add(new Text("Nombre(s): ").setFont(almaraiBold))
		    	    .add(new Text(docente.getNombres()).setFont(almarai))
		    	    .add(new Text("\nApellidos: ").setFont(almaraiBold))
		    	    .add(new Text(docente.getApellidos()).setFont(almarai))
		    	    .add(new Text("\nNumero control: ").setFont(almaraiBold))
		    	    .add(new Text(String.valueOf(docente.getNumeroControl())).setFont(almarai))
		    	    .add(new Text("\nFecha Nacimiento: ").setFont(almaraiBold))
		    	    .add(new Text(formatter.format(fechaLocal)).setFont(almarai))
		    	    .add(new Text("\nGenero: ").setFont(almaraiBold))
		    	    .add(new Text(docente.getGenero()).setFont(almarai))
		    	    .add(new Text("\nTelefono: ").setFont(almaraiBold))
		    	    .add(new Text(docente.getTelefono()).setFont(almarai))
		    	    .add(new Text("\nGrado de estudios: ").setFont(almaraiBold))
		    	    .add(new Text(String.valueOf(docente.getGradoEstudios())).setFont(almarai))
		    	    .add(new Text("\nDomicilio: ").setFont(almaraiBold))
		    	    .add(new Text(docente.getDomicilio()).setFont(almarai))
		    	    .add(new Text("\nCorreo Electronico: ").setFont(almaraiBold))
		    	    .add(new Text(docente.getCorreo()).setFont(almarai))
		    	    .add(new Text("\nCurp: ").setFont(almaraiBold))
		    	    .add(new Text(docente.getCurp()).setFont(almarai));

			if(docente.getFoto()!=null) {
				tabla = new Table(UnitValue.createPercentArray(new float[]{4f,1f}))
						.useAllAvailableWidth();
				
				byte[] imageBytes = Utils.toByte(Utils.toBufferedImage(docente.getFoto()));
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
	
	public void descargarCredencial(String ruta, Docente docente) {
		try (Document documento = new Document(new PdfDocument(new PdfWriter(ruta)))) {

			PdfFont almarai = PdfFontFactory.createFont("Fonts/Almarai-Regular.ttf");
			PdfFont almaraiBold = PdfFontFactory.createFont("Fonts/Almarai-Bold.ttf");

			if(docente.getFoto()!=null) {
				byte[] imageBytes = Utils.toByte(Utils.toBufferedImage(docente.getFoto()));
				ImageData data = ImageDataFactory.create(imageBytes);
				com.itextpdf.layout.element.Image img = new com.itextpdf.layout.element.Image(
						data)
						.setWidth(250)
						.setHeight(250)
						.setHorizontalAlignment(HorizontalAlignment.CENTER)
						.setTextAlignment(TextAlignment.CENTER)
						.setMarginTop(25f)
						.setMarginBottom(25f);
				documento.add(img);
			} else {
				documento.setTopMargin(40f);
			}
			Paragraph noControl = new Paragraph()
					.setFont(almarai)
					.setFontSize(28f)
					.add("No. control: ")
					.add(String.valueOf(docente.getNumeroControl()))
					.setHorizontalAlignment(HorizontalAlignment.CENTER)
					.setTextAlignment(TextAlignment.CENTER);


			Paragraph docenteParagraph = new Paragraph()
					.setFont(almaraiBold)
					.setFontSize(40f)
					.add("DOCENTE")
					.setHorizontalAlignment(HorizontalAlignment.CENTER)
					.setTextAlignment(TextAlignment.CENTER);

			Paragraph nombre = new Paragraph()
					.setFont(almarai)
					.setFontSize(28f)
					.add(docente.getNombres())
					.add(" ")
					.add(docente.getApellidos())
					.setHorizontalAlignment(HorizontalAlignment.CENTER)
					.setTextAlignment(TextAlignment.CENTER);

			BufferedImage logoUabcs = ImageIO.read(this.getClass().getResource("/imagenes/uabcs (1).png"));
			byte[] logoBytes = Utils.toByte(logoUabcs);
			ImageData logoData = ImageDataFactory.create(logoBytes);
			com.itextpdf.layout.element.Image imgUabcs = new com.itextpdf.layout.element.Image(logoData)
					.setWidth(150)
					.setHeight(150)
					.setHorizontalAlignment(HorizontalAlignment.CENTER)
					.setTextAlignment(TextAlignment.CENTER)
					.setMarginTop(25f);

			documento.add(noControl);
			documento.add(docenteParagraph);
			documento.add(nombre);
			documento.add(imgUabcs);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Credencial lista");
	}
}
