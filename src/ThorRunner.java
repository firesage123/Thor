import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.actor.ActorWorld;
public class ThorRunner
{
	// Your world must be static.
	public static ActorWorld world = new ActorWorld(new BoundedGrid(23, 35));
	public static ThorGameOptions tgo = new ThorGameOptions(world);
	// The Actor you want to control must be static.
	// This can be any Actor, array of Actors, Critter, etc.
	public static Thor b = new Thor(world);
	public static Thor c = new Thor(world);
	public static int score = 0;
	public static int hammersthrown = 0;
	 
	public static void main(String[] args)
	{
		UFO o = new UFO(b);
		world.add(new Location(0, 10), o);
		world.add(new Location(9,0), new Rock());
		world.add(new Location(9,1), new Rock());
		world.add(new Location(9,2), new Rock());
		world.add(new Location(9,3), new Rock());
		world.add(new Location(9,4), new Rock());
		world.add(new Location(9,5), new Rock());
		world.add(new Location(9,6), new Rock());
		world.add(new Location(9,7), new Rock());
		world.add(new Location(9,8), new Rock());
		world.add(new Location(9,9), new Rock());
		world.add(new Location(9,10), new Rock());
		world.add(new Location(9,11), new Rock());
		world.add(new Location(9,12), new Rock());
		world.add(new Location(9,13), new Rock());
		world.add(new Location(9,14), new Rock());
		world.add(new Location(9,15), new Rock());
		world.add(new Location(9,16), new Rock());
		world.add(new Location(9,17), new Rock());
		world.add(new Location(9,18), new Rock());
		world.add(new Location(9,19), new Rock());
		world.add(new Location(6,5), new Rock());
		world.add(new Location(6,6), new Rock());
		world.add(new Location(6,7), new Rock());
		world.add(new Location(6,8), new Rock());
		world.add(new Location(6,9), new Rock());
		world.add(new Location(3,10), new Rock());
		world.add(new Location(3,11), new Rock());
		world.add(new Location(3,12), new Rock());
		world.add(new Location(3,14), new Rock());
		world.add(new Location(3,13), new Rock());
		world.add(new Location(4,0), new Rock());
		world.add(new Location(4,1), new Rock());
		world.add(new Location(4,2), new Rock());
		world.add(new Location(4,3), new Rock());
		world.add(new Location(4,4), new Rock());
		//world.add(new Location(5,9), e);
		world.add(new Location(8,0), b);
		//world.add(new Location(8,19), c);
		world.setMessage("Player Score: " + score);
		tgo.setVisible(true);
		//world.show();
		// Uses an inner class to active key presses.
		// The inner classes must use class variables (static)
	java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager()
	.addKeyEventDispatcher(new java.awt.KeyEventDispatcher()
	{
   public boolean dispatchKeyEvent(java.awt.event.KeyEvent event)
   {
      String key = javax.swing.KeyStroke.getKeyStrokeForEvent(event).toString();
      if (b.getLocation() == null)
      {
    	  b.alive = false;
      }
      b.setDirection(0);
      if (key.equals("pressed UP"))
      {
      	 b.jump();
         world.show();
      }
      if (key.equals("pressed DOWN"))
      {
         Location loc = b.getLocation().getAdjacentLocation(180);
         if(b.getGrid().isValid(loc) == false || b.getGrid().get(loc) instanceof Rock)
         b.moveTo(b.getLocation());
         else
         b.moveTo(loc);
         world.show();
      }
      if (key.equals("pressed RIGHT"))
      {
      	 if (!b.isFacingRight())
      	 	b.changeDirection();
         Location loc = b.getLocation().getAdjacentLocation(90);
         if(b.getGrid().isValid(loc) == false || b.getGrid().get(loc) instanceof Rock)
         	b.moveTo(b.getLocation());
         else
         	b.moveTo(loc);
         world.show();
      }
      if (key.equals("pressed LEFT"))
      {
      	 if (b.isFacingRight())
      	   b.changeDirection();
         Location loc = b.getLocation().getAdjacentLocation(270);
         if(b.getGrid().isValid(loc) == false || b.getGrid().get(loc) instanceof Rock)
         	b.moveTo(b.getLocation());
         else
	         b.moveTo(loc);
	     world.show();
      }
      if (key.equals("pressed SPACE"))
      {
    	 hammersthrown += 1;
      	 Hammer h = new Hammer(b);
      	 Location loc1 = b.getLocation();
      	 if (b.isFacingRight())
      	 	loc1 = b.getLocation().getAdjacentLocation(90);
      	 else
      	 	loc1 = b.getLocation().getAdjacentLocation(270);
      	 if (b.getGrid().isValid(loc1))
      	 {
      	 	h.putSelfInGrid(b.getGrid(), loc1);
      	 	world.show();
      	 	//h.act();
      	 	//world.show();
      	 }
      }

      if (key.equals("pressed P"))
      {
      	b.specialAttack();
      	world.show();
      }

      if (key.equals("pressed R"))
      {
      	  c.specialAttack();
      	  world.show();
      }



      if (key.equals("pressed W"))
      {
      	 c.jump();
         world.show();
      }
      if (key.equals("pressed S"))
      {
         Location loc = c.getLocation().getAdjacentLocation(180);
         if(c.getGrid().isValid(loc) == false || c.getGrid().get(loc) instanceof Rock)
         c.moveTo(b.getLocation());
         else
         c.moveTo(loc);
         world.show();
      }
      if (key.equals("pressed A"))
      {
      	 if (c.isFacingRight())
      	 	c.changeDirection();
         Location loc = c.getLocation().getAdjacentLocation(270);
         if(c.getGrid().isValid(loc) == false || c.getGrid().get(loc) instanceof Rock)
         	c.moveTo(c.getLocation());
         else
         	c.moveTo(loc);
         world.show();
      }
      if (key.equals("pressed D"))
      {
      	 if (!c.isFacingRight())
      	   c.changeDirection();
         Location loc = c.getLocation().getAdjacentLocation(90);
         if(c.getGrid().isValid(loc) == false || c.getGrid().get(loc) instanceof Rock)
         	c.moveTo(c.getLocation());
         else
	         c.moveTo(loc);
	     world.show();
      }
      if (key.equals("pressed Z"))
      {
      	 Hammer h = new Hammer(c);
      	 Location loc1 = c.getLocation();
      	 if (c.isFacingRight())
      	 	loc1 = c.getLocation().getAdjacentLocation(90);
      	 else
      	 	loc1 = c.getLocation().getAdjacentLocation(270);
      	 if (c.getGrid().isValid(loc1))
      	 {
      	 	h.putSelfInGrid(c.getGrid(), loc1);
      	 	world.show();
      	 	//h.act();
      	 	//world.show();
      	 }
      }





   return true;

   }
});


	} // end main method
}