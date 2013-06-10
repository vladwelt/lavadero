package autolavado_iu;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

public class Inicio extends JPanel implements ActionListener{
	

	private JFrame Padre;
	private JButton btnMD;	
        //Direcciones Multilataforma
        String a = new String("Imgs"+File.separator);

	public Inicio(JFrame Padre){
		this.Padre = Padre;
		ImageIcon imgFondo = new ImageIcon(a+"FondoCool.JPG");
		
		this.setBounds(0,20,Padre.getWidth(),Padre.getHeight());
		this.setFocusable(false);
		this.setLayout(null);
		
		Botones();
		
		JLabel lblFondo = new JLabel(imgFondo);
		lblFondo.setBounds(0, -80, imgFondo.getIconWidth(), imgFondo.getIconHeight());
		this.add(lblFondo);
	}
	
	private void Botones(){
		ImageIcon imgMD = new ImageIcon(a+"btnDisenio1.png");
		ImageIcon imgMD2 = new ImageIcon(a+"btnDisenio2.png");
		btnMD = new JButton(imgMD);
		btnMD.setSize(250, 250);
		btnMD.setLocation(225, 235);
                btnMD.setRolloverIcon(imgMD2);
		btnMD.setBorderPainted(false);
		btnMD.setContentAreaFilled(false);
		btnMD.addActionListener(this);
		this.add(btnMD);
		

	}


	public void actionPerformed(ActionEvent OnClic) {
		// TODO Auto-generated method stub
		if(OnClic.getSource() == btnMD){
			((Autolavado) Padre).cargarMD();
                        ((Autolavado) Padre).darReporte("Iniciando Disenio del Terreno.....");
		}
	}
}
