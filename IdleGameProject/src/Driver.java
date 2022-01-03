import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Driver extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Driver d = new Driver();
		
	}
	
	public Driver() {
		JFrame frame = new JFrame("ArrayList stuff");
		frame.setSize(800,600);
		frame.add(this);
		
		//this part makes sure the x button closes the program
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);
	}
	Timer t = new Timer(1, this);
	
	
	
	
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
