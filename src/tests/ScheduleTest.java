package tests;

import bot.Attr;
import bot.DayOfWeek;
import bot.Schedule;
import bot.Student;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScheduleTest {
    private static String mondaySchedule =
            "09:00 - 10:30\n" +
            "1. Объектно-ориентированное программирование (подгруппа)\n" +
            "лабораторные занятия\n" +
            "511 Тургенева, 4\n" +
            "Объектно-ориентированное программирование (подгруппа)\n" +
            "лабораторные занятия\n" +
            "526 Тургенева, 4\n" +
            "10:40 - 12:10\t\n" +
            "2. Объектно-ориентированное программирование\n" +
            "лекции\n" +
            "632 Тургенева, 4\n" +
            "Преподаватель: Клепинин А. В.\n";

    @Test
    public void getDayOfWeekSchedule_0() {
        Student student = new Student(1);
        student.setAttr(Attr.Group, "КН-203");
        Schedule schedule = new Schedule();
        assertEquals(mondaySchedule,
                schedule.getDayOfWeekSchedule(student, DayOfWeek.monday));
    }
}