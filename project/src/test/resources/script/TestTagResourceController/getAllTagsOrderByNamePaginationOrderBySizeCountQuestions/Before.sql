INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, email, is_enabled, last_redaction_date, password, role_id)
VALUES (100, '0@mail.com', true, now(), '$2a$10$sas6S77dkMjtev/s.77JousVSc7hbodeq/bl7TB1tQ2ENd5eFFWQK', 100);

INSERT INTO question (id, description, last_redaction_date, is_deleted, persist_date, title, user_id)
values (100, 'Question delete 0', now(), false, now(), 'Title 0',100),
       (101, 'Question delete 0', now(), false, now(), 'Title 0',100);

INSERT INTO tag (id, description, name, persist_date)
VALUES (100, 'Tags Message 1', 'Java', '2022-11-05T00:00:00'),
       (101, 'Tags Message 2', 'Java', '2022-11-04T00:00:00');

INSERT INTO question_has_tag (question_id, tag_id)
VALUES (100, 100),
       (101, 101),
       (101, 101);

