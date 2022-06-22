package project2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EveryPlaceHasAName 
{
	public static void main(String [] args)
	{
		System.out.println("Search the dataset by using one of the following queries.\n"
				+ "  To search for features by keyword in their name, enter\n"
				+ "	name KEYWORD\n"
				+ "  To limit the search to a particular class of features , enter\n"
				+ "	name KEYWORD class FEATURE_CLASS\n"
				+ "  To limit the search to a particular state, enter\n"
				+ "	name KEYWORD state STATE\n"
				+ "  Or combine both restrictions by entering\n"
				+ "	name KEYWORD class CLASS state STATE\n"
				+ "    or\n"
				+ "	name KEYWORD state STATE class CLASS\n"
				+ "    To terminate the program, enter\n"
				+ "	quit");
		
		if (args.length == 0 ) {
			System.err.println("Usage Error: the program expects file name as an argument.\n");
			System.exit(1);
		}
		
		//verify that command line argument contains a name of an existing file 
		File placeFile = new File(args[0]); 
		if (!placeFile.exists()){
			System.err.println("Error: the file "+placeFile.getAbsolutePath()+" does not exist.\n");
			System.exit(1);
		}
		if (!placeFile.canRead()){
			System.err.println("Error: the file "+placeFile.getAbsolutePath()+
											" cannot be opened for reading.\n");
			System.exit(1);
		}
		
		//open the file for reading 
		Scanner Places = null; 
		
		
		try {
			Places = new Scanner (placeFile ) ;
		} catch (FileNotFoundException e) {
			System.err.println("Error: the file "+placeFile.getAbsolutePath()+
											" cannot be opened for reading.\n");
			System.exit(1);
		}
		
		//read the content of the file and save the data in a list of named colors
		FeatureList list = new FeatureList(); 
		String readline=null;
		String [] features = new String [20]; 
		String featureName = null;
		String featureClass = null; 
		Location featureLocation=null;
		String featureState = null;
		String featureCounty = null;
		double featurelatitude=0.0;
		double featurelongitude=0.0;
		int featureelevation=0;
		Feature current = null;
		while (Places.hasNextLine()) {
			try { 
				readline = Places.nextLine(); 
				for(int i=0;i<features.length;i++)
				{
					features[i]=(splitInputLine(readline))[i];					
				}
				featureName=features[1];
				featureClass=features[2];
				featureState=features[3];
				featureCounty=features[5];
				featurelatitude=Double.parseDouble(features[9]);
				featurelongitude=Double.parseDouble(features[10]);
				featureelevation=Integer.parseInt(features[16]);
				}
				
			catch (NoSuchElementException ex ) {
				//caused by an incomplete or miss-formatted line in the input file
				System.err.println(readline);
				continue; 	
			}
			try {
				featureLocation=new Location(featureState,featureCounty);
				featureLocation.setLatitude(featurelatitude);
				featureLocation.setLongitude(featurelongitude);
				featureLocation.setElevation(featureelevation);
				current = new Feature (featureName,featureClass,featureLocation );
				list.add(current); 
			}
			catch (IllegalArgumentException ex ) {
				//ignore this exception and skip to the next line 
			}
		}
		
		
		//interactive mode: 
		
		Scanner userInput  = new Scanner (System.in ); 
		String userValue = "";
		
		
		do {
			System.out.println("Enter your search query:" );
			//get value of from the user 
			userValue = userInput.nextLine();
			if (!userValue.equalsIgnoreCase("quit")) 
			{ 
				userValue.toLowerCase();
				
				FeatureList fl1=new FeatureList();
				//FeatureList fl2=new FeatureList();
				//FeatureList fl3=new FeatureList();
				String [] userIn =userValue.split(" ");
				if(userIn[0].equals("name"))
				{
					 fl1 = list.getByName(userIn[1]);
					if(userIn[2].equals("class"))
					{
						 fl1 = fl1.getByClass(userIn[3]);
						 if(userIn[4].equals("state"))
						 {
							 fl1 = fl1.getByState(userIn[5]);
						 }
					}
					if(userIn[2].equals("state"))
					{
						 fl1 = fl1.getByState(userIn[3]);
						 if(userIn[4].equals("class"))
						 {
							 fl1 = fl1.getByClass(userIn[5]);
						 }
					}				
				}	
				if (fl1 == null ) 
				{
					try {
						System.out.println("No matches found. Try again.");
					}
					catch (IllegalArgumentException ex ) {
						System.out.println("Error: This is not a valid query. Try again."); 
					continue;
					}
				}
				else
				{
				for(Feature f:fl1)
				System.out.println(f.toString() + "\n"+"-----------"); 
				}
			}
			
		} while (!userValue.equalsIgnoreCase("quit"));     
		
		userInput.close();
		
		
	}
	/**
	 * Splits the given line of a pipe-delimited file according to | characters.
	 * @author Joanna Klukowska
	 * @param textLine	a line of text to be parsed
	 * @return the array containing words (or empty strings) from between | characters
	 */
	public static String [] splitInputLine(String textLine){

		if (textLine == null ) return null;

		String [] entries = null;

		entries = textLine.split("\\|");

		return entries;
	}
}
