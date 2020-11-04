package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerTest {

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

    private Worker createNewWorker(){
        return new Worker("James May", "Electrician",2 ,"jm@email.com","(123) 456-7890");
    }

}