import java.util.Map;

public class Student {
    private int Id;
    private String Name;
    private String Group;
    private String SubGroup;

    Student(int id) { this.Id = id; }

    private static String[] AuthorizeQuestions = new String[]{
            "Как твое имя?",
            "Какая у тебя группа?",
            "Какой номер твоей группы?"
    };

    static int questionNumber(String quest){
        for (int i = 0; i < AuthorizeQuestions.length; i++) {
            String p = AuthorizeQuestions[i];
            if (AuthorizeQuestions[i].equals(quest))
                return i;
        }
        return -1;
    }

    static Map<String, String> QuestionsToAttr = Map.of(
            AuthorizeQuestions[0], "Name",
            AuthorizeQuestions[1], "Group",
            AuthorizeQuestions[2], "SubGroup"
    );
    
    boolean isNotAuthorized(){
        for (String value: new String[]{this.Name, this.Group, this.SubGroup}) {
            if (value == null)
                return true;
        }
        return false;
    }

    String askAthorizationQues(int authorizeStep){
        if (authorizeStep == AuthorizeQuestions.length)
            return null;
        return AuthorizeQuestions[authorizeStep];
    }

    public String getName() { return  this.Name; }

    public void setName(String value) { this.Name = value; }

    public String getGroup() { return  this.Group; }

    public void setGroup(String value) { this.Group = value; }

    public String getSubGroup() { return this.SubGroup; }

    public void setSubGroup(String value) { this.SubGroup = value; }

    void setAttr(String attrName, String value){
        switch (attrName){
            case "Name":
                this.Name = value;
                break;
            case "Group":
                this.Group = value;
                break;
            case "SubGroup":
                this.SubGroup = value;
                break;
        }
    }

    String ToString(){
        if (this.isNotAuthorized())
            return "Пользователь не авторизован";
        return String.format("Имя: %s\nГруппа: %s\nНомер группы: %s",
                this.Name, this.Group, this.SubGroup);
    }
}
