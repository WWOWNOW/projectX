TRUNCATE TABLE role CASCADE;

INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, email,is_deleted, is_enabled, last_redaction_date,persist_date, password, role_id)
VALUES (100, '0@mail.com',false, true,now(),'1999-03-16', '$2a$10$sas6S77dkMjtev/s.77JousVSc7hbodeq/bl7TB1tQ2ENd5eFFWQK',100);
INSERT INTO user_entity (id, email,is_deleted, is_enabled, last_redaction_date,persist_date, password, role_id)
VALUES(101, '1@mail.com',true, true,now(),now(), 'pass1', 100);