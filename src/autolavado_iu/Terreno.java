package autolavado_iu;

import java.awt.Dimension;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import javax.swing.JFileChooser;

public class Terreno{
	
	private static int Pared = 0;
	private static int Camino = 1;
	private static int Entrada = 2;
	private static int Salida = 3;
	
        private static int tunel = 4;
        private static int estacionamiento = 5;
        private static int asfalto = 6;
        private static int trayecto = 7;
        
              
	private Point puntoEntrada = new Point();
	private Point puntoSalida = new Point();
        
        private JFileChooser selector;
	
	int[][] Terreno;
	private int Fila;
	private int Columna;
	
	private boolean hayTerreno = false;
	private boolean hayEntrada = false;
	private boolean haySalida = false;
	
	private boolean abrioTerreno;	

        private boolean alto_usocomercial=false;
        private boolean esquina=false;
        private boolean avenida=false;
        
        private double media=10;
        private double desvio=10;
        private int cambio = 0;
   
	public Terreno(){
                abrioTerreno = true;
            
                /*Cliente cliente = new Cliente();
                //Vehiculo vehiculo = new Vehiculo();
               // double n = this.intervaloDeLlegadas(8);
                int m = (int)(60/n);
                System.out.println(m);
                
             /*   ArrayList<String> veh = vehiculo.tipoVehiculo(m);
                ArrayList<String> cli = cliente.tipoCliente(m);
                for (int i = 0; i < m; i++) {
                    System.out.println("nro" + (i+1) +"\t"+"tipo vehiculo:" + veh.get(i)+"\t"+"tipo cliente:"+cli.get(i));
                }
                
                System.out.println("El intervalo de llegadas cada hora de los clientes es de: "+ n+ "   minutos");
                System.out.println("La cantidad de clientes que llegan en una hora es: "+ m);
                System.out.println("Trafico local de vehiculos:"+ traficoLocal(m, 0.6));
                */
              
	}
	
        public void setCaracteristicas(boolean comer, boolean esq, boolean av){

            if(comer){
                alto_usocomercial=true;
//                System.out.println("el terreno es de alto uso comercial");
            }
            if(esq){
                esquina=true;
//                System.out.println("el terreno esta ubicado en una esquina");
            }
            if(av){
                avenida=true;
//                System.out.println("el terreno esta ubicado en una avenida");
            }
        }
        
        public int capacidadDeLavaderos(Equipo e){
                int cant = 0;
                double capEst,supter;
                supter = getTotalColumnas()*getTotalFilas();
                capEst = tamanoDeEstacionamiento(e);
                while(supter>0){
                supter -= (e.getSuperficie()+capEst);
                cant++;
            }
            return cant-1;
        }
        
        public int capacidadDeEstacionamientos(Equipo e){
                int resultado = 0;
                resultado = capacidadDeLavaderos(e)*tamanoDeEstacionamiento(e);
                return resultado;
        }

        public int cantidadClientes(int tiempo){

            int  m=(int)((tiempo)/(intervaloDeLlegadas(8)));
            return m;
        }

        public int tamanoDeEstacionamiento(Equipo e){
            int result =0;
            result = e.getCapacidad()/2;
            return result;
        }

        public boolean invesionInicial(){
            boolean result=false;
            return result;
        }

        public double intervaloDeLlegadas(int tiempo){
            double res = 0;
            //int aux =tiempoGe
            Generador g = new Generador();
            //int tiempomin = tiempo*60;
            double tiempo_llegada=0.0;
            double num_ramdom=0;
            while(tiempo>=1){
                //int num_ramdom =(int)(Math.random()*60);
                if (cambio == 0)
                    num_ramdom = g.exponencial(media);
                else
                    num_ramdom = g.normal(media,desvio);
                tiempo_llegada +=num_ramdom;
                tiempo--;  
            }
            res = tiempo_llegada/60;
            return res;

        }

        public int traficoLocal(int cantClientes, double nroGrado){
            int x=0; 
            x = (int)((cantClientes*100)/(nroGrado))+1;
            if (this.alto_usocomercial)x=x+20;
            if (this.avenida)x=x+20;
            if (this.esquina)x=x+20;
            return x;

        }

	protected void Construir(int f, int c){
		//Tamanio del Terreno
		Fila = f; Columna = c;
		Terreno = new int[Fila][Columna];
		haySalida=false;
		hayEntrada = false;
		
		//Con paredes que lo rodean
		for (int i = 0; i < Fila; i++) {
			for (int j = 0; j < Columna; j++) {
				if((i==0||i==Fila-1) || (j==0||j==Columna-1))Terreno[i][j]=Pared;
				else Terreno[i][j]=Camino;
			}
		}
	}
	protected void Mostrar(){
		String aux="";
		for (int i = 0; i < Fila; i++) {
			for (int j = 0; j < Columna; j++) {
				aux += Terreno[i][j];
			}
			System.out.println(aux);
			aux="";
		}
	}
	protected void Limpiar(){
		for (int f = 1; f < getTotalFilas()-1; f++) {
			for (int c = 1; c < getTotalColumnas()-1; c++) {
				Terreno[f][c] = Camino;
			}
		}
	}
	
	
	protected void addPared(int f,int c, int [][]pared){
		int colIni=c;
		if((f>=0 && f+pared.length<=getTotalFilas())
		 &&(c>=0 && c+pared[0].length<=getTotalColumnas())){
			//Reccorro la pared que quiero aderir
			for(int fPared=0; fPared < pared.length; fPared++) {
				for(int cPared = 0; cPared < pared[0].length ; cPared++){
					if(!esPared(f, c)&&!esEntrada(f, c)&&!esSalida(f, c)){
						Terreno[f][c] = pared[fPared][cPared];
					}
					c++;
				}
				f++;
				c=colIni;
			}
		}
	}
	 
        protected void addTunel(int f,int c, int [][]tunel){
		int colIni=c;
		if((f>=0 && f+tunel.length<=getTotalFilas())
		 &&(c>=0 && c+tunel[0].length<=getTotalColumnas())){
			//Reccorro el tunel que quiero aderir
			for(int fPared=0; fPared < tunel.length; fPared++) {
				for(int cPared = 0; cPared < tunel[0].length ; cPared++){
					if(!esPared(f, c)&&!esEntrada(f, c)&&!esSalida(f, c)){
						Terreno[f][c] = tunel[fPared][cPared];
					}
					c++;
				}
				f++;
				c=colIni;
			}
		}
	}
        
        protected void addEstacionam(int f,int c, int [][]esta){
		int colIni=c;
		if((f>=0 && f+esta.length<=getTotalFilas())
		 &&(c>=0 && c+esta[0].length<=getTotalColumnas())){
			//Reccorro el estacionamiento que quiero aderir
			for(int fPared=0; fPared < esta.length; fPared++) {
				for(int cPared = 0; cPared < esta[0].length ; cPared++){
					if(!esPared(f, c)&&!esEntrada(f, c)&&!esSalida(f, c)){
						Terreno[f][c] = esta[fPared][cPared];
					}
					c++;
				}
				f++;
				c=colIni;
			}
		}
	}
        
        
        protected void addAsfalto1(int f,int c, int [][]asfal){
		int colIni=c;
		if((f>=0 && f+asfal.length<=getTotalFilas())
		 &&(c>=0 && c+asfal[0].length<=getTotalColumnas())){
			//Reccorro el estacionamiento que quiero aderir
			for(int fPared=0; fPared < asfal.length; fPared++) {
				for(int cPared = 0; cPared < asfal[0].length ; cPared++){
					if(!esPared(f, c)&&!esEntrada(f, c)&&!esSalida(f, c)){
						Terreno[f][c] = asfal[fPared][cPared];
					}
					c++;
				}
				f++;
				c=colIni;
			}
		}
        }
        
        protected void addAsfalto2(int f,int c, int [][]asfal){
		int colIni=c;
		if((f>=0 && f+asfal.length<=getTotalFilas())
		 &&(c>=0 && c+asfal[0].length<=getTotalColumnas())){
			//Reccorro el estacionamiento que quiero aderir
			for(int fPared=0; fPared < asfal.length; fPared++) {
				for(int cPared = 0; cPared < asfal[0].length ; cPared++){
					if(!esPared(f, c)&&!esEntrada(f, c)&&!esSalida(f, c)){
						Terreno[f][c] = asfal[fPared][cPared];
					}
					c++;
				}
				f++;
				c=colIni;
			}
		}
	}
        
        
        
        
	protected void setEntrada(int f, int c){
		
			if(((f==0 || f==getTotalFilas()-1)&&(c>0 && c<getTotalColumnas()-1)) 
			 ||((c==0 || c==getTotalColumnas()-1)&&(f>0 && f<getTotalFilas()-1))){
				if(esSalida(f, c)) haySalida = false;
				Terreno[f][c] = Entrada;
				puntoEntrada.setLocation(f, c);
				hayEntrada = true;
			}
		
	}
	protected void setSalida(int f, int c){
		
			if(((f==0 || f==getTotalFilas()-1)&&(c>0 && c<getTotalColumnas()-1)) 
			 ||((c==0 || c==getTotalColumnas()-1)&&(f>0 && f<getTotalFilas()-1))){
				if(esEntrada(f, c)) hayEntrada = false;
				Terreno[f][c] = Salida;
				puntoSalida.setLocation(f, c);
				haySalida = true;
			}
		
		
	}
	
	protected boolean hayEntrada(){return hayEntrada;}
	protected boolean haySalida(){return haySalida;}
	protected boolean hayTerreno(){return hayTerreno;}
	
	protected boolean esPared(int f, int c){return Terreno[f][c] == Pared;}
	protected boolean esCamino(int f, int c){return Terreno[f][c]==Camino;}
	protected boolean esEntrada(int f, int c){return Terreno[f][c] == Entrada;}
	protected boolean esSalida(int f, int c){return Terreno[f][c] == Salida;}
	protected boolean esTunel(int f, int c){return Terreno[f][c] == tunel;}
	protected boolean esEsta(int f, int c){return Terreno[f][c] == estacionamiento;}
	protected boolean esAsfalto(int f, int c){return Terreno[f][c] == asfalto;}
        protected boolean esTrayecto(int f, int c){return Terreno[f][c] == trayecto;}
        
        public boolean getAlto_usoComercial(){return alto_usocomercial;}
        public boolean getEsquina(){return alto_usocomercial;}
        public boolean getAvenida(){return alto_usocomercial;}
        
	protected int getTotalColumnas(){return Columna;}
	protected int getTotalFilas(){return Fila;}
	protected Point getEntrada(){return puntoEntrada;}
	protected Point getSalida(){return puntoSalida;	}

	protected boolean  abrioTerreno(){
		return abrioTerreno;
	}
         public int[][] darTerreno(){
             return Terreno;
         }
         
         public ArrayList<Dimension> camino(){
             ArrayList<Dimension> inicio = new ArrayList<Dimension>();
             ArrayList<Dimension> fin = new ArrayList<Dimension>();
             fin=inicioFinal();
             System.out.println("tyuiuyuyuiuyui"+fin);
//             for(int i=0;i<fin.size();i++){
//                 System.out.println("pos "+i+" = "+fin.get(i)+"\n");
//             }
//             System.out.println("i"+inicioFinal().get(0).getWidth());
//             System.out.println("i"+inicioFinal().get(0).getHeight());
//             System.out.println("s"+inicioFinal().get(1).getWidth());
//             System.out.println("s"+inicioFinal().get(1).getHeight());
             return fin;
         }
         
         private ArrayList<Dimension> inicioFinal(){
             ArrayList<ArrayList> contenedor = new ArrayList<ArrayList>();
             ArrayList<Dimension> inicio = new ArrayList<Dimension>();
             int finalx = Terreno[0].length-1;
             int finaly = Terreno.length-1;
             Dimension in = new Dimension();
//             Dimension fi = new Dimension();
             for (int i = 0; i<Terreno.length;i++){
                 if(Terreno[0][i]==2){in.setSize(0,i); }
//                 else if(Terreno[0][i]==3){fi.setSize(0,i); }
                 if(Terreno[finalx][i]==2){ in.setSize(finalx,i); }
//                 else if(Terreno[finalx][i]==3){ fi.setSize(finalx,i); }
             }
             for (int i = 0; i<Terreno[0].length;i++){
                 if(Terreno[i][0]==2){ in.setSize(i,0); }
//                 else if(Terreno[i][0]==3){ fi.setSize(i,0); }
                 if(Terreno[i][finaly]==2){ in.setSize(i,finaly); }
//                 else if(Terreno[i][finaly]==3){ fi.setSize(i,finaly); }
             }
             System.out.println("a.-"+in.height+" b.-"+in.width);
             inicio.add(in);
             if(inicio.size()>0){
                centro(inicio,contenedor);
                if(inicio.size()>1){
                    if(!buscarfin(inicio))
                        inicio.clear();
                    System.out.println("kknafkjlfajkldsljfkhdsjk"+inicio.size());    
                }
             }
             else
                 inicio.clear();
             System.out.println("kknafkjlfajkldsljfkhdsjk"+inicio.size());    
//             inicio.add(fi);
             for(int i=0;i<contenedor.size();i++)
                System.out.println(contenedor.get(i));
             return inicio;
         }
         private void centro(ArrayList<Dimension> inicio, ArrayList<ArrayList> contenedor){
             
             int i = 1, j=1,aux=0,q=0;
             boolean test=true;
             while(test){
                 i=(int)inicio.get(inicio.size()-1).getWidth();
                 j=(int)inicio.get(inicio.size()-1).getHeight();
                 try{
                 if(Terreno[i+1][j]==7&&aux!=1){
                     buscarfc(inicio, 0, i, j, 1);
                     aux=3;
                 }
                 else
                     q++;
                 }
                 catch(Exception ex){
                     System.out.println(ex);
                 }
                 i=(int)inicio.get(inicio.size()-1).getWidth();
                 j=(int)inicio.get(inicio.size()-1).getHeight();
                 try{
                 if (Terreno[i][j+1]==7&&aux!=2){
                     buscarfc(inicio, 1, i, j, 1);
                     aux = 4;
                 }
                 else 
                     q++;
                 }
                 catch(Exception ex){
                     System.out.println(ex);
                 }
                 i=(int)inicio.get(inicio.size()-1).getWidth();
                 j=(int)inicio.get(inicio.size()-1).getHeight();
                 try{
                 if (Terreno[i-1][j]==7&&aux!=3){
                     buscarfc(inicio, 0, i, j, -1);
                     aux=1;
                 }
                 else 
                     q++;
                 }
                 catch(Exception ex){
                     System.out.println(ex);
                 }
                 i=(int)inicio.get(inicio.size()-1).getWidth();
                 j=(int)inicio.get(inicio.size()-1).getHeight();
                 try{
                 if (Terreno[i][j-1]==7&&aux!=4){
                     buscarfc(inicio, 1, i, j, -1);
                     aux=2;
                 }
                 else 
                     q++;
                 }catch(Exception ex){
                     System.out.println(ex);
                 }
                 if(q>=4)
                     test=false;
             }
         }
         public void buscarfc(ArrayList<Dimension> posiciones, int fc, int f, int c, int p) {
             boolean test = true;
             while (test) {
                 if (fc == 0) {
                     f += p;
                 } else {
                     c += p;
                 }
                 System.out.println("numeros = " + c);
                 if (Terreno[f][c] == 7) {
                     posiciones.add(new Dimension(f, c));
                 } else {
                     test = false;
                 }
             }
    }
         private boolean buscarfin(ArrayList<Dimension> inicio){
             boolean result=false;
             int i = 1, j = 1;
             i = (int) inicio.get(inicio.size() - 1).getWidth();
             j = (int) inicio.get(inicio.size() - 1).getHeight();
             Dimension in = new Dimension();
             if(Terreno[i+1][j]==3){
                     inicio.add(new Dimension(i+1,j));
                     result=true;
                 }
                 else if (Terreno[i][j+1]==3){
                     inicio.add(new Dimension(i,j+1));
                     result=true;
                 }
                 else if (Terreno[i-1][j]==3){
                     inicio.add(new Dimension(i-1,j+1));
                     result=true;
                 }
                 else if (Terreno[i][j-1]==3){
                     inicio.add(new Dimension(i,j-1));
                     result=true;
                 }
             return result;
         }
         
         protected void SaveMaze(){
		selector = new JFileChooser("Laberintos");
		int valor = selector.showSaveDialog(selector);
		
		if(valor == JFileChooser.APPROVE_OPTION){
			
			File archivo = selector.getSelectedFile();
			String ruta = archivo.getAbsolutePath();
			try {
				
				PrintWriter escritor = new PrintWriter(ruta);
				
				escritor.println(getTotalFilas());
				escritor.println(getTotalColumnas());
				
				for (int f = 0; f < getTotalFilas(); f++) {
					String linea="";
					for (int c = 0; c < getTotalColumnas(); c++) {
						linea += Terreno[f][c];
					}
					escritor.println(linea);
				}
				escritor.close();
			} catch (FileNotFoundException e) {
				System.out.println("No se encuentra el archivo");
			} catch (Exception e) {
				System.out.println("Error de escritura");
			}
		}
	}
	
	protected void OpenMaze(){
		selector = new JFileChooser("Laberintos");
		int valor = selector.showOpenDialog(selector);
		if(valor == JFileChooser.APPROVE_OPTION){
			abrioTerreno=true;
			String ruta = selector.getSelectedFile().getAbsolutePath();
			haySalida=false;
			hayEntrada=false;
			try {
				FileReader archivo = new FileReader(ruta);
				BufferedReader lector = new BufferedReader(archivo);
				
				String linea="";
				int i = 1;
				int f = 0;
				
				int tamF = 0;	int tamC = 0;
				
				while(lector.ready()){
					linea = lector.readLine();
					if(i==1)Fila = Integer.parseInt(linea);
					if(i==2){
						Columna = Integer.parseInt(linea);
						Terreno = new int[Fila][Columna];
					}
					if(i>=3){
						for (int c = 0; c < linea.length(); c++) {
							Terreno[f][c] = Integer.parseInt(""+linea.charAt(c));
							if(esEntrada(f, c)) setEntrada(f, c);
							if(esSalida(f, c)) setSalida(f, c);
                                                        if(esAsfalto(f, c)) addAsfalto1(f, c, Terreno);
							if(esTrayecto(f, c)) addAsfalto2(f, c, Terreno);
                                                        if(esTunel(f, c)) addTunel(f, c, Terreno);
						}
						f++;
					}
					i++;
				}
				hayTerreno=true;
			} catch (FileNotFoundException e) {
			} catch (IOException e) {
			}
		}else{
			abrioTerreno=false;
			
		}
	}
        public void setDistribucion(int cambio){
            this.cambio = cambio;
        }
        public void setMedia(double cambio){
            media = cambio;
        }
        public void setDesvio(double cambio){
            desvio = cambio;
        }
}
