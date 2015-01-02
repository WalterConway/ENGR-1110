import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class creates the images for the intro screen.
 * It is also responsible for beginning the music.
 * 
 * @author Team 20
 * @version 11/12/12
 */
public class Splash extends Actor
{
    private GreenfootImage image0 = new GreenfootImage("Press Start.png");
    private GreenfootImage image1 = new GreenfootImage("Command Line.png");
    private GreenfootImage image2 = new GreenfootImage("Instructions.png");
    private GreenfootImage image3 = new GreenfootImage("Teleport.png");
    public static GreenfootSound intro = new GreenfootSound("Escape Theme.mp3");
    private int type;
        
    /**
     * Constructor for Splash. Type 0 has interaction, the other do not.
     * @param typeIn Value to assign to type. Decides what image to display.
     */
    public Splash(int typeIn)
    {
        type = typeIn;
        switch(type)
        {
            case 0:
                setImage(image0);
                break;
            case 1:
                setImage(image1);
                break;
            case 2:
                setImage(image2);
                break;
            case 3:
                setImage(image3);
                break;
            default:
                setImage(image0);
        }
    }
    
    /**
     * Creates a blinking effect for type 0, does nothing for other types.
     */
    public void act() 
    {
        switch (type)
        {
            case 0:
                if(Greenfoot.isKeyDown("escape"))
                {
                    ((LabWorld)getWorld()).populateWorld(0);
                }
                else
                {
                    getImage().setTransparency(Greenfoot.getRandomNumber(256));
                }
                if (!intro.isPlaying())
                    intro.play();
                break;
            default:
                break;
        }
    }    
}