/**
 * Author: Nathaniel Atwood
 * Date: November 28, 2025
 * Description: ImageSource class that provides images from a specified folder.
 * Copyright: © 2025 Nathaniel Atwood
 */

package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * Simple source of images from a single folder.
 * 
 * Responsibility:
 * - Given a folder path, find all image files in that folder (non-recursive for now).
 * - Provide them one-by-one as ImageOpen objects via nextImage().
 */
public class ImageSource {
    private Path sourcePath;
    private Path[] imageFiles;
    private int currentIndex = 0;

    /**
     * Constructs an ImageSource with the given path and image files.
     * 
     * @param sourcePath the path to the image source
     */
    public ImageSource(Path sourcePath) {
        // Initialize the source path
        this.sourcePath = sourcePath;

        Path[] files;
        try {
            files = Files.list(this.sourcePath)
                    .filter(Files::isRegularFile)
                    .filter(this::isImageFile)
                    .toArray(Path[]::new);
        } catch (IOException e) {
            // If the folder can't be read, treat it as empty for now
            files = new Path[0];
        }
        this.imageFiles = files;
    }

    /**
     * Simple extension check for now.
     * 
     * @param path the file path to check
     * @return true if the file is an image, false otherwise
     */
    private boolean isImageFile(Path path) {
        String name = path.getFileName().toString().toLowerCase();
        return name.endsWith(".jpg")
                || name.endsWith(".jpeg")
                || name.endsWith(".png")
                || name.endsWith(".tif")
                || name.endsWith(".tiff")
                || name.endsWith(".raw");
    }

    /**
     * Returns the next image for processing, or null if no more images.
     *
     * @return the next ImageOpen object, or null when exhausted
     */
    public ImageOpen nextImage() {
        if (!hasNext()) {
            return null;
        }
        Path imagePath = imageFiles[currentIndex];
        currentIndex++;

        // Adjust this constructor to whatever ImageOpen actually expects
        return new ImageOpen(imagePath);
    }

    /**
     * Checks if there are more images to process.
     *
     * @return true if there are more images, false otherwise
     */
    public boolean hasNext() {
        return currentIndex < imageFiles.length;
    }

    /**
     * Closes the image source and releases any resources.
     *
     * @return true if the source was closed successfully
     */
    public boolean close() {
        // Nothing to close right now, but you might add resources later.
        return true;
    }
}
