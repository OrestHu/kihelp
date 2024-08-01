--liquibase formatted sql

--changeset OrestHutovych:create-ki_help-transactions-table
--comment create table ki_help.transactions
create table ki_help.transactions
(
    id          serial primary key,
    payment_id  varchar not null,
    initial     varchar not null,
    amount      double precision not null,
    created_time_stamp timestamp not null,
    wallet_id   int not null
);
--rollback drop table ki_help.transactions;

--changeset OrestHutovych:add-wallets-transactions-constraints
--comment add constraints to transactions
alter table ki_help.transactions
    add constraint transactions__wallet_id__fk
        foreign key (wallet_id) references ki_help.wallets(id)

--rollback alter table ki_help.transactions drop constraints transactions__wallet_id__fk;