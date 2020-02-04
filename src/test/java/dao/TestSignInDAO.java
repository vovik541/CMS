package dao;

import app.entities.User;
import app.persistences.dao.SignInDAO;
import org.junit.BeforeClass;


import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestSignInDAO extends Mockito {
    private static SignInDAO signInDAO = null;
    private static User user1 = null;
    private static User user2 = null;

    @BeforeClass
    public static void setUp() {
        signInDAO = mock(SignInDAO.class);

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

        when(signInDAO.getUsers()).thenReturn(Arrays.asList(user1, user2));
        when(signInDAO.getUserByLogPas("login1","password1")).thenReturn(user1);
    }

    @Test
    public void getUsersTest(){
        List<User> users = signInDAO.getUsers();
        assertEquals(2, users.size());
        assertEquals("firstName1", users.get(0).getFirstName());
        assertEquals("lastName2", users.get(1).getLastName());
    }

    @Test
    public void getUsersByLogPassTest(){
        String login = "login1";
        String password = "password1";

        User user = signInDAO.getUserByLogPas(login,password);
        assertEquals(login, user.getLogin());
        assertEquals(password, user.getPassword());

        user = signInDAO.getUserByLogPas("badLogin", "badPassword");
        assertNull(user);
    }
}
