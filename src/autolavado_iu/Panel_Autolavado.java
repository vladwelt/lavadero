package autolavado_iu;

import java.io.File;
import javax.swing.*;

public class Panel_Autolavado extends JPanel{

	private Terreno terreno;
	private boolean hayTerreno = false;
	private JLabel[][] lblMaze;
        //Direcciones Multiplataforma
        private String b= new String("Imgs"+File.separator+"Terreno"+File.separator);
        private String a= new String(b+"PorDefecto"+File.separator);
        //Direcciones Multiplataforma
	private ImageIcon Pared = new ImageIcon(a+"Pared.jpg");
	private ImageIcon Camino = new ImageIcon(a+"Camino.JPG");
        private ImageIcon Tunel = new ImageIcon(a+"tunel.jpg");
        private ImageIcon Esta = new ImageIcon(a+"estacionam.jpg");
        private ImageIcon Asfa1 = new ImageIcon(a+"asfalto.jpg");
        private ImageIcon Asfa2 = new ImageIcon(a+"asfalto2.jpg");
        
	private ImageIcon Entrada = new ImageIcon(b+"Entrada.jpg");
	private ImageIcon Salida = new ImageIcon(b+"Salida.jpg");
	
	public Panel_Autolavado(int posX, int posY, int ancho, int alto, Terreno terreno){
                this.terreno = terreno;
		setBounds(posX,posY,ancho,alto);
		setLayout(null);
		setBorder(BorderFactory.createTitledBorder("Editor del Terreno del AutoLavado"));
		setOpaque(false);
	}
	
	protected void CrearTerreno(int f, int c, boolean esNuevo){
	
            Ventana_Carac ventana = new Ventana_Carac(terreno);
            ventana.setVisible(true);
            
            if(f <= 45 && f>= 10 && c <= 70 && c>=10){
			if(!hayTerreno){
				int posX = getWidth()/2 - c*Pared.getIconWidth()/2;
				int posY = getHeight()/2 - f*Pared.getIconHeight()/2 +5;

				lblMaze = new JLabel[f][c];
				if(esNuevo)terreno.Construir(f,c);
				int W = Pared.getIconWidth();
				int H = Pared.getIconHeight();
				for (int i = 0; i < f; i++) {
					for (int j = 0; j < c; j++) {
						lblMaze[i][j] = new JLabel();
						lblMaze[i][j].setBounds((W*j)+posX,(H*i)+posY,W,H);
						add(lblMaze[i][j]);
					}
				}
				hayTerreno = true;
				DibujarDisenio();
				repaint();
			}
		}
            	else System.out.println("El Terreno es demasiado Grande/Peque�o");
	}
	protected void EliminarTerreno(){
		if(hayTerreno){
			removeAll();
			hayTerreno = false;
			repaint();
		}
	}
	
	
	protected void DibujarDisenio(){
		if(hayTerreno){
			for (int f = 0; f < terreno.getTotalFilas(); f++) {
				for (int c = 0; c < terreno.getTotalColumnas(); c++) {	
					if(terreno.esPared(f, c)) lblMaze[f][c].setIcon(Pared);
                                        if(terreno.esCamino(f, c)) lblMaze[f][c].setIcon(Camino);
					
					if(terreno.esEntrada(f, c)) lblMaze[f][c].setIcon(Entrada);
					if(terreno.esSalida(f, c)) lblMaze[f][c].setIcon(Salida);
				}
			}
			repaint();
		}
	}
	
	protected void setSalida(int f, int c){
		if(hayTerreno){
			
			if(((f==0 || f==terreno.getTotalFilas()-1)&&(c>0 && c<terreno.getTotalColumnas()-1)) 
			 ||((c==0 || c==terreno.getTotalColumnas()-1)&&(f>0 && f<terreno.getTotalFilas()-1))){
				
					terreno.setSalida(f, c);
					lblMaze[f][c].setIcon(Salida);
				
			}
		}
	}
        
	protected void setEntrada(int f, int c){
		if(hayTerreno){
			/*
			int f = (int)Convertir_aFC(mouseX, mouseY).getX();
			int c = (int)Convertir_aFC(mouseX, mouseY).getY();
			*/
			if(((f==0 || f==terreno.getTotalFilas()-1)&&(c>0 && c<terreno.getTotalColumnas()-1)) 
			 ||((c==0 || c==terreno.getTotalColumnas()-1)&&(f>0 && f<terreno.getTotalFilas()-1))){
				
					terreno.setEntrada(f, c);
					lblMaze[f][c].setIcon(Entrada);
				
			}
		}
	}
	
	protected void addPared(int f, int c, int[][] p){
		if(hayTerreno){
			/*
			int f = (int)Convertir_aFC(mouseX, mouseY).getX();
			int c = (int)Convertir_aFC(mouseX, mouseY).getY();
			*/
			if((f>=0 && f<=terreno.getTotalFilas())
			 &&(c>=0 && c<=terreno.getTotalColumnas())){
			
				if(p.length == 1)c = c - p[0].length/2;
				if(p[0].length==1)f = f - p.length/2;
				if(p.length>1 && p[0].length>1){
					f = f - p.length/2;
					c = c - p[0].length/2;
				}
			
				terreno.addPared(f,c,p);
				DibujarPared(f,c,p);
			}
		}
	}
        
	protected void DibujarPared(int f, int c, int[][]p){
 		if(hayTerreno){
			if((f>=0 && f+p.length<=terreno.getTotalFilas())
	 		 &&(c>=0 && c+p[0].length<=terreno.getTotalColumnas())){
	 			for (int i = f; i < f+p.length; i++) {
					for (int j = c; j < c+p[0].length; j++) {
						if(terreno.esPared(i, j))lblMaze[i][j].setIcon(Pared);
						this.repaint();
					}
				}
	 		}
 		}
	 }	
        
        protected void addTunel(int f, int c, int[][] p){
		if(hayTerreno){
			
			if((f>=0 && f<=terreno.getTotalFilas())
			 &&(c>=0 && c<=terreno.getTotalColumnas())){
			
				if(p.length == 1)c = c - p[0].length/2;
				if(p[0].length==1)f = f - p.length/2;
				if(p.length>1 && p[0].length>1){
					f = f - p.length/2;
					c = c - p[0].length/2;
				}
			
                                terreno.addTunel(f, c, p);
				DibujarTunel(f,c,p);
			}
		}
	}
        
	protected void DibujarTunel(int f, int c, int[][]p){
 		if(hayTerreno){
			if((f>=0 && f+p.length<=terreno.getTotalFilas())
	 		 &&(c>=0 && c+p[0].length<=terreno.getTotalColumnas())){
	 			for (int i = f; i < f+p.length; i++) {
					for (int j = c; j < c+p[0].length; j++) {
						if(terreno.esTunel(i, j))lblMaze[i][j].setIcon(Tunel);
						this.repaint();
					}
				}
	 		}
 		}
	 }
        
        protected void addEstacionam(int f, int c, int[][] p){
		if(hayTerreno){
			/*
			int f = (int)Convertir_aFC(mouseX, mouseY).getX();
			int c = (int)Convertir_aFC(mouseX, mouseY).getY();
			*/
			if((f>=0 && f<=terreno.getTotalFilas())
			 &&(c>=0 && c<=terreno.getTotalColumnas())){
			
				if(p.length == 1)c = c - p[0].length/2;
				if(p[0].length==1)f = f - p.length/2;
				if(p.length>1 && p[0].length>1){
					f = f - p.length/2;
					c = c - p[0].length/2;
				}
			
                                terreno.addEstacionam(f, c, p);
				DibujarEsta(f,c,p);
			}
		}
	}
	protected void DibujarEsta(int f, int c, int[][]p){
 		if(hayTerreno){
			if((f>=0 && f+p.length<=terreno.getTotalFilas())
	 		 &&(c>=0 && c+p[0].length<=terreno.getTotalColumnas())){
	 			for (int i = f; i < f+p.length; i++) {
					for (int j = c; j < c+p[0].length; j++) {
						if(terreno.esEsta(i, j))lblMaze[i][j].setIcon(Esta);
						this.repaint();
					}
				}
	 		}
 		}
	 }
        
        protected void addAsfalto(int f, int c, int[][] p){
		if(hayTerreno){
			
			if((f>=0 && f<=terreno.getTotalFilas())
			 &&(c>=0 && c<=terreno.getTotalColumnas())){
			
				if(p.length == 1)c = c - p[0].length/2;
				if(p[0].length==1)f = f - p.length/2;
				if(p.length>1 && p[0].length>1){
					f = f - p.length/2;
					c = c - p[0].length/2;
				}
			
                                terreno.addAsfalto1(f, c, p);
				DibujarAsfa(f,c,p);
			}
		}
	}
        
	protected void DibujarAsfa(int f, int c, int[][]p){
 		if(hayTerreno){
			if((f>=0 && f+p.length<=terreno.getTotalFilas())
	 		 &&(c>=0 && c+p[0].length<=terreno.getTotalColumnas())){
	 			for (int i = f; i < f+p.length; i++) {
					for (int j = c; j < c+p[0].length; j++) {
						if(terreno.Terreno[i][j]==6)lblMaze[i][j].setIcon(Asfa1);
                                                if(terreno.Terreno[i][j]==7)lblMaze[i][j].setIcon(Asfa2);
                                                
                                                this.repaint();
					}
				}
	 		}
 		}
	 }
        public void marcar(int i,int j){
            lblMaze[i][j].setIcon(Entrada);
        }
        public void remarcar(int i,int j){
            lblMaze[i][j].setIcon(Asfa2);
        }
	protected boolean hayTerreno(){return hayTerreno;}
	
	public Terreno getDisenio(){
		return terreno; 
	}
        
}
