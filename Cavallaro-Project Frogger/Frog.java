import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Frog here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Frog extends Actor
{
    private boolean keyUp;
    private boolean keyDown;
    private boolean keyLeft;
    private boolean keyRight;
    private boolean canMoveUp = true;
    int xSafeStart[] = {38, 145, 251, 358, 466};
    int xSafeEnd[] = {94, 202, 309, 415, 521};
    int xBlockStart[] = {0, 94, 202, 309, 415, 521};
    int xBlockEnd[] = {38, 145, 251, 358, 466, 625};
    int xSafeFrog[] = {63, 169, 275, 381, 487};
    int i = 0;
    int topLane = 109;
    //int safeFrogs = 0;
    int horizontalMove = 15;
    int verticalMove = 70;
    int moveSpeed = 0;
    int logSpeed[] = {-2, 1, -2};
    
    /**
     * Act - do whatever the Frog wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkSavedFrog();
        roadKill();
        checkDrownFrog();
        rideLog();
        move();
        i=0; //reset loop counter
        canMoveUp = true; //allow to move up again
    }    
    
    /**
     * move frog to start position when reaches safe zone
     */
    private void checkSavedFrog()
    {
        if (getY() == 39)
        {
            createSafeFrog(); 
            resetFrog();
            ((MyWorld) getWorld()).updateScore(50);
        }
    }
    
    /**
     * Move the frog with arrow keys
     */
    private void move()
    {
        move(moveSpeed); //causes frog to move with logs
        if (!keyUp &&  Greenfoot.isKeyDown("up")) 
        {
             moveUp(); //moves up distance = verticalMove
        }
        if (keyUp && !Greenfoot.isKeyDown("up")) 
            {
             keyUp = false; //enables pressing "up" to move again
            }
        if (!keyDown &&  Greenfoot.isKeyDown("down"))
        {
           moveDown(); //moves down distance = verticalMove
        }
        if (keyDown && !Greenfoot.isKeyDown("down"))
        {
            keyDown = false; //enables pressing "down" to move again
        }
        if (!keyLeft &&  Greenfoot.isKeyDown("left"))
        {
            moveLeft(); //moves left distande = horizontalMove
        }
        if (keyLeft && !Greenfoot.isKeyDown("left"))
        {
            keyLeft = false; //enables pressing "left" to move again
        }
        if (!keyRight &&  Greenfoot.isKeyDown("right"))
        {
            moveRight(); //moves right distance = horizontalMove
        }
        if (keyRight && !Greenfoot.isKeyDown("right"))
        {
            keyRight = false; //enables pressing "righht" to move again
        }
    }
    
    /**
     * Move right
     */
    private void moveRight()
    {
        if (getX() < 487) //Check if in right most column
            {
                setLocation(getX()+horizontalMove, getY()); //Move right 1 column
                keyRight = true; //move 1 column per key press
            }
    }
    
    /**
     * Move left
     */
    private void moveLeft()
    {
        if (getX() > 63) //Check if in left most column
            {
                setLocation(getX()-horizontalMove, getY()); //Move left 1 column
                keyLeft = true; //move 1 column per key press
            }
    }
    
    /**
     * Move up
     */
    private void moveUp()
    {
        List neighbours = getNeighbours(70, true, SafeFrog.class); //creates a list of "safe frogs" within 70 pixels of frog
        if (getY() == topLane) //checks if in top lane
        {
            while (i<xBlockStart.length) //initiates loop to check if in a block movement zone
            {
                if (getX() > xBlockStart[i]) //check if in a block zone
                    {if (getX() < xBlockEnd[i]) //check if in a block zone
                        {
                            canMoveUp = false; //prevents moving into victory lane in blocked column
                            return; //exit the method
                        }
                     else
                        {
                            i++; //increments loop counter
                        }
                    }
                else
                    {
                        i++; //increments loop counter
                    }
            }
            if (neighbours.isEmpty()) //check if safe frog above
            {
                canMoveUp = true; //allows frog to move up
            }
            else
            {
                canMoveUp = false;//prevent moving up if cell occupied
            }
            if (canMoveUp == true) //check if can move up
            {
                i=0;
                while (i<xSafeStart.length) //initiate loop to check which safe zone frog is in
                    {
                        if (getX() >= xSafeStart[i])
                        {
                            if (getX() <= xSafeEnd[i])
                            {
                                setLocation(xSafeFrog[i], getY()-verticalMove); //move up 1 lane
                                keyUp = true; //move one lane per key press
                                return; //exits method
                            }
                            else
                            {
                                i++; //increments loop counter
                            }
                        }
                    }
            }
        }
        else if(getY() > 109) //Checks if below lane 7
        {
            setLocation(getX(), getY()-verticalMove); //move up 1 lane
            keyUp = true; //move one lane per key press
             
        } 
    }
    
    /**
     * Move down
     */
    private void moveDown()
    {
         if (getY() < 599) //checks if in bottom lane
            {
                setLocation(getX(), getY()+verticalMove); //move down 1 lane
                keyDown = true; //move 1 lane per key press
            }
    }
    
    /**
     * place frog at starting location
     */
    private void resetFrog()
    {
        setLocation(275, 599); //place frog at start point
    }
    
    /**
     * 
     */
    private void createSafeFrog()
    {
        World world = getWorld(); 
        world.addObject(new SafeFrog(), getX(), getY()); //create a safe frog in the safe row where the frog is
    }
    
    /**
     * kill frogs when they are run over
     */
    private void roadKill()
    {
        if (isTouching(Car.class))
        {
           World world = getWorld(); 
           world.addObject(new RoadkillFrog(), getX(), getY()); //create a road kill frog where frog was run over
           resetFrog(); //puts frog back at start location
           ((MyWorld) getWorld()).updateLives(-1); // reduce remaining lives by1
           Greenfoot.playSound("squish.wav");
        }
    }
    
    /**
     * Kill frogs in water
     */
    private void checkDrownFrog()
    {
        if (getY() == 249)
        {
            if (isTouching(Log.class))
            {
                
            }
            else
            {
                drownFrog();
            }
        }
        else if (getY() == 179)
        {
            if (isTouching(Log.class))
            {
                
            }
            else
            {
                drownFrog();
            }
        }
        else if (getY() == 109)
        {
            if (isTouching(Log.class))
            {
                
            }
            else
            {
                drownFrog();
            }
        }
    }
    
    /**
     * drown the frog
     */
    private void drownFrog()
    {
        World world = getWorld();
        world.addObject(new DrownedFrog(), getX(), getY()); //create a drowned frog where frog was run over
        resetFrog();
        ((MyWorld) getWorld()).updateLives(-1); // reduce remaining lives by1
        Greenfoot.playSound("splash.wav");
    }
    
    /**
     * move frog with logs
     */
    private void rideLog()
    {
        if (isTouching(Log.class))
        {
            if (getY() == 249)
            {
                moveSpeed = -2;
            }
            else if (getY() == 179)
            {
                moveSpeed = 1;
            }
            else if (getY() == 109)
            {
                moveSpeed = -1;
            }
        }
        else
        {
            moveSpeed = 0;
        }
    }
}