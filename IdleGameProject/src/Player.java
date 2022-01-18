
public class Player {
	private int health;
	private int maxHealth;
	private int defense;
	private int attack;
	private int gold;
	private int loot;
	
	
	
	
	public Player() {
		health = 10;
		maxHealth = 10;
		defense = 0;
		attack = 1;
		gold = 10;
		loot = 0;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getLoot() {
		return loot;
	}

	public void setLoot(int loot) {
		this.loot = loot;
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


