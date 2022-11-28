INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, email, is_enabled, is_deleted, last_redaction_date, password, role_id)
VALUES (100, '0@mail.com', true, true, now(), '$2a$10$sas6S77dkMjtev/s.77JousVSc7hbodeq/bl7TB1tQ2ENd5eFFWQK', 100);

INSERT INTO question (id, description, last_redaction_date, is_deleted, persist_date, title, user_id)
values (100, 'Question delete 0', now(), true, now(), 'Title',100);

insert into answer(id, date_accept_time, html_body, is_deleted, is_deleted_by_moderator, is_helpful
                  , persist_date, update_date, question_id, user_id)
values (100, now(), 'HTML', false, false, true, now(), now(), 100, 100),
       (101, now(), 'HTML', false, false, true, now(), now(), 100, 100);