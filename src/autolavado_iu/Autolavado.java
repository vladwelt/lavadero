package autolavado_iu;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class Autolavado extends JFrame{

	public static void main(String[] args) {
	Autolavado auto = new Autolavado(); 
	}
        
        Timer timer;
        Equipo equipo;
	Terreno terreno;
        //----------------------------------
	private JFrame ventanaReportes;
        private JLabel areaReportes1;
        private JScrollPane areaReportes;
        private JTextArea reportes;
        private int p=0;
        
        //-----------------------------------
        
	private JMenuBar barra;
	private JMenu menuOpciones;
	private JMenu menuIrA;
	private JMenu menuTutorial;
        private JMenu menuSimular;
	
	private JMenu menuNuevo;
	private JMenuItem itemJugar;
	private JMenuItem itemCrear;
	
	public JMenuItem itemCargar;
	public JMenuItem itemGuardar;
	private JMenuItem itemPuntaje;
	private JMenuItem itemSalir;
	
	private JMenuItem itemMazeM;
	private JMenuItem itemMazeD;
        private JMenuItem itemReportes;
	
	private JMenu menuAyuda;
	private JMenuItem itemAyudaMM;
	private JMenuItem itemAyudaMP;
	private JMenuItem itemAyudaMD;
	
        private JMenuItem itemPlay;
        private JMenuItem itemPause;
        private JMenuItem itemStop;
	private JMenuItem itemReglas;
	private JMenuItem itemCredito;
        
        private Diseño_Terreno disenio;
        private ArrayList<Dimension> ruta;
	
	public Autolavado(){
                Menu();
		iniciarReportes();	
                terreno = new Terreno();
                equipo = new Equipo(60, new Dimension(9,15));
		Dimension dim = getToolkit().getScreenSize();
		int ancho=1275 , alto=750;
		this.setSize(ancho,alto);
		this.setLocation((int)dim.getWidth()/2 -ancho/2 ,(int)dim.getHeight()/2-alto/2);
		this.setTitle("AutoLavado");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cargarMM();
                timer = new Timer(1000, new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(p==ruta.size()-1)
                            timer.stop();
                        if(p!=0)
                            disenio.getPanelAutolavado().remarcar((int)ruta.get(p-1).getWidth(),(int)ruta.get(p-1).getHeight());
                        disenio.getPanelAutolavado().marcar((int)ruta.get(p).getWidth(),(int)ruta.get(p).getHeight());
                        p++;
                    }
                });
		this.setVisible(true);
	}

	private void Menu(){
		barra = new JMenuBar();
		
		menuOpciones = new JMenu("Archivo");
		menuTutorial = new JMenu("Tutorial");
		menuIrA = new JMenu("Ir A");
		
		
		menuNuevo = new JMenu("Nuevo");
		itemCargar = new JMenuItem("Cargar");
		itemCargar.setEnabled(false);
                itemGuardar = new JMenuItem("Guardar");
                itemGuardar.setEnabled(false);
                itemPuntaje = new JMenuItem("Puntaje");
                itemSalir = new JMenuItem("Salir");
                itemSalir.addActionListener(new Autolavado.accionesMenu());
                itemJugar = new JMenuItem("jugar");
                itemJugar.addActionListener(new Autolavado.accionesMenu());
                itemCrear = new JMenuItem("crear");
                itemCrear.addActionListener(new Autolavado.accionesMenu());

                itemMazeM = new JMenuItem("Maze Maker");
                itemMazeM.addActionListener(new Autolavado.accionesMenu());
                itemMazeD = new JMenuItem("Maze Designer");
                itemMazeD.addActionListener(new Autolavado.accionesMenu());
                
                itemReportes = new JMenuItem("Reportes");
                itemReportes.addActionListener(new Autolavado.accionesMenu());

                menuAyuda = new JMenu("Ayuda");
                itemAyudaMM = new JMenuItem("Maze Maker");
                itemAyudaMP = new JMenuItem("Maze Player");
                itemAyudaMP.addActionListener(new Autolavado.accionesMenu());
                itemAyudaMD = new JMenuItem("Maze Designer");
		itemReglas = new JMenuItem("Reglas y consejos");
		itemCredito = new JMenuItem("creditos");
		
                menuSimular = new JMenu("Simular");
                itemPlay = new JMenuItem("Simular");
                itemPlay.addActionListener(new Autolavado.accionesMenu());
                itemPause = new JMenuItem("Pausar");
                itemStop = new JMenuItem("Parar");
                menuSimular.add(itemPlay);
                menuSimular.add(itemPause);
                menuSimular.add(itemStop);
                
		menuOpciones.add(menuNuevo);
		menuOpciones.add(itemCargar);
		menuOpciones.add(itemGuardar);
//		menuOpciones.add(itemPuntaje);
		menuOpciones.add(itemSalir);
//		menuNuevo.add(itemJugar);
//		menuNuevo.add(itemCrear);
		
		menuIrA.add(itemMazeM);
		menuIrA.add(itemMazeD);
                menuIrA.add(itemReportes);
		
		menuTutorial.add(menuAyuda);
		menuTutorial.add(itemReglas);
		menuTutorial.add(itemCredito);
		menuAyuda.add(itemAyudaMM);
		menuAyuda.add(itemAyudaMP);
		menuAyuda.add(itemAyudaMD);
		barra.add(menuOpciones);
		barra.add(menuIrA);
                barra.add(menuSimular);
		barra.add(menuTutorial);
		
		this.setJMenuBar(barra);
	}
	class accionesMenu implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent itemMenu) {
			if(itemMenu.getSource()==itemSalir){
				System.exit(0);
			}
			if(itemMenu.getSource() == itemCrear){
				cargarMD();
			}
			if(itemMenu.getSource() == itemMazeD){
				cargarMD();
			}
			if(itemMenu.getSource()== itemMazeM){
				cargarMM();
			}
			if(itemMenu.getSource()== itemAyudaMP){
				File ayuda=new File("MPTUTO.pdf");
			}
			if(itemMenu.getSource()==itemReportes){
                                habilitarReportes();
                        }
                        if(itemMenu.getSource()==itemPlay){
                                simular();
                        }
		}
		
	}
	
	protected void cargarMD(){
                disenio = new Diseño_Terreno(this,terreno);
		this.setContentPane(disenio);
		this.repaint();
	}
	
	protected void cargarMM(){
		this.setContentPane(new Inicio(this));
		this.repaint();
	}

	protected void sinFocus(){
		setFocusable(false);
	}
	
	protected void habilitarCargarGardar(){
		itemCargar.setEnabled(true);
		itemGuardar.setEnabled(true);
	}
        
        private void iniciarReportes(){
                ventanaReportes = new JFrame("Reportes");
                ventanaReportes.setSize(400, 400);
                ventanaReportes.setDefaultCloseOperation(HIDE_ON_CLOSE);
                ventanaReportes.setLayout(new BorderLayout());
                areaReportes1 = new JLabel("AUTOLAVADO");
                reportes = new JTextArea("Autolavado",10,15);
                areaReportes = new JScrollPane(reportes);
                reportes.setText("Bienvenido a Autolavado\n");
                reportes.setEditable(false);
                reportes.setVisible(true);
                ventanaReportes.add(areaReportes1,BorderLayout.NORTH);
                ventanaReportes.getContentPane().add(areaReportes, BorderLayout.CENTER);
                ventanaReportes.setVisible(true);
        }
        
        private void habilitarReportes(){
            ventanaReportes.setVisible(true);
            ventanaReportes.repaint();
        }
        
        public void darReporte(String reporte){
            reportes.append(reporte+"\n");
        }
        
        public void simular(){
            p = 0;
            if(disenio.getPanelAutolavado().hayTerreno()){
                darReporte("El trafico local es "+terreno.traficoLocal(50, 0.3));
                darReporte("La cantidad de clientes es: "+terreno.cantidadClientes(60));
                darReporte("El intervalo  de llegadas es: "+terreno.intervaloDeLlegadas(60));
                darReporte("El tamaño de estacionamiento es : "+ terreno.tamanoDeEstacionamiento(equipo));
                darReporte("La capacidad de Estacionamientos es : "+ terreno.capacidadDeEstacionamientos(equipo));
                darReporte("La capacidad de Lavaderos es : "+ terreno.capacidadDeLavaderos(equipo));
                darReporte((terreno.getAlto_usoComercial())? "Es de uso Comercial":"");
                disenio.getPanelAutolavado().getDisenio().camino();
                disenio.getPanelAutolavado().marcar(3,4);
                ruta = terreno.camino();
                timer.start();
            }
        }
        
        public void mover(){
            
        }
}
