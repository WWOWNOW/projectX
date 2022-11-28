INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, email, is_enabled, password, last_redaction_date, role_id)
VALUES (100, '100@mail.com', true, '$2a$12$oS58OyKQr3m4bQWFp/JstOv2BBOclFbd1cS1/FU3W0jx34Vu.n.4u', NOW(), 100);

INSERT INTO tag (id, name, persist_date)
VALUES (100, 'Tag 100', NOW());