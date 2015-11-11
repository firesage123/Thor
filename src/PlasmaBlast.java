import info.gridworld.actor.*;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

public class PlasmaBlast extends Actor
{
	private int hitpoint;
	private Color normal;

	public PlasmaBlast()
	{
		hitpoint = 1;
		super.setColor(normal);
	}

	public void act()
	{
		goForward();
	}
	public void goForward()
	{
		Location loc = getLocation().getAdjacentLocation(getDirection() - 90);
		if(getGrid().isValid(loc) && getGrid().get(loc) == null) 
				moveTo(loc);
		else
			removeSelfFromGrid();
	}
}