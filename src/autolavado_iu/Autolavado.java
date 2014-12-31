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
        
        ArrayList<Timer> timer;
        Timer llegada;
        Equipo equipo;
	Terreno terreno;
        //----------------------------------
	private JFrame ventanaReportes;
        private JLabel areaReportes1;
        private JScrollPane areaReportes;
        private JTextArea reportes;
        //-----------------------------------
        
	private JMenuBar barra;
	private JMenu menuArchivo;
        private JMenu menuEditar;
	private JMenu menuIrA;
	private JMenu menuTutorial;
        private JMenu menuSimular;
	
//	private JMenu menuNuevo;
	private JMenuItem itemReiniciar;
	private JMenuItem itemBorrar;
	
	public JMenuItem itemCargar;
	public JMenuItem itemGuardar;
	private JMenuItem itemSalir;
	
	private JMenuItem itemMazeM;
        private JMenuItem itemReportes;
        private JMenu itemEstadisticas;
        private JMenuItem itemEstadisticasTorta;
        private JMenuItem itemEstadisticasBarras;
	
	private JMenu menuAyuda;
	private JMenuItem itemAyudaMM;
	private JMenuItem itemAyudaMP;
	private JMenuItem itemAyudaMD;
	
        private JMenuItem itemPlay;
        private JMenuItem itemAv;
        private JMenuItem itemStop;
	private JMenuItem itemReglas;
	private JMenuItem itemCredito;
        
        private Diseño_Terreno disenio;
        private ArrayList<Dimension> ruta;
        private int aux2=0;
        private ArrayList <temporizador> clientes;
        private int intervalo;
	private int cant=0;
        private int velocidad;
        private Cliente cliente;
        private Vehiculo vehiculo;
        private ArrayList<String> veh;
        private ArrayList<String> cli;
        
//        ---------------------------------------------------------------
        private int distribucion_tc;
        private int distribucion_tv;
        private int distribucion_tl;
        private int media_tc;
        private int media_tv;
        private int media_tl;
        private int desvio_tc;
        private int desvio_tv;
        private int desvio_tl;
//        ----------------------------------------------------------
        
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
                timer= new ArrayList<Timer>();
                cliente = new Cliente();
                vehiculo = new Vehiculo();
		cargarMM();
                habilitarCargarGardar();
		this.setVisible(true);
	}

	private void Menu(){
		barra = new JMenuBar();
		
		menuArchivo = new JMenu("Archivo");
		menuTutorial = new JMenu("Tutorial");
		menuIrA = new JMenu("Ir A");
                menuEditar = new JMenu("Editar");
		
		
//		menuNuevo = new JMenu("Nuevo");
		itemCargar = new JMenuItem("Cargar");
		itemCargar.setEnabled(false);
                itemCargar.addActionListener(new Autolavado.accionesMenu());
                itemGuardar = new JMenuItem("Guardar");
                itemGuardar.setEnabled(false);
                itemGuardar.addActionListener(new Autolavado.accionesMenu());
                itemSalir = new JMenuItem("Salir");
                itemSalir.addActionListener(new Autolavado.accionesMenu());
                itemReiniciar = new JMenuItem("Reiniciar");
                itemReiniciar.addActionListener(new Autolavado.accionesMenu());
                itemBorrar = new JMenuItem("Borrar");
                itemBorrar.addActionListener(new Autolavado.accionesMenu());

                itemMazeM = new JMenuItem("Maze Maker");
                itemMazeM.addActionListener(new Autolavado.accionesMenu());
                
                itemReportes = new JMenuItem("Reportes");
                itemReportes.addActionListener(new Autolavado.accionesMenu());
                itemEstadisticas = new JMenu("Mostrar Graficos");
                itemEstadisticasTorta = new JMenuItem("Tipo Torta");
                itemEstadisticasBarras = new JMenuItem("Tipo Barras");
                itemEstadisticasTorta.addActionListener(new Autolavado.accionesMenu());
                itemEstadisticasBarras.addActionListener(new Autolavado.accionesMenu());

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
                itemStop = new JMenuItem("Parar");
                itemStop.addActionListener(new Autolavado.accionesMenu());
                itemAv = new JMenuItem("Acelerar");
                itemAv.addActionListener(new Autolavado.accionesMenu());
                menuSimular.add(itemAv);
                menuSimular.add(itemPlay);
                menuSimular.add(itemStop);
                
		menuArchivo.add(itemReiniciar);
		menuArchivo.add(itemCargar);
		menuArchivo.add(itemGuardar);
		menuArchivo.add(itemSalir);
                menuEditar.add(itemBorrar);
                menuIrA.add(itemEstadisticas);
                itemEstadisticas.add(itemEstadisticasTorta);
                itemEstadisticas.add(itemEstadisticasBarras);
                menuIrA.add(itemReportes);
		
		menuTutorial.add(menuAyuda);
		menuTutorial.add(itemReglas);
		menuTutorial.add(itemCredito);
		menuAyuda.add(itemAyudaMM);
		menuAyuda.add(itemAyudaMP);
		menuAyuda.add(itemAyudaMD);
		barra.add(menuArchivo);
                barra.add(menuEditar);
		barra.add(menuIrA);
                barra.add(menuSimular);
		barra.add(menuTutorial);
		
		this.setJMenuBar(barra);
	}
	class accionesMenu implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent itemMenu) {
                        if(itemMenu.getSource()==itemGuardar){
				disenio.getPanelAutolavado().Guardar();
			}
                        if(itemMenu.getSource()==itemCargar){
				disenio.getPanelAutolavado().Cargar();
			}
                        if(itemMenu.getSource()==itemReiniciar){
				cargarMM();
			}
			if(itemMenu.getSource()==itemSalir){
				System.exit(0);
			}
			if(itemMenu.getSource() == itemBorrar){
				cargarMD();
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
                        if(itemMenu.getSource()==itemStop){
                                detener();
                        }
                        if(itemMenu.getSource()==itemAv){
                                Avanzar();
                        }
                        if(itemMenu.getSource()==itemEstadisticasBarras){
                                mostrarEstadisticasTipoBarras();
                        }
                        if(itemMenu.getSource()==itemEstadisticasTorta){
                                mostrarEstadisticasTipoTorta();
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
            if(disenio.getPanelAutolavado().hayTerreno()){                
                ruta = terreno.camino();
                if(ruta.size()<=1){
                    JOptionPane.showMessageDialog(this, "ERROR!!!!"
                                                        + "\n Su diseño es incorrecto"
                                                        + "\nPosibles causas:"
                                                        + "\n-Falta de una Entrada"
                                                        + "\n-Su Asfaltos no coinciden"
                                                        + "\n-Falta de una Salida"
                                                       ,"Simular",JOptionPane.ERROR_MESSAGE);
                    
                }
                else{
                    darReporte("El trafico local es "+terreno.traficoLocal(50, 0.3));
                    darReporte("La cantidad de clientes es: "+terreno.cantidadClientes(60));
                    intervalo = (int)terreno.intervaloDeLlegadas(60);
                    darReporte("El intervalo  de llegadas es: "+intervalo);
                    darReporte("El tamaño de estacionamiento es : "+ terreno.tamanoDeEstacionamiento(equipo));
                    darReporte("La capacidad de Estacionamientos es : "+ terreno.capacidadDeEstacionamientos(equipo));
                    darReporte("La capacidad de Lavaderos es : "+ terreno.capacidadDeLavaderos(equipo));
                    darReporte((terreno.getAlto_usoComercial())? "Es de uso Comercial":"");
                    ejecutar();
                }
            }
        }
        
        public void ejecutar() {
            velocidad = 10;
            if (cant == 0) {
                cant = terreno.cantidadClientes(60);
                veh = vehiculo.tipoVehiculo(cant);
                cli = cliente.tipoCliente(cant);
            }
            clientes = new ArrayList<temporizador>();
            final int r = 0;
            llegada = new Timer(intervalo * 1000, new ActionListener() {
                int i = 1;
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (i == cant + 1) {
                        llegada.stop();
                    }
                    temporizador t = new temporizador(velocidad, terreno, disenio, i, Autolavado.this,veh.get(i),cli.get(i));
                    clientes.add(t);
                    i++;
                }
            });
            llegada.start();
        }
        public void avanzar(){
            velocidad = (int)(velocidad*0.5);
            try{
            llegada.stop();
            }
            catch(Exception ex){
                
            }
            clientes.clear();
            intervalo = (int)(intervalo*0.5);
            llegada = new Timer(intervalo*1000, new ActionListener() {
                    int i=1;
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(i==cant+1){
                            llegada.stop();
                        }
                        temporizador t = new temporizador(velocidad,terreno,disenio,i, Autolavado.this,veh.get(i),cli.get(i));
                        clientes.add(t);
                        i++;
                    }
                });
                llegada.start();
        }
        
        public void detener(){
            if(llegada.isRunning()){
            llegada.stop();
            for(int i=0;i<clientes.size();i++){
                clientes.get(i).cancelar();
            }
            }
        }
        public void Avanzar(){
            if(llegada.isRunning()){
                for(int i=0;i<clientes.size();i++){
//                    if(clientes.get(i).)
                    clientes.get(i).controlar();
                }
                avanzar();  
            }
        }
        public void mostrarEstadisticasTipoTorta(){
            Torta client = new Torta(100,100,"Tipo de Clientes","Socio","No Socio",cliente.getSocio(),cliente.getNormal());
            Torta vehic = new Torta(100,370,"Tipo de Vehiculos","Auto Normal","Camioneta",vehiculo.getAuto(),vehiculo.getCamioneta());
        }
        public void mostrarEstadisticasTipoBarras(){
            Barras c = new Barras(200,100,"Clientes","Socio","No Socio",cliente.getSocio(),cliente.getNormal(),"Numero de Clientes","Tipo de Clientes");
            Barras v = new Barras(200,370,"Vehiculos","Auto Normal","Camioneta",vehiculo.getAuto(),vehiculo.getCamioneta(),"Numero de Vehiculos","Tipo de Vehiculos");
        }
        public void cambiarCliente(int cambio,String media,String desvio){
            cliente.setDistribucion(cambio);
            double me=Double.parseDouble(media);
            if(me<8)
                me = 8;
            cliente.setMedia(me);
            double de=Double.parseDouble(desvio);
            if(de<8)
            cliente.setDesvio(de);
        }
        public void cambiarVehiculo(int cambio,String media,String desvio){
            vehiculo.setDistribucion(cambio);
            double me=Double.parseDouble(media);
            if(me<8)
                me=8;
            vehiculo.setMedia(me);
            double de=Double.parseDouble(desvio);
            if(de<8)
                de=8;
            vehiculo.setDesvio(de);
        }
        public void cambiarIntervalo(int cambio,String media,String desvio){
            terreno.setDistribucion(cambio);
            double me=Double.parseDouble(media);
            if(me<8)
                me=8;
            terreno.setMedia(me);
            double de=Double.parseDouble(desvio);
            if(de<8)
                de=8;
            terreno.setDesvio(de);

        }
}
