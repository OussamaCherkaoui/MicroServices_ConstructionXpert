CREATE TABLE task (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      description VARCHAR(255) NOT NULL,
                      status VARCHAR(255) NOT NULL,
                      project_id BIGINT NOT NULL
);