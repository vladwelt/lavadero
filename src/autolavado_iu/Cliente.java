/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autolavado_iu;

import java.util.ArrayList;
/**
 *
 * @author jimena
 */
public class Cliente {
    String nombre;
    
public ArrayList<String> tipoCliente(int cantidadClietes){
      
        ArrayList<String> lista_autos = new ArrayList();        
        //int  normal= 0;
        //
       // int socio=1;
        //
         String res = "";
        Generador g = new Generador();   
        for(int i=1; i<= cantidadClietes; i++){
            double aux = g.exponencial(1);
            String clientes;
            if(aux<1){ 
                 res = "normal"; 
                 //clientes = "Cliente "+i+"\t"+res;
                 //System.out.println(clientes);
            }else {
                 res = "socio";
                 //clientes = "Cliente "+i+"\t"+res;
                 //System.out.println(clientes);
            }
            lista_autos.add(res);
        }
         return lista_autos;   
        }
}


