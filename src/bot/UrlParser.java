package bot;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlParser {
    static String getSchedule(String group, String day, String nextDay) {
        String groupID = "";
        try {
             groupID = getGETRequestContent("https://urfu.ru/api/schedule/groups/suggest/?query=" + URLEncoder.encode(group, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int index = groupID.indexOf("\"data\":") + 8;
        groupID = groupID.substring(index, index + 6);
        Lecture[] lectures = getLectures(day, nextDay, getGETRequestContent("https://urfu.ru/api/schedule/groups/lessons/" + groupID));
        return getStringFromLectures(lectures);
    }

    private static String getStringFromLectures(Lecture[] lectures) {
        StringBuilder result = new StringBuilder();
        for (Lecture lecture: lectures) {
            result.append(lecture.toString());
            result.append("\n");
        }
        return result.toString();
    }

    private static String getGETRequestContent(String url) {
        try {
            URL UrFUSchedulePage = new URL(url);
            HttpURLConnection request = (HttpURLConnection)UrFUSchedulePage.openConnection();
            request.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
            request.setRequestMethod("GET");
            Scanner input = new Scanner((InputStream)request.getContent());
            StringBuilder result = new StringBuilder();

            while(input.hasNext()) {
                result.append(input.nextLine());
            }

            return result.toString();
        } catch (Exception var5) {
            var5.printStackTrace();
            return "хз";
        }
    }

    private static Lecture[] getLectures(String day, String nextDay, String schedule) {
        ArrayList<Lecture> result = new ArrayList<>();
        int start = schedule.indexOf(day);
        int end = schedule.indexOf(nextDay);
        String scheduleDay = schedule.substring(start, end);
        Matcher timeMatcher = Pattern.compile("<td class=\"shedule-weekday-time\">(.+?)</td>").matcher(scheduleDay);
        Matcher nameMatcher = Pattern.compile("<dd>(.+?)</dd>", Pattern.MULTILINE | Pattern.DOTALL).matcher(scheduleDay);
        Matcher cabinetMatcher = Pattern.compile("<span class=\"cabinet\">(.+?)</span>", Pattern.MULTILINE | Pattern.DOTALL).matcher(scheduleDay);
        Matcher teacherMatcher = Pattern.compile("<span class=\"teacher\">Преподаватель:(.+?)</span>").matcher(scheduleDay);
        while(nameMatcher.find() && timeMatcher.find() && cabinetMatcher.find()) {
            String name = nameMatcher.group(1);
            String time = timeMatcher.group(1);
            String cabinet = cabinetMatcher.group(1);
            String teacher;
            if (teacherMatcher.find()) {
                teacher = teacherMatcher.group(1);
            }
            else {
                teacher = "";
            }
            result.add(new Lecture(name, teacher, time, cabinet));
        }
        return (Lecture[])result.toArray(new Lecture[0]);
    }
}
