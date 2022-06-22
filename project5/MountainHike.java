package project5;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *The main method that is responsible for parsing and validating the command line arguments, 
 *reading and parsing the input file, producing any error messages, handling any exceptions thrown by other classes, and producing output.
 *It creates the mountain, calling functions making a hiker to find all possible path, and print the result.
 *It is able to handle file exceptions and print error message when there is invalid user input.
 *
 * @exception FileNotFoundException
 * @exception IllegalArgumentException
 * @author dingxinzhang
 *
 */
public class MountainHike {
	 /**
	  * The main method that is responsible for parsing and validating the command line arguments, 
	  * reading and parsing the input file, producing any error messages, handling any exceptions thrown by other classes, and producing output.
	  *
	  * @param args user file input
	  * 
	  * 
	  */
	 
	public static void main(String[] args)
	{
        BSTMountain mountain = new BSTMountain();
        String line;
        String[] resArray;
        Boolean hasObstacle;
        RestStop rs;
        ArrayList<String[]> al;

        Scanner scanner = new Scanner(System.in);
        /**
         * @exception NoCommandLine
         */
        if (args.length == 0)
        {
            System.err.println("Error: Command line argument is required");
            System.exit(0);
        }
        /**
         * @exception CommandLineIsNotValid
         */
        else if(args.length>1)
        {
            System.err.println("Error: Only one file is allowed at a time");
            System.exit(0);
        }
        //Reading the file
        try{
            File text = new File(args[0]);
            
            if (!text.exists())
            {
    			System.err.println("Error: the file "+text.getAbsolutePath()+" does not exist.\n");
    			System.exit(0);
    		}
            if (!text.canRead())
            {
    			System.err.println("Error: the file "+text.getAbsolutePath()+
    											" cannot be opened for reading.\n");
    			System.exit(0);
    		}
    		
            Scanner textreader = new Scanner(text);
           
            while(textreader.hasNextLine())
            {
                line = textreader.nextLine();
                resArray = line.split(" ");
                rs = new RestStop();
                /**
                 * @exception StringElementLabelIsNotValid
                 */
                if (!(resArray[0] instanceof String))
                {
                    System.err.println("Error: The first element in each line (label) should be in the type of String ");
                    System.exit(0);
                }
                else{
                    rs.setLabel(resArray[0]);
                }
                hasObstacle = false;
                for(int i=1; i<resArray.length; i++)
                {
                	 /**
                     * @exception StringElementLabelIsNotValid
                     */
                    if (!(resArray[i] instanceof String))
                    {
                        System.err.println("Error: The first element in each line (label) should be in the type of String ");
                        System.exit(0);
                    }
                    /*
                     * create the mountain from the file
                     */
                    if(resArray[i].equals("fallen")) 
                    {
                        if((i+1)<resArray.length && resArray[i+1].equals("tree"))
                        rs.setNewfallentree(rs.getNewfallentree()+1);
                        hasObstacle = true;
                        i++;
                    }
                    else if(resArray[i].equals("river")) 
                    {
                        rs.setNewriver(rs.getNewriver()+1);
                        hasObstacle = true;
                    }
                    if(!hasObstacle)
                    {
                        if(resArray[i].equals("food")) 
                        {
                            rs.setNewfood(rs.getNewfood()+1);
                        }
                        else if(resArray[i].equals("axe")) 
                        {
                            rs.setNewaxe(rs.getNewaxe()+1);
                        }
                        else if(resArray[i].equals("raft"))
                        {
                            rs.setNewraft(rs.getNewraft()+1);
                        }
                    }
                }
                mountain.add(rs);
            }
        }
        /**
         * @exception FileNotFoundException e
         */
        catch(FileNotFoundException e)
        {
            System.err.println("Error: the file cannot be opened for reading.");
            System.exit(0);
        }
        /**
         * @exception IllegalArgumentException e
         */
        catch (IllegalArgumentException e)
        {
            System.err.println("Usage Error: the program expects file name as an argument.");
            System.exit(0);
        }
        scanner.close();
       
        //find all possible ways and print out all the result
        al = mountain.findWay();
        for(int i=0; i<al.size(); i++)
        {
            String[] str = al.get(i);
            for(int j=0; j<str.length-1; j++)
            {
                System.out.print(str[j]+" ");
            }
            System.out.println(str[str.length-1]);
        }
    }
}
