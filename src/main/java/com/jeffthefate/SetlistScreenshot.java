package com.jeffthefate;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class SetlistScreenshot extends Screenshot {

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text;
    private int fontSize;

	public SetlistScreenshot(String templateFile, String fontFile,
			String text, int fontSize, int topOffset, int bottomOffset,
            String filename) {
		super(templateFile, fontFile, topOffset, bottomOffset, filename);
        this.text = text;
        this.fontSize = fontSize;
	}
	
	public void createScreenshot() {
    	ArrayList<String> setlistList = new ArrayList<>(Arrays.asList(
                text.split("\n")));
        BufferedImage img = setupImage();
        int height = img.getHeight();
        int width = img.getWidth();
        setupGraphics(img);
        setupFontMetrics(fontSize, height, setlistList.size());
        int currentHeight = drawStringsToImage(setlistList, width, true);
        setOutputFilename(tearDown(img, width, currentHeight, false));
    }

}
