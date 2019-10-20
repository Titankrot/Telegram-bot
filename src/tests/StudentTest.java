package tests;

import bot.Attr;
import bot.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {
    private Student student;
    @Before
    public void setUp() throws Exception {
        student = new Student(0);
    }

    @Test
    public void getName_NULLLINE() {
        assertEquals("Должен состоять из символов",
                student.setAttr(Attr.Name, ""));
    }

    @Test
    public void getName_NOTNULL() {
        student.setAttr(Attr.Name, "Student");
        assertEquals("Student", student.getName());
    }

    @Test
    public void getName_RUSSIAN() {
        student.setAttr(Attr.Name, "Студент");
        assertEquals("Студент", student.getName());
    }

    @Test
    public void getGroup() {
        student.setAttr(Attr.Group, "КН-203");
        assertEquals("КН-203", student.getGroup());
    }

    @Test
    public void getGroup_NULLGROUP() {
        assertEquals("Введите группу по шаблону КН-203",
                student.setAttr(Attr.Group, ""));
    }

    @Test
    public void getGroup_GROUPWITHGROUND() {
        student.setAttr(Attr.Group, "ЛОЛ_404");
        assertEquals("ЛОЛ-404", student.getGroup());
    }

    @Test
    public void getGroup_GROUPWITHWHITESPACE() {
        student.setAttr(Attr.Group, "КЕК 227");
        assertEquals("КЕК-227", student.getGroup());
    }

    @Test
    public void getGroup_ENGLISH() {
        assertEquals("Введите группу по шаблону КН-203",
                student.setAttr(Attr.Group, "jj-203"));
    }

    @Test
    public void getGroup_LESSNUMBEROFDECIMALS() {
        assertEquals("Введите группу по шаблону КН-203",
                student.setAttr(Attr.Group, "КН-00"));
    }

    @Test
    public void getGroup_BIGGERNUMBEROFDECIMALS() {
        assertEquals("Введите группу по шаблону КН-203",
                student.setAttr(Attr.Group, "КН-2032"));
    }

    @Test
    public void getSubgroup_1() {
        student.setAttr(Attr.Subgroup, "1");
        assertEquals("1", student.getSubgroup());
    }

    @Test
    public void getSubgroup_2() {
        student.setAttr(Attr.Subgroup, "2");
        assertEquals("2", student.getSubgroup());
    }

    @Test
    public void getSubgroup_0() {
        assertEquals("введите 1 или 2", student.setAttr(Attr.Subgroup, "0"));
    }

    @Test
    public void getSubgroup_3() {
        assertEquals("введите 1 или 2", student.setAttr(Attr.Subgroup, "3"));
    }

    @Test
    public void getSubgroup_NOTANUMBER() {
        assertEquals("введите 1 или 2", student.setAttr(Attr.Subgroup, "h"));
    }

    @Test
    public void testToString() {
        student.setAttr(Attr.Name, "Наташа");
        student.setAttr(Attr.Group, "КН-203");
        student.setAttr(Attr.Subgroup, "1");
        assertEquals("Имя: Наташа\nГруппа: КН-203\nНомер группы: 1", student.toString());
    }

    @Test
    public void  testToString_NOTAUTHORIZED() {
        student.setAttr(Attr.Name, "Наташа");
        student.setAttr(Attr.Group, "КН-203");
        assertEquals("Пользователь не авторизован", student.toString());
    }
}