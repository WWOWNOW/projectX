INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, email, is_enabled, last_redaction_date, password, role_id)
VALUES (100, '0@mail.com', true, now(), '$2a$10$sas6S77dkMjtev/s.77JousVSc7hbodeq/bl7TB1tQ2ENd5eFFWQK', 100);

INSERT INTO user_entity (id, email, is_enabled, last_redaction_date, password, role_id)
VALUES (101, '1@mail.com', true, now(), '$2a$12$cMe8VHtl1XIxTFhr9aEuNOVRWDhgDYdIqFUFrMuacpoLR53euAaue', 100);

INSERT INTO question (id, description, last_redaction_date, title, user_id)
VALUES (100, 'Test question', now(), 'Question Title 1', 100);

INSERT INTO answer (id, date_accept_time, html_body, is_deleted, is_deleted_by_moderator, is_helpful,
persist_date, update_date, question_id, user_id, edit_moderator_id)
VALUES (100, now(), 'Answer for test question', false, false, true, now(), now(), 100, 100, null);

INSERT INTO comment (id, comment_type, last_redaction_date, persist_date, text, user_id)
VALUES (generate_series(100, 121), 1, now(),
generate_series ('2022-01-01 00:00:00', '2022-01-22 00:00:00', interval '1 day'),
CONCAT('Test comment by answerId = ', generate_series(100, 121)),100);

INSERT INTO comment_answer (comment_id, answer_id)
VALUES (generate_series(100, 121), 100);

INSERT INTO reputation (id, "count", persist_date, type, answer_id, author_id, question_id, sender_id)
VALUES (100, 10, now(), 3, null, 100, 100, 101);

INSERT INTO reputation (id, "count", persist_date, type, answer_id, author_id, question_id, sender_id)
VALUES (101, 10, now(), 3, null, 100, 100, 101);

INSERT INTO reputation (id, "count", persist_date, type, answer_id, author_id, question_id, sender_id)
VALUES (102, -5, now(), 3, null, 100, 100, 101);