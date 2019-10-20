package bot;

import java.io.*;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Scanner;

public class Schedule {
    private static Map<Integer, String> days = Map.of(
            0, "Пн",
            1, "Вт",
            2, "Ср",
            3, "Чт",
            4, "Пт",
            5, "Сб",
            6, "Вс");

    private static String getScheduleFromTo(String group, String start, String end) throws IOException {
        Path path = Path.of(".","resources", "rasp.txt");
        StringBuilder fileContent = new StringBuilder();
        Scanner br = new Scanner(path);
        String sub;
        boolean groupFound = false;
        while (br.hasNext()) {
            sub = br.nextLine();
            if (!groupFound) {
                groupFound = sub.contains(group);
                continue;
            }
            if (sub.contains(start))
                break;
        }
        while (br.hasNext()) {
            sub = br.nextLine();
            if (sub.contains(end))
                break;
            fileContent.append(sub).append("\n");
        }
        return fileContent.toString();
    }

    public String getTodaySchedule(Student student) {
        Calendar curTime = new GregorianCalendar();
        int dayOfWeek = curTime.get(Calendar.DAY_OF_WEEK);
        return getDayOfWeekSchedule(student, DayOfWeek.values()[dayOfWeek]);
    }

    public String getDayOfWeekSchedule(Student student, DayOfWeek dayOfWeek) {
        String day = days.get(dayOfWeek.ordinal());
        String nextDay = days.get((dayOfWeek.ordinal()+1)%7);
        try {
            return getScheduleFromTo(student.getGroup(), day, nextDay);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
