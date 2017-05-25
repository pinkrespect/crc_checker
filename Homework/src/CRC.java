/**
 * Created by maisie on 17. 5. 25.
 */

// Divisor: X^5 + X^4 + X^2 + 1 = 110101
// Dividend: x^9 + x^7 + x^3 + x^2 + 1 = 1010001101

class Encoder{
    public int[] divisor; // Fixed-number array
    private int[] dividend; // Flexible-number array - It changes as the calculation progresses.
    private int[] remainder;
    private int[] codeword;

    public Encoder(int array[]){
        setDivisor();
        setDividend(array);
        setRemainder();
    }

    private void setDivisor(){ // Fix Divisor's data & size.
        divisor = new int[6];
        for(int i = 0; i < divisor.length; i++)
            if((i == 0)||(i == 1)||(i == 3)||(i == 5))
                divisor[i] = 1;
    }

    private void setDividend(int array[]){ // Fix Dividend's size.
        dividend = new int[array.length + divisor.length - 1];
        for(int i = 0 ; i < array.length; i++) {
            dividend[i] = array[i];
        }
    }

    private void setRemainder(){
        // Reminder = n - 1, n = divisor's length.
        remainder = new int[divisor.length - 1];
    }

    private void setCodeword(int[] array, int[] remainder){ // Codeword
        codeword = new int[dividend.length];
        for(int i = 0; i < array.length;i++){
            codeword[i] = array[i];
        }
        for(int i = 0; i < remainder.length; i++){
            codeword[i+array.length] = remainder[i];
        }
    }

    private int XOR(int x, int y){
        // It's just XOR calculator.
        if(x == y){
            return 0;
        }else{
            return 1;
        }
    }

    public int[] calculate(int[] array){
        for(int i = 0; i + divisor.length -1 < dividend.length; i++) {
            // When the end of divisor array touches the end of dividend array, the loop ends.
            int j = 0; // This integer variables shared by two loops.
            if (dividend[i] == 0) {
                // Dividend starts 0 >> Uses 0 divisor.
                for (; j < divisor.length; j++) {
                        dividend[i + j] = XOR(0, dividend[i + j]);
                }
            } else {
                // Dividend starts 1 >> Uses fixed divisor.
                for (; j < divisor.length; j++) { //
                    dividend[i + j] = XOR(divisor[j], dividend[i + j]);
                }
            }
        }

        for(int i = 0; i < remainder.length; i++){
            // Now remainder array gets remainder from dividend.
            remainder[i] = dividend[i + dividend.length - remainder.length];
        }

        setCodeword(array, remainder);
        return codeword;
    }
}

public class CRC {
    public static void main(String args[]){
        int[] array = {1, 0, 1, 0, 0, 0, 1, 1, 0, 1};
        Encoder encoder = new Encoder(array);
        int remainder[] = encoder.calculate(array);

        System.out.print("The data: ");
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }

        System.out.println();

        System.out.print("The divisor: ");
        for(int i = 0; i < encoder.divisor.length; i++){
            System.out.print(encoder.divisor[i] + " ");
        }

        System.out.println();

        System.out.print("The remainder: ");
        for(int i = 0; i < remainder.length; i++){
            System.out.print(remainder[i] + " ");
            if (i == 9){
                System.out.print(" ");
            }
        }
    }
}
