package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void NewWorkerObjectIsCreatedCorrectly_true(){
        Worker worker = createNewWorker();
        assertTrue(worker instanceof Worker);
    }

    @Test
    public void NewWorkerObjectIsCreatedWithRightParameters_true(){
        Worker worker = createNewWorker();
        assertEquals("James May", worker.getName());
        assertEquals("jm@email.com", worker.getEmail());
        assertEquals("Electrician", worker.getSkill());
    }

    @Test
    public void equals_ReturnsTrueIfNameIsSame_true() {
        Worker testWorker = createNewWorker();
        Worker anotherWorker = createNewWorker();
        assertEquals(testWorker, anotherWorker);
        assertEquals(testWorker.getPhone(), anotherWorker.getPhone());
    }

    @Test
    public void addingWorkerSetsId() throws Exception {
        Worker worker = createNewWorker();
        int originalWorkerId = worker.getId();
        worker.add();

        assertNotEquals(originalWorkerId, worker.getId());
    }

    @Test
    public void addedWorkersAreReturnedFromGetAll() throws Exception {
        Worker worker = createNewWorker();
        worker.add();

        assertEquals(1, worker.getAll().size());
    }

    @Test
    public void noWorkersReturnsEmptyList() throws Exception {
        Worker worker = createNewWorker();
        assertEquals(0, worker.getAll().size());
    }

    @Test
    public void findByIdReturnsCorrectWorker() throws Exception {
        Worker worker = createNewWorker();
        worker.add();
        int workerId = worker.getId();
        Worker otherWorker = createOtherWorker();
        otherWorker.add();

        assertEquals("James May", worker.findById(workerId).getName());
        assertEquals("jt@email.com", otherWorker.findById(otherWorker.getId()).getEmail());
    }

    @Test
    public void updateCorrectlyUpdatesAllFields() throws Exception {
        Worker worker = createNewWorker();
        worker.add();
        int workerId = worker.getId();
        Worker otherWorker = createOtherWorker();
        otherWorker.add();
        worker.update(workerId, "Chris Rock","Gardener",5, "cr@email.com","(951) 753-2424" );
        Worker foundWorker = worker.findById(worker.getId());

        assertEquals("Chris Rock", foundWorker.getName());
        assertEquals("cr@email.com", foundWorker.getEmail());
        assertEquals(workerId, foundWorker.getId());
    }

    @Test
    public void deleteByIdDeletesCorrectWorker() throws Exception {
        Worker worker = createNewWorker();
        worker.add();
        Worker otherWorker = createOtherWorker();
        otherWorker.add();
        worker.deleteById();

        assertEquals(1, worker.getAll().size());
    }

    @Test
    public void deleteAllDeletesAllWorkers() throws Exception {
        Worker worker = createNewWorker();
        worker.add();
        Worker otherWorker = createOtherWorker();
        otherWorker.add();
        worker.deleteAll();

        assertEquals(0, worker.getAll().size());
    }

    private Worker createNewWorker(){
        return new Worker("James May", "Electrician",2 ,"jm@email.com","(123) 456-7890");
    }

    private Worker createOtherWorker(){
        return new Worker("Joe Trump", "Mechanic",10 ,"jt@email.com","(071) 982-4538");
    }

}