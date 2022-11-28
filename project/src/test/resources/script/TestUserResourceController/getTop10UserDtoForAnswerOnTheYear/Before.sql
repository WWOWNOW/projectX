TRUNCATE TABLE role CASCADE;

INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, city, email, full_name, image_link, is_enabled, last_redaction_date, password, role_id)
VALUES (GENERATE_SERIES(100, 120), CONCAT('City ', GENERATE_SERIES(100, 120)),
        CONCAT(GENERATE_SERIES(100, 120), '@mail.com'),
        CONCAT('FullName ', GENERATE_SERIES(100, 120)), CONCAT('LinkImage ', GENERATE_SERIES(100, 120)), true, NOW(),
        '$2a$10$sas6S77dkMjtev/s.77JousVSc7hbodeq/bl7TB1tQ2ENd5eFFWQK', 100);

INSERT INTO question (id, description, last_redaction_date, title, user_id)
VALUES (100, 'Question Description 1', NOW(), 'Question Title 1', 100);

INSERT INTO reputation (id, "count", persist_date, type, answer_id, author_id, question_id, sender_id)
VALUES (GENERATE_SERIES(100, 120), GENERATE_SERIES(-50, 50, 5), NOW(), 2, null, GENERATE_SERIES(100, 120), 100, 100);

DO
'
DECLARE count_answers INT = 1;
    DECLARE id_answer INT = 100;
    DECLARE date_answer TIMESTAMP = NOW() - INTERVAL ''365'' DAY;
BEGIN
FOR id_user in 100..120
    LOOP
    BEGIN
    FOR value IN 1..count_answers
        LOOP
        INSERT INTO answer (id, html_body, is_deleted, is_deleted_by_moderator,
            is_helpful, persist_date, update_date, question_id, user_id, edit_moderator_id)
        VALUES (id_answer, ''Answer body'', false, false, true, date_answer, NOW(), 100, id_user, 100);
        id_answer = id_answer + 1;
        date_answer = date_answer + INTERVAL ''1'' DAY;
        END LOOP;
    END;
    count_answers = count_answers + 1;
    END LOOP;
END;
';