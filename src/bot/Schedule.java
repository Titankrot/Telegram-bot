package bot;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

public class Schedule {
    private static Map<Integer, String> days = Map.of(
            0, "Пн",
            1, "Вт",
            2, "Ср",
            3, "Чт",
            4, "Пт",
            5, "Сб",
            6, "Вс");

    public String getTodaySchedule(Student student) {
        Calendar curTime = new GregorianCalendar();
        int dayOfWeek = (curTime.get(Calendar.DAY_OF_WEEK) + 5)%7;
        if (dayOfWeek == 6) {
            return "Живи спокойно, воскресенье";
        }
        return getDayOfWeekSchedule(student, DayOfWeek.values()[dayOfWeek]);
    }

    public String getDayOfWeekSchedule(Student student, DayOfWeek dayOfWeek) {
        String day = days.get((dayOfWeek.ordinal())%7);
        String nextDay = days.get((dayOfWeek.ordinal() + 1)%7);
        return UrlParser.getSchedule(student.getUrFUGroup(), day, nextDay);
    }
}
