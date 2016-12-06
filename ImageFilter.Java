/**
 * Created by Andy on 12/5/2016.
 */
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageFilter {

    public static BufferedImage getBuff( String FilePath){
        //Function that takes a File Path and Returns an Buffered Image

        File imageFile = new File(FilePath);
        try{
            BufferedImage img = ImageIO.read(imageFile);
            return img;
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writePng( BufferedImage img, String FilePath){
        // writes PNG to a File Path from a buffered image

        try {
            ImageIO.write(img, "png", new File(FilePath));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage rgb2gray(BufferedImage img, String method){

        BufferedImage grayImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // Iterate Thru pixles
        for(int i = 0; i < img.getWidth(); i++){
            for(int j = 0; j  < img.getHeight(); j++){

                Color c = new Color(img.getRGB(i,j));

                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                int a = c.getAlpha();
                
                int gr = 0;

                if (method == "simple"){
                    // Simple average of color intensity
                    gr = (r + b + g) / 3;
                } else if (method == "luma") {
                    // Weights colors differently based on how bright they are perceived as by the human eye
                    gr = (int) Math.floor( r * .3 + g * .59 + b * .11);
                }
                Color gColor = new Color(gr,gr,gr,a);
                grayImg.setRGB(i, j, gColor.getRGB());
            }
        }
        return grayImg;
    }

    public static BufferedImage rgb2gray(BufferedImage img){
        // OverLoads rgb2Gray so that if not provided with a method will default to luma
        return (rgb2gray(img, "luma"));
    }

    public static void main(String[] args){
        BufferedImage img = getBuff("C:\\Users\\Andy\\Pictures\\Landscape.jpg");
        writePng(rgb2gray(img,"simple"),"C:\\Users\\Andy\\Pictures\\LandscapeSimple.png");
    }

}

