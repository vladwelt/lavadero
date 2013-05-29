/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autolavado;

/**
 *
 * @author vlady
 */
public class Vehiculo {
    Dimension tamano;
    public Vehiculo(Dimension s){
        tamano = s;
    }
    public Dimension getDimension(){
        return tamano;
    }
    public double getSuperficie(){
        return tamano.getSuperficie();
    }
}
