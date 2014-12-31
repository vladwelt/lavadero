/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autolavado_iu;

import java.awt.Dimension;
import java.util.ArrayList;


/**
 *
 * @author vlady
 **/
public class Vehiculo {
    
    Dimension tamano;
    private int auto = 0;
    private int camioneta = 0;
    private int cambio = 0;
    private Generador g;
    private double media=10;
    private double desvio=10;
    public Vehiculo(){
        
    }
    
    public ArrayList<String> tipoVehiculo(int cantidadClietes){
      
        ArrayList<String> lista_autos = new ArrayList();        
         String res = "";
         g = new Generador();  
        for(int i=1; i<= cantidadClietes; i++){
            double aux;
            if(cambio == 0)
                aux = g.exponencial(media);
            else 
                aux = g.normal(media,desvio);
            if(aux<1){ 
                 res = "auto";                  
                 auto++;
            }else{
                 res = "camioneta";
                 camioneta++;
            }
            lista_autos.add(res);
        }
         return lista_autos;   
        }
    
    public int getAuto(){
        return auto;
    }
    
    public int getCamioneta(){
        return camioneta;
    }        
    
    public Vehiculo(Dimension s){
        tamano = s;
    }
    public Dimension getDimension(){
        return tamano;
    }
    public double getSuperficie(){
        return tamano.getHeight()*tamano.getWidth();
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
