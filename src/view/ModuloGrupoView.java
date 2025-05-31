package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controlles.Controller;
import controlles.ModuloAsignaturaController;
import controlles.ModuloDocenteController;
import controlles.ModuloEstudianteController;
import controlles.ModuloGrupoController;
import model.Estudiante;
import model.ModuloAsignaturaModel;
import model.ModuloDocenteModel;
import model.ModuloEstudianteModel;
import view.ModuloAsignturaView.BotonRenderer;
import view.ModuloDocenteView.BotonEditor;
import view.ModuloDocenteView.PanelBotonesEditor;
import view.ModuloDocenteView.PanelBotonesRenderer;

public class ModuloGrupoView {
	ModuloEstudianteModel mem =new ModuloEstudianteModel();
	ArrayList<Estudiante> listaEstudiantes =mem.getEstudiantes();
	
	
	private ArrayList<Estudiante> estudiantesAñadidos;
	
	//private Docente docenteGrupo;
	
	ImageIcon buscar = new ImageIcon("/imagenes/buscar.png");
	ModuloDocenteModel mdm= new ModuloDocenteModel();
	ModuloAsignaturaModel mam = new ModuloAsignaturaModel();
	Color borde = new Color(206, 207, 202);
	Color azul2 = new Color(52, 134, 199);
	Color azul1 = new Color(54, 146, 218);
	Color azulC = new Color(40, 103, 152);
	Color azulBorde= new Color(101, 166, 217);
	public void moduloGrupo() {
		JFrame modulo= new JFrame();
		modulo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		modulo.setBounds(100, 100, 982, 647);
		JPanel contentPane = new JPanel();
		modulo.setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		modulo.setVisible(true);

		modulo.setContentPane(contentPane);
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
		
		JLabel lblInicio = new JLabel("Modulo grupo");
		lblInicio.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblInicio.setBorder(null);
		lblInicio.setFont(new Font("Almarai-Bold", Font.PLAIN, 50));
		lblInicio.setForeground(Color.white);
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
		            "Estas seguro que quieres cerrar sesión?",
		            "Cerrar sesión",
		            JOptionPane.YES_NO_OPTION);

		        if(n==0){
		        	Controller c = new Controller();
		            modulo.dispose();
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
		
		JPanel moduloAlumnos = new JPanel ();
		moduloAlumnos.setAlignmentX(Component.RIGHT_ALIGNMENT);
		moduloAlumnos.setPreferredSize(new Dimension(130, 120));
		moduloAlumnos.setMaximumSize(new Dimension(130, 130));
		moduloAlumnos.setBackground(azul2);
		
		ImageIcon iconAlumnos = new ImageIcon (this.getClass().getResource("/imagenes/alumnos (1).png"));
		moduloAlumnos.setLayout(new BoxLayout(moduloAlumnos, BoxLayout.Y_AXIS));
		
		JButton btnAlumnos = new JButton(iconAlumnos);
		btnAlumnos.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAlumnos.setBackground(azul2);
		btnAlumnos.setBorder(null);
		btnAlumnos.addActionListener(e->{
			ModuloEstudianteController mec = new ModuloEstudianteController();
			modulo.dispose();
			mec.ModuloEstudiante();
		});
		
		moduloAlumnos.add(btnAlumnos);
		
		JLabel lblAlumnos = new JLabel ("<html><div style='text-align: center;'>Modulo de<br>alumnos");
		lblAlumnos.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAlumnos.setForeground(new Color(255, 255, 255));
		lblAlumnos.setBackground(azul2);
		lblAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlumnos.setMaximumSize(new Dimension(80, 70));
		moduloAlumnos.add(lblAlumnos);
		options.add(moduloAlumnos);
		
		
		
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
		btnMaestros.addActionListener(e->{
			ModuloDocenteController mdc= new ModuloDocenteController();
			modulo.dispose();
			mdc.moduloDocente();
		});
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
		moduloGrupo.setBackground(azulBorde);
		
		ImageIcon iconGrupo = new ImageIcon (this.getClass().getResource("/imagenes/grupos (1).png"));
		moduloGrupo.setLayout(new BoxLayout(moduloGrupo, BoxLayout.Y_AXIS));
		
		JButton btnGrupos = new JButton(iconGrupo);
		btnGrupos.setBorder(null);
		btnGrupos.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnGrupos.setBackground(azulBorde);
		btnGrupos.addActionListener(e->{
			ModuloGrupoController mgc= new ModuloGrupoController();
			modulo.dispose();
			mgc.moduloGrupo();
		});
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
		btnAsignatura.addActionListener(e->{
			ModuloAsignaturaController mac= new ModuloAsignaturaController();
			modulo.dispose();
			mac.moduloAsignatura();
			
		});
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
		
		JLabel lblRegistroAlumnos = new JLabel("Registro de grupo");
		lblRegistroAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroAlumnos.setFont(new Font("Almarai-Bold",Font.PLAIN, 25));
		lblRegistroAlumnos.setMaximumSize(new Dimension(Integer.MAX_VALUE,40));
		lblRegistroAlumnos.setPreferredSize(new Dimension(Integer.MAX_VALUE,40));
		lblRegistroAlumnos.setForeground(Color.BLACK);
		lblRegistroAlumnos.setAlignmentX(Component.CENTER_ALIGNMENT);
		contenido.add(lblRegistroAlumnos);		
		
		JPanel option = new JPanel();
		option.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		option.setBackground(Color.WHITE);
		option.setLayout(new BoxLayout(option, BoxLayout.LINE_AXIS));
		option.setBorder(new EmptyBorder(15, 0, 5, 0));
		contenido.add(option);
		
		ImageIcon add = new ImageIcon(getClass().getResource("/imagenes/añadir.png"));
		JButton btnNuevoReg = new JButton(add);
		btnNuevoReg.setText("Nuevo");
		btnNuevoReg.setBackground(azul1);
		btnNuevoReg.setAlignmentY(Component.TOP_ALIGNMENT);
		btnNuevoReg.setForeground(Color.white);
		btnNuevoReg.setBorder(BorderFactory.createLineBorder(azulBorde,5));
		btnNuevoReg.addActionListener(e->{
			ModuloGrupoController mgc = new ModuloGrupoController();
			modulo.dispose();
			mgc.crear();
		});
		btnNuevoReg.setMaximumSize(new Dimension(75,30));
		option.add(btnNuevoReg);
		
		option.add(Box.createRigidArea(new Dimension(180,0)));
		
	
		String[] opciones = {"Filtrar","Identificador", "Nombre", "Grupo" };
		JComboBox<String> filtroCombo = new JComboBox<>(opciones);
		filtroCombo.setAlignmentY(Component.TOP_ALIGNMENT);
		filtroCombo.setMaximumSize(new Dimension(150, 30));
		filtroCombo.setBorder(BorderFactory.createLineBorder(borde, 5));
		filtroCombo.setBackground(Color.white);
		
		option.add(filtroCombo);
        
        option.add(Box.createRigidArea(new Dimension(10,0)));
        
        JTextField txtFiltro = new JTextField();
        txtFiltro.setAlignmentY(Component.TOP_ALIGNMENT);
        txtFiltro.setBorder(BorderFactory.createLineBorder(borde,5));
        txtFiltro.setMaximumSize(new Dimension(280,30));
        option.add(txtFiltro);
        txtFiltro.setColumns(10);
        
        option.add(Box.createRigidArea(new Dimension(20,0)));
        
    	// tabla 
        String[] columnas = {"Identificador", "Nombre", "Docente", "Asignatura", "Detalles del grupo", "Opciones"};
        Object[][] datos = new Object[10][columnas.length];

        for (int i = 0; i < 10; i++) {
            datos[i][0] = String.format("%03d", i + 1); 
            datos[i][1] = "6A"; 
            datos[i][2] = "Eduardo Rios Villanueva"; 
            datos[i][3] = "Programación III"; 
            datos[i][4] = "Datos completos"; 
            datos[i][5] = "Opciones"; 
        }

        DefaultTableModel model = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 4; 
            }
        };

        JTable tabla = new JTable(model);
        tabla.setRowHeight(30);

        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);
        tabla.getColumn("Identificador").setCellRenderer(centrado);
        tabla.getColumn("Nombre").setCellRenderer(centrado);
        tabla.getColumn("Docente").setCellRenderer(centrado);
        tabla.getColumn("Asignatura").setCellRenderer(centrado);

        tabla.getColumnModel().getColumn(0).setPreferredWidth(80);  
        tabla.getColumnModel().getColumn(1).setPreferredWidth(60); 
        tabla.getColumnModel().getColumn(2).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(120); 
        tabla.getColumnModel().getColumn(4).setPreferredWidth(120);

        tabla.getColumn("Detalles del grupo").setCellRenderer(new BotonRenderer("Datos completos"));
        tabla.getColumn("Detalles del grupo").setCellEditor(new BotonEditor(new JCheckBox(), "Datos completos", tabla,modulo));
        
        tabla.getColumnModel().getColumn(tabla.getColumnCount() - 1).setCellRenderer(new PanelBotonesRenderer());
        tabla.getColumnModel().getColumn(tabla.getColumnCount() - 1).setCellEditor(new PanelBotonesEditor(new JCheckBox(), tabla,modulo));
        
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBackground(Color.white);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        contenido.add(scrollPane);
        
        
        //Botones de buscar y refrescar despues de la tabla
        ImageIcon buscar = new ImageIcon(this.getClass().getResource("/imagenes/busqueda.png"));
        
        JButton btnBuscar = new JButton (buscar);
        btnBuscar.setBackground(azul1);
        btnBuscar.setBorder(BorderFactory.createLineBorder(azulBorde,5));
        btnBuscar.setAlignmentY(Component.TOP_ALIGNMENT);
        btnBuscar.setMaximumSize(new Dimension(50,30));
        btnBuscar.addActionListener(e -> {
    
    	    String texto = txtFiltro.getText().trim();
    	    int seleccion = filtroCombo.getSelectedIndex();

    	    if (seleccion == 0) {
    	        JOptionPane.showMessageDialog(null, "Seleccione un campo para filtrar.");
    	        return;
    	    }

    	    int columna = seleccion - 1;
    	    
    	    if (columna == 0 && !texto.matches("\\d+")) {
    	        JOptionPane.showMessageDialog(null, "El identificador debe ser numérico.");
    	        return;
    	    }

    	    DefaultTableModel model1 = (DefaultTableModel) tabla.getModel();
    	    TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
    	    tabla.setRowSorter(sorter);

    	    sorter.setRowFilter(RowFilter.regexFilter("(?i)^" + Pattern.quote(texto) + "$", columna));

    	    if (tabla.getRowCount() == 0) {
    	        JOptionPane.showMessageDialog(null, "No se encontró ningún resultado para: " + texto);
    	        tabla.setRowSorter(null); // Quitar filtro
    	    }
        	

        });

        option.add(btnBuscar);
        
        option.add(Box.createRigidArea(new Dimension(10,0)));
        
        ImageIcon refresh = new ImageIcon(this.getClass().getResource("/imagenes/refresh (1).png"));
        
        JButton btnRefrescar = new JButton (refresh);
        btnRefrescar.setAlignmentY(Component.TOP_ALIGNMENT);
        btnRefrescar.setMaximumSize(new Dimension(50,30));
        btnRefrescar.setBackground(azul1);
        btnRefrescar.setBorder(BorderFactory.createLineBorder(azulBorde,5));
        btnRefrescar.addActionListener(e -> {
            txtFiltro.setText("");
            tabla.setRowSorter(null);
            filtroCombo.setSelectedIndex(0);
        });
        option.add(btnRefrescar);
        
        

		
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
	
	public void crear() {
    	Color borde = new Color(206, 207, 202);
    	Color azul2 = new Color(52, 134, 199);
    	Color azul1 = new Color(54, 146, 218);
    	Color azulBorde = new Color(101, 166, 217);
    	JFrame modulo = new JFrame();
    	modulo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	modulo.setBounds(100, 100, 982, 647);
    	JPanel contentPane = new JPanel();
    	modulo.setLocationRelativeTo(null);
    	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    	modulo.setVisible(true);
    	modulo.setContentPane(contentPane);
    	contentPane.setLayout(new BorderLayout(0, 0));

    	JPanel header = new JPanel();
    	contentPane.add(header, BorderLayout.NORTH);
    	header.setBackground(azul2);
    	header.setPreferredSize(new Dimension(2147483647, 90));
    	header.setLayout(new BoxLayout(header, BoxLayout.LINE_AXIS));
    	header.setMaximumSize(new Dimension(2147483647, 40));

    	header.add(Box.createRigidArea(new Dimension(10, 0)));
    	ImageIcon logo = new ImageIcon(this.getClass().getResource("/imagenes/uabcs (1).png"));

    	JLabel logoUabcs = new JLabel(logo);
    	logoUabcs.setBackground(azul2);
    	logoUabcs.setBorder(null);
    	logoUabcs.setPreferredSize(new Dimension(100, 100));
    	logoUabcs.setMaximumSize(new Dimension(60, 100));
    	header.add(logoUabcs);

    	header.add(Box.createRigidArea(new Dimension(50, 0)));

    	JLabel lblInicio = new JLabel("Modulo grupo");
    	lblInicio.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblInicio.setBorder(null);
    	lblInicio.setFont(new Font("Almarai-Bold", Font.PLAIN, 50));
    	lblInicio.setForeground(Color.white);
    	lblInicio.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
    	lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
    	lblInicio.setBackground(azul2);
    	header.add(lblInicio);

    	ImageIcon iconCerrarSesion = new ImageIcon(this.getClass().getResource("/imagenes/cerrarsesion (1).png"));

    	JButton btnCerrarSesion = new JButton(iconCerrarSesion);
    	btnCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnCerrarSesion.setBackground(azul2);
    	btnCerrarSesion.setBorder(null);
    	btnCerrarSesion.setPreferredSize(new Dimension(120, 120));
    	btnCerrarSesion.addActionListener(e -> {
    	    int n = JOptionPane.showConfirmDialog(
    	            null,
    	            "Estas seguro que quieres cerrar sesión?",
    	            "Cerrar sesión",
    	            JOptionPane.YES_NO_OPTION);

    	    if (n == 0) {
    	        Controller c = new Controller();
    	        modulo.dispose();
    	        c.despliegue();
    	    } else if (n == 1) {
    	        JOptionPane.showMessageDialog(null, "GOODBYE");
    	    }
    	});
    	header.add(btnCerrarSesion);

    	header.add(Box.createRigidArea(new Dimension(10, 0)));

    	JPanel options = new JPanel();
    	contentPane.add(options, BorderLayout.WEST);
    	options.setPreferredSize(new Dimension(120, 2147483647));
    	options.setMaximumSize(new Dimension(120, 2147483647));
    	options.setLayout(new BoxLayout(options, BoxLayout.PAGE_AXIS));

    	JPanel moduloAlumnos = new JPanel();
    	moduloAlumnos.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloAlumnos.setPreferredSize(new Dimension(130, 120));
    	moduloAlumnos.setMaximumSize(new Dimension(130, 130));
    	moduloAlumnos.setBackground(azul2);

    	ImageIcon iconAlumnos = new ImageIcon(this.getClass().getResource("/imagenes/alumnos (1).png"));
    	moduloAlumnos.setLayout(new BoxLayout(moduloAlumnos, BoxLayout.Y_AXIS));

    	JButton btnAlumnos = new JButton(iconAlumnos);
    	btnAlumnos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnAlumnos.setBackground(azul2);
    	btnAlumnos.setBorder(null);
    	btnAlumnos.addActionListener(e -> {
    	    ModuloEstudianteController mac = new ModuloEstudianteController();
    	    modulo.dispose();
    	    mac.ModuloEstudiante();
    	});

    	moduloAlumnos.add(btnAlumnos);

    	JLabel lblAlumnos = new JLabel("<html><div style='text-align: center;'>Modulo de<br>alumnos");
    	lblAlumnos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblAlumnos.setForeground(new Color(255, 255, 255));
    	lblAlumnos.setBackground(azul2);
    	lblAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
    	lblAlumnos.setMaximumSize(new Dimension(80, 70));
    	moduloAlumnos.add(lblAlumnos);
    	options.add(moduloAlumnos);

    	JPanel moduloMaestros = new JPanel();
    	moduloMaestros.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    	moduloMaestros.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloMaestros.setPreferredSize(new Dimension(130, 140));
    	moduloMaestros.setMaximumSize(new Dimension(130, 130));
    	moduloMaestros.setBackground(azul2);

    	ImageIcon iconDocentnes = new ImageIcon(this.getClass().getResource("/imagenes/docentes (1).png"));
    	moduloMaestros.setLayout(new BoxLayout(moduloMaestros, BoxLayout.Y_AXIS));

    	JButton btnMaestros = new JButton(iconDocentnes);
    	btnMaestros.setBorder(null);
    	btnMaestros.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnMaestros.setBackground(azul2);
    	btnMaestros.addActionListener(e -> {
    	    ModuloDocenteController mdc = new ModuloDocenteController();
    	    modulo.dispose();
    	    mdc.moduloDocente();
    	});
    	moduloMaestros.add(btnMaestros);

    	JLabel lblMaestros = new JLabel("<html><div style='text-align: center;'>Modulo de<br>maestros");
    	lblMaestros.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblMaestros.setForeground(new Color(255, 255, 255));
    	lblMaestros.setBackground(azul2);
    	lblMaestros.setHorizontalAlignment(SwingConstants.CENTER);
    	lblMaestros.setMaximumSize(new Dimension(80, 70));
    	moduloMaestros.add(lblMaestros);
    	options.add(moduloMaestros);

    	JPanel moduloGrupo = new JPanel();
    	moduloGrupo.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    	moduloGrupo.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloGrupo.setPreferredSize(new Dimension(130, 120));
    	moduloGrupo.setMaximumSize(new Dimension(130, 130));
    	moduloGrupo.setBackground(azulBorde);

    	ImageIcon iconGrupo = new ImageIcon(this.getClass().getResource("/imagenes/grupos (1).png"));
    	moduloGrupo.setLayout(new BoxLayout(moduloGrupo, BoxLayout.Y_AXIS));

    	JButton btnGrupos = new JButton(iconGrupo);
    	btnGrupos.setBorder(null);
    	btnGrupos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnGrupos.setBackground(azulBorde);
    	btnGrupos.addActionListener(e -> {
    	    ModuloGrupoController mgc = new ModuloGrupoController();
    	    modulo.dispose();
    	    mgc.moduloGrupo();
    	});
    	moduloGrupo.add(btnGrupos);

    	JLabel lblGrupos = new JLabel("<html><div style='text-align: center;'>Modulo de<br>grupos");
    	lblGrupos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblGrupos.setForeground(new Color(255, 255, 255));
    	lblGrupos.setBackground(azul2);
    	lblGrupos.setHorizontalAlignment(SwingConstants.CENTER);
    	lblGrupos.setMaximumSize(new Dimension(80, 70));
    	moduloGrupo.add(lblGrupos);
    	options.add(moduloGrupo);

    	JPanel moduloAsignatura = new JPanel();
    	moduloAsignatura.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    	moduloAsignatura.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloAsignatura.setPreferredSize(new Dimension(150, 150));
    	moduloAsignatura.setMaximumSize(new Dimension(150, 150));
    	moduloAsignatura.setBackground(azul2);

    	ImageIcon iconAsignatura = new ImageIcon(this.getClass().getResource("/imagenes/asignaturas (1).png"));
    	moduloAsignatura.setLayout(new BoxLayout(moduloAsignatura, BoxLayout.Y_AXIS));

    	JButton btnAsignatura = new JButton(iconAsignatura);
    	btnAsignatura.setBorder(null);
    	btnAsignatura.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnAsignatura.setBackground(azul2);
    	btnAsignatura.addActionListener(e -> {
    	    ModuloAsignaturaController mac = new ModuloAsignaturaController();
    	    modulo.dispose();
    	    mac.moduloAsignatura();
    	});
    	moduloAsignatura.add(btnAsignatura);

    	JLabel lblAsignatura = new JLabel("<html><div style='text-align: center;'>Modulo de<br>asignaturas");
    	lblAsignatura.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblAsignatura.setForeground(new Color(255, 255, 255));
    	lblAsignatura.setBackground(azul2);
    	lblAsignatura.setHorizontalAlignment(SwingConstants.CENTER);
    	lblAsignatura.setMaximumSize(new Dimension(80, 70));
    	moduloAsignatura.add(lblAsignatura);
    	options.add(moduloAsignatura);

    	//Contenido
    	JPanel contenido = new JPanel();
        contentPane.add(contenido, BorderLayout.CENTER);
        contenido.setBackground(Color.WHITE);
        contenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 15));
        contenido.setLayout(new BorderLayout(70,20));
        
        JLabel lblTitulo = new JLabel("Creación de grupo");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contenido.add(lblTitulo,BorderLayout.NORTH);
        
        JPanel Formulario = new JPanel();
        Formulario.setBorder(BorderFactory.createLineBorder(Color.black,4));
        Formulario.setBackground(Color.white);
        contenido.add(Formulario, BorderLayout.CENTER);
        
        GridBagLayout gbl_Formulario = new GridBagLayout();
        Formulario.setLayout(gbl_Formulario);
        
        JLabel lblDocente = new JLabel("Docente");
        lblDocente.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JLabel lblAsignaturas = new JLabel("Asignatura");
        lblAsignaturas.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JLabel lblIdentificador = new JLabel("Identificador");
        lblIdentificador.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JLabel lblListaAlumnos = new JLabel("Lista de alumnos");
        lblListaAlumnos.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JTextField txtDocente = new JTextField();
        txtDocente.setPreferredSize(new Dimension(200, 25));
        txtDocente.setBorder(BorderFactory.createLineBorder(borde,4));
        txtDocente.setColumns(10);
        
        JTextField txtAsignatura = new JTextField();
        txtAsignatura.setBorder(BorderFactory.createLineBorder(borde,4));
        txtAsignatura.setPreferredSize(new Dimension(200, 25));
        txtAsignatura.setColumns(10);
        
        JTextField txtIdentificador = new JTextField();
        txtIdentificador.setEditable(false);
        txtIdentificador.setBorder(BorderFactory.createLineBorder(borde,4));
        txtIdentificador.setText("01");
        txtIdentificador.setPreferredSize(new Dimension(200, 25));
        txtIdentificador.setColumns(10);
        
        JTextField txtNombre = new JTextField();
        txtNombre.setText("6A");
        txtNombre.setBorder(BorderFactory.createLineBorder(borde,4));
        txtNombre.setPreferredSize(new Dimension(200, 25));
        txtNombre.setColumns(10);
        
        JButton btnBuscarDocente = new JButton("🔍");
        btnBuscarDocente.setPreferredSize(new Dimension(30, 25));
        btnBuscarDocente.setBackground(azul1);
        btnBuscarDocente.setBorder(BorderFactory.createLineBorder(azulBorde,5));
        btnBuscarDocente.setFont(new Font("Arial", Font.PLAIN, 10));
        
        JButton btnBuscarAsignatura = new JButton("🔍");
        btnBuscarAsignatura.setPreferredSize(new Dimension(30, 25));
        btnBuscarAsignatura.setBackground(azul1);
        btnBuscarAsignatura.setBorder(BorderFactory.createLineBorder(azulBorde,5));
        btnBuscarAsignatura.setFont(new Font("Arial", Font.PLAIN, 10));
        
        inicializarListaEstudiantes();
        
        DefaultListModel<Estudiante> modelo = new DefaultListModel<>();
        
        modelo.clear();
        for(Estudiante e : estudiantesAñadidos) {
        	modelo.addElement(e);
        	System.out.println("estufiante"+e.getNombres());
        
        }
        JList<Estudiante> listaAñadidos = new JList<>(modelo);
        listaAñadidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        listaAñadidos.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, 
                    int index, boolean isSelected, boolean cellHasFocus) {
                
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                
                if (value instanceof Estudiante) {
                    Estudiante estudiante = (Estudiante) value;
                    setText(estudiante.getNombres());
                }
                
                setHorizontalAlignment(SwingConstants.CENTER);
                return this;
            }
        });

        listaAñadidos.setBorder(BorderFactory.createLineBorder(borde,4));
        JScrollPane scrollLista = new JScrollPane(listaAñadidos);
        
        JButton btnAnadir = new JButton("⊕ Añadir");
        btnAnadir.setBackground(azul2);
        btnAnadir.setForeground(Color.WHITE);
        btnAnadir.setBackground(azul1);
        btnAnadir.setBorder(BorderFactory.createLineBorder(azulBorde,5));
        btnAnadir.setPreferredSize(new Dimension(100, 30));
        btnAnadir.setMaximumSize(new Dimension(100, 40));
        
        JButton btnEliminar = new JButton("⊖ Eliminar");
        btnEliminar.setBackground(azulC);
        btnEliminar.setBorder(BorderFactory.createLineBorder(azul2,5));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setMaximumSize(new Dimension(100, 40));
        
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.white);
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.add(Box.createVerticalStrut(50));
        panelBotones.add(btnAnadir);
        panelBotones.add(Box.createVerticalStrut(50));
        panelBotones.add(btnEliminar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(azulC);
        btnCancelar.setBorder(BorderFactory.createLineBorder(azul2,5));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setPreferredSize(new Dimension(100, 35));
        
        JButton btnCrear = new JButton("Crear");
        btnCrear.setBackground(azul1);
        btnCrear.setBorder(BorderFactory.createLineBorder(azulBorde,5));
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setPreferredSize(new Dimension(100, 35));
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.anchor = GridBagConstraints.WEST; 
        gbc.insets = new Insets(20, 50, 5, 5);
        Formulario.add(lblDocente, gbc);
        
        gbc.gridx = 1; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.weightx = 1.0;
        gbc.insets = new Insets(20, 5, 5, 5);
        Formulario.add(txtDocente, gbc);
        
        gbc.gridx = 2; 
        gbc.fill = GridBagConstraints.NONE; 
        gbc.weightx = 0.0;
        gbc.insets = new Insets(20, 5, 5, 20);
        Formulario.add(btnBuscarDocente, gbc);
        
        gbc.gridx = 3; 
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 20, 5, 5);
        Formulario.add(lblAsignaturas, gbc);
        
        gbc.gridx = 4; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.weightx = 1.0;
        gbc.insets = new Insets(20, 0, 5, 5);
        Formulario.add(txtAsignatura, gbc);
        
        gbc.gridx = 5; 
        gbc.fill = GridBagConstraints.NONE; 
        gbc.weightx = 0.0;
        gbc.insets = new Insets(20, 0, 5, 20);
        Formulario.add(btnBuscarAsignatura, gbc);
        
        gbc.gridx = 0; 
        gbc.gridy = 1; 
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 20, 5, 5);
        Formulario.add(lblIdentificador, gbc);
        
        gbc.gridx = 1; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.weightx = 1.0;
        gbc.insets = new Insets(20, 0, 5, 5);
        Formulario.add(txtIdentificador, gbc);
        
        gbc.gridx = 3; 
        gbc.fill = GridBagConstraints.NONE; 
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 20, 5, 5);
        Formulario.add(lblNombre, gbc);
        
        gbc.gridx = 4; 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 0, 5, 5);
        Formulario.add(txtNombre, gbc);
        
        gbc.gridx = 2; 
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE; 
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 20, 5, 5);
        Formulario.add(lblListaAlumnos, gbc);
        
        gbc.gridx = 0; 
        gbc.gridy = 3; 
        gbc.gridwidth = 5; 
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 40, 20, 40);
        Formulario.add(scrollLista, gbc);
        
        gbc.gridx = 6;
        gbc.gridwidth = 1; 
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(0, 5, 20, 50);
        Formulario.add(panelBotones, gbc);
        
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panelInferior.add(btnCancelar);
        panelInferior.add(btnCrear);
        contenido.add(panelInferior, BorderLayout.SOUTH);
        
        btnBuscarDocente.addActionListener(e->{
        	ModuloGrupoController mgc = new ModuloGrupoController();
        	mgc.addDocente();
        	modulo.dispose();
        });
        
        btnBuscarAsignatura.addActionListener(e -> {
        	ModuloGrupoController mgc = new ModuloGrupoController();
        	mgc.addAsignatura();
        	modulo.dispose();
        });
        
        btnAnadir.addActionListener(e -> {
            ModuloGrupoController mgc = new ModuloGrupoController();
            modulo.dispose();
            mgc.AddEstudiante();
        });
        
        btnEliminar.addActionListener(e -> {
            if (listaAñadidos.getSelectedIndex() != -1) {
                JOptionPane.showMessageDialog(modulo, "Función para eliminar alumno seleccionado");
            } else {
                JOptionPane.showMessageDialog(modulo, "Seleccione un alumno para eliminar");
            }
        });
        
        btnCancelar.addActionListener(e -> {
            int n = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro que desea cancelar? Se perderán todos los cambios.",
                "Cancelar creación",
                JOptionPane.YES_NO_OPTION);
            
            if(n == 0) {
                modulo.dispose();
            }
        });
        
        btnCrear.addActionListener(e -> {
           JOptionPane.showMessageDialog(modulo, "Función para crear grupo");
        });
        

        contenido.revalidate();
        contenido.repaint();
	}
   
	public void modificar() {
    	JFrame modulo = new JFrame();
    	modulo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	modulo.setBounds(100, 100, 982, 647);
    	JPanel contentPane = new JPanel();
    	modulo.setLocationRelativeTo(null);
    	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    	modulo.setVisible(true);
    	modulo.setContentPane(contentPane);
    	contentPane.setLayout(new BorderLayout(0, 0));

    	JPanel header = new JPanel();
    	contentPane.add(header, BorderLayout.NORTH);
    	header.setBackground(azul2);
    	header.setPreferredSize(new Dimension(2147483647, 90));
    	header.setLayout(new BoxLayout(header, BoxLayout.LINE_AXIS));
    	header.setMaximumSize(new Dimension(2147483647, 40));

    	header.add(Box.createRigidArea(new Dimension(10, 0)));
    	ImageIcon logo = new ImageIcon(this.getClass().getResource("/imagenes/uabcs (1).png"));

    	JLabel logoUabcs = new JLabel(logo);
    	logoUabcs.setBackground(azul2);
    	logoUabcs.setBorder(null);
    	logoUabcs.setPreferredSize(new Dimension(100, 100));
    	logoUabcs.setMaximumSize(new Dimension(60, 100));
    	header.add(logoUabcs);

    	header.add(Box.createRigidArea(new Dimension(50, 0)));

    	JLabel lblInicio = new JLabel("Modulo grupo");
    	lblInicio.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblInicio.setBorder(null);
    	lblInicio.setFont(new Font("Almarai-Bold", Font.PLAIN, 50));
    	lblInicio.setForeground(Color.white);
    	lblInicio.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
    	lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
    	lblInicio.setBackground(azul2);
    	header.add(lblInicio);

    	ImageIcon iconCerrarSesion = new ImageIcon(this.getClass().getResource("/imagenes/cerrarsesion (1).png"));

    	JButton btnCerrarSesion = new JButton(iconCerrarSesion);
    	btnCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnCerrarSesion.setBackground(azul2);
    	btnCerrarSesion.setBorder(null);
    	btnCerrarSesion.setPreferredSize(new Dimension(120, 120));
    	btnCerrarSesion.addActionListener(e -> {
    	    int n = JOptionPane.showConfirmDialog(
    	            null,
    	            "Estas seguro que quieres cerrar sesión?",
    	            "Cerrar sesión",
    	            JOptionPane.YES_NO_OPTION);

    	    if (n == 0) {
    	        Controller c = new Controller();
    	        modulo.dispose();
    	        c.despliegue();
    	    } else if (n == 1) {
    	        JOptionPane.showMessageDialog(null, "GOODBYE");
    	    }
    	});
    	header.add(btnCerrarSesion);

    	header.add(Box.createRigidArea(new Dimension(10, 0)));

    	JPanel options = new JPanel();
    	contentPane.add(options, BorderLayout.WEST);
    	options.setPreferredSize(new Dimension(120, 2147483647));
    	options.setMaximumSize(new Dimension(120, 2147483647));
    	options.setLayout(new BoxLayout(options, BoxLayout.PAGE_AXIS));

    	JPanel moduloAlumnos = new JPanel();
    	moduloAlumnos.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloAlumnos.setPreferredSize(new Dimension(130, 120));
    	moduloAlumnos.setMaximumSize(new Dimension(130, 130));
    	moduloAlumnos.setBackground(azul2);

    	ImageIcon iconAlumnos = new ImageIcon(this.getClass().getResource("/imagenes/alumnos (1).png"));
    	moduloAlumnos.setLayout(new BoxLayout(moduloAlumnos, BoxLayout.Y_AXIS));

    	JButton btnAlumnos = new JButton(iconAlumnos);
    	btnAlumnos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnAlumnos.setBackground(azul2);
    	btnAlumnos.setBorder(null);
    	btnAlumnos.addActionListener(e -> {
    	    ModuloEstudianteController mac = new ModuloEstudianteController();
    	    modulo.dispose();
    	    mac.ModuloEstudiante();
    	});

    	moduloAlumnos.add(btnAlumnos);

    	JLabel lblAlumnos = new JLabel("<html><div style='text-align: center;'>Modulo de<br>alumnos");
    	lblAlumnos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblAlumnos.setForeground(new Color(255, 255, 255));
    	lblAlumnos.setBackground(azul2);
    	lblAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
    	lblAlumnos.setMaximumSize(new Dimension(80, 70));
    	moduloAlumnos.add(lblAlumnos);
    	options.add(moduloAlumnos);

    	JPanel moduloMaestros = new JPanel();
    	moduloMaestros.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    	moduloMaestros.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloMaestros.setPreferredSize(new Dimension(130, 140));
    	moduloMaestros.setMaximumSize(new Dimension(130, 130));
    	moduloMaestros.setBackground(azul2);

    	ImageIcon iconDocentnes = new ImageIcon(this.getClass().getResource("/imagenes/docentes (1).png"));
    	moduloMaestros.setLayout(new BoxLayout(moduloMaestros, BoxLayout.Y_AXIS));

    	JButton btnMaestros = new JButton(iconDocentnes);
    	btnMaestros.setBorder(null);
    	btnMaestros.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnMaestros.setBackground(azul2);
    	btnMaestros.addActionListener(e -> {
    	    ModuloDocenteController mdc = new ModuloDocenteController();
    	    modulo.dispose();
    	    mdc.moduloDocente();
    	});
    	moduloMaestros.add(btnMaestros);

    	JLabel lblMaestros = new JLabel("<html><div style='text-align: center;'>Modulo de<br>maestros");
    	lblMaestros.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblMaestros.setForeground(new Color(255, 255, 255));
    	lblMaestros.setBackground(azul2);
    	lblMaestros.setHorizontalAlignment(SwingConstants.CENTER);
    	lblMaestros.setMaximumSize(new Dimension(80, 70));
    	moduloMaestros.add(lblMaestros);
    	options.add(moduloMaestros);

    	JPanel moduloGrupo = new JPanel();
    	moduloGrupo.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    	moduloGrupo.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloGrupo.setPreferredSize(new Dimension(130, 120));
    	moduloGrupo.setMaximumSize(new Dimension(130, 130));
    	moduloGrupo.setBackground(azulBorde);

    	ImageIcon iconGrupo = new ImageIcon(this.getClass().getResource("/imagenes/grupos (1).png"));
    	moduloGrupo.setLayout(new BoxLayout(moduloGrupo, BoxLayout.Y_AXIS));

    	JButton btnGrupos = new JButton(iconGrupo);
    	btnGrupos.setBorder(null);
    	btnGrupos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnGrupos.setBackground(azulBorde);
    	btnGrupos.addActionListener(e -> {
    	    ModuloGrupoController mgc = new ModuloGrupoController();
    	    modulo.dispose();
    	    mgc.moduloGrupo();
    	});
    	moduloGrupo.add(btnGrupos);

    	JLabel lblGrupos = new JLabel("<html><div style='text-align: center;'>Modulo de<br>grupos");
    	lblGrupos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblGrupos.setForeground(new Color(255, 255, 255));
    	lblGrupos.setBackground(azul2);
    	lblGrupos.setHorizontalAlignment(SwingConstants.CENTER);
    	lblGrupos.setMaximumSize(new Dimension(80, 70));
    	moduloGrupo.add(lblGrupos);
    	options.add(moduloGrupo);

    	JPanel moduloAsignatura = new JPanel();
    	moduloAsignatura.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    	moduloAsignatura.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloAsignatura.setPreferredSize(new Dimension(150, 150));
    	moduloAsignatura.setMaximumSize(new Dimension(150, 150));
    	moduloAsignatura.setBackground(azul2);

    	ImageIcon iconAsignatura = new ImageIcon(this.getClass().getResource("/imagenes/asignaturas (1).png"));
    	moduloAsignatura.setLayout(new BoxLayout(moduloAsignatura, BoxLayout.Y_AXIS));

    	JButton btnAsignatura = new JButton(iconAsignatura);
    	btnAsignatura.setBorder(null);
    	btnAsignatura.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnAsignatura.setBackground(azul2);
    	btnAsignatura.addActionListener(e -> {
    	    ModuloAsignaturaController mac = new ModuloAsignaturaController();
    	    modulo.dispose();
    	    mac.moduloAsignatura();
    	});
    	moduloAsignatura.add(btnAsignatura);

    	JLabel lblAsignatura = new JLabel("<html><div style='text-align: center;'>Modulo de<br>asignaturas");
    	lblAsignatura.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblAsignatura.setForeground(new Color(255, 255, 255));
    	lblAsignatura.setBackground(azul2);
    	lblAsignatura.setHorizontalAlignment(SwingConstants.CENTER);
    	lblAsignatura.setMaximumSize(new Dimension(80, 70));
    	moduloAsignatura.add(lblAsignatura);
    	options.add(moduloAsignatura);

    	JPanel contenido = new JPanel();
    	contentPane.add(contenido, BorderLayout.CENTER);
    	contenido.setBackground(Color.white);
    	contenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 0));
    	contenido.setLayout(new BoxLayout(contenido, BoxLayout.PAGE_AXIS));
    	
    	JLabel temporal = new JLabel("MODIFICAR GRUPO");
    	contenido.add(temporal);
    }
	
	
    
    public void detalles() {
    	Color borde = new Color(206, 207, 202);
    	Color azul2 = new Color(52, 134, 199);
    	Color azul1 = new Color(54, 146, 218);
    	Color azulBorde = new Color(101, 166, 217);
    	JFrame modulo = new JFrame();
    	modulo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	modulo.setBounds(100, 100, 982, 647);
    	JPanel contentPane = new JPanel();
    	modulo.setLocationRelativeTo(null);
    	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    	modulo.setVisible(true);
    	modulo.setContentPane(contentPane);
    	contentPane.setLayout(new BorderLayout(0, 0));

    	JPanel header = new JPanel();
    	contentPane.add(header, BorderLayout.NORTH);
    	header.setBackground(azul2);
    	header.setPreferredSize(new Dimension(2147483647, 90));
    	header.setLayout(new BoxLayout(header, BoxLayout.LINE_AXIS));
    	header.setMaximumSize(new Dimension(2147483647, 40));

    	header.add(Box.createRigidArea(new Dimension(10, 0)));
    	ImageIcon logo = new ImageIcon(this.getClass().getResource("/imagenes/uabcs (1).png"));

    	JLabel logoUabcs = new JLabel(logo);
    	logoUabcs.setBackground(azul2);
    	logoUabcs.setBorder(null);
    	logoUabcs.setPreferredSize(new Dimension(100, 100));
    	logoUabcs.setMaximumSize(new Dimension(60, 100));
    	header.add(logoUabcs);

    	header.add(Box.createRigidArea(new Dimension(50, 0)));

    	JLabel lblInicio = new JLabel("Modulo grupo");
    	lblInicio.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblInicio.setBorder(null);
    	lblInicio.setFont(new Font("Almarai-Bold", Font.PLAIN, 50));
    	lblInicio.setForeground(Color.white);
    	lblInicio.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
    	lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
    	lblInicio.setBackground(azul2);
    	header.add(lblInicio);

    	ImageIcon iconCerrarSesion = new ImageIcon(this.getClass().getResource("/imagenes/cerrarsesion (1).png"));

    	JButton btnCerrarSesion = new JButton(iconCerrarSesion);
    	btnCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnCerrarSesion.setBackground(azul2);
    	btnCerrarSesion.setBorder(null);
    	btnCerrarSesion.setPreferredSize(new Dimension(120, 120));
    	btnCerrarSesion.addActionListener(e -> {
    	    int n = JOptionPane.showConfirmDialog(
    	            null,
    	            "Estas seguro que quieres cerrar sesión?",
    	            "Cerrar sesión",
    	            JOptionPane.YES_NO_OPTION);

    	    if (n == 0) {
    	        Controller c = new Controller();
    	        modulo.dispose();
    	        c.despliegue();
    	    } else if (n == 1) {
    	        JOptionPane.showMessageDialog(null, "GOODBYE");
    	    }
    	});
    	header.add(btnCerrarSesion);

    	header.add(Box.createRigidArea(new Dimension(10, 0)));

    	JPanel options = new JPanel();
    	contentPane.add(options, BorderLayout.WEST);
    	options.setPreferredSize(new Dimension(120, 2147483647));
    	options.setMaximumSize(new Dimension(120, 2147483647));
    	options.setLayout(new BoxLayout(options, BoxLayout.PAGE_AXIS));

    	JPanel moduloAlumnos = new JPanel();
    	moduloAlumnos.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloAlumnos.setPreferredSize(new Dimension(130, 120));
    	moduloAlumnos.setMaximumSize(new Dimension(130, 130));
    	moduloAlumnos.setBackground(azul2);

    	ImageIcon iconAlumnos = new ImageIcon(this.getClass().getResource("/imagenes/alumnos (1).png"));
    	moduloAlumnos.setLayout(new BoxLayout(moduloAlumnos, BoxLayout.Y_AXIS));

    	JButton btnAlumnos = new JButton(iconAlumnos);
    	btnAlumnos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnAlumnos.setBackground(azul2);
    	btnAlumnos.setBorder(null);
    	btnAlumnos.addActionListener(e -> {
    	    ModuloEstudianteController mac = new ModuloEstudianteController();
    	    modulo.dispose();
    	    mac.ModuloEstudiante();
    	});

    	moduloAlumnos.add(btnAlumnos);

    	JLabel lblAlumnos = new JLabel("<html><div style='text-align: center;'>Modulo de<br>alumnos");
    	lblAlumnos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblAlumnos.setForeground(new Color(255, 255, 255));
    	lblAlumnos.setBackground(azul2);
    	lblAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
    	lblAlumnos.setMaximumSize(new Dimension(80, 70));
    	moduloAlumnos.add(lblAlumnos);
    	options.add(moduloAlumnos);

    	JPanel moduloMaestros = new JPanel();
    	moduloMaestros.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    	moduloMaestros.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloMaestros.setPreferredSize(new Dimension(130, 140));
    	moduloMaestros.setMaximumSize(new Dimension(130, 130));
    	moduloMaestros.setBackground(azul2);

    	ImageIcon iconDocentnes = new ImageIcon(this.getClass().getResource("/imagenes/docentes (1).png"));
    	moduloMaestros.setLayout(new BoxLayout(moduloMaestros, BoxLayout.Y_AXIS));

    	JButton btnMaestros = new JButton(iconDocentnes);
    	btnMaestros.setBorder(null);
    	btnMaestros.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnMaestros.setBackground(azul2);
    	btnMaestros.addActionListener(e -> {
    	    ModuloDocenteController mdc = new ModuloDocenteController();
    	    modulo.dispose();
    	    mdc.moduloDocente();
    	});
    	moduloMaestros.add(btnMaestros);

    	JLabel lblMaestros = new JLabel("<html><div style='text-align: center;'>Modulo de<br>maestros");
    	lblMaestros.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblMaestros.setForeground(new Color(255, 255, 255));
    	lblMaestros.setBackground(azul2);
    	lblMaestros.setHorizontalAlignment(SwingConstants.CENTER);
    	lblMaestros.setMaximumSize(new Dimension(80, 70));
    	moduloMaestros.add(lblMaestros);
    	options.add(moduloMaestros);

    	JPanel moduloGrupo = new JPanel();
    	moduloGrupo.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    	moduloGrupo.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloGrupo.setPreferredSize(new Dimension(130, 120));
    	moduloGrupo.setMaximumSize(new Dimension(130, 130));
    	moduloGrupo.setBackground(azulBorde);

    	ImageIcon iconGrupo = new ImageIcon(this.getClass().getResource("/imagenes/grupos (1).png"));
    	moduloGrupo.setLayout(new BoxLayout(moduloGrupo, BoxLayout.Y_AXIS));

    	JButton btnGrupos = new JButton(iconGrupo);
    	btnGrupos.setBorder(null);
    	btnGrupos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnGrupos.setBackground(azulBorde);
    	btnGrupos.addActionListener(e -> {
    	    ModuloGrupoController mgc = new ModuloGrupoController();
    	    modulo.dispose();
    	    mgc.moduloGrupo();
    	});
    	moduloGrupo.add(btnGrupos);

    	JLabel lblGrupos = new JLabel("<html><div style='text-align: center;'>Modulo de<br>grupos");
    	lblGrupos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblGrupos.setForeground(new Color(255, 255, 255));
    	lblGrupos.setBackground(azul2);
    	lblGrupos.setHorizontalAlignment(SwingConstants.CENTER);
    	lblGrupos.setMaximumSize(new Dimension(80, 70));
    	moduloGrupo.add(lblGrupos);
    	options.add(moduloGrupo);

    	JPanel moduloAsignatura = new JPanel();
    	moduloAsignatura.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    	moduloAsignatura.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloAsignatura.setPreferredSize(new Dimension(150, 150));
    	moduloAsignatura.setMaximumSize(new Dimension(150, 150));
    	moduloAsignatura.setBackground(azul2);

    	ImageIcon iconAsignatura = new ImageIcon(this.getClass().getResource("/imagenes/asignaturas (1).png"));
    	moduloAsignatura.setLayout(new BoxLayout(moduloAsignatura, BoxLayout.Y_AXIS));

    	JButton btnAsignatura = new JButton(iconAsignatura);
    	btnAsignatura.setBorder(null);
    	btnAsignatura.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnAsignatura.setBackground(azul2);
    	btnAsignatura.addActionListener(e -> {
    	    ModuloAsignaturaController mac = new ModuloAsignaturaController();
    	    modulo.dispose();
    	    mac.moduloAsignatura();
    	});
    	moduloAsignatura.add(btnAsignatura);

    	JLabel lblAsignatura = new JLabel("<html><div style='text-align: center;'>Modulo de<br>asignaturas");
    	lblAsignatura.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblAsignatura.setForeground(new Color(255, 255, 255));
    	lblAsignatura.setBackground(azul2);
    	lblAsignatura.setHorizontalAlignment(SwingConstants.CENTER);
    	lblAsignatura.setMaximumSize(new Dimension(80, 70));
    	moduloAsignatura.add(lblAsignatura);
    	options.add(moduloAsignatura);

    	JPanel contenido = new JPanel();
    	contentPane.add(contenido, BorderLayout.CENTER);
    	contenido.setBackground(Color.white);
    	contenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 0));
    	contenido.setLayout(new BoxLayout(contenido, BoxLayout.PAGE_AXIS));
    	
    	JLabel temporal = new JLabel("DETALLES GRUPO");
    	contenido.add(temporal);
    }

    //metodos para agregar y mostrar estudiantes asignaturas y docentes
    
    public void addAlumno() {
		JFrame modulo = new JFrame();
    	modulo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	modulo.setBounds(100, 100, 982, 647);
    	JPanel contentPane = new JPanel();
    	modulo.setLocationRelativeTo(null);
    	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    	modulo.setVisible(true);
    	modulo.setContentPane(contentPane);
    	contentPane.setLayout(new BorderLayout(0, 0));

    	JPanel header = new JPanel();
    	contentPane.add(header, BorderLayout.NORTH);
    	header.setBackground(azul2);
    	header.setPreferredSize(new Dimension(2147483647, 90));
    	header.setLayout(new BoxLayout(header, BoxLayout.LINE_AXIS));
    	header.setMaximumSize(new Dimension(2147483647, 40));

    	header.add(Box.createRigidArea(new Dimension(10, 0)));
    	ImageIcon logo = new ImageIcon(this.getClass().getResource("/imagenes/uabcs (1).png"));

    	JLabel logoUabcs = new JLabel(logo);
    	logoUabcs.setBackground(azul2);
    	logoUabcs.setBorder(null);
    	logoUabcs.setPreferredSize(new Dimension(100, 100));
    	logoUabcs.setMaximumSize(new Dimension(60, 100));
    	header.add(logoUabcs);

    	header.add(Box.createRigidArea(new Dimension(50, 0)));

    	JLabel lblInicio = new JLabel("Modulo grupo");
    	lblInicio.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblInicio.setBorder(null);
    	lblInicio.setFont(new Font("Almarai-Bold", Font.PLAIN, 50));
    	lblInicio.setForeground(Color.white);
    	lblInicio.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
    	lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
    	lblInicio.setBackground(azul2);
    	header.add(lblInicio);

    	ImageIcon iconCerrarSesion = new ImageIcon(this.getClass().getResource("/imagenes/cerrarsesion (1).png"));

    	JButton btnCerrarSesion = new JButton(iconCerrarSesion);
    	btnCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnCerrarSesion.setBackground(azul2);
    	btnCerrarSesion.setBorder(null);
    	btnCerrarSesion.setPreferredSize(new Dimension(120, 120));
    	btnCerrarSesion.addActionListener(e -> {
    	    int n = JOptionPane.showConfirmDialog(
    	            null,
    	            "Estas seguro que quieres cerrar sesión?",
    	            "Cerrar sesión",
    	            JOptionPane.YES_NO_OPTION);

    	    if (n == 0) {
    	        Controller c = new Controller();
    	        modulo.dispose();
    	        c.despliegue();
    	    } else if (n == 1) {
    	        JOptionPane.showMessageDialog(null, "GOODBYE");
    	    }
    	});
    	header.add(btnCerrarSesion);

    	header.add(Box.createRigidArea(new Dimension(10, 0)));

    	JPanel options = new JPanel();
    	contentPane.add(options, BorderLayout.WEST);
    	options.setPreferredSize(new Dimension(120, 2147483647));
    	options.setMaximumSize(new Dimension(120, 2147483647));
    	options.setLayout(new BoxLayout(options, BoxLayout.PAGE_AXIS));

    	JPanel moduloAlumnos = new JPanel();
    	moduloAlumnos.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloAlumnos.setPreferredSize(new Dimension(130, 120));
    	moduloAlumnos.setMaximumSize(new Dimension(130, 130));
    	moduloAlumnos.setBackground(azul2);

    	ImageIcon iconAlumnos = new ImageIcon(this.getClass().getResource("/imagenes/alumnos (1).png"));
    	moduloAlumnos.setLayout(new BoxLayout(moduloAlumnos, BoxLayout.Y_AXIS));

    	JButton btnAlumnos = new JButton(iconAlumnos);
    	btnAlumnos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnAlumnos.setBackground(azul2);
    	btnAlumnos.setBorder(null);
    	btnAlumnos.addActionListener(e -> {
    	    ModuloEstudianteController mac = new ModuloEstudianteController();
    	    modulo.dispose();
    	    mac.ModuloEstudiante();
    	});

    	moduloAlumnos.add(btnAlumnos);

    	JLabel lblAlumnos = new JLabel("<html><div style='text-align: center;'>Modulo de<br>alumnos");
    	lblAlumnos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblAlumnos.setForeground(new Color(255, 255, 255));
    	lblAlumnos.setBackground(azul2);
    	lblAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
    	lblAlumnos.setMaximumSize(new Dimension(80, 70));
    	moduloAlumnos.add(lblAlumnos);
    	options.add(moduloAlumnos);

    	JPanel moduloMaestros = new JPanel();
    	moduloMaestros.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    	moduloMaestros.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloMaestros.setPreferredSize(new Dimension(130, 140));
    	moduloMaestros.setMaximumSize(new Dimension(130, 130));
    	moduloMaestros.setBackground(azul2);

    	ImageIcon iconDocentnes = new ImageIcon(this.getClass().getResource("/imagenes/docentes (1).png"));
    	moduloMaestros.setLayout(new BoxLayout(moduloMaestros, BoxLayout.Y_AXIS));

    	JButton btnMaestros = new JButton(iconDocentnes);
    	btnMaestros.setBorder(null);
    	btnMaestros.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnMaestros.setBackground(azul2);
    	btnMaestros.addActionListener(e -> {
    	    ModuloDocenteController mdc = new ModuloDocenteController();
    	    modulo.dispose();
    	    mdc.moduloDocente();
    	});
    	moduloMaestros.add(btnMaestros);

    	JLabel lblMaestros = new JLabel("<html><div style='text-align: center;'>Modulo de<br>maestros");
    	lblMaestros.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblMaestros.setForeground(new Color(255, 255, 255));
    	lblMaestros.setBackground(azul2);
    	lblMaestros.setHorizontalAlignment(SwingConstants.CENTER);
    	lblMaestros.setMaximumSize(new Dimension(80, 70));
    	moduloMaestros.add(lblMaestros);
    	options.add(moduloMaestros);

    	JPanel moduloGrupo = new JPanel();
    	moduloGrupo.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    	moduloGrupo.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloGrupo.setPreferredSize(new Dimension(130, 120));
    	moduloGrupo.setMaximumSize(new Dimension(130, 130));
    	moduloGrupo.setBackground(azulBorde);

    	ImageIcon iconGrupo = new ImageIcon(this.getClass().getResource("/imagenes/grupos (1).png"));
    	moduloGrupo.setLayout(new BoxLayout(moduloGrupo, BoxLayout.Y_AXIS));

    	JButton btnGrupos = new JButton(iconGrupo);
    	btnGrupos.setBorder(null);
    	btnGrupos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnGrupos.setBackground(azulBorde);
    	btnGrupos.addActionListener(e -> {
    	    ModuloGrupoController mgc = new ModuloGrupoController();
    	    modulo.dispose();
    	    mgc.moduloGrupo();
    	});
    	moduloGrupo.add(btnGrupos);

    	JLabel lblGrupos = new JLabel("<html><div style='text-align: center;'>Modulo de<br>grupos");
    	lblGrupos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblGrupos.setForeground(new Color(255, 255, 255));
    	lblGrupos.setBackground(azul2);
    	lblGrupos.setHorizontalAlignment(SwingConstants.CENTER);
    	lblGrupos.setMaximumSize(new Dimension(80, 70));
    	moduloGrupo.add(lblGrupos);
    	options.add(moduloGrupo);

    	JPanel moduloAsignatura = new JPanel();
    	moduloAsignatura.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    	moduloAsignatura.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloAsignatura.setPreferredSize(new Dimension(150, 150));
    	moduloAsignatura.setMaximumSize(new Dimension(150, 150));
    	moduloAsignatura.setBackground(azul2);

    	ImageIcon iconAsignatura = new ImageIcon(this.getClass().getResource("/imagenes/asignaturas (1).png"));
    	moduloAsignatura.setLayout(new BoxLayout(moduloAsignatura, BoxLayout.Y_AXIS));

    	JButton btnAsignatura = new JButton(iconAsignatura);
    	btnAsignatura.setBorder(null);
    	btnAsignatura.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnAsignatura.setBackground(azul2);
    	btnAsignatura.addActionListener(e -> {
    	    ModuloAsignaturaController mac = new ModuloAsignaturaController();
    	    modulo.dispose();
    	    mac.moduloAsignatura();
    	});
    	moduloAsignatura.add(btnAsignatura);

    	JLabel lblAsignatura = new JLabel("<html><div style='text-align: center;'>Modulo de<br>asignaturas");
    	lblAsignatura.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblAsignatura.setForeground(new Color(255, 255, 255));
    	lblAsignatura.setBackground(azul2);
    	lblAsignatura.setHorizontalAlignment(SwingConstants.CENTER);
    	lblAsignatura.setMaximumSize(new Dimension(80, 70));
    	moduloAsignatura.add(lblAsignatura);
    	options.add(moduloAsignatura);
    	
    	JPanel contenido = new JPanel();
    	contentPane.add(contenido, BorderLayout.CENTER);
    	contenido.setBackground(Color.white);
    	contenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 0));
    	contenido.setLayout(new BorderLayout(0, 10));

    	JPanel panelSuperior = new JPanel(new BorderLayout());
    	panelSuperior.setBackground(Color.white);

    	JButton btnCancelar = new JButton("Cancelar");
    	btnCancelar.setBackground(new Color(108, 117, 125));
    	btnCancelar.setForeground(Color.white);
    	btnCancelar.setPreferredSize(new Dimension(80, 30));
    	btnCancelar.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    	btnCancelar.addActionListener(e -> {
    		ModuloGrupoController mgc = new ModuloGrupoController();

    		mgc.crear();
    	    modulo.dispose();
    	});

    	JLabel lblTitulo = new JLabel("Añadir un estudiante");
    	lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
    	lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

    	panelSuperior.add(btnCancelar, BorderLayout.WEST);
    	panelSuperior.add(lblTitulo, BorderLayout.CENTER);

    	contenido.add(panelSuperior, BorderLayout.NORTH);

    	// Panel de búsqueda
    	JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    	panelBusqueda.setBackground(Color.white);

    	JTextField txtBusqueda = new JTextField(20);
    	txtBusqueda.setPreferredSize(new Dimension(200, 30));

    	ImageIcon buscar = new ImageIcon(getClass().getResource("/imagenes/busqueda.png"));
    	JButton btnBuscar = new JButton(buscar);
    	btnBuscar.setBackground(new Color(52, 144, 220));
    	btnBuscar.setForeground(Color.white);
    	btnBuscar.setPreferredSize(new Dimension(40, 30));
    	btnBuscar.setBorder(null);

    	JButton btnActualizar = new JButton("🔄");
    	btnActualizar.setBackground(new Color(52, 144, 220));
    	btnActualizar.setForeground(Color.white);
    	btnActualizar.setPreferredSize(new Dimension(40, 30));
    	btnActualizar.setBorder(null);

    	panelBusqueda.add(new JLabel("Buscar:"));
    	panelBusqueda.add(txtBusqueda);
    	panelBusqueda.add(btnBuscar);
    	panelBusqueda.add(btnActualizar);

    	contenido.add(panelBusqueda, BorderLayout.CENTER);

    	JPanel panelTabla = new JPanel(new BorderLayout());
    	panelTabla.setBackground(Color.white);

    	String[] columnas = {"Identificador", "Nombre completo", "Detalles", "Añadir"};
    	Object[][] datos = new Object[listaEstudiantes.size()][4];

    	// Carga el ícono (debe existir en esa ruta)
    	ImageIcon iconoOjo = new ImageIcon("/imagenes/añadir.png");

    	for (int i = 0; i < listaEstudiantes.size(); i++) {
    	    Estudiante est = listaEstudiantes.get(i);
    	    datos[i][0] = est.getNumeroControl();
    	    datos[i][1] = est.getNombres() + " " + est.getApellidos();
    	    datos[i][2] = "Datos completos";
    	    datos[i][3] = iconoOjo;
    	}

    	// Crear tabla
    	JTable tabla = new JTable(datos, columnas) {
    	    @Override
    	    public boolean isCellEditable(int row, int column) {
    	        return false;
    	    }

    	    @Override
    	    public Class<?> getColumnClass(int column) {
    	        return column == 3 ? Icon.class : String.class;
    	    }

    	    @Override
    	    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
    	        Component comp = super.prepareRenderer(renderer, row, column);

    	        if (row % 2 == 0) {
    	            comp.setBackground(Color.white);
    	        } else {
    	            comp.setBackground(new Color(248, 249, 250));
    	        }

    	        if (column == 2) {
    	            comp.setForeground(new Color(52, 144, 220));
    	        } else {
    	            comp.setForeground(Color.black);
    	        }

    	        return comp;
    	    }
    	};


    	tabla.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseClicked(MouseEvent e) {
    	    	inicializarListaEstudiantes();
    	        int fila = tabla.rowAtPoint(e.getPoint());
    	        int columna = tabla.columnAtPoint(e.getPoint());
    	        
    	        if (columna == 2) { 
    	            ModuloGrupoController mec = new ModuloGrupoController();
                    if (fila >= 0) {
                        Estudiante eSeleccionado = listaEstudiantes.get(fila);
	    	            modulo.dispose(); 
	    	            mec.datosGenerales(eSeleccionado);
                    }
    	        } else if (columna == 3) {
    	        	Estudiante eSeleccionado = listaEstudiantes.get(fila);
                    
                        
                    int respuesta = JOptionPane.showConfirmDialog(modulo,
                        "¿Desea añadir al estudiante " + tabla.getValueAt(fila, 1) + " al grupo?",
                        "Confirmar",
                        JOptionPane.YES_NO_OPTION);
                    
                    if (respuesta == JOptionPane.YES_OPTION) {
                        estudiantesAñadidos.add(eSeleccionado);
                        
                        JOptionPane.showMessageDialog(modulo, 
                            "Estudiante añadido correctamente al grupo");
                        
                        modulo.dispose();
                        crear();
    	            }
    	        }
    	    }
    	});

    	JScrollPane scrollPane = new JScrollPane(tabla);
    	scrollPane.setPreferredSize(new Dimension(700, 400));
    	panelTabla.add(scrollPane, BorderLayout.CENTER);

    	JPanel contenidoCentral = new JPanel(new BorderLayout(0, 10));
    	contenidoCentral.setBackground(Color.white);
    	contenidoCentral.add(panelBusqueda, BorderLayout.NORTH);
    	contenidoCentral.add(panelTabla, BorderLayout.CENTER);

    	contenido.add(contenidoCentral, BorderLayout.CENTER);

	}
    
    public void datoEstudiante(Estudiante estudiante) {
    	JFrame modulo= new JFrame();
		modulo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		modulo.setBounds(100, 100, 982, 647);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		modulo.setLocationRelativeTo(null);
		modulo.setVisible(true);

		modulo.setContentPane(contentPane);
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
		lblInicio.setFont(new Font("Almarai-Bold", Font.PLAIN, 50));
		lblInicio.setForeground(Color.white);
		lblInicio.setMaximumSize(new Dimension(Integer.MAX_VALUE,80));
		lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicio.setBackground(azul2);
		header.add(lblInicio);
		
		ImageIcon iconCerrarSesion = new ImageIcon(this.getClass().getResource("/imagenes/cerrarsesion (1).png"));
		
		JButton btnCerrarSesion = new JButton(iconCerrarSesion);
		btnCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCerrarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCerrarSesion.setBackground(azul2);
		btnCerrarSesion.setBorder(null);
		btnCerrarSesion.setPreferredSize(new Dimension(120,120));
		btnCerrarSesion.addActionListener(e->{
			int n = JOptionPane.showConfirmDialog(
		            null,
		            "Estas seguro que quieres cerrar sesión?",
		            "Cerrar sesión",
		            JOptionPane.YES_NO_OPTION);

		        if(n==0){
		            modulo.dispose();
		            Controller c = new Controller();
		            c.despliegue();
		            JOptionPane.showMessageDialog(null,"Sesión cerrada correctamente");
		        }
		        else if(n==1) {
		            
		        }
		});
		header.add(btnCerrarSesion);
		
		header.add(Box.createRigidArea(new Dimension(10,0)));
		
		JPanel options = new JPanel();
		modulo.add(options, BorderLayout.WEST);
		options.setPreferredSize(new Dimension(120, 120));
		options.setLayout(new BoxLayout(options, BoxLayout.PAGE_AXIS));
		
		JPanel moduloAlumnos = new JPanel ();
		moduloAlumnos.setAlignmentX(Component.RIGHT_ALIGNMENT);
		moduloAlumnos.setPreferredSize(new Dimension(130, 120));
		moduloAlumnos.setMaximumSize(new Dimension(130, 130));
		moduloAlumnos.setBackground(azulBorde);
		
		ImageIcon iconAlumnos = new ImageIcon (this.getClass().getResource("/imagenes/alumnos (1).png"));
		moduloAlumnos.setLayout(new BoxLayout(moduloAlumnos, BoxLayout.Y_AXIS));
		
		JButton btnAlumnos = new JButton(iconAlumnos);
		btnAlumnos.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAlumnos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlumnos.setBackground(azulBorde);
		btnAlumnos.setBorder(null);
		btnAlumnos.addActionListener(e->{
			ModuloEstudianteController mac = new ModuloEstudianteController();
			modulo.dispose();
			mac.ModuloEstudiante();
		});
		
		moduloAlumnos.add(btnAlumnos);
		
		JLabel lblAlumnos = new JLabel ("<html><div style='text-align: center;'>Modulo de<br>alumnos");
		lblAlumnos.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAlumnos.setForeground(new Color(255, 255, 255));
		lblAlumnos.setBackground(azul2);
		lblAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlumnos.setMaximumSize(new Dimension(80, 70));
		lblAlumnos.setFont(new Font("Almarai-Bold", Font.PLAIN, 15));
		moduloAlumnos.add(lblAlumnos);
		options.add(moduloAlumnos);
		
		
		
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
		btnMaestros.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMaestros.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnMaestros.setBackground(azul2);
		btnMaestros.addActionListener(e->{
			ModuloDocenteController mdc= new ModuloDocenteController();
			modulo.dispose();
			mdc.moduloDocente();
		});
		moduloMaestros.add(btnMaestros);
		
		JLabel lblMaestros = new JLabel ("<html><div style='text-align: center;'>Modulo de<br>docentes");
		lblMaestros.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblMaestros.setForeground(new Color(255, 255, 255));
		lblMaestros.setBackground(azul2);
		lblMaestros.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaestros.setMaximumSize(new Dimension(80, 70));
		lblMaestros.setFont(new Font("Almarai-Bold", Font.PLAIN, 15));
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
		btnGrupos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGrupos.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnGrupos.setBackground(azul2);
		btnGrupos.addActionListener(e->{
			ModuloGrupoController mgc= new ModuloGrupoController();
			modulo.dispose();
			mgc.moduloGrupo();
		});		moduloGrupo.add(btnGrupos);
		
		JLabel lblGrupos = new JLabel ("<html><div style='text-align: center;'>Modulo de<br>grupos");
		lblGrupos.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblGrupos.setForeground(new Color(255, 255, 255));
		lblGrupos.setBackground(azul2);
		lblGrupos.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupos.setMaximumSize(new Dimension(80, 70));
		lblGrupos.setFont(new Font("Almarai-Bold", Font.PLAIN, 15));
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
		btnAsignatura.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAsignatura.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAsignatura.setBackground(azul2);
		btnAsignatura.addActionListener(e->{
			ModuloAsignaturaController mac= new ModuloAsignaturaController();
			modulo.dispose();
			mac.moduloAsignatura();
			
		});
		moduloAsignatura.add(btnAsignatura);
		
		JLabel lblAsignatura = new JLabel ("<html><div style='text-align: center;'>Modulo de<br>asignaturas");
		lblAsignatura.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAsignatura.setForeground(new Color(255, 255, 255));
		lblAsignatura.setBackground(azul2);
		lblAsignatura.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsignatura.setMaximumSize(new Dimension(80, 70));
		lblAsignatura.setFont(new Font("Almarai-Bold", Font.PLAIN, 15));
		moduloAsignatura.add(lblAsignatura);
		options.add(moduloAsignatura);
		
		// Panel de contenido
		JPanel contenido = new JPanel();
		contentPane.add(contenido, BorderLayout.CENTER);
		contenido.setBackground(Color.white);
		contenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 0));
		System.out.println(contenido.getHeight());
		contenido.setLayout(new BorderLayout(0, 0));
		
		JPanel panelContenido = new JPanel();
		panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
		panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		panelContenido.setBackground(Color.white);
		contentPane.add(panelContenido, BorderLayout.CENTER);

		JLabel lblTitulo = new JLabel("Datos generales de alumno");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(lblTitulo);
        panelContenido.add(Box.createVerticalStrut(20));

        JPanel Formulario = new JPanel(new GridBagLayout());
        Formulario.setBackground(Color.white);
        Formulario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        GridBagConstraints organizador = new GridBagConstraints();
        organizador.insets = new Insets(8, 10, 8, 10);
        organizador.anchor = GridBagConstraints.WEST;
        organizador.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblNombres = new JLabel("Nombres");
        JTextField txtNombres = new JTextField(15);
        txtNombres.setBackground(Color.WHITE);
        txtNombres.setBorder(null);
        txtNombres.setEditable(false);

        JLabel lblApellidos = new JLabel("Apellidos");
        JTextField txtApellidos = new JTextField(15);
        txtApellidos.setBackground(Color.WHITE);
        txtApellidos.setBorder(null);
        txtApellidos.setEditable(false);
        
        JLabel lblId = new JLabel("Identificador");
        JTextField txtId = new JTextField();
        txtId.setEditable(false);
        txtId.setBackground(Color.WHITE);
        txtId.setBorder(null);
        
        JLabel lblFecha = new JLabel("Fecha de nacimiento");
        JTextField txtFecha = new JTextField();
        txtFecha.setBackground(Color.WHITE);
        txtFecha.setBorder(null);
        txtFecha.setEditable(false);
        
        JLabel lblGenero = new JLabel("Género");
        JTextField txtGenero= new JTextField();
        txtGenero.setBackground(Color.WHITE);
        txtGenero.setBorder(null);
        txtGenero.setEditable(false);
        
        JLabel lblTelefono = new JLabel("Teléfono");
        JTextField txtTelefono = new JTextField(15);
        txtTelefono.setBackground(Color.WHITE);
        txtTelefono.setBorder(null);
        txtTelefono.setEditable(false);
        
        JLabel lblGrado = new JLabel("Grado");
        JTextField txtGrado = new JTextField(15);
        txtGrado.setBackground(Color.WHITE);
        txtGrado.setBorder(null);
        txtGrado.setEditable(false);
        
        JLabel lblDomicilio = new JLabel("Domicilio");
        JTextField txtDomicilio = new JTextField(15);
        txtDomicilio.setBackground(Color.WHITE);
        txtDomicilio.setBorder(null);
        txtDomicilio.setEditable(false);
        
        JLabel lblCorreo = new JLabel("Correo electrónico");
        JTextField txtCorreo = new JTextField(15);
        txtCorreo.setBackground(Color.WHITE);
        txtCorreo.setBorder(null);
        txtCorreo.setEditable(false);
        
        JLabel lblCurp = new JLabel("CURP");
        JTextField txtCurp = new JTextField(15);
        txtCurp.setBackground(Color.WHITE);
        txtCurp.setBorder(null);
        txtCurp.setEditable(false);
        
        JPanel panelFoto = new JPanel();
        panelFoto.setLayout(new BoxLayout(panelFoto, BoxLayout.Y_AXIS));
        panelFoto.setBorder(BorderFactory.createTitledBorder("Foto"));
        panelFoto.setBackground(Color.white);

        JLabel lblFoto = new JLabel(); 
        lblFoto.setPreferredSize(new Dimension(100, 100));
        lblFoto.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnCargar = new JButton("📷 Cargar");
        btnCargar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCargar.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelFoto.add(Box.createVerticalStrut(10));
        panelFoto.add(lblFoto);
        panelFoto.add(Box.createVerticalStrut(10));
        panelFoto.add(btnCargar);

        // Carga de datos
        txtId.setText(String.valueOf(estudiante.getId()));
        txtNombres.setText(estudiante.getNombres());
        txtApellidos.setText(estudiante.getApellidos());
        txtCorreo.setText(estudiante.getCorreo());
        txtCurp.setText(estudiante.getCurp());
        txtFecha.setText(String.valueOf(estudiante.getFechaNacimiento()));
        txtGenero.setText(estudiante.getGenero());
        txtTelefono.setText(estudiante.getTelefono());
        txtGrado.setText(estudiante.getGrupo());
        txtDomicilio.setText(estudiante.getDomicilio());
       
        if (estudiante.getFoto() != null) {
            ImageIcon icon = new ImageIcon(estudiante.getFoto()); 
            Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            lblFoto.setIcon(new ImageIcon(scaledImage));
        }  
        int fila = 0;

        organizador.gridx = 0; 
        organizador.gridy = fila;
        Formulario.add(lblNombres, organizador);
        
        organizador.gridx = 1;
        Formulario.add(txtNombres, organizador);
        
        organizador.gridx = 2;
        Formulario.add(lblCorreo, organizador);
        
        organizador.gridx = 3;
        Formulario.add(txtCorreo, organizador);
        fila++;

        organizador.gridx = 0; 
        organizador.gridy = fila;
        Formulario.add(lblApellidos, organizador);
        
        organizador.gridx = 1;
        Formulario.add(txtApellidos, organizador);
        
        organizador.gridx = 2;
        Formulario.add(lblCurp, organizador);
        
        organizador.gridx = 3;
        Formulario.add(txtCurp, organizador);
        fila++;

        organizador.gridx = 0; 
        organizador.gridy = fila;
        Formulario.add(lblId, organizador);
        
        organizador.gridx = 1;
        Formulario.add(txtId, organizador);
        fila++;

        organizador.gridx = 0; 
        organizador.gridy = fila;
        Formulario.add(lblFecha, organizador);
        
        organizador.gridx = 1;
        Formulario.add(txtFecha, organizador);
        fila++;

        organizador.gridx = 0; 
        organizador.gridy = fila;
        Formulario.add(lblGenero, organizador);
        
        organizador.gridx = 1;
        Formulario.add(txtGenero, organizador);
        fila++;

        organizador.gridx = 0; 
        organizador.gridy = fila;
        Formulario.add(lblTelefono, organizador);
        
        organizador.gridx = 1;
        Formulario.add(txtTelefono, organizador);
        fila++;

        organizador.gridx = 0; 
        organizador.gridy = fila;
        Formulario.add(lblGrado, organizador);
        
        organizador.gridx = 1;
        Formulario.add(txtGrado, organizador);
        fila++;

        organizador.gridx = 0; 
        organizador.gridy = fila;
        Formulario.add(lblDomicilio, organizador);
        
        organizador.gridx = 1;
        Formulario.add(txtDomicilio, organizador);

        organizador.gridx = 3;
        organizador.gridy = 2;
        organizador.gridheight = 6;
        Formulario.add(panelFoto, organizador);

        panelContenido.add(Formulario);
        panelContenido.add(Box.createVerticalStrut(20));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCancelar = new JButton("Regresar");
        JButton btnCrear = new JButton("Descargar PDF");
        btnCancelar.setBackground(azulC);
        btnCancelar.setForeground(Color.white);
        btnCancelar.setBorder(BorderFactory.createLineBorder(azul2,5));
        btnCrear.setBackground(azul1);
        btnCrear.setForeground(Color.white);
        btnCrear.setBorder(BorderFactory.createLineBorder(azulBorde,5));
        btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCrear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(e->{
        	ModuloGrupoController mgc = new ModuloGrupoController();
        	modulo.dispose();
        	mgc.crear();
        });
        btnCrear.addActionListener(e->{
        	JFileChooser fileChooser = new JFileChooser();
        	fileChooser.setDialogTitle("Guardar PDF");

        	FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF (*.pdf)", "pdf");
        	fileChooser.setFileFilter(filter);

        	int userSelection = fileChooser.showSaveDialog(null);

        	if (userSelection == JFileChooser.APPROVE_OPTION) {
        	    File fileToSave = fileChooser.getSelectedFile();

        	    String ruta = fileToSave.getAbsolutePath();
        	    if (!ruta.toLowerCase().endsWith(".pdf")) {
        	        ruta += ".pdf";
        	    }

        	    System.out.println(ruta);
        	    mem.descargarInformacion(ruta, estudiante);
        	}
        	
        });
        panelBotones.add(btnCancelar);
        panelBotones.add(btnCrear);

        panelContenido.add(panelBotones);

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
    
    public void addDocente() {
		JFrame modulo = new JFrame();
    	modulo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	modulo.setBounds(100, 100, 982, 647);
    	JPanel contentPane = new JPanel();
    	modulo.setLocationRelativeTo(null);
    	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    	modulo.setVisible(true);
    	modulo.setContentPane(contentPane);
    	contentPane.setLayout(new BorderLayout(0, 0));

    	JPanel header = new JPanel();
    	contentPane.add(header, BorderLayout.NORTH);
    	header.setBackground(azul2);
    	header.setPreferredSize(new Dimension(2147483647, 90));
    	header.setLayout(new BoxLayout(header, BoxLayout.LINE_AXIS));
    	header.setMaximumSize(new Dimension(2147483647, 40));

    	header.add(Box.createRigidArea(new Dimension(10, 0)));
    	ImageIcon logo = new ImageIcon(this.getClass().getResource("/imagenes/uabcs (1).png"));

    	JLabel logoUabcs = new JLabel(logo);
    	logoUabcs.setBackground(azul2);
    	logoUabcs.setBorder(null);
    	logoUabcs.setPreferredSize(new Dimension(100, 100));
    	logoUabcs.setMaximumSize(new Dimension(60, 100));
    	header.add(logoUabcs);

    	header.add(Box.createRigidArea(new Dimension(50, 0)));

    	JLabel lblInicio = new JLabel("Modulo grupo");
    	lblInicio.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblInicio.setBorder(null);
    	lblInicio.setFont(new Font("Almarai-Bold", Font.PLAIN, 50));
    	lblInicio.setForeground(Color.white);
    	lblInicio.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
    	lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
    	lblInicio.setBackground(azul2);
    	header.add(lblInicio);

    	ImageIcon iconCerrarSesion = new ImageIcon(this.getClass().getResource("/imagenes/cerrarsesion (1).png"));

    	JButton btnCerrarSesion = new JButton(iconCerrarSesion);
    	btnCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnCerrarSesion.setBackground(azul2);
    	btnCerrarSesion.setBorder(null);
    	btnCerrarSesion.setPreferredSize(new Dimension(120, 120));
    	btnCerrarSesion.addActionListener(e -> {
    	    int n = JOptionPane.showConfirmDialog(
    	            null,
    	            "Estas seguro que quieres cerrar sesión?",
    	            "Cerrar sesión",
    	            JOptionPane.YES_NO_OPTION);

    	    if (n == 0) {
    	        Controller c = new Controller();
    	        modulo.dispose();
    	        c.despliegue();
    	    } else if (n == 1) {
    	        JOptionPane.showMessageDialog(null, "GOODBYE");
    	    }
    	});
    	header.add(btnCerrarSesion);

    	header.add(Box.createRigidArea(new Dimension(10, 0)));

    	JPanel options = new JPanel();
    	contentPane.add(options, BorderLayout.WEST);
    	options.setPreferredSize(new Dimension(120, 2147483647));
    	options.setMaximumSize(new Dimension(120, 2147483647));
    	options.setLayout(new BoxLayout(options, BoxLayout.PAGE_AXIS));

    	JPanel moduloAlumnos = new JPanel();
    	moduloAlumnos.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloAlumnos.setPreferredSize(new Dimension(130, 120));
    	moduloAlumnos.setMaximumSize(new Dimension(130, 130));
    	moduloAlumnos.setBackground(azul2);

    	ImageIcon iconAlumnos = new ImageIcon(this.getClass().getResource("/imagenes/alumnos (1).png"));
    	moduloAlumnos.setLayout(new BoxLayout(moduloAlumnos, BoxLayout.Y_AXIS));

    	JButton btnAlumnos = new JButton(iconAlumnos);
    	btnAlumnos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnAlumnos.setBackground(azul2);
    	btnAlumnos.setBorder(null);
    	btnAlumnos.addActionListener(e -> {
    	    ModuloEstudianteController mac = new ModuloEstudianteController();
    	    modulo.dispose();
    	    mac.ModuloEstudiante();
    	});

    	moduloAlumnos.add(btnAlumnos);

    	JLabel lblAlumnos = new JLabel("<html><div style='text-align: center;'>Modulo de<br>alumnos");
    	lblAlumnos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblAlumnos.setForeground(new Color(255, 255, 255));
    	lblAlumnos.setBackground(azul2);
    	lblAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
    	lblAlumnos.setMaximumSize(new Dimension(80, 70));
    	moduloAlumnos.add(lblAlumnos);
    	options.add(moduloAlumnos);

    	JPanel moduloMaestros = new JPanel();
    	moduloMaestros.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    	moduloMaestros.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloMaestros.setPreferredSize(new Dimension(130, 140));
    	moduloMaestros.setMaximumSize(new Dimension(130, 130));
    	moduloMaestros.setBackground(azul2);

    	ImageIcon iconDocentnes = new ImageIcon(this.getClass().getResource("/imagenes/docentes (1).png"));
    	moduloMaestros.setLayout(new BoxLayout(moduloMaestros, BoxLayout.Y_AXIS));

    	JButton btnMaestros = new JButton(iconDocentnes);
    	btnMaestros.setBorder(null);
    	btnMaestros.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnMaestros.setBackground(azul2);
    	btnMaestros.addActionListener(e -> {
    	    ModuloDocenteController mdc = new ModuloDocenteController();
    	    modulo.dispose();
    	    mdc.moduloDocente();
    	});
    	moduloMaestros.add(btnMaestros);

    	JLabel lblMaestros = new JLabel("<html><div style='text-align: center;'>Modulo de<br>maestros");
    	lblMaestros.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblMaestros.setForeground(new Color(255, 255, 255));
    	lblMaestros.setBackground(azul2);
    	lblMaestros.setHorizontalAlignment(SwingConstants.CENTER);
    	lblMaestros.setMaximumSize(new Dimension(80, 70));
    	moduloMaestros.add(lblMaestros);
    	options.add(moduloMaestros);

    	JPanel moduloGrupo = new JPanel();
    	moduloGrupo.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    	moduloGrupo.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloGrupo.setPreferredSize(new Dimension(130, 120));
    	moduloGrupo.setMaximumSize(new Dimension(130, 130));
    	moduloGrupo.setBackground(azulBorde);

    	ImageIcon iconGrupo = new ImageIcon(this.getClass().getResource("/imagenes/grupos (1).png"));
    	moduloGrupo.setLayout(new BoxLayout(moduloGrupo, BoxLayout.Y_AXIS));

    	JButton btnGrupos = new JButton(iconGrupo);
    	btnGrupos.setBorder(null);
    	btnGrupos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnGrupos.setBackground(azulBorde);
    	btnGrupos.addActionListener(e -> {
    	    ModuloGrupoController mgc = new ModuloGrupoController();
    	    modulo.dispose();
    	    mgc.moduloGrupo();
    	});
    	moduloGrupo.add(btnGrupos);

    	JLabel lblGrupos = new JLabel("<html><div style='text-align: center;'>Modulo de<br>grupos");
    	lblGrupos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblGrupos.setForeground(new Color(255, 255, 255));
    	lblGrupos.setBackground(azul2);
    	lblGrupos.setHorizontalAlignment(SwingConstants.CENTER);
    	lblGrupos.setMaximumSize(new Dimension(80, 70));
    	moduloGrupo.add(lblGrupos);
    	options.add(moduloGrupo);

    	JPanel moduloAsignatura = new JPanel();
    	moduloAsignatura.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    	moduloAsignatura.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloAsignatura.setPreferredSize(new Dimension(150, 150));
    	moduloAsignatura.setMaximumSize(new Dimension(150, 150));
    	moduloAsignatura.setBackground(azul2);

    	ImageIcon iconAsignatura = new ImageIcon(this.getClass().getResource("/imagenes/asignaturas (1).png"));
    	moduloAsignatura.setLayout(new BoxLayout(moduloAsignatura, BoxLayout.Y_AXIS));

    	JButton btnAsignatura = new JButton(iconAsignatura);
    	btnAsignatura.setBorder(null);
    	btnAsignatura.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnAsignatura.setBackground(azul2);
    	btnAsignatura.addActionListener(e -> {
    	    ModuloAsignaturaController mac = new ModuloAsignaturaController();
    	    modulo.dispose();
    	    mac.moduloAsignatura();
    	});
    	moduloAsignatura.add(btnAsignatura);

    	JLabel lblAsignatura = new JLabel("<html><div style='text-align: center;'>Modulo de<br>asignaturas");
    	lblAsignatura.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblAsignatura.setForeground(new Color(255, 255, 255));
    	lblAsignatura.setBackground(azul2);
    	lblAsignatura.setHorizontalAlignment(SwingConstants.CENTER);
    	lblAsignatura.setMaximumSize(new Dimension(80, 70));
    	moduloAsignatura.add(lblAsignatura);
    	options.add(moduloAsignatura);
    	
    	JPanel contenido = new JPanel();
    	contentPane.add(contenido, BorderLayout.CENTER);
    	contenido.setBackground(Color.white);
    	contenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 0));
    	contenido.setLayout(new BorderLayout(0, 10));

    	JPanel panelSuperior = new JPanel(new BorderLayout());
    	panelSuperior.setBackground(Color.white);

    	JButton btnCancelar = new JButton("Cancelar");
    	btnCancelar.setBackground(new Color(108, 117, 125));
    	btnCancelar.setForeground(Color.white);
    	btnCancelar.setPreferredSize(new Dimension(80, 30));
    	btnCancelar.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    	btnCancelar.addActionListener(e -> {
    		ModuloGrupoController mgc = new ModuloGrupoController();

    		mgc.crear();
    	    modulo.dispose();
    	});

    	JLabel lblTitulo = new JLabel("Añadir un docente");
    	lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
    	lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

    	panelSuperior.add(btnCancelar, BorderLayout.WEST);
    	panelSuperior.add(lblTitulo, BorderLayout.CENTER);

    	contenido.add(panelSuperior, BorderLayout.NORTH);

    	// Panel de búsqueda
    	JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    	panelBusqueda.setBackground(Color.white);

    	JTextField txtBusqueda = new JTextField(20);
    	txtBusqueda.setPreferredSize(new Dimension(200, 30));

    	JButton btnBuscar = new JButton("🔍");
    	btnBuscar.setBackground(new Color(52, 144, 220));
    	btnBuscar.setForeground(Color.white);
    	btnBuscar.setPreferredSize(new Dimension(40, 30));
    	btnBuscar.setBorder(null);

    	JButton btnActualizar = new JButton("🔄");
    	btnActualizar.setBackground(new Color(52, 144, 220));
    	btnActualizar.setForeground(Color.white);
    	btnActualizar.setPreferredSize(new Dimension(40, 30));
    	btnActualizar.setBorder(null);

    	panelBusqueda.add(new JLabel("Buscar:"));
    	panelBusqueda.add(txtBusqueda);
    	panelBusqueda.add(btnBuscar);
    	panelBusqueda.add(btnActualizar);

    	contenido.add(panelBusqueda, BorderLayout.CENTER);

    	JPanel panelTabla = new JPanel(new BorderLayout());
    	panelTabla.setBackground(Color.white);

    	String[] columnas = {"Identificador", "Nombre completo", "Detalles", "Añadir"};
    	Object[][] datos = new Object[listaEstudiantes.size()][4];

    	// Carga el ícono (debe existir en esa ruta)
    	ImageIcon iconoOjo = new ImageIcon("/imagenes/añadir.png");

    	/*for (int i = 0; i < listaDocente.size(); i++) {
    	    Docente doc = listaDocente.get(i);
    	    datos[i][0] = doc.getNumeroControl();
    	    datos[i][1] = doc.getNombres() + " " + doc.getApellidos();
    	    datos[i][2] = "Datos completos";
    	    datos[i][3] = iconoOjo;
    	}*/

    	// Crear tabla
    	JTable tabla = new JTable(datos, columnas) {
    	    @Override
    	    public boolean isCellEditable(int row, int column) {
    	        return false;
    	    }

    	    @Override
    	    public Class<?> getColumnClass(int column) {
    	        return column == 3 ? Icon.class : String.class;
    	    }

    	    @Override
    	    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
    	        Component comp = super.prepareRenderer(renderer, row, column);

    	        if (row % 2 == 0) {
    	            comp.setBackground(Color.white);
    	        } else {
    	            comp.setBackground(new Color(248, 249, 250));
    	        }

    	        if (column == 2) {
    	            comp.setForeground(new Color(52, 144, 220));
    	        } else {
    	            comp.setForeground(Color.black);
    	        }

    	        return comp;
    	    }
    	};


    	tabla.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseClicked(MouseEvent e) {
    	        int fila = tabla.rowAtPoint(e.getPoint());
    	        int columna = tabla.columnAtPoint(e.getPoint());
    	        
    	        if (columna == 2) { 
    	            ModuloGrupoController mec = new ModuloGrupoController();
                    if (fila >= 0) {
                        Estudiante eSeleccionado = listaEstudiantes.get(fila);
	    	            modulo.dispose(); 
	    	            mec.datosGenerales(eSeleccionado);
                    }
    	        } else if (columna == 3) {
    	            int respuesta = JOptionPane.showConfirmDialog(modulo,
    	                "¿Desea añadir al docente " + tabla.getValueAt(fila, 1) + " al grupo?",
    	                "Confirmar",
    	                JOptionPane.YES_NO_OPTION);
    	            
    	            if (respuesta == JOptionPane.YES_OPTION) {
    	            	//Docente dSeleccionado= listaDocente.get(fila);
    	            	
    	            	//docentesAñadidos.add(dSeleccionado);
    	            	ModuloGrupoController mgc = new ModuloGrupoController();
    	            	modulo.dispose();
    	            	mgc.crear();
    	                JOptionPane.showMessageDialog(modulo, 
    	                    "Docente añadido correctamente al grupo");
    	            }
    	        }
    	    }
    	});

    	JScrollPane scrollPane = new JScrollPane(tabla);
    	scrollPane.setPreferredSize(new Dimension(700, 400));
    	panelTabla.add(scrollPane, BorderLayout.CENTER);

    	JPanel contenidoCentral = new JPanel(new BorderLayout(0, 10));
    	contenidoCentral.setBackground(Color.white);
    	contenidoCentral.add(panelBusqueda, BorderLayout.NORTH);
    	contenidoCentral.add(panelTabla, BorderLayout.CENTER);

    	contenido.add(contenidoCentral, BorderLayout.CENTER);

	}

    public void datosDocente(/*Docente docente*/) {
    	Color borde = new Color(206, 207, 202);
    	Color azul2 = new Color(52, 134, 199);
    	Color azul1 = new Color(54, 146, 218);
    	Color azulBorde = new Color(101, 166, 217);
    	JFrame modulo = new JFrame();
    	modulo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	modulo.setBounds(100, 100, 982, 647);
    	JPanel contentPane = new JPanel();
    	modulo.setLocationRelativeTo(null);
    	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    	modulo.setVisible(true);
    	modulo.setContentPane(contentPane);
    	contentPane.setLayout(new BorderLayout(0, 0));

    	JPanel header = new JPanel();
    	contentPane.add(header, BorderLayout.NORTH);
    	header.setBackground(azul2);
    	header.setPreferredSize(new Dimension(2147483647, 90));
    	header.setLayout(new BoxLayout(header, BoxLayout.LINE_AXIS));
    	header.setMaximumSize(new Dimension(2147483647, 40));

    	header.add(Box.createRigidArea(new Dimension(10, 0)));
    	ImageIcon logo = new ImageIcon(this.getClass().getResource("/imagenes/uabcs (1).png"));

    	JLabel logoUabcs = new JLabel(logo);
    	logoUabcs.setBackground(azul2);
    	logoUabcs.setBorder(null);
    	logoUabcs.setPreferredSize(new Dimension(100, 100));
    	logoUabcs.setMaximumSize(new Dimension(60, 100));
    	header.add(logoUabcs);

    	header.add(Box.createRigidArea(new Dimension(50, 0)));

    	JLabel lblInicio = new JLabel("Modulo docente");
    	lblInicio.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblInicio.setBorder(null);
    	lblInicio.setFont(new Font("Almarai-Bold", Font.PLAIN, 50));
    	lblInicio.setForeground(Color.white);
    	lblInicio.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
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
		            "Estas seguro que quieres cerrar sesión?",
		            "Cerrar sesión",
		            JOptionPane.YES_NO_OPTION);

		        if(n==0){
		            modulo.dispose();
		            Controller c = new Controller();
		            c.despliegue();
		            JOptionPane.showMessageDialog(null,"Sesión cerrada correctamente");
		        }
		        else if(n==1) {
		            
		        }
		});
		header.add(btnCerrarSesion);
		
		header.add(Box.createRigidArea(new Dimension(10,0)));
		
		JPanel options = new JPanel();
		modulo.add(options, BorderLayout.WEST);
		options.setPreferredSize(new Dimension(120, 120));
		options.setLayout(new BoxLayout(options, BoxLayout.PAGE_AXIS));
		
		JPanel moduloAlumnos = new JPanel ();
		moduloAlumnos.setAlignmentX(Component.RIGHT_ALIGNMENT);
		moduloAlumnos.setPreferredSize(new Dimension(130, 120));
		moduloAlumnos.setMaximumSize(new Dimension(130, 130));
		moduloAlumnos.setBackground(azul2);
		
		ImageIcon iconAlumnos = new ImageIcon (this.getClass().getResource("/imagenes/alumnos (1).png"));
		moduloAlumnos.setLayout(new BoxLayout(moduloAlumnos, BoxLayout.Y_AXIS));
		
		JButton btnAlumnos = new JButton(iconAlumnos);
		btnAlumnos.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAlumnos.setBackground(azul2);
		btnAlumnos.setBorder(null);
		btnAlumnos.addActionListener(e->{
			ModuloEstudianteController mac = new ModuloEstudianteController();
			modulo.dispose();
			mac.ModuloEstudiante();
		});
		
		moduloAlumnos.add(btnAlumnos);
		
		JLabel lblAlumnos = new JLabel ("<html><div style='text-align: center;'>Modulo de<br>alumnos");
		lblAlumnos.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAlumnos.setForeground(new Color(255, 255, 255));
		lblAlumnos.setBackground(azul2);
		lblAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlumnos.setMaximumSize(new Dimension(80, 70));
		lblAlumnos.setFont(new Font("Almarai-Bold", Font.PLAIN, 15));
		moduloAlumnos.add(lblAlumnos);
		options.add(moduloAlumnos);
		
		
		
		JPanel moduloMaestros = new JPanel ();
		moduloMaestros.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		moduloMaestros.setAlignmentX(Component.RIGHT_ALIGNMENT);
		moduloMaestros.setPreferredSize(new Dimension(130, 140));
		moduloMaestros.setMaximumSize(new Dimension(130, 130));
		moduloMaestros.setBackground(azulBorde);
		
		ImageIcon iconDocentnes = new ImageIcon (this.getClass().getResource("/imagenes/docentes (1).png"));
		moduloMaestros.setLayout(new BoxLayout(moduloMaestros, BoxLayout.Y_AXIS));
		
		JButton btnMaestros = new JButton(iconDocentnes);
		btnMaestros.setBorder(null);
		btnMaestros.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnMaestros.setBackground(azulBorde);
		btnMaestros.addActionListener(e->{
			ModuloDocenteController mdc= new ModuloDocenteController();
			modulo.dispose();
			mdc.moduloDocente();
		});
		moduloMaestros.add(btnMaestros);
		
		JLabel lblMaestros = new JLabel ("<html><div style='text-align: center;'>Modulo de<br>docentes");
		lblMaestros.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblMaestros.setForeground(new Color(255, 255, 255));
		lblMaestros.setBackground(azul2);
		lblMaestros.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaestros.setMaximumSize(new Dimension(80, 70));
		lblMaestros.setFont(new Font("Almarai-Bold", Font.PLAIN, 15));
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
		btnGrupos.addActionListener(e->{
			ModuloGrupoController mgc= new ModuloGrupoController();
			modulo.dispose();
			mgc.moduloGrupo();
		});		moduloGrupo.add(btnGrupos);
		
		JLabel lblGrupos = new JLabel ("<html><div style='text-align: center;'>Modulo de<br>grupos");
		lblGrupos.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblGrupos.setForeground(new Color(255, 255, 255));
		lblGrupos.setBackground(azul2);
		lblGrupos.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupos.setMaximumSize(new Dimension(80, 70));
		lblGrupos.setFont(new Font("Almarai-Bold", Font.PLAIN, 15));
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
		btnAsignatura.addActionListener(e->{
			ModuloAsignaturaController mac= new ModuloAsignaturaController();
			modulo.dispose();
			mac.moduloAsignatura();
			
		});
		moduloAsignatura.add(btnAsignatura);
		
		JLabel lblAsignatura = new JLabel ("<html><div style='text-align: center;'>Modulo de<br>asignaturas");
		lblAsignatura.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAsignatura.setForeground(new Color(255, 255, 255));
		lblAsignatura.setBackground(azul2);
		lblAsignatura.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsignatura.setMaximumSize(new Dimension(80, 70));
		lblAsignatura.setFont(new Font("Almarai-Bold", Font.PLAIN, 15));
		moduloAsignatura.add(lblAsignatura);
		options.add(moduloAsignatura);

    	// Panel de contenido
		JPanel contenido = new JPanel();
		contentPane.add(contenido, BorderLayout.CENTER);
		contenido.setBackground(Color.white);
		contenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 0));
		System.out.println(contenido.getHeight());
		contenido.setLayout(new BorderLayout(0, 0));
		
		JPanel panelContenido = new JPanel();
		panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
		panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		panelContenido.setBackground(Color.white);
		contentPane.add(panelContenido, BorderLayout.CENTER);

		JLabel lblTitulo = new JLabel("Datos generales de docente");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(lblTitulo);
        panelContenido.add(Box.createVerticalStrut(20));

        JPanel Formulario = new JPanel(new GridBagLayout());
        Formulario.setBackground(Color.white);
        Formulario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        GridBagConstraints organizador = new GridBagConstraints();
        organizador.insets = new Insets(8, 10, 8, 10);
        organizador.anchor = GridBagConstraints.WEST;
        organizador.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblNombres = new JLabel("Nombres");
        JTextField txtNombres = new JTextField(15);
        txtNombres.setBackground(Color.WHITE);
        txtNombres.setBorder(null);
        txtNombres.setEditable(false);

        JLabel lblApellidos = new JLabel("Apellidos");
        JTextField txtApellidos = new JTextField(15);
        txtApellidos.setBackground(Color.WHITE);
        txtApellidos.setBorder(null);
        txtApellidos.setEditable(false);
        
        JLabel lblId = new JLabel("Identificador");
        JTextField txtId = new JTextField();
        txtId.setEditable(false);
        txtId.setBackground(Color.WHITE);
        txtId.setBorder(null);
        
        JLabel lblFecha = new JLabel("Fecha de nacimiento");
        JTextField txtFecha = new JTextField();
        txtFecha.setBackground(Color.WHITE);
        txtFecha.setBorder(null);
        txtFecha.setEditable(false);
        
        JLabel lblGenero = new JLabel("Género");
        JTextField txtGenero= new JTextField();
        txtGenero.setBackground(Color.WHITE);
        txtGenero.setBorder(null);
        txtGenero.setEditable(false);
        
        JLabel lblTelefono = new JLabel("Teléfono");
        JTextField txtTelefono = new JTextField(15);
        txtTelefono.setBackground(Color.WHITE);
        txtTelefono.setBorder(null);
        txtTelefono.setEditable(false);
        
        JLabel lblGrado = new JLabel("Grado de estudios");
        JTextField txtGrado = new JTextField(15);
        txtGrado.setBackground(Color.WHITE);
        txtGrado.setBorder(null);
        txtGrado.setEditable(false);
        
        JLabel lblDomicilio = new JLabel("Domicilio");
        JTextField txtDomicilio = new JTextField(15);
        txtDomicilio.setBackground(Color.WHITE);
        txtDomicilio.setBorder(null);
        txtDomicilio.setEditable(false);
        
        JLabel lblCorreo = new JLabel("Correo electrónico");
        JTextField txtCorreo = new JTextField(15);
        txtCorreo.setBackground(Color.WHITE);
        txtCorreo.setBorder(null);
        txtCorreo.setEditable(false);
        
        JLabel lblCurp = new JLabel("CURP");
        JTextField txtCurp = new JTextField(15);
        txtCurp.setBackground(Color.WHITE);
        txtCurp.setBorder(null);
        txtCurp.setEditable(false);
        
        JPanel panelFoto = new JPanel();
        panelFoto.setLayout(new BoxLayout(panelFoto, BoxLayout.Y_AXIS));
        panelFoto.setBorder(BorderFactory.createTitledBorder("Foto"));
        panelFoto.setBackground(Color.white);

        JLabel lblFoto = new JLabel(); 
        lblFoto.setPreferredSize(new Dimension(100, 100));
        lblFoto.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnCargar = new JButton("📷 Cargar");
        btnCargar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCargar.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelFoto.add(Box.createVerticalStrut(10));
        panelFoto.add(lblFoto);
        panelFoto.add(Box.createVerticalStrut(10));
        panelFoto.add(btnCargar);

        // Carga de datos
        /*txtId.setText(String.valueOf(docente.getId()));
        txtNombres.setText(docente.getNombres());
        txtApellidos.setText(docente.getApellidos());
        txtCorreo.setText(docente.getCorreo());
        txtCurp.setText(docente.getCurp());
        txtFecha.setText(String.valueOf(docente.getFechaNacimiento()));
        txtGenero.setText(estudiante.getGenero());
        txtTelefono.setText(docente.getTelefono());
        txtGrado.setText(docente.getGrupo());
        txtDomicilio.setText(docente.getDomicilio());
       
        if (docente.getFoto() != null) {
            ImageIcon icon = new ImageIcon(docente.getFoto()); 
            Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            lblFoto.setIcon(new ImageIcon(scaledImage));
        }  */
        int fila = 0;

        organizador.gridx = 0; 
        organizador.gridy = fila;
        Formulario.add(lblNombres, organizador);
        
        organizador.gridx = 1;
        Formulario.add(txtNombres, organizador);
        
        organizador.gridx = 2;
        Formulario.add(lblCorreo, organizador);
        
        organizador.gridx = 3;
        Formulario.add(txtCorreo, organizador);
        fila++;

        organizador.gridx = 0; 
        organizador.gridy = fila;
        Formulario.add(lblApellidos, organizador);
        
        organizador.gridx = 1;
        Formulario.add(txtApellidos, organizador);
        
        organizador.gridx = 2;
        Formulario.add(lblCurp, organizador);
        
        organizador.gridx = 3;
        Formulario.add(txtCurp, organizador);
        fila++;

        organizador.gridx = 0; 
        organizador.gridy = fila;
        Formulario.add(lblId, organizador);
        
        organizador.gridx = 1;
        Formulario.add(txtId, organizador);
        fila++;

        organizador.gridx = 0; 
        organizador.gridy = fila;
        Formulario.add(lblFecha, organizador);
        
        organizador.gridx = 1;
        Formulario.add(txtFecha, organizador);
        fila++;

        organizador.gridx = 0; 
        organizador.gridy = fila;
        Formulario.add(lblGenero, organizador);
        
        organizador.gridx = 1;
        Formulario.add(txtGenero, organizador);
        fila++;

        organizador.gridx = 0; 
        organizador.gridy = fila;
        Formulario.add(lblTelefono, organizador);
        
        organizador.gridx = 1;
        Formulario.add(txtTelefono, organizador);
        fila++;

        organizador.gridx = 0; 
        organizador.gridy = fila;
        Formulario.add(lblGrado, organizador);
        
        organizador.gridx = 1;
        Formulario.add(txtGrado, organizador);
        fila++;

        organizador.gridx = 0; 
        organizador.gridy = fila;
        Formulario.add(lblDomicilio, organizador);
        
        organizador.gridx = 1;
        Formulario.add(txtDomicilio, organizador);

        organizador.gridx = 3;
        organizador.gridy = 2;
        organizador.gridheight = 6;
        Formulario.add(panelFoto, organizador);

        panelContenido.add(Formulario);
        panelContenido.add(Box.createVerticalStrut(20));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCancelar = new JButton("Regresar");
        JButton btnCrear = new JButton("Descargar PDF");
        btnCancelar.setBackground(azulC);
        btnCancelar.setForeground(Color.white);
        btnCancelar.setBorder(BorderFactory.createLineBorder(azul2,5));
        btnCrear.setBackground(azul1);
        btnCrear.setForeground(Color.white);
        btnCrear.setBorder(BorderFactory.createLineBorder(azulBorde,5));
        btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCrear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(e->{
        	ModuloDocenteController mdc = new ModuloDocenteController();
        	modulo.dispose();
        	mdc.moduloDocente();
        });
        btnCrear.addActionListener(e->{
        	
        });
        panelBotones.add(btnCancelar);
        panelBotones.add(btnCrear);

        panelContenido.add(panelBotones);

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
    
    public void addAsignatura() {
		JFrame modulo = new JFrame();
    	modulo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	modulo.setBounds(100, 100, 982, 647);
    	JPanel contentPane = new JPanel();
    	modulo.setLocationRelativeTo(null);
    	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    	modulo.setVisible(true);
    	modulo.setContentPane(contentPane);
    	contentPane.setLayout(new BorderLayout(0, 0));

    	JPanel header = new JPanel();
    	contentPane.add(header, BorderLayout.NORTH);
    	header.setBackground(azul2);
    	header.setPreferredSize(new Dimension(2147483647, 90));
    	header.setLayout(new BoxLayout(header, BoxLayout.LINE_AXIS));
    	header.setMaximumSize(new Dimension(2147483647, 40));

    	header.add(Box.createRigidArea(new Dimension(10, 0)));
    	ImageIcon logo = new ImageIcon(this.getClass().getResource("/imagenes/uabcs (1).png"));

    	JLabel logoUabcs = new JLabel(logo);
    	logoUabcs.setBackground(azul2);
    	logoUabcs.setBorder(null);
    	logoUabcs.setPreferredSize(new Dimension(100, 100));
    	logoUabcs.setMaximumSize(new Dimension(60, 100));
    	header.add(logoUabcs);

    	header.add(Box.createRigidArea(new Dimension(50, 0)));

    	JLabel lblInicio = new JLabel("Modulo grupo");
    	lblInicio.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblInicio.setBorder(null);
    	lblInicio.setFont(new Font("Almarai-Bold", Font.PLAIN, 50));
    	lblInicio.setForeground(Color.white);
    	lblInicio.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
    	lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
    	lblInicio.setBackground(azul2);
    	header.add(lblInicio);

    	ImageIcon iconCerrarSesion = new ImageIcon(this.getClass().getResource("/imagenes/cerrarsesion (1).png"));

    	JButton btnCerrarSesion = new JButton(iconCerrarSesion);
    	btnCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnCerrarSesion.setBackground(azul2);
    	btnCerrarSesion.setBorder(null);
    	btnCerrarSesion.setPreferredSize(new Dimension(120, 120));
    	btnCerrarSesion.addActionListener(e -> {
    	    int n = JOptionPane.showConfirmDialog(
    	            null,
    	            "Estas seguro que quieres cerrar sesión?",
    	            "Cerrar sesión",
    	            JOptionPane.YES_NO_OPTION);

    	    if (n == 0) {
    	        Controller c = new Controller();
    	        modulo.dispose();
    	        c.despliegue();
    	    } else if (n == 1) {
    	        JOptionPane.showMessageDialog(null, "GOODBYE");
    	    }
    	});
    	header.add(btnCerrarSesion);

    	header.add(Box.createRigidArea(new Dimension(10, 0)));

    	JPanel options = new JPanel();
    	contentPane.add(options, BorderLayout.WEST);
    	options.setPreferredSize(new Dimension(120, 2147483647));
    	options.setMaximumSize(new Dimension(120, 2147483647));
    	options.setLayout(new BoxLayout(options, BoxLayout.PAGE_AXIS));

    	JPanel moduloAlumnos = new JPanel();
    	moduloAlumnos.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloAlumnos.setPreferredSize(new Dimension(130, 120));
    	moduloAlumnos.setMaximumSize(new Dimension(130, 130));
    	moduloAlumnos.setBackground(azul2);

    	ImageIcon iconAlumnos = new ImageIcon(this.getClass().getResource("/imagenes/alumnos (1).png"));
    	moduloAlumnos.setLayout(new BoxLayout(moduloAlumnos, BoxLayout.Y_AXIS));

    	JButton btnAlumnos = new JButton(iconAlumnos);
    	btnAlumnos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnAlumnos.setBackground(azul2);
    	btnAlumnos.setBorder(null);
    	btnAlumnos.addActionListener(e -> {
    	    ModuloEstudianteController mac = new ModuloEstudianteController();
    	    modulo.dispose();
    	    mac.ModuloEstudiante();
    	});

    	moduloAlumnos.add(btnAlumnos);

    	JLabel lblAlumnos = new JLabel("<html><div style='text-align: center;'>Modulo de<br>alumnos");
    	lblAlumnos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblAlumnos.setForeground(new Color(255, 255, 255));
    	lblAlumnos.setBackground(azul2);
    	lblAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
    	lblAlumnos.setMaximumSize(new Dimension(80, 70));
    	moduloAlumnos.add(lblAlumnos);
    	options.add(moduloAlumnos);

    	JPanel moduloMaestros = new JPanel();
    	moduloMaestros.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    	moduloMaestros.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloMaestros.setPreferredSize(new Dimension(130, 140));
    	moduloMaestros.setMaximumSize(new Dimension(130, 130));
    	moduloMaestros.setBackground(azul2);

    	ImageIcon iconDocentnes = new ImageIcon(this.getClass().getResource("/imagenes/docentes (1).png"));
    	moduloMaestros.setLayout(new BoxLayout(moduloMaestros, BoxLayout.Y_AXIS));

    	JButton btnMaestros = new JButton(iconDocentnes);
    	btnMaestros.setBorder(null);
    	btnMaestros.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnMaestros.setBackground(azul2);
    	btnMaestros.addActionListener(e -> {
    	    ModuloDocenteController mdc = new ModuloDocenteController();
    	    modulo.dispose();
    	    mdc.moduloDocente();
    	});
    	moduloMaestros.add(btnMaestros);

    	JLabel lblMaestros = new JLabel("<html><div style='text-align: center;'>Modulo de<br>maestros");
    	lblMaestros.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblMaestros.setForeground(new Color(255, 255, 255));
    	lblMaestros.setBackground(azul2);
    	lblMaestros.setHorizontalAlignment(SwingConstants.CENTER);
    	lblMaestros.setMaximumSize(new Dimension(80, 70));
    	moduloMaestros.add(lblMaestros);
    	options.add(moduloMaestros);

    	JPanel moduloGrupo = new JPanel();
    	moduloGrupo.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    	moduloGrupo.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloGrupo.setPreferredSize(new Dimension(130, 120));
    	moduloGrupo.setMaximumSize(new Dimension(130, 130));
    	moduloGrupo.setBackground(azulBorde);

    	ImageIcon iconGrupo = new ImageIcon(this.getClass().getResource("/imagenes/grupos (1).png"));
    	moduloGrupo.setLayout(new BoxLayout(moduloGrupo, BoxLayout.Y_AXIS));

    	JButton btnGrupos = new JButton(iconGrupo);
    	btnGrupos.setBorder(null);
    	btnGrupos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnGrupos.setBackground(azulBorde);
    	btnGrupos.addActionListener(e -> {
    	    ModuloGrupoController mgc = new ModuloGrupoController();
    	    modulo.dispose();
    	    mgc.moduloGrupo();
    	});
    	moduloGrupo.add(btnGrupos);

    	JLabel lblGrupos = new JLabel("<html><div style='text-align: center;'>Modulo de<br>grupos");
    	lblGrupos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblGrupos.setForeground(new Color(255, 255, 255));
    	lblGrupos.setBackground(azul2);
    	lblGrupos.setHorizontalAlignment(SwingConstants.CENTER);
    	lblGrupos.setMaximumSize(new Dimension(80, 70));
    	moduloGrupo.add(lblGrupos);
    	options.add(moduloGrupo);

    	JPanel moduloAsignatura = new JPanel();
    	moduloAsignatura.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    	moduloAsignatura.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	moduloAsignatura.setPreferredSize(new Dimension(150, 150));
    	moduloAsignatura.setMaximumSize(new Dimension(150, 150));
    	moduloAsignatura.setBackground(azul2);

    	ImageIcon iconAsignatura = new ImageIcon(this.getClass().getResource("/imagenes/asignaturas (1).png"));
    	moduloAsignatura.setLayout(new BoxLayout(moduloAsignatura, BoxLayout.Y_AXIS));

    	JButton btnAsignatura = new JButton(iconAsignatura);
    	btnAsignatura.setBorder(null);
    	btnAsignatura.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnAsignatura.setBackground(azul2);
    	btnAsignatura.addActionListener(e -> {
    	    ModuloAsignaturaController mac = new ModuloAsignaturaController();
    	    modulo.dispose();
    	    mac.moduloAsignatura();
    	});
    	moduloAsignatura.add(btnAsignatura);

    	JLabel lblAsignatura = new JLabel("<html><div style='text-align: center;'>Modulo de<br>asignaturas");
    	lblAsignatura.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lblAsignatura.setForeground(new Color(255, 255, 255));
    	lblAsignatura.setBackground(azul2);
    	lblAsignatura.setHorizontalAlignment(SwingConstants.CENTER);
    	lblAsignatura.setMaximumSize(new Dimension(80, 70));
    	moduloAsignatura.add(lblAsignatura);
    	options.add(moduloAsignatura);
    	
    	JPanel contenido = new JPanel();
    	contentPane.add(contenido, BorderLayout.CENTER);
    	contenido.setBackground(Color.white);
    	contenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 0));
    	contenido.setLayout(new BorderLayout(0, 10));

    	JPanel panelSuperior = new JPanel(new BorderLayout());
    	panelSuperior.setBackground(Color.white);

    	JButton btnCancelar = new JButton("Cancelar");
    	btnCancelar.setBackground(new Color(108, 117, 125));
    	btnCancelar.setForeground(Color.white);
    	btnCancelar.setPreferredSize(new Dimension(80, 30));
    	btnCancelar.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    	btnCancelar.addActionListener(e -> {
    		ModuloGrupoController mgc = new ModuloGrupoController();

    		mgc.crear();
    	    modulo.dispose();
    	});

    	JLabel lblTitulo = new JLabel("Añadir un estudiante");
    	lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
    	lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

    	panelSuperior.add(btnCancelar, BorderLayout.WEST);
    	panelSuperior.add(lblTitulo, BorderLayout.CENTER);

    	contenido.add(panelSuperior, BorderLayout.NORTH);

    	// Panel de búsqueda
    	JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    	panelBusqueda.setBackground(Color.white);

    	JTextField txtBusqueda = new JTextField(20);
    	txtBusqueda.setPreferredSize(new Dimension(200, 30));

    	ImageIcon buscar = new ImageIcon(getClass().getResource("/imagenes/busqueda.png"));
    	JButton btnBuscar = new JButton(buscar);
    	btnBuscar.setBackground(new Color(52, 144, 220));
    	btnBuscar.setForeground(Color.white);
    	btnBuscar.setPreferredSize(new Dimension(40, 30));
    	btnBuscar.setBorder(null);

    	JButton btnActualizar = new JButton("🔄");
    	btnActualizar.setBackground(new Color(52, 144, 220));
    	btnActualizar.setForeground(Color.white);
    	btnActualizar.setPreferredSize(new Dimension(40, 30));
    	btnActualizar.setBorder(null);

    	panelBusqueda.add(new JLabel("Buscar:"));
    	panelBusqueda.add(txtBusqueda);
    	panelBusqueda.add(btnBuscar);
    	panelBusqueda.add(btnActualizar);

    	contenido.add(panelBusqueda, BorderLayout.CENTER);

    	JPanel panelTabla = new JPanel(new BorderLayout());
    	panelTabla.setBackground(Color.white);

    	/*String[] columnas = {"Identificador", "Nombre completo", "Detalles", "Añadir"};
    	Object[][] datos = new Object[listaAsignatura.size()][4];

    	// Carga el ícono (debe existir en esa ruta)
    	ImageIcon iconoOjo = new ImageIcon("/imagenes/añadir.png");

    	for (int i = 0; i < listaAsignatura.size(); i++) {
    		Asignaturas asi = listaAsignatura.get(i);
    	    datos[i][0] = asi.getNumeroControl();
    	    datos[i][1] = asi.getNombres() + " " + asi.getApellidos();
    	    datos[i][2] = "Datos completos";
    	    datos[i][3] = iconoOjo;
    	}

    	// Crear tabla
    	JTable tabla = new JTable(datos, columnas) {
    	    @Override
    	    public boolean isCellEditable(int row, int column) {
    	        return false;
    	    }

    	    @Override
    	    public Class<?> getColumnClass(int column) {
    	        return column == 3 ? Icon.class : String.class;
    	    }

    	    @Override
    	    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
    	        Component comp = super.prepareRenderer(renderer, row, column);

    	        if (row % 2 == 0) {
    	            comp.setBackground(Color.white);
    	        } else {
    	            comp.setBackground(new Color(248, 249, 250));
    	        }

    	        if (column == 2) {
    	            comp.setForeground(new Color(52, 144, 220));
    	        } else {
    	            comp.setForeground(Color.black);
    	        }

    	        return comp;
    	    }
    	};


    	tabla.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseClicked(MouseEvent e) {
    	    	inicializarListaEstudiantes();
    	        int fila = tabla.rowAtPoint(e.getPoint());
    	        int columna = tabla.columnAtPoint(e.getPoint());
    	        
    	        if (columna == 2) { 
    	            ModuloGrupoController mec = new ModuloGrupoController();
                    if (fila >= 0) {
                    	//Asignatura aSeleccionado = listaAsignatura.get(fila);
	    	            modulo.dispose(); 
	    	           // mec.datosGenerales(aSeleccionado);
                    }
    	        } else if (columna == 3) {
    	        	Estudiante eSeleccionado = listaEstudiantes.get(fila);
                    
                        
                    int respuesta = JOptionPane.showConfirmDialog(modulo,
                        "¿Desea añadir al asignatura " + tabla.getValueAt(fila, 1) + " al grupo?",
                        "Confirmar",
                        JOptionPane.YES_NO_OPTION);
                    
                    if (respuesta == JOptionPane.YES_OPTION) {
                    	//asignaturasAñadidos.add(aSeleccionado);
                        
                        JOptionPane.showMessageDialog(modulo, 
                            "Asignatura añadida correctamente al grupo");
                        
                        modulo.dispose();
                        crear();
    	            }
    	        }
    	    }
    	});

    	JScrollPane scrollPane = new JScrollPane(tabla);
    	scrollPane.setPreferredSize(new Dimension(700, 400));
    	panelTabla.add(scrollPane, BorderLayout.CENTER);
*/
    	JPanel contenidoCentral = new JPanel(new BorderLayout(0, 10));
    	contenidoCentral.setBackground(Color.white);
    	contenidoCentral.add(panelBusqueda, BorderLayout.NORTH);
    	contenidoCentral.add(panelTabla, BorderLayout.CENTER);

    	contenido.add(contenidoCentral, BorderLayout.CENTER);

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

    public class BotonEditor extends DefaultCellEditor {
	    protected JButton boton;
	    private String texto;
	    private JTable tabla;
	    private JFrame modulo;

	    public BotonEditor(JCheckBox checkBox, String texto, JTable tabla, JFrame modulo) {
	        super(checkBox);
	        this.texto = texto;
	        this.tabla = tabla;
	        this.modulo = modulo;
	        boton = new JButton(texto);
	        boton.setForeground(Color.BLUE);
	        boton.setBorderPainted(false);
	        boton.setContentAreaFilled(false);

	        boton.addActionListener(e -> {
	        	ModuloGrupoController mgc = new ModuloGrupoController();
				modulo.dispose();
				mgc.datos();
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
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            JButton btnEditar = new JButton(new ImageIcon(this.getClass().getResource("/imagenes/edit (1).png")));
            JButton btnBorrar = new JButton(new ImageIcon(this.getClass().getResource("/imagenes/delete (1).png")));
            btnEditar.setPreferredSize(new Dimension(20, 20));
            btnEditar.setBorder(null);
            btnEditar.setBackground(Color.white);
            btnBorrar.setBorder(null);
            btnBorrar.setPreferredSize(new Dimension(20, 20));
            btnBorrar.setBackground(Color.white);
            this.setBackground(Color.white);
            add(btnEditar);
            add(btnBorrar);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    class PanelBotonesEditor extends DefaultCellEditor {
        private JPanel panel;
        private JButton editar, borrar;
        private JTable tabla;
        private JFrame modulo;
        

        public PanelBotonesEditor(JCheckBox checkBox, JTable tabla,JFrame ventana) {
            super(checkBox);
            this.tabla = tabla;
            this.modulo=ventana;
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {

            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
            panel.setBackground(Color.WHITE);

            editar = new JButton(new ImageIcon(getClass().getResource("/imagenes/edit (1).png")));
            borrar = new JButton(new ImageIcon(getClass().getResource("/imagenes/delete (1).png")));

            editar.setPreferredSize(new Dimension(20, 20));
            editar.setBorder(null);
            editar.setBackground(Color.white);
            borrar.setBorder(null);
            borrar.setPreferredSize(new Dimension(20, 20));
            borrar.setBackground(Color.white);
            
            editar.setFocusable(false);
            borrar.setFocusable(false);
            
            editar.addActionListener(e -> {
                ModuloGrupoController mgc = new ModuloGrupoController();
                modulo.dispose();
                mgc.modificar();
            });

            borrar.addActionListener(e -> {
                ((DefaultTableModel) tabla.getModel()).removeRow(row);
                JOptionPane.showMessageDialog(null, "Fila eliminada " + (row + 1));
                fireEditingStopped();
                System.out.println(borrar.getSize());
            });

            panel.add(editar);
            panel.add(borrar);
            panel.repaint();
            return panel;
        }

        public Object getCellEditorValue() {
            return "";
        }
   	}
    private void inicializarListaEstudiantes() {
        if (estudiantesAñadidos == null) {
            estudiantesAñadidos = new ArrayList<>();
        }
    }
}