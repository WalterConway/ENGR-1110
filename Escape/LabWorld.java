import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * LabWorld initializes the game scenarios (not the title screen).
 * Use the populateWorld() method with the level number to go to the next level.
 * 
 */
public class LabWorld extends World
{
    private ScoreBoard theScoreBoard = new ScoreBoard(5,100,2,5);
    private int level = 0;
    private Robot Frank = new Robot();
    public Splash teleport = new Splash(3);
    private boolean hasTeleport = false;
    /**
     * Constructor for objects of class LabWorld.
     */
    public LabWorld()
    {    
        super(500, 500, 1);
        populateWorld(1);
        setPaintOrder(ScoreBoard.class, Conclusion.class, Projectile.class, Robot.class, Enemy.class);
    }

    /**
     * Removes all Actors in the world already, then places
     * all Actors for another level in the world.
     * @param level The level number to create. Actual levels
     * start at 0, not 1. -1 is the title screen.
     */
    public void populateWorld(int levelIn)
    {
        level = levelIn;
        removeObjects(getObjects(null));
        switch (level){
            case -1:
            addObject(new Splash(0), 275, 175);
            addObject(new Splash(1), 140, 245);
            addObject(new Splash(2), 240, 125);
            setBackground("Title Screen.png");
            break;
            case 0:
            setBackground("Background.png");
            //Score Board
            addObject(theScoreBoard, getWidth()/2, 6);
            //Frank
            addObject(Frank, 40, 445);
            Frank.resetOrigCheck();
            //Scientist
            addObject(new Enemy(2), 300, 300);
            //Platforms
            addObject(new Platform(), 150, 400);
            addObject(new Platform(), 100, 300);
            makeFloor();
            //Door
            addObject(new Door(true), 480, 425); //door needs a key
            //Key
            addObject(new Item(3), 300, 450);
            break;
            case 1:
            addObject(Frank, 40, 445);
            Frank.resetOrigCheck();
            //Platforms
            addObject(new Platform(), 177, 130);
            addObject(new Platform(true, 1, 1, 60),58, 235);
            addObject(new Platform(),457, 90);
            addObject(new Platform(), 320, 118);
            addObject(new Platform(true, 0, 1, 55), 215, 280);
            addObject(new Platform(),497, 229);
            addObject(new Platform(),486, 375);
            addObject(new Platform(true, 1,1,60),368, 306);
            makeFloor();
            //Enemies
            addObject(new Enemy(0,41,40,1,1),200, 100);
            addObject(new Enemy(0,41,40,1,1),468,60);
            addObject(new Enemy(0,41,40,1,1),413, 453);
            addObject(new Enemy(0,41,40,1,1),500,200);
            addObject(new Enemy(0,41,40,1,1),390,270);
            addObject(new Enemy(1), 200,445);
            //Door
            addObject(new Door(false),480, 47); // door needs no key
            //Items
            addObject(new Item(1), 480, 440);
            //Score Board
            addObject(theScoreBoard, getWidth()/2, 6);
            //Conclusion Screen
            addObject(new Conclusion(1),getWidth()/2,150);
            break;
            case 2:
            addObject(Frank, 13, 446);
            Frank.resetOrigCheck();
            //Floor
            makeFloor();
            // floor level 2
            addObject(new Platform(true, 1,1,60),50, 140);
            addObject(new Platform(true, 0,1,100),263, 197);
            addObject(new Platform(),150, 83);
            addObject(new Platform(),250, 83);
            addObject(new Platform(),350, 83);
            addObject(new Platform(),450, 83);
            //floor level 1
            addObject(new Platform(),50, 300);
            addObject(new Platform(),150, 300);
            addObject(new Platform(),250, 300);
            addObject(new Platform(),350, 300);
            addObject(new Platform(),487, 192);
            //moving plt under lvl 1
            addObject(new Platform(true, 0,1,100),287, 368);
            //Enemy
            addObject(new Enemy(1),488, 446);
            addObject(new Enemy(1),327,50);
            addObject(new Enemy(1),47,268);
            addObject(new Enemy(0,21,20,1,1),49, 110);
            addObject(new Enemy(0,51,50,1,1),461, 55);
            addObject(new Enemy(0,11,10,1,1),471, 165);
            //Item
            addObject(new Item(3), 483, 136);
            //Door
            addObject(new Door(true),480, 41); // door needs a key
            //Score Board
            addObject(theScoreBoard, getWidth()/2, 6);
            //Conclusion Screen
            addObject(new Conclusion(1),getWidth()/2,150);
            break;
            case 3: //level Three
            addObject(Frank = new Robot(), 250, 446);
            Frank.resetOrigCheck();
            // addObject(new Robot(false), 100, 100);
            // Platforms
            addObject(new Platform(), 139, 390);
            addObject(new Platform(true, 1, 1, 20), 87, 279);
            addObject(new Platform(), 238, 390);
            addObject(new Platform(true, 0, 1, 60), 395, 322);
            addObject(new Platform(), 256, 233);
            addObject(new Platform(true, 1, 1, 35), 57, 111);
            addObject(new Platform(true, 1, 1, 35), 155, 111);
            addObject(new Platform(true, 1, 1, 50), 476, 109);
            addObject(new Platform(true, 1, 1, 50), 378, 109);
            //Evil Robots
            addObject(new Enemy(1), 12,446);
            addObject(new Enemy(1), 462,447);
            //Drones
            addObject(new Enemy(0,29,28,0,1), 86, 251);
            addObject(new Enemy(0,80,79,0,1), 144, 84);
            addObject(new Enemy(0,31,30,0,1), 378, 82);
            //Key
            addObject(new Item(3), 25, 83);
            //Missiles
            addObject(new Item(1), 488, 451);
            //Health
            addObject(new Item(0), 259, 204);
            //Door
            addObject(new Door(true),480, 67); // door needs a key
            makeFloor();
            addObject(theScoreBoard, getWidth()/2, 6);
            //Conclusion Screen
            addObject(new Conclusion(1),getWidth()/2,150);
            break;
            default:
            addObject(Frank, 10, 10);
            Frank.resetOrigCheck();
            //Score Board
            addObject(theScoreBoard, getWidth()/2, 6);
            //Conclusion Screen
            addObject(new Conclusion(1),getWidth()/2,150);
            makeFloor();
        }
    }

    /**
     * Creates a solid floor. Use on every level.
     */
    public void makeFloor()
    {
        addObject(new Platform(), 50, 480);
        addObject(new Platform(),150, 480);
        addObject(new Platform(),250, 480);
        addObject(new Platform(),350, 480);
        addObject(new Platform(),450, 480);
    }

    /**
     * @return Returns the scoreboard.
     */
    public ScoreBoard getScoreBoard()
    {
        return theScoreBoard;
    }

    /**
     * @return Returns the current level.
     */
    public int getLevel()
    {
        return level;
    }

    /**
     * @return Returns Frank.
     */
    public Robot getFrank()
    {
        return Frank;
    }

    /**
     * Resets Frank to his original location for the level.
     */
    public void resetFrank()
    {
        Frank.setLocation(Frank.getOrigX(), Frank.getOrigY());
    }

    /**
     * Places the teleport splash in the world.
     */
    public void addTeleport()
    {
        addObject(teleport, 250, 250);
        hasTeleport = true;
    }

    /**
     * Removes the teleport splash from the world.
     */
    public void removeTeleport()
    {
        removeObject(teleport);
        hasTeleport = false;
    }

    /**
     * @return Returns true if the teleport splash is in the world.
     */
    public boolean hasTeleport()
    {
        return hasTeleport;
    }
}