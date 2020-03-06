package homeworks_java_modul.task2;

public class KeyValueMatchException extends Exception {

  private Integer key;

  public Integer getKey() {
    return key;
  }

  public KeyValueMatchException(String message) {
    super(message);
  }
}
