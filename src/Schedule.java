import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

public class Schedule {
    private static Map<Integer, String> int2day = Map.of(
            0, "Ïí",
            1, "Âò",
            2, "Ñð",
            3, "×ò",
            4, "Ïò",
            5, "Ñá",
            6, "Âñ");

    public static void main(String[] args) {
        Schedule k = new Schedule();
        try {
            System.out.println(k.getNextLesson(new Student(1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getTextFromTo(String start, String end) throws IOException {
        String path = "rasp.txt";
        StringBuilder fileContent = new StringBuilder();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(path), "utf-8"));
            String sub;
            while ((sub = br.readLine()) != null && !sub.contains(start)) {
                continue;
            }
            while ((sub = br.readLine()) != null && !sub.contains(end)) {
                fileContent.append(sub + "\n");
            }
        return fileContent.toString();
    }

    public String getNextLesson(Student student) throws IOException {
        Calendar curTime = new GregorianCalendar();
        int dayOfWeek = curTime.get(Calendar.DAY_OF_WEEK);
        String day = int2day.get((dayOfWeek+5) % 7);
        String nextDay = int2day.get((dayOfWeek+6) % 7);
        return getTextFromTo(day, nextDay);
    }
}
