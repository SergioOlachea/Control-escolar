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
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
import view.ModuloDocenteView.PanelBotonesEditor;
import view.ModuloDocenteView.PanelBotonesRenderer;

public class ModuloAsignturaView {

	Color borde = new Color(206, 207, 202);
	Color azul2 = new Color(52, 134, 199);
	Color azul1 = new Color(54, 146, 218);
	Color azulBorde= new Color(101, 166, 217);
	Color azulcan= new Color(40, 103, 152);
	public void ModuloAsignatura() {
		Color borde = new Color(206, 207, 202);
		Color azul2 = new Color(52, 134, 199);
		Color azul1 = new Color(54, 146, 218);
		Color azulBorde= new Color(101, 166, 217);
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
		
		JLabel lblInicio = new JLabel("Modulo asignatura");
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
		            JOptionPane.showMessageDialog(null,"HOLAAAA");
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
		moduloAsignatura.setBackground(azulBorde);
		
		ImageIcon iconAsignatura = new ImageIcon (this.getClass().getResource("/imagenes/asignaturas (1).png"));
		moduloAsignatura.setLayout(new BoxLayout(moduloAsignatura, BoxLayout.Y_AXIS));
		
		JButton btnAsignatura = new JButton(iconAsignatura);
		btnAsignatura.setBorder(null);
		btnAsignatura.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAsignatura.setBackground(azulBorde);
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
		
		JLabel lblRegistroAlumnos = new JLabel("Registro de alumnos");
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
			ModuloAsignaturaController mac = new ModuloAsignaturaController();
			modulo.dispose();
			mac.crear();
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
        
        // Tabla
        String[] columnas = {"Identificador", "Nombre", "Grupos asignados", "Detalles de la asignatura", "Opciones"};
        Object[][] datos = new Object[10][columnas.length];

        for (int i = 0; i < 10; i++) {
            datos[i][0] = String.format("%03d", i + 1);
            datos[i][1] = "Programación III";
            datos[i][2] = "4A 4B 4C";
            datos[i][3] = "Datos completos";
            datos[i][4] = "Opciones";
        }

        DefaultTableModel model = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 3;
            }
        };

        JTable tabla = new JTable(model);
        tabla.setRowHeight(30);

        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);
        tabla.getColumn("Identificador").setCellRenderer(centrado);
        tabla.getColumn("Nombre").setCellRenderer(centrado);
        tabla.getColumn("Grupos asignados").setCellRenderer(centrado);

        tabla.getColumnModel().getColumn(0).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(130);

        tabla.getColumn("Detalles de la asignatura").setCellRenderer(new BotonRenderer("Datos completos"));
        tabla.getColumn("Detalles de la asignatura").setCellEditor(new BotonEditor(new JCheckBox(), "Datos completos", tabla,modulo));
        
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
		Color azulC = new Color(40, 103, 152);
		Color azulBorde= new Color(101, 166, 217);
		JFrame crear = new JFrame();
		crear.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		crear.setBounds(100, 100, 982, 662);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		crear.setLocationRelativeTo(null);

		crear.setVisible(true);
		crear.setContentPane(contentPane);
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
		header.add(logoUabcs);
		
		header.add(Box.createRigidArea(new Dimension(50,0)));
		
		JLabel lblInicio = new JLabel("Modulo asignatura");
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
		            crear.dispose();
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
		crear.add(options, BorderLayout.WEST);
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
			crear.dispose();
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
			crear.dispose();
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
			crear.dispose();
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
		moduloAsignatura.setBackground(azulBorde);
		
		ImageIcon iconAsignatura = new ImageIcon (this.getClass().getResource("/imagenes/asignaturas (1).png"));
		moduloAsignatura.setLayout(new BoxLayout(moduloAsignatura, BoxLayout.Y_AXIS));
		
		JButton btnAsignatura = new JButton(iconAsignatura);
		btnAsignatura.setBorder(null);
		btnAsignatura.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAsignatura.setBackground(azulBorde);
		btnAsignatura.addActionListener(e->{
			ModuloAsignaturaController mac= new ModuloAsignaturaController();
			crear.dispose();
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
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		contenido.add(panel, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("Creación de asignaturas");
		lblTitulo.setFont(new Font("Almarai-Bold", Font.BOLD, 30));
		panel.add(lblTitulo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_1.setBackground(Color.white);
		contenido.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Almarai-Bold", Font.BOLD, 14));
		lblNombre.setBounds(88, 37, 65, 29);
		panel_1.add(lblNombre);
		
		JTextField txtNombre = new JTextField();
		txtNombre.setBounds(148, 39, 205, 29);
		txtNombre.setBorder(BorderFactory.createLineBorder(borde,5));
		txtNombre.setColumns(10);
		panel_1.add(txtNombre);
		
		JLabel lblIdentificador = new JLabel("Identificador");
		lblIdentificador.setFont(new Font("Almarai-Bold", Font.BOLD, 14));
		lblIdentificador.setBounds(442, 37, 95, 29);
		panel_1.add(lblIdentificador);
		
		JTextField txtIdentificador = new JTextField();
		txtIdentificador.setColumns(10);
		txtIdentificador.setBorder(BorderFactory.createLineBorder(borde,5));
		txtIdentificador.setBounds(534, 39, 205, 29);
		panel_1.add(txtIdentificador);
		
		
		JLabel descriptionTag = new JLabel("Descripción");
		descriptionTag.setFont(new Font("Almarai-Bold", Font.BOLD, 14));
		descriptionTag.setBounds(364, 98, 84, 29);
		panel_1.add(descriptionTag);
		
		JTextPane descriptionInput = new JTextPane();
		descriptionInput.setBorder(BorderFactory.createLineBorder(borde,5));
		descriptionInput.setBounds(88, 135, 651, 260);
		panel_1.add(descriptionInput);
		
		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelBotones.setBackground(Color.white);
		
        JButton btnRegresar = new JButton("Cancelar");
        btnRegresar.setBackground(azulC);
        btnRegresar.setForeground(Color.white);
        btnRegresar.setBorder(BorderFactory.createLineBorder(azul2,5));
        btnRegresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRegresar.addActionListener(e -> {
			ModuloAsignaturaController mac = new ModuloAsignaturaController();
        	crear.dispose();
        	mac.moduloAsignatura();
		});
        panelBotones.add(btnRegresar);
        
        
        JButton btnCrear = new JButton("Crear");
        btnCrear.setBackground(azul1);
        btnCrear.setForeground(Color.white);
        btnCrear.setBorder(BorderFactory.createLineBorder(azulBorde,5));
        btnCrear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelBotones.add(btnCrear);
        btnCrear.addActionListener(e->{
        	String nombre = txtNombre.getText().trim();
        	String descripcion = descriptionInput.getText().trim();
        	
        	 Pattern soloLetras = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
        	 
        	 StringBuilder errores = new StringBuilder("Por favor corrige los siguientes campos:\n");
        	 boolean camposValidos=true;
        	 if (nombre.isEmpty() || !soloLetras.matcher(nombre).matches()) {
		            txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		            errores.append("Nombres (solo letras)\n");
		            camposValidos = false;
		        } else {
		            txtNombre.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		        }

		        if (descripcion.isEmpty() || !soloLetras.matcher(descripcion).matches()) {
		        	descriptionInput.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		            errores.append("Descripcion (solo letras)\n");
		            camposValidos = false;
		        } else {
		        	descriptionInput.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		        }
		        if (camposValidos) {
		        	
		        	JOptionPane.showMessageDialog(null, "Asignatura creada correctamente.");
		            ModuloAsignaturaController mec= new ModuloAsignaturaController();
		            crear.dispose();
		            mec.moduloAsignatura();
		        } else {
		            JOptionPane.showMessageDialog(null, errores.toString(), "Campos inválidos", JOptionPane.WARNING_MESSAGE);
		        }
		        
        	
        });
        
		contenido.add(panelBotones, BorderLayout.SOUTH);

	}
	
	 public void modificar() {
			Color azulC = new Color(40, 103, 152);
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

	    	JLabel lblInicio = new JLabel("Modulo asignatura");
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
			moduloAsignatura.setBackground(azulBorde);
			
			ImageIcon iconAsignatura = new ImageIcon (this.getClass().getResource("/imagenes/asignaturas (1).png"));
			moduloAsignatura.setLayout(new BoxLayout(moduloAsignatura, BoxLayout.Y_AXIS));
			
			JButton btnAsignatura = new JButton(iconAsignatura);
			btnAsignatura.setBorder(null);
			btnAsignatura.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnAsignatura.setBackground(azulBorde);
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
			
			// panel contenido
			JPanel contenido = new JPanel();
			contentPane.add(contenido, BorderLayout.CENTER);
			contenido.setBackground(Color.white);
			contenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 0));
			System.out.println(contenido.getHeight());
			contenido.setLayout(new BorderLayout(0, 0));
			
			JPanel panel = new JPanel();
			panel.setBackground(Color.white);
			contenido.add(panel, BorderLayout.NORTH);
			
			JLabel lblNewLabel = new JLabel("Modificación de asignaturas");
			lblNewLabel.setFont(new Font("Almarai-Bold", Font.BOLD, 30));
			panel.add(lblNewLabel);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panel_1.setBackground(Color.white);
			contenido.add(panel_1, BorderLayout.CENTER);
			panel_1.setLayout(null);
			
			JLabel nameTag = new JLabel("Nombre");
			nameTag.setFont(new Font("Almarai-Bold", Font.BOLD, 14));
			nameTag.setBounds(88, 37, 65, 29);
			panel_1.add(nameTag);
			
			JTextField nameInput = new JTextField();
			nameInput.setBounds(148, 39, 205, 29);
			nameInput.setBorder(BorderFactory.createLineBorder(borde,5));
			nameInput.setColumns(10);
			panel_1.add(nameInput);
			
			JLabel identifierTag = new JLabel("Identificador");
			identifierTag.setFont(new Font("Almarai-Bold", Font.BOLD, 14));
			identifierTag.setBounds(442, 37, 95, 29);
			panel_1.add(identifierTag);
			
			JTextField identifierInput = new JTextField();
			identifierInput.setColumns(10);
			identifierInput.setBorder(BorderFactory.createLineBorder(borde,5));
			identifierInput.setBounds(534, 39, 205, 29);
			panel_1.add(identifierInput);
			
			
			JLabel descriptionTag = new JLabel("Descripción");
			descriptionTag.setFont(new Font("Almarai-Bold", Font.BOLD, 14));
			descriptionTag.setBounds(364, 98, 84, 29);
			panel_1.add(descriptionTag);
			
			JTextPane descriptionInput = new JTextPane();
			descriptionInput.setBorder(BorderFactory.createLineBorder(borde,5));
			descriptionInput.setBounds(88, 135, 651, 260);
			panel_1.add(descriptionInput);
			
			JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			panelBotones.setBackground(Color.white);
			
	        JButton btnRegresar = new JButton("Cancelar");
	        btnRegresar.setBackground(azulC);
	        btnRegresar.setForeground(Color.white);
	        btnRegresar.setBorder(BorderFactory.createLineBorder(azul2,5));
	        btnRegresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        btnRegresar.addActionListener(e -> {
				ModuloAsignaturaController mac = new ModuloAsignaturaController();
				modulo.dispose();
	        	mac.moduloAsignatura();
			});
	        panelBotones.add(btnRegresar);
	        
	        
	        JButton btnCrear = new JButton("Crear");
	        btnCrear.setBackground(azul1);
	        btnCrear.setForeground(Color.white);
	        btnCrear.setBorder(BorderFactory.createLineBorder(azulBorde,5));
	        btnCrear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        panelBotones.add(btnCrear);
	        
			contenido.add(panelBotones, BorderLayout.SOUTH);
	    }
	    
	 public void datos() {
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

	    	JLabel lblInicio = new JLabel("Modulo asignatura");
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
			moduloAsignatura.setBackground(azulBorde);
			
			ImageIcon iconAsignatura = new ImageIcon (this.getClass().getResource("/imagenes/asignaturas (1).png"));
			moduloAsignatura.setLayout(new BoxLayout(moduloAsignatura, BoxLayout.Y_AXIS));
			
			JButton btnAsignatura = new JButton(iconAsignatura);
			btnAsignatura.setBorder(null);
			btnAsignatura.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnAsignatura.setBackground(azulBorde);
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
			lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

			JPanel panelFormulario = new JPanel(new GridBagLayout());
			panelFormulario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
			panelFormulario.setBackground(Color.WHITE);
			GridBagConstraints d = new GridBagConstraints();

			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setFont(new Font("Arial", Font.BOLD, 14));

			JLabel lblValorNombre = new JLabel("Programación III");

			JLabel lblIdentificador = new JLabel("Identificador");
			lblIdentificador.setFont(new Font("Arial", Font.BOLD, 14));

			JLabel lblValorID = new JLabel("002");

			JLabel lblGruposAsignados = new JLabel("Grupos asignados");
			lblGruposAsignados.setFont(new Font("Arial", Font.BOLD, 14));

			JLabel lblDescripcion = new JLabel("Descripción");
			lblDescripcion.setFont(new Font("Arial", Font.BOLD, 14));

			JTextArea areaDescripcion = new JTextArea("Descripción de la asignatura");
			areaDescripcion.setLineWrap(true);
			areaDescripcion.setWrapStyleWord(true);
			areaDescripcion.setEditable(false);
			areaDescripcion.setFont(new Font("Arial", Font.PLAIN, 12));
			areaDescripcion.setBorder(BorderFactory.createLineBorder(borde,4));

			String[] grupos = {"4A", "4B", "4C"};
			JList<String> listaGrupos = new JList<>(grupos);
			listaGrupos.setFont(new Font("Arial", Font.PLAIN, 12));
			listaGrupos.setBorder(BorderFactory.createLineBorder(borde,4));
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

		class BotonEditor extends DefaultCellEditor {
		    protected JButton boton;
		    private String texto;
		    JTable tabla;
		    private JFrame modulo;

		    public BotonEditor(JCheckBox checkBox, String texto, JTable tabla, JFrame modulo) {
		        super(checkBox);
		        this.tabla = tabla;
		        this.texto = texto;
		        this.modulo=modulo;
		        boton = new JButton(texto);
		        boton.setForeground(Color.BLUE);
		        boton.setBorderPainted(false);
		        boton.setContentAreaFilled(false);

		        boton.addActionListener(e -> {
		            ModuloAsignaturaController mac= new ModuloAsignaturaController();
		            modulo.dispose();
		            mac.datos();
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
	                ModuloAsignaturaController mac = new ModuloAsignaturaController();
	                modulo.dispose();
	                mac.modificar();
	                
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
	    DefaultTableCellRenderer centroAzul = new DefaultTableCellRenderer() {
	        @Override
	        public Component getTableCellRendererComponent(JTable table, Object value,
	                boolean isSelected, boolean hasFocus, int row, int column) {
	            
	            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	            label.setHorizontalAlignment(SwingConstants.CENTER);
	            label.setForeground(Color.BLUE);
	            return label;
	        }
	    };
}
