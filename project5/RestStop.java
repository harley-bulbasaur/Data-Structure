package project5;
/**
 * This class represents a single rest stop. 
 * It is capable of storing the label of the rest stop along with a list of the supplies 
 * that a hiker can collect at this rest-stop and a list of obstacles that a hiker may encounter at this rest-stop. 
 * 
 * @implement Comparable<RestStop> interface
 * 
 * @author dingxin zhang
 *
 */
public class RestStop implements Comparable<RestStop>
{
    
	private String label;
    private int newFood;
    private int newAxe;
    private int newRaft;
    private int newFallenTree;
    private int newRiver;
    /**
     * initialize a default rest stop
     */
    public RestStop()
    {
        newFood = 0;
        newAxe = 0;
        newRaft = 0;
        newFallenTree = 0;
        newRiver = 0;
    }
    /*
     * All helper methods that gets and sets information in the RestStop class
     */
    public void setLabel(String label) 
    {
        this.label = label;
    }

    public void setNewfood(int newfood) 
    {
        this.newFood= newfood;
    }

    public void setNewaxe(int newaxe) 
    {
        this.newAxe = newaxe;
    }

    public void setNewraft(int newraft) 
    {
        this.newRaft = newraft;
    }

    public void setNewfallentree(int newfallentree) 
    {
        this.newFallenTree = newfallentree;
    }

    public void setNewriver(int newriver) 
    {
        this.newRiver = newriver;
    }

    public String getLabel() 
    {
        return label;
    }

    public int getNewfood() 
    {
        return newFood;
    }

    public int getNewaxe() 
    {
        return newAxe;
    }

    public int getNewraft() 
    {
        return newRaft;
    }

    public int getNewfallentree() 
    {
        return newFallenTree;
    }

    public int getNewriver() 
    {
        return newRiver;
    }

    /**
     * This method overrides the compareTo in the Comparable method.
     * After override, the RestStop class object can be compared accordingly.
     * 
     * @override compareTo method
     */
    @Override
    public int compareTo(RestStop other) 
    {
        return this.getLabel().compareTo(other.getLabel());
    }
}
