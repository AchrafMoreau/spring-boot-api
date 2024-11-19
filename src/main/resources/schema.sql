CREATE TABLE IF NOT EXISTS Run (
    id INT NOT NULL,
    title VARCHAR(230) NOT NULL,
    start_on TIMESTAMP NOT NULL,
    complete_on TIMESTAMP NOT NULL,
    miles INT NOT NULL,
    location VARCHAR(30) NOT NULL,
    version INT,
    PRIMARY KEY(id)
);