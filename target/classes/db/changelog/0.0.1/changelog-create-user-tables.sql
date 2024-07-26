--liquibase formatted sql

--changeset OrestHutovych:create-ki_help-users-table
--comment create table ki_help.users
create table ki_help.users
(
    id          serial primary key,
    telegram_id varchar  not null unique,
    username    varchar,
    password    varchar not null,
    logo        varchar not null
);
--rollback drop table ki_help.users;

--changeset OrestHutovych:create-ki_help-roles-table
--comment create table ki_help.roles
create table ki_help.roles
(
    id   serial primary key,
    name varchar not null
);
--rollback drop table ki_help.roles;


--changeset OrestHutovych:create-ki_help-users_roles-table
--comment create table ki_help.users_roles
create table ki_help.users_roles
(
    user_id integer not null,
    role_id integer not null
);
--rollback drop table ki_help.users_roles;

--changeset OrestHutovych:add-users_roles-table-constraints
--comment add constraints to users_roles
alter table ki_help.users_roles
    add constraint users_roles__user_id__fk
        foreign key (user_id) references ki_help.users(id);

alter table ki_help.users_roles
    add constraint users_roles__role_id__fk
        foreign key (role_id) references ki_help.roles(id);

alter table ki_help.users_roles
    add constraint users_roles_unique
        unique (user_id, role_id);
--rollback alter table ki_help.users_roles drop constraints users_roles__user_id__fk;
--rollback alter table ki_help.users_roles drop constraints users_roles__role_id__fk;
--rollback alter table ki_help.users_roles drop constraints users_roles_unique;
