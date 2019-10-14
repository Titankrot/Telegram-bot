import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

public class Schedule {
    private static Map<Integer, String> days = Map.of(
            0, "Ïí",
            1, "Âò",
            2, "Ñð",
            3, "×ò",
            4, "Ïò",
            5, "Ñá",
            6, "Âñ");

    private static String getScheduleFromTo(String group, String start, String end) throws IOException {
        String path = "rasp.txt";
        StringBuilder fileContent = new StringBuilder();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(path), "utf-8"));
        String sub;
        boolean groupFound = false;
        while ((sub = br.readLine()) != null && (!groupFound || !sub.contains(start))) {
            if (!groupFound)
                groupFound = sub.contains(start);
        }
        while ((sub = br.readLine()) != null && !sub.contains(end)) {
            fileContent.append(sub + "\n");
        }
        return fileContent.toString();
    }

    public String getTodaySchedule(Student student) {
        Calendar curTime = new GregorianCalendar();
        int dayOfWeek = curTime.get(Calendar.DAY_OF_WEEK);
        String day = days.get((dayOfWeek+5) % 7);
        String nextDay = days.get((dayOfWeek+6) % 7);
        try {
            return getScheduleFromTo(student.getGroup(), day, nextDay);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getDayOfWeekSchedule(Student student, DayOfWeek dayOfWeek) {
        String day = days.get(dayOfWeek.ordinal());
        String nextDay = days.get(dayOfWeek.ordinal());
        try {
            return getScheduleFromTo(student.getGroup(), day, nextDay);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
