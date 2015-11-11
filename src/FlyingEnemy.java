import info.gridworld.actor.*;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

public class FlyingEnemy extends Enemy
{
	private int counter;
	private int direction;
	private Thor t11;
	public FlyingEnemy(Thor t)
	{
		super(t);
		direction = 180;
		t11 = t;
	}
	public void act()
	{
		if (counter % 2 == 0)
		  attack();
		counter++;
		move();
	}
	public void attack()
	{
		PlasmaBlast blast = new PlasmaBlast();
		Location loc = getLocation();
		if (thorOnLeft())
			loc = getLocation().getAdjacentLocation(Location.LEFT);
		else
		{
			loc = getLocation().getAdjacentLocation(Location.RIGHT);
			blast.setDirection(getDirection() + 180);
		}
      	if(getGrid().isValid(loc) && !(getGrid().get(loc) instanceof Rock) && !(getGrid().get(loc) instanceof UFO))
      	{
      		blast.putSelfInGrid(getGrid(), loc);
      		blast.act();
		}
	}
	public void move()
	{
		Location loc = getLocation().getAdjacentLocation(direction);
		if (getGrid().isValid(loc) && !(getGrid().get(loc) instanceof Rock) && !(getGrid().get(loc) instanceof Enemy))
		{
			if (getGrid().get(loc) instanceof Thor)
				t11.alive = false;
			moveTo(loc);
		}
		else
			changeDirection();
	}
	public void changeDirection()
	{
		direction = direction + 180;
	}
}