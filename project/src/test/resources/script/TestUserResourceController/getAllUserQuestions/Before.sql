INSERT INTO role
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, email, is_enabled, password, last_redaction_date, role_id)
VALUES (100, '100@mail.com', true, '$2a$10$sas6S77dkMjtev/s.77JousVSc7hbodeq/bl7TB1tQ2ENd5eFFWQK', NOW(), 100);

INSERT INTO question (id, title, description, user_id, last_redaction_date)
VALUES (100, 'Title 100', 'Description 100', 100, NOW());
INSERT INTO question (id, title, description, user_id, last_redaction_date)
VALUES (101, 'Title 101', 'Description 101', 100, NOW());
