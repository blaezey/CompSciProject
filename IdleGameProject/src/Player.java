
public class Player {
	//Stats
	private int health;
	private int maxHealth;
	private int defense;
	private int attack;
	private int gold;
	private int loot;
	
	//Inventory
	private int sPotions;
	private int mPotions;
	private int lPotions;
	
	//Leveling
	private int level;
	private int xp;
	private int xpReq;
	private int tXp;
	
	//Equipped gear
	private int healthB, attackB, defenseB;
	
	
	public int getHealthB() {
		return healthB;
	}

	public void setHealthB(int healthB) {
		this.healthB = healthB;
	}

	public int getAttackB() {
		return attackB;
	}

	public void setAttackB(int attackB) {
		this.attackB = attackB;
	}

	public int getDefenseB() {
		return defenseB;
	}

	public void setDefenseB(int defenseB) {
		this.defenseB = defenseB;
	}

	public Player() {
		health = 10;
		maxHealth = 10;
		defense = 1;
		attack = 1;
		gold = 10;
		loot = 0;
		sPotions = 0;
		mPotions = 0;
		lPotions = 0;
		level = 0;
		xp = 0;
		xpReq = 5;
		tXp = 0;
		healthB = 0;
		attackB = 0;
		defenseB = 0;
	}
	
	//Attacking monsters
	public int Attack(Enemy en) {
		int damage;
		damage = (this.attack*2)-(en.getDefense()*1);
		if(damage < 1) damage = 1;
		return damage;
	}
	
	public void Heal(int heal) {
		health += heal;
		if(health > maxHealth) {
			health = maxHealth;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	public int gettXp() {
		return tXp;
	}

	public void settXp(int tXp) {
		this.tXp = tXp;
	}

	
	public int getsPotions() {
		return sPotions;
	}

	public void setsPotions(int sPotions) {
		this.sPotions = sPotions;
	}

	public int getmPotions() {
		return mPotions;
	}

	public void setmPotions(int mPotions) {
		this.mPotions = mPotions;
	}

	public int getlPotions() {
		return lPotions;
	}

	public void setlPotions(int lPotions) {
		this.lPotions = lPotions;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getXpReq() {
		return xpReq;
	}

	public void setXpReq(int xpReq) {
		this.xpReq = xpReq;
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
