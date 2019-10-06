import java.util.regex.Pattern;

public class Student {
    private int id;
    private String name;
    private String group;
    private String subgroup;

    Student(int id) { this.id = id; }

    boolean isNotAuthorized(){
        for (String value: new String[]{this.name, this.group, this.subgroup}) {
            if (value == null)
                return true;
        }
        return false;
    }

    public String getName() { return  this.name; }

    public String getGroup() { return  this.group; }

    public String getSubgroup() { return this.subgroup; }

    String setAttr(String attrName, String value) {
        switch (attrName) {
            case "Name":
                if (Pattern.matches(".+", value)) {
                    this.name = value;
                    return null;
                }
                else return "Должен состоять из символов";
            case "Group":
                if (Pattern.matches("[а-яА-Я]+[-_ ]\\d{3}", value)) {
                    this.group = value;
                    return null;
                }
                else return "Введите группу по шаблону КН-203";
            case "Subgroup":
                if (Pattern.matches("[12]", value)) {
                    this.subgroup = value;
                    return null;
                }
                else return "введите 1 или 2";
            default:
                return "";
        }
    }

    @Override
    public String toString() {
        if (this.isNotAuthorized())
            return "Пользователь не авторизован";
        return String.format("Имя: %s\nГруппа: %s\nНомер группы: %s",
                this.name, this.group, this.subgroup);
    }
}
