package dao;

import models.Client;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oClientDaoTest {

    private static Connection conn;
    private static Sql2oClientDao clientDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        clientDao = new Sql2oClientDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingClientSetsId() throws Exception {
        Client client = createNewClient();
        int originalClientId = client.getId();
        clientDao.add(client);

        assertNotEquals(originalClientId, client.getId());
        assertEquals(1, clientDao.getAll().get(0).getId());
    }

    @Test
    public void addedClientsAreReturnedFromGetAll() throws Exception {
        Client client = createNewClient();
        clientDao.add(client);

        assertEquals(1, clientDao.getAll().size());
    }

    @Test
    public void noClientsReturnsEmptyList() throws Exception {
        assertEquals(0, clientDao.getAll().size());
    }

    @Test
    public void findByIdReturnsCorrectClient() throws Exception {
        Client client = createNewClient();
        clientDao.add(client);
        Client otherClient = createOtherClient();
        clientDao.add(otherClient);

        assertEquals(client, clientDao.findById(client.getId()));
        assertEquals("mj@email.com", clientDao.findById(otherClient.getId()).getEmail());
    }

    @Test
    public void updateCorrectlyUpdatesAllFields() throws Exception {
        Client client = createNewClient();
        clientDao.add(client);
        int clientId = client.getId();
        Client otherClient = createOtherClient();
        clientDao.add(otherClient);
        clientDao.update(clientId, "James May", "jm@email.com","(123) 454-3210" );
        Client foundClient = clientDao.findById(client.getId());

        assertEquals("James May", foundClient.getName());
        assertEquals("jm@email.com", foundClient.getEmail());
        assertEquals(clientId, foundClient.getId());
    }

    @Test
    public void deleteByIdDeletesCorrectClient() throws Exception {
        Client client = createNewClient();
        clientDao.add(client);
        Client otherClient = createOtherClient();
        clientDao.add(otherClient);
        clientDao.deleteById(client.getId());

        assertEquals(1, clientDao.getAll().size());
    }

    @Test
    public void deleteAllDeletesAllClients() throws Exception {
        Client client = createNewClient();
        clientDao.add(client);
        Client otherClient = createOtherClient();
        clientDao.add(otherClient);
        clientDao.deleteAll();

        assertEquals(0, clientDao.getAll().size());
    }


    public Client createNewClient (){
        return new Client("John Doe", "jd@email.com","(123) 456-7890");
    }

    public Client createOtherClient() {
        return new Client("Mary Jane", "mj@email.com","(123) 454-3210");
    }

}