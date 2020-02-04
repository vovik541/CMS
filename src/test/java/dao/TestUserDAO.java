package dao;

import app.entities.User;
import app.persistences.dao.UserDAO;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestUserDAO {

    private static UserDAO userDAO = null;
    private static User user1 = null;
    private static User user2 = null;
    private static User user3 = null;
    private static User speaker1 = null;
    private static User speaker2 = null;

    @BeforeClass
    public static void setUp(){
        userDAO = mock(UserDAO.class);

        user1 = new User.Builder("firstName1","lastName1")
                .setCustomerId(1)
                .setLogin("login1")
                .setPassword("password1")
                .setEmail("email1@gmail.com")
                .setRole(1)
                .build();

        user2 = new User.Builder("firstName2","lastName2")
                .setCustomerId(2)
                .setLogin("login2")
                .setPassword("password2")
                .setEmail("email2@gmail.com")
                .setRole(1)
                .build();

        user3 = new User.Builder("firstName3","lastName3")
                .setCustomerId(2)
                .setLogin("login3")
                .setPassword("password3")
                .setEmail("email3@gmail.com")
                .setRole(1)
                .build();

        speaker1 = new User.Builder("firstName4","lastName4")
                .setCustomerId(3)
                .setLogin("login4")
                .setPassword("password4")
                .setEmail("email4@gmail.com")
                .setRole(2)
                .build();

        speaker2 = new User.Builder("firstName5","lastName5")
                .setCustomerId(3)
                .setLogin("login5")
                .setPassword("password5")
                .setEmail("email5@gmail.com")
                .setRole(2)
                .build();

        when(userDAO.getSpeakersForOption()).thenReturn(Arrays.asList(speaker1, speaker2));
        when(userDAO.getUsersForPag(1,3)).
                thenReturn(Arrays.asList(user1, user2, user3));
        when(userDAO.getNumOfCustomers()).thenReturn(3);
    }

    @Test
    public void TestGetSpeakersForOption(){
        List<User> speakers = userDAO.getSpeakersForOption();
        assertEquals(2, speakers.size());
        assertEquals("firstName4", speakers.get(0).getFirstName());
        assertEquals("lastName5", speakers.get(1).getLastName());
    }

    @Test
    public void TestGetUsersForPag(){
        List<User> users = userDAO.getUsersForPag(1,3);
        assertEquals(3, users.size());
        assertEquals("firstName1", users.get(0).getFirstName());
        assertEquals("lastName2", users.get(1).getLastName());
        assertEquals("login3", users.get(2).getLogin());
    }

    @Test
    public void TestGetNumOfCustomers(){
        int numOfCustomers = userDAO.getNumOfCustomers();
        assertEquals(3,numOfCustomers);
    }
}
