package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;


public class View {
	private JFrame ventana;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private BufferedImage imagenLogin;
	private JTextField txtPassword;
	private JTextField txtEmail;
	private boolean mostrar=false;
	public View() {
		
	}
	public void login() {
		ventana=new JFrame();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setBounds(100, 100, 1000, 600);
		ventana.getContentPane().setLayout(new BorderLayout());
		
		ventana.setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Login

		try {
			imagenLogin = ImageIO.read(this.getClass().getResource("/imagenes/login.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		ImagenPanel panelImagen = new ImagenPanel(imagenLogin);
		panelImagen.setPreferredSize(new Dimension(ventana.getWidth() / 2, ventana.getHeight()));
		panelImagen.revalidate();
		panelImagen.repaint();
		ventana.getContentPane().add(panelImagen, BorderLayout.WEST);
		
		JPanel login = new JPanel();
		ventana.getContentPane().add(login, BorderLayout.CENTER);
		login.setLayout(new BoxLayout(login, BoxLayout.Y_AXIS));
		login.setBorder(new EmptyBorder(40, 40, 40, 40));
		
		JLabel lblTitle = new JLabel("Control escolar");
		lblTitle.setFont(new Font("Almarai ExtraBold",Font.PLAIN, 25));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		login.add(lblTitle);
		
		login.add(Box.createRigidArea(new Dimension(0,20)));
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setAlignmentX(0.5f);
		lblEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		login.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		login.add(txtEmail);
		txtEmail.setColumns(10);
		
		login.add(Box.createRigidArea(new Dimension(0,20)));
		
		JPanel password = new JPanel();
		password.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		password.setLayout(new BoxLayout(password, BoxLayout.X_AXIS));
		password.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		
		JLabel lblPassword = new JLabel();
		login.add(lblPassword);
		lblPassword.setText("Contraseña");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		passwordField = new JPasswordField();
		password.add(passwordField);
		passwordField.setMaximumSize(new Dimension(400, 40));
		passwordField.setColumns(10);
		
		ImageIcon iconMostrar = new ImageIcon(this.getClass().getResource("/imagenes/mostrar.png"));
		ImageIcon iconOcultar = new ImageIcon(this.getClass().getResource("/imagenes/ocultar.png"));
		JButton btnMostrar = new JButton(iconMostrar);
		btnMostrar.setPreferredSize(new Dimension(40, 40));
		btnMostrar.setMaximumSize(new Dimension(40, 40));
		btnMostrar.setMinimumSize(new Dimension(40, 40));
		btnMostrar.setBackground(Color.WHITE);
		btnMostrar.addActionListener(e->{
			mostrar=!mostrar;
			if (mostrar) {
				passwordField.setEchoChar((char) 0); // muestra el texto
		        btnMostrar.setIcon(iconOcultar);
		    } else {
		    	passwordField.setEchoChar('•'); // oculta el texto
		        btnMostrar.setIcon(iconMostrar);
		    }
		});
		password.add(btnMostrar);
		
		login.add(password);
		
		ventana.repaint();
		ventana.revalidate();
		
		// fuentes 
		System.out.println(getClass().getResource("/Fonts/Almarai-Light.ttf"));
		final GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
		final List<String> AVAILABLE_FONT_FAMILY_NAMES = Arrays.asList(GE.getAvailableFontFamilyNames());
		try {
		    final List<File> LIST = Arrays.asList(
	    		new File("src/Fonts/Almarai-ExtraBold.ttf"),
		        new File("src/Fonts/Almarai-Light.ttf"),
		        new File("src/Fonts/Almarai-Regular.ttf"),
		        new File("src/Fonts/Almarai-Bold.ttf")
		     );
		     for (File LIST_ITEM : LIST) {
		         if (LIST_ITEM.exists()) {
		             Font FONT = Font.createFont(Font.TRUETYPE_FONT, LIST_ITEM);
		             if (!AVAILABLE_FONT_FAMILY_NAMES.contains(FONT.getFontName())){ 
		                 GE.registerFont(FONT);
		             }
		         }
		     }
		} catch (FontFormatException | IOException exception) {
		    JOptionPane.showMessageDialog(null, exception.getMessage());
		}
 	 }
	
	//subClase para hacer la imagen responsiva
	 class ImagenPanel extends JPanel {
	        private BufferedImage imagen;

	        public ImagenPanel(BufferedImage img) {
	            this.imagen = img;
	        }

	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            if (imagen != null) {
	                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
	            }
	        }
	    }
}
