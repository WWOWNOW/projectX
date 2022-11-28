TRUNCATE TABLE role CASCADE;

INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, city, email, full_name, image_link, is_enabled, last_redaction_date, password, role_id)
VALUES (100, 'City 100', '100@mail.com', 'FullName 100', 'LinkImage 100', true, NOW(),
        '$2a$10$sas6S77dkMjtev/s.77JousVSc7hbodeq/bl7TB1tQ2ENd5eFFWQK', 100);