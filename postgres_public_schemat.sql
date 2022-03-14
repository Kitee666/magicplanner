
create table if not exists "Lecturer"
(
    id       integer not null
        constraint pracownicy_pk
            primary key,
    name     varchar not null,
    lastname varchar not null,
    subject  varchar
);

alter table "Lecturer"
    owner to postgres;

create table if not exists "Room"
(
    id     integer not null
        constraint sale_pk
            primary key,
    number varchar not null
);

alter table "Room"
    owner to postgres;

create table if not exists "Meeting"
(
    id        integer not null
        constraint meeting_pk
            primary key,
    date_from date    not null,
    date_to   date
);

alter table "Meeting"
    owner to postgres;

create table if not exists "Groups"
(
    id   integer not null
        constraint groups_pk
            primary key,
    type varchar not null,
    rok  integer not null,
    size integer not null
);

alter table "Groups"
    owner to postgres;

create table if not exists "Subject"
(
    id          integer not null
        constraint przedmioty_pk
            primary key,
    name        varchar not null,
    lecturer_id integer
        constraint subject_lecturer_id_fk
            references "Lecturer",
    group_id    integer
        constraint subject_groups_id_fk
            references "Groups"
);

alter table "Subject"
    owner to postgres;

create table if not exists "Note"
(
    id      integer not null
        constraint note_pk
            primary key,
    title   varchar,
    content text    not null
);

alter table "Note"
    owner to postgres;

create table if not exists "Events"
(
    id         integer not null
        constraint events_pk
            primary key,
    date_from  date    not null,
    date_to    date    not null,
    subject_id integer
        constraint events_subject_id_fk
            references "Subject",
    room_id    integer
        constraint events_room_id_fk
            references "Room",
    meeting_id integer
        constraint events_meeting_id_fk
            references "Meeting"
);

alter table "Events"
    owner to postgres;

create table if not exists "Events_note"
(
    id        integer not null
        constraint events_note_pk
            primary key,
    events_id integer
        constraint events_note_events_id_fk
            references "Events",
    note_id   integer
        constraint events_note_note_id_fk
            references "Note"
);

alter table "Events_note"
    owner to postgres;

INSERT INTO public."Lecturer" VALUES (1,'Jerzy','Białkowski','PZ');
INSERT INTO public."Lecturer" VALUES (2,'Bartosz','Bieganowski','ASD,asd, pp, wsw');
INSERT INTO public."Lecturer" VALUES (3,'Rafał','Bocian','SK, sk, MIKR, mikr');
INSERT INTO public."Lecturer" VALUES (4,'Aleksandra','Boniewicz','bd I, c, bd II, sem');
INSERT INTO public."Lecturer" VALUES (5,'Dariusz','Borkowski','');
INSERT INTO public."Lecturer" VALUES (6,'Marta','Burzańska','BD I, BD II, TC, sem');
INSERT INTO public."Lecturer" VALUES (7,'Michał','Burzański','wsw, tc, mier');
INSERT INTO public."Lecturer" VALUES (8,'Krzysztof','Czarkowski','wsw');
INSERT INTO public."Lecturer" VALUES (9,'Adrian','Falkowski','mat');
INSERT INTO public."Lecturer" VALUES (10,'Anna','Gogolińska','IO, io, JAV, jav');
INSERT INTO public."Lecturer" VALUES (11,'Agnieszka','Goroncy','bd I, sad');
INSERT INTO public."Lecturer" VALUES (12,'Piotr','Górny','mat');
INSERT INTO public."Lecturer" VALUES (13,'Tomasz','Grzona','grf');
INSERT INTO public."Lecturer" VALUES (14,'Alicja','Jaworska-Pastuszak','mat');
INSERT INTO public."Lecturer" VALUES (15,'Mariusz','Kaniecki','PTO, pto');
INSERT INTO public."Lecturer" VALUES (16,'Anna','Kwiatkowska','PYTH');
INSERT INTO public."Lecturer" VALUES (17,'Damian','Kurpiewski','pp, pyth');
INSERT INTO public."Lecturer" VALUES (18,'Dawid','Maliszewski','c');
INSERT INTO public."Lecturer" VALUES (19,'Jakub','Narębski','FIZ, fiz, MIER, mob');
INSERT INTO public."Lecturer" VALUES (20,'Grzegorz','Pastuszak','mat');
INSERT INTO public."Lecturer" VALUES (21,'Marcin','Piątkowski','C, c,');
INSERT INTO public."Lecturer" VALUES (22,'Andrzej','Rutkowski','');
INSERT INTO public."Lecturer" VALUES (23,'Andrzej','Sendlewski','WSW');
INSERT INTO public."Lecturer" VALUES (24,'Robert','Skiba','mat');
INSERT INTO public."Lecturer" VALUES (25,'Krzysztof','Skowronek','');
INSERT INTO public."Lecturer" VALUES (26,'Mikołaj','Szczupak','bk, pp, so, wsw');
INSERT INTO public."Lecturer" VALUES (27,'Dorota','Szreder','ang');
INSERT INTO public."Lecturer" VALUES (28,'Jerzy','Szymański','SO, so, AUS, aus');
INSERT INTO public."Lecturer" VALUES (29,'Mateusz','Topolewski','pp, bd II, pdw1');
INSERT INTO public."Lecturer" VALUES (30,'Sylwester','Wieczorkowski','web');
INSERT INTO public."Lecturer" VALUES (31,'Aleksander','Zaigrajew','SAD, sad');
INSERT INTO public."Lecturer" VALUES (32,'Bartosz','Ziemkiewicz','asd, bk, PP, pp, grf, pdw1, sem');

INSERT INTO public."Room" VALUES (1,'Aula');
INSERT INTO public."Room" VALUES (2,'S1');
INSERT INTO public."Room" VALUES (3,'S2');
INSERT INTO public."Room" VALUES (4,'S3');
INSERT INTO public."Room" VALUES (5,'S4');
INSERT INTO public."Room" VALUES (6,'S5');
INSERT INTO public."Room" VALUES (7,'S6');
INSERT INTO public."Room" VALUES (8,'S7');
INSERT INTO public."Room" VALUES (9,'S8');
INSERT INTO public."Room" VALUES (10,'S9');
INSERT INTO public."Room" VALUES (11,'SS1');
INSERT INTO public."Room" VALUES (12,'SS2');
INSERT INTO public."Room" VALUES (13,'SS3');
INSERT INTO public."Room" VALUES (14,'L1');
INSERT INTO public."Room" VALUES (15,'L2');
INSERT INTO public."Room" VALUES (16,'L3');
INSERT INTO public."Room" VALUES (17,'L4');
INSERT INTO public."Room" VALUES (18,'L5');
INSERT INTO public."Room" VALUES (19,'L6');
INSERT INTO public."Room" VALUES (20,'L7');
INSERT INTO public."Room" VALUES (21,'L8');
INSERT INTO public."Room" VALUES (22,'L9');
INSERT INTO public."Room" VALUES (23,'L10');
INSERT INTO public."Room" VALUES (24,'L11');
INSERT INTO public."Room" VALUES (25,'L12');
INSERT INTO public."Room" VALUES (26,'L13');
INSERT INTO public."Room" VALUES (27,'L14');
INSERT INTO public."Room" VALUES (28,'L15');
INSERT INTO public."Room" VALUES (29,'Pm1');
INSERT INTO public."Room" VALUES (30,'Pm2');
INSERT INTO public."Room" VALUES (31,'Pm3');

INSERT INTO public."Meeting" VALUES (1,'2021-10-16','2021-10-17');
INSERT INTO public."Meeting" VALUES (2,'2021-10-23','2021-10-24');
INSERT INTO public."Meeting" VALUES (3,'2021-11-06','2021-11-07');
INSERT INTO public."Meeting" VALUES (4,'2021-11-20','2021-11-21');
INSERT INTO public."Meeting" VALUES (5,'2021-12-04','2021-12-05');
INSERT INTO public."Meeting" VALUES (6,'2021-12-18','2021-12-19');
INSERT INTO public."Meeting" VALUES (7,'2022-01-15','2022-01-16');
INSERT INTO public."Meeting" VALUES (8,'2022-01-29','2022-01-30');
INSERT INTO public."Meeting" VALUES (9,'2022-02-05','2022-02-06');
INSERT INTO public."Meeting" VALUES (10,'2022-02-26','2022-02-27');
INSERT INTO public."Meeting" VALUES (11,'2022-03-12','2022-03-13');
INSERT INTO public."Meeting" VALUES (12,'2022-03-26','2022-03-27');
INSERT INTO public."Meeting" VALUES (13,'2022-04-09','2022-04-10');
INSERT INTO public."Meeting" VALUES (14,'2022-04-23','2022-04-24');
INSERT INTO public."Meeting" VALUES (15,'2022-05-07','2022-05-08');
INSERT INTO public."Meeting" VALUES (16,'2022-05-14','2022-05-15');
INSERT INTO public."Meeting" VALUES (17,'2022-05-28','2022-05-29');
INSERT INTO public."Meeting" VALUES (18,'2022-06-11','2022-06-12');

INSERT INTO public."Groups" VALUES (1,'Wykład',1,52);
INSERT INTO public."Groups" VALUES (2,'Laboratorium',1,52);
INSERT INTO public."Groups" VALUES (3,'Ćwiczenia',1,52);
INSERT INTO public."Groups" VALUES (4,'Wykład',2,43);
INSERT INTO public."Groups" VALUES (5,'Laboratorium',2,43);
INSERT INTO public."Groups" VALUES (6,'Ćwiczenia',2,43);
INSERT INTO public."Groups" VALUES (7,'Wykład',3,29);
INSERT INTO public."Groups" VALUES (8,'Laboratorium',3,29);
INSERT INTO public."Groups" VALUES (9,'Ćwiczenia',3,29);
