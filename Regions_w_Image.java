import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;



public class Regions_w_Image {
	public static void main(String[] args){
		
		File originalImage = new File("C:\\Users\\Rohan Deshpande\\Desktop\\Test_1.jpg");
		
		BufferedImage img = null;
		try{
		img = ImageIO.read(originalImage);
		
		//Grayscale Image
		BufferedImage BlackWhiteImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		for(int i=0; i<img.getHeight();i++){
			for(int j=0; j<img.getWidth();j++){
				Color c= new Color(img.getRGB(j, i));
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();
				int a = c.getAlpha();
				
				// Simple grayscale: (R+G+B)/3
				
				int gr = (r+b+g)/3;
				
				int threshold = 150;
				
				if(gr<=threshold){	//Make Black and White while Inverting Image
					gr=255;
				}
				else{
					gr=0;
				}
				
				Color gColor = new Color(gr,gr,gr,a);
				BlackWhiteImage.setRGB(j, i, gColor.getRGB());
			}
		}
		ImageIO.write(BlackWhiteImage, "png", new File("C:\\Users\\Rohan Deshpande\\Desktop\\Test3.png"));
		
		for(int j=0; j<BlackWhiteImage.getHeight();j++){
			for(int i=0; i<BlackWhiteImage.getWidth();i++){
				Color pixcolor= new Color(BlackWhiteImage.getRGB(i, j));
				int b = pixcolor.getBlue();
				System.out.print(b+"\t");
			}
			System.out.println();
		}
		
		ArrayList<ArrayList<Integer>> AllRegions = new ArrayList<ArrayList<Integer>>();
	    ArrayList<Integer> Region = new ArrayList<Integer>(); 
		
	    
	    for(int j=0;j<BlackWhiteImage.getHeight();j++){
	    	for(int i=0;i<BlackWhiteImage.getWidth();i++){
	    		Color pixcolor= new Color(BlackWhiteImage.getRGB(i, j));
				int b = pixcolor.getBlue();
				if(b==255){
					Color bColor = new Color(0,0,0,1);
					BlackWhiteImage.setRGB(i, j, bColor.getRGB());
					int index=j*BlackWhiteImage.getWidth()+i;
					Region.add(index);
					SearchNear(index,BlackWhiteImage,Region);
					for(int k=1;k<Region.size();k++){
						SearchNear(Region.get(k),BlackWhiteImage,Region);
					}
					AllRegions.add(Region);
					Region = new ArrayList<Integer>(Region);
					Region.clear();
				}
	    	}
	    }
	    
	    System.out.println();System.out.println();
	    System.out.println("There are a total of "+AllRegions.size()+ " identified.");
	    System.out.println();System.out.println();
		String cluster [] = {"AAA","BBB","CCC","DDD","EEE","FFF"};
		boolean found = false;
		int clusternum=-1;
		
		for(int j=0;j<BlackWhiteImage.getHeight();j++){
	    	for(int i=0;i<BlackWhiteImage.getWidth();i++){
	    		
	    		int index=j*BlackWhiteImage.getWidth()+i;
	    		
	    		for(int m=0;m<AllRegions.size();m++){
	    			ArrayList<Integer> Region2 = new ArrayList<Integer>();
	    			Region2=AllRegions.get(m);
	    				if(Region2.indexOf(index)!=-1){
	    					 found = true;
	    					 clusternum=m;
	    				}
	    		}
	    		if(found == true){
	    			System.out.print(cluster[clusternum]+"\t");
	    		}
	    		else{
	    			System.out.print(0+"\t");
	    		}
	    		
	    		clusternum = -1;
	    		found = false;
	    		
			}
	    	System.out.println();
    	}
	    
		
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void SearchNear(int index, BufferedImage BlackWhiteImage, ArrayList<Integer> Region){
		int j = index/BlackWhiteImage.getWidth();
		int i = index-(j*BlackWhiteImage.getWidth());
		
		if(i<BlackWhiteImage.getWidth()	&&	j!=0){
			Color pixcolor= new Color(BlackWhiteImage.getRGB(i+1, j-1));
			int b = pixcolor.getBlue();
			if(b==255){
				int index2=(j-1)*BlackWhiteImage.getWidth()+i+1;
				Region.add(index2);
				Color bColor = new Color(0,0,0,1);
				BlackWhiteImage.setRGB(i+1, j-1, bColor.getRGB());
			}
		}
		if(i<BlackWhiteImage.getWidth()){
			Color pixcolor= new Color(BlackWhiteImage.getRGB(i+1, j));
			int b = pixcolor.getBlue();
			if(b==255){
				int index2=j*BlackWhiteImage.getWidth()+i+1;
				Region.add(index2);
				Color bColor = new Color(0,0,0,1);
				BlackWhiteImage.setRGB(i+1, j, bColor.getRGB());
			}
		}
		
		if(i<BlackWhiteImage.getWidth()&&j<BlackWhiteImage.getHeight()){
			Color pixcolor= new Color(BlackWhiteImage.getRGB(i+1, j+1));
			int b = pixcolor.getBlue();
			if(b==255){
				int index2=(j+1)*BlackWhiteImage.getWidth()+i+1;
				Region.add(index2);
				Color bColor = new Color(0,0,0,1);
				BlackWhiteImage.setRGB(i+1, j+1, bColor.getRGB());
			}
		}
		
		if(j<BlackWhiteImage.getHeight()){
			Color pixcolor= new Color(BlackWhiteImage.getRGB(i, j+1));
			int b = pixcolor.getBlue();
			if(b==255){
				int index2=(j+1)*BlackWhiteImage.getWidth()+i;
				Region.add(index2);
				Color bColor = new Color(0,0,0,1);
				BlackWhiteImage.setRGB(i, j+1, bColor.getRGB());
			}
		}
		if(i!=0	&&	j!=0){
			Color pixcolor= new Color(BlackWhiteImage.getRGB(i-1, j+1));
			int b = pixcolor.getBlue();
			if(b==255){
				int index2=(j+1)*BlackWhiteImage.getWidth()+i-1;
				Region.add(index2);
				Color bColor = new Color(0,0,0,1);
				BlackWhiteImage.setRGB(i-1, j+1, bColor.getRGB());
			}
		}
	}
	
	
	
}
