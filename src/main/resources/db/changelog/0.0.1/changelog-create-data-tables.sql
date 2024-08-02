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
    title    varchar not null,
    info     varchar,
    path     varchar not null,
    price    decimal not null,
    discount decimal,
    type     BOOLEAN,
    visible  BOOLEAN,
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

--changeset OrestHutovych:create-ki_help-histories-table
--comment create table ki_help.histories
create table ki_help.histories
(
    id   serial primary key,
    name varchar not null,
    created_time_stamp timestamp not null,
    user_id bigint not null,
    task_id int not null
);
--rollback drop table ki_help.histories;

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

--changeset OrestHutovych:add-tasks-table-constraints
--comment add constraints to tasks
alter table ki_help.tasks
    add constraint tasks__teacher_id__fk
        foreign key (teacher_id) references ki_help.teachers(id);

alter table ki_help.tasks
    add constraint unique_task_title_per_teacher
        unique (title, teacher_id);
--rollback alter table ki_help.tasks drop constraint tasks__teacher_id__fk;
--rollback alter table ki_help.tasks drop constraint unique_task_title_per_teacher;

--changeset OrestHutovych:add-histories-table-constraints
--comment add constraints to histories
alter table ki_help.histories
    add constraint histories__user_id__fk
        foreign key (user_id) references ki_help.users(id);

alter table ki_help.histories
    add constraint histories__task_id__fk
        foreign key (task_id) references ki_help.tasks(id);

--rollback alter table ki_help.histories drop constraint histories__user_id__fk;
--rollback alter table ki_help.histories drop constraint histories__task_id__fk;


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


