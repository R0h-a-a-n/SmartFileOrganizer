import java.io.File;
import java.nio.file.*;
import java.util.*;

public class FileCategorizer {
    private static final Map<String, String> extensionMap = new HashMap<>();

    static {
        extensionMap.put("jpg", "Images");
        extensionMap.put("jpeg", "Images");
        extensionMap.put("png", "Images");
        extensionMap.put("gif", "Images");

        extensionMap.put("pdf", "Documents");
        extensionMap.put("doc", "Documents");
        extensionMap.put("docx", "Documents");
        extensionMap.put("txt", "Documents");

        extensionMap.put("mp4", "Videos");
        extensionMap.put("mkv", "Videos");
        extensionMap.put("mov", "Videos");

        extensionMap.put("zip", "Archives");
        extensionMap.put("rar", "Archives");
        extensionMap.put("7z", "Archives");

        extensionMap.put("mp3", "Audio");
        extensionMap.put("wav", "Audio");

        extensionMap.put("java", "Code");
        extensionMap.put("cpp", "Code");
        extensionMap.put("py", "Code");
        extensionMap.put("js", "Code");
        extensionMap.put("html", "Code");
    }

    public static String getCategory(String fileName) {
        String ext = getFileExtension(fileName);
        return extensionMap.getOrDefault(ext.toLowerCase(), "Others");
    }

    private static String getFileExtension(String name) {
        int lastDot = name.lastIndexOf('.');
        return (lastDot == -1) ? "" : name.substring(lastDot + 1);
    }

    public static void organizeFiles(String sourcePath, String destBasePath) {
        File folder = new File(sourcePath);
        File[] files = folder.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isFile()) {
                String category = getCategory(file.getName());
                File destDir = new File(destBasePath +"/"+category);
                destDir.mkdirs();
                try {
                    Files.move(file.toPath(),
                            Paths.get(destDir.getAbsolutePath(), file.getName()),
                            StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception e) {
                    System.err.println("Error moving file " + file.getName() + ": " + e.getMessage());
                }
            }
        }
    }
}
