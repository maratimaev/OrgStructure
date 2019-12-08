DROP TABLE IF EXISTS salary;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS position;
DROP TABLE IF EXISTS department;
CREATE TABLE IF NOT EXISTS position (
                                          id                  SERIAL PRIMARY KEY,
                                          name                varchar(100) NOT NULL,
                                          description         varchar(500),
                                          version             integer
);

CREATE TABLE IF NOT EXISTS department (
                                          id                  SERIAL PRIMARY KEY,
                                          name                varchar(100) NOT NULL,
                                          creation_day        date NOT NULL,
                                          head_department_id  integer,
                                          version             integer
);
ALTER TABLE department ADD FOREIGN KEY (head_department_id) REFERENCES department(id);
CREATE INDEX IX_head_department_id ON department (head_department_id);

CREATE TABLE employee (
                                          id                  SERIAL PRIMARY KEY,
                                          name                varchar(100) NOT NULL,
                                          second_name         varchar(100) NOT NULL,
                                          middle_name         varchar(100),
                                          sex                 boolean NOT NULL,
                                          birthday            date NOT NULL,
                                          phone_number        varchar(11) NOT NULL,
                                          email               varchar(100) NOT NULL,
                                          employment_day      date NOT NULL,
                                          dismissal_day       date,
                                          salary              numeric NOT NULL,
                                          chief               boolean DEFAULT FALSE,
                                          position_id         integer not null,
                                          department_id       integer not null,
                                          version             integer
);
ALTER TABLE employee ADD FOREIGN KEY (position_id) REFERENCES position(id);
CREATE INDEX IX_position_id ON employee (position_id);
ALTER TABLE employee ADD FOREIGN KEY (department_id) REFERENCES department(id);
CREATE INDEX IX_department_id ON employee (department_id);

CREATE TABLE salary
(
                                          id            SERIAL PRIMARY KEY,
                                          department_id integer not null,
                                          fund_salary   numeric NOT NULL
);
ALTER TABLE salary ADD FOREIGN KEY (department_id) REFERENCES department(id);
CREATE INDEX IX_salary_department_id ON salary (department_id);