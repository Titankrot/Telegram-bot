import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;

public class XlParser {
    private XSSFSheet workSheet;
    private HashMap<String, Integer> groups = new HashMap<>();
    public XlParser(String filename) throws Exception {
        XSSFWorkbook book = readWorkbook(filename);
        if (book == null) throw new Exception("Wrong file!");
        workSheet = book.getSheetAt(0);
        fillGroups();
    }

    public String getSubject(Student student, DayOfWeek day, Integer numberOfClass) {
        int numberOfRow = 5 + day.ordinal() * 12 + numberOfClass * 2;
        XSSFRow row = workSheet.getRow(numberOfRow);
        XSSFCell cell = row.getCell(
                groups.get(student.getGroup())
                + Integer.parseInt(student.getSubgroup()) - 1);
        return cell.getStringCellValue();
    }

    public String[] getSubjectForDay(Student student, DayOfWeek day) {
        Iterator rowIter = workSheet.rowIterator();
        int numberOfRow = 0;
        int neededRowNumber = 5 + day.ordinal() * 12;
        while (rowIter.hasNext() && numberOfRow != neededRowNumber) {
            XSSFRow row = (XSSFRow) rowIter.next();
            numberOfRow++;
        }
        String[] subjectsForDay = new String[6];
        for (int i=0; i<12; i+=2) {
            if (rowIter.hasNext()) {
                subjectsForDay[i / 2] = ((XSSFRow) rowIter.next())
                        .getCell(groups.get(student.getGroup())
                                + Integer.parseInt(student.getSubgroup()) - 1).getStringCellValue();
            }
            else break;
            if (rowIter.hasNext()) {
                rowIter.next();
            }
            else break;
        }
        return subjectsForDay;
    }

    private XSSFWorkbook readWorkbook(String filename) {
        try {
            return new XSSFWorkbook(new FileInputStream(filename));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void fillGroups() {
        groups.put("МТ-201", 3);
        groups.put("МТ-202", 5);
        groups.put("МХ-201", 7);
        groups.put("КБ-201", 9);
        groups.put("КН-201", 11);
        groups.put("КН-202", 13);
        groups.put("КН-203", 15);
        groups.put("ФТ-201", 17);
        groups.put("ФТ-202", 19);
        groups.put("МО-201", 21);
        groups.put("МО-202", 23);
        groups.put("ПМ-201", 25);
    }
}
