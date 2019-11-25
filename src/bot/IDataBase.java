package bot;

public interface IDataBase {
    static void connect() { }
    static void createDB() { }
    void put(int id, Student student);
    Student get(int id);
    private void deleteKey(int id) {}
    boolean containsKey(int id);
}
