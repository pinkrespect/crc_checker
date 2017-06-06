import java.util.Arrays;
import java.util.stream.IntStream;

public class CRC {
    public static void main(String args[]) {
        System.out.println(Arrays.toString(CRC_Checker(new int[]{1, 0, 1, 0, 0, 0, 1, 1, 0, 1})));
    }

    static int[] CRC_Checker(int[] input) {
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
}
