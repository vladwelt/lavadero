/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autolavado;

/**
 *
 * @author vlady
 */
import javax.swing.*;
import java.awt.*;
import java.lang.String;

public class Vista extends JFrame{
    JMenuBar barra = new JMenuBar();
    
    public Vista(){
        setTitle("Autolavado");
        setBackground(java.awt.Color.BLACK);
        setSize(200,300);
        this.setJMenuBar(barra);
        
        setVisible(true);
    }
    
}
