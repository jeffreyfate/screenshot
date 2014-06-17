package com.jeffthefate;

import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Hello world!
 *
 */
public abstract class Screenshot {
	
	protected final int TEXT_HEIGHT_OFFSET = 2;
	protected final String TWEET_DATE_FORMAT = "yyyy-MM-dd";

    public String getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(String templateFile) {
        this.templateFile = templateFile;
    }

    public String getFontFile() {
        return fontFile;
    }

    public void setFontFile(String fontFile) {
        this.fontFile = fontFile;
    }

    private String templateFile;
	private String fontFile;
	private String outputFile;
	private int verticalOffset;

    private Logger logger = Logger.getLogger(Screenshot.class);
	
	public Screenshot(String templateFile, String fontFile,
            int verticalOffset) {
		this.templateFile = templateFile;
		this.fontFile = fontFile;
		this.verticalOffset = verticalOffset;
	}
	
	public String getOutputFilename() {
		return outputFile;
	}
	
	public void setOutputFilename(String outputFile) {
		this.outputFile = outputFile;
	}
	
	public int getVerticalOffset() {
		return verticalOffset;
	}

    /**
     * Check if another line of text will fit on the image.
     *
     * @param imageHeight   height of the image to be drawn on
     * @param g2d           graphics object that will be drawn on
     * @param numLines      number of lines of text to be drawn
     * @return              true if the text will fit
     */
	boolean willTextFit(int imageHeight, Graphics2D g2d,
            int numLines) {
    	int totalTextHeight = numLines * (g2d.getFontMetrics().getHeight() -
                TEXT_HEIGHT_OFFSET);
    	return totalTextHeight <= (imageHeight - verticalOffset);
    }

    /**
     * Add a horizontally centered string to the image.
     *
     * @param startHeight   vertical position to start drawing text
     * @param width         horizontal size of image to be drawn on
     * @param g2d           graphics object to draw on
     * @param string        text to draw
     * @return              height of the text that was drawn
     */
	int addCenteredStringToImage(int startHeight, int width,
    		Graphics2D g2d, String string) {
    	FontMetrics fm = g2d.getFontMetrics();
    	int stringWidth = fm.stringWidth(string);
        return addStringToImage(startHeight, (width / 2) - (stringWidth / 2),
                g2d, string);
    }

    /**
     * Add a string to the image at the given position.
     *
     * @param startHeight   vertical position to start drawing text
     * @param startPos      horizontal position to start drawing text
     * @param g2d           graphics object to draw on
     * @param string        text to draw
     * @return              height of the text that was drawn
     */
	int addStringToImage(int startHeight, int startPos,
    		Graphics2D g2d, String string) {
    	FontMetrics fm = g2d.getFontMetrics();
        int x = startPos;
        int textHeight = fm.getHeight();
        int y = textHeight + startHeight;
        g2d.drawString(string, x, y);
        return textHeight;
    }

    /**
     * Crop the current image to the given size dimensions.
     *
     * @param img           image to crop
     * @param startX        start horizontal position of the cropped image
     * @param startY        start vertical position of the cropped image
     * @param cropWidth     total width of the cropped image
     * @param cropHeight    total height of the cropped image
     * @param bottomBuffer  extra space to include at the bottom of the cropped
     *                      image
     * @return              the newly cropped image
     */
	BufferedImage cropImage(BufferedImage img, int startX, int startY,
			int cropWidth, int cropHeight, int bottomBuffer) {
		Dimension size = new Dimension(cropWidth, cropHeight + bottomBuffer);
		Rectangle clip = createClip(img, size, startX, startY);
		int w = clip.width;
		int h = clip.height;
		 
		return img.getSubimage(clip.x, clip.y, w, h);
	}

	private Rectangle createClip(BufferedImage img, Dimension size, int clipX,
		    int clipY) {
        if (clipX < 0) {
            clipX = 0;
        }
        if (clipY < 0) {
            clipY = 0;
        }
        Rectangle clip;
        if ((size.width + clipX) <= img.getWidth() &&
                (size.height + clipY) <= img.getHeight()) {
            clip = new Rectangle(size);
            clip.x = clipX;
            clip.y = clipY;
        }
        else {
            if ((size.width + clipX) > img.getWidth()) {
                size.width = img.getWidth() - clipX;
            }
            if ((size.height + clipY) > img.getHeight()) {
                size.height = img.getHeight() - clipY;
            }
            clip = new Rectangle(size);
            clip.x = clipX;
            clip.y = clipY;
        }
        return clip;
	}
}