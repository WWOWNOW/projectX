
INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, email, is_enabled, last_redaction_date, password, role_id)
VALUES (100, '0@mail.com', true, now(), '$2a$10$sas6S77dkMjtev/s.77JousVSc7hbodeq/bl7TB1tQ2ENd5eFFWQK', 100);

INSERT INTO question (id, description, last_redaction_date, is_deleted, persist_date, title, user_id)
values (100, 'Question delete 0', now(), true, now(), 'Title',100);

insert into reputation(id, count, persist_date, type, author_id, question_id, sender_id)
values (100, 5, '2022-11-04T00:00:00', 3, 100, 100, 100),
       (101, 5, '2022-11-03T00:00:00', 3, 100, 100, 100),
       (102, -15, '2022-11-02T00:00:00', 3, 100, 100, 100)