import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 7, 8, 9);
    multiplier(numbers);
    System.out.println(multiplier(numbers));
  }

  public static List<Integer> multiplier(List<Integer> listOfNumbers) {
    return (listOfNumbers.stream().map(a -> a % 2 == 0 ? a * 2 : a * 3).collect(
        Collectors.toList()));
  }
}
