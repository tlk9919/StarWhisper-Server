create table user
(
    id       bigint auto_increment
        primary key,
    username varchar(255) not null,
    password varchar(255) not null,
    constraint uk_username
        unique (username)
);
create table verification_code
(
    id      bigint auto_increment
        primary key,
    email   varchar(255) not null,
    code    varchar(6)   not null,
    expires datetime     not null,
    constraint uk_email
        unique (email)
);
