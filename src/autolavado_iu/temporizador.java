package autolavado_iu;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author VladySP
 */
public class temporizador {
    Terreno terreno;
    Diseño_Terreno disenio;
    Timer timer;
    int id;
    int aux2=0;
    double seconds;
    private ArrayList<Dimension> ruta;
    String tipoDeVehiculo;
    String tipoDeCliente;
//    RemindTask tiempito;
    public temporizador(int seconds,Terreno terreno,Diseño_Terreno disenio,int id,Autolavado a,String tipoDeVehiculo,String tipoDeCliente) {
        this.seconds = seconds;
        this.terreno = terreno;
        this.disenio = disenio;
        ruta = terreno.camino();
        timer = new Timer();
        timer.schedule(new RemindTask(),0, seconds*100);
        this.id=id;
        this.tipoDeCliente = tipoDeCliente;
        this.tipoDeVehiculo = tipoDeVehiculo;
        a.darReporte("Ingreso "+id+ " Vehiculo"+"\nTipo de Vehiculo : "+tipoDeVehiculo+"\nTipo de Cliente : "+tipoDeCliente);
    }
    public void cancelar(){
        timer.cancel();
    }
    
    public  void controlar(){
        try{
        seconds = seconds*0.2;
        System.out.println("TIEMPO"+seconds*100);
        timer.wait();
        
        }
        catch(Exception ex){
//            System.out.println("dsadas"+ex);
        }
        try {
            timer.schedule(new RemindTask(),0, (int)seconds*100);
        } catch (Exception e) {
        }
    }
    
    class RemindTask extends TimerTask {

        @Override
        public void run() {
            if (aux2 > ruta.size() - 1) {
                        disenio.getPanelAutolavado().ponerSalida((int) ruta.get(aux2 - 1).getWidth(), (int) ruta.get(aux2 - 1).getHeight());
                        timer.cancel();
                    }
            else {
            if (aux2 == 1) 
                disenio.getPanelAutolavado().ponerEntrada((int) ruta.get(aux2 - 1).getWidth(), (int) ruta.get(aux2 - 1).getHeight());
            else if (aux2 != 0 )
                disenio.getPanelAutolavado().remarcar((int) ruta.get(aux2 - 1).getWidth(), (int) ruta.get(aux2 - 1).getHeight());
            
            disenio.getPanelAutolavado().marcar((int) ruta.get(aux2).getWidth(), (int) ruta.get(aux2).getHeight());
            aux2++;
            }
                    
        }
        
    }
}
