/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autolavado;

/**
 *
 * @author vlady
 */


public class Autolavado {

    /**
     * @param args the command line arguments
     */
    
    public Autolavado(){
        
    }
    
    public int capacidadDeLavaderos(Terreno t,Equipo e,Vehiculo v){
        int cant = 0;
        double capEst,supter;
        supter = t.getSuperficie();
        capEst = tamanoDeEstacionamiento(e,v);
        while(supter>0){
        supter -= (e.getSuperficie()+capEst);
        cant++;
        }
        return cant-1;
    }
    public int capacidadDeEstacionamientos(Terreno t,Equipo e,Vehiculo v){
        
        return this.capacidadDeLavaderos(t, e, v)*(e.getCapacidad()/2);
    }
    
    public double tamanoDeEstacionamiento(Equipo e,Vehiculo v){
        double result =0, aux;
        aux = e.getCapacidad()/2;
        result = (aux-1)*0.5*v.getDimension().getAncho();
        result += aux*v.getSuperficie();
        return result;
    }
    
    public boolean invesionInicial(){
        boolean result=false;
        return result;
    }
    
    public int cantidadDeAutolavados(Equipo e,int cantidad){
        int res=1;
        
        while(e.getCapacidad()<cantidad){
            cantidad-=e.getCapacidad();
            res++;
        }
        
        return res;
    }
    
    public int intervaloDeLlegadas(int tiempo){
        int sumador = 0, intervalo, contador = 0;
        tiempo = tiempo*60*60;
        while(tiempo>0){
            intervalo = (int)Math.random()*3600;
            //sumador = sumador+intervalo;
            
            tiempo -= intervalo;
            contador++;
        }
        return contador;
    }
    
    public static void main(String[] args) {
        Equipo e = new Equipo(45,new Dimension(2.5,10.375));
        Terreno t = new Terreno(new Dimension(45,56));
        Vehiculo v = new Vehiculo(new Dimension(1.760,4.275));
        Autolavado a = new Autolavado();
        int b = a.capacidadDeEstacionamientos(t, e, v);
        //String r = "12.12";
        System.out.println("asdfghjk "+ a.intervaloDeLlegadas(1));
        System.out.println("la cantidad de autolavados es: "+ a.cantidadDeAutolavados(e, 200));
        System.out.println("La capacidad de autolavados es :"+ a.capacidadDeLavaderos(t, e, v));
        System.out.println("La capacidad de Estacionamientos es :"+ b);
        System.out.println("Tamaño de un estacionamiento es :"+ a.tamanoDeEstacionamiento(e, v));
//        Vista q = new Vista();
        
        
    }
}
