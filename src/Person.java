import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class Person extends Actor
{
    //configure
    private static final int INFECTION_TIME= 400;
    private final int percentSocialDistancing;
    private final int percentMaskProtection; 
    private final int percentHandSanitization; 
    
    private int infection = 0;
    private HealthStatus status;
    private boolean isQuarantine;
    private static int numInfected = 0;
   
    public Person(boolean isquarantine, int percentSD, boolean isMaskProtected, boolean isHandSanitized){
        turn(Greenfoot.getRandomNumber(361));
        isQuarantine = isquarantine;
        percentSocialDistancing = percentSD;
        if (isMaskProtected) percentMaskProtection = 40;
        else percentMaskProtection = 0;
        if (isHandSanitized) percentHandSanitization = 20;
        else percentHandSanitization = 0;
        status = HealthStatus.susceptible;
        reset();
    }
    
    public static int getNumInfected(){
        return numInfected;
    }
    
    public static void reset(){
        numInfected = 0;
    }
    
    /*
     * Called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(!isQuarantine){
            move();
        }
        else quarantine();
        
        if(isInfected()==true){
           infectOthers();
           heal();
        }
    } 
    
    /*
     * Random walk with arbitrary numbers
     */
    public void move(){
        if (getObjectsInRange(10, Person.class)!=null){
            socialDistancing(percentSocialDistancing);
        }
        move(5); 
        
        if(isAtEdge()){
            turn(45); 
        }
        if(Greenfoot.getRandomNumber(101) < 20){
            turn(Greenfoot.getRandomNumber(361));
        }
    }
    
    /*
     * If touching others, infect them.
     * If people are in quarantine, cannot infect them.
     */
    public void infectOthers(){
        List<Person> others = (List<Person>) getIntersectingObjects(Person.class);
        if (others!=null){
            for (Person person : others){
                //Person other = (Person)getOneIntersectingObject(Person.class);
                if(!person.isQuarantine() && Greenfoot.getRandomNumber(101) >= percentSocialDistancing){
                    person.infect();
                }
            }
        }
    }
    /*
     * If we are not immune or infected, we will get infected.
     */
    public void infect(){
        if(status == HealthStatus.susceptible){
            if (Greenfoot.getRandomNumber(101) >= percentMaskProtection + percentHandSanitization){
                infection = INFECTION_TIME;
                if (numInfected < MyWorld.getNumOfPeople()){
                    numInfected++;
                }
                setStatus(HealthStatus.infected);
            }
        }
    }
    /*
     * This method is used for MyWorld to configure the environment.
     */
    public void preInfect(){
        if(status == HealthStatus.susceptible){
            infection = INFECTION_TIME;
            if (numInfected < MyWorld.getNumOfPeople()){
               numInfected++;
            }
            setStatus(HealthStatus.infected);
        }
    }
    
    public boolean isInfected(){
        return status == HealthStatus.infected;
    }
    
    /*
     * If infected, start healing.
     * If infection reaches 0, set the person immune.
     */
    public void heal(){
        if(isInfected()){
            infection--;
            if(infection == 0){
                setStatus(HealthStatus.immune);
                if (numInfected >0){
                    numInfected--;
                }
            }
        }
    }
    
    public void setStatus(HealthStatus newStatus){
        status = newStatus;
        setImage(status.getIcon());
    }
    
    /*
     * @percentSocialDistancing means how many percent of people follow the rule.
     * Radius 5 means social distance.
     */
    private void socialDistancing(int percentSocialDistancing){
        List<Person> others = (List<Person>) getObjectsInRange(10, Person.class);
        for (Person person : others){
            if (person.isQuarantine()){
                this.turn(Greenfoot.getRandomNumber(46));
                person.turn(90);
            }
            else if(Greenfoot.getRandomNumber(101) < percentSocialDistancing){
                this.turn(46);
            }
        }
    }
    
    /*
     * Anyone in quarantine is isolated, which means they cannot be touched and infected.
     */
    private void quarantine(){
        List<Person> others = (List<Person>) getObjectsInRange(5, Person.class);
        for (Person person : others){
            person.turn(Greenfoot.getRandomNumber(361));
        }
    }
    
    public boolean isQuarantine(){
        return this.isQuarantine;
    }
    
    /*
     * These methods used for unit test.
     */
    public HealthStatus getHealthStatus(){
        return this.status;
    }
    public int getInfectionTime(){
        return this.infection;
    }
    
}
