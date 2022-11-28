TRUNCATE TABLE role CASCADE;
TRUNCATE TABLE chat CASCADE;

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
VALUES (100, 0);
INSERT INTO chat (id, chat_type)
VALUES (101, 0);

INSERT INTO message (id, last_redaction_date, message, chat_id, user_sender_id,persist_date)
VALUES (100,now(),'message100',100,100,'2022-10-30T16:19:31.538221');
INSERT INTO message (id, last_redaction_date, message, chat_id, user_sender_id,persist_date)
VALUES (101,now(),'message101',101,101,'2022-10-30T16:19:30.538221');

INSERT INTO single_chat (chat_id, use_two_id, user_one_id)
VALUES (100,100,100);
INSERT INTO single_chat (chat_id, use_two_id, user_one_id)
VALUES (101,100,101);
