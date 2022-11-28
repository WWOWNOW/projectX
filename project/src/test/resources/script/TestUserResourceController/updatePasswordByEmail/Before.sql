INSERT INTO role
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, email, is_enabled, password, last_redaction_date, role_id)
VALUES (100, '100@mail.com', true, '$2a$10$sas6S77dkMjtev/s.77JousVSc7hbodeq/bl7TB1tQ2ENd5eFFWQK', NOW(), 100);
INSERT INTO user_entity (id, email, is_enabled, password, last_redaction_date, role_id)
VALUES (101, '101@mail.com', true, '$2a$12$zGzx/WO7GmNgsLeJ1qQrTeroRDQw5bsXVvIQJUxOy.oMwP5tbX0Mq', NOW(), 100);
