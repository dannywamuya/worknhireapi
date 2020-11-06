CREATE DATABASE worknhire;

\c worknhire;

CREATE TABLE workers (
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    skill VARCHAR,
    experience INTEGER,
    email VARCHAR,
    phone VARCHAR
);

CREATE TABLE clients (
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    email VARCHAR,
    phone VARCHAR
);

CREATE TABLE job_details (
    id SERIAL PRIMARY KEY,
    job_category VARCHAR,
    job_detail VARCHAR,
    client_id INTEGER
);

CREATE DATABASE worknhire_test WITH TEMPLATE worknhire;









--
--CREATE TABLE job_applications (
--    id INTEGER PRIMARY KEY,
--    job_details_id INTEGER,
--    job_details_category VARCHAR,
--    job_details_detail VARCHAR,
--    job_client_id INTEGER,
--    worker_id INTEGER,
--    worker_name VARCHAR,
--    worker_skill VARCHAR,
--    worker_experience_years INTEGER
--);
--

--
--
--
--


--
--SET MODE PostgreSQL;
--
--CREATE TABLE IF NOT EXISTS workers (
--    id int PRIMARY KEY auto_increment,
--    name VARCHAR,
--    skill VARCHAR,
--    experience INTEGER,
--    email VARCHAR,
--    phone VARCHAR
--);
--
--CREATE TABLE IF NOT EXISTS clients (
--    id int PRIMARY KEY auto_increment,
--    name VARCHAR,
--    email VARCHAR,
--    phone VARCHAR
--);
--
--CREATE TABLE IF NOT EXISTS job_details (
--    id int PRIMARY KEY auto_increment,
--    job_category VARCHAR,
--    job_detail VARCHAR,
--    client_id INTEGER
--);
--
--CREATE TABLE IF NOT EXISTS job_applications (
--    id int PRIMARY KEY auto_increment,
--    job_details_id INTEGER,
--    job_details_category VARCHAR,
--    job_details_detail VARCHAR,
--    job_client_id INTEGER,
--    worker_id INTEGER,
--    worker_name VARCHAR,
--    worker_skill VARCHAR,
--    worker_experience_years INTEGER
--);