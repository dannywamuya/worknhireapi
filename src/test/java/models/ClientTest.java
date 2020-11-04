package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {

    @Test
    public void NewClientObjectIsCreatedCorrectly_true(){
        Client client = createNewClient();
        assertTrue(client instanceof Client);
    }

    @Test
    public void NewClientObjectIsCreatedWithRightParameters_true(){
        Client client = createNewClient();
        assertEquals("John Doe", client.getName());
        assertEquals("jd@email.com", client.getEmail());
        assertEquals("(123) 456-7890", client.getPhone());
    }

    public Client createNewClient (){
        return new Client("John Doe", "jd@email.com","(123) 456-7890");
    }
}