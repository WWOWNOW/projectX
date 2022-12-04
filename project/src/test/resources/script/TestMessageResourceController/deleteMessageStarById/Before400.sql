TRUNCATE TABLE role CASCADE;
TRUNCATE TABLE user_entity CASCADE;
TRUNCATE TABLE message CASCADE;
TRUNCATE TABLE message_star CASCADE;

INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, email, is_enabled, nickname, image_link, last_redaction_date, password, role_id)
VALUES (100, '100@mail.com', true, 'user 100','image 100',now(), '$2a$12$foEcElkFYSO8wbNnWcP74OmUGYri6SHfM8qtHnc6iGv0wblx20mhy', 100);
INSERT INTO user_entity (id, email, is_enabled, nickname, image_link, last_redaction_date, password, role_id)
VALUES (101, '1@mail.com', true, 'user 100','image 100',now(), '$2a$12$NkyfI/0umQiQRH.HbP15aud1QZsSEHgGTZRxU383/awKn72ZWHECi', 100);

INSERT INTO chat (id)
VALUES (100);

INSERT INTO message (id, last_redaction_date, message, chat_id, user_sender_id,persist_date)
VALUES (100,now(),'message100',100,100,'2022-10-27');
INSERT INTO message (id, last_redaction_date, message, chat_id, user_sender_id,persist_date)
VALUES (101,now(),'message100',100,101,'2022-10-27');

INSERT INTO message_star (id, persist_date, message_id, user_id)
VALUES (100, now(), 100, 100);
INSERT INTO message_star (id, persist_date, message_id, user_id)
VALUES (101, now(), 101, 101);
