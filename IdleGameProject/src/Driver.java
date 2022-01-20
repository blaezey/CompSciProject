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
	String op1 = "Open map";
	String op2 = "...";
	String op3 = "...";
	String op4 = "...";
	String narr = "You find yourself in the town plaza...";
	Player p = new Player();
	Boolean main = true;
	Boolean start = true;
	Boolean storeM = false;
	Boolean storeB = false;
	Boolean storeS = false;
	Boolean map = false;
	Boolean dead = false;
	Boolean hosp = false;
	Boolean dung = false;
	Boolean opt1 = false;
	Boolean opt2 = false;
	Boolean opt3 = false;
	Boolean opt4 = false;
	int aP, hP, dP;
	
	
	
	
	public void paint(Graphics g) {
		super.repaint();
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
		g.drawString("Health: "+p.getHealth()+"/"+p.getMaxHealth(), 450, 325);
		g.drawString("Defense: "+p.getDefense(), 560, 325);
		g.drawString("Attack: "+p.getAttack(), 660, 325);
		g.drawString("Gold: "+p.getGold(), 520, 345);
		g.drawString("Loot: "+p.getLoot(), 620, 345);
		
		//Narration
		g.drawString(narr, 20, 450);
		
		//Store prices
		aP = 10*(p.getAttack()-1);
		hP = 10*(p.getMaxHealth()-10);
		dP = 10*(p.getDefense());
		
		//Navigation logic
		if(main && opt1) {
			map = true;
			start = false;
			narr = "You open your map. Where do you want to go?";
			main = false;
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt4 = false;
		}
		
		if(p.getHealth()<=0) {
			dead = true;
			map = false;
			hosp = false;
			dung = false;
			storeM = false;
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt4 = false;
			narr = "You died...";
			op1 = "respawn";
			op2 = "...";
			op3 = "...";
			op4 = "...";
		}
		
		//Map Navigation
		if(map) {
			
			op1 = "Store";
			op2 = "Hospital";
			op3 = "Dungeons [CLOSED]";
			op4 = "...";
		}
		
		if(map && opt2) {
			hosp = true;
			start = false;
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt4 = false;
			map = false;
		}
		
		if(map && opt1) {
			storeM = true;
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt4 = false;
			map = false;
			start = false;
		}
		
		if(map && opt3) {
			dung = true;
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt4 = false;
		}
		
		
		//Hospital
		if(hosp) {
			narr = "You walk into the hospital. What now?";
			op1 = "Get healed";
			op2 = "Donate blood "+"(-"+p.getMaxHealth()/4+" health)";
			op3 = "Leave";
			op4 = "...";
		}
		
		if(hosp && opt1) {
			p.setHealth(p.getMaxHealth());
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt4 = false;
			if(p.getHealth()==p.getMaxHealth()) {
				narr = "You got your wounds healed.";
			}else {
				narr = "You are already at perfect health.";
			}
		}
		
		if(hosp && opt2) {
			narr = "You donated some blood and received compensation";
			p.setHealth(p.getHealth()-p.getMaxHealth()/4);
			p.setGold(p.getGold()+3);
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt4 = false;
		}
		
		if(hosp && opt3) {
			narr = "You head back out to town. Where do you go?";
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt4 = false;
			hosp = false;
			map = true;
			main = false;
			start = false;
		}
		
		//Store
		if(storeM) {
			narr = "You walk into the old store. The shopkeeper greets you.";
			op1 = "Buy";
			op2 = "Sell";
			op3 = "leave";
			op4 = "...";
			hosp = false;
			map = false;
		}
		
		if(storeM && opt1) {
			narr = "What do you want to buy?";
			op1 = "Health + " + hP;
			op2 = "Attack + " + aP;
			op3 = "Defense + " + dP;
			op4 = "Go back";
			storeB = true;
			storeM = false;
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt4 = false;
		}
		
		if(storeM && opt2) {
			narr = "What do you want to sell?";
			op1 = "Sell loot " + "("+(p.getLoot()*3)+"G)";
			op2 = "Go back";
			op3 = "...";
			op4 = "...";
			storeS = true;
			storeM = false;
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt4 = false;
			
		}
		
		if(storeM && opt3) {
			map = true;
			storeM = false;
			narr = "You head back out to the town.";
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt4 = false;
		}
		
		if(storeB && opt4) {
			storeM = true;
			storeB = false;
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt4 = false;
		}
		
		if(storeS && opt2) {
			storeM = true;
			storeS = false;
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt4 = false;
		}
		
		//Purchasing upgrades
		if(storeB && opt1) {
			if(p.getGold() >= hP) {
				p.setGold(p.getGold()-hP);
				p.setMaxHealth(p.getMaxHealth()+1);
			}else {
				narr = "insufficient funds.";
			}
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt4 = false;
		}
		
		
		
		
		
		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Driver d = new Driver();
		
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
		frame.repaint();
		frame.update(getGraphics());
		update(getGraphics());
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
		repaint();
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==49) {
			System.out.println("1 pressed");
			opt1 = true;
			opt2 = false;
			opt3 = false;
			opt4 = false;
		}
		if(arg0.getKeyCode()==50) {
			System.out.println("2 pressed");
			opt2 = true;
			opt1 = false;
			opt3 = false;
			opt4 = false;
		}
		if(arg0.getKeyCode()==51) {
			System.out.println("3 pressed");
			opt3 = true;
			opt1 = false;
			opt2 = false;
			opt4 = false;
		}
		if(arg0.getKeyCode()==52) {
			System.out.println("4 pressed");
			opt4 = true;
			opt1 = false;
			opt2 = false;
			opt3 = false;
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
