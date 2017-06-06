import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by maisie on 17. 5. 31.
 */
public class CRC {
	static int[] CRC_Checker(int[] input){
		int[] divisor = new int[]{1, 1, 0, 1, 0, 1};
		int[] register = new int[divisor.length - 1];

		IntStream.range(0, input.length).forEachOrdered(i -> {
			int temp = register[0] ^ input[i];
			IntStream.range(0, register.length).forEachOrdered(j -> {
				if(j < register.length-1){
					if(divisor[j+1] == 1){
						register[j] = register[j+1] ^ temp;
					}else {
						register[j] = register[j+1];
					}
				}else{
					register[j] = temp;
				}
			});
			System.out.print("Loop[" + i + "] [ ");
			for(int k = 0; k < register.length; k++){
				System.out.print(register[k] + " ");
			}
			System.out.print("]\n");
		});
		return IntStream.concat(Arrays.stream(input), Arrays.stream(register)).toArray();
	}

	public static void main(String args[]) {
		System.out.println(Arrays.toString(CRC_Checker(new int[]{1, 0, 1, 0, 0, 0, 1, 1, 0, 1})));
	}
}
