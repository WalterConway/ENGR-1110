   import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
   import java.util.List;
/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Projectile extends Physics
{
    private int damage;
    private int ammoType;
    private int ammoVelocity;
    private int ammoLifeCount;
    private int ammoLife;
    
    /**
     * Constructor for Projectile.
     * @param ammoOfType Type of ammo this is 
     * (0 for lasor, 1 for fireball, 2 for missile).
     * @param headingIn Direction to travel.
     * @param damageAmt How much damage this projectile should do.
     */
    public Projectile (int ammoOfType, int headingin, int damageAmt)
    {
        setHeading(headingin);
        ammoType = ammoOfType;
        damage = damageAmt;
        if (ammoOfType == 0)
        {
            setImage("Lazor.png");
            if (headingin == LEFT)
                 getImage().mirrorHorizontally();
            //Greenfoot.playSound("Laser.wav");
            ammoVelocity = 2;
            ammoLife = 50;
                        
        }
        if (ammoOfType == 1)
        {
            setImage("Missile.png");
            if (headingin == LEFT)
                getImage().mirrorHorizontally();
            ammoVelocity = 4;
            ammoLife = 500;            
        }
        if (ammoOfType == 2)
        {
            setImage("Fireball.png");
            if (headingin == LEFT)
                getImage().mirrorHorizontally();
            ammoVelocity = 3;
            ammoLife = 70;
        }
    }
   
    /**
     * Moves and checks for collision and/or death of this projectile.
     */
    public void act() 
    {
        ammoLifeCount++;
        LabWorld lab = (LabWorld) getWorld();
        //movement
        if (getHeading() == RIGHT) 
        {
            moveToRight(ammoVelocity);
        }
        else
        {
            moveToLeft(ammoVelocity);
        }
        //deal damage
        List<Robot> robots = getIntersectingObjects(Robot.class);
        for (Robot robot : robots)
        {
            robot.setHealthAmt(robot.getHealthAmt() - damage);
        }
        List<Enemy> enemies = getIntersectingObjects(Enemy.class);
        for (Enemy enemy : enemies)
        {
            enemy.setLife(enemy.getLife() - damage);
        }
        //check for death
        if ( ammoLifeCount > ammoLife || getX() <= 0 || getX() >= getWorld().getWidth() - 1 
                || getOneIntersectingObject(Enemy.class) != null
                || getOneIntersectingObject(Robot.class) != null
                || getOneIntersectingObject(Platform.class) != null)
        {
            ammoLife = 0;
            getWorld().removeObject(this);
        }
    }    
}
