package com.jeffthefate;

import junit.framework.TestCase;

import java.io.File;

/**
 * Created by Jeff on 6/17/2014.
 */
public class SetlistScreenshotTest extends TestCase {

    public void testCreateScreenshot() {
        SetlistScreenshot setlistScreenshot = new SetlistScreenshot(
                new File("src/test/resources/setlist.jpg").getAbsolutePath(),
                new File("src/test/resources/roboto.ttf").getAbsolutePath(),
                "Test", 40, 200);
        System.out.println(setlistScreenshot.getOutputFilename());
    }

}
