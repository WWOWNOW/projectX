INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, email, is_enabled, last_redaction_date, password, role_id)
VALUES (100, '0@mail.com', true, now(), '$2a$10$sas6S77dkMjtev/s.77JousVSc7hbodeq/bl7TB1tQ2ENd5eFFWQK', 100);

INSERT INTO question (id, description, last_redaction_date, title, user_id)
VALUES (100, 'Test question', now(), 'Question Title 1', 100);

INSERT INTO answer (id, date_accept_time, html_body, is_deleted, is_deleted_by_moderator, is_helpful,
persist_date, update_date, question_id, user_id, edit_moderator_id)
VALUES (100, now(), 'Answer for test question', false, false, true, now(), now(), 100, 100, null);

INSERT INTO comment (id, comment_type, text, user_id)
VALUES (generate_series(100, 102), 1, CONCAT('Test comment by answerId = ', generate_series(100, 102)),100);

INSERT INTO comment_answer (comment_id, answer_id)
VALUES (generate_series(100, 102), 100);