package homeworks_java_modul.task1;

import java.util.List;
import java.util.stream.Collectors;

public class Task1 {
  public static List<Integer> multiplier(List<Integer> listOfNumbers) {
    return (listOfNumbers.stream().map(a -> a % 2 == 0 ? a * 2 : a * 3).collect(
        Collectors.toList()));
  }
}
