/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autolavado;
/**
 *
 * @author vlady
 */
public class Dimension {
    double ancho;
    double largo;
    public Dimension(double a,double l){
        ancho=a;
        largo=l;
    }
    public double getAncho(){
        return ancho;
    }
    public double getLargo(){
        return largo;
    }
    public double getSuperficie(){
        return largo*ancho;
    }
}
