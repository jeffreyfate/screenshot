package com.jeffthefate;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map.Entry;
import java.util.TreeMap;

public class TriviaScreenshot extends Screenshot {

    private final int DATE_FONT_SIZE = 12;
    private final String TITLE_TEXT = "Top Scores";
    private final int SCORES_LIMIT = 10;

    public int getDateFontSize() {
        return dateFontSize;
    }

    public void setDateFontSize(int dateFontSize) {
        this.dateFontSize = dateFontSize;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public int getScoresLimit() {
        return scoresLimit;
    }

    public void setScoresLimit(int scoresLimit) {
        this.scoresLimit = scoresLimit;
    }

    private int dateFontSize = DATE_FONT_SIZE;
    private String titleText = TITLE_TEXT;
    private int scoresLimit = SCORES_LIMIT;

    private TreeMap<String, Integer> sortedMap;
	
	public TriviaScreenshot(String templateFile, String fontFile,
            String titleText, TreeMap<String, Integer> sortedMap,
            int mainFontSize, int dateFontSize, int scoresLimit,
            int verticalOffset, String filename) {
		super(templateFile, fontFile, verticalOffset, filename);
        if (titleText != null) {
            this.titleText = titleText;
        }
        if (dateFontSize >= 0) {
            this.dateFontSize = dateFontSize;
        }
        if (scoresLimit > 0) {
            this.scoresLimit = scoresLimit;
        }
		setOutputFilename(createScreenshot(sortedMap, mainFontSize));
	}

    private int addSpacedUserScores(TreeMap<String, Integer> sortedMap,
            Graphics2D g2d, int currentHeight, int width) {
        int position = 1;
        StringBuilder sb;
        FontMetrics fm = g2d.getFontMetrics();
        int stringWidth;
        for (Entry<String, Integer> player : sortedMap.entrySet()) {
            if (position > scoresLimit) {
                break;
            }
            sb = new StringBuilder();
            if (!sb.toString().isEmpty()) {
                sb.append("\n");
            }
            sb.append("#");
            if (position < 10) {
                sb.append(" ");
            }
            sb.append(position);
            addStringToImage(currentHeight, width/16, g2d, sb.toString());
            sb = new StringBuilder();
            sb.append("@");
            sb.append(player.getKey());
            int fontHeight = fm.getHeight();
            addStringToImage(currentHeight, fontHeight*3, g2d,
                    sb.toString());
            stringWidth = fm.stringWidth(
                    Integer.toString(player.getValue()));
            currentHeight += addStringToImage(currentHeight,
                    (width-stringWidth)-(width/16), g2d,
                    Integer.toString(player.getValue()));
            position++;
        }
        return currentHeight;
    }

    /**
     * Create screenshot with given title and list of scores.
     *
     * @param sortedMap     user scores
     * @param mainFontSize  text size for scores
     * @return              filename of the screenshot
     */
	String createScreenshot(TreeMap<String, Integer> sortedMap,
            int mainFontSize) {
        BufferedImage img = setupImage();
        int height = img.getHeight();
        int width = img.getWidth();
        setupGraphics(img);
        // TODO Fix this to be accurate
        setupFontMetrics(mainFontSize, height, scoresLimit+4);
        int currentHeight = getVerticalOffset();
        currentHeight += (addCenteredStringToImage(currentHeight, width,
                getGraphics(), titleText) + TEXT_HEIGHT_OFFSET);
        currentHeight += 40;
        currentHeight = addSpacedUserScores(sortedMap, getGraphics(),
                currentHeight, width);
        addTimestamp(dateFontSize, currentHeight, width);
        return tearDown(img, width, currentHeight, true);
    }

}
