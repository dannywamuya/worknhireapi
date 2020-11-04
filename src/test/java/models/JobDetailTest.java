package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class JobDetailTest {

    @Test
    public void NewJobDetailObjectIsCreatedCorrectly_true(){
        JobDetail jobDetail = createNewJobDetail();
        assertTrue(jobDetail instanceof JobDetail);
    }

    @Test
    public void NewJobDetailObjectIsCreatedWithRightParameters_true(){
        JobDetail jobDetail = createNewJobDetail();
        assertEquals("Plumber", jobDetail.getJob_category());
        assertEquals("I need my drainage fixed", jobDetail.getJob_detail());
        assertEquals(1, jobDetail.getClient_id());
    }

    private JobDetail createNewJobDetail (){
        return new JobDetail("Plumber", "I need my drainage fixed",1);
    }

}