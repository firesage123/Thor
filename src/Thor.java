import info.gridworld.actor.*;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

public class Thor extends Actor
{
	private Color normal;
	private boolean right;
	private int hitpoints;
	public boolean alive;
	private ActorWorld world;
	
	public Thor(ActorWorld w)
	{
		super();
		super.setColor(normal);
		right = true;
		hitpoints = 5;
		alive = true;
		world = w;
	}
	public void act()
	{
		if (alive == false)
		{
			new GameOver(world);
			return;
		}
		Grid<Actor> gr = getGrid();
		Location gravity = getLocation().getAdjacentLocation(180);
		while (gr.isValid(gravity) && !(gr.get(gravity) instanceof Rock))
		{
		if (gr.isValid(gravity))
			moveTo(gravity);
			gravity = gravity.getAdjacentLocation(180);
		}
		if (willTakeDamage())
			takeDamage();
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
	public void jump()
	{
		if (!(getGrid().get(getLocation().getAdjacentLocation(180)) instanceof Rock))
			return;
		Location temp = getLocation();
		Location loc = temp.getAdjacentLocation(0);
		Location loc1 = loc.getAdjacentLocation(0);
		Location loc2 = loc1.getAdjacentLocation(0);
		if(getGrid().get(loc) instanceof Rock || getGrid().isValid(loc) == false)
			return;
		else
			moveTo(loc);
		if(getGrid().get(loc1) instanceof Rock || getGrid().isValid(loc1) == false)
			return;
		else
			moveTo(loc1);
		if(getGrid().get(loc2) instanceof Rock || getGrid().isValid(loc2) == false)
			return;
		else
			moveTo(loc2);
	}
	public void specialAttack()
	{
		if (isFacingRight())
		{
			if (getGrid().isValid(getLocation().getAdjacentLocation(90)) && !(getGrid().get(getLocation().getAdjacentLocation(90)) instanceof Rock))
			{
				ChargingBeam c = new ChargingBeam();
				c.putSelfInGrid(getGrid(), getLocation().getAdjacentLocation(90));
			}
			else
				return;
			for (int i = getLocation().getCol() + 2; i < getGrid().getNumCols(); i++)
			{
				LaserBeam l = new LaserBeam();
				Location loc = new Location(getLocation().getRow(), i);
				if (getGrid().isValid(loc) && !(getGrid().get(loc) instanceof Rock))
					l.putSelfInGrid(getGrid(), new Location(getLocation().getRow(), i));
				else
					return;
			}
		}
		else
		{
			if (getGrid().isValid(getLocation().getAdjacentLocation(270)))
			{
				ChargingBeam c = new ChargingBeam();
				c.putSelfInGrid(getGrid(), getLocation().getAdjacentLocation(270));
			}
			else
				return;
			for (int i = getLocation().getCol() - 2; i >= 0; i--)
			{
				LaserBeam l = new LaserBeam();
				Location loc = new Location(getLocation().getRow(), i);
				if (getGrid().isValid(loc) && !(getGrid().get(loc) instanceof Rock))
					l.putSelfInGrid(getGrid(), new Location(getLocation().getRow(), i));
				else
					return;
			}
		}
	}
	public void takeDamage()
	{
		hitpoints--;
	}
	public boolean willTakeDamage()
	{
		Location loc1 = getLocation().getAdjacentLocation(90);
		Location loc2 = getLocation().getAdjacentLocation(270);
		if (getGrid().isValid(loc1) && getGrid().get(loc1) instanceof PlasmaBlast)
		{
			if(getGrid().get(loc1).getDirection() == 0)
				return true;
		}
		if (getGrid().isValid(loc2) && getGrid().get(loc2) instanceof PlasmaBlast)
		{
			if(getGrid().get(loc2).getDirection() == 180)
				return true;
		}
		return false;
	}
}