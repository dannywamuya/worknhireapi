import com.google.gson.Gson;

import exceptions.ApiException;
import models.Client;
import models.JobDetail;
import models.Worker;
import org.sql2o.Connection;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {

        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);

        staticFileLocation("/public");

        Connection conn;
        Gson gson =new Gson();

        //CREATE
        //post: creates a new client
        post("/clients/new", "application/json",(req, res) -> {
            Client client = gson.fromJson(req.body(), Client.class);
            client.add();
            res.status(201);
            return gson.toJson(client);
        });

        //post: creates a new worker
        post("/workers/new", "application/json",(req, res) -> {
            Worker worker = gson.fromJson(req.body(), Worker.class);
            worker.add();
            res.status(201);
            return gson.toJson(worker);
        });

        //post: creates a new jobdetail posting
        post("/jobdetails/new", "application/json",(req, res) -> {
            JobDetail jobDetail = gson.fromJson(req.body(), JobDetail.class);
            jobDetail.add();
            res.status(201);
            return gson.toJson(jobDetail);
        });


        //READ
        //get: gets all clients
        get("/clients", "application/json", (req, res) -> {
            Client clientInstance = new Client("su", "su@email.com", "su");
            if (clientInstance.getAll().size() > 0) {
                return gson.toJson(clientInstance.getAll());
            } else {
                return "{\"message\":\"I'm sorry, there are no clients currently listed in the database.\"}";
            }
        });

        //get: gets a client by their Id
        get("/clients/:id", "application/json", (req, res) -> {
            Client clientInstance = new Client("su", "su@email.com", "su");
            int clientId = Integer.parseInt(req.params("id"));
            if (clientInstance.findById(clientId) == null) {
                throw new ApiException(404, String.format("No client with the ID: %s exists", req.params("id")));
            }
            return gson.toJson(clientInstance.findById(clientId));
        });

        //get: gets all workers
        get("/workers", "application/json", (req, res) -> {
            Worker workerInstance = new Worker("su","su", 1,"su@email.com", "su");
            if (workerInstance.getAll().size() > 0) {
                return gson.toJson(workerInstance.getAll());
            } else {
                return "{\"message\":\"I'm sorry, there are no workers currently listed in the database.\"}";
            }
        });

        //get: gets a worker by their Id
        get("/workers/:id", "application/json", (req, res) -> {
            Worker workerInstance = new Worker("su","su", 1,"su@email.com", "su");
            int workerId = Integer.parseInt(req.params("id"));
            if (workerInstance.findById(workerId) == null) {
                throw new ApiException(404, String.format("No worker with the ID: %s exists", req.params("id")));
            }
            return gson.toJson(workerInstance.findById(workerId));
        });

        //get: gets all job details posting
        get("/jobdetails", "application/json", (req, res) -> {
            JobDetail jobDetailInstance = new JobDetail("su", "su",1);
            if (jobDetailInstance.getAll().size() > 0) {
                return gson.toJson(jobDetailInstance.getAll());
            } else {
                return "{\"message\":\"I'm sorry, there are no job listings currently listed in the database.\"}";
            }
        });

        //get: gets a job details posting by its Id
        get("/jobdetails/:id", "application/json", (req, res) -> {
            JobDetail jobDetailInstance = new JobDetail("su", "su",1);
            int jobDetailId = Integer.parseInt(req.params("id"));
            if (jobDetailInstance.findById(jobDetailId) == null) {
                throw new ApiException(404, String.format("No job listing with the ID: %s exists", req.params("id")));
            }
            return gson.toJson(jobDetailInstance.findById(jobDetailId));
        });

        //get: gets all job details postings by ClientId
        get("/clients/jobdetails/:client_id", "application/json", (req, res) -> {
            JobDetail jobDetailInstance = new JobDetail("su", "su",1);
            int jobDetailClientId = Integer.parseInt(req.params("client_id"));
            if (jobDetailInstance.findById(jobDetailClientId) == null) {
                throw new ApiException(404, String.format("No job listing by client of client ID: %s exists", req.params("client_id")));
            }
            return gson.toJson(jobDetailInstance.findAllByClient(jobDetailClientId));
        });


        //UPDATE
        //post: update a client
        post("/clients/update/:id", "application/json", (req, res) ->{
            Client client = gson.fromJson(req.body(), Client.class);
            Client clientInstance = new Client("su", "su@email.com", "su");

            int clientId = client.getId();
            String clientName = client.getName();
            String clientEmail = client.getEmail();
            String clientPhone = client.getPhone();

            int idToCheck = Integer.parseInt(req.params("id"));
            Client clientToUpdate = clientInstance.findById(idToCheck);

            if (clientToUpdate == null) {
                throw new ApiException(404, String.format("No client of ID: %s exists", idToCheck));
            } else if (clientId == 0 || idToCheck != clientId){
                throw new ApiException(404, String.format("You cannot update client of client ID: %s", idToCheck));
            } else {
                client.update(clientId, clientName, clientEmail, clientPhone);
                res.status(201);
                return gson.toJson(String.format("Client of ID %s has been updated successfully", clientId));
            }
        });

        //post: update a worker
        post("/workers/update/:id", "application/json", (req, res) ->{
            Worker worker = gson.fromJson(req.body(), Worker.class);
            Worker workerInstance = new Worker("su","su", 1,"su@email.com", "su");

            int workerId = worker.getId();
            String workerName = worker.getName();
            String workerSkill = worker.getSkill();
            int workerExperience = worker.getExperience();
            String workerEmail = worker.getEmail();
            String workerPhone = worker.getPhone();

            int idToCheck = Integer.parseInt(req.params("id"));
            Worker workerToUpdate = workerInstance.findById(idToCheck);

            if( workerToUpdate == null) {
                throw new ApiException(404, String.format("No worker of ID: %s exists", idToCheck));
            } else if (workerId == 0 || idToCheck != workerId){
                throw new ApiException(404, String.format("You cannot update client of client ID: %s", idToCheck));
            } else {
                worker.update(workerId, workerName, workerSkill, workerExperience, workerEmail, workerPhone);
                res.status(201);
                return gson.toJson(String.format("Worker of ID %s has been updated successfully", workerId));
            }
        });

        //post: updates a jobdetails posting
        post("/jobdetails/update/:id", "application/json", (req, res)-> {
            JobDetail detail = gson.fromJson(req.body(), JobDetail.class);
            JobDetail jobDetailInstance = new JobDetail("su", "su",1);

            int detailId = detail.getId();
            String detailCategory = detail.getJob_category();
            String detailDetail = detail.getJob_detail();
            int detailClientId = detail.getClient_id();

            int idToCheck = Integer.parseInt(req.params("id"));
            JobDetail jobDetailToUpdate = jobDetailInstance.findById(idToCheck);
            int formerId = jobDetailToUpdate.getClient_id();

            if (jobDetailToUpdate == null) {
                throw new ApiException(404, String.format("No job detail post of ID: %s exists", idToCheck));
            } else if (detailId == 0 || idToCheck != detailId || detailClientId != formerId){
                throw new ApiException(404, String.format("You cannot update job detail post of ID: %s", idToCheck));
            } else {
                jobDetailInstance.update(detailId, detailCategory, detailDetail, detailClientId);
                res.status(201);
                return gson.toJson(String.format("Job detail post of ID %s has been updated successfully", detailId));
            }
        });


        //DELETE
        //post: delete a client
        post("/clients/delete/:id", "application/json", (req, res) ->{
            Client client = gson.fromJson(req.body(), Client.class);
            Client clientInstance = new Client("su", "su@email.com", "su");
            int clientId = client.getId();

            int idToDelete = Integer.parseInt(req.params("id"));
            Client clientToDelete = clientInstance.findById(idToDelete);
            
            if (clientToDelete == null) {
                throw new ApiException(404, String.format("No client of ID: %s exists", idToDelete));
            } else if(idToDelete != clientId){
                throw new ApiException(404, String.format("You cannot delete client of ID: %s", idToDelete));
            } else {
                client.deleteById();
                res.status(201);
                return gson.toJson(String.format("Client with ID of %s has been deleted successfully", idToDelete));
            }
        });

        //post: delete a worker
        post("/workers/delete/:id", "application/json", (req, res) ->{
            Worker worker = gson.fromJson(req.body(), Worker.class);
            Worker workerInstance = new Worker("su","su", 1,"su@email.com", "su");
            int workerId = worker.getId();

            int idToDelete = Integer.parseInt(req.params("id"));
            Worker workerToDelete = workerInstance.findById(idToDelete);

            if (workerToDelete == null) {
                throw new ApiException(404, String.format("No worker of ID: %s exists", idToDelete));
            } else if(idToDelete != workerId){
                throw new ApiException(404, String.format("You cannot delete worker of ID: %s", idToDelete));
            } else {
                worker.deleteById();
                res.status(201);
                return gson.toJson(String.format("Worker with ID of %s has been deleted successfully", workerId));
            }
        });

        //post: delete a job details posting
        post("/jobdetails/delete/:id", "application/json", (req, res) ->{
            JobDetail jobDetailInstance = new JobDetail("su", "su",1);
            JobDetail jobDetail =  gson.fromJson(req.body(), JobDetail.class);
            int detailId = jobDetail.getId();
            int detailClient = jobDetail.getClient_id();

            int idToDelete = Integer.parseInt(req.params("id"));
            JobDetail jobDetailToDelete = jobDetailInstance.findById(idToDelete);
            int clientId = jobDetailInstance.findById(idToDelete).getClient_id();
            int jobDetailIdToDelete = jobDetailInstance.findById(idToDelete).getId();

            if (jobDetailToDelete == null) {
                throw new ApiException(404, String.format("No job detail post of ID: %s exists", idToDelete));
            } else if (detailClient != clientId || detailId != jobDetailIdToDelete) {
                throw new ApiException(404, String.format("You cannot delete post for client of ID: %s", clientId));
            } else {
                jobDetail.deleteById();
                res.status(201);
                return gson.toJson(String.format("Job Detail with ID of %s has been deleted successfully", idToDelete));
            }
        });


        //FILTERS
        exception(ApiException.class, (exception, req, res) -> {
            ApiException err = exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });

        after((req, res) ->{
            res.type("application/json");
        });
    }
}