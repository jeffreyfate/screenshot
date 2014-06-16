package com.jeffthefate;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;

/**
 * Hello world!
 *
 */
public abstract class Screenshot {
	
	protected final int TEXT_HEIGHT_OFFSET = 2;
	protected final String TWEET_DATE_FORMAT = "yyyy-MM-dd";
	
	protected String templateFile;
	protected String fontFile;
	private String outputFile;
	private int verticalOffset;
	
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
	
	protected boolean willTextFit(int imageHeight, Graphics2D g2d,
    		int numLines) {
    	FontMetrics fm = g2d.getFontMetrics();
    	int totalTextHeight = numLines * (fm.getHeight() - TEXT_HEIGHT_OFFSET);
    	System.out.println(imageHeight + " : " + totalTextHeight);
    	return totalTextHeight <= (imageHeight - verticalOffset);
    }
	
	protected int addCenteredStringToImage(int startHeight, int width,
    		Graphics2D g2d, String string) {
    	FontMetrics fm = g2d.getFontMetrics();
    	int stringWidth = fm.stringWidth(string);
        int x = (width / 2) - (stringWidth / 2);
        int textHeight = fm.getHeight();
        int y = textHeight + startHeight;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
    			RenderingHints.VALUE_ANTIALIAS_ON);
    	g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
    	        RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
    	g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
    			RenderingHints.VALUE_RENDER_QUALITY);
        g2d.drawString(string, x, y);
        return textHeight;
    }
    
	protected int addStringToImage(int startHeight, int startPos,
    		Graphics2D g2d, String string) {
    	FontMetrics fm = g2d.getFontMetrics();
        int x = startPos;
        int textHeight = fm.getHeight();
        int y = textHeight + startHeight;
        g2d.drawString(string, x, y);
        return textHeight;
    }
    
	protected int addLeftStringToImage(int startHeight, int width,
    		Graphics2D g2d, String string) {
    	FontMetrics fm = g2d.getFontMetrics();
        int x = 0 + 8;
        int textHeight = fm.getHeight();
        int y = textHeight + startHeight;
        g2d.drawString(string, x, y);
        return textHeight;
    }
    
	protected int addRightStringToImage(int startHeight, int width,
    		Graphics2D g2d, String string) {
    	FontMetrics fm = g2d.getFontMetrics();
    	int stringWidth = fm.stringWidth(string);
        int x = width - stringWidth - 8;
        int textHeight = fm.getHeight();
        int y = textHeight + startHeight;
        g2d.drawString(string, x, y);
        return textHeight;
    }
	
	protected BufferedImage cropImage(BufferedImage img, int startX, int startY,
			int cropWidth, int cropHeight, int bottomBuffer) {
		BufferedImage cropped = null;
		Dimension size = new Dimension(cropWidth, cropHeight + bottomBuffer);
		Rectangle clip = createClip(img, size, startX, startY);
		int w = clip.width;
		int h = clip.height;
		 
		System.out.println("Crop Width " + w);
		System.out.println("Crop Height " + h);
		System.out.println("Crop Location " + "(" + clip.x + "," + clip.y
				+ ")");
		 
		cropped = img.getSubimage(clip.x, clip.y, w, h);
		 
		System.out.println("Image Cropped. New Image Dimension: "
				+ cropped.getWidth() + "w X " + cropped.getHeight() + "h");
		return cropped;
	}
	
	/**
	* This method crops an original image to the crop parameters provided.
	*
	* If the crop rectangle lies outside the rectangle (even if partially),
	* adjusts the rectangle to be included within the image area.
	*
	* @param img = Original Image To Be Cropped
	* @param size = Crop area rectangle
	* @param clipX = Starting X-position of crop area rectangle
	* @param clipY = Strating Y-position of crop area rectangle
	* @throws Exception
	*/
	private Rectangle createClip(BufferedImage img, Dimension size, int clipX,
			int clipY) {
		/**
		* Sometimes clip area might lie outside the original image,
		* fully or partially. In such cases, this program will adjust
		* the crop area to fit within the original image.
		*
		* isClipAreaAdjusted flas is usded to denote if there was any
		* adjustment made.
		*/
		boolean isClipAreaAdjusted = false;
		 
		/**Checking for negative X Co-ordinate**/
		if (clipX < 0) {
			clipX = 0;
			isClipAreaAdjusted = true;
		}
		/**Checking for negative Y Co-ordinate**/
		if (clipY < 0) {
			clipY = 0;
			isClipAreaAdjusted = true;
		}
		Rectangle clip;
		/**Checking if the clip area lies outside the rectangle**/
		if ((size.width + clipX) <= img.getWidth() &&
				(size.height + clipY) <= img.getHeight()) {
			/**
			* Setting up a clip rectangle when clip area
			* lies within the image.
			*/
			clip = new Rectangle(size);
			clip.x = clipX;
			clip.y = clipY;
		}
		else {
			/**
			* Checking if the width of the clip area lies outside the image.
			* If so, making the image width boundary as the clip width.
			*/
			if ((size.width + clipX) > img.getWidth())
			size.width = img.getWidth() - clipX;
			 
			/**
			* Checking if the height of the clip area lies outside the image.
			* If so, making the image height boundary as the clip height.
			*/
			if ((size.height + clipY) > img.getHeight())
			size.height = img.getHeight() - clipY;
			 
			/**Setting up the clip are based on our clip area size adjustment**/
			clip = new Rectangle(size);
			clip.x = clipX;
			clip.y = clipY;
			 
			isClipAreaAdjusted = true;
		}
		if (isClipAreaAdjusted)
			System.out.println("Crop Area Lied Outside The Image."
			+ " Adjusted The Clip Rectangle\n");
		return clip;
	}
}
