create sequence ADDRESS_SEQUENCE;

create sequence ENTRY_SEQUENCE;

create sequence USER_SEQUENCE;

create table ADDRESSES
(
    ADDRESS_ID     BIGINT       not null
        primary key,
    ADDRESS_NUMBER INTEGER      not null,
    ADDRESS_STREET VARCHAR(100) not null
);

create table ENTRIES
(
    ID           BIGINT       not null
        primary key,
    ENTRY_NAME   VARCHAR(100) not null,
    ENTRY_NUMBER VARCHAR(11),
    USER_ID      BIGINT
);

create table USERS
(
    USER_ID     BIGINT       not null
        primary key,
    USER_NAME   VARCHAR(100) not null
        constraint UK_K8D0F2N7N88W1A16YHUA64ONX
            unique,
    USER_NUMBER VARCHAR(11)  not null
);

create table USERS_ADDRESSES
(
    USERS_USER_ID        BIGINT not null,
    ADDRESSES_ADDRESS_ID BIGINT not null,
    primary key (USERS_USER_ID, ADDRESSES_ADDRESS_ID),
    constraint FKJLB7EEE1N6CWRK9BAPD3TYCT3
        foreign key (ADDRESSES_ADDRESS_ID) references ADDRESSES (ADDRESS_ID),
    constraint FKLNP51EIWQNDDPMQ2HALEAN5W0
        foreign key (USERS_USER_ID) references USERS (USER_ID)
);

