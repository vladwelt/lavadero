package autolavado_iu;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class Diseño_Terreno extends JPanel implements MouseInputListener, ActionListener{
  

	private static final long serialVersionUID = 1L;
	
	JTextField filas = new JTextField();
	JTextField columnas = new JTextField();
	JButton btnOK = new JButton("OK");
	
	private JFrame Padre;
	
//Paneles de trabajo
	private JPanel panelTools;
        private JPanel panelAsfaltos;
        
	private Panel_Autolavado panelDesigner;
	
//Botones para el panel Maze
	private JButton btnPlay;
	
	private JButton btnCrear;
	
	//Elemento alpha
	private JLabel lblAlphaTool;

	
//Botones para el panel Tools
	//Botones de los tuneles
	private JButton btnTu3;
	private JButton btnTu4;
        private JButton btnTu5;
	private JButton btnTu6;
        //Entrada
        private JButton btnT1;
	//Salida
        private JButton btnT2;
        //Estacionamiento
        
        private JButton esta1;
        private JButton esta2;
       
         private JButton asfa1;
        private JButton asfa2;
         private JButton asfaL1;
        private JButton asfaL2;
         private JButton asfaL3;
        private JButton asfaL4;
        
        
        
        private JButton btnT3;
	private JButton btnT4;
	
	//Botones de la forma I y cruz
	private JButton btnTu1;
	private JButton btnTu2;
	private JButton btnCruz;

	public Diseño_Terreno(JFrame Padre, Terreno terreno){
		this.Padre = Padre;

		this.setSize(Padre.getWidth(),Padre.getHeight());
		this.setLocation(0, 23);
		this.setFocusable(false);
		this.setLayout(null);
		
		ImageIcon imgBGMD = new ImageIcon("Imgs/MazeD.jpg");
		JLabel lblBGMD = new JLabel(imgBGMD);
		lblBGMD.setBounds(0,0,imgBGMD.getIconWidth(),imgBGMD.getIconHeight());
		
		lblAlphaTool = new JLabel();
		lblAlphaTool.setVisible(false);
		this.add(lblAlphaTool);
		
		panelDesigner = new Panel_Autolavado(15, 30, 840,565,terreno);
		this.add(panelDesigner);
		
		OpcionesDesigner();
		addPanelTools();
                addPanelAsfaltos();
		
		this.add(lblBGMD);
		this.setVisible(true);
		((Autolavado)Padre).habilitarCargarGardar();
		((Autolavado)Padre).itemCargar.addActionListener(this);
		((Autolavado)Padre).itemGuardar.addActionListener(this);
	}
	private void OpcionesDesigner(){
	
		int Y = 640;
		btnPlay = new JButton("Simular");
		btnPlay.setBounds(250+5,Y,100,20);
		btnPlay.addActionListener(this);
		this.add(btnPlay);
		
		filas.setBounds(860, Y-20, 50, 20);
		this.add(filas);
		
		columnas.setBounds(910, Y-20, 50, 20);
		this.add(columnas);
		btnCrear = new JButton("TERRENO");
		btnCrear.setBounds(860, Y, 100,20);
		btnCrear.addMouseListener(this);
		btnCrear.addMouseMotionListener(this);
		btnCrear.addActionListener(this);
		this.add(btnCrear);
	}
	
    ////////PANEL TOOL//////////
	private void addPanelTools(){
		panelTools = new JPanel();
		panelTools.setBounds(860,30,195,535);
		panelTools.setLayout(null);
		panelTools.setOpaque(false);
		panelTools.setBorder(BorderFactory.createTitledBorder("Herramientas"));
		//A�adimos las herramientas al panel
		addTools();
		//A�adimos el panel a la ventana
		add(panelTools);
               
                
	}
        
       private void addTools(){
		int w = 85,	 h = 85;
		int left = 5, right = w+5;
		int y = 20;		
		//************TUNELES EN EL PANEL DE HERRAMIENTAS******************/////
		ImageIcon imgIV = new ImageIcon("Imgs/Tools/tunel1.jpg");
		btnTu1 = new JButton(imgIV);
		btnTu1.setBounds(left, y+0, w, h);
		btnTu1.setContentAreaFilled(false);
		btnTu1.addMouseMotionListener(this);
		btnTu1.addMouseListener(this);
        	panelTools.add(btnTu1);
     	
		ImageIcon imgIH = new ImageIcon("Imgs/Tools/tunel1_2.jpg");
		btnTu2 = new JButton(imgIH);
		btnTu2.setBounds(right, y+0, w, h);
		btnTu2.setContentAreaFilled(false);
		btnTu2.addMouseMotionListener(this);
		btnTu2.addMouseListener(this);
		panelTools.add(btnTu2);
		
		ImageIcon imgL1 = new ImageIcon("Imgs/Tools/tunel2.jpg");
		btnTu3 = new JButton(imgL1);
		btnTu3.setBounds(left, y+85, w, h);
		btnTu3.setContentAreaFilled(false);
		btnTu3.addMouseMotionListener(this);
		btnTu3.addMouseListener(this);
		panelTools.add(btnTu3);

		ImageIcon imgL2 = new ImageIcon("Imgs/Tools/tunel2_2.jpg");
		btnTu4 = new JButton(imgL2);
		btnTu4.setBounds(right, y+85, w, h);
		btnTu4.setContentAreaFilled(false);
		btnTu4.addMouseMotionListener(this);
		btnTu4.addMouseListener(this);
		panelTools.add(btnTu4);
		
		ImageIcon imgL3 = new ImageIcon("Imgs/Tools/tunel3.jpg");
		btnTu5 = new JButton(imgL3);
		btnTu5.setBounds(left, y+170, w, h);
		btnTu5.setContentAreaFilled(false);
		btnTu5.addMouseMotionListener(this);
		btnTu5.addMouseListener(this);
		panelTools.add(btnTu5);
		
		ImageIcon imgL4 = new ImageIcon("Imgs/Tools/tunel3_2.jpg");
		btnTu6 = new JButton(imgL4);
		btnTu6.setBounds(right, y+170, w, h);
		btnTu6.setContentAreaFilled(false);
		btnTu6.addMouseMotionListener(this);
		btnTu6.addMouseListener(this);
		panelTools.add(btnTu6);
		
		//*************ENTRADA Y SALIDA**************///
		ImageIcon imgT1 = new ImageIcon("Imgs/Tools/btnSalida.png");
		btnT1 = new JButton(imgT1);
		btnT1.setBounds(left, y+255, w, h);
		btnT1.setContentAreaFilled(false);
		btnT1.addMouseMotionListener(this);
		btnT1.addMouseListener(this);
		panelTools.add(btnT1);
		
		ImageIcon imgT2 = new ImageIcon("Imgs/Tools/btnEntrada.png");
		btnT2 = new JButton(imgT2);
		btnT2.setBounds(right, y+255, w, h);
		btnT2.setContentAreaFilled(false);
		btnT2.addMouseMotionListener(this);
		btnT2.addMouseListener(this);
		panelTools.add(btnT2);
		
                ImageIcon imgEs1 = new ImageIcon("Imgs/Tools/estac1.jpg");
		esta1 = new JButton(imgEs1);
		esta1.setBounds(left, y+340, w, h);
		esta1.setContentAreaFilled(false);
		esta1.addMouseMotionListener(this);
		esta1.addMouseListener(this);
		panelTools.add(esta1);
                
		ImageIcon imgEs2 = new ImageIcon("Imgs/Tools/estac2.jpg");
		esta2 = new JButton(imgEs2);
		esta2.setBounds(right, y+340, w, h);
		esta2.setContentAreaFilled(false);
		esta2.addMouseMotionListener(this);
		esta2.addMouseListener(this);
		panelTools.add(esta2);
                                
	}
	
        private void addPanelAsfaltos(){
		
                panelAsfaltos = new JPanel();
                panelAsfaltos.setBounds(1060,30,195,535);
		panelAsfaltos.setLayout(null);
		panelAsfaltos.setOpaque(false);
		panelAsfaltos.setBorder(BorderFactory.createTitledBorder("Asfaltos"));
		//A�adimos las herramientas al panel
		addAsfaltos();
		//A�adimos el panel a la ventana
		add(panelAsfaltos);
                
	}
        
	
        
        private void addAsfaltos(){
		int w = 85,	 h = 85;
		int left = 5, right = w+5;
		int y = 20;		
		//************TUNELES EN EL PANEL DE ASFALTOS******************/////
	
                ImageIcon imgAs1 = new ImageIcon("Imgs/Tools/btnAsfa1.JPG");
		asfa1 = new JButton(imgAs1);
		asfa1.setBounds(left, y+0, w, h);
		asfa1.setContentAreaFilled(false);
		asfa1.addMouseMotionListener(this);
		asfa1.addMouseListener(this);
		panelAsfaltos.add(asfa1);
                
                ImageIcon imgAs2 = new ImageIcon("Imgs/Tools/btnAsfa2.JPG");
		asfa2 = new JButton(imgAs2);
		asfa2.setBounds(right, y+0, w, h);
		asfa2.setContentAreaFilled(false);
		asfa2.addMouseMotionListener(this);
		asfa2.addMouseListener(this);
		panelAsfaltos.add(asfa2);
		
              
		ImageIcon imgAsL1 = new ImageIcon("Imgs/Tools/btnAsfaL1.JPG");
		asfaL1 = new JButton(imgAsL1);
		asfaL1.setBounds(left, y+85, w, h);
		asfaL1.setContentAreaFilled(false);
		asfaL1.addMouseMotionListener(this);
		asfaL1.addMouseListener(this);
		panelAsfaltos.add(asfaL1);

		ImageIcon imgAsL2 = new ImageIcon("Imgs/Tools/btnAsfaL2.JPG");
		asfaL2 = new JButton(imgAsL2);
		asfaL2.setBounds(right, y+85, w, h);
		asfaL2.setContentAreaFilled(false);
		asfaL2.addMouseMotionListener(this);
		asfaL2.addMouseListener(this);
		panelAsfaltos.add(asfaL2);
		
		ImageIcon imgAsL3 = new ImageIcon("Imgs/Tools/btnAsfaL3.JPG");
		asfaL3 = new JButton(imgAsL3);
		asfaL3.setBounds(left, y+170, w, h);
		asfaL3.setContentAreaFilled(false);
		asfaL3.addMouseMotionListener(this);
		asfaL3.addMouseListener(this);
		panelAsfaltos.add(asfaL3);
		
		ImageIcon imgAsL4 = new ImageIcon("Imgs/Tools/btnAsfaL4.JPG");
		asfaL4 = new JButton(imgAsL4);
		asfaL4.setBounds(right, y+170, w, h);
		asfaL4.setContentAreaFilled(false);
		asfaL4.addMouseMotionListener(this);
		asfaL4.addMouseListener(this);
		panelAsfaltos.add(asfaL4);
		
                
	}
	
        
        
        
        
        
	////////////EVENTOS DEL MOUSE/////////////////
	public void mouseClicked(MouseEvent onClicked) {}
	public void mouseEntered(MouseEvent onEntered) {}
	public void mouseExited(MouseEvent onExited) {}
	public void mousePressed(MouseEvent onPressed) {
		if(onPressed.getSource() == btnTu1)
			ShowAlfaIMG(btnTu1, new ImageIcon("Imgs/Tools/Alfas/tunel1_1.jpg"), onPressed.getX(), onPressed.getY());
		if(onPressed.getSource() == btnTu2)
			ShowAlfaIMG(btnTu2, new ImageIcon("Imgs/Tools/Alfas/tunel1_2.jpg"), onPressed.getX(), onPressed.getY());
		if(onPressed.getSource() == btnTu3)
			ShowAlfaIMG(btnTu3, new ImageIcon("Imgs/Tools/Alfas/tunel2_1.jpg"), onPressed.getX(), onPressed.getY());
                if(onPressed.getSource() == btnTu4)
			ShowAlfaIMG(btnTu4, new ImageIcon("Imgs/Tools/Alfas/tunel2_2.png"), onPressed.getX(), onPressed.getY());
		if(onPressed.getSource() == btnTu5)
			ShowAlfaIMG(btnTu5, new ImageIcon("Imgs/Tools/Alfas/tunel3_1.jpg"), onPressed.getX(), onPressed.getY());
		if(onPressed.getSource() == btnTu6)
			ShowAlfaIMG(btnTu6, new ImageIcon("Imgs/Tools/Alfas/tunel3_2.png"), onPressed.getX(), onPressed.getY());
	
		if(onPressed.getSource() == btnT1)
			ShowAlfaIMG(btnT1, new ImageIcon("Imgs/Terreno/Entrada.jpg"), onPressed.getX(), onPressed.getY());
		if(onPressed.getSource() == btnT2)
			ShowAlfaIMG(btnT2, new ImageIcon("Imgs/Terreno/Salida.jpg"), onPressed.getX(), onPressed.getY());
		
                if(onPressed.getSource() == esta1)
			ShowAlfaIMG(esta1, new ImageIcon("Imgs/Tools/Alfas/esta1.png"), onPressed.getX(), onPressed.getY());
		if(onPressed.getSource() == esta2)
			ShowAlfaIMG(esta2, new ImageIcon("Imgs/Tools/Alfas/esta2.png"), onPressed.getX(), onPressed.getY());
		                
	}
	public void mouseReleased(MouseEvent onReleased) {
		if(onReleased.getSource() == btnTu1){
			if(panelDesigner.hayTerreno()){
                            Point p = Convertir_aFC(btnTu1, onReleased.getX(), onReleased.getY());
				int[][] Pared = {{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                                                {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                                                {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                                                {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                                                {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                                                {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                                                {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                                                {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                                                {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4}};
                                panelDesigner.addTunel((int)p.getX(), (int)p.getY(), Pared);
                                panelDesigner.addAsfalto((int)p.getX(), (int)p.getY(), Pared);
                                
			}
			lblAlphaTool.setVisible(false);
		}
		
		
		if(onReleased.getSource() == btnTu2){
			if(panelDesigner.hayTerreno()){
				Point p = Convertir_aFC(btnTu2, onReleased.getX(), onReleased.getY());
                                int[][] Pared = {{4,4,4,4,7,4,4,4,4},
                                                                                                 {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4}};
                                panelDesigner.addTunel((int)p.getX(), (int)p.getY(), Pared);
                                panelDesigner.addAsfalto((int)p.getX(), (int)p.getY(), Pared);
			}
			lblAlphaTool.setVisible(false);
		}
		
		if(onReleased.getSource() == btnTu3){
			if(panelDesigner.hayTerreno()){
				Point p = Convertir_aFC(btnTu3, onReleased.getX(), onReleased.getY());
				int[][] Pared = {{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                                                                                                 {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                                                                                                 {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                                                                                                 {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                                                                                                 {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                                                                                                 {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                                                                                                 {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                                                                                                 {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                                                                                                 {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4}};
				panelDesigner.addTunel((int)p.getX(), (int)p.getY(), Pared);
                                panelDesigner.addAsfalto((int)p.getX(), (int)p.getY(), Pared);
			}
			lblAlphaTool.setVisible(false);
		}
		
		if(onReleased.getSource() == btnTu4){
			if(panelDesigner.hayTerreno()){
				Point p = Convertir_aFC(btnTu4, onReleased.getX(), onReleased.getY());
				int[][] Pared = {{4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4}};
				panelDesigner.addTunel((int)p.getX(), (int)p.getY(), Pared);
                                panelDesigner.addAsfalto((int)p.getX(), (int)p.getY(), Pared);
			}
			lblAlphaTool.setVisible(false);
		}
		
		if(onReleased.getSource() == btnTu5){
			if(panelDesigner.hayTerreno()){
				Point p = Convertir_aFC(btnTu5, onReleased.getX(), onReleased.getY());
				int[][] Pared = {{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                                                                                                 {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                                                                                                 {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                                                                                                 {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                                                                                                 {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                                                                                                 {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                                                                                                 {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                                                                                                 {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                                                                                                 {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4}};
				panelDesigner.addTunel((int)p.getX(), (int)p.getY(), Pared);
                                panelDesigner.addAsfalto((int)p.getX(), (int)p.getY(), Pared);
			}
			lblAlphaTool.setVisible(false);
		}
		
		if(onReleased.getSource() == btnTu6){
			if(panelDesigner.hayTerreno()){
				Point p = Convertir_aFC(btnTu6, onReleased.getX(), onReleased.getY());
				int[][] Pared = {{4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4},
                                                                                                {4,4,4,4,7,4,4,4,4}};
				panelDesigner.addTunel((int)p.getX(), (int)p.getY(), Pared);
                                panelDesigner.addAsfalto((int)p.getX(), (int)p.getY(), Pared);
			}
			lblAlphaTool.setVisible(false);
		}
		
		if(onReleased.getSource() == esta1){
			if(panelDesigner.hayTerreno()){
				Point p = Convertir_aFC(esta1, onReleased.getX(), onReleased.getY());
				int[][] Pared = {{5,5,5,5,5},
                                                 {7,7,7,7,7},
                                                 {5,5,5,5,5}
                                                };
				panelDesigner.addEstacionam((int)p.getX(), (int)p.getY(), Pared);
                                panelDesigner.addAsfalto((int)p.getX(), (int)p.getY(), Pared);
			}
			lblAlphaTool.setVisible(false);
		}
                
                if(onReleased.getSource() == esta2){
			if(panelDesigner.hayTerreno()){
				Point p = Convertir_aFC(esta2, onReleased.getX(), onReleased.getY());
				int[][] Pared = {{5,7,5},
                                                 {5,7,5},
                                                 {5,7,5},
                                                 {5,7,5},
                                                 {5,7,5}};
				panelDesigner.addEstacionam((int)p.getX(), (int)p.getY(), Pared);
                                panelDesigner.addAsfalto((int)p.getX(), (int)p.getY(), Pared);
			}
			lblAlphaTool.setVisible(false);
		}
                                
                
                if(onReleased.getSource() == asfa1){
			if(panelDesigner.hayTerreno()){
				Point p = Convertir_aFC2(asfa1, onReleased.getX(), onReleased.getY());
				int[][] Pared = {{6,7,6},{6,7,6},{6,7,6}};
				panelDesigner.addAsfalto((int)p.getX(), (int)p.getY(), Pared);
                        }
			lblAlphaTool.setVisible(false);
		}
                
                if(onReleased.getSource() == asfa2){
			if(panelDesigner.hayTerreno()){
				Point p = Convertir_aFC2(asfa2, onReleased.getX(), onReleased.getY());
				int[][] Pared = {{6,6,6},
                                                 {7,7,7},
                                                 {6,6,6}};
				panelDesigner.addAsfalto((int)p.getX(), (int)p.getY(), Pared);
			}
			lblAlphaTool.setVisible(false);
		}
                
                
                if(onReleased.getSource() == asfaL1){
			if(panelDesigner.hayTerreno()){
				Point p = Convertir_aFC2(asfaL1, onReleased.getX(), onReleased.getY());
				int[][] Pared = {{6,6,6},
                                                 {6,7,7},
                                                 {6,7,6}};
				panelDesigner.addAsfalto((int)p.getX(), (int)p.getY(), Pared);
			}
			lblAlphaTool.setVisible(false);
		}
                
                if(onReleased.getSource() == asfaL2){
			if(panelDesigner.hayTerreno()){
				Point p = Convertir_aFC2(asfaL2, onReleased.getX(), onReleased.getY());
				int[][] Pared = {{6,7,6},
                                                 {6,7,7},
                                                 {6,6,6}};
				panelDesigner.addAsfalto((int)p.getX(), (int)p.getY(), Pared);
			}
			lblAlphaTool.setVisible(false);
		}
                
                
                 if(onReleased.getSource() == asfaL3){
			if(panelDesigner.hayTerreno()){
				Point p = Convertir_aFC2(asfaL3, onReleased.getX(), onReleased.getY());
				int[][] Pared = {{6,6,6},
                                                 {7,7,6},
                                                 {6,7,6}};
				panelDesigner.addAsfalto((int)p.getX(), (int)p.getY(), Pared);
			}
			lblAlphaTool.setVisible(false);
		}
                
                if(onReleased.getSource() == asfaL4){
			if(panelDesigner.hayTerreno()){
				Point p = Convertir_aFC2(asfaL4, onReleased.getX(), onReleased.getY());
				int[][] Pared = {{6,7,6},
                                                 {7,7,6},
                                                 {6,6,6}};
				panelDesigner.addAsfalto((int)p.getX(), (int)p.getY(), Pared);
			}
			lblAlphaTool.setVisible(false);
		}
                
                
                
                
		if(onReleased.getSource() == btnT1){
			if(panelDesigner.hayTerreno()){
				Point p = Convertir_aFC(btnT1, onReleased.getX(), onReleased.getY());
				panelDesigner.setSalida((int)p.getX(), (int)p.getY());
			}
			lblAlphaTool.setVisible(false);
		}
		
		if(onReleased.getSource() == btnT2){
			if(panelDesigner.hayTerreno()){
				Point p = Convertir_aFC(btnT2, onReleased.getX(), onReleased.getY());
				panelDesigner.setEntrada((int)p.getX(), (int)p.getY());
			}
			lblAlphaTool.setVisible(false);
		}
	}
	public void mouseDragged(MouseEvent onDragged) {
		if(onDragged.getSource() == btnTu1)
			MoveAlfaIMG(btnTu1, onDragged.getX(), onDragged.getY());
		if(onDragged.getSource() == btnTu2)
			MoveAlfaIMG(btnTu2, onDragged.getX(), onDragged.getY());	
		
		if(onDragged.getSource() == btnTu3)
			MoveAlfaIMG(btnTu3, onDragged.getX(), onDragged.getY());
		if(onDragged.getSource() == btnTu4)
			MoveAlfaIMG(btnTu4, onDragged.getX(), onDragged.getY());
		if(onDragged.getSource() == btnTu5)
			MoveAlfaIMG(btnTu5, onDragged.getX(), onDragged.getY());
		if(onDragged.getSource() == btnTu6)
			MoveAlfaIMG(btnTu6, onDragged.getX(), onDragged.getY());
		
		if(onDragged.getSource() == btnT1)
			MoveAlfaIMG(btnT1, onDragged.getX(), onDragged.getY());
		if(onDragged.getSource() == btnT2)
			MoveAlfaIMG(btnT2, onDragged.getX(), onDragged.getY());
		
                if(onDragged.getSource() == esta1)
			MoveAlfaIMG(esta1, onDragged.getX(), onDragged.getY());
		if(onDragged.getSource() == esta2)
			MoveAlfaIMG(esta2, onDragged.getX(), onDragged.getY());
		
                if(onDragged.getSource() == btnT3)
			MoveAlfaIMG(btnT3, onDragged.getX(), onDragged.getY());
		if(onDragged.getSource() == btnT4)
			MoveAlfaIMG(btnT4, onDragged.getX(), onDragged.getY());
		
		if(onDragged.getSource() == btnCruz)
			MoveAlfaIMG(btnCruz, onDragged.getX(), onDragged.getY());
				
		if(onDragged.getSource() == btnT1)
			MoveAlfaIMG(btnT1, onDragged.getX(), onDragged.getY());
		
		if(onDragged.getSource() == btnT2)
			MoveAlfaIMG(btnT2, onDragged.getX(), onDragged.getY());
		
	}
	public void mouseMoved(MouseEvent onMoved) {}

	private Point Convertir_aFC(JButton btn, int mouseX, int mouseY){
		int iniX = (panelTools.getX()+ btn.getX() - panelDesigner.getX()) + (mouseX-panelDesigner.getComponent(0).getX());
		int iniY = (panelTools.getY()+ btn.getY() - panelDesigner.getY()) + (mouseY-panelDesigner.getComponent(0).getY());
		int f = (iniY - iniY%12)/12; 
		int c = (iniX - iniX%12)/12;
		System.out.println(f+" "+c);
		Point p = new Point(f,c);
		return p;
	}
	private Point Convertir_aFC2(JButton btn, int mouseX, int mouseY){
		int iniX = (panelAsfaltos.getX()+ btn.getX() - panelDesigner.getX()) + (mouseX-panelDesigner.getComponent(0).getX());
		int iniY = (panelAsfaltos.getY()+ btn.getY() - panelDesigner.getY()) + (mouseY-panelDesigner.getComponent(0).getY());
		int f = (iniY - iniY%12)/12; 
		int c = (iniX - iniX%12)/12;
		System.out.println(f+" "+c);
		Point p = new Point(f,c);
		return p;
	}
	private void MoveAlfaIMG(JButton btn, int mouseX, int mouseY){
		int x = panelTools.getX() + btn.getX()+ mouseX - lblAlphaTool.getWidth()/2;
		int y = panelTools.getY() + btn.getY()+ mouseY - lblAlphaTool.getHeight()/2;
		lblAlphaTool.setLocation(x, y);
	}
	private void ShowAlfaIMG(JButton btn, ImageIcon img, int mouseX, int mouseY){
		int w = img.getIconWidth();
		int h = img.getIconHeight();
		int x = panelTools.getX()+ btn.getX()+ mouseX-w/2;
		int y = panelTools.getY()+ btn.getY()+ mouseY-h/2;
		lblAlphaTool.setIcon(img);
		lblAlphaTool.setSize(w, h);
		lblAlphaTool.setLocation(x,	y);
		lblAlphaTool.setVisible(true);
	}
		
	private boolean esNumero(String txt){
		char[] pos= txt.toCharArray();
		boolean res=false;
		for (int i = 0; i < pos.length; i++) {
			if(pos[i]>=48&&pos[i]<=57){
				res=true;
			}else{
				res=false;
				i=pos.length+1;
			}
		}
		return res;
	}
	
	public void actionPerformed(ActionEvent onClicked) {
               
		if(onClicked.getSource() == btnCrear){
			if(!filas.getText().isEmpty() && !columnas.getText().isEmpty()){
				if(esNumero(filas.getText())&&esNumero(columnas.getText())){
				
					int f = Integer.parseInt(filas.getText()); 
					int c = Integer.parseInt(columnas.getText());
					
					if(f>=10&&f<=45&&c>=10&&c<=70){
						if(panelDesigner.hayTerreno()){
							int res = JOptionPane.showConfirmDialog(this, "Desa crear un nuevo Terreno"
																	,"Info:", JOptionPane.YES_NO_OPTION);
							if(res == JOptionPane.YES_OPTION){
								panelDesigner.EliminarTerreno();
								panelDesigner.CrearTerreno(f, c, true);
							}
						}else {
                                                    panelDesigner.CrearTerreno(f, c, true);
                                                    ((Autolavado)Padre).darReporte("Creando Terreno....\nAncho = "+ f +"\nLargo = "+ c );
                                                    
                                                }
					}else{
						JOptionPane.showMessageDialog(this, "Valores ingresados incorrectos","Error:", JOptionPane.WARNING_MESSAGE);
						
						if(f<10)filas.setText("10");
						else if(f>45)filas.setText("45");
						
						if(c<10)columnas.setText("10");
						else if(c>70)columnas.setText("70");
					}
				}else{
					JOptionPane.showMessageDialog(this, "Ingrese valores validos","Error:", JOptionPane.WARNING_MESSAGE);
					
				}
			}
			else{
				JOptionPane.showMessageDialog(this, "Ingrese valores","Error:", JOptionPane.WARNING_MESSAGE);
			}
                    
		}
                 if(onClicked.getSource() == btnPlay){
                     ((Autolavado)Padre).simular();
//                     panelDesigner.getDisenio().Mostrar();
                 }
//        if(onClicked.getSource() == btnPlay){
//                    Terreno terr = panelDesigner.getDisenio();
//                    
//                    double n = terr.intervaloDeLlegadas(8);
//                    int m = (int)(60/n);
//                    System.out.println("El intervalo de llegadas cada hora de los clientes es de: "+ n+ "   minutos");
//                    System.out.println("La cantidad de clientes que llegan en una hora es: "+ m);
//                    
//                    Cliente cliente = new Cliente();
//                    Vehiculo vehiculo = new Vehiculo();
//
//                    ArrayList<String> veh = vehiculo.tipoVehiculo(m);
//                    ArrayList<String> cli = cliente.tipoCliente(m);
//                    for (int i = 0; i < m; i++) {
//                        System.out.println("nro" + (i+1) +"\t"+"tipo vehiculo:" + veh.get(i)+"\t"+"tipo cliente:"+cli.get(i));
//        }
//                }
        
//                double largo = Double.parseDouble(this.filas.getText());
//                double ancho = Double.parseDouble(this.columnas.getText());
	}
        public Panel_Autolavado getPanelAutolavado(){
                    return panelDesigner;
                }
}