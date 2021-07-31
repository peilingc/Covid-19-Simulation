public enum HealthStatus  
{
    susceptible("normal.png"), infected("infected.png"), immune("immune.png");
    
    private String icon;
    
    private HealthStatus(String iconName){
        icon = iconName;
    }
    
    public String getIcon(){
        return this.icon;
    }
}
