package Utilities;
import java.util.Calendar;

public class Time {
    public static int getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR)*10000 + (calendar.get(Calendar.MONTH)+1)*100 + calendar.get(Calendar.DAY_OF_MONTH);
    }
}
