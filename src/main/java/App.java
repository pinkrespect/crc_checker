import java.util.Arrays;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) {
        final int[] input = new int[]{1, 0, 1, 0, 0, 0, 1, 1, 0, 1};

        System.out.println(Arrays.toString(crc_with_padding(input)));
        System.out.println(Arrays.toString(crc_without_padding(input)));
    }

    static int[] crc_with_padding(int[] input) {
        final int[] divisor = new int[] { 1, 1, 0, 1, 0, 1 };
        final int[] dividend = IntStream.concat(
                Arrays.stream(input),
                IntStream.generate(() -> 0).limit(divisor.length-1)
        ).toArray();

        IntStream.range(0, input.length).forEachOrdered(i -> {
            if (dividend[i] != 1) { return; }
            IntStream.range(0, divisor.length).forEachOrdered(j -> {
                dividend[i+j] = divisor[j] ^ dividend[i+j];
            });
        });

        return IntStream.concat(
                Arrays.stream(input),
                Arrays.stream(dividend, dividend.length-divisor.length + 1, dividend.length)
        ).toArray();
    }

    static int[] crc_without_padding(int[] input) {
        final int[] divisor = new int[] { 1, 1, 0, 1, 0, 1 };
        final int[] register = new int[divisor.length - 1];

        IntStream.range(0, input.length).forEachOrdered(i -> {
            final int temp = register[0] ^ input[i];
            IntStream.range(0, register.length).forEachOrdered(j -> {
                register[j] = j >= register.length - 1 ? temp :
                    divisor[j+1] == 1 ? register[j+1] ^ temp :
                    register[j+1];
            });
        });

        return IntStream.concat(Arrays.stream(input), Arrays.stream(register)).toArray();
    }
}
