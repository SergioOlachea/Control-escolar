package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import controlles.HomeController;
import model.AuthModel;


public class View {
	private JFrame ventana;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private BufferedImage imagenLogin;
	private JTextField txtPassword;
	private JTextField txtEmail;
	private boolean mostrar=false;
	AuthModel funciones;
	Color azul1;
	Color borde;
	public View() {
		azul1= new Color(54, 146, 218);
		borde = new Color(206, 207, 202);
		funciones = new AuthModel();
	}
	public void login() {
		ventana=new JFrame();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setBounds(100, 100, 800, 600);
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
		login.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		JPanel contentIcon = new JPanel ();
		contentIcon.setLayout(new BoxLayout(contentIcon, BoxLayout.X_AXIS));
		contentIcon.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		
		ImageIcon iconLogo = new ImageIcon(this.getClass().getResource("/imagenes/logouni.png"));
		JLabel lblLogo = new JLabel(iconLogo);
		lblLogo.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentIcon.add(lblLogo);
		login.add(contentIcon);
		
		login.add(Box.createRigidArea(new Dimension(0,40)));
		
		JLabel lblTitle = new JLabel("Control escolar");
		lblTitle.setFont(new Font("Almarai-ExtraBold",Font.PLAIN, 25));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setPreferredSize(new Dimension(Integer.MAX_VALUE,25));
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		login.add(lblTitle);
		
		JLabel lblMensaje = new JLabel("Gestiona cuestiones escolares al instante");
		lblMensaje.setHorizontalAlignment(SwingConstants.LEFT);
		lblMensaje.setFont(new Font("Almarai ",Font.PLAIN, 12));
		lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
		login.add(lblMensaje);
		
		login.add(Box.createRigidArea(new Dimension(0,40)));
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setAlignmentX(0.5f);
		lblEmail.setFont(new Font("Almarai",Font.PLAIN, 12));
		lblEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		login.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setToolTipText("Ingrese email");
		txtEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		txtEmail.setBorder(BorderFactory.createLineBorder(borde,2));
		login.add(txtEmail);
		txtEmail.setColumns(10);
		
		login.add(Box.createRigidArea(new Dimension(0,10)));
		
		// Contenedor de JPasswordField y Boton mostrar y ocultar
		JPanel password = new JPanel();
		password.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		password.setLayout(new BoxLayout(password, BoxLayout.LINE_AXIS));
		password.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		password.setBorder(BorderFactory.createLineBorder(borde,2));
		
		JLabel lblPassword = new JLabel();
		login.add(lblPassword);
		lblPassword.setFont(new Font("Almarai",Font.PLAIN, 13));
		lblPassword.setText("Contraseña");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		passwordField = new JPasswordField();
		passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		passwordField.setToolTipText("Ingrese contraseña");
		passwordField.setBorder(null);
		passwordField.setColumns(10);
		password.add(passwordField);
		
		ImageIcon iconMostrar = new ImageIcon(this.getClass().getResource("/imagenes/mostrar.png"));
		ImageIcon iconOcultar = new ImageIcon(this.getClass().getResource("/imagenes/ocultar.png"));
		JButton btnMostrar = new JButton(iconMostrar);
		btnMostrar.setAlignmentX(-0.5f);
		btnMostrar.setBorderPainted(false);
		btnMostrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMostrar.setPreferredSize(new Dimension(40, 40));
		btnMostrar.setMaximumSize(new Dimension(40, 40));
		btnMostrar.setMinimumSize(new Dimension(40, 40));
		btnMostrar.setBackground(Color.WHITE);
		btnMostrar.addActionListener(e->{
			mostrar=!mostrar;
			if (mostrar) {
				passwordField.setEchoChar((char) 0); 
		        btnMostrar.setIcon(iconOcultar);
		    } else {
		    	passwordField.setEchoChar('•'); 
		        btnMostrar.setIcon(iconMostrar);
		    }
		});
		password.add(btnMostrar);
		login.add(password);
		
		login.add(Box.createRigidArea(new Dimension(0,90)));
		
		// boton para iniciar sesion
		JButton btnIniciarSesion =new JButton ("Iniciar sesión");
		btnIniciarSesion.setAlignmentX(0.5f);
		btnIniciarSesion.setBackground(azul1);
		btnIniciarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnIniciarSesion.setFont(new Font("Almarai-Bold",Font.PLAIN, 20));
		btnIniciarSesion.setForeground(Color.WHITE);
		btnIniciarSesion.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		btnIniciarSesion.addActionListener(e->{
			
			String passText = new String(passwordField.getPassword());
			Boolean flag1 = false, flag2 = false;
			String username = txtEmail.getText();
			
			if( passText.equals("") ) {
				
				password.setBorder(BorderFactory.createLineBorder(Color.red,3));
				
			}else {
				
				password.setBorder(BorderFactory.createLineBorder(Color.green,3));
				flag1 = true;
			}
			
			
			if(txtEmail.getText().equals("")) {
				txtEmail.setBorder(BorderFactory.createLineBorder(Color.red,3));
			}else {
				
				txtEmail.setBorder(BorderFactory.createLineBorder(Color.green,3));
				flag2 = true;
			}if(flag1 && flag2) {
				
				boolean user_auth = funciones.access(username,passText); 
				if(user_auth) {
				 System.out.println("Validacion");
				 JOptionPane.showMessageDialog(ventana, "Bienvenido.");
				 ventana.dispose();
				 HomeController hc = new HomeController();
				 hc.home();
				 
				}else {
					txtEmail.setBorder(BorderFactory.createLineBorder(Color.red,3));
					password.setBorder(BorderFactory.createLineBorder(Color.red,3));
					JOptionPane.showMessageDialog(ventana, "email o contraseña incorrectos","Datos incorrectos",JOptionPane.WARNING_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(ventana, "email o contraseña incorrectos","Datos incorrectos",JOptionPane.WARNING_MESSAGE);
			}
		});
		login.add(btnIniciarSesion);
		
		login.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
			    KeyStroke.getKeyStroke("ENTER"), "presionarBoton"
			);
			login.getActionMap().put("presionarBoton", new AbstractAction() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        btnIniciarSesion.doClick(); 
			    }
			});
		
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
