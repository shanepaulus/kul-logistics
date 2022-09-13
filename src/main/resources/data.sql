insert into users(email, name, password) values ('shane@test.com', 'Shane', '$2a$12$ZQwsXADIP5mXH7nv7BPTP.EAmkhtyf3ppThNkun9qPHB6nB.Ibe3e');

insert into location(name, description) values ('A', 'This is the first location!');
insert into location(name, description) values ('B', 'This is the second location!');

insert into location_link(primary_location_id, adjacent_location_id, distance) values (1, 1, 0);
insert into location_link(primary_location_id, adjacent_location_id, distance) values (1, 2, 15)