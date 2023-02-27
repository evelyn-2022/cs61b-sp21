package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static final int stringNum = 37;

    public static void main(String[] args) {
        GuitarString[] strings = new GuitarString[stringNum];
        for (int i = 0; i < stringNum; i++) {
            strings[i] = new GuitarString(440 * Math.pow(2, (i - 24) / 12.0));
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int stringIndex = keyboard.indexOf(key);
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
