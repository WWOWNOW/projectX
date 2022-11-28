
INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, email, is_enabled, last_redaction_date, password, role_id)
VALUES (100, '0@mail.com', true, now(), '$2a$10$sas6S77dkMjtev/s.77JousVSc7hbodeq/bl7TB1tQ2ENd5eFFWQK', 100);

INSERT INTO question (id, description, last_redaction_date, is_deleted, persist_date, title, user_id)
values (100, 'Question delete 0', now(), true, '2022-11-02T00:00:00', 'Title 0',100),
       (101, 'Question delete 1', now(), true, '2022-11-03T00:00:00', 'Title 1',100),
       (102, 'Question delete 2', now(), true, '2022-11-04T00:00:00', 'Title 2',100);

insert into answer (id, date_accept_time, html_body, is_deleted, is_deleted_by_moderator, is_helpful, persist_date
                   , update_date, question_id, user_id, edit_moderator_id)
values (100, now(), 'text', true, false, false, now(), now(), 100, 100, 100),
       (101, now(), 'text', true, false, false, now(), now(), 100, 100, 100);
