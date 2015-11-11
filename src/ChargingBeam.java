import info.gridworld.actor.*;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

public class ChargingBeam extends PlasmaBlast
{
	private Color normal;

	public ChargingBeam()
	{
		setColor(normal);
	}
	public void act()
	{
		removeSelfFromGrid();
	}
}