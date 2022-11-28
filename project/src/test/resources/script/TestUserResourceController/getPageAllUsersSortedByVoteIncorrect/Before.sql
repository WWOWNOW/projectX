TRUNCATE TABLE role CASCADE;
TRUNCATE  TABLE reputation CASCADE;
TRUNCATE  TABLE votes_on_questions CASCADE;
TRUNCATE  TABLE question CASCADE;
TRUNCATE TABLE  votes_on_answers CASCADE;
TRUNCATE TABLE answer CASCADE;

INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');


INSERT INTO user_entity (id, email,full_name, is_enabled, last_redaction_date, password, role_id)
VALUES ( 100,'0@mail.com','user0', true,now(), '$2a$10$sas6S77dkMjtev/s.77JousVSc7hbodeq/bl7TB1tQ2ENd5eFFWQK',100);
INSERT INTO user_entity (id, email, is_enabled, last_redaction_date, password, role_id)
VALUES(101, '1@mail.com', true,now(), 'pass1', 100);
INSERT INTO user_entity (id, email, is_enabled, last_redaction_date, password, role_id)
VALUES(102, '2@mail.com', true,now(), 'pass1', 100);
INSERT INTO user_entity (id, email, is_enabled, last_redaction_date, password, role_id)
VALUES(103, '3@mail.com', true,now(), 'pass1', 100);


INSERT INTO question(id, description, is_deleted, last_redaction_date, persist_date, title, user_id)
VALUES (100,'Question Description 0',false,now(),now(),'Question Title 0',100);
INSERT INTO votes_on_questions(id, persist_date, vote,question_id, user_id)
VALUES (100,now(),'DOWN_VOTE',100,100);
INSERT INTO answer(id, date_accept_time, html_body, is_deleted, is_deleted_by_moderator, is_helpful, persist_date, update_date, question_id, user_id)
VALUES (100,now(),'html_body0',false,false,true,now(),now(),100,100);
INSERT INTO votes_on_answers(id, persist_date, vote, answer_id, user_id)
VALUES (100,now(),'DOWN_VOTE',100,100);

INSERT INTO question(id, description, is_deleted, last_redaction_date, persist_date, title, user_id)
VALUES (101,'Question Description 1',false,now(),now(),'Question Title 1',102);
INSERT INTO votes_on_questions(id, persist_date, vote,question_id, user_id)
VALUES (101,now(),'UP_VOTE',101,102);
INSERT INTO answer(id, date_accept_time, html_body, is_deleted, is_deleted_by_moderator, is_helpful, persist_date, update_date, question_id, user_id)
VALUES (101,now(),'html_body0',false,false,true,now(),now(),101,102);
INSERT INTO votes_on_answers(id, persist_date, vote, answer_id, user_id)
VALUES (101,now(),'UP_VOTE',101,102);

INSERT INTO question(id, description, is_deleted, last_redaction_date, persist_date, title, user_id)
VALUES (102,'Question Description 2',false,now(),now(),'Question Title 2',101);
INSERT INTO votes_on_questions(id, persist_date, vote,question_id, user_id)
VALUES (102,now(),'UP_VOTE',102,101);

INSERT INTO question(id, description, is_deleted, last_redaction_date, persist_date, title, user_id)
VALUES (103,'Question Description 3',false,now(),now(),'Question Title 3',103);
INSERT INTO votes_on_questions(id, persist_date, vote,question_id, user_id)
VALUES (103,now(),'DOWN_VOTE',103,103);

INSERT INTO reputation(id, persist_date, type, author_id,question_id,answer_id)
VALUES (100,now(),3,100,100,100);
INSERT INTO reputation(id,  persist_date, type, author_id,question_id)
VALUES (101,now(),3,101,102);
INSERT INTO reputation(id,  persist_date, type, author_id,question_id,answer_id)
VALUES (102,now(),3,102,101,101);
INSERT INTO reputation (id,  persist_date, type, author_id,question_id)
VALUES (103,now(),3,103,103);