import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;

// DESIGN PATTERN: Flyweight (Structural)
// The ImageCache stores shared Image objects to reduce memory usage.
// Instead of loading a new Image for every object, we reuse existing ones.
public class ImageCache {
    private static Map<String, Image> imageMap = new HashMap<>();

    public static Image getImage(String path) {
        if (!imageMap.containsKey(path)) {
            try {
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
        return imageMap.get(path);
    }
}
