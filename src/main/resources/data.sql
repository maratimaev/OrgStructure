INSERT INTO position_dict (name, description, version) VALUES ('Инженер', null, 0);
INSERT INTO position_dict (name, description, version) VALUES ('Ведущий инженер', 'Ведущий инженер программист', 0);
INSERT INTO position_dict (name, description, version) VALUES ('Главный инженер', null, 0);
INSERT INTO position_dict (name, description, version) VALUES ('Начальник отдела', null, 0);

INSERT INTO department (name, creation_day, head_department_id, version) VALUES ('Отдел планирования', '2018-05-06', null, 0);  --1
INSERT INTO department (name, creation_day, head_department_id, version) VALUES ('Бухгалтерия', '2018-04-06', 1, 0);            --2
INSERT INTO department (name, creation_day, head_department_id, version) VALUES ('Отдел разраработки', '2018-07-16', 2, 0);     --3
INSERT INTO department (name, creation_day, head_department_id, version) VALUES ('Отдел тестирования', '2019-01-24', 2, 0);     --4
INSERT INTO department (name, creation_day, head_department_id, version) VALUES ('Отдел тестирования1', '2019-02-21', 3, 0);    --5
INSERT INTO department (name, creation_day, head_department_id, version) VALUES ('Отдел тестирования2', '2019-03-22', 3, 0);    --6
INSERT INTO department (name, creation_day, head_department_id, version) VALUES ('Отдел тестирования3', '2019-04-23', 5, 0);    --7

INSERT INTO employee (name, second_name, middle_name, sex, birthday, phone_number, email,
                      employment_day, dismissal_day, position_id, salary, chief, department_id, version)
VALUES ('Иван', 'Иванов', 'Иванович', true, '1970-11-05', '81234567899', 'Ivanov@ya.ru',
        '2019-11-27', null, 1, 100000, false, 1, 0);

INSERT INTO employee (name, second_name, middle_name, sex, birthday, phone_number, email,
                      employment_day, dismissal_day, position_id, salary, chief, department_id, version)
VALUES ('Петр', 'Петров', 'Петрович', true, '1971-10-05', '81237667899', 'Petrov@ya.ru',
        '2019-10-20', '2019-11-10', 2, 200000, false, 2, 0);

INSERT INTO employee (name, second_name, middle_name, sex, birthday, phone_number, email,
                      employment_day, dismissal_day, position_id, salary, chief, department_id, version)
VALUES ('Сергей', 'Сергеев', 'Сергеевич', true, '1975-01-05', '8123346799', 'Sergeyev@ya.ru',
        '2017-10-10', null, 4, 500000, true, 2, 0);

INSERT INTO employee (name, second_name, middle_name, sex, birthday, phone_number, email,
                      employment_day, dismissal_day, position_id, salary, chief, department_id, version)
VALUES ('Иван', 'Сергеев', 'Петрович', true, '1980-01-08', '81234656789', 'aaaa@ya.ru',
        '2016-05-09', null, 2, 300000, false, 3, 0);

INSERT INTO employee (name, second_name, middle_name, sex, birthday, phone_number, email,
                      employment_day, dismissal_day, position_id, salary, chief, department_id, version)
VALUES ('Ольга', 'Иванова', 'Петровна', false, '1985-09-08', '81344656789', 'olga@ya.ru',
        '2016-08-19', null, 2, 200000, false, 4, 0);