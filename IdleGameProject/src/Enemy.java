
public class Enemy {
	private int health, maxHealth;
	private int defense;
	private int attack;
	private int tier;
	private int lootY;
	private int xpY;
	private String name;
	private boolean alive;
	
	public Enemy(int maxHealth, int attack, int tier, int defense, int lootY, int xpY, String name) {
		this.health = maxHealth;
		this.maxHealth = maxHealth;
		this.defense = defense;
		this.attack = attack;
		this.tier = tier;
		this.lootY = lootY;
		this.xpY = xpY;
		this.name = name;
		alive = false;
	}
	
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getTier() {
		return tier;
	}

	public void setTier(int tier) {
		this.tier = tier;
	}

	public int getXpY() {
		return xpY;
	}

	public void setXpY(int xpY) {
		this.xpY = xpY;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int Attack(Player pl) {
		int damage;
		damage = (this.attack*3)-(pl.getDefense()/2);
		if(damage < 0) damage = 0;
		return damage;
	}
	
	public void Spawn() {
		health = maxHealth;
	}
	
	public void setCurrentEnemy(Enemy en) {
		this.name = en.getName();
		this.attack = en.getAttack();
		this.defense = en.getDefense();
		this.health = en.getHealth();
		this.maxHealth = en.getMaxHealth();
		this.tier = en.getTier();
		this.lootY = en.getLootY();
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
