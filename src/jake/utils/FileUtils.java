package jake.utils;

import java.io.File;
import java.io.IOException;

public final class FileUtils {
    private FileUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * @param file The target file
     * @return The target file's name, which doesn't include an extension
     */
    public static String getName(File file) {
        return getName(file.getAbsolutePath());
    }

    /**
     * @param path The target file's path
     * @return The target file's name, which doesn't include an extension
     */
    public static String getName(String path) {
        int startIndex = Math.max(path.lastIndexOf('/'), path.lastIndexOf('\\')) + 1,
                endIndex = path.lastIndexOf('.');
        if (endIndex == -1 || endIndex < startIndex) {
            return path.substring(startIndex);
        } else {
            return path.substring(startIndex, endIndex);
        }
    }

    /**
     * @param file The target file
     * @return The target file's extension
     */
    public static String getExtension(File file) {
        return getExtension(file.getAbsolutePath());
    }

    /**
     * @param path The target file's path
     * @return The target file's extension
     */
    public static String getExtension(String path) {
        final int index = path.lastIndexOf('.');
        if (index != -1 && index > path.lastIndexOf('/') && index > path.lastIndexOf('\\')) {
            return path.substring(index + 1);
        } else {
            return "";
        }
    }

    /**
     * Creates the given file on the file system. The file's parent directories will also be created if they don't
     * exist.
     *
     * @param file The target file
     * @throws IOException If the file already exists as a directory, its parent directories couldn't be created, or if
     *                     the file system just didn't like it for some reason (e.g. invalid permissions).
     */
    public static void createFile(File file) throws IOException {
        if (file.isDirectory()) {
            throw new IOException("Unable to create file: it's already a directory! " + file);
        }
        if (!file.exists()) {
            final File parent = file.getParentFile();
            try {
                createDirectory(parent);
            } catch (IOException e) {
                throw new IOException("Unable to create file: can't create its parent " + file, e);
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new IOException("Unable to create file: " + file, e);
            }
        }
    }

    /**
     * Creates the given file on the file system as a directory. The file's parent directories will also be created if
     * they don't exist.
     *
     * @param file The target directory
     * @throws IOException If the file already exists as a file, its parent directories couldn't be created, or if
     *                     the file system just didn't like it for some reason (e.g. invalid permissions).
     */
    public static void createDirectory(File file) throws IOException {
        if (file.isFile()) {
            throw new IOException("Unable to create directory: it's already a file! " + file);
        }
        if (!file.exists() && !file.mkdirs()) {
            throw new IOException("Unable to create directory: " + file);
        }
    }

    /**
     * If a file is passed, it will be deleted. If a directory is passed, its contents will be recursively deleted and
     * then the (empty) directory will be deleted.
     *
     * @param file The target file or directory
     * @throws IOException If the given file (or any of its children) cannot be removed from the file system (e.g.
     *                     invalid permissions).
     */
    public static void delete(File file) throws IOException {
        if (file.isDirectory()) {
            final File[] children = file.listFiles();
            if (children == null) {
                throw new IOException("Unable to delete directory as its contents couldn't be accessed: " + file);
            }
            for (File child : children) {
                try {
                    delete(child);
                } catch (IOException e) {
                    throw new IOException("Unable to delete child file for directory: " + file, e);
                }
            }
        }
        if (file.exists()) {
            if (!file.delete()) {
                throw new IOException("Unable to delete " + (file.isFile() ? "file" : "directory") + ": " + file);
            }
        }
    }
}
