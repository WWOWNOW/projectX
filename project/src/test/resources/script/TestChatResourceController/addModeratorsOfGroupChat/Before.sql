TRUNCATE TABLE role CASCADE;
TRUNCATE TABLE user_entity CASCADE;
TRUNCATE TABLE chat CASCADE;
TRUNCATE TABLE group_chat CASCADE;
TRUNCATE TABLE groupchat_has_users CASCADE;
TRUNCATE TABLE group_chat_moderator CASCADE;

INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, email, is_enabled, password, last_redaction_date, role_id)
VALUES (100, '100@mail.com', true, '$2a$12$oS58OyKQr3m4bQWFp/JstOv2BBOclFbd1cS1/FU3W0jx34Vu.n.4u', NOW(), 100);
INSERT INTO user_entity (id, email, is_enabled, password, last_redaction_date, role_id)
VALUES (200, '200@mail.com', true, '$2a$12$8sMqAxAsfzttoX3Db/tJJOJbByAY5B5aZQX0DGujNHdo4PeaoEFgy', NOW(), 100);

INSERT INTO chat (id, chat_type)
VALUES (100, 1);
INSERT INTO chat (id, chat_type)
VALUES (200, 1);

INSERT INTO group_chat (chat_id, is_global, author_id, image_chat)
VALUES (100, false, 100, 'image');
INSERT INTO group_chat (chat_id, is_global, author_id, image_chat)
VALUES (200, false, 200, 'image');

INSERT INTO groupchat_has_users (chat_id, user_id)
VALUES (100, 100);
INSERT INTO groupchat_has_users (chat_id, user_id)
VALUES (200, 200);



