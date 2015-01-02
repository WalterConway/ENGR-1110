   import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Chip here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item extends Physics
{
    private final int FIRSTAID = 0;
    private final int MOREMISS = 1;
    private final int MOREFIRE = 2;
    private final int KEY = 3;
    private int type;
    private int i = 0;
   
    /**
     * Constructor for Item. Designates the type of item.
     * @param typeIn. Type of item this is.
     */
    public Item(int typeIn)
    {
        type = typeIn;
        if (typeIn == 0)
            setImage("Heal-Chip.png");
        if (typeIn == 1)
             setImage("Missile Pack.png");
        if (typeIn == 2)
            setImage("Fuel Tank.png");
        if (typeIn == 3)
            setImage("Key Card.png");
    }
   
    /**
     * Gives the user 50 more health.
     */
    public void moreHealth()
    {
        setHealthAmt(getHealthAmt() + 50);
    }
 
    /**
     * Gives the user 3 more missiles.
     */
    public void moreMissiles()
    {
        setMissileAmt(getMissileAmt() + 3);
    }
   
    /**
     * Gives the user 5 more fireballs.
     */
    public void moreFireballs()
    {
        setFireballAmt(getFireballAmt() + 5);
    }
   
    /**
     * Gives the user a key.
     */
    public void key()
    {
        setKeyAmt(getKeyAmt() + 1);
    }
    
    /**
     * Checks to see if this item has been picked up.
     */
    public void giveMe()
    {
        LabWorld lab = (LabWorld) getWorld();  
        if (getOneIntersectingObject(Robot.class) == lab.getFrank())
        {
            switch(type)
            {
                case 0:
                    moreHealth();
                    break;
                case 1:
                    moreMissiles();
                    break;             
                case 2:
                    moreFireballs();
                    break;
                case 3:
                    key();
                    break;
                default:
                    break;
            }
            getWorld().removeObject(this);
        }
    }
   
    /**
     * Checks to see if this item has been picked up.
     */
    public void act() 
    {
        giveMe();
    }    
}
