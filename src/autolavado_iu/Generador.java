/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autolavado_iu;

/**
 *
 * @author jimena
 */
public class Generador {
    
    public double exponencial(double media){

        double res = -media*Math.log(Math.random());

        String aux = "" + res;
        if(aux.equalsIgnoreCase("NaN")){
            return exponencial(media);
        }else{
            return Math.rint(res*100)/100;
        }


    }

    public double normal(double media, double desvio){
        double z, res;

        z = Math.sqrt((-2 * Math.log(Math.random()))*Math.sin(2 * Math.PI *Math.random()));

        res = (z*desvio + media);

        String aux = ""+res;
        if(aux.equalsIgnoreCase("Null")){
            return normal(media, desvio);
        }else{
            return Math.rint(res*100)/100;
        }
    }
    
}
