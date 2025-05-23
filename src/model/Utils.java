package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Utils {
	
    public static BufferedImage toBufferedImage(Image img) {
    	if(img == null) {
    		return null;
    	}
        BufferedImage bufferedImg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bufferedImg.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bufferedImg;
    }
    
    public static byte[] toByte(BufferedImage img) {
    	if(img == null) {
    		return null;
    	}
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
			ImageIO.write(img, "png", out);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
        byte[] imageBytes = out.toByteArray();
        return imageBytes;
    }
}
