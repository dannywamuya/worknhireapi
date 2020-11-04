package dao;

import models.Worker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oWorkerDaoTest {

    private static Connection conn;
    private static Sql2oWorkerDao workerDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        workerDao = new Sql2oWorkerDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingWorkerSetsId() throws Exception {
        Worker worker = createNewWorker();
        int originalWorkerId = worker.getId();
        workerDao.add(worker);

        assertNotEquals(originalWorkerId, worker.getId());
        assertEquals(1, workerDao.getAll().get(0).getId());
    }

    @Test
    public void addedWorkersAreReturnedFromGetAll() throws Exception {
        Worker worker = createNewWorker();
        workerDao.add(worker);

        assertEquals(1, workerDao.getAll().size());
    }

    @Test
    public void noWorkersReturnsEmptyList() throws Exception {
        assertEquals(0, workerDao.getAll().size());
    }

    @Test
    public void findByIdReturnsCorrectWorker() throws Exception {
        Worker worker = createNewWorker();
        workerDao.add(worker);
        Worker otherWorker = createOtherWorker();
        workerDao.add(otherWorker);

        assertEquals(worker, workerDao.findById(worker.getId()));
        assertEquals("jt@email.com", workerDao.findById(otherWorker.getId()).getEmail());
    }

    @Test
    public void updateCorrectlyUpdatesAllFields() throws Exception {
        Worker worker = createNewWorker();
        workerDao.add(worker);
        int workerId = worker.getId();
        Worker otherWorker = createOtherWorker();
        workerDao.add(otherWorker);
        workerDao.update(workerId, "Chris Rock","Gardener",5, "cr@email.com","(951) 753-2424" );
        Worker foundWorker = workerDao.findById(worker.getId());

        assertEquals("Chris Rock", foundWorker.getName());
        assertEquals("cr@email.com", foundWorker.getEmail());
        assertEquals(workerId, foundWorker.getId());
    }

    @Test
    public void deleteByIdDeletesCorrectWorker() throws Exception {
        Worker worker = createNewWorker();
        workerDao.add(worker);
        Worker otherWorker = createOtherWorker();
        workerDao.add(otherWorker);
        workerDao.deleteById(worker.getId());

        assertEquals(1, workerDao.getAll().size());
    }

    @Test
    public void deleteAllDeletesAllWorkers() throws Exception {
        Worker worker = createNewWorker();
        workerDao.add(worker);
        Worker otherWorker = createOtherWorker();
        workerDao.add(otherWorker);
        workerDao.deleteAll();

        assertEquals(0, workerDao.getAll().size());
    }

    private Worker createNewWorker(){
        return new Worker("James May", "Electrician",2 ,"jm@email.com","(123) 456-7890");
    }

    private Worker createOtherWorker(){
        return new Worker("Joe Trump", "Mechanic",10 ,"jt@email.com","(071) 982-4538");
    }
}