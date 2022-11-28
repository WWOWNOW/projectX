TRUNCATE TABLE role CASCADE;

INSERT INTO role (id, name)
VALUES (100, 'ROLE_USER');

INSERT INTO user_entity (id, city, email, full_name, image_link, is_enabled, last_redaction_date, password, role_id)
VALUES (GENERATE_SERIES(100, 101), CONCAT('City ', GENERATE_SERIES(100, 101)),
        CONCAT(GENERATE_SERIES(100, 101), '@mail.com'),
        CONCAT('FullName ', GENERATE_SERIES(100, 101)), CONCAT('LinkImage ', GENERATE_SERIES(100, 101)), true, NOW(),
        '$2a$10$sas6S77dkMjtev/s.77JousVSc7hbodeq/bl7TB1tQ2ENd5eFFWQK', 100);

INSERT INTO question (id, description, last_redaction_date, title, user_id)
VALUES (GENERATE_SERIES(100, 102), CONCAT('Question Description ', GENERATE_SERIES(100, 102)), NOW(),
        CONCAT('Question Title ', GENERATE_SERIES(100, 102)), 100);

DO
'
DECLARE date_answer TIMESTAMP = NOW() - INTERVAL ''15'' DAY;
    DECLARE id_answer INT = 100;
BEGIN
FOR time in 1..3
    LOOP
    INSERT INTO answer (id, html_body, is_deleted, is_deleted_by_moderator,
        is_helpful, persist_date, update_date, question_id, user_id, edit_moderator_id)
    VALUES (id_answer, CONCAT(''Question 100 Answer body '', time), false, false, true, date_answer, NOW(), 100, 101, 100);
    id_answer = id_answer + 1;
    date_answer = date_answer + INTERVAL ''1'' DAY;
    INSERT INTO answer (id, html_body, is_deleted, is_deleted_by_moderator,
        is_helpful, persist_date, update_date, question_id, user_id, edit_moderator_id)
    VALUES (id_answer, CONCAT(''Question 101 Answer body '', time), false, false, true, date_answer, NOW(), 101, 101, 100);
    id_answer = id_answer + 1;
    date_answer = date_answer + INTERVAL ''2'' DAY;
    INSERT INTO answer (id, html_body, is_deleted, is_deleted_by_moderator,
        is_helpful, persist_date, update_date, question_id, user_id, edit_moderator_id)
    VALUES (id_answer, CONCAT(''Question 102 Answer body '', time), false, false, true, date_answer, NOW(), 102, 101, 100);
    id_answer = id_answer + 1;
    date_answer = date_answer + INTERVAL ''3'' DAY;
    END LOOP;
END;
';

DO
'
BEGIN
FOR i in 100..108
    LOOP
    IF (i % 2 = 0) THEN
        INSERT INTO votes_on_answers (id, persist_date, vote, answer_id, user_id)
        VALUES (i, NOW(), ''DOWN VOTE'', i, 100);
        ELSE
            INSERT INTO votes_on_answers (id, persist_date, vote, answer_id, user_id)
            VALUES (i, NOW(), ''UP_VOTE'', i, 100);
        END IF;
    END LOOP;
END;
';