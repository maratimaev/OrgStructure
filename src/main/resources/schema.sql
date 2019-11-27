DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS position_dict;
DROP TABLE IF EXISTS department;
CREATE TABLE IF NOT EXISTS position_dict (
                                             id                  SERIAL PRIMARY KEY,
                                             name                varchar(100) NOT NULL,
                                             description         varchar(500),
                                             version             integer
);

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
                                          position_id         int REFERENCES position_dict(id),
                                          salary              numeric NOT NULL,
                                          chief               boolean DEFAULT FALSE,
                                          version             integer
);

CREATE TABLE IF NOT EXISTS department (
                                        id                  SERIAL PRIMARY KEY,
                                        name                varchar(100) NOT NULL,
                                        creation_day        date NOT NULL,
                                        version             integer
);

