TRUNCATE TABLE role CASCADE;
TRUNCATE TABLE comment CASCADE;

INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, email, is_enabled, full_name, last_redaction_date, password, role_id)
VALUES (100, '0@mail.com', true, 'user 100',now(), '$2a$10$sas6S77dkMjtev/s.77JousVSc7hbodeq/bl7TB1tQ2ENd5eFFWQK', 100);

INSERT INTO user_entity (id, email, is_enabled, full_name, last_redaction_date, password, role_id)
VALUES (101, '1@mail.com', true, 'user 101',now(), '$2a$12$cMe8VHtl1XIxTFhr9aEuNOVRWDhgDYdIqFUFrMuacpoLR53euAaue', 100);

INSERT INTO question (id, description, last_redaction_date, title, user_id)
VALUES (100, 'Question Description 1', now(), 'Question Title 1', 100);

INSERT INTO comment (id, comment_type, text, user_id)
VALUES (generate_series(100, 121), 1, CONCAT('comment ', generate_series(100, 121)), 100);

INSERT INTO comment_question (comment_id, question_id)
VALUES (generate_series(100, 121), 100);

INSERT INTO reputation (id, "count", persist_date, type, answer_id, author_id, question_id, sender_id)
VALUES (100, 10, now(), 3, null, 100, 100, 101);

INSERT INTO reputation (id, "count", persist_date, type, answer_id, author_id, question_id, sender_id)
VALUES (101, 10, now(), 3, null, 100, 100, 101);

INSERT INTO reputation (id, "count", persist_date, type, answer_id, author_id, question_id, sender_id)
VALUES (102, -5, now(), 3, null, 100, 100, 101);
