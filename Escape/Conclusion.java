   import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
   import java.awt.Color;

/**
 * Write a description of class Conclusion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Conclusion extends Actor
{
    int type;
    double timer = 0;
    LabWorld world = (LabWorld)getWorld();
    
    /**
     * Constructor fo Conclusion.
     * @param typeIn Whether to display a game over message
     * or a level up message.
     */
    public Conclusion(int typeIn)
    {
        type = typeIn;
    }
    
    /**
     * Displays the appropriate message according to type.
     */
    public void act() 
    {
        if (type == 0)
        {
            makeGameOver();
            if (timer > 20)
                Greenfoot.stop();
        }
        if (type == 1)
        {
            makeLvlUp();
            if (timer > 20)
                getWorld().removeObject(this);
        }
        timer += .11;
    }
    
    /**
     * Displays the game over message.
     */
    public void makeGameOver()
    {
        GreenfootImage gameOver = new GreenfootImage("Game Over", 100, Color.WHITE, Color.BLACK);
        setImage(gameOver);
    }
    
    /**
     * Displays the level up message.
     */
    public void makeLvlUp()
    {
        LabWorld lab = (LabWorld) getWorld();
        ScoreBoard board = lab.getScoreBoard();
        GreenfootImage nextLvl = new GreenfootImage("Congrats!\n\tLevel: " + board.getLVL() , 100, Color.WHITE, Color.BLACK);
        setImage(nextLvl);
    }
}
