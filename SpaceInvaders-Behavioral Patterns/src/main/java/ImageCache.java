import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;

// Flyweight pattern - reuses shared image instances to save memory
public class ImageCache {
    private static Map<String, Image> imageMap = new HashMap<>();

    // Get image from cache or load if not cached
    public static Image getImage(String path) {
        if (!imageMap.containsKey(path)) {
            try {
                // Load image from resources
                URL url = ImageCache.class.getResource(path);
                if (url != null) {
                    ImageIcon ii = new ImageIcon(url);
                    imageMap.put(path, ii.getImage());
                } else {
                    System.err.println("Could not find image: " + path);
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        // Return cached image instance
        return imageMap.get(path);
    }
}
