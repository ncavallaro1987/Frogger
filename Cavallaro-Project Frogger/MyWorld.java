import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private int carSpeed[] = {-1, 1, -2};
    private int logSpeed[] = {-2, 1, -1};
    private boolean canSpawnLane1 = true;
    private boolean canSpawnLane2 = true;
    private boolean canSpawnLane3 = true;
    private boolean canSpawnLane5 = true;
    private boolean canSpawnLane6 = true;
    private boolean canSpawnLane7 = true; 
    private int lane1SpawnTimer = 80;
    private int lane2SpawnTimer = 80;
    private int lane3SpawnTimer = 80;
    private int lane5SpawnTimer = 180;
    private int lane6SpawnTimer = 180;
    private int lane7SpawnTimer = 180;
    private int remainingLives = 3;
    private int timer = 5000;
    private int score = 0;
    private int safeFrogs = 0;
    
    /**
     * perform actions
     */
    public void act()
    {
       makeCars();
       makeLogs();
       resetSpawnFlag();
       runTimer();
    }
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(550, 625, 1); 
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Frog frog = new Frog();
        addObject(frog,275,599);
        showLives();
        showTimer();
        showScore();
        setPaintOrder(Car.class, Frog.class, RoadkillFrog.class, Log.class);
    }
    
    /**
     * spawn cars
     */
    private void makeCars()
    {
        if (canSpawnLane1)
        {
            Car car = new Car(carSpeed[0]);
            addObject(car, 548, 529);
            canSpawnLane1 = false;
        }
        if (canSpawnLane2)
        {
            Car car = new Car(carSpeed[1]);
            addObject(car, 1, 459);
            canSpawnLane2 = false;
        }
        if (canSpawnLane3)
        {
            Car car = new Car(carSpeed[2]);
            addObject(car, 547, 389);
            canSpawnLane3 = false;
        }
    }
    
    /**
     * reset spawn flags for cars and logs
     */
    private void resetSpawnFlag()
    {
        if (canSpawnLane1 == false)
            {
                if (lane1SpawnTimer > 0)
                {
                    lane1SpawnTimer--;
                }
                else
                {
                    canSpawnLane1 = true;
                    lane1SpawnTimer = Greenfoot.getRandomNumber(180)+80;
                }
            }
        if (canSpawnLane2 == false)
            {
                if (lane2SpawnTimer > 0)
                {
                    lane2SpawnTimer--;
                }
                else
                {
                    canSpawnLane2 = true;
                    lane2SpawnTimer = Greenfoot.getRandomNumber(180)+80;
                }
            }
        if (canSpawnLane3 == false)
            {
                if (lane3SpawnTimer > 0)
                {
                    lane3SpawnTimer--;
                }
                else
                {
                    canSpawnLane3 = true;
                    lane3SpawnTimer = Greenfoot.getRandomNumber(160)+30;
                }
            }
        if (canSpawnLane5 == false)
            {
                if (lane5SpawnTimer > 0)
                {
                    lane5SpawnTimer--;
                }
                else
                {
                    canSpawnLane5 = true;
                    lane5SpawnTimer = Greenfoot.getRandomNumber(150)+180;
                }
            }
        if (canSpawnLane6 == false)
            {
                if (lane6SpawnTimer > 0)
                {
                    lane6SpawnTimer--;
                }
                else
                {
                    canSpawnLane6 = true;
                    lane6SpawnTimer = Greenfoot.getRandomNumber(150)+180;
                }
            }
        if (canSpawnLane7 == false)
            {
                if (lane7SpawnTimer > 0)
                {
                    lane7SpawnTimer--;
                }
                else
                {
                    canSpawnLane7 = true;
                    lane7SpawnTimer = Greenfoot.getRandomNumber(150)+180;
                }
            }
    }
    
    /**
     * spawn logs
     */
    private void makeLogs()
    {
        if (canSpawnLane5)
        {
            Log log = new Log(logSpeed[0]);
            addObject(log, 548, 249);
            canSpawnLane5 = false;
        }
        if (canSpawnLane6)
        {
            Log log = new Log(logSpeed[1]);
            addObject(log, 1, 179);
            canSpawnLane6 = false;
        }
        if (canSpawnLane7)
        {
            Log log = new Log(logSpeed[2]);
            addObject(log, 547, 109);
            canSpawnLane7 = false;
        }
    }
    
    /**
     * calculate current lives
     */
    public void updateLives(int lives)
    {
        remainingLives = remainingLives + lives;
        showLives();
        if (remainingLives == 0)
        {
            defeat();
        }
    }
    
    /**
     * show remaining lives
     */
    private void showLives()
    {
        showText("Lives: " + remainingLives, 40, 9);
    }
    
    /**
     * run the timer
     */
    private void runTimer()
    {
        timer--;
        showTimer();
        if (timer == 0)
        {
            defeat();
        }
    }
    
    /**
     * show timer
     */
    private void showTimer()
    {
        showText("Time: " + timer, 500, 9);
    }
    
    /**
     * show score
     */
    private void showScore()
    {
        showText("Score: " + score, 275, 9);
    }
    
    /**
     * calculate current score
     */
    public void updateScore(int points)
    {
        safeFrogs++;
        score = score + points + (timer/2) + (500 * remainingLives);
        showScore();
        if (safeFrogs == 5)
        {
            victory();
        }
    }
    
    /**
     * End the game in Victory
     */
    private void victory()
    {
        //Greenfoot.playSoung("");
        Greenfoot.stop();
        showText("Victory! Final score: " + score, 275, 312);
    }
    
    /**
     * show game over defeat message
     */
    private void defeat()
    {
        //Greenfoot.playSound();
            Greenfoot.stop();
            showText("GAME OVER", 275, 312);
    }
}
