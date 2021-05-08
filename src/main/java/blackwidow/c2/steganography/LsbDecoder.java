package blackwidow.c2.steganography;

import javax.inject.Named;
import java.awt.*;
import java.awt.image.BufferedImage;

import static blackwidow.c2.utils.ImageUtil.getPixelCoordinates;

@Named
public class LsbDecoder implements Decoder<BufferedImage, String> {
    private static final String END_OF_MESSAGE = "\0";

    @Override
    public String decode(BufferedImage encoded) {
        StringBuilder decoded = new StringBuilder();
        byte buffered = 0;
        for (int pixelIndex = 0, bitIndex = 0;
             pixelIndex < encoded.getWidth() * encoded.getHeight() && !decoded.toString().contains(END_OF_MESSAGE);
             pixelIndex++) {
            Point coord = getPixelCoordinates(pixelIndex, encoded);
            int rgb = encoded.getRGB(coord.x, coord.y);
            for (int colorIndex = 0; colorIndex <= 2; colorIndex++) {
                buffered |= decodeLsbFromRgbColor(bitIndex, rgb, colorIndex);
                bitIndex++;
                if (bitIndex == 8) {
                    decoded.append((char) buffered);
                    buffered = 0;
                    bitIndex = 0;
                }
            }
        }

        return getStringWithoutEndSymbol(decoded);
    }

    private int decodeLsbFromRgbColor(int bitIndex, int rgb, int colorIndex) {
        return ((rgb >> 8 * colorIndex) & 1) << bitIndex;
    }

    private String getStringWithoutEndSymbol(StringBuilder decoded) {
        return decoded.substring(0, decoded.length() - END_OF_MESSAGE.length());
    }
}
