INSERT INTO position_dict (name, description, version) VALUES ('Инженер', null, 0);
INSERT INTO position_dict (name, description, version) VALUES ('Ведущий инженер', 'Ведущий инженер программист', 0);
INSERT INTO position_dict (name, description, version) VALUES ('Главный инженер', null, 0);
INSERT INTO position_dict (name, description, version) VALUES ('Начальник отдела', null, 0);

INSERT INTO employee (name, second_name, middle_name, sex, birthday, phone_number, email,
                      employment_day, dismissal_day, position_id, salary, chief, version)
VALUES ('Иван', 'Иванов', 'Иванович', true, '1970-11-05', '81234567899', 'Ivanov@ya.ru',
        '2019-11-27', null, 1, 100000, false, 0);

INSERT INTO employee (name, second_name, middle_name, sex, birthday, phone_number, email,
                      employment_day, dismissal_day, position_id, salary, chief, version)
VALUES ('Петр', 'Петров', 'Петрович', true, '1971-10-05', '81237667899', 'Petrov@ya.ru',
        '2019-10-20', '2019-11-10', 2, 200000, false, 0);

INSERT INTO employee (name, second_name, middle_name, sex, birthday, phone_number, email,
                      employment_day, dismissal_day, position_id, salary, chief, version)
VALUES ('Сергей', 'Сергеев', 'Сергеевич', true, '1975-01-05', '8123346799', 'Sergeyev@ya.ru',
        '2017-10-10', null, 4, 500000, true, 0);

INSERT INTO employee (name, second_name, middle_name, sex, birthday, phone_number, email,
                      employment_day, dismissal_day, position_id, salary, chief, version)
VALUES ('Иван', 'Сергеев', 'Петрович', true, '1980-01-08', '81234656789', 'aaaa@ya.ru',
        '2016-05-09', null, 2, 300000, false, 0);

INSERT INTO employee (name, second_name, middle_name, sex, birthday, phone_number, email,
                      employment_day, dismissal_day, position_id, salary, chief, version)
VALUES ('Ольга', 'Иванова', 'Петровна', false, '1985-09-08', '81344656789', 'olga@ya.ru',
        '2016-08-19', null, 2, 200000, false, 0);