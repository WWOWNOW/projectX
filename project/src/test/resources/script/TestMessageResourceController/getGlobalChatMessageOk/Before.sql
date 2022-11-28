
INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, email, is_enabled, last_redaction_date, password, role_id)
VALUES (100, '0@mail.com', true, now(), '$2a$10$sas6S77dkMjtev/s.77JousVSc7hbodeq/bl7TB1tQ2ENd5eFFWQK', 100);

INSERT INTO chat (id, chat_type, persist_date)
values (100, 1, now());

INSERT INTO message (id, last_redaction_date, message, persist_date, chat_id, user_sender_id)
VALUES (101, now(), 'Global test message 1', '2022.11.01', 100, 100),
       (100, now(), 'Global test message 2', '2022.10.31', 100, 100);

INSERT INTO group_chat (chat_id, is_global, image_chat, title)
VALUES (100, true, 'Some group chat image 1', 'Some group chat 1');
