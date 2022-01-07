
public class Enemy {
	private int health;
	private int defense;
	private int attack;
	private int tier;
	private int lootY;
	private int xpY;
	
	public Enemy(int health, int attack, int tier, int defense, int lootY, int xpY) {
		this.health = health;
		this.defense = defense;
		this.attack = attack;
		this.tier = tier;
		this.lootY = lootY;
		this.xpY = xpY;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getLootY() {
		return lootY;
	}

	public void setLootY(int lootY) {
		this.lootY = lootY;
	}
	
	
	
	
	
}
