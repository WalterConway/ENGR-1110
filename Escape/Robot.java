   import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Frank here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Robot extends Physics
{
    protected int currentProjectile = 0;
    protected int currentProjectileDamage = 34;
    private int keyDelay = 0;
    private int keyDelay2 = 0;
    private int speed = 2;
    private double count =0;
    private boolean origCheck = false;
    private int originalX;
    private int originalY;
    
    /**
     * Checks for user input and updates its location.
     */
    public void act() 
    {
        setOriginalCoord();
        checkKeyPress();
        fall();
        bounceBack();
        keyDelay++;
        keyDelay2++;
    }
   
    /**
     * Checks to see if a key is press and does a following action
     * Keys used: 
     *  Left Arrow  or  a(lower case) turns the figure to the left.
     *  Right Arrow or  d(lower case) turns the figure to the right.
     *  Up  Arrow   or  w(lower case) makes the figure jump.
     *  Z - Changes weapons from: Laser, Fireball, Missile.
     *  Space - Fires the weapon chosen.
     */
    public void checkKeyPress()
    {
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a"))
        {
            setImage("Frank.png");
            getImage().mirrorHorizontally();
            moveToLeft(speed);
        }
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d"))
        {
            setImage("Frank.png"); //right figure
            moveToRight(speed);
        }
        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w"))
        {
            if (canIJump)
            {
               jump();
            }      
        }
        if (Greenfoot.isKeyDown("z") && keyDelay2 > 15)
        {
            currentProjectile++;
            switch(currentProjectile)
            {
                case 0:
                    setSelection(140,160);
                    currentProjectileDamage = 34;
                    break;
                case 1:
                    setSelection(60,75);
                    currentProjectileDamage = 50;
                    break;
                case 2:
                    setSelection(100,120);
                    currentProjectileDamage = 40;
                    break;
                default:
                    setSelection(140,160);
                    currentProjectileDamage = 34;
                    currentProjectile = 0;
            }
            keyDelay2 = 0;
        }
        if (Greenfoot.isKeyDown("space") && keyDelay > 8)
        {
            if (currentProjectile == 0)
            {
                setShotCountAmt(getShotCountAmt() + 1);
                fire();
            }
            if (currentProjectile == 1 && getMissileAmt() != 0)
            {
                setMissileAmt((getMissileAmt() - 1));
                fire();
            }
            if (currentProjectile == 2 && getFireballAmt() != 0)
            {
                setFireballAmt((getFireballAmt() - 1));
                fire();
            }
            keyDelay = 0;
        }
       
    }
   
    /**
     * @return Returns whether or not this is colliding with an enemy.
     */
    public boolean collisionWEnemy()
    {
        LabWorld lab = (LabWorld) getWorld();
        Actor badGuy = getOneIntersectingObject(Enemy.class);
        if (badGuy != null)
            return true;
        return false;
    }
    
    /**
     * Makes Frank get bounced back from an enemy collision.
     */
    public void bounceBack()
    {
        Actor badGuy = getOneIntersectingObject(Enemy.class);
        if (collisionWEnemy())
        {
            if (badGuy.getX() <= getX())
               setLocation(getX() + 20,getY());
            if (badGuy.getX()>= getX())
               setLocation(getX() - 20,getY());
            if (velocityGravity > 0)
               jump();
            setHealthAmt(getHealthAmt() - 1);
        }
    }
   
    /**
     * Creates an instance of the selected weapon in front of Frank.
     */
    public void fire()
    {
        if (heading == RIGHT)
        {
            getWorld().addObject(new Projectile(currentProjectile, heading, currentProjectileDamage),
                getX() + getImage().getWidth() + 2, getY());
        }
        else if (heading == LEFT)
        {
            getWorld().addObject(new Projectile(currentProjectile, heading, currentProjectileDamage),
                getX() - getImage().getWidth() - 2, getY());
        }
    }
    
    /**
     * Sets the Original Coordinates to be used if the Robot health is gone.
     */
    public void setOriginalCoord()
    {
        if(!origCheck)
        {
            originalX = getX();
            originalY = getY();
            origCheck = true;
        }
    }
    
    /**
     * Resets origCheck to false.
     */
    public void resetOrigCheck()
    {
        origCheck = false;
    }
    
    /**
     * @return Returns the original X coordinates for the level.
     */
    public int getOrigX()
    {
        return originalX;
    }

    /**
     * @return Returns the original Y coordinate for the level.
     */
    public int getOrigY()
    {
        return originalY;
    }
}