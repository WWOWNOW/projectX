TRUNCATE TABLE role CASCADE;
TRUNCATE TABLE user_entity CASCADE;
TRUNCATE TABLE chat CASCADE;
TRUNCATE TABLE group_chat CASCADE;
TRUNCATE TABLE groupchat_has_users CASCADE;
TRUNCATE TABLE message CASCADE;
TRUNCATE TABLE message_star CASCADE;

INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, email, is_enabled, nickname, image_link, last_redaction_date, password, role_id)
VALUES (100, '100@mail.com', true, 'user 100','image 100',now(), '$2a$12$foEcElkFYSO8wbNnWcP74OmUGYri6SHfM8qtHnc6iGv0wblx20mhy', 100);
INSERT INTO user_entity (id, email, is_enabled, nickname, image_link, last_redaction_date, password, role_id)
VALUES (101, '1@mail.com', true, 'user 100','image 100',now(), '$2a$12$iSHqfk6qIPpldQysy3XOaOINRmUrY5rumfJLSgBc07zkJ.CYc36Ry', 100);

INSERT INTO chat (id, chat_type, persist_date)
VALUES (100, 1,'2022-10-27');

INSERT INTO group_chat (chat_id, is_global, image_chat, title, author_id)
VALUES (100, true, 'image 100', 'some title', 100);

INSERT INTO groupchat_has_users (chat_id, user_id)
VALUES (100,100);


INSERT INTO message (id, last_redaction_date, message, chat_id, user_sender_id,persist_date)
VALUES (100,now(),'message100',100,100,'2022-10-27');
INSERT INTO message (id, last_redaction_date, message, chat_id, user_sender_id,persist_date)
VALUES (101,now(),'message100',100,101,'2022-10-27');


