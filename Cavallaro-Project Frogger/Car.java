import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Car here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Car extends Actor
{
    private int speed = 0;
    /**
     * Act - do whatever the Car wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        removeCar();
        move(speed);
    }
   
    /**
     * Create a car
     */
    public Car(int moveSpeed)
    {
        speed = moveSpeed;
    }
    /**
     * remove objects at edge of world
     */
    private void removeCar()
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
}
