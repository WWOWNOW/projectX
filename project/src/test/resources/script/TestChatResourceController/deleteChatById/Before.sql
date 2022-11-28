INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, email, is_enabled, password, last_redaction_date, role_id)
VALUES (100, '100@mail.com', true, '$2a$12$oS58OyKQr3m4bQWFp/JstOv2BBOclFbd1cS1/FU3W0jx34Vu.n.4u', NOW(), 100);
INSERT INTO user_entity (id, email, is_enabled, password, last_redaction_date, role_id)
VALUES (101, '101@mail.com', true, '$2a$12$lEN0bVuULuMrrSz7puuUoOiJYMGNxkEtY/RNHBE7MDmIvv7sj3lJu', NOW(), 100);
INSERT INTO user_entity (id, email, is_enabled, password, last_redaction_date, role_id)
VALUES (102, '102@mail.com', true, '$2a$12$Q4lj3fTX4jtLUjNFGESwBe25LhE/W9s2RzCDLFteoI3VtCDwH.o2S', NOW(), 100);

-- Single chat
INSERT INTO chat (id, chat_type)
VALUES (100, 1);

INSERT INTO single_chat (chat_id, user_one_id, use_two_id, is_delete_one, is_delete_two)
VALUES (100, 100, 101, false, false);

-- Group chat
INSERT INTO chat (id, chat_type)
VALUES (101, 1);

INSERT INTO group_chat (chat_id, is_global, author_id, image_chat)
VALUES (101, false, 100, 'image');

INSERT INTO groupchat_has_users (chat_id, user_id)
VALUES (101, 101);


