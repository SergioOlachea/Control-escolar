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
import javax.swing.JTextArea;
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
import model.Asignatura;
import model.Docente;
import model.Estudiante;
import model.Grupo;
import model.ModuloAsignaturaModel;
import model.ModuloDocenteModel;
import model.ModuloEstudianteModel;
import model.ModuloGrupoModel;
import view.ModuloAsignturaView.BotonRenderer;
import view.ModuloDocenteView.BotonEditor;
import view.ModuloDocenteView.PanelBotonesEditor;
import view.ModuloDocenteView.PanelBotonesRenderer;

public class ModuloGrupoView {
	String nombreDocente;
	ModuloGrupoModel mgm =new ModuloGrupoModel();
	ArrayList<Grupo> listaGrupos =mgm.getGrupos();
	
	ModuloEstudianteModel mem =new ModuloEstudianteModel();
	ArrayList<Estudiante> listaEstudiantes =mem.getEstudiantes();
	
	ModuloDocenteModel mdm1 =new ModuloDocenteModel();
	ArrayList<Docente> listaDocentes =mdm1.getDocentes();
	
	ModuloAsignaturaModel mam1 =new ModuloAsignaturaModel();
	ArrayList<Asignatura> listaAsignaturas =mam1.getAsignaturas();
	
	ArrayList<Estudiante> lista = new ArrayList();
	
	private Asignatura asignaturaGrupo;
	private Docente docenteGrupo;
	
	ImageIcon buscar = new ImageIcon("/imagenes/buscar.png");
	ModuloDocenteModel mdm= new ModuloDocenteModel();
	ModuloAsignaturaModel mam = new ModuloAsignaturaModel();
	Color borde = new Color(206, 207, 202);
	Color azul2 = new Color(52, 134, 199);
	Color azul1 = new Color(54, 146, 218);
	Color azulC = new Color(40, 103, 152);
	Color azulBorde= new Color(101, 166, 217);
	Color azulcan= new Color(40, 103, 152);
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
		lblMaestros.setFont(new Font("Almarai-Bold", Font.PLAIN, 15));
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
        Object[][] datos = new Object[listaGrupos.size()][columnas.length];

        
        
        for (int i = 0; i < listaGrupos.size(); i++) {
        	Grupo g = listaGrupos.get(i);
        	Docente docente = g.getDocente();
        	if (docente != null) {
        	    nombreDocente = docente.getNombres()+" "+docente.getApellidos();
        	} else {
        		System.out.println(g.getId()+"no hay");
        	}
        	
            datos[i][0] = g.getId(); 
            datos[i][1] = g.getNombre(); 
            datos[i][2] = nombreDocente; 
            datos[i][3] = g.getAsignatura().getNombre(); 
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
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(model1);
            tabla.setRowSorter(sorter);

            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(texto), columna));

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
    	lblAlumnos.setFont(new Font("Almarai-Bold", Font.PLAIN, 15));
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
    	lblMaestros.setFont(new Font("Almarai-Bold", Font.PLAIN, 15));
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
    	lblGrupos.setFont(new Font("Almarai-Bold", Font.PLAIN, 15));
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
    	lblAsignatura.setFont(new Font("Almarai-Bold", Font.PLAIN, 15));
    	moduloAsignatura.add(lblAsignatura);
    	options.add(moduloAsignatura);

    	//Contenido
    	JPanel contenido = new JPanel();
    	contentPane.add(contenido, BorderLayout.CENTER);
    	contenido.setBackground(Color.WHITE);
    	contenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 15));
    	contenido.setLayout(new BorderLayout(0, 20));

    	JLabel lblTitulo = new JLabel("Creación de grupo");
    	lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
    	lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
    	contenido.add(lblTitulo, BorderLayout.NORTH);

    	JPanel Formulario = new JPanel();
    	Formulario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
    	Formulario.setBackground(Color.WHITE);
    	contenido.add(Formulario, BorderLayout.CENTER);

    	Formulario.setLayout(new GridBagLayout());
    	GridBagConstraints gbc = new GridBagConstraints();

    	JLabel lblDocente = new JLabel("Docente");
    	lblDocente.setFont(new Font("Arial", Font.BOLD, 14));

    	JLabel lblAsignaturas = new JLabel("Asignatura");
    	lblAsignaturas.setFont(new Font("Arial", Font.BOLD, 14));

    	JLabel lblIdentificador = new JLabel("Identificador");
    	lblIdentificador.setFont(new Font("Arial", Font.BOLD, 14));

    	JLabel lblNombre = new JLabel("Nombre");
    	lblNombre.setFont(new Font("Arial", Font.BOLD, 14));

    	JLabel lblListaAlumnos = new JLabel("Lista de alumnos");
    	lblListaAlumnos.setFont(new Font("Arial", Font.BOLD, 14));

    	JLabel lblAlumno = new JLabel("Alumno");
    	lblAlumno.setFont(new Font("Arial", Font.BOLD, 14));

    	JTextField txtIdentificador = new JTextField();
    	txtIdentificador.setEditable(false);
    	txtIdentificador.setText("se genera automaticamente");
    	txtIdentificador.setPreferredSize(new Dimension(200, 30));
    	txtIdentificador.setBorder(BorderFactory.createLineBorder(borde, 2));

    	JTextField txtNombre = new JTextField();
    	txtNombre.setPreferredSize(new Dimension(200, 30));
    	txtNombre.setBorder(BorderFactory.createLineBorder(borde, 2));

    	JComboBox<String> comboAsignatura = new JComboBox<>();
    	comboAsignatura.setPreferredSize(new Dimension(200, 30));
    	comboAsignatura.setBackground(Color.WHITE);
    	comboAsignatura.setBorder(BorderFactory.createLineBorder(borde, 2));

    	JComboBox<String> comboDocente = new JComboBox<>();
    	comboDocente.setPreferredSize(new Dimension(200, 30));
    	comboDocente.setBackground(Color.WHITE);
    	comboDocente.setBorder(BorderFactory.createLineBorder(borde, 2));

    	JComboBox<String> comboAlumno = new JComboBox<>();
    	comboAlumno.setPreferredSize(new Dimension(200, 30));
    	comboAlumno.setBackground(Color.WHITE);
    	comboAlumno.setBorder(BorderFactory.createLineBorder(borde, 2));

    	for (Asignatura a : listaAsignaturas) {
    	    comboAsignatura.addItem(a.getNombre());
    	}
    	for (Docente d : listaDocentes) {
    	    comboDocente.addItem(d.getNombres());
    	}
    	for (Estudiante e: listaEstudiantes) {
    		String nombreCompleto = e.getNombres() + " " + e.getApellidos();
    	    comboAlumno.addItem(nombreCompleto);
    	}

    	DefaultListModel<String> modeloLista = new DefaultListModel<>();
    	ArrayList<Estudiante> listaEstudiantesSeleccionados = new ArrayList<>();

    	JList<String> listaAñadidos = new JList<>(modeloLista);
    	listaAñadidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	listaAñadidos.setBorder(BorderFactory.createLineBorder(borde, 2));
    	JScrollPane scrollLista = new JScrollPane(listaAñadidos);
    	scrollLista.setPreferredSize(new Dimension(500, 200));

    	ImageIcon iconAnadir = new ImageIcon(getClass().getResource("/imagenes/añadir (1).png"));
    	JButton btnAnadir = new JButton("Añadir", iconAnadir);
    	btnAnadir.setBackground(azul1);
    	btnAnadir.setForeground(Color.WHITE);
    	btnAnadir.setBorder(BorderFactory.createLineBorder(azulBorde, 2));
    	btnAnadir.setPreferredSize(new Dimension(100, 35));

    	ImageIcon iconEliminar = new ImageIcon(getClass().getResource("/imagenes/delete (1).png"));
    	JButton btnEliminar = new JButton(iconEliminar);
    	btnEliminar.setBackground(azul1);
    	btnEliminar.setBorder(BorderFactory.createLineBorder(azulBorde, 2));
    	btnEliminar.setPreferredSize(new Dimension(35, 35));

    	JButton btnCancelar = new JButton("Cancelar");
    	btnCancelar.setBackground(azulC);
    	btnCancelar.setForeground(Color.WHITE);
    	btnCancelar.setBorder(BorderFactory.createLineBorder(azulBorde, 2));
    	btnCancelar.setPreferredSize(new Dimension(100, 35));

    	JButton btnCrear = new JButton("Crear");
    	btnCrear.setBackground(azul1);
    	btnCrear.setForeground(Color.WHITE);
    	btnCrear.setBorder(BorderFactory.createLineBorder(azulBorde, 2));
    	btnCrear.setPreferredSize(new Dimension(100, 35));

    	gbc.gridx = 0; gbc.gridy = 0;
    	gbc.anchor = GridBagConstraints.WEST;
    	gbc.insets = new Insets(20, 20, 10, 10);
    	Formulario.add(lblDocente, gbc);

    	gbc.gridx = 1;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.weightx = 1.0;
    	gbc.insets = new Insets(20, 0, 10, 50);
    	Formulario.add(comboDocente, gbc);

    	gbc.gridx = 2;
    	gbc.fill = GridBagConstraints.NONE;
    	gbc.weightx = 0.0;
    	gbc.insets = new Insets(20, 50, 10, 10);
    	Formulario.add(lblAsignaturas, gbc);

    	gbc.gridx = 3;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.weightx = 1.0;
    	gbc.insets = new Insets(20, 0, 10, 20);
    	Formulario.add(comboAsignatura, gbc);

    	gbc.gridx = 0; gbc.gridy = 1;
    	gbc.fill = GridBagConstraints.NONE;
    	gbc.weightx = 0.0;
    	gbc.insets = new Insets(10, 20, 5, 10);
    	Formulario.add(lblIdentificador, gbc);

    	gbc.gridx = 1;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.weightx = 1.0;
    	gbc.insets = new Insets(10, 0, 5, 50);
    	Formulario.add(txtIdentificador, gbc);

    	gbc.gridx = 2;
    	gbc.fill = GridBagConstraints.NONE;
    	gbc.weightx = 0.0;
    	gbc.insets = new Insets(10, 50, 5, 10);
    	Formulario.add(lblNombre, gbc);

    	gbc.gridx = 3;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.weightx = 1.0;
    	gbc.insets = new Insets(10, 0, 5, 20);
    	Formulario.add(txtNombre, gbc);

    	gbc.gridx = 0; gbc.gridy = 3;
    	gbc.gridwidth = 4;
    	gbc.anchor = GridBagConstraints.WEST;
    	gbc.insets = new Insets(20, 20, 10, 10);
    	Formulario.add(lblListaAlumnos, gbc);

    	gbc.gridx = 0; gbc.gridy = 4;
    	gbc.gridwidth = 2;
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.weightx = 1.0;
    	gbc.weighty = 1.0;
    	gbc.insets = new Insets(0, 20, 20, 20);
    	Formulario.add(scrollLista, gbc);

    	JPanel panelAlumno = new JPanel();
    	panelAlumno.setBackground(Color.WHITE);
    	panelAlumno.setLayout(new GridBagLayout());
    	GridBagConstraints gbcAlumno = new GridBagConstraints();

    	gbcAlumno.gridx = 0; gbcAlumno.gridy = 0;
    	gbcAlumno.insets = new Insets(0, 0, 10, 0);
    	panelAlumno.add(lblAlumno, gbcAlumno);

    	gbcAlumno.gridy = 1;
    	panelAlumno.add(comboAlumno, gbcAlumno);

    	gbcAlumno.gridy = 2;
    	panelAlumno.add(btnAnadir, gbcAlumno);

    	gbcAlumno.gridy = 3;
    	panelAlumno.add(btnEliminar, gbcAlumno);

    	gbc.gridx = 2; gbc.gridy = 4;
    	gbc.gridwidth = 2;
    	gbc.fill = GridBagConstraints.NONE;
    	gbc.weightx = 0.0;
    	gbc.weighty = 0.0;
    	gbc.anchor = GridBagConstraints.NORTH;
    	gbc.insets = new Insets(0, 20, 20, 20);
    	Formulario.add(panelAlumno, gbc);

    	JPanel panelInferior = new JPanel();
    	panelInferior.setBackground(Color.WHITE);
    	panelInferior.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    	panelInferior.add(btnCancelar);
    	panelInferior.add(btnCrear);
    	contenido.add(panelInferior, BorderLayout.SOUTH);

    	btnAnadir.addActionListener(e -> {
    		 String nombreSeleccionado = (String) comboAlumno.getSelectedItem();
    		    if (nombreSeleccionado != null && !modeloLista.contains(nombreSeleccionado)) {
    		        modeloLista.addElement(nombreSeleccionado);

    		        for (Estudiante est : listaEstudiantes) {
    		            String nombreCompleto = est.getNombres() + " " + est.getApellidos();
    		            if (nombreCompleto.equals(nombreSeleccionado)) {
    		                listaEstudiantesSeleccionados.add(est);
    		                System.out.println("Estudiante agregado: " + nombreCompleto);
    		                break;
    		            }
    		        }
    		    } else if (modeloLista.contains(nombreSeleccionado)) {
    		        JOptionPane.showMessageDialog(modulo, "El alumno ya está en la lista");
    		    }
    	});

    	btnEliminar.addActionListener(e -> {
    	    int indiceSeleccionado = listaAñadidos.getSelectedIndex();
    	    if (indiceSeleccionado != -1) {
    	        String alumnoEliminado = modeloLista.getElementAt(indiceSeleccionado);
    	        modeloLista.removeElementAt(indiceSeleccionado);
    	        System.out.println("Alumno eliminado: " + alumnoEliminado);
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
    	        ModuloGrupoController mgc = new ModuloGrupoController();
    	        modulo.dispose();
    	        mgc.moduloGrupo();
    	    }
    	});

    	btnCrear.addActionListener(e -> {
    		
    	    String nombreAsignaturaSeleccionada = (String) comboAsignatura.getSelectedItem();
    	    Asignatura asignaturaGrupo = null;
    	    for (Asignatura a : listaAsignaturas) {
    	        if (a.getNombre().equals(nombreAsignaturaSeleccionada)) {
    	            asignaturaGrupo = a;
    	            break;
    	        }
    	    }

    	    String nombreDocenteSeleccionado = (String) comboDocente.getSelectedItem();
    	    Docente docenteGrupo = null;
    	    for (Docente d : listaDocentes) {
    	        if (d.getNombres().equals(nombreDocenteSeleccionado)) {
    	            docenteGrupo = d;
    	            break;
    	        }
    	    }

    	    String nombre = txtNombre.getText().trim();
    	    boolean camposValidos = true;
    	    StringBuilder errores = new StringBuilder("Por favor corrige los siguientes campos:\n");
    	    Pattern soloMayusculasYNumeros = Pattern.compile("^[A-ZÁÉÍÓÚÑ0-9 ]+$");

    	    if (nombre.isEmpty() || !soloMayusculasYNumeros.matcher(nombre).matches() || nombre.length() > 2) {
    	        txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
    	        errores.append("Nombres (solo letras mayúsculas y números, máximo 2 caracteres)\n");
    	        camposValidos = false;
    	    } else {
    	        txtNombre.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
    	    }

    	    if (modeloLista.size() > 0) {
    	        listaAñadidos.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
    	    } else {
    	        listaAñadidos.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
    	        camposValidos = false;
    	        errores.append("Lista de alumnos no puede estar vacía\n");
    	    }

    	    if (docenteGrupo != null) {
    	        comboDocente.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
    	    } else {
    	        comboDocente.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
    	        camposValidos = false;
    	        errores.append("Debe seleccionar un docente\n");
    	    }

    	    if (asignaturaGrupo != null) {
    	        comboAsignatura.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
    	    } else {
    	        comboAsignatura.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
    	        camposValidos = false;
    	        errores.append("Debe seleccionar una asignatura\n");
    	    }

    	    if (camposValidos) {
    	        Grupo nuevoGrupo = new Grupo();
    	        nuevoGrupo.setNombre(nombre);
    	        nuevoGrupo.setDocente(docenteGrupo);
    	        nuevoGrupo.setAsignatura(asignaturaGrupo);
    	        nuevoGrupo.setEstudiantes(listaEstudiantesSeleccionados);
    	        mgm.add(nuevoGrupo);
    	        JOptionPane.showMessageDialog(modulo, "Grupo creado exitosamente");
    	        modulo.dispose();
    	        ModuloGrupoController mgc = new ModuloGrupoController();
    	        mgc.moduloGrupo();
    	    } else {
    	        JOptionPane.showMessageDialog(modulo, errores.toString(), "Errores de validación", JOptionPane.ERROR_MESSAGE);
    	    }
    	});

        

        modulo.revalidate();
        modulo.repaint();
	}
   
	public void modificar(Grupo grupo) {
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
    	lblAlumnos.setFont(new Font("Almarai-Bold", Font.PLAIN, 15));
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
    	lblMaestros.setFont(new Font("Almarai-Bold", Font.PLAIN, 15));
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
    	lblGrupos.setFont(new Font("Almarai-Bold", Font.PLAIN, 15));
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
    	lblAsignatura.setFont(new Font("Almarai-Bold", Font.PLAIN, 15));
    	moduloAsignatura.add(lblAsignatura);
    	options.add(moduloAsignatura);
    	
    	//Contenido
    	JPanel contenido = new JPanel();
    	contentPane.add(contenido, BorderLayout.CENTER);
    	contenido.setBackground(Color.WHITE);
    	contenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 15));
    	contenido.setLayout(new BorderLayout(0, 20));

    	JLabel lblTitulo = new JLabel("Modificación de grupo");
    	lblTitulo.setFont(new Font("Almarai-Bold", Font.BOLD, 24));
    	lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
    	contenido.add(lblTitulo, BorderLayout.NORTH);

    	JPanel Formulario = new JPanel();
    	Formulario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
    	Formulario.setBackground(Color.WHITE);
    	contenido.add(Formulario, BorderLayout.CENTER);

    	Formulario.setLayout(new GridBagLayout());
    	GridBagConstraints gbc = new GridBagConstraints();

    	JLabel lblDocente = new JLabel("Docente");
    	lblDocente.setFont(new Font("Arial", Font.BOLD, 14));

    	lblDocente.setFont(new Font("Almarai-Bold", Font.BOLD, 14));
    	JLabel lblAsignaturas = new JLabel("Asignatura");
    	lblAsignaturas.setFont(new Font("Arial", Font.BOLD, 14));

    	lblAsignaturas.setFont(new Font("Almarai-Bold", Font.BOLD, 14));
    	JLabel lblIdentificador = new JLabel("Identificador");
    	lblIdentificador.setFont(new Font("Arial", Font.BOLD, 14));

    	lblIdentificador.setFont(new Font("Almarai-Bold", Font.BOLD, 14));
    	JLabel lblNombre = new JLabel("Nombre");
    	lblNombre.setFont(new Font("Arial", Font.BOLD, 14));

    	lblNombre.setFont(new Font("Almarai-Bold", Font.BOLD, 14));
    	JLabel lblListaAlumnos = new JLabel("Lista de alumnos");
    	lblListaAlumnos.setFont(new Font("Almarai-Bold", Font.BOLD, 14));

    	JLabel lblAlumno = new JLabel("Alumno");
    	lblAlumno.setFont(new Font("Arial", Font.BOLD, 14));

    	JTextField txtIdentificador = new JTextField();
    	txtIdentificador.setEditable(false);
    	txtIdentificador.setPreferredSize(new Dimension(200, 30));
    	txtIdentificador.setBorder(BorderFactory.createLineBorder(borde, 2));

    	JTextField txtNombre = new JTextField();
    	txtNombre.setPreferredSize(new Dimension(200, 30));
    	txtNombre.setBorder(BorderFactory.createLineBorder(borde, 2));

    	JComboBox<String> comboAsignatura = new JComboBox<>();
    	comboAsignatura.setPreferredSize(new Dimension(200, 30));
    	comboAsignatura.setBackground(Color.WHITE);
    	comboAsignatura.setBorder(BorderFactory.createLineBorder(borde, 2));

    	JComboBox<String> comboDocente = new JComboBox<>();
    	comboDocente.setPreferredSize(new Dimension(200, 30));
    	comboDocente.setBackground(Color.WHITE);
    	comboDocente.setBorder(BorderFactory.createLineBorder(borde, 2));

    	JComboBox<String> comboAlumno = new JComboBox<>();
    	comboAlumno.setPreferredSize(new Dimension(200, 30));
    	comboAlumno.setBackground(Color.WHITE);
    	comboAlumno.setBorder(BorderFactory.createLineBorder(borde, 2));

    	for (Asignatura a : listaAsignaturas) {
    	    comboAsignatura.addItem(a.getNombre());
    	}
    	for (Docente d : listaDocentes) {
    	    comboDocente.addItem(d.getNombres());
    	}
    	for (Estudiante e: listaEstudiantes) {
    		String nombreCompleto = e.getNombres() + " " + e.getApellidos();
    	    comboAlumno.addItem(nombreCompleto);
    	}

    	DefaultListModel<String> modeloLista = new DefaultListModel<>();
    	ArrayList<Estudiante> listaEstudiantesSeleccionados = new ArrayList<>();

    	JList<String> listaAñadidos = new JList<>(modeloLista);
    	listaAñadidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	listaAñadidos.setBorder(BorderFactory.createLineBorder(borde, 2));
    	JScrollPane scrollLista = new JScrollPane(listaAñadidos);
    	scrollLista.setPreferredSize(new Dimension(500, 200));

    	ImageIcon iconAnadir = new ImageIcon(getClass().getResource("/imagenes/añadir (1).png"));
    	JButton btnAnadir = new JButton("Añadir", iconAnadir);
    	btnAnadir.setBackground(azul1);
    	btnAnadir.setForeground(Color.WHITE);
    	btnAnadir.setBorder(BorderFactory.createLineBorder(azulBorde, 2));
    	btnAnadir.setPreferredSize(new Dimension(100, 35));

    	ImageIcon iconEliminar = new ImageIcon(getClass().getResource("/imagenes/delete (1).png"));
    	JButton btnEliminar = new JButton(iconEliminar);
    	btnEliminar.setBackground(azul1);
    	btnEliminar.setBorder(BorderFactory.createLineBorder(azulBorde, 2));
    	btnEliminar.setPreferredSize(new Dimension(35, 35));

    	JButton btnCancelar = new JButton("Cancelar");
    	btnCancelar.setBackground(azulcan);
    	btnCancelar.setBackground(azulC);
    	btnCancelar.setForeground(Color.WHITE);
    	btnCancelar.setBorder(BorderFactory.createLineBorder(azulBorde, 2));
    	btnCancelar.setPreferredSize(new Dimension(100, 35));
    	btnCancelar.addActionListener(e->{
        	modulo.dispose();
        	ModuloGrupoController mgc= new ModuloGrupoController();
        	mgc.moduloGrupo();
        });

    	JButton btnCrear = new JButton("Modificar");
    	btnCrear.setBackground(azul1);
    	btnCrear.setForeground(Color.WHITE);
    	btnCrear.setBorder(BorderFactory.createLineBorder(azulBorde, 2));
    	btnCrear.setPreferredSize(new Dimension(100, 35));

    	//Precargado de datos
        for (int i = 0; i < comboAsignatura.getItemCount(); i++) {
            if (comboAsignatura.getItemAt(i).equals(grupo.getAsignatura().getNombre())) {
                comboAsignatura.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < comboDocente.getItemCount(); i++) {
            if (comboDocente.getItemAt(i).equals(grupo.getDocente().getNombres())) {
                comboDocente.setSelectedIndex(i);
                break;
            }
        }

        txtNombre.setText(grupo.getNombre());
        txtIdentificador.setText(String.valueOf(grupo.getId()));
        modeloLista.clear();
        listaEstudiantesSeleccionados.clear();

        for (Estudiante est : grupo.getEstudiantes()) {
            String nombreCompleto = est.getNombres() + " " + est.getApellidos();
            modeloLista.addElement(nombreCompleto);
            listaEstudiantesSeleccionados.add(est);
        }

    	gbc.gridx = 0; gbc.gridy = 0;
    	gbc.anchor = GridBagConstraints.WEST;
    	gbc.insets = new Insets(20, 20, 10, 10);
    	Formulario.add(lblDocente, gbc);

    	gbc.gridx = 1;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.weightx = 1.0;
    	gbc.insets = new Insets(20, 0, 10, 50);
    	Formulario.add(comboDocente, gbc);

    	gbc.gridx = 2;
    	gbc.fill = GridBagConstraints.NONE;
    	gbc.weightx = 0.0;
    	gbc.insets = new Insets(20, 50, 10, 10);
    	Formulario.add(lblAsignaturas, gbc);

    	gbc.gridx = 3;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.weightx = 1.0;
    	gbc.insets = new Insets(20, 0, 10, 20);
    	Formulario.add(comboAsignatura, gbc);

    	gbc.gridx = 0; gbc.gridy = 1;
    	gbc.fill = GridBagConstraints.NONE;
    	gbc.weightx = 0.0;
    	gbc.insets = new Insets(10, 20, 5, 10);
    	Formulario.add(lblIdentificador, gbc);

    	gbc.gridx = 1;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.weightx = 1.0;
    	gbc.insets = new Insets(10, 0, 5, 50);
    	Formulario.add(txtIdentificador, gbc);

    	gbc.gridx = 2;
    	gbc.fill = GridBagConstraints.NONE;
    	gbc.weightx = 0.0;
    	gbc.insets = new Insets(10, 50, 5, 10);
    	Formulario.add(lblNombre, gbc);

    	gbc.gridx = 3;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.weightx = 1.0;
    	gbc.insets = new Insets(10, 0, 5, 20);
    	Formulario.add(txtNombre, gbc);

    	gbc.gridx = 0; gbc.gridy = 3;
    	gbc.gridwidth = 4;
    	gbc.anchor = GridBagConstraints.WEST;
    	gbc.insets = new Insets(20, 20, 10, 10);
    	Formulario.add(lblListaAlumnos, gbc);

    	gbc.gridx = 0; gbc.gridy = 4;
    	gbc.gridwidth = 2;
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.weightx = 1.0;
    	gbc.weighty = 1.0;
    	gbc.insets = new Insets(0, 20, 20, 20);
    	Formulario.add(scrollLista, gbc);

    	JPanel panelAlumno = new JPanel();
    	panelAlumno.setBackground(Color.WHITE);
    	panelAlumno.setLayout(new GridBagLayout());
    	GridBagConstraints gbcAlumno = new GridBagConstraints();

    	gbcAlumno.gridx = 0; gbcAlumno.gridy = 0;
    	gbcAlumno.insets = new Insets(0, 0, 10, 0);
    	panelAlumno.add(lblAlumno, gbcAlumno);

    	gbcAlumno.gridy = 1;
    	panelAlumno.add(comboAlumno, gbcAlumno);

    	gbcAlumno.gridy = 2;
    	panelAlumno.add(btnAnadir, gbcAlumno);

    	gbcAlumno.gridy = 3;
    	panelAlumno.add(btnEliminar, gbcAlumno);

    	gbc.gridx = 2; gbc.gridy = 4;
    	gbc.gridwidth = 2;
    	gbc.fill = GridBagConstraints.NONE;
    	gbc.weightx = 0.0;
    	gbc.weighty = 0.0;
    	gbc.anchor = GridBagConstraints.NORTH;
    	gbc.insets = new Insets(0, 20, 20, 20);
    	Formulario.add(panelAlumno, gbc);

    	JPanel panelInferior = new JPanel();
    	panelInferior.setBackground(Color.WHITE);
    	panelInferior.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    	panelInferior.add(btnCancelar);
    	panelInferior.add(btnCrear);
    	contenido.add(panelInferior, BorderLayout.SOUTH);

    	btnAnadir.addActionListener(e -> {
    		 String nombreSeleccionado = (String) comboAlumno.getSelectedItem();
    		    if (nombreSeleccionado != null && !modeloLista.contains(nombreSeleccionado)) {
    		        modeloLista.addElement(nombreSeleccionado);

    		        for (Estudiante est : listaEstudiantes) {
    		            String nombreCompleto = est.getNombres() + " " + est.getApellidos();
    		            if (nombreCompleto.equals(nombreSeleccionado)) {
    		                listaEstudiantesSeleccionados.add(est);
    		                System.out.println("Estudiante agregado: " + nombreCompleto);
    		                break;
    		            }
    		        }
    		    } else if (modeloLista.contains(nombreSeleccionado)) {
    		        JOptionPane.showMessageDialog(modulo, "El alumno ya está en la lista");
    		    }
    	});

    	btnEliminar.addActionListener(e -> {
    	    int indiceSeleccionado = listaAñadidos.getSelectedIndex();
    	    if (indiceSeleccionado != -1) {
    	        String alumnoEliminado = modeloLista.getElementAt(indiceSeleccionado);
    	        modeloLista.removeElementAt(indiceSeleccionado);
    	        System.out.println("Alumno eliminado: " + alumnoEliminado);
    	    } else {
    	        JOptionPane.showMessageDialog(modulo, "Seleccione un alumno para eliminar");
    	    }
    	});

    	btnCancelar.addActionListener(e -> {
	        ModuloGrupoController mgc = new ModuloGrupoController();
	        modulo.dispose();
	        mgc.moduloGrupo();
    	});

    	btnCrear.addActionListener(e -> {
    		
    	    String nombreAsignaturaSeleccionada = (String) comboAsignatura.getSelectedItem();
    	    Asignatura asignaturaGrupo = null;
    	    for (Asignatura a : listaAsignaturas) {
    	        if (a.getNombre().equals(nombreAsignaturaSeleccionada)) {
    	            asignaturaGrupo = a;
    	            break;
    	        }
    	    }

    	    String nombreDocenteSeleccionado = (String) comboDocente.getSelectedItem();
    	    Docente docenteGrupo = null;
    	    for (Docente d : listaDocentes) {
    	        if (d.getNombres().equals(nombreDocenteSeleccionado)) {
    	            docenteGrupo = d;
    	            break;
    	        }
    	    }

    	    String nombre = txtNombre.getText().trim();
    	    boolean camposValidos = true;
    	    StringBuilder errores = new StringBuilder("Por favor corrige los siguientes campos:\n");
    	    Pattern soloMayusculasYNumeros = Pattern.compile("^[A-ZÁÉÍÓÚÑ0-9 ]+$");

    	    if (nombre.isEmpty() || !soloMayusculasYNumeros.matcher(nombre).matches() || nombre.length() > 2) {
    	        txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
    	        errores.append("Nombres (solo letras mayúsculas y números, máximo 2 caracteres)\n");
    	        camposValidos = false;
    	    } else {
    	        txtNombre.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
    	    }

    	    if (modeloLista.size() > 0) {
    	        listaAñadidos.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
    	    } else {
    	        listaAñadidos.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
    	        camposValidos = false;
    	        errores.append("Lista de alumnos no puede estar vacía\n");
    	    }

    	    if (docenteGrupo != null) {
    	        comboDocente.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
    	    } else {
    	        comboDocente.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
    	        camposValidos = false;
    	        errores.append("Debe seleccionar un docente\n");
    	    }

    	    if (asignaturaGrupo != null) {
    	        comboAsignatura.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
    	    } else {
    	        comboAsignatura.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
    	        camposValidos = false;
    	        errores.append("Debe seleccionar una asignatura\n");
    	    }

    	    if (camposValidos) {
    	        Grupo grupoMod = new Grupo();
    	        grupoMod.setNombre(nombre);
    	        grupoMod.setDocente(docenteGrupo);
    	        grupoMod.setAsignatura(asignaturaGrupo);
    	        grupoMod.setEstudiantes(listaEstudiantesSeleccionados);
    	        mgm.update(grupo.getId(),grupoMod);
    	        JOptionPane.showMessageDialog(modulo, "Grupo mdoificado exitosamente");
    	        modulo.dispose();
    	        ModuloGrupoController mgc = new ModuloGrupoController();
    	        mgc.moduloGrupo();
    	    } else {
    	        JOptionPane.showMessageDialog(modulo, errores.toString(), "Errores de validación", JOptionPane.ERROR_MESSAGE);
    	    }
    	});

        

        modulo.revalidate();
        modulo.repaint();
	}

	
	
    
    public void detalles(Grupo grupo) {
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
    	lblAlumnos.setFont(new Font("Almarai-Bold", Font.PLAIN, 15));
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
    	lblMaestros.setFont(new Font("Almarai-Bold", Font.PLAIN, 15));
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
    	lblGrupos.setFont(new Font("Almarai-Bold", Font.PLAIN, 15));
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
    	lblAsignatura.setFont(new Font("Almarai-Bold", Font.PLAIN, 15));
    	moduloAsignatura.add(lblAsignatura);
    	options.add(moduloAsignatura);

    	JPanel contenido = new JPanel();
    	contentPane.add(contenido, BorderLayout.CENTER);
    	contenido.setBackground(Color.WHITE);
    	contenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 15));
    	contenido.setLayout(new BorderLayout(70, 20));

    	JLabel lblTitulo = new JLabel("Detalles del grupo");
    	lblTitulo.setFont(new Font("Almarai-Bold", Font.BOLD, 20));
    	lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
    	contenido.add(lblTitulo, BorderLayout.NORTH);

    	JPanel Formulario = new JPanel();
    	Formulario.setBorder(BorderFactory.createLineBorder(Color.black, 4));
    	Formulario.setBackground(Color.white);
    	contenido.add(Formulario, BorderLayout.CENTER);

    	GridBagLayout gbl_Formulario = new GridBagLayout();
    	Formulario.setLayout(gbl_Formulario);

    	JLabel lblDocente = new JLabel("Docente");
    	lblDocente.setFont(new Font("Almarai-Bold", Font.PLAIN, 12));

    	JLabel lblAsignaturas = new JLabel("Asignatura");
    	lblAsignaturas.setFont(new Font("Almarai-Bold", Font.PLAIN, 12));

    	JLabel lblIdentificador = new JLabel("Identificador");
    	lblIdentificador.setFont(new Font("Almarai-Bold", Font.PLAIN, 12));

    	JLabel lblNombre = new JLabel("Nombre");
    	lblNombre.setFont(new Font("Almarai-Bold", Font.PLAIN, 12));

    	JLabel lblListaAlumnos = new JLabel("Lista de alumnos");
    	lblListaAlumnos.setFont(new Font("Almarai-Bold", Font.PLAIN, 12));

    	JLabel lblValorDocente = new JLabel();
    	lblValorDocente.setFont(new Font("Almarai-Bold", Font.PLAIN, 12));
    	lblValorDocente.setOpaque(true);
    	lblValorDocente.setBackground(Color.WHITE);
    	lblValorDocente.setBorder(BorderFactory.createLineBorder(borde, 4));
    	lblValorDocente.setPreferredSize(new Dimension(200, 28));
    	Docente docente = grupo.getDocente();
    	if (docente != null) {
    		String docenten = docente.getNombres()+" "+docente.getApellidos();
    		lblValorDocente.setText(docenten);
    	} else {
    		lblValorDocente.setText("No disponible");
    	}

    	
    	   
    	JLabel lblValorAsignatura = new JLabel();
    	lblValorAsignatura.setFont(new Font("Almarai-Bold", Font.PLAIN, 12));
    	lblValorAsignatura.setOpaque(true);
    	lblValorAsignatura.setBackground(Color.WHITE);
    	lblValorAsignatura.setBorder(BorderFactory.createLineBorder(borde, 4));
    	lblValorAsignatura.setPreferredSize(new Dimension(200, 28));
    	Asignatura asignatura = grupo.getAsignatura();
    	if (asignatura != null) {
    		String asignaturan = asignatura.getNombre();
    	    lblValorAsignatura.setText(asignaturan);
    	} else {
    	    lblValorAsignatura.setText("No disponible");
    	}

    	JLabel lblValorIdentificador = new JLabel(String.valueOf(grupo.getId()));
    	lblValorIdentificador.setFont(new Font("Almarai-Bold", Font.PLAIN, 12));
    	lblValorIdentificador.setOpaque(true);
    	lblValorIdentificador.setBackground(Color.WHITE);
    	lblValorIdentificador.setBorder(BorderFactory.createLineBorder(borde, 4));
    	lblValorIdentificador.setPreferredSize(new Dimension(200, 28));

    	JLabel lblValorNombre = new JLabel(grupo.getNombre());
    	lblValorNombre.setFont(new Font("Almarai-Bold", Font.PLAIN, 12));
    	lblValorNombre.setOpaque(true);
    	lblValorNombre.setBackground(Color.WHITE);
    	lblValorNombre.setBorder(BorderFactory.createLineBorder(borde, 4));
    	lblValorNombre.setPreferredSize(new Dimension(200, 28));

    	DefaultListModel<Estudiante> modelo = new DefaultListModel<>();
    	System.out.println("Cantidad de estudiantes: " + grupo.getEstudiantes().size());
    	for (Estudiante e : grupo.getEstudiantes()) {
    	    modelo.addElement(e);
    	    System.out.println("Estudiante: " + e.getNombres());
    	}

    	JList<Estudiante> listaAñadidos = new JList<>(modelo);
    	listaAñadidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	listaAñadidos.setCellRenderer(new DefaultListCellRenderer() {
    	    @Override
    	    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    	        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    	        if (value instanceof Estudiante) {
    	            Estudiante estudiante = (Estudiante) value;
    	            setText(estudiante.getNombres()+" "+estudiante.getApellidos());
    	            setHorizontalAlignment(SwingConstants.CENTER);
    	        }
    	        
    	        return this;
    	    }
    	});

    	listaAñadidos.setBorder(BorderFactory.createLineBorder(borde, 4));
    	JScrollPane scrollLista = new JScrollPane(listaAñadidos);

    	JButton btnCancelar = new JButton("Cancelar");
    	btnCancelar.setBackground(azulC);
    	btnCancelar.setBorder(BorderFactory.createLineBorder(azul2, 5));
    	btnCancelar.setForeground(Color.WHITE);
    	btnCancelar.setPreferredSize(new Dimension(100, 35));

    	JButton btnCrear = new JButton("Descargar PDF");
    	btnCrear.setBackground(azul1);
    	btnCrear.setBorder(BorderFactory.createLineBorder(azulBorde, 5));
    	btnCrear.setForeground(Color.WHITE);
    	btnCrear.setPreferredSize(new Dimension(130, 35));

    	GridBagConstraints gbc = new GridBagConstraints();

    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	gbc.anchor = GridBagConstraints.WEST;
    	gbc.insets = new Insets(20, 20, 5, 5);
    	Formulario.add(lblDocente, gbc);

    	gbc.gridx = 1;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.weightx = 1.0;
    	gbc.insets = new Insets(20, 0, 5, 5);
    	Formulario.add(lblValorDocente, gbc);

    	gbc.gridx = 3;
    	gbc.anchor = GridBagConstraints.WEST;
    	gbc.insets = new Insets(20, 0, 5, 5);
    	Formulario.add(lblAsignaturas, gbc);

    	gbc.gridx = 4;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.weightx = 1.0;
    	gbc.insets = new Insets(20, 0, 5, 5);
    	Formulario.add(lblValorAsignatura, gbc);

    	gbc.gridx = 0;
    	gbc.gridy = 1;
    	gbc.anchor = GridBagConstraints.WEST;
    	gbc.insets = new Insets(20, 20, 5, 5);
    	Formulario.add(lblIdentificador, gbc);

    	gbc.gridx = 1;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.weightx = 1.0;
    	gbc.insets = new Insets(20, 0, 5, 5);
    	Formulario.add(lblValorIdentificador, gbc);

    	gbc.gridx = 3;
    	gbc.anchor = GridBagConstraints.WEST;
    	gbc.insets = new Insets(10, 20, 5, 5);
    	Formulario.add(lblNombre, gbc);

    	gbc.gridx = 4;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.weightx = 1.0;
    	gbc.insets = new Insets(10, 0, 5, 5);
    	Formulario.add(lblValorNombre, gbc);

    	gbc.gridx = 2;
    	gbc.gridy = 2;
    	gbc.fill = GridBagConstraints.NONE;
    	gbc.weightx = 0.0;
    	gbc.anchor = GridBagConstraints.WEST;
    	gbc.insets = new Insets(20, 20, 5, 5);
    	Formulario.add(lblListaAlumnos, gbc);

    	gbc.gridx = 0;
    	gbc.gridy = 3;
    	gbc.gridwidth = 6;
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.weightx = 1.0;
    	gbc.weighty = 1.0;
    	gbc.insets = new Insets(0, 40, 20, 40);
    	Formulario.add(scrollLista, gbc);

    	JPanel panelInferior = new JPanel();
    	panelInferior.setBackground(Color.white);
    	panelInferior.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    	panelInferior.add(btnCancelar);
    	panelInferior.add(btnCrear);
    	contenido.add(panelInferior, BorderLayout.SOUTH);

    	btnCancelar.addActionListener(e -> {
	   
	        ModuloGrupoController mgc = new ModuloGrupoController();
	        modulo.dispose();
	        mgc.moduloGrupo();
    	    
    	});

    	btnCrear.addActionListener(e -> {
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
        	    ModuloGrupoModel mgm = new ModuloGrupoModel();
        	    System.out.println(ruta);
        	    mgm.descargarPdf(ruta, grupo);
        	}    	});

    	contenido.revalidate();
    	contenido.repaint();

    }

    //metodos para agregar y mostrar estudiantes asignaturas y docentes
    
    public void detallesE(Estudiante estudiante) {
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

    public void detallesD(Docente docente) {
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
        txtId.setText(String.valueOf(docente.getId()));
        txtNombres.setText(docente.getNombres());
        txtApellidos.setText(docente.getApellidos());
        txtCorreo.setText(docente.getCorreo());
        txtCurp.setText(docente.getCurp());
        txtFecha.setText(String.valueOf(docente.getFechaNacimiento()));
        txtGenero.setText(docente.getGenero());
        txtTelefono.setText(docente.getTelefono());
        txtGrado.setText(docente.getGradoEstudios());
        txtDomicilio.setText(docente.getDomicilio());
       
        if (docente.getFoto() != null) {
            ImageIcon icon = new ImageIcon(docente.getFoto()); 
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
        	ModuloDocenteController mdc = new ModuloDocenteController();
        	modulo.dispose();
        	mdc.moduloDocente();
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
        	    ModuloDocenteModel mdm = new ModuloDocenteModel();
        	    System.out.println(ruta);
        	    mdm.descargarInformacion(ruta, docente);
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
    
    public void detallesA(Asignatura asignatura, Grupo grupo) {
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

		
		// contenido
		JPanel contenido = new JPanel(new BorderLayout());
		contenido.setBackground(Color.WHITE);
		contenido.setBorder(new EmptyBorder(20, 40, 20, 40));

		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(Color.WHITE);
		JLabel lblTitulo = new JLabel("Detalles de asignaturas", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Almarai-Bold", Font.BOLD, 24));

		JPanel panelFormulario = new JPanel(new GridBagLayout());
		panelFormulario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelFormulario.setBackground(Color.WHITE);
		GridBagConstraints d = new GridBagConstraints();

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Almarai-Bold", Font.BOLD, 14));

		JLabel lblValorNombre = new JLabel(asignatura.getNombre());

		JLabel lblIdentificador = new JLabel("Identificador");
		lblIdentificador.setFont(new Font("Almarai-Bold", Font.BOLD, 14));

		JLabel lblValorID = new JLabel(String.valueOf(asignatura.getId()));

		JLabel lblGruposAsignados = new JLabel("Grupos asignados");
		lblGruposAsignados.setFont(new Font("Almarai-Bold", Font.BOLD, 14));

		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setFont(new Font("Almarai-Bold", Font.BOLD, 14));

		JTextArea areaDescripcion = new JTextArea(asignatura.getDescripcion());
		areaDescripcion.setLineWrap(true);
		areaDescripcion.setWrapStyleWord(true);
		areaDescripcion.setEditable(false);
		areaDescripcion.setFont(new Font("Almarai-Bold", Font.PLAIN, 12));
		areaDescripcion.setBorder(BorderFactory.createLineBorder(borde,4));

		ArrayList<Grupo> grupos = asignatura.getGrupos();
		List<String> nombresGrupos = new ArrayList<>();
		for (Grupo g : grupos) {
		    nombresGrupos.add(g.getNombre());
		}
		JList<String> listaGrupos = new JList<>(nombresGrupos.toArray(new String[0]));
		listaGrupos.setFont(new Font("Almarai-Bold", Font.PLAIN, 12));
		listaGrupos.setBorder(BorderFactory.createLineBorder(borde, 4));
		JScrollPane scrollGrupos = new JScrollPane(listaGrupos);

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 20));
		panelBotones.setBackground(Color.WHITE);

		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setBackground(azulcan);
		btnRegresar.setForeground(Color.WHITE);

		JButton btnDescargar = new JButton("Descargar PDF");
		btnDescargar.setBackground(azul1);
		btnDescargar.setForeground(Color.WHITE);

		btnRegresar.addActionListener(e -> {
		    ModuloAsignaturaController mac = new ModuloAsignaturaController();
		    modulo.dispose();
		    mac.moduloAsignatura();
		});
		btnDescargar.addActionListener(e->{
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
        	    ModuloAsignaturaModel mam = new ModuloAsignaturaModel();
        	    System.out.println(ruta);
        	    mam.descargarPdf(ruta, asignatura,grupo.getId());
        	}
		});

		panelTitulo.add(lblTitulo);

		d.insets = new Insets(15, 15, 10, 15);
		d.gridx = 0; d.gridy = 0; d.anchor = GridBagConstraints.WEST;
		panelFormulario.add(lblNombre, d);

		d.gridx = 1; d.anchor = GridBagConstraints.CENTER;
		panelFormulario.add(lblValorNombre, d);

		d.gridx = 2;
		panelFormulario.add(lblIdentificador, d);

		d.gridx = 3;
		panelFormulario.add(lblValorID, d);

		d.gridx = 4;
		panelFormulario.add(lblGruposAsignados, d);

		d.gridx = 0; d.gridy = 1; d.gridwidth = 5;
		d.insets = new Insets(20, 15, 5, 15);
		d.anchor = GridBagConstraints.CENTER;
		panelFormulario.add(lblDescripcion, d);

		d.gridy = 2; d.gridx = 0; d.gridwidth = 3;
		d.fill = GridBagConstraints.BOTH;
		d.weightx = 1; d.weighty = 1;
		panelFormulario.add(areaDescripcion, d);

		d.gridx = 4; d.gridwidth = 1; d.weightx = 0.3;
		panelFormulario.add(scrollGrupos, d);

		panelBotones.add(btnRegresar);
		panelBotones.add(btnDescargar);

		contenido.add(panelTitulo, BorderLayout.NORTH);
		contenido.add(panelFormulario, BorderLayout.CENTER);
		contenido.add(panelBotones, BorderLayout.SOUTH);

		modulo.add(contenido);
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
	        	int filaSeleccionada = tabla.convertRowIndexToModel(tabla.getSelectedRow());
                if (filaSeleccionada >= 0) {
                	Grupo gSeleccionado = listaGrupos.get(filaSeleccionada);
		        	ModuloGrupoController mgc = new ModuloGrupoController();
					modulo.dispose();
					mgc.datosGenerales(gSeleccionado);
		            fireEditingStopped();
                }
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
            
            int filaSeleccionada = tabla.convertRowIndexToModel(tabla.getSelectedRow());
            if (filaSeleccionada >= 0) {
            	Grupo gSeleccionado = listaGrupos.get(filaSeleccionada);
            	
	            editar.addActionListener(e -> {
	                ModuloGrupoController mgc = new ModuloGrupoController();
	                modulo.dispose();
	                mgc.modificar(gSeleccionado);
	            });
	
	            borrar.addActionListener(e -> {
	            	
	            	int n = JOptionPane.showConfirmDialog(
	    		            null,
	    		            "Estas seguro que quieres eliminar este registro?",
	    		            "Eliminar",
	    		            JOptionPane.YES_NO_OPTION);

	    		        if(n==0){
	    		        	ModuloGrupoModel mgm = new ModuloGrupoModel();
	    		        	mgm.delete(gSeleccionado.getId());
							((DefaultTableModel) tabla.getModel()).removeRow(row);
							JOptionPane.showMessageDialog(null, "Fila eliminada " + (row + 1));
							fireEditingStopped();
							System.out.println(borrar.getSize());
	    		        }
	               
	            });
            }

            panel.add(editar);
            panel.add(borrar);
            panel.repaint();
            return panel;
        }

        public Object getCellEditorValue() {
            return "";
        }
   	}
    
}