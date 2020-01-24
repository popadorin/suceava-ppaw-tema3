DROP TABLE IF EXISTS test_entity;
DROP TABLE IF EXISTS mark;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS subject;

-- creation of tables
CREATE TABLE test_entity
(
  id   INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

CREATE TABLE student
(
  id         INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name  VARCHAR(250) NOT NULL
);

CREATE TABLE subject
(
  id   INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

CREATE TABLE mark
(
  id         INT AUTO_INCREMENT PRIMARY KEY,
  value      INT NOT NULL,
  student_id INT NOT NULL,
  subject_id INT NOT NULL
);

-- insertions of data
INSERT INTO test_entity (name)
VALUES ('Aliona'),
       ('Bill');

INSERT INTO student (first_name, last_name)
VALUES ('Alexandru', 'Zanudus'),
       ('Daniela', 'Agresivus'),
       ('Anna', 'Jucausus'),
       ('Dorin', 'Maladetsus')
;

INSERT INTO subject (name)
VALUES ('OOP'),
       ('APA'),
       ('BD')
;

INSERT INTO mark (student_id, subject_id, value)
VALUES (1, 1, 8),
       (1, 2, 7),
       (2, 3, 4),
       (4, 1, 10),
       (4, 3, 8)
;
