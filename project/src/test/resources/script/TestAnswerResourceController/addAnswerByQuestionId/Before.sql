INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, email, full_name, image_link, is_enabled, last_redaction_date, password, role_id)
VALUES (100, '100@mail.com', 'fullName100', 'imageLink100', true, now(), '$2a$12$foEcElkFYSO8wbNnWcP74OmUGYri6SHfM8qtHnc6iGv0wblx20mhy', 100);

INSERT INTO question (id, description, last_redaction_date, title, user_id)
VALUES (100, 'Question Description 100', now(), 'Question Title 100', 100);

