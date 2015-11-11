import info.gridworld.actor.*;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

public class Enemy extends Actor
{
	private int counter;
	private boolean right;
	private Color normal;
	private Thor thor;
	public Enemy(Thor t)
	{
		super.setColor(normal);
		setDirection(Location.NORTH);
		counter = 0;
		right = false;
		thor = t;
	}
	public void act()
	{
		Grid<Actor> gr = getGrid();
		Location gravity = getLocation().getAdjacentLocation(180);
		while (!(gr.get(gravity) instanceof Rock))
		{
			if (gr.get(gravity) instanceof FlyingEnemy)
			{
				Location loc1 = new Location(getLocation().getRow(), getLocation().getCol() + 1);
				Location loc2 = new Location(getLocation().getRow(), getLocation().getCol() - 1);
				if (getGrid().isValid(loc1))
					moveTo(loc1);
				else if (getGrid().isValid(loc2))
					moveTo(loc2);					
			}
			moveTo(gravity);
			gravity = gravity.getAdjacentLocation(180);
		}
	    //if (getGrid().isValid(gravity.getAdjacentLocation(180)) && !(getGrid().get(gravity.getAdjacentLocation(180)) instanceof Rock))
	    	//changeDirection();
	    move();
		if (enemiesInRow())
			attack();
	}
	public void attack()
	{
		PlasmaBlast blast = new PlasmaBlast();
		Location loc = getLocation();
		if (enemiesInRow() && thorOnLeft())
		{
			loc = getLocation().getAdjacentLocation(Location.LEFT);
			blast.setDirection(getDirection());
		}
		else if (enemiesInRow())
		{
			loc = getLocation().getAdjacentLocation(Location.RIGHT);
			blast.setDirection(getDirection() + 180);
		}
		if (getGrid().isValid(loc) && !(getGrid().get(loc) instanceof Rock))
		{
			blast.putSelfInGrid(getGrid(), loc);
			blast.act();
		}
	}
	public void move()
	{
		Location loc = getLocation();
		if(isFacingRight())
			loc = getLocation().getAdjacentLocation(Location.RIGHT);
		else
			loc = getLocation().getAdjacentLocation(Location.LEFT);
		if (getGrid().isValid(loc) && getGrid().get(loc.getAdjacentLocation(180)) instanceof Rock && !(getGrid().get(loc) instanceof Enemy) && !(getGrid().get(loc) instanceof FlyingEnemy))
		{
			if (getGrid().get(loc) instanceof Thor)
				thor.alive = false;
			moveTo(loc);
		}
		else
			changeDirection();
	}
	public void changeDirection()
	{
		right = !right;
	}
	public boolean isFacingRight()
	{
		if (right == true)
			return true;
		return false;
	}
	public boolean enemiesInRow()
	{
	   boolean b = false;
	   Location oldLoc = getLocation();
	   if (isFacingRight())
	   {
    	for (int c = getLocation().getCol(); c < getGrid().getNumCols() - 1; c++)
    	{
    		Location loc = oldLoc.getAdjacentLocation(Location.RIGHT);
    		Actor a = getGrid().get(loc);
    		if (a instanceof Thor && !(a instanceof Rock))
    		{
    			b = true;
    			oldLoc = loc;
    		}
    		else
 				oldLoc = loc;
    	}
	   }
	   else
	   {
	   	for (int c = getLocation().getCol(); c > 0; c--)
    	{
    		Location loc = oldLoc.getAdjacentLocation(Location.LEFT);
    		Actor a = getGrid().get(loc);
    		if (a instanceof Thor && !(a instanceof Rock))
    		{
    			b = true;
    			oldLoc = loc;
    		}
    		else
 				oldLoc = loc;
    	}
	   }
	   return b;
	}
	public boolean thorOnLeft()
	{
		if (!isFacingRight())
		{
			for (int r = 0; r < getGrid().getNumRows(); r++)
			{
				for (int c = 0; c <= getLocation().getCol(); c++)
				{
					Location loc = new Location(r, c);
					if (getGrid().isValid(loc) && getGrid().get(loc) instanceof Thor)
						return true;
				}
			}
			return false;
		}
		else
		{
			for (int r = 0; r < getGrid().getNumRows(); r++)
			{
				for (int c = getLocation().getCol() + 1; c < getGrid().getNumCols(); c++)
				{
					Location loc = new Location(r, c);
					if (getGrid().isValid(loc) && getGrid().get(loc) instanceof Thor)
						return true;
				}
			}
			return false;
		}
	}
}