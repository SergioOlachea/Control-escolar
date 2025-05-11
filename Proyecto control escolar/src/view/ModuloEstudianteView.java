package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controlles.Controller;
public class ModuloEstudianteView {

	public void moduloAlumnos() {
		Color borde = new Color(206, 207, 202);
		Color azul2 = new Color(52, 134, 199);
		Color azul1 = new Color(54, 146, 218);
		JFrame moduloAlumnos = new JFrame();
		moduloAlumnos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		moduloAlumnos.setBounds(100, 100, 982, 647);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		moduloAlumnos.setVisible(true);

		moduloAlumnos.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel header = new JPanel();
		contentPane.add(header, BorderLayout.NORTH);
		header.setBackground(azul2);
		header.setPreferredSize(new Dimension(2147483647, 90));
		header.setLayout(new BoxLayout(header, BoxLayout.LINE_AXIS));
		header.setMaximumSize(new Dimension(2147483647, 40));
		
		header.add(Box.createRigidArea(new Dimension(10,0)));
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/imagenes/uabcs (1).png"));
		
		JLabel logoUabcs = new JLabel(logo);
		logoUabcs.setBackground(azul2);
		logoUabcs.setBorder(null);
		logoUabcs.setPreferredSize(new Dimension(100, 100));
		logoUabcs.setMaximumSize(new Dimension(60, 100));
		header.add(logoUabcs);
		
		header.add(Box.createRigidArea(new Dimension(50,0)));
		
		JLabel lblInicio = new JLabel("Modulo alumnos");
		lblInicio.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblInicio.setBorder(null);
		lblInicio.setFont(new Font("Almarai Bold", Font.PLAIN, 50));
		lblInicio.setMaximumSize(new Dimension(Integer.MAX_VALUE,80));
		lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicio.setBackground(azul2);
		header.add(lblInicio);
		
		ImageIcon iconCerrarSesion = new ImageIcon(this.getClass().getResource("/imagenes/cerrarsesion (1).png"));
		
		JButton btnCerrarSesion = new JButton(iconCerrarSesion);
		btnCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCerrarSesion.setBackground(azul2);
		btnCerrarSesion.setBorder(null);
		btnCerrarSesion.setPreferredSize(new Dimension(120,120));
		btnCerrarSesion.addActionListener(e->{
			int n = JOptionPane.showConfirmDialog(
		            null,
		            "Estas seguro que quieres cerrar sesió?",
		            "Cerrar sesión",
		            JOptionPane.YES_NO_OPTION);

		        if(n==0){
		        	Controller c = new Controller();
		            JOptionPane.showMessageDialog(null,"HOLAAAA");
		            moduloAlumnos.dispose();
		            c.despliegue();
		        }
		        else if(n==1) {
		            JOptionPane.showMessageDialog(null, "GOODBYE");
		        }
		});
		header.add(btnCerrarSesion);
		
		header.add(Box.createRigidArea(new Dimension(10,0)));
		
		JPanel options = new JPanel();
		contentPane.add(options, BorderLayout.WEST);
		options.setPreferredSize(new Dimension(120, 2147483647));
		options.setMaximumSize(new Dimension(120,2147483647));
		options.setLayout(new BoxLayout(options, BoxLayout.PAGE_AXIS));
		
		JPanel moduloAlumno = new JPanel ();
		moduloAlumno.setAlignmentX(Component.RIGHT_ALIGNMENT);
		moduloAlumno.setPreferredSize(new Dimension(130, 120));
		moduloAlumno.setMaximumSize(new Dimension(130, 130));
		moduloAlumno.setBackground(azul2);
		
		ImageIcon iconAlumnos = new ImageIcon (this.getClass().getResource("/imagenes/alumnos (1).png"));
		moduloAlumno.setLayout(new BoxLayout(moduloAlumno, BoxLayout.Y_AXIS));
		
		JButton btnAlumnos = new JButton(iconAlumnos);
		btnAlumnos.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAlumnos.setBackground(azul2);
		btnAlumnos.setBorder(null);
		
		moduloAlumno.add(btnAlumnos);
		
		JLabel lblAlumnos = new JLabel ("<html><div style='text-align: center;'>Modulo de<br>alumnos");
		lblAlumnos.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAlumnos.setForeground(new Color(255, 255, 255));
		lblAlumnos.setBackground(azul2);
		lblAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlumnos.setMaximumSize(new Dimension(80, 70));
		moduloAlumno.add(lblAlumnos);
		options.add(moduloAlumno);
		
		
		
		JPanel moduloMaestros = new JPanel ();
		moduloMaestros.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		moduloMaestros.setAlignmentX(Component.RIGHT_ALIGNMENT);
		moduloMaestros.setPreferredSize(new Dimension(130, 140));
		moduloMaestros.setMaximumSize(new Dimension(130, 130));
		moduloMaestros.setBackground(azul2);
		
		ImageIcon iconDocentnes = new ImageIcon (this.getClass().getResource("/imagenes/docentes (1).png"));
		moduloMaestros.setLayout(new BoxLayout(moduloMaestros, BoxLayout.Y_AXIS));
		
		JButton btnMaestros = new JButton(iconDocentnes);
		btnMaestros.setBorder(null);
		btnMaestros.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnMaestros.setBackground(azul2);
		moduloMaestros.add(btnMaestros);
		
		JLabel lblMaestros = new JLabel ("<html><div style='text-align: center;'>Modulo de<br>maestros");
		lblMaestros.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblMaestros.setForeground(new Color(255, 255, 255));
		lblMaestros.setBackground(azul2);
		lblMaestros.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaestros.setMaximumSize(new Dimension(80, 70));
		moduloMaestros.add(lblMaestros);
		options.add(moduloMaestros);
		
		JPanel moduloGrupo = new JPanel ();
		moduloGrupo.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		moduloGrupo.setAlignmentX(Component.RIGHT_ALIGNMENT);
		moduloGrupo.setPreferredSize(new Dimension(130, 120));
		moduloGrupo.setMaximumSize(new Dimension(130, 130));
		moduloGrupo.setBackground(azul2);
		
		ImageIcon iconGrupo = new ImageIcon (this.getClass().getResource("/imagenes/grupos (1).png"));
		moduloGrupo.setLayout(new BoxLayout(moduloGrupo, BoxLayout.Y_AXIS));
		
		JButton btnGrupos = new JButton(iconGrupo);
		btnGrupos.setBorder(null);
		btnGrupos.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnGrupos.setBackground(azul2);
		moduloGrupo.add(btnGrupos);
		
		JLabel lblGrupos = new JLabel ("<html><div style='text-align: center;'>Modulo de<br>grupos");
		lblGrupos.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblGrupos.setForeground(new Color(255, 255, 255));
		lblGrupos.setBackground(azul2);
		lblGrupos.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupos.setMaximumSize(new Dimension(80, 70));
		moduloGrupo.add(lblGrupos);
		options.add(moduloGrupo);
		
		JPanel moduloAsignatura = new JPanel ();
		moduloAsignatura.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		moduloAsignatura.setAlignmentX(Component.RIGHT_ALIGNMENT);
		moduloAsignatura.setPreferredSize(new Dimension(150, 150));
		moduloAsignatura.setMaximumSize(new Dimension(150, 150));
		moduloAsignatura.setBackground(azul2);
		
		ImageIcon iconAsignatura = new ImageIcon (this.getClass().getResource("/imagenes/asignaturas (1).png"));
		moduloAsignatura.setLayout(new BoxLayout(moduloAsignatura, BoxLayout.Y_AXIS));
		
		JButton btnAsignatura = new JButton(iconAsignatura);
		btnAsignatura.setBorder(null);
		btnAsignatura.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAsignatura.setBackground(azul2);
		moduloAsignatura.add(btnAsignatura);
		
		JLabel lblAsignatura = new JLabel ("<html><div style='text-align: center;'>Modulo de<br>asignaturas");
		lblAsignatura.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAsignatura.setForeground(new Color(255, 255, 255));
		lblAsignatura.setBackground(azul2);
		lblAsignatura.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsignatura.setMaximumSize(new Dimension(80, 70));
		moduloAsignatura.add(lblAsignatura);
		options.add(moduloAsignatura);
		
		// Panel de contenido
		
		JPanel contenido = new JPanel();
		contentPane.add(contenido, BorderLayout.CENTER);
		contenido.setBackground(Color.white);
		contenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 0));
		contenido.setLayout(new BoxLayout(contenido, BoxLayout.PAGE_AXIS));
		
		JLabel lblRegistroAlumnos = new JLabel("Registro de alumnos");
		lblRegistroAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroAlumnos.setFont(new Font("Almarai Bold",Font.PLAIN, 20));
		lblRegistroAlumnos.setMaximumSize(new Dimension(Integer.MAX_VALUE,40));
		lblRegistroAlumnos.setPreferredSize(new Dimension(Integer.MAX_VALUE,40));
		lblRegistroAlumnos.setAlignmentX(Component.CENTER_ALIGNMENT);
		contenido.add(lblRegistroAlumnos);		
		
		JPanel option = new JPanel();
		option.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		contenido.add(option);
		option.setBackground(Color.WHITE);
		option.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
		option.setLayout(new BoxLayout(option, BoxLayout.LINE_AXIS));
		
		JButton btnNuevoReg = new JButton("Nuevo");
		btnNuevoReg.setAlignmentY(Component.TOP_ALIGNMENT);
		option.add(btnNuevoReg);
		option.add(Box.createRigidArea(new Dimension(200,0)));
		
		JMenuBar filtro = new JMenuBar();
		filtro.setAlignmentY(-1.0f);
		filtro.setMargin(new Insets(10, 10, 10, 10));
		filtro.setMaximumSize(new Dimension(150, 40));
		JMenu menu = new JMenu("Filtrar");
		menu.setHorizontalAlignment(SwingConstants.CENTER);
		menu.setMaximumSize(new Dimension(150, 40));
		
		JMenuItem item1 = new JMenuItem("Identificador");  
        JMenuItem item2 = new JMenuItem("Nombre");  
        JMenuItem item3 = new JMenuItem("Grupo");
        
        item1.setPreferredSize(new Dimension(150, 20));
        item2.setPreferredSize(new Dimension(150, 20));
        item3.setPreferredSize(new Dimension(150, 20));
        
        menu.add(item1);  
        menu.add(item2);  
        menu.add(item3);
		
        filtro.add(menu);
        moduloAlumnos.repaint();
        moduloAlumnos.revalidate();
      
        option.add(filtro);
        
        option.add(Box.createRigidArea(new Dimension(10,0)));
        
        JTextField txtFiltro = new JTextField();
        txtFiltro.setAlignmentY(Component.TOP_ALIGNMENT);
        txtFiltro.setMaximumSize(new Dimension(280,40));
        option.add(txtFiltro);
        txtFiltro.setColumns(10);
        
        option.add(Box.createRigidArea(new Dimension(20,0)));
        
        JButton btnBuscar = new JButton ();
        btnBuscar.setAlignmentY(Component.TOP_ALIGNMENT);
        btnBuscar.setMaximumSize(new Dimension(50,40));
        option.add(btnBuscar);
        
        option.add(Box.createRigidArea(new Dimension(10,0)));
        
        JButton btnRefrescar = new JButton ();
        btnRefrescar.setAlignmentY(Component.TOP_ALIGNMENT);
        btnRefrescar.setMaximumSize(new Dimension(50,40));
        option.add(btnRefrescar);
        
        String[] columnas = {"Identificador", "Nombre completo", "Grupo", "Detalles del alumno", "Credencial", "Opciones"};
        Object[][] datos = new Object[10][columnas.length];
        
        for (int i = 0; i < 10; i++) {
            datos[i][0] = String.format("%03d", i + 1);
            datos[i][1] = "Marco Antonio Núñez Muñoz";
            datos[i][2] = "4A";
            datos[i][3] = "Detalles"; 
            datos[i][4] = "Credencial"; 
            datos[i][5] = "Opciones"; 
        }

        DefaultTableModel model = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 3;
            }
        };

        JTable tabla = new JTable(model);
        tabla.setRowHeight(30);

        // Botones individuales
        tabla.getColumn("Detalles del alumno").setCellRenderer(new BotonRenderer("Datos completos"));
        tabla.getColumn("Detalles del alumno").setCellEditor(new BotonEditor(new JCheckBox(), "Datos completos"));

        tabla.getColumn("Credencial").setCellRenderer(new BotonRenderer("Generar"));
        tabla.getColumn("Credencial").setCellEditor(new BotonEditor(new JCheckBox(), "Generar"));

        // Botón doble para la columna de opciones
        tabla.getColumn("Opciones").setCellRenderer(new PanelBotonesRenderer());
        tabla.getColumn("Opciones").setCellEditor(new PanelBotonesEditor(new JCheckBox()));

        contenido.add(new JScrollPane(tabla));

		
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
	 // Renderizador simple para botones
    class BotonRenderer extends JButton implements TableCellRenderer {
        public BotonRenderer(String texto) {
            setText(texto);
            setForeground(Color.BLUE);
            setBorderPainted(false);
            setContentAreaFilled(false);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // Editor para botones
    class BotonEditor extends DefaultCellEditor {
        protected JButton boton;
        private String texto;

        public BotonEditor(JCheckBox checkBox, String texto) {
            super(checkBox);
            this.texto = texto;
            boton = new JButton(texto);
            boton.setForeground(Color.BLUE);
            boton.setBorderPainted(false);
            boton.setContentAreaFilled(false);

            boton.addActionListener(e -> {
                JOptionPane.showMessageDialog(null, "Botón '" + texto + "' presionado en fila " + (tabla.getSelectedRow() + 1));
                fireEditingStopped();
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
            return boton;
        }

        public Object getCellEditorValue() {
            return texto;
        }
    }

    // Panel con dos botones
    class PanelBotonesRenderer extends JPanel implements TableCellRenderer {
        public PanelBotonesRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            JButton editar = new JButton(new ImageIcon("edit.png"));
            JButton borrar = new JButton(new ImageIcon("delete.png"));
            add(editar);
            add(borrar);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    class PanelBotonesEditor extends DefaultCellEditor {
        protected JPanel panel;
        protected JButton editar, borrar;

        public PanelBotonesEditor(JCheckBox checkBox) {
            super(checkBox);
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            editar = new JButton(new ImageIcon("edit.png"));
            borrar = new JButton(new ImageIcon("delete.png"));

            editar.addActionListener(e -> {
                JOptionPane.showMessageDialog(null, "Editar fila " + (tabla.getSelectedRow() + 1));
                fireEditingStopped();
            });

            borrar.addActionListener(e -> {
                JOptionPane.showMessageDialog(null, "Eliminar fila " + (tabla.getSelectedRow() + 1));
                fireEditingStopped();
            });

            panel.add(editar);
            panel.add(borrar);
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
            return panel;
        }

        public Object getCellEditorValue() {
            return "";
        }
    }
    JTable tabla;
    private JTextField textField;
}
