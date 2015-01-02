   import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
   import java.util.List;

/**
 * Write a description of class Platform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
 public class Platform extends Physics
{
    boolean moving;
    int velocityValue;
    int displacementValue;
    int directionValue;
    int direction = 1;
    final int HORIZONTAL = 0;
    final int VERTICAL = 1;
    int originalX;
    int originalY;
    int count = 0;
    
    /**
     * Constructor for Platform. Only use for moving platforms.
     * @param move Whether or not this platoform is moving.
     * Only use true for this constructor.
     * @param direction Direction this platform is moving 
     * (0 for up, 1 for down).
     * @param speed Speed this platform is moving.
     * @param displacement how far this platform should move from its original position.
     */
    public Platform(boolean move, int direction, int speed, int displacement)
    {
        moving = move;
        velocityValue = speed;
        displacementValue = displacement;
        directionValue = direction;
    }
   
    /**
     * Constructor for Platform. Only use for nonmoving platforms.
     */
    public Platform()
    {
        moving = false;
    }
   
    /**
     * Moves if the Platform is a moving type.
     */
    public void act() 
    { 
        if (count==0)
        {
            originalX = getX();
            originalY = getY();
            count++;
        }
        //movement
        if (moving == true && directionValue == HORIZONTAL)
        {
            horizontalMove();
        }
        if (moving == true && directionValue == VERTICAL)
        {
            verticalMove();
        }
    }
    
    /**
     * Moves this platform right or left.
     */
    public void horizontalMove()
    {
        if (nearBorder())
        {
            direction *= -1;
        }
        setLocation(getX() + velocityValue * direction, getY());
        freeRideX();
    }
   
    /**
     * Moves this platform up and down.
     */
    public void verticalMove()
    {
        if (nearBorder())
        {
            direction *= -1;
        }
        setLocation(getX(), getY() + velocityValue * direction);
        freeRideY();
    }
   
    /**
     * Moves any objects standing on this platform right or left.
     */
    public void freeRideX()
    {
        List<Actor> actors =  getObjectsInRange(43, null);
        List<Actor> actors2 = getObjectsAtOffset(50, -34, null);
        List<Actor> actors3 = getObjectsAtOffset(-50, -34, null);
        for (Actor actor : actors2)
        {
            actors.add(actor);
        }
        for (Actor actor : actors3)
        {
            actors.add(actor);
        }
        LabWorld lab = (LabWorld) getWorld();
        ScoreBoard board = lab.getScoreBoard();
        if (actors.size() == 0)
            return;
        
        for(Actor obj : actors)
        { 
            if (obj != board && obj instanceof Conclusion == false && obj instanceof Platform == false)
            {
                obj.setLocation (obj.getX() + velocityValue * direction, obj.getY());
            }
        }
    }
   
    /**
     * Moves any objects standing on this platform up or down.
     */
    public void freeRideY()
    {
        List<Actor> actors =  getObjectsInRange(43, null);
        List<Actor> actors2 = getObjectsAtOffset(48, -34, null);
        List<Actor> actors3 = getObjectsAtOffset(-48, -34, null);
        for (Actor actor : actors2)
        {
            actors.add(actor);
        }
        for (Actor actor : actors3)
        {
            actors.add(actor);
        }
        LabWorld lab = (LabWorld) getWorld();
        ScoreBoard board = lab.getScoreBoard();
        if (actors.size() == 0)
            return;
        
        for(Actor obj : actors)
        { 
            if (obj != board && obj instanceof Conclusion == false && obj instanceof Platform == false)
            {
                obj.setLocation (obj.getX(), obj.getY() + velocityValue * direction);
            }
        }
    }
   
    /**
     * @return Returns true if this object is near the edge of the world.
     */
    public boolean nearBorder()
    {
        if (directionValue == HORIZONTAL)
        {
            if (getX() > getWorld().getWidth() - getImage().getWidth()/2)
                return true;
            if (getX() < getImage().getWidth()/2) 
                return true;
            if (getX() == originalX + displacementValue)
                return true;
            if (getX() == originalX - displacementValue)
                return true;
        }
        else if (directionValue == VERTICAL)
        {
            if (getY() < getImage().getHeight()/2 + 50)
                return true;
            if (getY() > getWorld().getHeight() - getImage().getHeight()/2)
                return true;
            if (getY() == originalY - displacementValue)
                return true;
            if (getY() == originalY + displacementValue)
                return true;
        }
        return false;
    }
}
