import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CRC {
    static int[] CRC_Checker(int[] input) {
        int[] divisor = new int[]{1, 1, 0, 1, 0, 1};
        int[] result = new int[input.length + divisor.length - 1];
        List dividend = new ArrayList();
        for(int i = 0; i < result.length; i++){
            if(i < input.length)
                dividend.add(input[i]);
            else
                dividend.add(0);
        }

        for (int i = 0; i < dividend.size() - divisor.length; i++) {
            if ((int)dividend.get(i) == 0) {
                continue;
            }
            for (int j = 0; j < divisor.length; j++) {
                dividend.set(i + j, divisor[j] ^ (int)dividend.get(i + j));
            }
        }

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
