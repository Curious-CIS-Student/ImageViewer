import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * OFImage is a class that defines an image in OF (Objects First) format.
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 1.0
 */
public class OFImage extends BufferedImage
{
    /**
     * Create an OFImage copied from a BufferedImage.
     * @param image The image to copy.
     */
    public OFImage(BufferedImage image)
    {
         super(image.getColorModel(), image.copyData(null), 
               image.isAlphaPremultiplied(), null);
    }

    /**
     * Create an OFImage with specified size and unspecified content.
     * @param width The width of the image.
     * @param height The height of the image.
     */
    public OFImage(int width, int height)
    {
        super(width, height, TYPE_INT_RGB);
    }

    /**
     * Set a given pixel of this image to a specified color. The
     * color is represented as an (r,g,b) value.
     * @param x The x position of the pixel.
     * @param y The y position of the pixel.
     * @param col The color of the pixel.
     */
    public void setPixel(int x, int y, Color col)
    {
        int pixel = col.getRGB();
        setRGB(x, y, pixel);
    }
    
    /**
     * Get the color value at a specified pixel position.
     * @param x The x position of the pixel.
     * @param y The y position of the pixel.
     * @return The color of the pixel at the given position.
     */
    public Color getPixel(int x, int y)
    {
        int pixel = getRGB(x, y);
        return new Color(pixel);
    }
    
    /**
     * Make this image a bit darker.
     */
    public void darker()
    {
        int height = getHeight();
        int width = getWidth();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                setPixel(x, y, getPixel(x, y).darker());
            }
        }
    }
    
    /**
     * Make this image a bit lighter.
     */
    public void lighter()
    {
        int height = getHeight();
        int width = getWidth();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                setPixel(x, y, getPixel(x, y).brighter());
            }
        }
    }
    
    /**
     * Make this image grayscale according to a threshold.
     */
    public void threshold()
    {
        int height = getHeight();
        int width = getWidth();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixelColor = getPixel(x, y);
                int red = pixelColor.getRed();
                int green = pixelColor.getGreen();
                int blue = pixelColor.getBlue();
                int threshold = (red + green + blue);
                    setPixel(x, y, new Color(0,0,0));
                }
                else if (threshold < 240) {
                    setPixel(x, y, new Color(125,125,125));
                }
                else { // threshold >= 170
                    setPixel (x, y, new Color(255,255,255));
                }
            }
        }
    }
}
