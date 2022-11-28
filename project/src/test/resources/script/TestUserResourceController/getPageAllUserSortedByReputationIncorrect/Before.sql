TRUNCATE TABLE role CASCADE;
TRUNCATE  TABLE reputation CASCADE;

INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');
INSERT INTO user_entity (id, email,full_name, is_deleted,is_enabled, last_redaction_date, password, role_id)
VALUES ( 100,'0@mail.com','user0',false, true,now(), '$2a$10$sas6S77dkMjtev/s.77JousVSc7hbodeq/bl7TB1tQ2ENd5eFFWQK',100);
INSERT INTO user_entity (id, email, is_deleted, is_enabled, last_redaction_date, password, role_id)
VALUES(101, '1@mail.com',false, true,now(), 'pass1', 100);
INSERT INTO user_entity (id, email, is_deleted,is_enabled, last_redaction_date, password, role_id)
VALUES(102, '2@mail.com',false, true,now(), 'pass1', 100);
INSERT INTO user_entity (id, email, is_deleted,is_enabled, last_redaction_date, password, role_id)
VALUES(103, '3@mail.com',false, true,now(), 'pass1', 100);

INSERT INTO reputation(id, count, persist_date, type, author_id)
VALUES (100,-5,now(),3,100);
INSERT INTO reputation(id, count, persist_date, type, author_id)
VALUES (101,10,now(),3,101);
INSERT INTO reputation(id, count, persist_date, type, author_id)
VALUES (102,0,now(),3,102);
INSERT INTO reputation(id, count, persist_date, type, author_id)
VALUES (103,3,now(),3,103);