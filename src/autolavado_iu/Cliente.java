package autolavado_iu;

import java.util.ArrayList;
/**
 *
 * @author jimena
 */
public class Cliente {

    private int socio = 0;
    private int normal = 0;
    private int cambio = 0;
    private double media=10;
    private double desvio=10;

    public ArrayList<String> tipoCliente(int cantidadClietes) {

        ArrayList<String> lista_autos = new ArrayList();
        String res = "";
        Generador g = new Generador();
        for (int i = 1; i <= cantidadClietes; i++) {
            double aux;
            if(cambio == 0)
                aux = g.exponencial(media);
            else 
                aux = g.normal(media,desvio);
            String clientes;
            if (aux < 1) {
                res = "normal";
                normal++;
            } else {
                res = "socio";
                socio++;
            }
            lista_autos.add(res);
        }
        return lista_autos;
    }
    
    public int getSocio(){
        return socio;
    }
    public int getNormal(){
        return normal;
    }
    public void setDistribucion(int cambio){
        this.cambio=cambio;
    }
    public void setMedia(double cambio){
        media = cambio;
    }
    public void setDesvio(double cambio){
        desvio = cambio;
    }
}


