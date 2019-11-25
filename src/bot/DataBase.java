package bot;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase implements IDataBase {
    private static Connection conn;
    private static Statement statement;
    private static Path pathToFile;

    public DataBase(String filename) {
        pathToFile = Path.of(".", "resources", filename);
        connect();
        createDB();
    }

    private static void connect() {
        conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + pathToFile);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createDB() {
        try {
            statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS 'students' ('id' INT, 'name' TEXT, 'group' TEXT, 'subgroup' TEXT);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void put(int id, Student student) {
        if (this.containsKey(id))
            this.deleteKey(id);
        try {
            statement.execute(String.format("INSERT INTO 'students' ('id', 'name', 'group', 'subgroup') VALUES (%d, '%s', '%s', '%s');",
                    id, student.getName(), student.getGroup(), student.getSubgroup()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student get(int id) {
        try {
            ResultSet resSet = statement.executeQuery(String.format("SELECT * FROM students WHERE id = %d;", id));
            if (resSet.next())
            {
                String  name = resSet.getString("name");
                String  group = resSet.getString("group");
                String  subgroup = resSet.getString("subgroup");

                Student student = new Student(id);
                student.setAttr(Attr.Name, name);
                student.setAttr(Attr.Group, group);
                student.setAttr(Attr.Subgroup, subgroup);

                return  student;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void deleteKey(int id) {
        try {
            statement.execute(String.format("DELETE FROM students WHERE id = %d", id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean containsKey(int  id) {
        try {
            ResultSet resSet = statement.executeQuery(String.format("SELECT * FROM 'students' WHERE id = %d;", id));
            return resSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
