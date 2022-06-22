package project2;

public class Location implements Comparable<Location> {
	
	    public String state;
	    public String county;
	    private double latitude=0.0;
	    private double longitude=0.0;
	    private int elevation=0;

	    public Location(String state, String county)throws IllegalArgumentException
	    {
			if(state==null)
	            throw new IllegalArgumentException("Error: invalid state name.");
	        if(county==null)
	            throw new IllegalArgumentException("Error: invalid county name.");
	        this.state=state;
	        this.county=county;
	    }
	    public double getLatitude()
	    {
	        return latitude;
	    }
	    public void setLatitude(double latitude) throws IllegalArgumentException
	    {
	       // if(latitude==null)
	       // throw new IllegalArgumentException("Error: invalid latitude number, it should be between -90 and 90.");
	        if(latitude<-90||latitude>90)
	        throw new IllegalArgumentException("Error: invalid latitude number, it should be between -90 and 90.");
	        this.latitude=latitude;
	    }
	    public double getLongitude()
	    {
	        return longitude;
	    }
	    public void setLongitude(double longitude)throws IllegalArgumentException
	    {
	       // if(longitude==null)
	      //  throw new IllegalArgumentException("Error: invalid longtitude number, it should be between -180 and 180.");
	        if(longitude<-180||longitude>180)
	        throw new IllegalArgumentException("Error: invalid longtitude number, it should be between -180 and 180.");
	        this.longitude=longitude;
	    }
	    public int getElevation()
	    {
	        return elevation;
	    }
	    public void setElevation(int elevation)throws IllegalArgumentException
	    {
	      //  if(elevation==null)
	       // throw new IllegalArgumentException("Error: invalid elevation number.");
	        this.elevation=elevation;
	    }
	    public boolean equals(Location loc) 
	    {
			if (this == loc)
				return true;
			if (loc == null)
				return false;
			if (!(loc instanceof Location))
				return false;
			Location other = (Location) loc;
			if (state == null) 
	        {
				if (other.state != null)
					return false;
			}
	        latitude=((double)((int)(latitude*1000000)))/1000000; 
	        other.latitude=((double)((int)(other.latitude*1000000)))/1000000;
	        longitude=((double)((int)(longitude*1000000)))/1000000;
	        other.longitude=((double)((int)(other.longitude*1000000)))/1000000;

	        if (!state.equalsIgnoreCase(other.state)||!county.equalsIgnoreCase(other.county)||latitude!=other.latitude||longitude!=other.longitude||elevation!=other.elevation)
				return false;
			return true;
		}
	    public String toString () 
	    {
			
	      //  state=this.state.substring(0,1).toUpperCase()+this.state.substring(1).toLowerCase();
	      // county=this.county.substring(0,1).toUpperCase()+this.county.substring(1).toLowerCase();
	        String output=String.format("%s,%s\n %.6f, %.6f, %d", 
	        		state, county, getLatitude(), getLongitude() ,getElevation());

				return output; 
		}

		@Override
		public int compareTo(Location o) 
		{ 
			// TODO Auto-generated method stub
			
			if(o == null) 
			{
				throw new IllegalArgumentException("The location cannot be null");
			}
			latitude=((double)((int)(latitude*1000000)))/1000000; 
	        o.latitude=((double)((int)(o.latitude*1000000)))/1000000;
	        longitude=((double)((int)(longitude*1000000)))/1000000;
	       o.longitude=((double)((int)(o.longitude*1000000)))/1000000;
			if (! this.state.equalsIgnoreCase(o.state.toUpperCase())) 
			{
				return this.state.toUpperCase().compareTo(o.state.toUpperCase());
			}
			else if (! this.county.equalsIgnoreCase(o.county)) 
			{
				return this.county.toUpperCase().compareTo(o.county.toUpperCase());
			}
			else if (this.longitude!=o.longitude)
			{
				return (int) (this.longitude-(o.longitude));
			}
			else if(this.latitude!=o.latitude)
			{
				return(int) (this.latitude-(o.latitude));
			}
			else if(this.elevation!=o.elevation)
			{
				return this.elevation-(o.elevation);
			}
			else
			{
				return 0;
			}
		}
			
				
			
		
				
		
}



