import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.awt.Color;

/**
 * This class represents all the different types of enemies.
 * It includes possibilities to be a Drone, a Robot, and a Scientist.
 * 
 * @author Team 20
 * @version 11/12/12
 */
public class Enemy extends Physics
{
    private int life = 100;
    private int shotDelay = 0;
    private HealthBar healthbar = new HealthBar();
    private int enemyType;
    private int displacementL;
    private int displacementR;
    private int direction=0;
    private int speed;
    private int stepCount = 0;
    private boolean patrolTurn = false;
    //Scientist variables
    private int imgCount = 0;

    /**
     * Constructor for Enemy. Use only for Robots.
     * @param typeOfEnemy Type of enemy this object should be.
     * This constructor should always have 1 as typeOfEnemy.
     */
    public Enemy(int typeOfEnemy)
    {
        enemyType = typeOfEnemy;
        switch (typeOfEnemy)
        {
            case 0:
                setImage("Drone.png");
                break;
            case 1:
                setImage("Evil Bot.png");
                break;
            case 2:
                setImage("Scientist.png");
                life = 500;
                break;
            default:
                setImage("Drone.png");
                enemyType = 0;
        }
    }

    /**
     * Constructor for Enemy. Use for Drones.
     * @param typeOfEnemy Type of enemy this object should be.
     * This constructor should always have 0 as typeOfEnemy.
     * @param displacementLeftIn Distance to travel left.
     * @param displacementRightIn Distance to travel right.
     * @param headingIn Initial direction to face.
     * @param speedIn Speed for this enemy.
     */
    public Enemy(int typeOfEnemy, int displacementLeftIn, int displacementRightIn, int headingIn, int speedIn)
    {
        enemyType = typeOfEnemy;
        switch (typeOfEnemy)
        {
            case 0:
                setImage("Drone.png");
                break;
            case 1:
                setImage("Evil Bot.png");
                break;
            case 2:
                setImage("Scientist.png");
                break;
            default:
                setImage("Drone.png");
                enemyType = 0;
        }
        displacementL = displacementLeftIn;
        displacementR = displacementRightIn;
        setHeading(headingIn);
        speed = speedIn;
    }

    /**
     * Updates the healthbar, checks for death, and calls the
     * method responsible for the action related to this enemy.
     */
    public void act() 
    {
        getWorld().addObject(healthbar, getX(),getY());
        healthbar.setLocation(getX(),getY() - getImage().getHeight());
        if (life <= 0)
        {
            getWorld().removeObject(healthbar);
            getWorld().removeObject(this);
        }
        else
        {
            fall();
            setEnemy();
        }
        shotDelay++;
    }
    
    /**
     * @return Returns true if Frank is to the left within 100 pixels.
     */
    public boolean frankIsLeft()
    {
        List<Robot> robots  = getNeighbours(100, false, Robot.class);
        LabWorld lab = (LabWorld) getWorld();
        for (Robot iRobot : robots)
        {
            if (iRobot == lab.getFrank())
            {
                if (lab.getFrank().getX() < getX())
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return Returns true if Frank is to the right within 100 pixels.
     */
    public boolean frankIsRight()
    {
        List<Robot> robots  = getNeighbours(100, false, Robot.class);
        LabWorld lab = (LabWorld) getWorld();
        for (Robot iRobot : robots)
        {
            if (iRobot == lab.getFrank())
            {
                if (lab.getFrank().getX() > getX())
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Fires a laser.
     */
    public void fire()
    {
        if (heading == 0)
        {
            getWorld().addObject(new Projectile(0,heading,10),
                getX() + getImage().getWidth() + 2, getY());
        }
        else if (heading == 1)
        {
            getWorld().addObject(new Projectile(0,heading,10),
                getX() - getImage().getWidth() - 2, getY());
        } 
    }

    /**
     * @return Returns true if Frank is within 100 pixels.
     */
    public boolean checkForFrank()
    {
        List<Robot> robots = getObjectsInRange(100, Robot.class);
        LabWorld lab = (LabWorld) getWorld();
        for (Robot iRobot : robots)
        {
            if (iRobot == lab.getFrank())
                return true;
        }
        return false;
    }

    /**
     * @return Returns true if close to the edge of the world.
     */
    public boolean atBorder()
    {
        if (getX() > getWorld().getWidth() - getImage().getWidth()/2)
            return true;
        if (getX() < getImage().getWidth()/2) 
            return true;
        return false;
    }

    /**
     * @return Returns the life of the enemy.
     */
    public int getLife()
    {
        return life;
    }

    /**
     * Sets the life of the enemy.
     * @param lifeAmt Value to set to life.
     */
    public void setLife(int lifeAmt)
    {
        healthbar.setHealth(lifeAmt);
        life = lifeAmt;
    }

    /**
     * Makes this enemy the correct type and calls the action for that enemy.
     */
    public void setEnemy()
    {
        switch (enemyType)
        {
            case 0:
                if (checkForFrank())
                {
                    droneAction();
                }
                else
                {
                    droneMovement();
                }
                break;
            case 1:
                robotMovement();
                break;
            case 2:
                scientistAction();
                dodgeLasers();
                break;
            default:
                break;
        }
    }

    /**
     * Moves the drone according to its pathfinding system.
     */

    public void droneMovement()
    {
        //travelling left
        if (stepCount < displacementL  && getHeading() == LEFT)
        {
            setImage("Drone.png");
            moveToLeft(speed);
            setHeading(LEFT);
        }
        else if (stepCount > displacementL && getHeading() == LEFT)
        {
            stepCount = 0;
            setHeading(RIGHT);
        }
        //travelling right
        if (stepCount < displacementR && getHeading() == RIGHT)
        {
            setImage("Drone.png");
            getImage().mirrorHorizontally();
            moveToRight(speed);
            setHeading(RIGHT);
        }
        else if (stepCount > displacementR && getHeading() == RIGHT)
        {
            stepCount = 0;
            setHeading(LEFT);
        }
        stepCount++;
    }

    /**
     * Causes the drone to attack Frank.
     */
    public void droneAction()
    {
        if (frankIsLeft())
        {
            setImage("Drone with Gun.png");
            setHeading(LEFT);
            if (shotDelay >10)
            {
                fire();
                shotDelay = 0;
            }
            return;
        }
        if (frankIsRight())
        {
            setImage("Drone with Gun.png");
            getImage().mirrorHorizontally();
            setHeading(RIGHT);
            if (shotDelay > 10)
            {
                fire();
                shotDelay = 0;
            }
        }
    }

    /**
     * Causes the robot to either attack Frank or move.
     */

    public void robotMovement()
    {
        if (checkForFrank())
        {
            robotAction();
        }
        else
        {
            robotPatrol();
        }
    }

    /**
     * Causes the robot to attack Frank.
     */
    public void robotAction()
    {
        if (frankIsLeft())
        {
            setImage("Evil Bot.png");
            getImage().mirrorHorizontally();
            moveToLeft();
            if (shotDelay > 10)
            {
                fire();
                shotDelay = 0;
            }
        }
        if (frankIsRight())
        {
            setImage("Evil Bot.png");
            moveToRight();
            if (shotDelay > 10)
            {
                fire();
                shotDelay = 0;
            }
        }
    }

    /**
     * Causes the robot to move according to its pathfinding system.
     */
    public void robotPatrol()
    {
        if (patrolTurn)
        {
            setImage("Evil Bot.png");
            moveToRight();
            if (atBorder() || !somethingInFront())
            {
                patrolTurn = false;
            }
        }
        else
        {
            setImage("Evil Bot.png");
            getImage().mirrorHorizontally();
            moveToLeft();
            if (atBorder() || !somethingInBack())
            {
                patrolTurn = true;
            }
        }
    }
    
    /**
     * @return Returns true if there is a Platform in front of this object.
     */
    public boolean somethingInFront()
    {
        if (getOneObjectAtOffset(getImage().getWidth()/2 + 4, getImage().getHeight()/2 + 4,Platform.class) != null)
            return true;
        return false;
    }
    
    /**
     * @return Returns true if there is a Platform behind this object.
     */
        public boolean somethingInBack()
    {
        if (getOneObjectAtOffset(-(getImage().getWidth()/2-4), (getImage().getHeight()/2)+4,Platform.class) != null)
            return true;
        return false;
    }
    
    /**
     * Teleports the scientist to the middle of the map.
     * Shows the teleport animation.
     */
    public void teleport()
    {
        LabWorld world = (LabWorld) getWorld();
        if(atBorder() && stepCount == 30)
        {
            world.addTeleport();
            setLocation(250,250);
            stepCount = 0;
        }
        else if (atBorder())
        {
            stepCount++;
        }
        if (imgCount == 18)
        {
            world.removeTeleport();
            imgCount = 0;
        }
        else if (world.hasTeleport())
        {
            imgCount++;
            setLocation(250,250);
        }
    }
    
    /**
     * The scientist finishes teleporting if it is the middle of doing so.
     * If not, it shoots at Frank and backs away from him.
     */
    public void scientistAction()
    {
        if (imgCount != 0)
        {
            teleport();
            return;
        }
        if (frankIsLeft())
        {
            setImage("Scientist.png");
            getImage().mirrorHorizontally();
            setHeading(LEFT);
            if (shotDelay > 10)
            {
                fire();
                shotDelay = 0;
            }
        }
        else if (frankIsRight())
        {
            setImage("Scientist.png");
            setHeading(RIGHT);
            if (shotDelay >10)
            {
                fire();
                shotDelay = 0;
            }
        }
    }
    
    /**
     * The scientist backs away from lasers if possible.
     */
    private void dodgeLasers()
    {
        List<Projectile> projectiles  = getNeighbours(100, false, Projectile.class);
        LabWorld lab = (LabWorld) getWorld();
        if (projectiles.size() == 0)
        {
            return;
        }
        else
        {
            if (projectiles.get(0).getX() > getX())
            {
                moveToLeft();
            }
            else
            {
                moveToRight();
            }
        }
        teleport();
    }
    
    
    public void teleeffect()
    {
        GreenfootImage line = new GreenfootImage(300,300);
        line.setColor(Color.BLUE);
        line.fillRect(0,0,5,22);
        line.rotate(90);
        setImage(line);
        
    }
}

