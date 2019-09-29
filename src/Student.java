public class Student {
    private Long Id;
    private String Name;
    private String Group;
    private String SubGroup;

    public Student(Long id) { this.Id = id; }

    public String getName() { return  this.Name; }

    public void setName(String value) { this.Name = value; }

    public String getGroup() { return  this.Group; }

    public void setGroup(String value) { this.Group = value; }

    public String getSubGroup() { return this.SubGroup; }

    public void setSubGroup(String value) { this.SubGroup = value; }
}
