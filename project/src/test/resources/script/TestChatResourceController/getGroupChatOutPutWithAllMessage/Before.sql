TRUNCATE TABLE role CASCADE;
TRUNCATE TABLE chat CASCADE;
TRUNCATE TABLE groupchat_has_users CASCADE;

INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, email,nickname,image_link, is_enabled, last_redaction_date, password, role_id)
VALUES (100, '0@mail.com','nickname0','imageLink0', true, now(),
        '$2a$10$lwhIK6IeCSg0NKbEmmKYFOUcxZ8lWvZUwh/3EaxGJloam.IwOwtFi', 100);
INSERT INTO user_entity (id, email,nickname,image_link, is_enabled, last_redaction_date, password, role_id)
VALUES (101, '1@mail.com','nickname1','imageLink1', true, now(),
        '$2a$12$NkyfI/0umQiQRH.HbP15aud1QZsSEHgGTZRxU383/awKn72ZWHECi', 100);
INSERT INTO user_entity (id, email,nickname,image_link, is_enabled, last_redaction_date, password, role_id)
VALUES (102, '2@mail.com','nickname2','imageLink2', true, now(),
        '$2a$10$qGIxi6XYGvztuNuwXXjSruynwvo8tX/UoC824IqCnzXLVSV5vo7U2', 100);
INSERT INTO chat (id, chat_type)
VALUES (100, 1);
INSERT INTO chat (id, chat_type)
VALUES (101, 1);

INSERT INTO group_chat (chat_id, is_global, image_chat, title, author_id)
VALUES (100, false,'Some group chat image 100','Some group chat 100',100);
INSERT INTO group_chat (chat_id, is_global, image_chat, title, author_id)
VALUES (101, false,'Some group chat image 101','Some group chat 101',100);

INSERT INTO groupchat_has_users (chat_id, user_id)
VALUES (100,100);
INSERT INTO groupchat_has_users (chat_id, user_id)
VALUES (101,100);
INSERT INTO groupchat_has_users (chat_id, user_id)
VALUES (101,101);

INSERT INTO message (id, last_redaction_date, message, chat_id, user_sender_id,persist_date)
VALUES (100,now(),'message100',100,100,'2022-10-27');
INSERT INTO message (id, last_redaction_date, message, chat_id, user_sender_id,persist_date)
VALUES (101,now(),'message101',100,101,'2022-10-28');
INSERT INTO message (id, last_redaction_date, message, chat_id, user_sender_id,persist_date)
VALUES (102,now(),'message102',101,101,'2022-10-29');
INSERT INTO message (id, last_redaction_date, message, chat_id, user_sender_id,persist_date)
VALUES (103,now(),'message103',101,100,'2022-10-30');

