
public class Enemy {
	private int health, maxHealth;
	private int defense;
	private int attack;
	private int mNumber;
	private int goldY;
	private int xpY;
	private String name;
	private boolean alive;
	
	//xp drops
	private int maxGold;
	private int minGold;
	
	
	
	
	
	public Enemy(int maxHealth, int attack, int tier, int defense, int goldY, int xpY, String name, int minGold, int maxGold) {
		this.health = maxHealth;
		this.maxHealth = maxHealth;
		this.defense = defense;
		this.attack = attack;
		this.mNumber = tier;
		this.goldY = goldY;
		this.xpY = xpY;
		this.name = name;
		
		this.minGold = minGold;
		this.maxGold = maxGold;
		
		alive = false;
	}
	
	
	
	
	public int Attack(Player pl) {
		int damage;
		damage = (this.attack*2)-(pl.getDefense()*1);
		if(damage < 1) damage = 1;
		return damage;
	}
	
	public int GiveGold() {
		int roll;
		roll = (int)(Math.random()*(maxGold))+minGold;
		return roll;
	}
	
	
	
	
	
	public int getmNumber() {
		return mNumber;
	}

	public void setmNumber(int mNumber) {
		this.mNumber = mNumber;
	}

	public int getMaxGold() {
		return maxGold;
	}


	public void setMaxGold(int maxGold) {
		this.maxGold = maxGold;
	}

	public int getMinGold() {
		return minGold;
	}


	public void setMinGold(int minGold) {
		this.minGold = minGold;
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
		this.mNumber = en.getMNumber();
		this.goldY = en.getGoldY();
		this.xpY = en.getXpY();
		this.minGold = en.getMinGold();
		this.maxGold = en.getMaxGold();
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

	public int getMNumber() {
		return mNumber;
	}

	public void setTier(int tier) {
		this.mNumber = tier;
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

	public int getGoldY() {
		return goldY;
	}

	public void setGoldY(int goldY) {
		this.goldY = goldY;
	}
	
	
	
	
	
}
