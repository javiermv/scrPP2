package com.ungs.pp2.scrPP2.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import com.ungs.pp2.scrPP2.Consulta.Consulta;
import com.ungs.pp2.scrPP2.Controller.AltaUserStoryController;
import com.ungs.pp2.scrPP2.Controller.BurndownChartController;
import com.ungs.pp2.scrPP2.Controller.HomeController;
import com.ungs.pp2.scrPP2.Controller.UserStoryPaginadoController;
import com.ungs.pp2.scrPP2.Dominio.Comando.AgregarOkListenerBacklogNuevo;
import com.ungs.pp2.scrPP2.Dominio.Comando.AgregarSiguienteListenerProyectoNuevo;
import com.ungs.pp2.scrPP2.Dominio.Comando.LimpiarBacklogNuevoView;
import com.ungs.pp2.scrPP2.Dominio.Comando.LimpiarProyectoNuevoView;
import com.ungs.pp2.scrPP2.Dominio.Comando.MostrarProyectoNuevo;
import com.ungs.pp2.scrPP2.Dominio.Interfaz.IAppController;
import com.ungs.pp2.scrPP2.utils.Logger;
import com.ungs.pp2.scrPP2.windows.UserStoryOrderableWindow;



public class HomeView  extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_Top;
	private IAppController AppController;
	private BurndownChartView burndownChartViewpanel;
	private UserStoryPaginadoView listadoPaginadoHistorias;
	private UserStoryOrderableWindow filtradoHistorias;
	private AltaUserStoryView userStoryUpload; //Alta de User Stories
	private JMenuBar menuBar;
	private JMenu menuP,mnIteraciones,mnSprint,mnBacklog,mnBurnDownChart;
	private JMenuItem mnListadoHistoriasItem,mnBurndownItem,mnFiltradoItem,mnNuevoProyectoItem,mnNuevaUserStory,mnit1item,mnit2item,mnit3item,mntmAbrirProyecto,mntmCerrarProyecto;

	public HomeView (IAppController controller)
	{

	   this.AppController=controller;
	   getContentPane().setLayout(new BorderLayout());
	   burndownChartViewpanel = new BurndownChartView(new BurndownChartController(null, null));
	   listadoPaginadoHistorias = new UserStoryPaginadoView(new UserStoryPaginadoController(new Consulta()));
	   filtradoHistorias = new UserStoryOrderableWindow(new UserStoryListView( ((HomeController)controller).getProyectoController().getBacklog() ));
	   userStoryUpload = new AltaUserStoryView(new AltaUserStoryController(new Consulta(),((HomeController)controller).getProyectoController())); //Alta User Stories
	   
		setTitle("Scrummer");		
		this.setJMenuBar(cargarMenu());


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        Logger.close();
		    }
		});
		this.setSize(600,400);
		this.setLocationRelativeTo(null);
		//getContentPane().setLayout(new BorderLayout(0, 0));

		panel_Top = new JPanel();
		panel_Top.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(panel_Top,BorderLayout.NORTH);
		//panel_Top.setLayout(new BorderLayout(0, 0));

		JLabel lblProyecto = new JLabel("Proyecto");
		panel_Top.add(lblProyecto);

		JLabel label = new JLabel("-");
		panel_Top.add(label);

		JLabel lblNumeroIteracion = new JLabel("#");
		panel_Top.add(lblNumeroIteracion);

		JLabel lblIteracin = new JLabel("Iteración");
		panel_Top.add(lblIteracin);

		//panel_Main = new JPanel();
		//panel_Main.setLayout(new BorderLayout());	

		burndownChartViewpanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(burndownChartViewpanel,BorderLayout.CENTER);

		//burndownChartViewpanel.setVisible(true);
		/*panel_Main.add(burndownChartViewpanel);
      panel_Main.setBorder(new LineBorder(new Color(0, 0, 0)));
      panel_Main.setBounds(0, 21, 584, 319);*/

		mnListadoHistoriasItem.addActionListener(new ActionListener() 
		{ 
			public void actionPerformed(ActionEvent e) 
			{ 
				MostrarListadoPaginadoHistorias();
				setearVista();
			} 
		});

		mnBurndownItem.addActionListener(new ActionListener() 
		{ 
			public void actionPerformed(ActionEvent e) 
			{ 
				MostrarBurndownChart();
				setearVista();
			} 
		});

		mnFiltradoItem.addActionListener(new ActionListener() 
		{ 
			public void actionPerformed(ActionEvent e) 
			{ 
				MostrarFiltradoHistorias();
				setearVista();
			} 
		});
		
		mnNuevaUserStory.addActionListener(new ActionListener() 
		{ 
			public void actionPerformed(ActionEvent e) 
			{ 
				MostrarUserStoryUpload();
				setearVista();
			} 
		});
	}
	

	//Menu donde se selecciona el tipo de chart
	private JMenuBar cargarMenu(){

		menuBar= new JMenuBar();
		menuP= new JMenu("Proyectos");
		mnIteraciones= new JMenu("Iteraciones");

		//menuP.add(menu1=new JMenuItem("Avance"));
		//menuP.add(menu2=new JMenuItem("Estimado"));
		//menuP.add(menu3=new JMenuItem("Comparativo"));

		mnIteraciones.add(mnit1item=new JMenuItem("Primera"));
		mnIteraciones.add(mnit2item=new JMenuItem("Segunda"));
		mnIteraciones.add(mnit3item=new JMenuItem("Tercera"));

		menuBar.add(menuP);

		mnNuevoProyectoItem = new JMenuItem("Nuevo Proyecto");
		mnNuevoProyectoItem.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
		      AppController.Execute(new MostrarProyectoNuevo());
		   }
		});
		
		AppController.Execute(new AgregarOkListenerBacklogNuevo());
		AppController.Execute(new AgregarSiguienteListenerProyectoNuevo());
		AppController.Execute(new LimpiarProyectoNuevoView());
		AppController.Execute(new LimpiarBacklogNuevoView());		
		
		menuP.add(mnNuevoProyectoItem);
		
		mntmAbrirProyecto = new JMenuItem("Abrir Proyecto");
		mntmAbrirProyecto.setEnabled(false);
		menuP.add(mntmAbrirProyecto);
		
		mntmCerrarProyecto = new JMenuItem("Cerrar Proyecto");
		mntmCerrarProyecto.setEnabled(false);
		menuP.add(mntmCerrarProyecto);

		mnBacklog = new JMenu("Product Backlog");
		menuBar.add(mnBacklog);
		
				mnSprint = new JMenu("Sprint Backlog");
				menuBar.add(mnSprint);
				
						mnListadoHistoriasItem = new JMenuItem("Listado");
						mnSprint.add(mnListadoHistoriasItem);
						
								mnFiltradoItem = new JMenuItem("Filtrado");
								mnSprint.add(mnFiltradoItem);
		
		mnBurnDownChart = new JMenu("Burndown Chart");
		menuBar.add(mnBurnDownChart);
		
		mnBurndownItem = new JMenuItem("Gráfico");
		mnBurnDownChart.add(mnBurndownItem);
		menuBar.add(mnIteraciones);
		
		mnNuevaUserStory = new JMenuItem("Nueva historia");
		mnBacklog.add(mnNuevaUserStory );
		return menuBar;
	}

	private void MostrarListadoPaginadoHistorias()
	{
		getContentPane().remove(1);
		getContentPane().add(listadoPaginadoHistorias,BorderLayout.CENTER);
	}

	private void MostrarBurndownChart()
	{
		getContentPane().remove(1);
		burndownChartViewpanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(burndownChartViewpanel,BorderLayout.CENTER);
	}

	private void MostrarFiltradoHistorias()
	{
		getContentPane().remove(1);
		//burndownChartViewpanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(filtradoHistorias,BorderLayout.CENTER);
	}

	//Roger espero que no haya agregado mal esta ventana
	private void MostrarUserStoryUpload()
	{
		getContentPane().remove(1);
		JScrollPane scroll=new JScrollPane(userStoryUpload);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(scroll,BorderLayout.CENTER);
	}

	private void setearVista()
	{
		SwingUtilities.updateComponentTreeUI(this);
	}



	public void showWindow(boolean esVisible) {
		setVisible(esVisible);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub

	}

}