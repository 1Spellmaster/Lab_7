import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.IntPredicate;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        // число просте, якщо воно > 1 і не ділиться ні на що від 2 до кореня з нього
        IntPredicate isPrime = number -> number > 1
                && IntStream.rangeClosed(2, (int) Math.sqrt(number))
                        .noneMatch(divisor -> number % divisor == 0);

        // кількість одиниць у двійковій формі числа
        ToIntFunction<Integer> countOnes = number -> Integer.bitCount(number);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть n: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Помилка: потрібно ввести ціле число.");
            scanner.close();
            return;
        }

        int n = scanner.nextInt();

        Optional<Integer> result = IntStream.rangeClosed(2, n)
                .filter(isPrime)
                .boxed()
                .max(Comparator.comparingInt(countOnes));

        if (result.isEmpty()) {
            System.out.println("Простих чисел, які не перевищують " + n + ", немає.");
        } else {
            int prime = result.get();
            System.out.println("Просте число: " + prime);
            System.out.println("Двійкова форма: " + Integer.toBinaryString(prime));
            System.out.println("Кількість одиниць: " + countOnes.applyAsInt(prime));
        }

        scanner.close();
    }
}
