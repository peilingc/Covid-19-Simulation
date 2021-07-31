//import java.lang.*;
//import java.util.*;
import greenfoot.*;

public class MyWorld extends World
{
    //configure 
    private final static int numOfPeople = 300;
    private static final int[] percentOfQuarantine = {0, 25, 50, 75, 90 };   
    private static final int[] percentOfSD = {75, 75, 75, 75, 75};   
    private static final boolean[] isMaskProtected = {false, true, true, true, true};
    private static final boolean[] isHandSanitized = {false, true, true, true, true};
    
    //configure graph settings
    private static final int SCALE_Y = 2;
    private static final Color[] colors = {Color.DARK_GRAY, Color.MAGENTA, Color.RED, Color.BLUE, Color.LIGHT_GRAY};
    
    private int run = 0;
    private int maxInfected = 0;
    private int xOffset = 0;

    public MyWorld()
    {
        super(1000, 600, 1);
        reset();
    }
    
    private void reset(){
        maxInfected = 0;
        xOffset = 0;
        getBackground().setColor(colors[run]);
        populate(percentOfQuarantine[run], percentOfSD[run], isMaskProtected[run], isHandSanitized[run]);
        //getBackground().drawString("Quarantine: " + percentOfQuarantine[run] + "%" , 650, 20 + 25*run);
    }
    
    /*
     * Create population
     */
    public void populate(int percentQuarantine, int percentOfSD, boolean isMaskProtected, boolean isHandSanitized)
    {
        removeObjects(getObjects(Person.class));
        Person.reset();
        Person person =  new Person(false, percentOfSD, isMaskProtected, isHandSanitized);
        person.preInfect();
        addObject(person, Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(600));
        int i =0;
        while (i < numOfPeople) {
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            
            boolean quarantine = Greenfoot.getRandomNumber(100) < percentQuarantine;
            
            addObject( new Person(quarantine, percentOfSD, isMaskProtected, isHandSanitized), x, y);
            i = i + 1;
        }
        
    }

    /*
     * Display info for every action
     */
    public void act()
    {
        displayInfo();
        if (xOffset > getWidth()){
            nextRun();
        }
    }

    private void nextRun(){
        run++;
        if (run == percentOfQuarantine.length){
            removeObjects(getObjects(Person.class));
            Greenfoot.stop();
        }
        else 
            reset();
    }

    /*
     * Display the stastistics of the population
     */
    private void displayInfo()
    {
        if (Person.getNumInfected() > maxInfected) {
            maxInfected = Person.getNumInfected();
        }
        showText("Infected: " + Person.getNumInfected(), 80, 20);
        showText("MaxInfected: " + maxInfected, 100, 40);
        plotValue(Person.getNumInfected());
        //GreenfootImage img = new GreenfootImage("Quarantine: " + percentOfQuarantine[run] + "%  " + 
        //                                        "MaxInfected:  "+ maxInfected, 20, Color.WHITE, colors[run]);
        //GreenfootImage img = new GreenfootImage("Social Distancing: " + percentOfSD[run] + "%  " + 
        //                                       "MaxInfected:  "+ maxInfected, 20, Color.WHITE, colors[run]);
        //GreenfootImage img = new GreenfootImage("Masks Protection: " + String.valueOf(isMaskProtected[run])  +
        //                                        "  MaxInfected:  "+ maxInfected, 20, Color.WHITE, colors[run]);
        GreenfootImage img = new GreenfootImage("Masks: " + String.valueOf(isMaskProtected[run])  +
                                                "  Hand Sanitization: " + String.valueOf(isHandSanitized[run])  +
                                                "  Social Distancing: " + percentOfSD[run] +
                                                "  Quarantine: " + percentOfQuarantine[run] +
                                                "  MaxInfected:  "+ maxInfected + " ", 20, Color.WHITE, colors[run]);
        getBackground().drawImage(img, 200, 20 + 25*run);
    }

    /*
     * Plot value on the panel.
     */
    public void plotValue(int value)
    {
        int yOffset = getHeight() - value * SCALE_Y;
        getBackground().drawLine(xOffset, yOffset, xOffset, yOffset - 4);
        xOffset = xOffset + 1;
    }
    
    public static int getNumOfPeople(){
        return numOfPeople;
    }
}
