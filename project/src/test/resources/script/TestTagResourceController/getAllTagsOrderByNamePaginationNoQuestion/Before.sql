INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, email, is_enabled, last_redaction_date, password, role_id)
VALUES (100, '0@mail.com', true, now(), '$2a$10$sas6S77dkMjtev/s.77JousVSc7hbodeq/bl7TB1tQ2ENd5eFFWQK', 100);

INSERT INTO tag (id, description, name, persist_date)
VALUES (100, 'Tags Message 1', 'Java', '2022-11-05T00:00:00');

