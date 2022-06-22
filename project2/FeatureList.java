package project2;

import java.util.ArrayList;

public class FeatureList extends ArrayList<Feature> {
	
	public FeatureList() {
		super();
	}
	
	
	public void sort(FeatureList ftlist) {
		for(int i = 0; i < ftlist.size()-1; i++){
            for(int j = i+1; j < ftlist.size(); j++){
                if(ftlist.get(i).compareTo(ftlist.get(j)) > 0)
                    swap(ftlist, i, j);
            }
        }
		
	}
	
    public static void swap(FeatureList ftlist, int i, int j){
        Feature temp = ftlist.get(i);
        ftlist.set(i, ftlist.get(j));
        ftlist.set(j, temp);
    }
	
	public FeatureList getByName ( String keyword ) throws IllegalArgumentException{
		if (keyword == null || keyword.equals("")) {
			throw new IllegalArgumentException("The keyword should not be null or empty.");
		}
		FeatureList ftlist = new FeatureList();
		
		for (int i = 0; i < this.size(); i++) {
            if(this.get(i).getFeatureName().toUpperCase().contains(keyword.toUpperCase())) {
            	ftlist.add(this.get(i));
            }
		}
		
		sort(ftlist);
		
		if (ftlist.size() == 0) {
			return null;
		}
		
		return ftlist;
	}

	public FeatureList getByClass ( String keyword ) throws IllegalArgumentException{
		if (keyword == null || keyword.equals("")) {
			throw new IllegalArgumentException("The keyword should not be null or empty.");
		}
		FeatureList ftlist = new FeatureList();
		
		for (int i = 0; i < this.size(); i++) {
            if(this.get(i).getFeatureClass().toUpperCase().contains(keyword.toUpperCase())) {
            	ftlist.add(this.get(i));
            }
		}
		
		sort(ftlist);
		
		if (ftlist.size() == 0) {
			return null;
		}
		
		return ftlist;
	}
	
	public FeatureList getByState ( String state ) throws IllegalArgumentException{
		if (state == null || state.equals("")) {
			throw new IllegalArgumentException("The state should not be null or empty.");
		}
		
		FeatureList ftlist = new FeatureList();
		
		for (int i = 0; i < this.size(); i++) {
            if(this.get(i).getFeatureLocation().state.toUpperCase().contains(state.toUpperCase())) {
            	ftlist.add(this.get(i));
            }
		}
		
		sort(ftlist);
		
		if (ftlist.size() == 0) {
			return null;
		}
		
		return ftlist;
		
	}
}
