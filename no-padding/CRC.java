import java.util.Arrays;
import java.util.stream.IntStream;

public class CRC {
    public static void main(String args[]) {
        System.out.println(Arrays.toString(CRC_Checker(new int[]{1, 0, 1, 0, 0, 0, 1, 1, 0, 1})));
    }

    static int[] CRC_Checker(int[] input) {
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
