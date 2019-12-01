package bot;

public class Lecture {
    private String name;
    private String lecturer;
    private String time;
    private String cabinet;

    public Lecture(String name, String lecturer, String time, String cabinet) {
        this.name = name.replaceAll("\\s\\s", "");
        this.lecturer = lecturer.replaceAll("\\s\\s", "");
        this.time = time.replaceAll("\\s\\s", "");
        this.cabinet = cabinet.replaceAll("\\s\\s", "");
    }

    public String getCabinet() {
        return this.cabinet;
    }

    public String getTime() {
        return this.time;
    }

    public String getLecturer() {
        return this.lecturer;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return String.format("%s\n%s\n  %s\n%s\n", this.time, this.name, this.lecturer, this.cabinet);
    }
}