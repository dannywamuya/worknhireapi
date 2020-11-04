package dao;

import models.JobDetail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oJobDetailDaoTest {

    private static Connection conn;
    private static Sql2oJobDetailDao jobDetailDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        jobDetailDao = new Sql2oJobDetailDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingJobDetailSetsId() throws Exception {
        JobDetail jobDetail = createNewJobDetail();
        int originalJobDetailId = jobDetail.getId();
        jobDetailDao.add(jobDetail);

        assertNotEquals(originalJobDetailId, jobDetail.getId());
        assertEquals(1, jobDetailDao.getAll().get(0).getId());
    }

    @Test
    public void addedJobDetailsAreReturnedFromGetAll() throws Exception {
        JobDetail jobDetail = createNewJobDetail();
        jobDetailDao.add(jobDetail);

        assertEquals(1, jobDetailDao.getAll().size());
    }

    @Test
    public void noJobDetailsReturnsEmptyList() throws Exception {
        assertEquals(0, jobDetailDao.getAll().size());
    }

    @Test
    public void findByIdReturnsCorrectJobDetail() throws Exception {
        JobDetail jobDetail = createNewJobDetail();
        jobDetailDao.add(jobDetail);
        JobDetail otherJobDetail = createOtherJobDetail();
        jobDetailDao.add(otherJobDetail);

        assertEquals(jobDetail, jobDetailDao.findById(jobDetail.getId()));
        assertEquals("I need a Math tutor", jobDetailDao.findById(otherJobDetail.getId()).getJob_detail());
    }

    @Test
    public void findAllByClientReturnsAllJobDetailsByClient() throws Exception {
        JobDetail jobDetail = createNewJobDetail();
        jobDetailDao.add(jobDetail);
        JobDetail otherJobDetail = createOtherJobDetail();
        jobDetailDao.add(otherJobDetail);
        JobDetail thirdJobDetail = new JobDetail("Cleaner", "I need someone to clean my house", 2);
        jobDetailDao.add(thirdJobDetail);

        assertEquals(2, jobDetailDao.findAllByClient(otherJobDetail.getClient_id()).size());
    }

    @Test
    public void updateCorrectlyUpdatesAllFields() throws Exception {
        JobDetail jobDetail = createNewJobDetail();
        jobDetailDao.add(jobDetail);
        int jobDetailId = jobDetail.getId();
        JobDetail otherJobDetail = createOtherJobDetail();
        jobDetailDao.add(otherJobDetail);

        jobDetailDao.update(jobDetailId, "Cleaner", "I need someone to clean my house", 1);
        JobDetail foundJobDetail = jobDetailDao.findById(jobDetail.getId());

        assertEquals("Cleaner", foundJobDetail.getJob_category());
    }

    @Test
    public void deleteByIdDeletesCorrectClient() throws Exception {
        JobDetail jobDetail = createNewJobDetail();
        jobDetailDao.add(jobDetail);
        int jobDetailId = jobDetail.getId();
        JobDetail otherJobDetail = createOtherJobDetail();
        jobDetailDao.add(otherJobDetail);
        jobDetailDao.deleteById(jobDetailId);

        assertEquals(1, jobDetailDao.getAll().size());
    }

    @Test
    public void deleteByAllByClientIdDeletesCorrectClient() throws Exception {
        JobDetail jobDetail = createNewJobDetail();
        jobDetailDao.add(jobDetail);
        int jobDetailId = jobDetail.getId();
        JobDetail otherJobDetail = createOtherJobDetail();
        jobDetailDao.add(otherJobDetail);
        JobDetail thirdJobDetail = new JobDetail("Cleaner", "I need someone to clean my house", 2);
        jobDetailDao.add(thirdJobDetail);
        jobDetailDao.deleteAllByClientId(2);

        assertEquals(1, jobDetailDao.getAll().size());
    }

    @Test
    public void deleteAllDeletesAllJobDetails() throws Exception {
        JobDetail jobDetail = createNewJobDetail();
        jobDetailDao.add(jobDetail);
        int jobDetailId = jobDetail.getId();
        JobDetail otherJobDetail = createOtherJobDetail();
        jobDetailDao.add(otherJobDetail);
        jobDetailDao.deleteAll();

        assertEquals(0, jobDetailDao.getAll().size());
    }

    private JobDetail createNewJobDetail (){
        return new JobDetail("Plumber", "I need my drainage fixed",1);
    }

    private JobDetail createOtherJobDetail (){
        return new JobDetail("Teacher", "I need a Math tutor",2);
    }

}