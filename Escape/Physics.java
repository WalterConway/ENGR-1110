   import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
   import java.util.List;

/**
 * The class Mover provides some basic movement methods. Use this as a superclass
 * for other actors that should be able to move left and right, jump up and fall 
 * down.
 */
public abstract class Physics extends Actor
{
    protected boolean canIJump = true;
    /** possible values for heading */
    public final int RIGHT = 0;
    public final int LEFT = 1;
    protected int heading = 0;
    /** gravity's acceleration */
    public final int ACCELERATION = 1;
    protected int velocityGravity = 0;
    protected int speed;
   
    /**
     * This applies gravity to the subclass that calls it.
     * It checks for collision with any platforms before moving the subclass.
     */
    public void gravity()
    {
        //get objects for interaction
        List<Platform> actors = getNeighbours(Math.abs(velocityGravity) + 34, true, Platform.class);
        List<Platform> actors2 = getObjectsAtOffset(10, velocityGravity, Platform.class);
        List<Platform> actors3 = getObjectsAtOffset(-10, velocityGravity, Platform.class);
        for (Platform actor : actors2)
        {
            actors.add(actor);
        }
        for (Platform actor : actors3)
        {
            actors.add(actor);
        }
        //movement if there is nothing to interact with
        if (actors.size() == 0)
        {
            setLocation(getX(), getY() + velocityGravity);
            velocityGravity += ACCELERATION;
            return;
        }
        //collision checking and movement
        Platform actor = null;
        if (velocityGravity > 0) //moving downwards
        {
            for (Platform temp : actors)
            {
                if (temp.getY() > getY())
                {
                    actor = temp;
                }
            }
            if (actor != null && actor.getY() > getY()) //actor is below this
            {
                //land on actor
                setLocation(getX(), actor.getY() - actor.getImage().getHeight()/2 - getImage().getHeight()/2);
                velocityGravity = 0;
            }
            else //actor is null, there is no collision
            {
                setLocation(getX(), getY() + velocityGravity);
                velocityGravity += ACCELERATION;
            }
        }
        else //moving upwards
        {
            for (Platform temp : actors)
            {
                if (temp.getY() < getY())
                {
                    actor = temp;
                }
            }
            if (actor != null && actor.getY() < getY()) //actor is above this
            {
                setLocation(getX(), actor.getY() + actor.getImage().getHeight()/2 + getImage().getHeight()/2);
                velocityGravity = ACCELERATION;
            }
            else //actor is null, there is no collision
            {
                setLocation(getX(), getY() + velocityGravity);
                velocityGravity += ACCELERATION;
            }
        }
    }
   
    /**
     * This checks to see if there is ground before it trys to fall.
     * If there is ground it doesn't fall, if there isn't ground it falls.
     * It also dictates whether the subclass that calls it can jump.
     */
    public void fall()
    {
         if(!checkForGround())
         {
            gravity();
         }
         else
         {
            //sets jump value so actor can jump again.
            canIJump = true;
         }
    }
    
    /**
     *  @return Returns the direction the subclass is facing.
     */
    public int getHeading()
    {
         return heading;
    }
   
    /**
     * Sets the heading.
     * @param headingIn Value to set to heading. Should be either 0 or 1.
     */
    public void setHeading(int headingIn)
    {
         heading = headingIn;
    }
   
    /**
     * This moves the subclass to the left by one pixel.
     */
    public void moveToLeft()
    {
         setLocation(getX() - 1, getY());
         setHeading(LEFT);
    }
   
    /**
     * This moves the subclass to the left by its speed.
     * @param speedAmt Amount to move by. Should be the subclass's
     * speed value.
     */
    public void moveToLeft(int speedAmt)
    {
         setLocation(getX() - speedAmt, getY());
         speedAmt--;
         setHeading(LEFT);
    }
  
    /**
     * This moves the subclass to the right by one pixel.
     */
    public void moveToRight()
    {
         setLocation(getX() + 1, getY());
         setHeading(RIGHT);
    }
   
    /**
     * This moves the subclass to the right by its speed.
     * @param speedAmt Amount to move by. Should be the subclass's
     * speed value.
     */
    public void moveToRight(int speedAmt)
    {
         setLocation(getX() + speedAmt, getY());
         setHeading(RIGHT);
    }
   
    /**
     * Sets the velocity from gravity to a negative,
     * effectively making it jump when gravity() is called.
     */
    public void jump()
    {
         velocityGravity = -15; // how high the actor wants to jump.
         gravity();
         fall();
    }
   
    /**
     * This checks to see if there is an object that is considered "ground"
     * that is underneath the subclass.
     * @return Returns whether or not there is ground underneath the subclass.
     */
    public boolean checkForGround()
    {
         boolean somethingUnderMyFeet = true;
         Actor ground = getOneObjectAtOffset(11, 15, Platform.class);
         Actor ground2 = getOneObjectAtOffset(-11, 15, Platform.class);
         if (ground == null && ground2 == null)
         {
             somethingUnderMyFeet = false;
             canIJump = false;
         }
         else
         {
             velocityGravity = 0;
         }
         return somethingUnderMyFeet;
    }
   
    /**
     * @return Returns the life amount of Frank.
     */
    public int getLife()
    {
         LabWorld lab = (LabWorld) getWorld();
         ScoreBoard board = lab.getScoreBoard();
         return board.getLife();
    }
   
    /**
     * @return Returns the shot count amount of Frank.
     */
    public int getShotCountAmt()
    {
         LabWorld lab = (LabWorld) getWorld();
         ScoreBoard board = lab.getScoreBoard();
         return board.getShotCount();
    }
   
    /**
     * @return Returns the key code of Frank.
     */
    public int getKeyAmt()
    {
         LabWorld lab = (LabWorld) getWorld();
         ScoreBoard board = lab.getScoreBoard();
         return board.getKey();
    }
   
    /**
     * Sets the key amount of Frank.
     * @param keyAmtIn Number of keys to set.
     */
    public void setKeyAmt(int keyAmtIn)
    {
         LabWorld lab = (LabWorld) getWorld();
         ScoreBoard board = lab.getScoreBoard();   
         board.setKey(keyAmtIn);
      }
   
    /**
     * Sets the shot count of Frank.
     * @param shotAmt Number of shots to set.
     */
    public void setShotCountAmt(int shotAmt)
    {
         LabWorld lab = (LabWorld) getWorld();
         ScoreBoard board = lab.getScoreBoard();   
         board.setShotCount(shotAmt);
    }
   
    /**
     * Sets the number of lives of Frank.
     * @param lifeAmt Number of lives to set.
     */
    public void setLife(int lifeAmt)
    {
         LabWorld lab = (LabWorld) getWorld();
         ScoreBoard board = lab.getScoreBoard();   
         board.setLife(lifeAmt);
    }
   
    /**
     * @return Returns the health amount of Frank.
     */
    public double getHealthAmt()
    {
         LabWorld lab = (LabWorld) getWorld();
         ScoreBoard board = lab.getScoreBoard();
         return board.getHealth();
    }
   
    /**
     * Sets the health amount of Frank.
     * @param healthAmt Amount of health to set
     */
    public void setHealthAmt(double healthAmt)
    {
         LabWorld lab = (LabWorld) getWorld();
         ScoreBoard board = lab.getScoreBoard();   
         board.setHealth(healthAmt);
    }
   
    /**
     * Resets the health amount of Frank to 100.
     */
    public void resetHealth()
    {
         setHealthAmt(100);
    }
   
    /**
     * @return Returns the missile amount of Frank.
     */
    public int getMissileAmt()
    {
         LabWorld lab = (LabWorld) getWorld();
         ScoreBoard board = lab.getScoreBoard();
         return board.getMissile();
    }
   
    /**
     * Sets the missile amount of Frank.
     * @param missileAmt Number of missiles to set.
     */
    public void setMissileAmt(int missileAmt)
    {
         LabWorld lab = (LabWorld) getWorld();
         ScoreBoard board = lab.getScoreBoard();   
         board.setMissile(missileAmt);
    }
   
    /**
     * @return Returns the fireball amount of Frank.
     */
    public int getFireballAmt()
    {
         LabWorld lab = (LabWorld) getWorld();
         ScoreBoard board = lab.getScoreBoard();
         return board.getFireball();
    }
   
    /**
     * Sets the fireball amount of Frank.
     * @param fireballAmt Number of fireballs to set.
     */
    public void setFireballAmt(int fireballAmt)
    {
         LabWorld lab = (LabWorld) getWorld();
         ScoreBoard board = lab.getScoreBoard();   
         board.setFireball(fireballAmt);
    }
   
    /**
     * Underlines the correct weapon selection.
     * @param xOne x-coordinate of left endpoint of line.
     * @param xTwo x-coordinate of right endpoint of line.
     */
    public void setSelection(int xOne,int xTwo)
    {
         LabWorld lab = (LabWorld) getWorld();
         ScoreBoard board = lab.getScoreBoard();   
         board.setXs(xOne,xTwo);
    }
   
}
