alter table answer
    alter column persist_date set default now();
alter table answer
    alter column persist_date set not null;

alter table bookmarks
    alter column persist_date set default now();
alter table bookmarks
    alter column persist_date set not null;

alter table chat
    alter column persist_date set default now();
alter table chat
    alter column persist_date set not null;

alter table comment
    alter column persist_date set default now();
alter table comment
    alter column persist_date set not null;

alter table message
    alter column persist_date set default now();
alter table message
    alter column persist_date set not null;

alter table question
    alter column persist_date set default now();
alter table question
    alter column persist_date set not null;

alter table question_viewed
    alter column persist_date set default now();
alter table question_viewed
    alter column persist_date set not null;

alter table reputation
    alter column persist_date set default now();
alter table reputation
    alter column persist_date set not null;

alter table tag
    alter column persist_date set default now();
alter table tag
    alter column persist_date set not null;

alter table tag_ignore
    alter column persist_date set default now();
alter table tag_ignore
    alter column persist_date set not null;

alter table tag_tracked
    alter column persist_date set default now();
alter table tag_tracked
    alter column persist_date set not null;

alter table user_entity
    alter column persist_date set default now();
alter table user_entity
    alter column persist_date set not null;

alter table votes_on_answers
    alter column persist_date set default now();
alter table votes_on_answers
    alter column persist_date set not null;

alter table votes_on_questions
    alter column persist_date set default now();
alter table votes_on_questions
    alter column persist_date set not null;