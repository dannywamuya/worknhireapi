package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class JobDetailTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

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

    @Test
    public void equals_ReturnsTrueIfCategoryIsSame_true() {
        JobDetail testJobDetail = createNewJobDetail();
        JobDetail otherJobDetail = createNewJobDetail();
        assertEquals(testJobDetail, otherJobDetail);
        assertEquals(testJobDetail.getJob_category(), otherJobDetail.getJob_category());
    }

    @Test
    public void addingJobDetailSetsId() throws Exception {
        JobDetail jobDetail = createNewJobDetail();
        int originalJobDetailId = jobDetail.getId();
        jobDetail.add();

        assertNotEquals(originalJobDetailId, jobDetail.getId());
    }

    @Test
    public void addedJobDetailsAreReturnedFromGetAll() throws Exception {
        JobDetail jobDetail = createNewJobDetail();
        jobDetail.add();

        assertEquals(1, jobDetail.getAll().size());
    }

    @Test
    public void noJobDetailsReturnsEmptyList() throws Exception {
        JobDetail jobDetail = createNewJobDetail();
        assertEquals(0, jobDetail.getAll().size());
    }

    @Test
    public void findByIdReturnsCorrectJobDetail() throws Exception {
        JobDetail jobDetail = createNewJobDetail();
        jobDetail.add();
        JobDetail otherJobDetail = createOtherJobDetail();
        otherJobDetail.add();

        assertEquals(jobDetail, jobDetail.findById(jobDetail.getId()));
        assertEquals("I need a Math tutor", otherJobDetail.findById(otherJobDetail.getId()).getJob_detail());
    }

    @Test
    public void findAllByClientReturnsAllJobDetailsByClient() throws Exception {
        JobDetail jobDetail = createNewJobDetail();
        jobDetail.add();
        JobDetail otherJobDetail = createOtherJobDetail();
        otherJobDetail.add();
        JobDetail thirdJobDetail = new JobDetail("Cleaner", "I need someone to clean my house", 2);
        thirdJobDetail.add();

        assertEquals(2, jobDetail.findAllByClient(otherJobDetail.getClient_id()).size());
    }

    @Test
    public void updateCorrectlyUpdatesAllFields() throws Exception {
        JobDetail jobDetail = createNewJobDetail();
        jobDetail.add();
        int jobDetailId = jobDetail.getId();
        JobDetail otherJobDetail = createOtherJobDetail();
        otherJobDetail.add();

        jobDetail.update(jobDetailId, "Cleaner", "I need someone to clean my house", 1);
        JobDetail foundJobDetail = jobDetail.findById(jobDetail.getId());

        assertEquals("Cleaner", foundJobDetail.getJob_category());
    }

    @Test
    public void deleteByIdDeletesCorrectJobDetail() throws Exception {
        JobDetail jobDetail = createNewJobDetail();
        jobDetail.add();
        int jobDetailId = jobDetail.getId();
        JobDetail otherJobDetail = createOtherJobDetail();
        otherJobDetail.add();
        jobDetail.deleteById();

        assertEquals(1, jobDetail.getAll().size());
    }

    @Test
    public void deleteByAllByClientIdDeletesCorrectClient() throws Exception {
        JobDetail jobDetail = createNewJobDetail();
        jobDetail.add();
        int jobDetailId = jobDetail.getId();
        JobDetail otherJobDetail = createOtherJobDetail();
        otherJobDetail.add();
        JobDetail thirdJobDetail = new JobDetail("Cleaner", "I need someone to clean my house", 2);
        thirdJobDetail.add();
        jobDetail.deleteAllByClientId(2);

        assertEquals(1, jobDetail.getAll().size());
    }

    @Test
    public void deleteAllDeletesAllJobDetails() throws Exception {
        JobDetail jobDetail = createNewJobDetail();
        jobDetail.add();
        JobDetail otherJobDetail = createOtherJobDetail();
        otherJobDetail.add();
        jobDetail.deleteAll();

        assertEquals(0, jobDetail.getAll().size());
    }

    private JobDetail createNewJobDetail (){
        return new JobDetail("Plumber", "I need my drainage fixed",1);
    }

    private JobDetail createOtherJobDetail (){
        return new JobDetail("Teacher", "I need a Math tutor",2);
    }

}