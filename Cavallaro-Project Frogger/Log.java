import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Log here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Log extends Actor
{
    private int speed = 0;
    /**
     * Act - do whatever the log wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        removeLog();
        move(speed);
    }
   
    /**
     * Create a log
     */
    public Log(int moveSpeed)
    {
        speed = moveSpeed;
    }
    
    /**
     * remove objects at edge of world
     */
    private void removeLog()
    {
        World world = getWorld();
        if (getX()==0)
        {
            world.removeObject(this);
        }
        else if (getX()==549)
        {
            world.removeObject(this);
        }
    }   
    public int getSpeed()
    {
        return speed;
    }
}
