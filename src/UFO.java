import info.gridworld.actor.*;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

public class UFO extends Actor
{
	private int hitpoints;
	private int counter;
	private Thor t;
	public UFO(Thor t1)
	{
		hitpoints = 0;
		t = t1;
		counter = 0;
	}

	public void act()
	{
		spawnEnemies();
		ArrayList<Actor> actors = new ArrayList<Actor>();
		for (int i = 0; i < getGrid().getNumRows(); i++)
		{
			for (int j = 0; j < getGrid().getNumCols(); j++)
			{
				Location loc = new Location(i, j);
				if (!(getGrid().get(loc) instanceof Rock) && !(getGrid().get(loc) instanceof Thor) && !(getGrid().get(loc) instanceof UFO) && getGrid().get(loc) != null)
					actors.add(getGrid().get(loc));
			}
		}
		if (hitpoints == 50 && isBossReadyToSpawn())
			spawnBoss();
	}

	public void spawnEnemies()
	{
		int random = (int) (Math.random()*20);
		int random1 = (int) (Math.random()*20);
		if (random == 10)
			return;
		if (random1 == 10)
			return;
		if (hitpoints == 50)
			return;
		if (hitpoints % 5 == 0)
		{
			Location loc = new Location(1, random);
			Enemy a1 = new Enemy(t);
			a1.putSelfInGrid(getGrid(), loc);
		}
		if (hitpoints % 12 == 0)
		{
			Location loc = new Location(1, random1);
			FlyingEnemy b1 = new FlyingEnemy(t);
			b1.putSelfInGrid(getGrid(), loc);
		}
		hitpoints++;
	}
	public void spawnBoss()
	{
		if (counter == 1)
			return;
		Boss b = new Boss(t);
		b.putSelfInGrid(getGrid(), new Location(8, 19));
		counter++;
	}
	public boolean isBossReadyToSpawn()
	{
		boolean b = true;
		ArrayList<Location> locs = getGrid().getOccupiedLocations();
		for (Location loc : locs)
		{
			if (getGrid().get(loc) instanceof Enemy || getGrid().get(loc) instanceof FlyingEnemy || getGrid().get(loc) instanceof PlasmaBlast)
				b = false;
		}
		return b;
	}
}