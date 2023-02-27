package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static final int STRING_NUM = 37;

    public static void main(String[] args) {
        GuitarString[] strings = new GuitarString[STRING_NUM];
        for (int i = 0; i < STRING_NUM; i++) {
            strings[i] = new GuitarString(440 * Math.pow(2, (i - 24) / 12.0));
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int stringIndex = KEYBOARD.indexOf(key);
                if (stringIndex < 0) {
                    continue;
                }
                strings[stringIndex].pluck();
            }

            double sample = 0.0;
            for (GuitarString string : strings) {
                sample += string.sample();
                string.tic();
            }
            StdAudio.play(sample);
        }
    }
}
