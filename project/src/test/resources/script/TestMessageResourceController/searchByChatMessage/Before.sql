INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, email, is_enabled, password, last_redaction_date, role_id)
VALUES (100, '100@mail.com', true, '$2a$12$oS58OyKQr3m4bQWFp/JstOv2BBOclFbd1cS1/FU3W0jx34Vu.n.4u', NOW(), 100);
INSERT INTO user_entity (id, email, is_enabled, password, last_redaction_date, role_id)
VALUES (101, '101@mail.com', true, '$2a$12$lEN0bVuULuMrrSz7puuUoOiJYMGNxkEtY/RNHBE7MDmIvv7sj3lJu', NOW(), 100);

INSERT INTO chat (id, chat_type)
VALUES (100, 1);
INSERT INTO chat (id, chat_type)
VALUES (101, 1);

INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (101, 'message101', NOW(), '2022-11-03T05:00:00', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (102, 'message102', NOW(), '2022-11-03T05:00:00', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (103, 'message103', NOW(), '2022-11-03T05:00:00', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (104, 'message104', NOW(), '2022-11-03T05:00:00', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (105, 'message105', NOW(), '2022-11-03T05:00:00', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (106, 'message106', NOW(), '2022-11-03T05:00:00', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (107, 'message107', NOW(), '2022-11-03T05:00:00', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (108, 'message108', NOW(), '2022-11-03T05:00:00', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (109, 'message109', NOW(), '2022-11-03T05:00:00', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (110, 'message110', NOW(), '2022-11-03T05:00:00', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (111, 'message111', NOW(), '2022-11-03T05:00:00', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (112, 'message112', NOW(), '2022-11-03T05:00:00', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (113, 'message113', NOW(), '2022-11-03T05:00:00', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (114, 'message114', NOW(), '2022-11-03T05:00:00', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (115, 'message115', NOW(), '2022-11-03T05:00:00', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (116, 'message116', NOW(), '2022-11-03T05:00:00', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (117, 'message117', NOW(), '2022-11-03T05:00:00', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (118, 'message118', NOW(), '2022-11-03T05:00:00', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (119, 'message119', NOW(), '2022-11-03T05:00:00', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (120, 'message120', NOW(), '2022-11-03T05:00:00', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (121, 'message121', NOW(), '2022-11-01T11:11:11', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (122, 'message122', NOW(), '2022-11-01T22:22:22', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (123, 'message123', NOW(), '2022-11-02T22:22:22', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (124, 'message124', NOW(), '2022-11-02T11:11:11', 100, 100);
INSERT INTO message (id, message, last_redaction_date, persist_date, user_sender_id, chat_id)
VALUES (125, 'message125', NOW(), '2022-11-03T00:00:00', 100, 100);


