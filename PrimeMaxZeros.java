import java.util.Optional;
import java.util.Scanner;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class PrimeMaxZeros {

    //чи є число простим
    private static final IntPredicate IS_PRIME = x -> x >= 2
            && IntStream.rangeClosed(2, (int) Math.sqrt(x))
                        .noneMatch(d -> x % d == 0);

    //кількість нулів у двійковому записі числа
    private static final IntUnaryOperator ZEROS = x ->
            Integer.toBinaryString(x).replace("1", "").length();

    // просте число <= n з максимальною кількістю нулів (при рівності - найменше)
    public static Optional<Integer> findPrime(int n) {
        return IntStream.rangeClosed(2, n)
                .filter(IS_PRIME)
                .boxed()
                .reduce((a, b) -> ZEROS.applyAsInt(b) > ZEROS.applyAsInt(a) ? b : a);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = sc.nextInt();

        Optional<Integer> result = findPrime(n);
        if (result.isPresent()) {
            int p = result.get();
            System.out.println("Prime: " + p);
            System.out.println("Binary: " + Integer.toBinaryString(p));
            System.out.println("Zeros: " + ZEROS.applyAsInt(p));
        } else {
            System.out.println("No primes <= " + n);
        }
        sc.close();
    }
}