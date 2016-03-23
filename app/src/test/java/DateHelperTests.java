import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;
import java.util.Date;

import nerd.tuxmobil.fahrplan.congress.DateHelper;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class DateHelperTests {

    protected Calendar calendar;

    @Before
    public void setup() {
        calendar = Calendar.getInstance();
    }

    @Test
    public void shiftByDaysWithMonthChange() {
        calendar.set(2016, 1, 29, 0, 0, 0); // 29.2.2016
        Date date = calendar.getTime();
        calendar.set(2016, 2, 1, 0, 0, 0); // 1.3.2016
        Date shiftedDate = calendar.getTime();
        assertThat(DateHelper.shiftByDays(date, 1)).isEqualTo(shiftedDate);
    }

    @Test
    public void getFormattedDateForSharingWithSummerTime() {
        assertThat(DateHelper.getFormattedDateForSharing(1459580400000L))
                .isEqualTo("Saturday, April 2, 2016 9:00 AM");
    }

    @Test
    public void getFormattedDateForSharingWithLeapYear() {
        assertThat(DateHelper.getFormattedDateForSharing(1456783200000L))
                .isEqualTo("Monday, February 29, 2016 11:00 PM");
    }

}
