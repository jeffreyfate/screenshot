package com.jeffthefate;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Base screenshot class that needs to be implemented to specify a type.
 * </p>
 * Contains functions that all types of screenshots want and need to use.
 */
public abstract class Screenshot {

    /**
     * Space between each line of text added, in pixels
     */
	final int TEXT_HEIGHT_OFFSET = -2;
    /**
     * Format to use to post the date stamp on Twitter screenshot images
     */
	final String TWEET_DATE_FORMAT = "yyyy-MM-dd";

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
    	int totalTextHeight = numLines * (g2d.getFontMetrics().getHeight() +
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
        int textHeight = fm.getHeight();
        int y = textHeight + startHeight;
        g2d.drawString(string, startPos, y);
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

    public Graphics2D getGraphics() {
        return g2d;
    }

    public void setGraphics(Graphics2D g2d) {
        this.g2d = g2d;
    }

    private Graphics2D g2d;

    BufferedImage setupImage() {
        FileInputStream fileInput;
        try {
            fileInput = new FileInputStream(new File(getTemplateFile()));
        } catch (FileNotFoundException e) {
            logger.error("Unable to read template file!");
            e.printStackTrace();
            return null;
        }
        try {
            return ImageIO.read(fileInput);
        } catch (IOException e) {
            logger.error("Unable to read template file!");
            e.printStackTrace();
            return null;
        }
    }

    void setupGraphics(BufferedImage img) {
        Graphics2D g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        //g2d.drawImage(img, 0, 0, null);
        g2d.setPaint(Color.white);
        setGraphics(g2d);
    }

    void setupFontMetrics(int fontSize, int height, int stringListSize) {
        Font font;
        do {
            logger.info("Font size: " + fontSize);
            font = new Font("Serif", Font.BOLD, --fontSize);
            try {
                font = Font.createFont(Font.TRUETYPE_FONT, new File(
                        getFontFile()));
                font = font.deriveFont((float) fontSize).deriveFont(
                        Font.BOLD);
            } catch (IOException e) {
                logger.error("Unable to read font file!");
                e.printStackTrace();
            } catch (FontFormatException e) {
                logger.error("Couldn't create font from " + getFontFile());
                e.printStackTrace();
            }
            getGraphics().setFont(font);
        } while (!willTextFit(height, getGraphics(), stringListSize));
        logger.info(font.getFontName());
        logger.info(font.getFamily());
        logger.info("size: " + font.getSize());
        logger.info("bold: " + font.isBold());
    }

    int drawStringsToImage(List<String> stringList, int width,
            boolean isCentered) {
        int currentHeight = getVerticalOffset();
        for (String line : stringList) {
            if (isCentered) {
                currentHeight += (addCenteredStringToImage(currentHeight, width,
                        getGraphics(), line) + TEXT_HEIGHT_OFFSET);
            }
            else {
                currentHeight += (addStringToImage(currentHeight, width,
                        getGraphics(), line) + TEXT_HEIGHT_OFFSET);
            }
        }
        return currentHeight;
    }

    String tearDown(BufferedImage img, int width, int currentHeight,
            String name, boolean crop) {
        getGraphics().dispose();
        if (crop) {
            img = cropImage(img, 0, 0, width, currentHeight,
                    getVerticalOffset());
        }
        String filename = "";
        try {
            filename += name;
            filename += System.currentTimeMillis();
            filename += ".jpg";
            File file = new File(filename);
            ImageIO.write(img, "jpg", file);
        } catch (IOException e) {
            logger.error("Unable to write image to file " + filename);
            e.printStackTrace();
        }
        return filename;
    }

    void addTimestamp(int dateFontSize, int currentHeight, int width) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                TWEET_DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
        Date date = new Date();
        String dateString = dateFormat.format(date);
        Font font = new Font("Serif", Font.BOLD, dateFontSize);
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,
                    new File(getFontFile()));
            font = font.deriveFont((float) dateFontSize).deriveFont(
                    Font.BOLD);
        } catch (Exception e) {
            logger.error("Unable to create font from " + getFontFile());
            e.printStackTrace();
        }
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        addStringToImage(currentHeight+(getVerticalOffset()/2),
                width-fm.stringWidth(dateString)-(width/16), g2d, dateString);
    }

}