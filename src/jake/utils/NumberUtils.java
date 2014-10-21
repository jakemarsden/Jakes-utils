package jake.utils;

/**
 * Created by OEM on 29/07/2014.
 */
public final class NumberUtils {
    private NumberUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * @param value The target number
     * @return The hexadecimal representation of the given number in the form "-0x1234567"
     */
    public static String toHexString(long value) {
        final String result = Long.toHexString(Math.abs(value));
        return (value < 0) ? ("-0x" + result) : ("0x" + result);
    }

    /**
     * @param value The target hexadecimal string in the form "-0x1234567"
     * @return The number representation of the given hexadecimal string
     */
    public static long fromHexString(String value) {
        return Long.decode(value);
    }

    /**
     * @param digits The digits to be concatenated
     * @return  The digits will be appended to each other, e.g. {1,0} will give 10
     */
    public static int concatenateDigits(int... digits) {
        final StringBuilder sb = new StringBuilder(digits.length);
        for (int digit : digits) {
            sb.append(digit);
        }
        return Integer.parseInt(sb.toString());
    }
}
