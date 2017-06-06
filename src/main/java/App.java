import java.util.Arrays;
import java.util.stream.IntStream;
import com.google.common.collect.Range;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.primitives.Ints;

public class App {
    public static void main(String[] args) {
        final int[] input = new int[]{ 1, 0, 1, 0, 0, 0, 1, 1, 0, 1 };

        System.out.println(Arrays.toString(crc_with_padding(input)));
        System.out.println(Arrays.toString(crc_without_padding(input)));
    }

    private static ContiguousSet<Integer> range(int to) { return range(0, to); }
    private static ContiguousSet<Integer> range(int from, int to) {
        return ContiguousSet.create(Range.closedOpen(from, to), DiscreteDomain.integers());
    }

    static int[] crc_with_padding(int[] input) {
        final int[] divisor = new int[] { 1, 1, 0, 1, 0, 1 };
        final int[] dividend = Ints.concat(input, new int[divisor.length-1]);

        for (int i : range(input.length)) {
            if (dividend[i] != 1) { continue; }
            for (int j : range(divisor.length)) {
                dividend[i+j] = divisor[j] ^ dividend[i+j];
            }
        }

        return Arrays.copyOfRange(dividend, dividend.length-divisor.length + 1, dividend.length);
    }

    static int[] crc_without_padding(int[] input) {
        final int[] divisor = new int[] { 1, 1, 0, 1, 0, 1 };
        final int[] register = new int[divisor.length - 1];

        for (int i : range(input.length)) {
            final int temp = register[0] ^ input[i];
            for (int j : range(register.length)) {
                register[j] = j >= register.length - 1 ? temp :
                    divisor[j+1] == 1 ? register[j+1] ^ temp :
                    register[j+1];
            }
        }

        return register;
    }
}
