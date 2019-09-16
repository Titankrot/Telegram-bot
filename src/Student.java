public class Student {
    private String Id;
    private String Name;
    private String Group;
    private Integer SubGroup;

    public Student(String name) {
        this.Name = name;
    }

    public String getName() { return  this.Name; }

    public String getGroup() { return  this.Group; }

    public void setGroup(String group) {
        //проверка
        Group = group;
    }

    public Integer getSubGroup() { return  this.SubGroup; }

    public void setSubGroup(String subGroup) {
        //проверка
        SubGroup = Integer.parseInt(subGroup);
    }
}
