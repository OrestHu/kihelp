--liquibase formatted sql

--changeset OrestHutovych:create-ki_help-wallets-table
--comment create table ki_help.wallets
create table ki_help.wallets
(
    id    serial primary key,
    amount bigint not null,
    user_id bigint not null
);
--rollback drop table ki_help.wallets;

--changeset OrestHutovych:add-wallets-table-constraints
--comment add constraints to wallets
alter table ki_help.wallets
    add constraint wallets__user_id__fk
        foreign key (user_id) references ki_help.users(id);

--rollback alter table ki_help.wallets drop constraints wallets__user_id__fk;