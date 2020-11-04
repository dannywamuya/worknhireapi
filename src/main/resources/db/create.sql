SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS workers (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    skill VARCHAR,
    experience INTEGER,
    email VARCHAR,
    phone VARCHAR
);

CREATE TABLE IF NOT EXISTS clients (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    email VARCHAR,
    phone VARCHAR
);

CREATE TABLE IF NOT EXISTS job_details (
    id int PRIMARY KEY auto_increment,
    job_category VARCHAR,
    job_detail VARCHAR,
    client_id INTEGER
);

CREATE TABLE IF NOT EXISTS job_applications (
    id int PRIMARY KEY auto_increment,
    job_details_id INTEGER,
    job_details_category VARCHAR,
    job_details_detail VARCHAR,
    job_client_id INTEGER,
    worker_id INTEGER,
    worker_name VARCHAR,
    worker_skill VARCHAR,
    worker_experience_years INTEGER
);
