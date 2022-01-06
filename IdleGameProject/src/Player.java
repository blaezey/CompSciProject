
public class Player {
	private int health;
	private int defense;
	private int attack;
	private int gold;
	
	
	public Player() {
		health = 10;
		defense = 0;
		attack = 1;
		gold = 10;
	}
	
	public Player(int attack, int defense, int health) {
		this.attack = attack;
		this.defense = defense;
		this.health = health;
	}
	
	
	
	
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setDefense(int defense) {
		this.defense = defense;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getDefense() {
		return defense;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public int getGold() {
		return gold;
	}
}


