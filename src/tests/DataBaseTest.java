package tests;

import bot.Attr;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import bot.DataBase;
import bot.Student;


import static org.junit.Assert.*;

public class DataBaseTest {
    private static DataBase data;
    private static Student student;

    @BeforeClass
    public static void setUp() {
        data = new DataBase("TestDataBase.s3db");
        student = new Student(0);
        student.setAttr(Attr.Name, "Danil");
        student.setAttr(Attr.Group, "สอ-203");
        student.setAttr(Attr.Subgroup, "2");
        data.put(1, student);
    }

    @Test
    public void put() {
        data.put(2, student);
    }

    @Test
    public void get() {
        Student st = data.get(1);
        assertEquals(st.getName(), "Danil");
        assertEquals(st.getGroup(), "สอ-203");
        assertEquals(st.getSubgroup(), "2");
    }

    @Test
    public void containsKey() {
        assert data.containsKey(1);
        assert !data.containsKey(99);
    }
}