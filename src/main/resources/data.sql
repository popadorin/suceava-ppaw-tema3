DROP TABLE IF EXISTS test_entity;

CREATE TABLE test_entity (
                            id INT AUTO_INCREMENT  PRIMARY KEY,
                            name VARCHAR(250) NOT NULL
);

INSERT INTO test_entity (name) VALUES
('Aliona'),
('Bill');