/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autolavado;
/**
 *
 * @author vlady
 */
public class Equipo {
    int capacidad;
    Dimension tamano;
    String nombre;
    public Equipo(int c,Dimension s){
        capacidad = c;
        tamano = s;
    }
    
    public int getCapacidad(){
        return capacidad;
    }
    public Dimension getDimension(){
        return tamano;
    }
    public double getSuperficie(){
        return tamano.getSuperficie();
    }
}
