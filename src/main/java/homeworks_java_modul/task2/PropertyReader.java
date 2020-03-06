package homeworks_java_modul.task2;

import static java.util.Map.Entry.comparingByKey;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class PropertyReader {

  String exceptionMessage = "KeyValueMatchException!! \nThe key: %s equal to value: %s";

  public HashMap<Integer, String> readAllProperties() throws IOException {
    HashMap<Integer, String> mapOfProperties = new HashMap<>();
    Files.lines(Paths.get("src/main/resources/config.properties")).map(str -> str.split(":"))
        .forEach(value -> mapOfProperties.put(Integer.valueOf(value[0]), value[1]));
    return mapOfProperties;
  }

  public void writeAllProperties(Map<String, Integer> resultData) throws IOException {
    Path pathToResultFile = Paths.get("src/main/resources/result.properties");
    if (!Files.exists(pathToResultFile)) {
      Files.createFile(pathToResultFile);
    }
    List<String> outPutData = resultData.entrySet().stream().map(resultDataEntity -> String
        .format("%s:%d", resultDataEntity.getKey(), resultDataEntity.getValue()))
        .collect(Collectors.toList());

    Files.write(pathToResultFile, outPutData);
  }

  public Map<String, Integer> swapKeyValue(Map<Integer, String> mapToSwap)
      throws KeyValueMatchException {
    Map<String, Integer> swapped = new HashMap<>();

    for (Entry<Integer, String> entry : mapToSwap.entrySet()) {
      if ((entry.getValue().trim()).equals(String.valueOf(entry.getKey()).trim())) {
        throw new KeyValueMatchException(
            String.format(exceptionMessage, entry.getValue(), entry.getValue()));
      }
      swapped.put(entry.getValue(), entry.getKey());
    }
    return swapped;
  }

  public LinkedHashMap<String, Integer> sortAlphabetically(Map<String, Integer> mapToSort) {
    return mapToSort.entrySet().stream()
        .sorted(comparingByKey()).collect(Collectors
            .toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
  }
}

