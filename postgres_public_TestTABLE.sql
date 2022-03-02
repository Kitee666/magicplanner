create table "TestTABLE"
(
    id   integer not null
        constraint testtable_pk
            primary key,
    name varchar
);

alter table "TestTABLE"
    owner to postgres;

INSERT INTO public."TestTABLE" (id, name) VALUES (1, 'Dominik');