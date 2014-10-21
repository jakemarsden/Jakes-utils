package jake.utils;

public class Random extends java.util.Random {
    private static final char[] ALPHANUMERIC_CHARACTERS, LETTER_CHARACTERS;
    static {
        final StringBuilder sb = new StringBuilder(26 + 10);
        for (char c = 'a'; c <= 'z'; c++) {
            sb.append(c);
        }
        LETTER_CHARACTERS = sb.toString().toCharArray();

        for (char c = '0'; c <= '9'; c++) {
            sb.append(c);
        }
        ALPHANUMERIC_CHARACTERS = sb.toString().toCharArray();
    }


    public Random() {
    }

    public Random(long seed) {
        super(seed);
    }

    /**
     * @param min The lowest possible integer to be returned
     * @param max One division larger than the highest possible integer to be returned
     * @return A random integer in the half-open range [min,max)
     */
    public int nextInt(int min, int max) {
        return nextInt(max - min) + min;
    }

    /**
     *
     * @param min The lowest possible float to be returned
     * @param max One division larger than the highest possible float to be returned
     * @return A random float in the half-open range [min,max)
     */
    public float nextFloat(float min, float max) {
        return (max - min) * nextFloat() + min;
    }

    /**
     * @param min The lowest possible double to be returned
     * @param max One division larger than the highest possible double to be returned
     * @return A random double in the half-open range [min,max)
     */
    public double nextDouble(double min, double max) {
        return (max - min) * nextDouble() + min;
    }

    /**
     * @param min The lowest possible long to be returned
     * @param max One division larger than the highest possible long to be returned
     * @return A random long in the half-open range [min,max)
     */
    public long nextLong(long min, long max) {
        return (max - min) * nextLong() + min;
    }

    /**
     * @param length How long the string should be
     * @param alphanumeric If the result is allowed to contain numbers
     * @return A random combination of length number of letters (and possibly numbers)
     */
    public String nextString(int length, boolean alphanumeric) {
        if (length <= 0) {
            throw new IllegalArgumentException("length is " + length);
        }
        final char[] symbols = (alphanumeric) ? ALPHANUMERIC_CHARACTERS : LETTER_CHARACTERS;
        final char[] string = new char[length];
        for (int i = 0; i < length; i++) {
            string[i] = symbols[nextInt(0, symbols.length)];
        }
        return new String(string);
    }
}
