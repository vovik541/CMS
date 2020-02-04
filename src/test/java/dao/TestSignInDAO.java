package dao;

import app.entities.User;
import app.persistences.dao.SignInDAO;
import org.junit.BeforeClass;


import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;


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

}
