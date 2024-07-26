--liquibase formatted sql

--changeset OrestHutovych:create-ki_help-schema
--comment create new schema
create schema ki_help;
--rollback drop schema ki_help;

--changeset OrestHutovych:create-ki_help-subjects-table
--comment create table ki_help.subjects
create table ki_help.subjects
(
    id    serial primary key,
    title varchar not null unique
);
--rollback drop table ki_help.subjects;

--changeset OrestHutovych:create-ki_help-tasks-table
--comment create table ki_help.tasks
create table ki_help.tasks
(
    id       serial primary key,
    title    varchar not null unique,
    path     varchar not null,
    price    int     not null,
    discount decimal,
    subject_id int not null,
    teacher_id int not null
);
--rollback drop table ki_help.tasks;

--changeset OrestHutovych:create-ki_help-teachers-table
--comment create table ki_help.teachers
create table ki_help.teachers
(
    id   serial primary key,
    name varchar not null,
    subject_id int
);
--rollback drop table ki_help.teachers;

--changeset OrestHutovych:create-ki_help-arguments-table
--comment create table ki_help.arguments
create table ki_help.arguments
(
    id   serial primary key,
    name varchar not null
);
--rollback drop table ki_help.arguments;

--changeset OrestHutovych:create-ki_help-tasks_arguments-table
--comment create table ki_help.tasks_arguments
create table ki_help.tasks_arguments
(
    task_id integer not null,
    argument_id integer not null
);
--rollback drop table ki_help.tasks_arguments;


--changeset OrestHutovych:add-tasks_arguments-table-constraints
--comment add constraints to tasks_arguments
alter table ki_help.tasks_arguments
    add constraint tasks_arguments__task_id__fk
        foreign key (task_id) references ki_help.tasks(id);

alter table ki_help.tasks_arguments
    add constraint tasks_arguments__argument_id__fk
        foreign key (argument_id) references ki_help.arguments(id);

alter table ki_help.tasks_arguments
    add constraint tasks_arguments_unique
        unique (task_id, argument_id);
--rollback alter table ki_help.tasks_arguments drop constraints tasks_arguments__task_id__fk;
--rollback alter table ki_help.tasks_arguments drop constraints tasks_arguments__argument_id__fk;
--rollback alter table ki_help.tasks_arguments drop constraints tasks_arguments_unique;


