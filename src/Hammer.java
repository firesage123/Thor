import info.gridworld.actor.*;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

public class Hammer extends Weapon
{
	int hitpoint;
	public Hammer(Thor t)
	{
		super(t);
		hitpoint = 1;
	}
}