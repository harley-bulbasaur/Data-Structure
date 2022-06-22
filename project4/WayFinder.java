package project4;

/**
 * The goal of this project is to implement a program that finds all solutions to a number puzzle.
 * The puzzle uses an array of positive integers. 
 * The objective is to find a path from index zero to the last index in the array. 
 * At each step we need to know the distance to be traveled and the direction. 
 * Each entry in the array is a number indicating the distance to be traveled on this particular leg of the path. 
 * The player (your program) needs to decide the direction (if the move should be made to the right or to the left).
 * 
 * @author Dingxin Zhang
 *
 */
public class WayFinder 
{
    public static void main(String[] args) 
    {
     //check if command line exists. If they are not passed, then the program terminates. 
  if (args.length == 0) 
  {
   System.err.println("Error: An arguement is required");
   System.exit(1);
  }
  //Create an integer array to store all the numbers from the String args array in the argument
  int [] userarray = new int [args.length];
  for(int i= 0; i<= userarray.length-1; i++) 
  {
   //check if the user input is valid
   try 
   {
	   //copy the array
	   userarray[i] = Integer.parseInt(args[i]);
   }
   //catch exception if the user input is not numeric or out of bound
   catch(NumberFormatException ex) 
   {
    System.err.println("Error: Only numerical digits are allowed");
    System.exit(1);
    }
   
   //print error if user input out of bound
   if (userarray[i]<0 || userarray[i]>99) 
   {
    System.err.println("Error: only positive integers in range between 0 and 99 are allowed.");
    System.exit(1);
   
   }
   
   //check if the last number of the puzzle is 0. 
   if (userarray[userarray.length-1] != 0) 
   {
    System.err.println("Error: the last number of the puzzle must be 0");
    System.exit(1);
   }

  }
         //the main program that solves the puzzle
		 PuzzleSolver ps = new PuzzleSolver(userarray);
	     ps.solve();
		
	}
		
	
	 
	
	 

}
	
