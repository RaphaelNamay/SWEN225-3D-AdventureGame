package gameworld.entities;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import gameworld.Location;
import gameworld.entities.Item.Action;
import gameworld.entities.Player;
import gameworld.entities.Strategy;

public class Cactus implements Strategy{
	protected List<String> actions = Arrays.asList("Examine");

	

	/*@Override
	protected String examine() {
		Random rand = new Random();
		int probability = rand.nextInt(4 + 1) + 1;
		switch(probability) {
		case 1:
			return givePlayerCoins(1);
		case 2:
			int damage = rand.nextInt(4 + 1) + 1;
			Player.getInstance().getDamaged(damage);
			return "Shouldn't have gotten too close to the cactus. It decided to take " + damage + " bits of your life";
		case 3:
			if(Player.getInstance().getInventory().isFull()) givePlayerCoins(1);
			Key key = new Key(Player.getInstance().getLocation());
			Player.getInstance().getInventory().add(key);
			key.setInPlayerInventory(true);
			return "You found a " + key.getName();
		default:
			return description;
		}
	}*/

	@Override
	public List<String> getActions() {
		return actions;
	}



	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String performAction(Action action) {
		// TODO Auto-generated method stub
		return null;
	}

	// ==================================================================================
			/*public Cactus(Location location) {
			super(location);
			coinBank = 5;
		}




			@Override
	protected String performAction(Action action, Player player) {
		return examine();

	}

	@Override
	protected List<String> getActions() {
		return Arrays.asList(Action.EXAMINE.toString());
	}*/
}
