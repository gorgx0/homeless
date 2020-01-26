-- auto-generated definition
create table PLACES
(
    ID          INT auto_increment,
    NAME        VARCHAR,
    DESCRIPTION VARCHAR,
    STYLE       VARCHAR,
    LOCATION    GEOMETRY
);

create unique index PLACES_ID_UINDEX
    on PLACES (ID);

