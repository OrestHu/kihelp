--liquibase formatted sql

--changeset OrestHutovych:create-ki_help-attachments-table
--comment create table ki_help.attachments
create table ki_help.attachments
(
    id    serial primary key,
    name varchar not null,
    type varchar not null,
    data varchar not null
);
--rollback drop table ki_help.attachments;