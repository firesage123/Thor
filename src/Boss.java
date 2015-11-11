import info.gridworld.actor.*;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

public class Boss extends Enemy
{
	private int hitpoints;
	private Color normal;
	private Thor t;
	private int counter;
	private boolean right;

	public Boss(Thor t1)
	{
		super(t1);
		setColor(normal);
		hitpoints = 10;
		t = t1;
		counter = 1;
		right = false;
	}
	public void act()
	{
		if (counter % 10 == 0)
		{
			specialAttack();
			counter++;
			return;
		}
		if (counter % 3 == 0)
			attack();
		counter++;
	}
	public void attack()
	{
		if (t == null)
			return;
		int direction = getLocation().getDirectionToward(t.getLocation());
		PlasmaBlast blast1 = new PlasmaBlast();
		PlasmaBlast blast2 = new PlasmaBlast();
		PlasmaBlast blast3 = new PlasmaBlast();
		blast1.putSelfInGrid(getGrid(), getLocation().getAdjacentLocation(direction));
		if (getGrid().isValid(getLocation().getAdjacentLocation(direction + 45)))
			blast2.putSelfInGrid(getGrid(), getLocation().getAdjacentLocation(direction + 45));
		blast1.setDirection(direction + 90);
		blast1.goForward();
		blast2.setDirection(direction + 90);
		blast2.goForward();
		if (getGrid().isValid(getLocation().getAdjacentLocation(direction - 45)) && getGrid().get(getLocation().getAdjacentLocation(direction - 45)) == null)
		{
			blast3.putSelfInGrid(getGrid(), getLocation().getAdjacentLocation(direction - 45));
			blast3.setDirection(direction + 90);
			blast3.goForward();
		}
	}
	public void specialAttack()
	{
		ArrayList<Actor> arr = new ArrayList<Actor>();
		if (isFacingRight())
		{
			if (getGrid().isValid(getLocation().getAdjacentLocation(90)))
			{
				ChargingBeam c = new ChargingBeam();
				c.putSelfInGrid(getGrid(), getLocation().getAdjacentLocation(90));
				arr.add(c);
			}
			else
				return;
			for (int i = getLocation().getCol() + 2; i < getGrid().getNumCols(); i++)
			{
				LaserBeam l = new LaserBeam();
				l.putSelfInGrid(getGrid(), new Location(getLocation().getRow(), i));
				arr.add(l);
			}
		}
		else
		{
			if (getGrid().isValid(getLocation().getAdjacentLocation(270)))
			{
				ChargingBeam c = new ChargingBeam();
				c.putSelfInGrid(getGrid(), getLocation().getAdjacentLocation(270));
				arr.add(c);
			}
			else
				return;
			for (int i = getLocation().getCol() - 2; i >= 0; i--)
			{
				LaserBeam l = new LaserBeam();
				l.putSelfInGrid(getGrid(), new Location(getLocation().getRow(), i));
				arr.add(l);
			}
		}
	}
	public void move()
	{
		
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
}