package jake.utils;

import java.util.Collection;

public final class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * @param in            The object to get a string representation of
     * @param desiredLength How long the string should be
     * @param paddingChar   What to pad with
     * @return If Object.toString() is less than desiredLength characters, paddingChar will be added to the start to
     * make it long enough
     */
    public static String padLeft(Object in, int desiredLength, char paddingChar) {
        String str = in + "";
        final int paddingSize = desiredLength - str.length();
        for (int i = 0; i < paddingSize; i++) {
            str = paddingChar + str;
        }
        return str;
    }

    /**
     * @param in            The object to get a string representation of
     * @param desiredLength How long the string should be
     * @param paddingChar   What to pad with
     * @return If Object.toString() is less than desiredLength characters, paddingChar will be added to the end to make
     * it long enough
     */
    public static String padRight(Object in, int desiredLength, char paddingChar) {
        String str = in + "";
        final int paddingSize = desiredLength - str.length();
        for (int i = 0; i < paddingSize; i++) {
            str += paddingChar;
        }
        return str;
    }

    /**
     * @param in The string representations of which will be joined
     * @param separator The string representation of which will be used to separate in
     * @return The contents of in joined together with separator between
     */
    public static String join(Collection<? extends Object> in, Object separator) {
        return join(in.toArray(), separator);
    }

    /**
     * @param in The string representations of which will be joined
     * @param separator The string representation of which will be used to separate in
     * @return The contents of in joined together with separator between
     */
    public static String join(Object[] in, Object separator) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < in.length; i++) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(in[i]);
        }
        return sb.toString();
    }
}
