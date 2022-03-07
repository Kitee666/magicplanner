create table "TestTABLE"
(
    id   integer not null
        constraint testtable_pk
            primary key,
    name varchar
);

alter table "TestTABLE"
    owner to postgres;

create table "Pracownicy"
(
    id           integer not null
        constraint pracownicy_pk
            primary key,
    "Imie"       varchar not null,
    "Nazwisko"   varchar not null,
    "Stanowisko" varchar
);

alter table "Pracownicy"
    owner to postgres;

create table "Przedmioty"
(
    id                 integer not null
        constraint przedmioty_pk
            primary key,
    "Nazwa_przedmiotu" varchar not null,
    "Typ"              varchar not null,
    "Liczba_godzin"    varchar not null,
    "Rok_studiów"      integer not null
);

alter table "Przedmioty"
    owner to postgres;

create table "Sale"
(
    id           integer not null
        constraint sale_pk
            primary key,
    "Nazwa_sali" varchar not null,
    "Pojemnosc"  integer
);

alter table "Sale"
    owner to postgres;

create table "Zjazdy"
(
    id     integer not null
        constraint zjazdy_pk
            primary key,
    "Data" date    not null
);

alter table "Zjazdy"
    owner to postgres;

create table "Grupy"
(
    id            integer not null
        constraint grupy_pk
            primary key,
    "Nazwa_grupy" varchar not null,
    "Rok_studiow" integer not null,
    "Liczebnosc"  integer not null
);

alter table "Grupy"
    owner to postgres;

create table "Dezyderaty"
(
    id            integer not null
        constraint dezyderaty_pk
            primary key,
    id_pracownika integer
        constraint dezyderaty_pracownicy_id_fk
            references "Pracownicy",
    id_zjazdu     integer
        constraint dezyderaty_zjazdy_id_fk
            references "Zjazdy",
    "Adnotacja"   varchar
);

alter table "Dezyderaty"
    owner to postgres;

create table "Przydzialy"
(
    id            integer not null
        constraint przydzialy_pk
            primary key,
    id_pracownika integer
        constraint przydzialy_pracownicy_id_fk
            references "Pracownicy",
    id_przedmiotu integer
        constraint przydzialy_przedmioty_id_fk
            references "Przedmioty",
    id_grupy      integer
        constraint przydzialy_grupy_id_fk
            references "Grupy"
);

alter table "Przydzialy"
    owner to postgres;

create table "Plan_zajec"
(
    id            integer not null
        constraint plan_zajec_pk
            primary key,
    id_przydzialu integer
        constraint plan_zajec_przydzialy_id_fk
            references "Przydzialy",
    id_zjazdu     integer
        constraint plan_zajec_zjazdy_id_fk
            references "Zjazdy",
    id_sali       integer
        constraint plan_zajec_sale_id_fk
            references "Sale",
    "Od"          integer not null,
    "Do"          integer not null
);

alter table "Plan_zajec"
    owner to postgres;


