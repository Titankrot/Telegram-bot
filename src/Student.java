import java.util.Map;

public class Student {
    private int id;
    private String name;
    private String group;
    private String subgroup;

    Student(int id) { this.id = id; }

    private static String[] autorizeQuestions = new String[] {
            "Как твое имя?",
            "Какая у тебя группа?",
            "Какой номер твоей группы?"
    };

    static int questionNumber(String quest){
        for (int i = 0; i < autorizeQuestions.length; i++) {
            String p = autorizeQuestions[i];
            if (autorizeQuestions[i].equals(quest))
                return i;
        }
        return -1;
    }

    static Map<String, String> questionsToAttr = Map.of(
            autorizeQuestions[0], "Name",
            autorizeQuestions[1], "Group",
            autorizeQuestions[2], "Subgroup"
    );
    
    boolean isNotAuthorized(){
        for (String value: new String[]{this.name, this.group, this.subgroup}) {
            if (value == null)
                return true;
        }
        return false;
    }

    String askAuthorizationQues(int authorizeStep){
        if (authorizeStep == autorizeQuestions.length)
            return null;
        return autorizeQuestions[authorizeStep];
    }

    public String getname() { return  this.name; }

    public void setname(String value) { this.name = value; }

    public String getgroup() { return  this.group; }

    public void setgroup(String value) { this.group = value; }

    public String getsubgroup() { return this.subgroup; }

    public void setsubgroup(String value) { this.subgroup = value; }

    void setAttr(String attrname, String value) {
        switch (attrname){
            case "Name":
                this.name = value;
                break;
            case "Group":
                this.group = value;
                break;
            case "Subgroup":
                this.subgroup = value;
                break;
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
