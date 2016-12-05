import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class GrayScale {
	public static void main(String[] args){
		
		File originalImage = new File("C:\\Users\\Rohan Deshpande\\Desktop\\Test_1.jpg");
		
		BufferedImage img = null;
		try{
		img = ImageIO.read(originalImage);
		
		//Grayscale Image
		BufferedImage grayscaleImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		for(int i=0; i<img.getHeight();i++){
			for(int j=0; j<img.getWidth();j++){
				Color c= new Color(img.getRGB(j, i));
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();
				int a = c.getAlpha();
				
				// Simple grayscale: (R+G+B)/3
				
				int gr = (r+b+g)/3;
				Color gColor = new Color(gr,gr,gr,a);
				grayscaleImage.setRGB(j, i, gColor.getRGB());
			}
		}
		ImageIO.write(grayscaleImage, "png", new File("C:\\Users\\Rohan Deshpande\\Desktop\\Test2.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
}
