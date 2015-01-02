   import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
   import java.awt.Color;

/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
   public class HealthBar extends Actor
   {
      private double health = 100;
    /**
     * Act - do whatever the HealthBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
      public void act() 
      {
         makeHealthBar();
      }    
    
      public void makeHealthBar()
      {
         GreenfootImage healthBar = new GreenfootImage(20, 3);
         healthBar.setColor(Color.BLACK);
         healthBar.fillRect(0, 0, 20, 3);
         setImage(healthBar);
         healthBar.setColor(Color.RED);
         healthBar.drawLine(1,1,((int)(((health)/100)*18)),1);
        
      }
    
      public void setHealth(double healthIn)
      {
         health = healthIn;
      }
    
      public double getHealth()
      {
         return health;
      }
   }
