create table if not exists microservice2
(
    id          integer not null  primary key,
    column1         varchar(255),
    column2         varchar(255)
);
create sequence if not exists microservice2_seq increment by 50;
