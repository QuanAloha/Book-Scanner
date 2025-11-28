package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Represents an image on disk plus simple helpers
 * to load the pixels and inspect basic metadata.
 */
public class ImageOpen {

    private final Path filePath;

    /**
     * Constructs an ImageOpen object with the given file path.
     *
     * @param filePath the path to the image file
     */
    public ImageOpen(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets the file path of the image.
     *
     * @return the file path
     */
    public Path getFilePath() {
        return filePath;
    }

    /**
     * Loads the image data into memory.
     *
     * @return BufferedImage for this file
     * @throws IOException if the file cannot be read as an image
     */
    public BufferedImage loadBufferedImage() throws IOException {
        return ImageIO.read(filePath.toFile());
    }

    /**
     * Returns the file name (e.g., "page_001.jpg").
     */
    public String getFileName() {
        return filePath.getFileName().toString();
    }

    /**
     * Returns the file size in bytes, or -1 if not available.
     */
    public long getFileSizeBytes() {
        try {
            return Files.size(filePath);
        } catch (IOException e) {
            return -1L;
        }
    }
}
