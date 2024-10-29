package PaddyLibrary.world;

import java.util.Random;

public class NoiseGenerator {
    private final int[] permutation;

    public NoiseGenerator(long seed) {
        permutation = new int[512];
        Random rand = new Random(seed);

        for (int i = 0; i < 256; i++) {
            permutation[i] = i;
        }

        // Array shuffle for randomness
        for (int i = 255; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = permutation[i];
            permutation[i] = permutation[j];
            permutation[j] = temp;
        }

        for (int i = 0; i < 256; i++) {
            permutation[256 + i] = permutation[i];
        }
    }

    public float perlin(float x, float y) {
        int X = (int) Math.floor(x) & 255;
        int Y = (int) Math.floor(y) & 255;
        x -= Math.floor(x);
        y -= Math.floor(y);
        float u = fade(x);
        float v = fade(y);
        int A = permutation[X] + Y;
        int B = permutation[X + 1] + Y;

        return lerp(v, lerp(u, grad(permutation[A], x, y),
                        grad(permutation[B], x - 1, y)),
                lerp(u, grad(permutation[A + 1], x, y - 1),
                        grad(permutation[B + 1], x - 1, y - 1)));
    }

    private float fade(float t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    private float lerp(float t, float a, float b) {
        return a + t * (b - a);
    }

    private float grad(int hash, float x, float y) {
        int h = hash & 15;
        float u = h < 8 ? x : y;
        float v = h < 4 ? y : h == 12 || h == 14 ? x : 0;
        return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
    }
}
