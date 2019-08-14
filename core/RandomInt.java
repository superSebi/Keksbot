package core;

import java.util.Random;

public class RandomInt {

    public static int random (int max) {
        return new Random().nextInt(max);
    }
}