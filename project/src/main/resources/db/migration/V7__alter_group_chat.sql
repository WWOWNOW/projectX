alter table group_chat
    add author_id bigint;

alter table group_chat
    add constraint group_chat_user_entity_id_fk
        foreign key (author_id) references user_entity;