import info.gridworld.actor.*;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

public class Weapon extends Actor
{
	private boolean away; // true when Hammer moves away
	// false when Hammers moves back to Thor
	private int hitpoints;
	private Thor t;
	private int direction;
	private int counter;

	public Weapon(Thor t1)
	{
		setColor(Color.BLACK);
		away = true;
		t = t1;
		counter = 0;
	}

	public void act()
	{
		if(away)
		{
			goForward();
		}
		else
		{
			goBack();
		}
	}
	public void goForward()
	{
		Location rightloc = getLocation().getAdjacentLocation(90);
		Location leftloc = getLocation().getAdjacentLocation(270);
		if (getGrid().isValid(rightloc) && getGrid().get(rightloc) instanceof Thor)
			direction = 270;
		if (getGrid().isValid(leftloc) && getGrid().get(leftloc) instanceof Thor)
			direction = 90;
		Location loc = getLocation().getAdjacentLocation(direction);
		turn();
		//if (getGrid().get(loc) instanceof Thor)
		//{
		//	removeSelfFromGrid();
		//	t.takeDamage();
		//	return;
		//}
		if(getGrid().isValid(loc) && !(getGrid().get(loc) instanceof Rock))
		  	moveTo(loc);
		else
		{
			this.removeSelfFromGrid();
			//goBack();
		}
	}
	public void goBack()
	{
		int dir = getLocation().getDirectionToward(t.getLocation());
		turn();
		Location loc = getLocation().getAdjacentLocation(dir);
		if(getGrid().isValid(loc) && getGrid().get(loc) == null)
		{
			moveTo(loc);
		}
		else
			removeSelfFromGrid();
		/*Location currentLoc = b.getLocation();
		int dir = getLocation().getDirectionToward(currentLoc);
		Location newLoc = getLocation().getAdjacentLocation(dir);
		moveTo(newLoc);
		*/
	}
	 public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }
    public boolean whetherToTurn(Location loc)
    {
    	if(getGrid().isValid(loc.getAdjacentLocation(Location.RIGHT)))
			return true;
		else
			return false;
    }
}