package dao;

import app.entities.Conference;
import app.entities.User;
import app.persistences.dao.ConferenceDAO;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestConferenceDAO {

    private static ConferenceDAO conferenceDAO = null;
    private static Conference conference1 = null;
    private static Conference conference2 = null;
    private static Conference conference3 = null;

    private static User speaker1 = null;
    private static User speaker2 = null;
    private static User user1 = null;

    private static java.sql.Date currentDate = null;
    private static String currentTime = null;

    private static int speakerId1 = 0;
    private static int speakerId2 = 0;

    @BeforeClass
    public static void setUp(){
        conferenceDAO = mock(ConferenceDAO.class);

        speaker1 = new User.Builder("firstName1","lastName1")
                .setCustomerId(1)
                .setLogin("login1")
                .setPassword("password1")
                .setEmail("email1@gmail.com")
                .setRole(2)
                .build();

        speaker2 = new User.Builder("firstName2","lastName2")
                .setCustomerId(2)
                .setLogin("login2")
                .setPassword("password2")
                .setEmail("email2@gmail.com")
                .setRole(2)
                .build();

        user1 = new User.Builder("firstName3","lastName3")
                .setCustomerId(3)
                .setLogin("login3")
                .setPassword("password3")
                .setEmail("email3@gmail.com")
                .setRole(1)
                .build();


        speakerId1 = speaker1.getCustomerId();
        speakerId2 = speaker2.getCustomerId();

        conference1 = new Conference.Builder(speakerId1, "Conference Name 1",
                "location 1", "2020-10-10 00:00:00", "09:00:00", "10:00:00")
                .setConferenceId(1)
                .build();

        conference2 = new Conference.Builder(speakerId1, "Conference Name 2",
                "location 2", "2020-10-10 00:00:00", "09:00:00", "10:00:00")
                .setConferenceId(2)
                .build();

        conference3 = new Conference.Builder(speakerId2, "Conference Name 3",
                "location 3", "2020-10-10 00:00:00", "09:00:00", "10:00:00")
                .setConferenceId(3)
                .build();

        currentDate = new java.sql.Date(400000);
        currentTime = "12:00:00";

        when(conferenceDAO.getConfBySpeakerId(speakerId1)).thenReturn(Arrays.asList(conference1, conference2));
        when(conferenceDAO.getConfBySpeakerId(speakerId2)).thenReturn(Arrays.asList(conference3));
        when(conferenceDAO.getConfAfterDateTime(currentDate,currentTime)).
                thenReturn(Arrays.asList(conference1, conference2, conference3));
        when(conferenceDAO.isRegisteredInConf(user1.getCustomerId(),conference1.getConferenceId())).thenReturn(true);
        when(conferenceDAO.isRegisteredInConf(user1.getCustomerId(),conference2.getConferenceId())).thenReturn(false);
    }

    @Test
    public void TestGetConfBySpeakerId(){
        List<Conference> conferences = conferenceDAO.getConfBySpeakerId(speakerId1);

        assertNotNull(conferences);
        assertEquals(2,conferences.size());
        assertEquals("Conference Name 1", conferences.get(0).getConfName());
        assertEquals("Conference Name 2", conferences.get(1).getConfName());

        conferences = conferenceDAO.getConfBySpeakerId(speakerId2);
        assertEquals(1,conferences.size());
        assertEquals("Conference Name 3", conferences.get(0).getConfName());
    }
    @Test
    public void getConfAfterDateTime(){
        List<Conference> conferences = conferenceDAO.getConfAfterDateTime(currentDate, currentTime);

        assertNotNull(conferences);
        assertEquals(3,conferences.size());
        assertEquals("Conference Name 1", conferences.get(0).getConfName());
        assertEquals("Conference Name 2", conferences.get(1).getConfName());
        assertEquals("Conference Name 3", conferences.get(2).getConfName());
    }
    @Test
    public void TestIsRegisteredInConf(){
        Boolean registered = conferenceDAO.isRegisteredInConf(user1.getCustomerId(),conference1.getConferenceId());

        assertTrue(registered);

        registered = conferenceDAO.isRegisteredInConf(user1.getCustomerId(),conference2.getConferenceId());

        assertFalse(registered);
    }

}
