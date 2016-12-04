import java.util.ArrayList;
import java.util.Arrays;

public class ConnectedRegions {
	public static void main(String[] args){
		int Values [] = {0,0,1,0,1,1,0,0,0,1,1,1,0,1,1,0,1,0,1,1,0,1,0,0,1};
		int width =5;
		
		// Generate the 2d array visualization from a simple 1d array and a given width
		System.out.println("The following Array was inputed:");
		for(int i=0; i<Values.length;i++){
			System.out.print(Values[i]+"\t");
			if(i%width==width-1){
				System.out.println();
			}
		}
		System.out.println();
		
		
		/*
		 * All Regions is an Array of Arrays. Each element in it is the array "Region"
		 * The array Region lists all the indices of pixels in the specified cluster
		 */
		ArrayList<ArrayList<Integer>> AllRegions = new ArrayList<ArrayList<Integer>>();
	    ArrayList<Integer> Region = new ArrayList<Integer>(); 
		
		for(int i=0;i<Values.length;i++){
			if(Values[i]==1){
				Values[i]=0;
				Region.add(i);
				SearchNear(i, Values, width, Region);
				for(int k=1; k<Region.size();k++){
					SearchNear(Region.get(k), Values, width, Region);
				}
				AllRegions.add(Region);
				Region = new ArrayList<Integer>(Region);
				Region.clear();
			}
		}
		
		
		//print out the number of regions identified and element in each region
		System.out.println("There are a total of "+AllRegions.size()+ " identified.\nThe Regions and indices are:");
		System.out.println(AllRegions);
		
		
	}
	/*
	 * Name: SearchNear
	 * Inputs: Current Pixel (J), Array of Pixels, width of 2d array, array which contains the indices in region (Region)
	 */
	public static void SearchNear(int j, int[] Values, int width, ArrayList<Integer> Region){
		if((j)%(width)!=(width-1)&&Values[j+1]==1){			//make sure that j is not on rightmost layer also
			Region.add(j+1);
			Values[j+1]=0;
		}
		if(j<Values.length-width&&(j)%(width)!=(width-1)&&Values[j+width+1]==1){	//make sure that j is not on rightmost layer or bottom layer
			Region.add(j+width+1);
			Values[j+width+1]=0;
		}
		if(j<Values.length-width&&Values[j+width]==1){			//make sure that j is not on the bottom layer
			Region.add(j+width);
			Values[j+width]=0;
		}
		if(j<Values.length-width&&(j)%(width)!=0&&Values[j-1]==1){			//make sure that j is not on the bottom or leftmost layer
			Region.add(j-1);
			Values[j-1]=0;
		}
		
	}
}
