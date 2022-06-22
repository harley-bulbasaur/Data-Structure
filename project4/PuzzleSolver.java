package project4;

import java.util.ArrayList;
/**
 * This class helps to solve the puzzle
 * it contains a Node class to record the changes in the array (i.e. 3 is "changed" to 3R)
 * 
 * @author Dingxin Zhang
 *
 */

public class PuzzleSolver {
	/**
	 * 
	 * The Node class contains variables: index, value, and direction
	 * It records and stores the changes in the array
	 * Index means the number's index in the user input array
	 * Value is its numeric value
	 * Direction is either L or R
	 * 
	 */
	
	public class Node
	{
		private int index;
		private int value;
		private String direction;
	
	public Node(int index, int value, String direction)
	{
		this.index=index;
		this.value=value;
		this.direction=direction;
	}
	/**
	 * helper method to get the index
	 * @return index
	 */
	public int getIndex()
	{
		return index;
	}
	/**
	 * helper method to get the value
	 * @return value
	 */
	public int getValue()
	{
		return this.value;
	}
	/**
	 * helper method to get the direction
	 * @return direction
	 */
	public String getDirection()
	{
		return this.direction;
	}
	/**
	 * This method helps to format the output from the Nodes
	 * @Override toString method
	 */
	
     public String toString()
	 {
		 if(index==0)
         return String.format("%2d", getValue()) + direction;
		 else
	     return String.format(" %2d", getValue()) + direction;
     }
     /**
      * This methods helps to use the contain method in the following method "findpath"
      * if not overrode it will automatically use the equal method for object
      * @Override equals method in the contains method
      */
     
     public boolean equals(Object o)
     {
         if(o == this)
         {
             return true;
         }
         if(o == null)
         {
             return false;
         }
         //change the object to Node
         if(! (o instanceof Node))
         {
             return false;
         }
         return index == ((Node)o).getIndex();
     }
 }
   
    /**
     * Parameters in the PuzzleSolver class
     * userArray is an integer array that stores data from the user
     * integer counters to count how many ways through the maze
     * ArrayList of nodes stores the "changes"
     */
	private int[] userArray;
    private int counter;
    ArrayList<Node> change = new ArrayList<Node>(); 
    
    //PuzzleSolver constructor
    public PuzzleSolver(int[] userArray)
    {
        this.userArray= userArray;
        change = new ArrayList<>();
    }
   
    //helper method that solves the puzzle
    public void solve()
    {
        // it uses a recursion to calculate all possible path
        findpath(0);
        if(counter == 0)
            System.out.println("No way through the maze");
        if(counter == 1)
         System.out.println("There is " +counter+ " way through the maze");
        if(counter > 1 )
         System.out.println("There is " +counter+ " ways through the maze");
    }

    /**
     * A recursion method that find all possible paths
     * @param index: current position
     */
    private void findpath(int index)
    {
        // if the position is invalid or a number moves back and forward, return to the last recursion step
        if(index < 0 || index >= userArray.length || change.contains(new Node(index, userArray[index], "R")) || change.contains(new Node(index, userArray[index], "L")))
        {
            return;
        }
        // if a path is found and index moves to the end, a path is print out, and return to the last recursion step
        // counter adds 1 if a path is found
       if(index == userArray.length - 1 && userArray[index] == 0)
       {
            counter++;
            printFinal();
            return;
       }

       	// By default, try move every number to left, if can't, move right
        // if the number moves left, adds a change Node
        // recursion to the corresponding index to see if workable
        // remove the change Node from the storage arraylist 
        change.add(new Node(index, userArray[index], "L"));
        findpath(index - userArray[index]);
        change.remove(change.size()-1);

        // Go to this if move left is not workable
        //if the number moves right, adds a change Node
        // recursion to the corresponding index to see if workable
        // remove the change Node from the storage arraylist 
        change.add(new Node(index, userArray[index], "R"));
        findpath(index + userArray[index]);
        change.remove(change.size()-1);
    }

    /**
     * A helper method
     * print the individual path in the correct format
     */
    private void printFinal()
    {
        // loop the path and print each state
     if(change.size() == 0)
     System.out.println("[ 0 ]");
        for(int j = 0; j < change.size(); j++)
        {
            String result = "";
            result += "[";
            int index = change.get(j).getIndex();
            
            for(int i = 0; i < userArray.length-1; i++)
            {             
                String tmp = "";
                if(index == i)
                {
                    tmp += change.get(j).toString() + ",";
                }
                else
                {
                	// the first digit
                	if (i==0)
                	{
                		tmp += String.format("%2d ", userArray[i])+ "," ;
                	}
                	// other digits
                	else 
                	{
                    tmp += String.format(" %2d ", userArray[i]) + ",";
                	}
                	
                }
                
                result += tmp;
            }
            // the last digit 
            result+= "  0 ]";
            System.out.println(result);
        }
        //move to the next line
        System.out.println();
      }
}