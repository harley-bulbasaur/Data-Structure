package project2;

public class Feature implements Comparable<Feature>{
	private String featureName;
    private String featureClass;
    private Location featureLocation;

    public Feature (String featureName, String featureClass, Location featureLocation) throws IllegalArgumentException 
    {
		if(featureName == null || featureClass == null)
            throw new IllegalArgumentException("Title, description or links cannot be empty or null.");
		if(featureLocation == null) {
			throw new IllegalArgumentException("Not valid location");
		}
        this.featureName = featureName;
        this.featureClass = featureClass;
        this.featureLocation = featureLocation;
	}
    public String getFeatureName()
    {
        return featureName;
    }
    public String getFeatureClass()
    {
        return featureClass;
    }
    public Location getFeatureLocation()
    {
        return featureLocation;
    }
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Feature))
            return false;
        return (this.featureName.toUpperCase().equals(((Feature)obj).featureName.toUpperCase())
        		&& this.featureClass.toUpperCase().equals(((Feature)obj).featureClass.toUpperCase())
        		&& this.featureLocation.equals(((Feature)obj).featureLocation));
    }
     public String toString () 
    {
    	 String output=String.format("%s,%s\n %s,%s\n %.6f, %.6f, %d", 
    	featureName,featureClass, featureLocation.state, featureLocation.county, featureLocation.getLatitude(), featureLocation.getLongitude() ,featureLocation.getElevation());

				return output; 

    }
	@Override
	public int compareTo(Feature o) 
	{
		if(!this.featureName.equalsIgnoreCase(o.featureName))
		{
			return this.featureName.toUpperCase().compareTo(o.featureName.toUpperCase());
		}
		else if(!this.featureClass.equalsIgnoreCase(o.featureClass))
		{
			return this.featureClass.toUpperCase().compareTo(o.featureClass.toUpperCase());
		}
		else
			{
				return this.featureLocation.compareTo(o.featureLocation);
			}
		
		}
	
}
