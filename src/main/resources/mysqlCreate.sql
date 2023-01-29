CREATE TABLE flights
(
    number         varchar(25) PRIMARY KEY,
    departure_city varchar(25),
    arrival_city   varchar(25),
    departure_time time,
    arrival_time   time,
    cost           integer CHECK ( cost > 0 ),
    seats          int CHECK ( seats > 0 ),
    weekdays       varchar(25)
);


CREATE TABLE flight_dates
(
    id             int PRIMARY KEY auto_increment,
    date           date,
    flight_number  varchar(25) references flights (number) on delete cascade,
    reserved_seats int DEFAULT 0
);

CREATE TABLE users
(
    username varchar(25) PRIMARY KEY,
    password varchar(25) NOT NULL,
    role     varchar(100) default 'ROLE_USER'
);

CREATE TABLE tickets
(
    id             int PRIMARY KEY auto_increment,
    flight_date_id int references flight_dates (id) ON DELETE CASCADE,
    username       varchar(25) references users (username) ON DELETE CASCADE,
    reserved_seats int
);

create table orders
(
    id                int primary key auto_increment,
    need_insurance    boolean not null,
    need_registration boolean not null,
    username          varchar(25) references users (username) on delete cascade,
    flight_number     varchar(25) references flights (number) on delete cascade,
    flight_date       date    not null
);

create table passengers
(
    id           int primary key auto_increment,
    name         varchar(25)                                  not null,
    surname      varchar(25)                                  not null,
    birth_date   date                                         not null,
    passport     varchar(25)                                  not null,
    need_luggage boolean                                      not null,
    order_id     int references orders (id) on delete cascade not null
);


insert into users
values ('adm', 'adm', 'ROLE_ADMIN');

insert into users
values ('ivan', 'ivan');

insert into flights
values ('A2332', 'Москва', 'Новосибирск', '12:03', '13:10', 25000, 32, 'Пн Вт Ср');

insert into flights
values ('B2332', 'Кемерово', 'Барнаул', '15:02', '12:10', 30000, 12, 'Чт Пт');

insert into flights
values ('A2233', 'Чита', 'Уфа', '15:01', '12:30', 11000, 12, 'Сб Вс');

insert into flight_dates(date, flight_number)
values ('2022.01.10', 'A2332');

insert into flight_dates(date, flight_number)
values ('2022.01.12', 'A2332');

insert into flight_dates(date, flight_number)
values ('2022.01.12', 'B2332');

insert into flight_dates(date, flight_number)
values ('17.01.2022', 'A2233');

insert into flight_dates(date, flight_number)
values ('18.01.2022', 'A2233');

SELECT *
FROM flights;

SELECT *
FROM flight_dates;