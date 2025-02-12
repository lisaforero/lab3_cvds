package edu.eci.cvds.tdd.library.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testSetNameAndGetName() {
        User user = new User("Maria Martinez", "2345");
        
        assertEquals("Maria Martinez", user.getName());
        
        user.setName("Jane Sanchez");
        
        assertEquals("Jane Sanchez", user.getName());
    }

    @Test
    public void testSetIdAndGetId() {
        User user = new User("Juan Perez", "1234");

        assertEquals("1234", user.getId());

        user.setId("6789");
    
        assertEquals("6789", user.getId());
    }
}
