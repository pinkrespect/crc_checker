import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class CRC {
    static int[] CRC_Checker(int[] input) {
        int[] divisor = new int[]{1, 1, 0, 1, 0, 1};
        int[] result = new int[input.length + divisor.length - 1];
        ArrayList dividend = new ArrayList();

        for(int i = 0; i < result.length; i++){
            if(i < input.length)
                dividend.add(input[i]);
            else
                dividend.add(0);
        }

        IntStream.range(0, result.length).forEachOrdered(i -> {
            if((int)dividend.get(i) == 1){
                IntStream.range(0, divisor.length).forEachOrdered(j -> {
                    dividend.set(i+j, divisor[j] ^ (int)dividend.get(i+j));
                });
            }
        });

        for(int i = 0; i < dividend.size(); i++){
            if(i < input.length)
                result[i] = input[i];
            else
                result[i] = (int)dividend.get(i);
        }

        return result;
    }

    public static void main(String args[]) {
        System.out.println(Arrays.toString(CRC_Checker(new int[]{1, 0, 1, 0, 0, 0, 1, 1, 0, 1})));
    }
}
