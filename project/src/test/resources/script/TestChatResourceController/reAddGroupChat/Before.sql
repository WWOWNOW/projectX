INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, email, full_name, image_link, is_enabled, last_redaction_date, password, role_id)
VALUES (100, '0@mail.com', 'TestUser1', 'TestImage1', true, now(), '$2a$10$sas6S77dkMjtev/s.77JousVSc7hbodeq/bl7TB1tQ2ENd5eFFWQK', 100);

INSERT INTO user_entity (id, email, full_name, image_link, is_enabled, last_redaction_date, password, role_id)
VALUES (101, '1@mail.com', 'TestUser2', 'TestImage2', true, now(), '$2a$12$cMe8VHtl1XIxTFhr9aEuNOVRWDhgDYdIqFUFrMuacpoLR53euAaue', 100);

INSERT INTO chat (id, chat_type, persist_date)
VALUES (100, 1 , now());

INSERT INTO group_chat (chat_id, is_global, image_chat, title)
VALUES (100, false, 'Image for test chat ', 'Some group chat 3');

INSERT INTO groupchat_has_users (chat_id, user_id)
VALUES (100, 100);

INSERT INTO groupchat_has_users (chat_id, user_id)
VALUES (100, 101);