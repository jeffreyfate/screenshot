package com.jeffthefate;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class SetlistScreenshot extends Screenshot {

	public SetlistScreenshot(String templateFile, String fontFile,
			String text, int fontSize, int verticalOffset, String filename) {
		super(templateFile, fontFile, verticalOffset, filename);
        setOutputFilename(createScreenshot(text, fontSize));
	}
	
	String createScreenshot(String text, int fontSize) {
    	ArrayList<String> setlistList = new ArrayList<String>(Arrays.asList(
                text.split("\n")));
        BufferedImage img = setupImage();
        int height = img.getHeight();
        int width = img.getWidth();
        setupGraphics(img);
        setupFontMetrics(fontSize, height, setlistList.size());
        int currentHeight = drawStringsToImage(setlistList, width, true);
        return tearDown(img, width, currentHeight, true);
    }

}
