package com.jeffthefate;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

public class TriviaScreenshot extends Screenshot {
	
	public TriviaScreenshot(String templateFile, String fontFile, String title,
			Map<String, Integer> sortedMap, int mainFontSize, int dateFontSize,
    		int limit, int verticalOffset) {
		super(templateFile, fontFile, verticalOffset);
		setOutputFilename(createScreenshot(title, sortedMap, mainFontSize,
				dateFontSize, limit));
	}

	private String createScreenshot(String title,
    		Map<String, Integer> sortedMap, int mainFontSize, int dateFontSize,
    		int limit) {
    	FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(new File(templateFile));
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
	    	g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
	    	        RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
	    	
	        g2d.drawImage(img, 0, 0, null);
	        g2d.setPaint(Color.white);
	        Font font = new Font("Serif", Font.BOLD, mainFontSize);
	        try {
				font = Font.createFont(Font.TRUETYPE_FONT, new File(fontFile));
				font = font.deriveFont((float)mainFontSize).deriveFont(
						Font.BOLD);
			} catch (FontFormatException e1) {
				System.out.println("Couldn't create font from " + fontFile);
				e1.printStackTrace();
			}
	        g2d.setFont(font);
	        int currentHeight = getVerticalOffset();
	        currentHeight += (addCenteredStringToImage(currentHeight, width,
	        		g2d, title) - TEXT_HEIGHT_OFFSET);
	        currentHeight += 40;
	        int position = 1;
	        String positionText;
	        FontMetrics fm = g2d.getFontMetrics();
	        int stringWidth;
	        for (Entry<String, Integer> player : sortedMap.entrySet()) {
	        	if (position > limit)
	        		break;
	        	if (position < 10)
	        		positionText = "# " + position;
	        	else
	        		positionText = "#" + position;
	        	addStringToImage(currentHeight, 50, g2d,
	        			positionText);
	        	addStringToImage(currentHeight, 180, g2d, "@"+player.getKey());
	        	stringWidth = fm.stringWidth(
	        			Integer.toString(player.getValue()));
	        	currentHeight += addStringToImage(currentHeight,
	        			(width-stringWidth)-50, g2d,
	        			Integer.toString(player.getValue()));
	        	position++;
	        }
	        SimpleDateFormat dateFormat = new SimpleDateFormat(
	        		TWEET_DATE_FORMAT);
	        dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
	        Date date = new Date();
	        String dateString = dateFormat.format(date);
	        font = new Font("Serif", Font.BOLD, dateFontSize);
	        try {
				font = Font.createFont(Font.TRUETYPE_FONT, new File(fontFile));
				font = font.deriveFont((float)dateFontSize).deriveFont(
						Font.BOLD);
			} catch (FontFormatException e1) {
				System.out.println("Couldn't create font from " + fontFile);
				e1.printStackTrace();
			}
	        g2d.setFont(font);
	        fm = g2d.getFontMetrics();
	        addStringToImage(currentHeight+(getVerticalOffset()/2), width-fm.stringWidth(dateString)-50,
	        		g2d, dateString);
	        g2d.dispose();
	        bufferedImage = cropImage(bufferedImage, 0, 0, width,
	        		currentHeight, getVerticalOffset());
	    	try {
	    		StringBuilder sb = new StringBuilder();
	    		sb.append("trivia");
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
