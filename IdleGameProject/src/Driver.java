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
	String op5 = "...";
	String narr = "You find yourself in the town plaza...";
	Player p = new Player();
	ArrayList<Gear> helmets = new ArrayList<Gear>();
	ArrayList<Gear> chests = new ArrayList<Gear>();
	ArrayList<Gear> legs = new ArrayList<Gear>();
	ArrayList<Gear> shoes = new ArrayList<Gear>();
	ArrayList<Gear> shields = new ArrayList<Gear>();
	ArrayList<Gear> swords = new ArrayList<Gear>();
	ArrayList<Gear> trinkets = new ArrayList<Gear>();
	Gear select;
	int sIndex;
	Gear currHelmet = new Gear(0);
	Gear currChestplate = new Gear(0);
	Gear currLeggings = new Gear(0);
	Gear currShoes = new Gear(0);
	Gear currShield = new Gear(0);
	Gear currSword = new Gear(0);
	Gear currTrinket = new Gear(0);
	
	//Enemies
	Enemy noEnemy = new Enemy(0, 0, 0, 0, 0, 0, "No Enemy", 0, 0);
	Enemy slime = new Enemy(5, 1, 1, 1, 2, 1, "Slime", 0, 2);
	Enemy bigSlime = new Enemy(7, 1, 1, 1, 2, 1, "Giant Slime", 0, 2);
	Enemy gRat = new Enemy(5, 2, 2, 1, 2, 2, "Giant Rat", 0, 2);
	Enemy gSpider = new Enemy(7, 2, 2, 2, 2, 3, "Giant Spider", 1, 2);
	Enemy flySwarm = new Enemy(10, 2, 2, 2, 2, 3, "Fly Swarm", 1, 3);
	Enemy goblin = new Enemy(15, 4, 3, 3, 5, 5, "Goblin", 2, 5);
	Enemy evilFairy = new Enemy(15, 4, 3, 3, 2, 5, "Evil Fairy", 2, 5);
	Enemy tenHorror = new Enemy(20, 5, 3, 5, 2, 7, "Tentacle Horror", 3, 5);
	Enemy bandit = new Enemy(25, 8, 3, 8, 2, 9, "Bandit", 5, 8);
	Enemy wizard = new Enemy(35, 12, 3, 10, 2, 12, "Wizard", 7, 10);
	Enemy vampire = new Enemy(40, 15, 3, 15, 2, 16, "Vampire", 8, 12);
	Enemy golem = new Enemy(80, 20, 4, 40, 20, 35, "Golem", 15, 20);
	Enemy basilisk = new Enemy(60, 30, 4, 20, 20, 35, "Basilisk", 15, 20);
	Enemy angryDemon = new Enemy(60, 40, 4, 25, 20, 35, "Angry Demon", 15, 20);
	Enemy currentE = noEnemy;
	int ePool = 0;
	
	//Navigation
	Boolean main = true;
	Boolean start = true;
	Boolean armory = false;
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
	Boolean opt5 = false;
	Boolean bag = false;
	Boolean vHelm = false;
	Boolean vChest = false;
	Boolean vLeg = false;
	Boolean vShoes = false;
	Boolean vShield = false;
	Boolean vSword = false;
	Boolean vTri = false;
	Boolean vPot = false;
	
	
	int page = 0;
	Boolean combat = false;
	
	//Store prices
	int aP, hP, dP;
	int sP = 15;
	int mP = 50;
	int lP = 120;
	
	//Colors
	Color white = new Color(255, 255, 255);
	Color lime = new Color(50, 205, 50);
	Color blue = new Color(137, 207, 240);
	Color purple = new Color(128, 0, 128);
	Color gold = new Color(255, 215, 0);
	
	
	
	public void paint(Graphics g) {
		super.repaint();
		g.fillRect(0, 0, 800, 600);
		Font font = new Font("Serif", Font.PLAIN, 16);
		Font bigFont = new Font("Serif", Font.PLAIN, 26);
		g.setFont(font);
		g.setColor(white);
		
		
		
		//UI
		g.drawString("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", 12, 300);
		g.drawString("[1] "+op1, 20, 320);
		g.drawString("[2] "+op2, 20, 340);
		g.drawString("[3] "+op3, 20, 360);
		g.drawString("[4] "+op4, 20, 380);
		g.drawString("[5] "+op5, 20, 400);
		g.drawLine(400, 5, 400, 290);
		g.drawLine(400, 305, 400, 560);
		g.drawString("Health: "+p.getHealth()+"/"+p.getMaxHealth(), 450, 325);
		g.drawString("Defense: "+p.getDefense(), 560, 325);
		g.drawString("Attack: "+p.getAttack(), 660, 325);
		g.drawString("Gold: "+p.getGold(), 520, 345);
		g.drawString("Loot: "+p.getLoot(), 620, 345);
		g.drawString("Enemy: "+ currentE.getName(), 540, 30);
		g.drawString("Health: "+ currentE.getHealth()+"/"+currentE.getMaxHealth(), 450, 60);
		g.drawString("Attack: "+ currentE.getAttack(), 570, 60);
		g.drawString("Defense: "+ currentE.getDefense(), 665, 60);
		
		if(p.getLevel()==15) {
			g.setColor(gold);
			g.drawString("Level "+ p.getLevel()+" - "+(p.getXp())+"/"+p.getXpReq(), 550, 550);
			g.setColor(white);
		}else {
			g.drawString("Level "+ p.getLevel()+" - "+(p.getXp())+"/"+p.getXpReq(), 550, 550);
		}
		
		//stat boosts from armor
		p.setHealthB(currHelmet.getHealthB()+currChestplate.getHealthB()+currLeggings.getHealthB()+currShoes.getHealthB()+currShield.getHealthB()+currSword.getHealthB()+currTrinket.getHealthB());
		p.setAttackB(currHelmet.getAttackB()+currChestplate.getAttackB()+currLeggings.getAttackB()+currShoes.getAttackB()+currShield.getAttackB()+currSword.getAttackB()+currTrinket.getAttackB());
		p.setDefenseB(currHelmet.getDefenseB()+currChestplate.getDefenseB()+currLeggings.getDefenseB()+currShoes.getDefenseB()+currShield.getDefenseB()+currSword.getDefenseB()+currTrinket.getDefenseB());
		
		//Inventory UI
		g.setFont(bigFont);
		g.drawString("Inventory", 150, 35);
		g.setFont(font);
		if(bag|| vPot) {
			g.drawString("Small Potions (x"+p.getsPotions()+")", 30, 70);
			g.drawString("Medium Potions (x"+p.getmPotions()+")", 30, 90);
			g.drawString("Large Potions (x"+p.getlPotions()+")", 30, 110);
		}
		if(vHelm) {
			for(int i = 0; i < helmets.size(); i++) {
				if(helmets.get(i).getTier()==1)g.setColor(white);
				if(helmets.get(i).getTier()==2)g.setColor(lime);
				if(helmets.get(i).getTier()==3)g.setColor(blue);
				if(helmets.get(i).getTier()==4)g.setColor(purple);
				if(helmets.get(i).getTier()==5)g.setColor(gold);
				g.drawString("[Helmet Lvl: "+helmets.get(i).getTier()+"]", 30, 70+(20*i));
				g.setColor(white);
			}
		}
		if(vChest) {
			for(int i = 0; i < chests.size(); i++) {
				if(chests.get(i).getTier()==1)g.setColor(white);
				if(chests.get(i).getTier()==2)g.setColor(lime);
				if(chests.get(i).getTier()==3)g.setColor(blue);
				if(chests.get(i).getTier()==4)g.setColor(purple);
				if(chests.get(i).getTier()==5)g.setColor(gold);
				g.drawString("[Chestplate Lvl: "+chests.get(i).getTier()+"]", 30, 70+(20*i));
				g.setColor(white);
			}
		}
		if(vLeg) {
			for(int i = 0; i < legs.size(); i++) {
				if(legs.get(i).getTier()==1)g.setColor(white);
				if(legs.get(i).getTier()==2)g.setColor(lime);
				if(legs.get(i).getTier()==3)g.setColor(blue);
				if(legs.get(i).getTier()==4)g.setColor(purple);
				if(legs.get(i).getTier()==5)g.setColor(gold);
				g.drawString("[Legs Lvl: "+legs.get(i).getTier()+"]", 30, 70+(20*i));
				g.setColor(white);
			}
		}
		if(vShoes) {
			for(int i = 0; i < shoes.size(); i++) {
				if(shoes.get(i).getTier()==1)g.setColor(white);
				if(shoes.get(i).getTier()==2)g.setColor(lime);
				if(shoes.get(i).getTier()==3)g.setColor(blue);
				if(shoes.get(i).getTier()==4)g.setColor(purple);
				if(shoes.get(i).getTier()==5)g.setColor(gold);
				g.drawString("[Shoes Lvl: "+shoes.get(i).getTier()+"]", 30, 70+(20*i));
				g.setColor(white);
			}
		}
		if(vShield) {
			for(int i = 0; i < shields.size(); i++) {
				if(shields.get(i).getTier()==1)g.setColor(white);
				if(shields.get(i).getTier()==2)g.setColor(lime);
				if(shields.get(i).getTier()==3)g.setColor(blue);
				if(shields.get(i).getTier()==4)g.setColor(purple);
				if(shields.get(i).getTier()==5)g.setColor(gold);
				g.drawString("[Shield Lvl: "+shields.get(i).getTier()+"]", 30, 70+(20*i));
				g.setColor(white);
			}
		}
		if(vSword) {
			for(int i = 0; i < swords.size(); i++) {
				if(swords.get(i).getTier()==1)g.setColor(white);
				if(swords.get(i).getTier()==2)g.setColor(lime);
				if(swords.get(i).getTier()==3)g.setColor(blue);
				if(swords.get(i).getTier()==4)g.setColor(purple);
				if(swords.get(i).getTier()==5)g.setColor(gold);
				g.drawString("[Sword Lvl: "+swords.get(i).getTier()+"]", 30, 70+(20*i));
				g.setColor(white);
			}
		}
		if(vTri) {
			for(int i = 0; i < trinkets.size(); i++) {
				if(trinkets.get(i).getTier()==1)g.setColor(white);
				if(trinkets.get(i).getTier()==2)g.setColor(lime);
				if(trinkets.get(i).getTier()==3)g.setColor(blue);
				if(trinkets.get(i).getTier()==4)g.setColor(purple);
				if(trinkets.get(i).getTier()==5)g.setColor(gold);
				g.drawString("[Trinket Lvl: "+trinkets.get(i).getTier()+"]", 30, 70+(20*i));
				g.setColor(white);
			}
		}
		
		
		//Equipped Items
		if(currHelmet.getTier()==0) {
			g.drawString("Helmet: Nothing", 210, 70);
		}else {
			g.drawString("Helmet: Helmet Lvl "+currHelmet.getTier(), 210, 70);
		}
		if(currChestplate.getTier()==0) {
			g.drawString("Chestplate: Nothing", 210, 100);
		}else {
			g.drawString("Chestplate: Chestplate Lvl "+currChestplate.getTier(), 210, 100);
		}
		if(currLeggings.getTier()==0) {
			g.drawString("Leggings: Nothing", 210, 130);
		}else {
			g.drawString("Leggings: Leggings Lvl "+currLeggings.getTier(), 210, 130);
		}
		if(currShoes.getTier()==0) {
			g.drawString("Shoes: Nothing", 210, 160);
		}else {
			g.drawString("Shoes: Shoes Lvl "+currShoes.getTier(), 210, 160);
		}
		if(currShield.getTier()==0) {
			g.drawString("Shield: Nothing", 210, 190);
		}else {
			g.drawString("Shield: Shield Lvl "+currShield.getTier(), 210, 190);
		}
		if(currSword.getTier()==0) {
			g.drawString("Sword: Nothing", 210, 220);
		}else {
			g.drawString("Sword: Sword Lvl "+currSword.getTier(), 210, 220);
		}
		if(currTrinket.getTier()==0) {
			g.drawString("Trinket: Nothing", 210, 250);
		}else {
			g.drawString("Trinket: Trinket Lvl "+currTrinket.getTier(), 210, 250);
		}
		
		//Armory controls
		if(vHelm || vChest || vLeg || vShoes || vShield || vSword || vTri || vPot) {
			g.drawString("[E] Equip selected item", 20, 510);
			g.drawString("[S] Sell selecetd item", 20, 530);
		}
		
		//Narration
		g.drawString(narr, 20, 460);
		
		//Leveling
		if(p.gettXp()>=0 && p.gettXp()<5) {
			p.setLevel(0);
			p.setXpReq(5);
			p.setXp(p.gettXp());
			p.setMaxHealth(10+p.getHealthB());
			p.setDefense(1+p.getDefenseB());
			p.setAttack(1+p.getAttackB());
		}else if(p.gettXp()>=5 && p.gettXp()<15) {
			p.setLevel(1);
			p.setXpReq(10);
			p.setMaxHealth(11+p.getHealthB());
			p.setDefense(1+p.getDefenseB());
			p.setAttack(2+p.getAttackB());
			p.setXp(p.gettXp()-5);
		}else if(p.gettXp()>=15 && p.gettXp()<40) {
			p.setLevel(2);
			p.setXpReq(25);
			p.setMaxHealth(13+p.getHealthB());
			p.setDefense(2+p.getDefenseB());
			p.setAttack(3+p.getAttackB());
			p.setXp(p.gettXp()-15);
		}else if(p.gettXp()>=40 && p.gettXp()<95) {
			p.setLevel(3);
			p.setXpReq(45);
			p.setMaxHealth(16+p.getHealthB());
			p.setDefense(4+p.getDefenseB());
			p.setAttack(4+p.getAttackB());
			p.setXp(p.gettXp()-40);
		}else if(p.gettXp()>=95 && p.gettXp()<195) {
			p.setLevel(4);
			p.setXpReq(100);
			p.setMaxHealth(19+p.getHealthB());
			p.setDefense(6+p.getDefenseB());
			p.setAttack(6+p.getAttackB());
			p.setXp(p.gettXp()-95);
		}else if(p.gettXp()>=195 && p.gettXp()<345) {
			p.setLevel(5);
			p.setXpReq(150);
			p.setMaxHealth(23+p.getHealthB());
			p.setDefense(10+p.getDefenseB());
			p.setAttack(9);
			p.setXp(p.gettXp()-195);
		}else if(p.gettXp()>=345 && p.gettXp()<545) {
			p.setLevel(6);
			p.setXpReq(200);
			p.setMaxHealth(27+p.getHealthB());
			p.setDefense(14+p.getDefenseB());
			p.setAttack(12);
			p.setXp(p.gettXp()-345);
		}else if(p.gettXp()>=545 && p.gettXp()<820) {
			p.setLevel(7);
			p.setXpReq(275);
			p.setMaxHealth(32+p.getHealthB());
			p.setDefense(18+p.getDefenseB());
			p.setAttack(16);
			p.setXp(p.gettXp()-545);
		}else if(p.gettXp()>=820 && p.gettXp()<1170) {
			p.setLevel(8);
			p.setXpReq(350);
			p.setMaxHealth(37+p.getHealthB());
			p.setDefense(23+p.getDefenseB());
			p.setAttack(20);
			p.setXp(p.gettXp()-820);
		}else if(p.gettXp()>=1170 && p.gettXp()<1595) {
			p.setLevel(9);
			p.setXpReq(425);
			p.setMaxHealth(43+p.getHealthB());
			p.setDefense(28);
			p.setAttack(25);
			p.setXp(p.gettXp()-1170);
		}else if(p.gettXp()>=1595 && p.gettXp()<2120) {
			p.setLevel(10);
			p.setXpReq(525);
			p.setMaxHealth(49+p.getHealthB());
			p.setDefense(33+p.getDefenseB());
			p.setAttack(30+p.getAttackB());
			p.setXp(p.gettXp()-1595);
		}else if(p.gettXp()>=2120 && p.gettXp()<2795) {
			p.setLevel(11);
			p.setXpReq(625);
			p.setMaxHealth(55+p.getHealthB());
			p.setDefense(38+p.getDefenseB());
			p.setAttack(36+p.getAttackB());
			p.setXp(p.gettXp()-2120);
		}else if(p.gettXp()>=2795 && p.gettXp()<3670) {
			p.setLevel(12);
			p.setXpReq(875);
			p.setMaxHealth(61+p.getHealthB());
			p.setDefense(45+p.getDefenseB());
			p.setAttack(42+p.getAttackB());
			p.setXp(p.gettXp()-2795);
		}else if(p.gettXp()>=3670 && p.gettXp()<4770) {
			p.setLevel(13);
			p.setXpReq(1100);
			p.setMaxHealth(68+p.getHealthB());
			p.setDefense(52+p.getDefenseB());
			p.setAttack(49+p.getAttackB());
			p.setXp(p.gettXp()-3670);
		}else if(p.gettXp()>=4770 && p.gettXp()<6170) {
			p.setLevel(14);
			p.setXpReq(1400);
			p.setMaxHealth(75+p.getHealthB());
			p.setDefense(59+p.getDefenseB());
			p.setAttack(56+p.getAttackB());
			p.setXp(p.gettXp()-4770);
		}else if(p.gettXp()>=6170) {
			p.setLevel(15);
			p.setXpReq(p.getXp());
			p.setMaxHealth(84+p.getHealthB());
			p.setDefense(67+p.getDefenseB());
			p.setAttack(64+p.getAttackB());
			p.setXp(0);
			
		}
		
		
		
		
		
		
		
		
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
		if(dead && opt1) {
			dead = false;
			p.setHealth(1);
			currentE.setCurrentEnemy(noEnemy);
			hosp = false;
			combat = false;
			dung = false;
			page = 0;
			narr = "You awaken in the town center.";
			map = true;
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt4 = false;
			opt5 = false;
			op1 = "Store";
			op2 = "Hospital";
			op3 = "Dungeons";
			op4 = "...";
			op5 = "...";
		}
		
		if(p.getHealth()<=0) {
			dead = true;
			map = false;
			hosp = false;
			dung = false;
			combat = false;
			main = false;
			storeM = false;
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt4 = false;
			narr = "You died...";
			op1 = "Respawn";
			op2 = "...";
			op3 = "...";
			op4 = "...";
			op5 = "...";
		}
		
		
		
		//Map Navigation
		if(map) {
			op1 = "Go to the store";
			op2 = "Go to the hospital";
			op3 = "Go to the armory";
			op4 = "Browse combat areas";
			op5 = "...";
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
		
		
		if(map && opt2) {
			hosp = true;
			narr = "You walk into the hospital. What now?";
			start = false;
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt4 = false;
			map = false;
		}
		
		if(map && opt3) {
			armory = true;
			dung = false;
			page = 0;
			start = false;
			narr = "You enter the armory.";
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt4 = false;
			opt5 = false;
			map = false;
		}
		
		if(map && opt4) {
			dung = true;
			narr = "You enter the main entrance of the dungeon.";
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt4 = false;
		}
		
		//Hospital
		if(hosp) {
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
			op3 = "Leave";
			op4 = "...";
			hosp = false;
			map = false;
		}
		
		if(storeM && opt1) {
			narr = "What do you want to buy?";
			op1 = "Small Potion ("+sP+"G)";
			op2 = "Medium Potion ("+mP+"G)";
			op3 = "Large Potion ("+lP+"G)";
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
			storeS = false;
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
			opt5 = false;
		}
		
		//Purchasing upgrades
		if(storeB) {
			if(opt1 && p.getGold()>=sP) {
				p.setsPotions(p.getsPotions()+1);
				p.setGold(p.getGold()-sP);
			}else if(opt1 && p.getGold()<sP) {
				narr = "You don't have enough gold for that.";
			}
			if(opt2 && p.getGold()>=mP) {
				p.setmPotions(p.getmPotions()+1);
				p.setGold(p.getGold()-mP);
			}else if(opt1 && p.getGold()<mP) {
				narr = "You don't have enough gold for that.";
			}
			if(opt3 && p.getGold()>=lP) {
				p.setlPotions(p.getlPotions()+1);
				p.setGold(p.getGold()-lP);
			}else if(opt1 && p.getGold()<lP) {
				narr = "You don't have enough gold for that.";
			}
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt4 = false;
			opt5 = false;
		}
		
		//Selling loot
		if(storeS && opt1) {
			p.setGold(p.getGold()+(p.getLoot()*3));
			p.setLoot(0);
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt4 = false;
			opt5 = false;
		}
		
		//Dungeons
		if(dung) {
			map = false;
			if(page == 0) {
				op1 = "Slime Plains (Recommended lvl: 0+)";
				op2 = "Dark Forest (Recommended lvl: 2+)";
				op3 = "Magic Badlands (Recommended lvl: 6+)";
				op4 = "More";
				op5 = "Leave";
				if(opt1) {
					ePool = 2;
					int rand = (int)(Math.random()*ePool)+1;
					if(rand==1) {
						currentE.setCurrentEnemy(slime);
					}else {
						currentE.setCurrentEnemy(bigSlime);
					}
					currentE.Spawn();
					combat = true;
					opt1 = false;
					opt2 = false;
					opt3 = false;
					opt4 = false;
					opt5 = false;
				}
				if(opt2) {
					ePool = 3;
					int rand = (int)(Math.random()*ePool+1);
					if(rand==1) {
						currentE.setCurrentEnemy(gRat);
					}else if(rand==2) {
						currentE.setCurrentEnemy(gSpider);
					}else if(rand==3) {
						currentE.setCurrentEnemy(flySwarm);
					}
					currentE.Spawn();
					combat = true;
					opt1 = false;
					opt2 = false;
					opt3 = false;
					opt4 = false;
					opt5 = false;
				}
				if(opt3) {
					ePool = 6;
					int rand = (int)(Math.random()*ePool+5);
					if(rand==5) {
						currentE.setCurrentEnemy(goblin);
					}else if(rand==6) {
						currentE.setCurrentEnemy(evilFairy);
					}else if(rand==7) {
						currentE.setCurrentEnemy(tenHorror);
					}else if(rand==8) {
						currentE.setCurrentEnemy(bandit);
					}else if(rand==9) {
						currentE.setCurrentEnemy(wizard);
					}else if(rand==10) {
						currentE.setCurrentEnemy(vampire);
					}
					currentE.Spawn();
					combat = true;
					opt1 = false;
					opt2 = false;
					opt3 = false;
					opt4 = false;
					opt5 = false;
				}
				if(opt4) {
					page = 1;
				}
				if(opt5) {
					map = true;
					narr = "You leave the dungeons and appear in the town center.";
					dung = false;
					opt1 = false;
					opt2 = false;
					opt3 = false;
					opt4 = false;
					opt5 = false;
				}
			}
			
			if(page == 1) {
				op1 = "Hallowed Coliseum (Recommended lvl: 12+)";
				op2 = "Back";
				op3 = "Leave";
				op4 = "...";
				op5 = "...";
				if(opt1) {
					ePool = 1;
					int rand = (int)(Math.random()*ePool+1);
					if(rand==1) {
						currentE.setCurrentEnemy(golem);
					}
					currentE.Spawn();
					combat = true;
					opt1 = false;
					opt2 = false;
					opt3 = false;
					opt4 = false;
					opt5 = false;
				}
				if(opt2) {
					page = 0;
					opt1 = false;
					opt2 = false;
					opt3 = false;
					opt4 = false;
					opt5 = false;
				}
				
				if(opt3) {
					map = true;
					page = 0;
					narr = "You leave the dungeons and appear in the town center.";
					dung = false;
					opt1 = false;
					opt2 = false;
					opt3 = false;
					opt4 = false;
					opt5 = false;
				}
				
			}
	
		}
		
		
		//armory
		if(armory) {
			
			if(page==0) {
				op1 = "Open helmet inventory";
				op2 = "Open chestplate inventory";
				op3 = "Open leggings inventory";
				op4 = "More";
				op5 = "Leave";
				if(opt1) {
					select = null;
					vHelm = true;
					vChest = false;
					vLeg = false;
					opt1 = false;
					opt2 = false;
					opt3 = false;
					opt4 = false;
					opt5 = false;
				}else if(opt2) {
					select = null;
					vHelm = false;
					vChest = true;
					vLeg = false;
					opt1 = false;
					opt2 = false;
					opt3 = false;
					opt4 = false;
					opt5 = false;
				}else if(opt3) {
					select = null;
					vHelm = false;
					vChest = false;
					vLeg = true;
					opt1 = false;
					opt2 = false;
					opt3 = false;
					opt4 = false;
					opt5 = false;
				}else if(opt4) {
					page = 1;
					select = null;
					vHelm = false;
					vChest = false;
					vLeg = false;
					opt1 = false;
					opt2 = false;
					opt3 = false;
					opt4 = false;
					opt5 = false;
				}else if(opt5){
					armory = false;
					select = null;
					vHelm = false;
					vChest = false;
					vLeg = false;
					page = 0;
					narr = "You leave the armory and arrive in town.";
					map = true;
					opt1 = false;
					opt2 = false;
					opt3 = false;
					opt4 = false;
					opt5 = false;
				}
			}else if(page==1) {
				op1 = "Open shoe inventory";
				op2 = "Open shield inventory";
				op3 = "Open sword inventory";
				op4 = "Less";
				op5 = "More";
				if(opt1) {
					select = null;
					vShoes = true;
					vShield = false;
					vSword = false;
					opt1 = false;
					opt2 = false;
					opt3 = false;
					opt4 = false;
					opt5 = false;
				}else if(opt2) {
					select = null;
					vShoes = false;
					vShield = true;
					vSword = false;
					opt1 = false;
					opt2 = false;
					opt3 = false;
					opt4 = false;
					opt5 = false;
				}else if(opt3) {
					select = null;
					vShoes = false;
					vShield = false;
					vSword = true;
					opt1 = false;
					opt2 = false;
					opt3 = false;
					opt4 = false;
					opt5 = false;
				}else if(opt4) {
					select = null;
					page = 0;
					vShoes = false;
					vShield = false;
					vSword = false;
					opt1 = false;
					opt2 = false;
					opt3 = false;
					opt4 = false;
					opt5 = false;
				}else if(opt5){
					select = null;
					page = 2;
					vShoes = false;
					vShield = false;
					vSword = false;
					opt1 = false;
					opt2 = false;
					opt3 = false;
					opt4 = false;
					opt5 = false;
				}
			}else if(page==2) {
				op1 = "Open trinket inventory";
				op2 = "Open potion inventory";
				op3 = "Go back";
				op4 = "...";
				op5 = "...";
				if(opt1) {
					select = null;
					vTri = true;
					vPot = false;
					opt1 = false;
					opt2 = false;
					opt3 = false;
					opt4 = false;
					opt5 = false;
				}else if(opt2) {
					select = null;
					vTri = false;
					vPot = true;
					opt1 = false;
					opt2 = false;
					opt3 = false;
					opt4 = false;
					opt5 = false;
				}else if(opt3) {
					select = null;
					page = 1;
					vTri = false;
					vPot = false;
					opt1 = false;
					opt2 = false;
					opt3 = false;
					opt4 = false;
					opt5 = false;
				}
			}
			
	
		}
		
		
		
		
		
		
		
		
		//Combat
		if(combat && !dead) {
			dung = false;
			
			
			if(!bag) {
				op1 = "Attack";
				op2 = "Do nothing";
				op3 = "Open Bag";
				op4 = "Leave";
				op5 = "...";
			}
			
			if(opt1) {
				if(bag) {
					if(p.getsPotions()>0) {
						narr = "You used a small potion.";
						p.Heal(5);
						p.setsPotions(p.getsPotions()-1);
						int roll = currentE.Attack(p);
						p.setHealth(p.getHealth()-roll);
						//narr += "/n "+currentE.getName()+" hit you for "+roll+"damage.";
					}else {
						narr = "You don't have any small potions.";
					}
				}
				if(!bag) {
					int currDamage = p.Attack(currentE);
					narr = "You swing at the "+currentE.getName()+". ";
					currentE.setHealth(currentE.getHealth()-currDamage);
					//narr += currentE.getName()+" hit you for "+currDamage+" damage.";
					
					if(currentE.getHealth()<=0) {
						int goldReturn = currentE.GiveGold();
						p.setGold(p.getGold()+goldReturn);
						currentE.setHealth(0);
						narr = "You defeated the monster. +"+currentE.getXpY()+" XP";
						p.settXp(p.gettXp()+currentE.getXpY());
						
						//Loot returns
						int t = (int)(Math.random()*1000+1);
						int s = (int)(Math.random()*7);
						if(currentE.getMNumber()==1 || currentE.getMNumber()==2) {
							if(t<=990) {
								Gear item = new Gear(1, s);
								item.getItem();
								if(s==0) {
									helmets.add(item);
									if(helmets.size()>12)helmets.remove(0);
								}else if(s==1) {
									chests.add(item);
									if(chests.size()>12)chests.remove(0);
								}else if(s==2) {
									legs.add(item);
									if(legs.size()>12)legs.remove(0);
								}else if(s==3) {
									shoes.add(item);
									if(shoes.size()>12)shoes.remove(0);
								}else if(s==4) {
									shields.add(item);
									if(shields.size()>12)shields.remove(0);
								}else if(s==5) {
									swords.add(item);
									if(swords.size()>12)swords.remove(0);
								}else if(s==6) {
									trinkets.add(item);
									if(trinkets.size()>12)trinkets.remove(0);
								}
							}else if(t!=993) {
								Gear item = new Gear(2, s);
								item.getItem();
								if(s==1) {
									helmets.add(item);
									if(helmets.size()>12)helmets.remove(0);
								}else if(s==2) {
									chests.add(item);
									if(chests.size()>12)chests.remove(0);
								}else if(s==3) {
									legs.add(item);
									if(legs.size()>12)legs.remove(0);
								}else if(s==4) {
									shoes.add(item);
									if(shoes.size()>12)shoes.remove(0);
								}else if(s==5) {
									shields.add(item);
									if(shields.size()>12)shields.remove(0);
								}else if(s==6) {
									swords.add(item);
									if(swords.size()>12)swords.remove(0);
								}else if(s==7) {
									trinkets.add(item);
									if(trinkets.size()>12)trinkets.remove(0);
								}
							}else {
								Gear item = new Gear(3, s);
								item.getItem();
								if(s==1) {
									helmets.add(item);
									if(helmets.size()>12)helmets.remove(0);
								}else if(s==2) {
									chests.add(item);
									if(chests.size()>12)chests.remove(0);
								}else if(s==3) {
									legs.add(item);
									if(legs.size()>12)legs.remove(0);
								}else if(s==4) {
									shoes.add(item);
									if(shoes.size()>12)shoes.remove(0);
								}else if(s==5) {
									shields.add(item);
									if(shields.size()>12)shields.remove(0);
								}else if(s==6) {
									swords.add(item);
									if(swords.size()>12)swords.remove(0);
								}else if(s==7) {
									trinkets.add(item);
									if(trinkets.size()>12)trinkets.remove(0);
								}
							}
						}else if(currentE.getMNumber()==3) {
							if(t <= 700) {
								Gear item = new Gear(1, s);
								item.getItem();
								if(s==1) {
									helmets.add(item);
									if(helmets.size()>12)helmets.remove(0);
								}else if(s==2) {
									chests.add(item);
									if(chests.size()>12)chests.remove(0);
								}else if(s==3) {
									legs.add(item);
									if(legs.size()>12)legs.remove(0);
								}else if(s==4) {
									shoes.add(item);
									if(shoes.size()>12)shoes.remove(0);
								}else if(s==5) {
									shields.add(item);
									if(shields.size()>12)shields.remove(0);
								}else if(s==6) {
									swords.add(item);
									if(swords.size()>12)swords.remove(0);
								}else if(s==7) {
									trinkets.add(item);
									if(trinkets.size()>12)trinkets.remove(0);
								}
							}else if(t>700 && t <=990) {
								Gear item = new Gear(2, s);
								item.getItem();
								if(s==1) {
									helmets.add(item);
									if(helmets.size()>12)helmets.remove(0);
								}else if(s==2) {
									chests.add(item);
									if(chests.size()>12)chests.remove(0);
								}else if(s==3) {
									legs.add(item);
									if(legs.size()>12)legs.remove(0);
								}else if(s==4) {
									shoes.add(item);
									if(shoes.size()>12)shoes.remove(0);
								}else if(s==5) {
									shields.add(item);
									if(shields.size()>12)shields.remove(0);
								}else if(s==6) {
									swords.add(item);
									if(swords.size()>12)swords.remove(0);
								}else if(s==7) {
									trinkets.add(item);
									if(trinkets.size()>12)trinkets.remove(0);
								}
							}else if(t!=993) {
								Gear item = new Gear(3, s);
								item.getItem();
								if(s==1) {
									helmets.add(item);
									if(helmets.size()>12)helmets.remove(0);
								}else if(s==2) {
									chests.add(item);
									if(chests.size()>12)chests.remove(0);
								}else if(s==3) {
									legs.add(item);
									if(legs.size()>12)legs.remove(0);
								}else if(s==4) {
									shoes.add(item);
									if(shoes.size()>12)shoes.remove(0);
								}else if(s==5) {
									shields.add(item);
									if(shields.size()>12)shields.remove(0);
								}else if(s==6) {
									swords.add(item);
									if(swords.size()>12)swords.remove(0);
								}else if(s==7) {
									trinkets.add(item);
									if(trinkets.size()>12)trinkets.remove(0);
								}
							}else {
								Gear item = new Gear(4, s);
								item.getItem();
								if(s==1) {
									helmets.add(item);
									if(helmets.size()>12)helmets.remove(0);
								}else if(s==2) {
									chests.add(item);
									if(chests.size()>12)chests.remove(0);
								}else if(s==3) {
									legs.add(item);
									if(legs.size()>12)legs.remove(0);
								}else if(s==4) {
									shoes.add(item);
									if(shoes.size()>12)shoes.remove(0);
								}else if(s==5) {
									shields.add(item);
									if(shields.size()>12)shields.remove(0);
								}else if(s==6) {
									swords.add(item);
									if(swords.size()>12)swords.remove(0);
								}else if(s==7) {
									trinkets.add(item);
									if(trinkets.size()>12)trinkets.remove(0);
								}
							}
						}else if(currentE.getMNumber()==4) {
							if(t <= 600) {
								Gear item = new Gear(1, s);
								item.getItem();
								if(s==1) {
									helmets.add(item);
									if(helmets.size()>12)helmets.remove(0);
								}else if(s==2) {
									chests.add(item);
									if(chests.size()>12)chests.remove(0);
								}else if(s==3) {
									legs.add(item);
									if(legs.size()>12)legs.remove(0);
								}else if(s==4) {
									shoes.add(item);
									if(shoes.size()>12)shoes.remove(0);
								}else if(s==5) {
									shields.add(item);
									if(shields.size()>12)shields.remove(0);
								}else if(s==6) {
									swords.add(item);
									if(swords.size()>12)swords.remove(0);
								}else if(s==7) {
									trinkets.add(item);
									if(trinkets.size()>12)trinkets.remove(0);
								}
							}else if(t > 600 && t <= 900) {
								Gear item = new Gear(2, s);
								item.getItem();
								if(s==1) {
									helmets.add(item);
									if(helmets.size()>12)helmets.remove(0);
								}else if(s==2) {
									chests.add(item);
									if(chests.size()>12)chests.remove(0);
								}else if(s==3) {
									legs.add(item);
									if(legs.size()>12)legs.remove(0);
								}else if(s==4) {
									shoes.add(item);
									if(shoes.size()>12)shoes.remove(0);
								}else if(s==5) {
									shields.add(item);
									if(shields.size()>12)shields.remove(0);
								}else if(s==6) {
									swords.add(item);
									if(swords.size()>12)swords.remove(0);
								}else if(s==7) {
									trinkets.add(item);
									if(trinkets.size()>12)trinkets.remove(0);
								}
							}else if(t > 900 && t <= 990) {
								Gear item = new Gear(3, s);
								item.getItem();
								if(s==1) {
									helmets.add(item);
									if(helmets.size()>12)helmets.remove(0);
								}else if(s==2) {
									chests.add(item);
									if(chests.size()>12)chests.remove(0);
								}else if(s==3) {
									legs.add(item);
									if(legs.size()>12)legs.remove(0);
								}else if(s==4) {
									shoes.add(item);
									if(shoes.size()>12)shoes.remove(0);
								}else if(s==5) {
									shields.add(item);
									if(shields.size()>12)shields.remove(0);
								}else if(s==6) {
									swords.add(item);
									if(swords.size()>12)swords.remove(0);
								}else if(s==7) {
									trinkets.add(item);
									if(trinkets.size()>12)trinkets.remove(0);
								}
							}else if(t != 993) {
								Gear item = new Gear(4, s);
								item.getItem();
								if(s==1) {
									helmets.add(item);
									if(helmets.size()>12)helmets.remove(0);
								}else if(s==2) {
									chests.add(item);
									if(chests.size()>12)chests.remove(0);
								}else if(s==3) {
									legs.add(item);
									if(legs.size()>12)legs.remove(0);
								}else if(s==4) {
									shoes.add(item);
									if(shoes.size()>12)shoes.remove(0);
								}else if(s==5) {
									shields.add(item);
									if(shields.size()>12)shields.remove(0);
								}else if(s==6) {
									swords.add(item);
									if(swords.size()>12)swords.remove(0);
								}else if(s==7) {
									trinkets.add(item);
									if(trinkets.size()>12)trinkets.remove(0);
								}
							}else {
								Gear item = new Gear(5, s);
								item.getItem();
								if(s==1) {
									helmets.add(item);
									if(helmets.size()>12)helmets.remove(0);
								}else if(s==2) {
									chests.add(item);
									if(chests.size()>12)chests.remove(0);
								}else if(s==3) {
									legs.add(item);
									if(legs.size()>12)legs.remove(0);
								}else if(s==4) {
									shoes.add(item);
									if(shoes.size()>12)shoes.remove(0);
								}else if(s==5) {
									shields.add(item);
									if(shields.size()>12)shields.remove(0);
								}else if(s==6) {
									swords.add(item);
									if(swords.size()>12)swords.remove(0);
								}else if(s==7) {
									trinkets.add(item);
									if(trinkets.size()>12)trinkets.remove(0);
								}
							}
						}
						
						
						//respawning random monster
						int rand = (int)(Math.random()*10+1);
						int tier = currentE.getMNumber();
						
						if(tier==1) {
							if(rand==1) {
								currentE.setCurrentEnemy(slime);
							}else if(rand==2) {
								currentE.setCurrentEnemy(bigSlime);
							}
						}else if(tier==2) {
							if(rand==1) {
								currentE.setCurrentEnemy(gRat);
							}else if(rand==2) {
								currentE.setCurrentEnemy(gSpider);
							}else if(rand==3) {
								currentE.setCurrentEnemy(flySwarm);
							}
						}else if(tier==3) {
							if(rand==1) {
								currentE.setCurrentEnemy(goblin);
							}else if(rand==2) {
								currentE.setCurrentEnemy(evilFairy);
							}else if(rand==3) {
								currentE.setCurrentEnemy(tenHorror);
							}else if(rand==4) {
								currentE.setCurrentEnemy(bandit);
							}else if(rand==5) {
								currentE.setCurrentEnemy(wizard);
							}else if(rand==6) {
								currentE.setCurrentEnemy(vampire);
							}
						}else if(tier==4) {
							currentE.setCurrentEnemy(golem);
						}
						
						currentE.Spawn();
					}else {
						currDamage = currentE.Attack(p);
						p.setHealth(p.getHealth()-currDamage);
					}
					
				}
				opt1 = false;
				opt2 = false;
				opt3 = false;
				opt4 = false;
				opt5 = false;
			}
			if(opt2) {
				if(bag) {
					if(p.getmPotions()>0) {
						narr = "You used a medium potion.";
						p.Heal(15);
						p.setmPotions(p.getmPotions()-1);
						currentE.Attack(p);
					}else {
						narr = "You don't have any medium potions.";
					}
				}
				if(!bag) {
					narr = "You stare at the "+currentE.getName()+". It stares back.";
					int currentDamage;
					currentDamage = currentE.Attack(p);
					p.setHealth(p.getHealth()-currentDamage);
					if(p.getHealth()<=0) {
						p.setHealth(0);
					}
				}
				
				opt1 = false;
				opt2 = false;
				opt3 = false;
				opt4 = false;
				opt5 = false;
			}
			if(opt3) {
				narr = "You open up your bag.";
				op1 = "Use Small Potion (Heals 5 health)";
				op2 = "Use Medium Potion (Heals 15 health)";
				op3 = "Use Large Potion (Heals 30 health)";
				op4 = "Close";
				op5 = "...";
				if(bag) {
					if(p.getlPotions()>0) {
						narr = "You used a large potion.";
						p.Heal(30);
						p.setlPotions(p.getlPotions()-1);
						currentE.Attack(p);
					}else {
						narr = "You don't have any large potions.";
					}
				}
				if(!bag) {
					bag = true;
				}
				
				
				
				
				opt1 = false;
				opt2 = false;
				opt3 = false;
				opt4 = false;
				opt5 = false;
			}
			if(opt4) {
				if(!bag && opt4) {
					narr = "You flee from the battle.";
					combat = false;
					currentE.setCurrentEnemy(noEnemy);
					
					dung = true;
					map = false;
				}
				if(bag) {
					bag = false;
					narr = "You close your bag.";
				}
				opt1 = false;
				opt2 = false;
				opt3 = false;
				opt4 = false;
				opt5 = false;

			}
			
			
		}
		
	
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Driver d = new Driver();
		
	}
	
	
	public Driver() {
		JFrame frame = new JFrame("Text RPG v1.3.0");
		frame.setSize(800,600);
		frame.add(this);
		
		System.out.println("Welcome to Text RPG.");
		System.out.println("-------------------------------------------------------------");
		System.out.println("Use numbers 1-5 to navigate the game or click on the option itself.");
		System.out.println("Fighting monsters is the name of the game (not literally).");
		System.out.println("All monsters drop loot when killed which can");
		System.out.println("be equipped or sold at the armory.");
		System.out.println("To sell or equip items: click on the item, then either press 'e' or 's' to equip or sell it.");
		System.out.println("");
		System.out.println("Enjoy the game!!");
		System.out.println("");
		
		
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
//		if(e.getX()>=28 && e.getX()<=310 && e.getY()>=338 && e.getY()<=352) {
//			opt1 = true;
//			System.out.println("1 clicked");
//		}
//		if(e.getX()>=28 && e.getX()<=310 && e.getY()>=358 && e.getY()<=372) {
//			opt2 = true;
//			System.out.println("2 clicked");
//		}
//		if(e.getX()>=28 && e.getX()<=310 && e.getY()>=380 && e.getY()<=392) {
//			opt3 = true;
//			System.out.println("3 clicked");
//		}
//		if(e.getX()>=28 && e.getX()<=310 && e.getY()>=398 && e.getY()<=412) {
//			opt4 = true;
//			System.out.println("4 clicked");
//		}
//		if(e.getX()>=28 && e.getX()<=310 && e.getY()>=418 && e.getY()<=432) {
//			opt5 = true;
//			System.out.println("5 clicked");
//		}
//		System.out.println("x"+e.getX()+" y"+e.getY());
		
		if(e.getX()>=39 && e.getX()<=149 && e.getY()>=89 && e.getY()<=103) {//1
			if(vHelm && helmets.size()>=1) {
				select = helmets.get(0);
				sIndex = 0;
			}else if(vChest && chests.size()>=1) {
				select = chests.get(0);
				sIndex = 0;
			}else if(vLeg && legs.size()>=1) {
				select = legs.get(0);
				sIndex = 0;
			}else if(vShoes && shoes.size()>=1) {
				select = shoes.get(0);
				sIndex = 0;
			}else if(vShield && shields.size()>=1) {
				select = shields.get(0);
				sIndex = 0;
			}else if(vSword && swords.size()>=1) {
				select = swords.get(0);
				sIndex = 0;
			}else if(vTri && trinkets.size()>=1) {
				select = trinkets.get(0);
				sIndex = 0;
			}
		}
		if(e.getX()>=39 && e.getX()<=149 && e.getY()>=109 && e.getY()<=123) {//2
			if(vHelm && helmets.size()>=2) {
				select = helmets.get(1);
				sIndex = 1;
			}else if(vChest && chests.size()>=2) {
				select = chests.get(1);
				sIndex = 1;
			}else if(vLeg && legs.size()>=2) {
				select = legs.get(1);
				sIndex = 1;
			}else if(vShoes && shoes.size()>=2) {
				select = shoes.get(1);
				sIndex = 1;
			}else if(vShield && shields.size()>=2) {
				select = shields.get(1);
				sIndex = 1;
			}else if(vSword && swords.size()>=2) {
				select = swords.get(1);
				sIndex = 1;
			}else if(vTri && trinkets.size()>=2) {
				select = trinkets.get(1);
				sIndex = 1;
			}
		}
		if(e.getX()>=39 && e.getX()<=149 && e.getY()>=129 && e.getY()<=141) {//3
			if(vHelm && helmets.size()>=3) {
				select = helmets.get(2);
				sIndex = 2;
			}else if(vChest && chests.size()>=3) {
				select = chests.get(2);
				sIndex = 2;
			}else if(vLeg && legs.size()>=3) {
				select = legs.get(2);
				sIndex = 2;
			}else if(vShoes && shoes.size()>=3) {
				select = shoes.get(2);
				sIndex = 2;
			}else if(vShield && shields.size()>=3) {
				select = shields.get(2);
				sIndex = 2;
			}else if(vSword && swords.size()>=3) {
				select = swords.get(2);
				sIndex = 2;
			}else if(vTri && trinkets.size()>=3) {
				select = trinkets.get(2);
				sIndex = 2;
			}
		}
		if(e.getX()>=39 && e.getX()<=149 && e.getY()>=149 && e.getY()<=163) {//4
			if(vHelm && helmets.size()>=4) {
				select = helmets.get(3);
				sIndex = 3;
			}else if(vChest && chests.size()>=4) {
				select = chests.get(3);
				sIndex = 3;
			}else if(vLeg && legs.size()>=4) {
				select = legs.get(3);
				sIndex = 3;
			}else if(vShoes && shoes.size()>=4) {
				select = shoes.get(3);
				sIndex = 3;
			}else if(vShield && shields.size()>=4) {
				select = shields.get(3);
				sIndex = 3;
			}else if(vSword && swords.size()>=4) {
				select = swords.get(3);
				sIndex = 3;
			}else if(vTri && trinkets.size()>=4) {
				select = trinkets.get(3);
				sIndex = 3;
			}
		}
		if(e.getX()>=39 && e.getX()<=149 && e.getY()>=169 && e.getY()<=183) {//5
			if(vHelm && helmets.size()>=5) {
				select = helmets.get(4);
				sIndex = 4;
			}else if(vChest && chests.size()>=5) {
				select = chests.get(4);
				sIndex = 4;
			}else if(vLeg && legs.size()>=5) {
				select = legs.get(4);
				sIndex = 4;
			}else if(vShoes && shoes.size()>=5) {
				select = shoes.get(4);
				sIndex = 4;
			}else if(vShield && shields.size()>=5) {
				select = shields.get(4);
				sIndex = 4;
			}else if(vSword && swords.size()>=5) {
				select = swords.get(4);
				sIndex = 4;
			}else if(vTri && trinkets.size()>=5) {
				select = trinkets.get(4);
				sIndex = 4;
			}
		}
		if(e.getX()>=39 && e.getX()<=149 && e.getY()>=189 && e.getY()<=203) {//6
			if(vHelm && helmets.size()>=6) {
				select = helmets.get(5);
				sIndex = 5;
			}else if(vChest && chests.size()>=6) {
				select = chests.get(5);
				sIndex = 5;
			}else if(vLeg && legs.size()>=6) {
				select = legs.get(5);
				sIndex = 5;
			}else if(vShoes && shoes.size()>=6) {
				select = shoes.get(5);
				sIndex = 5;
			}else if(vShield && shields.size()>=6) {
				select = shields.get(5);
				sIndex = 5;
			}else if(vSword && swords.size()>=6) {
				select = swords.get(5);
				sIndex = 5;
			}else if(vTri && trinkets.size()>=6) {
				select = trinkets.get(5);
				sIndex = 5;
			}
		}
		if(e.getX()>=39 && e.getX()<=149 && e.getY()>=209 && e.getY()<=221) {//7
			if(vHelm && helmets.size()>=7) {
				select = helmets.get(6);
				sIndex = 6;
			}else if(vChest && chests.size()>=7) {
				select = chests.get(6);
				sIndex = 6;
			}else if(vLeg && legs.size()>=7) {
				select = legs.get(6);
				sIndex = 6;
			}else if(vShoes && shoes.size()>=7) {
				select = shoes.get(6);
				sIndex = 6;
			}else if(vShield && shields.size()>=7) {
				select = shields.get(6);
				sIndex = 6;
			}else if(vSword && swords.size()>=7) {
				select = swords.get(6);
				sIndex = 6;
			}else if(vTri && trinkets.size()>=7) {
				select = trinkets.get(6);
				sIndex = 6;
			}
		}
		if(e.getX()>=39 && e.getX()<=149 && e.getY()>=229 && e.getY()<=241) {//8
			if(vHelm && helmets.size()>=8) {
				select = helmets.get(7);
				sIndex = 7;
			}else if(vChest && chests.size()>=8) {
				select = chests.get(7);
				sIndex = 7;
			}else if(vLeg && legs.size()>=8) {
				select = legs.get(7);
				sIndex = 7;
			}else if(vShoes && shoes.size()>=8) {
				select = shoes.get(7);
				sIndex = 7;
			}else if(vShield && shields.size()>=8) {
				select = shields.get(7);
				sIndex = 7;
			}else if(vSword && swords.size()>=8) {
				select = swords.get(7);
				sIndex = 7;
			}else if(vTri && trinkets.size()>=8) {
				select = trinkets.get(7);
				sIndex = 7;
			}
		}
		if(e.getX()>=39 && e.getX()<=149 && e.getY()>=249 && e.getY()<=263) {//9
			if(vHelm && helmets.size()>=9) {
				select = helmets.get(8);
				sIndex = 8;
			}else if(vChest && chests.size()>=9) {
				select = chests.get(8);
				sIndex = 8;
			}else if(vLeg && legs.size()>=8) {
				select = legs.get(8);
				sIndex = 8;
			}else if(vShoes && shoes.size()>=9) {
				select = shoes.get(8);
				sIndex = 8;
			}else if(vShield && shields.size()>=9) {
				select = shields.get(8);
				sIndex = 8;
			}else if(vSword && swords.size()>=9) {
				select = swords.get(8);
				sIndex = 8;
			}else if(vTri && trinkets.size()>=9) {
				select = trinkets.get(8);
				sIndex = 8;
			}
		}
		if(e.getX()>=39 && e.getX()<=149 && e.getY()>=269 && e.getY()<=281) {//10
			if(vHelm && helmets.size()>=10) {
				select = helmets.get(9);
				sIndex = 9;
			}else if(vChest && chests.size()>=10) {
				select = chests.get(9);
				sIndex = 9;
			}else if(vLeg && legs.size()>=10) {
				select = legs.get(9);
				sIndex = 9;
			}else if(vShoes && shoes.size()>=10) {
				select = shoes.get(9);
				sIndex = 9;
			}else if(vShield && shields.size()>=10) {
				select = shields.get(9);
				sIndex = 9;
			}else if(vSword && swords.size()>=10) {
				select = swords.get(9);
				sIndex = 9;
			}else if(vTri && trinkets.size()>=10) {
				select = trinkets.get(9);
				sIndex = 9;
			}
		}
		if(e.getX()>=39 && e.getX()<=149 && e.getY()>=289 && e.getY()<=303) {//11
			if(vHelm && helmets.size()>=11) {
				select = helmets.get(10);
				sIndex = 10;
			}else if(vChest && chests.size()>=11) {
				select = chests.get(10);
				sIndex = 10;
			}else if(vLeg && legs.size()>=10) {
				select = legs.get(10);
				sIndex = 10;
			}else if(vShoes && shoes.size()>=11) {
				select = shoes.get(10);
				sIndex = 10;
			}else if(vShield && shields.size()>=11) {
				select = shields.get(10);
				sIndex = 10;
			}else if(vSword && swords.size()>=11) {
				select = swords.get(10);
				sIndex = 10;
			}else if(vTri && trinkets.size()>=11) {
				select = trinkets.get(10);
				sIndex = 10;
			}
		}
		if(e.getX()>=39 && e.getX()<=149 && e.getY()>=309 && e.getY()<=323) {//12
			if(vHelm && helmets.size()>=12) {
				select = helmets.get(11);
				sIndex = 11;
			}else if(vChest && chests.size()>=11) {
				select = chests.get(11);
				sIndex = 11;
			}else if(vLeg && legs.size()>=12) {
				select = legs.get(11);
				sIndex = 11;
			}else if(vShoes && shoes.size()>=12) {
				select = shoes.get(11);
				sIndex = 11;
			}else if(vShield && shields.size()>=12) {
				select = shields.get(11);
				sIndex = 11;
			}else if(vSword && swords.size()>=12) {
				select = swords.get(11);
				sIndex = 11;
			}else if(vTri && trinkets.size()>=12) {
				select = trinkets.get(11);
				sIndex = 11;
			}
		}
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
			//System.out.println("1 pressed");
			opt1 = true;
			opt2 = false;
			opt3 = false;
			opt4 = false;
			opt5 = false;
		}
		if(arg0.getKeyCode()==50) {
			//System.out.println("2 pressed");
			opt2 = true;
			opt1 = false;
			opt3 = false;
			opt4 = false;
			opt5 = false;
		}
		if(arg0.getKeyCode()==51) {
			//System.out.println("3 pressed");
			opt3 = true;
			opt1 = false;
			opt2 = false;
			opt4 = false;
			opt5 = false;
		}
		if(arg0.getKeyCode()==52) {
			//System.out.println("4 pressed");
			opt4 = true;
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt5 = false;
		}
		if(arg0.getKeyCode()==53) {
			//System.out.println("5 pressed");
			opt4 = false;
			opt1 = false;
			opt2 = false;
			opt3 = false;
			opt5 = true;
		}
		if(arg0.getKeyCode()==69 ) {//e
			if(vHelm && select!=null) {
				currHelmet = select;
				select = null;
				helmets.remove(sIndex);
			}else if(vChest && select!=null) {
				currChestplate = select;
				select = null;
				chests.remove(sIndex);
			}else if(vLeg && select!=null) {
				currLeggings = select;
				select = null;
				legs.remove(sIndex);
			}else if(vShoes && select!=null) {
				currShoes = select;
				select = null;
				shoes.remove(sIndex);
			}else if(vShield && select!=null) {
				currShield = select;
				select = null;
				shields.remove(sIndex);
			}else if(vSword && select!=null) {
				currSword = select;
				select = null;
				swords.remove(sIndex);
			}else if(vTri && select!=null) {
				currTrinket = select;
				select = null;
				trinkets.remove(sIndex);
			}
			
		}
		
		if(arg0.getKeyCode()==83) {//s
			if(vHelm && select!=null) {
				p.setGold(p.getGold()+select.getValue());
				helmets.remove(sIndex);
				select = null;
			}else if(vChest && select!=null) {
				p.setGold(p.getGold()+select.getValue());
				chests.remove(sIndex);
				select = null;
				
			}else if(vLeg && select!=null) {
				p.setGold(p.getGold()+select.getValue());
				legs.remove(sIndex);
				select = null;
				
			}else if(vShoes && select!=null) {
				p.setGold(p.getGold()+select.getValue());
				shoes.remove(sIndex);
				select = null;
				
			}else if(vShield && select!=null) {
				p.setGold(p.getGold()+select.getValue());
				shields.remove(sIndex);
				select = null;
				
			}else if(vSword && select!=null) {
				p.setGold(p.getGold()+select.getValue());
				swords.remove(sIndex);
				select = null;
				
			}else if(vTri && select!=null) {
				p.setGold(p.getGold()+select.getValue());
				trinkets.remove(sIndex);
				select = null;
				
			}
		}
		
//		if(arg0.getKeyCode()==53) {
//			p.setLoot(p.getLoot()+1);
//		}
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
