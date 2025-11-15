import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

public class ImageCache {
    private static final ImageCache instance = new ImageCache();
    private final Map<String, Image> imageCache = new HashMap<>();

    private ImageCache() {
    }

    public static ImageCache getInstance() {
        return instance;
    }

    public Image getImage(String resourcePath) {
        if (!imageCache.containsKey(resourcePath)) {
            Image image = loadImage(resourcePath);
            if (image != null) {
                imageCache.put(resourcePath, image);
            }
        }
        return imageCache.get(resourcePath);
    }

    private Image loadImage(String resourcePath) {
        try {
            java.net.URL imgURL = ImageCache.class.getResource(resourcePath);
            if (imgURL != null) {
                ImageIcon ii = new ImageIcon(imgURL);
                return ii.getImage();
            } else {
                System.err.println("Warning: Could not find resource: " + resourcePath);
                return createPlaceholderImage(12, 12);
            }
        } catch (Exception e) {
            System.err.println("Error loading image: " + resourcePath);
            e.printStackTrace();
            return createPlaceholderImage(12, 12);
        }
    }

    private Image createPlaceholderImage(int width, int height) {
        java.awt.image.BufferedImage bi = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_RGB);
        return bi;
    }

    // COMMENT: Flyweight pattern - shares Image objects across sprites
}
