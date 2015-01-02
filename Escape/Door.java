   import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
   import java.util.List;
/**
 * Write a description of class door here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
   public class Door extends Actor
   {
      Boolean Locked;
    /**
     * Act - do whatever the door wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
      public void act() 
      {
         checkDone();
      }
   
      public Door(boolean lockedDoor)
      {
         Locked = lockedDoor;
      }
   
      public void checkDone()
      {
         List<Robot> robots = getNeighbours(getImage().getWidth() - 5, true, Robot.class);
         LabWorld world = (LabWorld)getWorld();
         List<Enemy> enemies = world.getObjects(Enemy.class);
         if ((robots.size() > 0))
         {
            for (Robot robot : robots)
            {
               if (Locked)
               {
                  if (robot == world.getFrank() && world.getScoreBoard().getKey() > 0 && enemies.isEmpty() )
                  {
                     world.getScoreBoard().setKey(world.getScoreBoard().getKey() -1);
                     world.populateWorld(world.getLevel() + 1);
                  }
               }
               else
               {
                  if (robot == world.getFrank() && enemies.isEmpty() )
                  {
                  
                     world.populateWorld(world.getLevel() + 1);
                  }
               }
                
            }
         }
      }
   }
