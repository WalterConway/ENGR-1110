   import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
   import java.awt.Color;
   import java.text.DecimalFormat;


/**
 * This class creates and draws the scoreboard for the game.
 * It holds all the information about Frank.
 * 
 * @author Team 20
 * @version 11/12/12
 */
public class ScoreBoard extends Actor
{
    private int life = 0;
    private double health = 0;
    private int missile = 0;
    private int fireball = 0;
    private int shotCount = 0;
    private int key = 0; 
    private int xOne=140;
    private int xTwo=160;
   
    private Conclusion gameOver = new Conclusion(0);
   
    /**
     * Constructor for ScoreBoard.
     * @param lifeIn Number of lives.
     * @param healthIn Amount of health.
     * @param missileIn Number of missiles.
     * @param fireballIn Number of fireballs.
     */
    public ScoreBoard(int lifeIn, double healthIn, int missileIn, int fireballIn)
    {
        life = lifeIn;
        health = healthIn;
        missile = missileIn;
        fireball = fireballIn;
    }
    
    /**
     * Calls the makeBoard method.
     */
    public void act() 
    {
        makeBoard();
    }
    
    /**
     * Draws the board.
     */
    public void makeBoard()
    {
        DecimalFormat decimalN = new DecimalFormat("0.00");
        GreenfootImage image = new GreenfootImage(getWorld().getWidth(), 13);
        image.setColor(new Color(0,0,0));
        image.fillRect(0, 0, getWorld().getWidth(), 13);
        setImage(image);
        image.setColor(Color.WHITE);
        image.drawString("Lives: " + getLife() , 0, 10); // how many lives
        image.drawString("M: " + getMissile(), 60, 10); // how many missiles
        image.drawString("FB: " + getFireball(), 100, 10); // how many fireballs
        image.drawString("LZ: ", 140,10); //lasers are infinite
        image.drawOval(160,3,5,5); // first oval of inf
        image.drawOval(165,3,5,5); // second oval of inf
        image.drawString("KEY: " + getKey(), 200,10);
        image.drawString("LVL: " + getLVL(), 250,10);
        image.drawString("Health: " , 300,10); // below is the progress bar.
        image.setColor(Color.YELLOW);
        image.drawLine(xOne,11,xTwo,11);
        image.setColor(Color.GRAY); // only serves the purpose to hold the visual rep of 100%
        image.fillRect(350, 1, 140, 10);
        image.setColor(Color.GREEN); // this moves based off the health value.
        image.fillRect(350, 1, ((int)((getHealth()/100)* 140)), 10);
    }
    
    /**
     * @return Returns the health, but if the health is less than 0
     * than it resets the health to 100 and subtracts a life.
     */
    public double getHealth()
    {
        if (health <= 0)
        {
            setLife(getLife() - 1);
            setHealth(100);
            LabWorld lab = (LabWorld) getWorld();
            lab.resetFrank();
        }
        if (health > 100)
        {
            health = 100;
            setLife(getLife() + 1);
        }
        return health;
    }
    
    /**
     * @return Returns the level.
     */
    public int getLVL()
    {
        LabWorld lab = (LabWorld) getWorld();
        return lab.getLevel();
    }
    
    /**
     * @return Returns the number of lives.
     */
    public int getLife()
    {
        if (life <= 0)
        {
            getWorld().addObject(gameOver,getWorld().getWidth()/2, 150);
            life = 0;
        }
        return life;
    }
    
    /**
     * @return Returns the number of missiles.
     */
    public int getMissile()
    {
        if(missile <= 0)
        {
            missile = 0;
        }
        return missile;
    }
    
    /**
     * @return Returns the number of fireballs.
     */
    public int getFireball()
    {
        if(fireball <= 0)
        {
            fireball = 0;
        }
        return fireball;
    }
    
    /**
     * @return Returns the number of lasers fired.
     */
    public int getShotCount()
    {
        return shotCount;
    }
    
    /**
     * @return Returns the number of keys.
     */
    public int getKey()
    {
        return key;
    }
    
    /**
     * Sets the number of keys.
     * @param keyIn New value for key.
     */
    public void setKey(int keyIn)
    {
        key = keyIn;
    }
    
    /**
     * Sets the number of lasers fired.
     * @param shotCountIn New value for shotCount.
     */
    public void setShotCount(int shotCountIn)
    {
        shotCount = shotCountIn;   
    }
    
    /**
     * Sets the amount of health.
     * @param healthIn New value for heatlh.
     */
    public void setHealth(double healthIn)
    {
        health = healthIn;
    }
    
    /**
     * Sets the number of lives.
     * @param lifeInNew value for life.
     */
    public void setLife(int lifeIn)
    {
        life = lifeIn;
    }
    
    /**
     * Sets the number of missiles.
     * @param missileIn New value for missile.
     */
    public void setMissile(int missileIn)
    {
        missile = missileIn;
    }
    
    /**
     * Sets the number of fireballs.
     * @param fireballIn New value for fireball.
     */
    public void setFireball(int fireballIn)
    {
        fireball = fireballIn;
    }
    
    /**
     * Sets the endpoints of the line that underlines the selected weapon.
     * @param xOneIn New value for xOne.
     * @param xTwoIn New value for xTwo.
     */
    public void setXs (int xOneIn, int xTwoIn)
    {
        xOne = xOneIn;
        xTwo = xTwoIn;
    }
}
