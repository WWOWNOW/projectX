INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, email, full_name, image_link, is_enabled, last_redaction_date, password, role_id)
VALUES (100, '0@mail.com', 'TestUser1', 'TestImage1', true, now(), '$2a$10$sas6S77dkMjtev/s.77JousVSc7hbodeq/bl7TB1tQ2ENd5eFFWQK', 100);

INSERT INTO user_entity (id, email, full_name, image_link, is_enabled, last_redaction_date, password, role_id)
VALUES (101, '1@mail.com', 'TestUser2', 'TestImage2', true, now(), '$2a$12$cMe8VHtl1XIxTFhr9aEuNOVRWDhgDYdIqFUFrMuacpoLR53euAaue', 100);

INSERT INTO chat (id, chat_type, persist_date)
VALUES (100, 0 , now());

INSERT INTO chat (id, chat_type, persist_date)
VALUES (generate_series(104, 106), 1 , now());

INSERT INTO message (id, last_redaction_date, message, persist_date, chat_id, user_sender_id)
VALUES (100, now(), 'Test message by id = 100', now(), 100, 101);

INSERT INTO message (id, last_redaction_date, message, persist_date, chat_id, user_sender_id)
VALUES (generate_series(104, 106), now(), CONCAT('Test message by id = ', generate_series(104, 106)), now(), generate_series(104, 106), 101);

INSERT INTO single_chat (chat_id, use_two_id, user_one_id, is_delete_one, is_delete_two)
VALUES (100, 101, 100, false, false);

INSERT INTO group_chat (chat_id, is_global, image_chat, title)
VALUES (generate_series(104, 106), false, CONCAT('Image for test chat ',generate_series(104, 106)),
CONCAT('Test group chat ',generate_series(104, 106)));

INSERT INTO groupchat_has_users (chat_id, user_id)
VALUES (104, 100);

INSERT INTO groupchat_has_users (chat_id, user_id)
VALUES (generate_series(104, 106), 101);