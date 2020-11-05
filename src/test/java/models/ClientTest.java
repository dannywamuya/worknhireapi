package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

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

    @Test
    public void equals_ReturnsTrueIfNameIsSame_true() {
        Client testClient = createNewClient();
        Client anotherClient = createNewClient();
        assertEquals(testClient, anotherClient);
        assertEquals(testClient.getPhone(), anotherClient.getPhone());
    }

    @Test
    public void addingClientSetsId() throws Exception {
        Client client = createNewClient();
        int originalClientId = client.getId();
        client.add();

        assertNotEquals(originalClientId, client.getId());
    }

    @Test
    public void addedClientsAreReturnedFromGetAll() throws Exception {
        Client client = createNewClient();
        client.add();

        assertEquals(1, client.getAll().size());
    }

    @Test
    public void noClientsReturnsEmptyList() throws Exception {
        Client client = createNewClient();
        assertEquals(0, client.getAll().size());
    }

    @Test
    public void findByIdReturnsCorrectClient() throws Exception {
        Client client = createNewClient();
        client.add();
        int clientId = client.getId();
        Client otherClient = createOtherClient();
        otherClient.add();

        assertEquals("(123) 456-7890", client.findById(clientId).getPhone());
        assertEquals("mj@email.com", otherClient.findById(otherClient.getId()).getEmail());
    }

    @Test
    public void updateCorrectlyUpdatesAllFields() throws Exception {
        Client client = createNewClient();
        client.add();
        int clientId = client.getId();
        Client otherClient = createOtherClient();
        otherClient.add();
        Client.update(clientId, "James May", "jm@email.com","(123) 454-3210" );
        Client foundClient = client.findById(clientId);

        assertEquals("James May", foundClient.getName());
        assertEquals("jm@email.com", foundClient.getEmail());
        assertEquals(clientId, foundClient.getId());
    }

    @Test
    public void deleteByIdDeletesCorrectClient() throws Exception {
        Client client = createNewClient();
        client.add();
        Client otherClient = createOtherClient();
        otherClient.add();
        client.deleteById();

        assertEquals(1, client.getAll().size());
    }

    @Test
    public void deleteAllDeletesAllClients() throws Exception {
        Client client = createNewClient();
        client.add();
        Client otherClient = createOtherClient();
        otherClient.add();
        client.deleteAll();

        assertEquals(0, client.getAll().size());
    }


    public Client createNewClient (){
        return new Client("John Doe", "jd@email.com","(123) 456-7890");
    }

    public Client createOtherClient() {
        return new Client("Mary Jane", "mj@email.com","(123) 454-3210");
    }
}