@Repository
public class InMemoryMessageRepository implements MessageRepository {

  private Map<String, Message> messages = new HashMap<>();

  @Override
  public void save(Message message) {
    messages.put(generateKey(message.getKey1(), message.getKey2()), message);
  }

  @Override
  public String getValue(String key) {
    return messages.get(key) != null ? messages.get(key).getValue1() : null;
  }

  @PostConstruct
  public void init() throws IOException {
    // Load data from CSV file
    ClassLoader classLoader = this.getClass().getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream("messages.csv"); // Replace with your file path
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

    String line;
    while ((line = reader.readLine()) != null) {
      if (line.isBlank()) {
        continue; // Skip empty lines
      }
      String[] data = line.split(",");
      if (data.length != 4) {
        throw new IllegalArgumentException("Invalid CSV format. Expected 4 columns.");
      }

      Message message = new Message();
      message.setKey1(data[0]);
      message.setKey2(data[1]);
      message.setValue1(data[2]);
      message.setValue2(data[3]);

      save(message);
    }
    reader.close();
  }

  private String generateKey(String key1, String key2) {
    // You can define a custom key generation logic here
    return key1 + ":" + key2;
  }
}
