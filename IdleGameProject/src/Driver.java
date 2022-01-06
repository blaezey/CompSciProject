import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Driver extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener{
	String op1 = "option 1";
	String op2 = "option 2";
	String op3 = "option 3";
	String op4 = "option 4";
	String narr = "Hello ";
	Player p = new Player();
	Boolean a1 = false;
	
	
	public void paint(Graphics g) {
		g.fillRect(0, 0, 800, 600);
		Font font = new Font("Serif", Font.PLAIN, 16);
		g.setFont(font);
		g.setColor(new Color(255,255,255));
		
		//UI
		g.drawString("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", 12, 300);
		g.drawString("[1] "+op1, 20, 320);
		g.drawString("[2] "+op2, 20, 340);
		g.drawString("[3] "+op3, 20, 360);
		g.drawString("[4] "+op4, 20, 380);
		g.drawLine(400, 5, 400, 290);
		g.drawLine(400, 305, 400, 560);
		g.drawString("Health: "+p.getHealth(), 450, 285);
		g.drawString("Defense: "+p.getDefense(), 550, 285);
		g.drawString("Attack: "+p.getAttack(), 650, 285);
		
		//Narration
		if(a1) {
			for(int i = 1; i < narr.length(); i++) {
				g.drawString(narr.substring(0, i), 420, 50);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					Thread.currentThread().interrupt();
				}
			}
			a1 = false;
		}
		g.drawString(narr, 410, 50);
		
		
		
		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Driver d = new Driver();
		
	}
	
	public void newNarration(String newNarr, Graphics g) {
		narr = newNarr;
		for(int i = 1; i < narr.length(); i++) {
			g.drawString(narr.substring(0, i), 420, 50);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
			}
		}
	}
	
	
	public Driver() {
		JFrame frame = new JFrame("Text RPG");
		frame.setSize(800,600);
		frame.add(this);
		
		//this part makes sure the x button closes the program
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		frame.addKeyListener(this);
		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);
		frame.setVisible(true);
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


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==49) {
			narr = "1 pressed";
			System.out.println("1 pressed");
			op1 = "1";
			a1 = true;
		}
		if(arg0.getKeyCode()==50) {
			System.out.println("2 pressed");
		}
		if(arg0.getKeyCode()==51) {
			System.out.println("3 pressed");
		}
		if(arg0.getKeyCode()==52) {
			System.out.println("4 pressed");
		}
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
