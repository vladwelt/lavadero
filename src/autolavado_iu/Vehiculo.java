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

public    Vehiculo() {
       
    }
     public ArrayList<String> tipoVehiculo(int cantidadClietes){
      
        ArrayList<String> lista_autos = new ArrayList();        
         String res = "";
         Generador g = new Generador();  
        for(int i=1; i<= cantidadClietes; i++){
            double aux = g.exponencial(1);
            if(aux<1){ 
                 res = "auto";                  
            }else{
                 res = "camioneta";                        
            }
            lista_autos.add(res);
        }
         return lista_autos;   
        }
    
   
    Dimension tamano;
    public Vehiculo(Dimension s){
        tamano = s;
    }
    public Dimension getDimension(){
        return tamano;
    }
    public double getSuperficie(){
        return tamano.getHeight()*tamano.getWidth();
    }
}
