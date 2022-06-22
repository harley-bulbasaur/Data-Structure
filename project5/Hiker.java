package project5;
/**
 * This class represents a hiker traveling down the mountain. 
 * Hiker object is capable of keeping track of all the supplies that the hiker has in their possession. 
 * This information would be updated as the hiker travels along a trail and consumes supplies 
 * (by either eating along the way, or using the tools to clear the path, or cross the river)
 * 
 * @author dingxin zhang
 *
 */
public class Hiker 
{
    private int food = 0;
    private int axe = 0;
    private int raft = 0;
    /**
     * initialize a hiker.
     * @param rs
     */
    public Hiker(RestStop rs)
    {
        food = rs.getNewfood();
        axe = rs.getNewaxe();
        raft = rs.getNewraft();
    }
    /*
     * All helper methods that gets and sets information in the about a hiker's status
     */
    public int getFood() 
    {
        return food;
    }

    public void setFood(int food) 
    {
        this.food = food;
    }

    public int getAxe() 
    {
        return axe;
    }

    public void setAxe(int axe) 
    {
        this.axe = axe;
    }

    public int getRaft() 
    {
        return raft;
    }

    public void setRaft(int raft) 
    {
        this.raft = raft;
    }
    
    //A helper method. If a hiker passes at a rest stop when trying to find all possible path.
    //A hiker gathers all supplies and solves the obstacles
    public void restAt(RestStop rs)
    {
        food += rs.getNewfood();
        axe += rs.getNewaxe();
        axe -= rs.getNewfallentree();
        raft += rs.getNewraft();
        raft -= rs.getNewriver();
    }
    //A helper method. If a hiker cannot pass a rest stop when trying to find all possible path.
    //A hiker should returns to the last rest stop, and reset his resource.
    //A hiker return all supplies and gathers back tools that is used in obstacles
    public void noRestAt(RestStop rs)
    {
        food -= rs.getNewfood();
        axe -= rs.getNewaxe();
        axe += rs.getNewfallentree();
        raft -= rs.getNewraft();
        raft += rs.getNewriver();
    }

}
