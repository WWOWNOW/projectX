TRUNCATE TABLE role CASCADE;

INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, city, email, full_name, image_link, is_enabled, last_redaction_date, password, role_id)
VALUES (GENERATE_SERIES(100, 120), CONCAT('City ', GENERATE_SERIES(100, 120)), CONCAT(GENERATE_SERIES(100, 120), '@mail.com'),
        CONCAT('FullName ', GENERATE_SERIES(100, 120)), CONCAT('LinkImage ', GENERATE_SERIES(100, 120)), true, NOW(),
        '$2a$10$sas6S77dkMjtev/s.77JousVSc7hbodeq/bl7TB1tQ2ENd5eFFWQK', 100);