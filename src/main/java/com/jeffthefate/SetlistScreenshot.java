package com.jeffthefate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class SetlistScreenshot extends Screenshot {

	public SetlistScreenshot(String templateFile, String fontFile,
			String setlistText, int fontSize, int verticalOffset) {
		super(templateFile, fontFile, verticalOffset);
		setOutputFilename(createScreenshot(setlistText, fontSize));
	}
	
	private String createScreenshot(String setlistText, int fontSize) {
    	ArrayList<String> setlistList = new ArrayList<String>(Arrays.asList(
    			setlistText.split("\n")));
    	FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(new File(getTemplateFile()));
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
			return null;
		}
    	BufferedImage img;
    	String filename = null;
		try {
			img = ImageIO.read(fileInput);
			int width = img.getWidth();
	    	int height = img.getHeight();
	    	BufferedImage bufferedImage = new BufferedImage(width, height,
	    			BufferedImage.TYPE_INT_RGB);
	    	Graphics2D g2d = bufferedImage.createGraphics();
	    	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	    			RenderingHints.VALUE_ANTIALIAS_ON);
	    	g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
	    	        RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
	    	g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
	    			RenderingHints.VALUE_RENDER_QUALITY);
	        g2d.drawImage(img, 0, 0, null);
	        g2d.setPaint(Color.white);
	        Font font = null;
	        do {
	        	System.out.println("Font size: " + fontSize);
	        	font = new Font("Serif", Font.BOLD, --fontSize);
		        try {
					font = Font.createFont(Font.TRUETYPE_FONT, new File(
							getFontFile()));
					font = font.deriveFont((float)fontSize).deriveFont(
							Font.BOLD);
				} catch (FontFormatException e1) {
					System.out.println("Couldn't create font from " +
							getFontFile());
					e1.printStackTrace();
				}
		        g2d.setFont(font);
	        } while (!willTextFit(height, g2d, setlistList.size()));
	        System.out.println(font.getFontName());
	        System.out.println(font.getFamily());
	        System.out.println("size: " + font.getSize());
	        System.out.println("bold: " + font.isBold());
	        int currentHeight = getVerticalOffset();
	        for (String line : setlistList) {
        		currentHeight += (addCenteredStringToImage(currentHeight, width,
        				g2d, line) - TEXT_HEIGHT_OFFSET);
	        }
	        System.out.println("height: " + height);
	        System.out.println("currentHeight: " + currentHeight);
	        g2d.dispose();
	        bufferedImage = cropImage(bufferedImage, 0, 0, width,
	        		currentHeight, getVerticalOffset());
	    	try {
	    		StringBuilder sb = new StringBuilder();
	    		sb.append("setlist");
	    		sb.append(System.currentTimeMillis());
	    		sb.append(".jpg");
	    		filename = sb.toString();
		    	File file = new File(filename);
		    	ImageIO.write(bufferedImage, "jpg", file);
	    	} catch (IOException e) { }
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return filename;
    }

}
