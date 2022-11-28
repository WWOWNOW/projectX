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
INSERT INTO group_chat (chat_id, is_global, image_chat, title, author_id)
VALUES (100, false,'Some group chat image 100','Some group chat 100',100);
INSERT INTO groupchat_has_users (chat_id, user_id)
VALUES (100,100);
INSERT INTO groupchat_has_users (chat_id, user_id)
VALUES (100,101);