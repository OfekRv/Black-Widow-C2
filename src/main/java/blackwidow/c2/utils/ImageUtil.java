package blackwidow.c2.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtil {
    public static Point getPixelCoordinates(int pixelIndex, BufferedImage img) {
        return new Point(pixelIndex % img.getWidth(), pixelIndex / img.getWidth());
    }
}
