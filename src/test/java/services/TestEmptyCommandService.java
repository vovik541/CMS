package services;

import app.services.EmptyCommandService;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestEmptyCommandService {

    @Test
    public void TestToFormat(){
        int i1 = 0;
        int i2 = 9;
        int i3 = 10;

        assertEquals("00", EmptyCommandService.toFormat(i1));
        assertEquals("09", EmptyCommandService.toFormat(i2));
        assertEquals("10", EmptyCommandService.toFormat(i3));
    }

    @Test
    public void TestGetCurrentTime(){

        String time = EmptyCommandService.getCurrentTime();

        assertNotNull(time);
        assertTrue(time instanceof String);
    }

    @Test
    public void TestGetCurrentDate(){

        java.sql.Date date = EmptyCommandService.getCurrentDay();

        assertNotNull(date);
        assertTrue(date instanceof java.sql.Date);
    }
}
