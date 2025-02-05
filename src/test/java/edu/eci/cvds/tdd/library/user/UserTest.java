package edu.eci.cvds.tdd.library.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    public void testCreateUser(){
        String name = "Maria Martinez";
        String id = "2345";
        User user1 = new User(name, id);

        assertEquals(name, user1.getName());
        assertEquals(id, user1.getId());
    }
}
