
public class Gear {
	private String name;//name of item
	private int tier;//rarity/tier
	private int healthB, attackB, defenseB;//stat boosts
	private int slot;//what gear slot it fits
	private int value;//sell value
	private int amount;//amount the player has
	private Boolean equipped;//is is equipped
	
	public Gear(int tier, int slot) {
		this.tier = tier;
		name = "";
		healthB = 0;
		attackB = 0;
		defenseB = 0;
		this.slot = slot;
		equipped = false;
		amount = 0;
		value = 0;
	}
	
	public Gear(int tier) {
		name = " Empty";
		healthB = 0;
		attackB = 0;
		defenseB = 0;
		this.tier = tier;
	}
	
	
	
	
	
	
	public int Sell() {
		amount -= 1;
		return value;
	}
	
	public void genName() {
		if(slot==0) {
			name = "Helmet";
		}else if(slot==1) {
			name = "Chestplate";
		}else if(slot==2) {
			name = "Leggings";
		}else if(slot==3) {
			name = "Shoes";
		}else if(slot==4) {
			name = "Shield";
		}else if(slot==5) {
			name = "Sword";
		}else if(slot==6) {
			name = "Trinket";
		}
	}
	
	public void genValue() {
		if(tier==1) {
			value = 3;
		}else if(tier==2) {
			value=7;
		}else if(tier==3) {
			value=15;
		}else if(tier==4) {
			value=50;
		}else if(tier==5) {
			value=500;
		}
	}
	
	public void genStats() {
		if(slot==0) {
			attackB = 0;
			if(tier==1) {
				healthB = 0;
				defenseB = 1;
			}else if(tier==2) {
				healthB = 2;
				defenseB = 2;
			}else if(tier==3) {
				healthB = 4;
				defenseB = 5;
			}else if(tier==4) {
				healthB = 6;
				defenseB = 5;
			}else if(tier==5) {
				healthB = 10;
				defenseB = 7;
			}
		}else if(slot==1) {
			attackB = 0;
			if(tier==1) {
				healthB = 1;
				defenseB = 2;
			}else if(tier==2) {
				healthB = 2;
				defenseB = 4;
			}else if(tier==3) {
				healthB = 4;
				defenseB = 6;
			}else if(tier==4) {
				healthB = 8;
				defenseB = 8;
			}else if(tier==5) {
				healthB = 12;
				defenseB = 10;
			}
		}else if(slot==2) {
			attackB = 0;
			if(tier==1) {
				healthB = 0;
				defenseB = 1;
			}else if(tier==2) {
				healthB = 1;
				defenseB = 2;
			}else if(tier==3) {
				healthB = 1;
				defenseB = 4;
			}else if(tier==4) {
				healthB = 2;
				defenseB = 6;
			}else if(tier==5) {
				healthB = 4;
				defenseB = 8;
			}
		}else if(slot==3) {
			attackB = 0;
			if(tier==1) {
				healthB = 1;
				defenseB = 2;
			}else if(tier==2) {
				healthB = 2;
				defenseB = 4;
			}else if(tier==3) {
				healthB = 4;
				defenseB = 6;
			}else if(tier==4) {
				healthB = 8;
				defenseB = 8;
			}else if(tier==5) {
				healthB = 12;
				defenseB = 10;
			}
		}else if(slot==4) {
			attackB = 0;
			if(tier==1) {
				healthB = 0;
				defenseB = 1;
			}else if(tier==2) {
				healthB = 0;
				defenseB = 3;
			}else if(tier==3) {
				healthB = 1;
				defenseB = 5;
			}else if(tier==4) {
				healthB = 1;
				defenseB = 8;
			}else if(tier==5) {
				healthB = 2;
				defenseB = 10;
			}
		}else if(slot==5) {
			healthB = 0;
			defenseB = 0;
			if(tier==1) {
				attackB = 1;
			}else if(tier==2) {
				attackB = 2;
			}else if(tier==3) {
				attackB = 4;
			}else if(tier==4) {
				attackB = 7;
			}else if(tier==5) {
				attackB = 12;
			}
		}else if(slot==6) {
			if(tier==1) {
				attackB = 0;
				healthB = 1;
				defenseB = 0;
				
			}else if(tier==2) {
				attackB = 0;
				healthB = 1;
				defenseB = 1;
				
			}else if(tier==3) {
				attackB = 1;
				healthB = 2;
				defenseB = 1;
				
			}else if(tier==4) {
				attackB = 1;
				healthB = 3;
				defenseB = 2;
				
			}else if(tier==5) {
				attackB = 2;
				healthB = 5;
				defenseB = 3;
			}
		}
		
	}
	
	public void getItem() {
		genName();
		genValue();
		genStats();
	}
	
	public String getTitle() {
		String build = "";
		build = "["+name+" Lvl: "+tier+"]";
		return build;
	}
	
	
	
	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTier() {
		return tier;
	}

	public void setTier(int tier) {
		this.tier = tier;
	}

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

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public Boolean getEquipped() {
		return equipped;
	}

	public void setEquipped(Boolean equipped) {
		this.equipped = equipped;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
