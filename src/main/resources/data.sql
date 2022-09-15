insert into users(email, name, password) values ('shane@test.com', 'Shane', '$2a$12$ZQwsXADIP5mXH7nv7BPTP.EAmkhtyf3ppThNkun9qPHB6nB.Ibe3e');

insert into location(name, description) values ('A', 'This is the first location!');    -- 1
insert into location(name, description) values ('B', 'This is the second location!');   -- 2
insert into location(name) values ('C');                                                -- 3
insert into location(name) values ('D');                                                -- 4
insert into location(name) values ('E');                                                -- 5
insert into location(name) values ('F');                                                -- 6

-- Populate the location links for the rest of the locations
insert into location_link(primary_location, adjacent_location, distance) values ('A', 'B', 2);   -- A -> B
insert into location_link(primary_location, adjacent_location, distance) values ('A', 'D', 8);   -- A -> D
insert into location_link(primary_location, adjacent_location, distance) values ('B', 'D', 5);   -- B -> D
insert into location_link(primary_location, adjacent_location, distance) values ('B', 'E', 6);   -- B -> E
insert into location_link(primary_location, adjacent_location, distance) values ('D', 'E', 3);    -- D -> F
insert into location_link(primary_location, adjacent_location, distance) values ('D', 'F', 2);    -- F -> E
insert into location_link(primary_location, adjacent_location, distance) values ('E', 'C', 9);   -- E -> C
insert into location_link(primary_location, adjacent_location, distance) values ('E', 'F', 1);   -- E -> F
insert into location_link(primary_location, adjacent_location, distance) values ('F', 'C', 3);   -- E -> C
