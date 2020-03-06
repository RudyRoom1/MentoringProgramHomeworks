import homeworks_java_modul.task1.Task1;
import homeworks_java_modul.task2.KeyValueMatchException;
import homeworks_java_modul.task2.PropertyReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

  public static void main(String[] args) throws IOException {
    firstTask();
    secondTask();
  }

  public static void firstTask() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 7, 8, 9);
    System.out.println(Task1.multiplier(numbers));
  }

  public static void secondTask() throws IOException {
    PropertyReader propertyReader = new PropertyReader();
    Map<String, Integer> swapped = new HashMap<>();

    try {
      swapped = propertyReader.swapKeyValue(propertyReader.readAllProperties());
    } catch (KeyValueMatchException | IOException ex) {
      System.out.println(ex.getMessage());
    }
    System.out.println(swapped);

    Map<String, Integer> sorted = propertyReader.sortAlphabetically(swapped);
    propertyReader.writeAllProperties(sorted);
    System.out.println(sorted);
  }
}
