insert into users(email, name, password) values ('shane@test.com', 'Shane', '$2a$12$ZQwsXADIP5mXH7nv7BPTP.EAmkhtyf3ppThNkun9qPHB6nB.Ibe3e');

insert into location(name, description) values ('A', 'This is the first location!');    -- 1
insert into location(name, description) values ('B', 'This is the second location!');   -- 2
insert into location(name) values ('C');                                                -- 3
insert into location(name) values ('D');                                                -- 4
insert into location(name) values ('E');                                                -- 5
insert into location(name) values ('F');                                                -- 6

-- Populate the location links of the entities to itself (this will always be 0)
insert into location_link(primary_location_id, adjacent_location_id, distance) values (1, 1, 0);
insert into location_link(primary_location_id, adjacent_location_id, distance) values (2, 2, 0);
insert into location_link(primary_location_id, adjacent_location_id, distance) values (3, 3, 0);
insert into location_link(primary_location_id, adjacent_location_id, distance) values (4, 4, 0);
insert into location_link(primary_location_id, adjacent_location_id, distance) values (5, 5, 0);
insert into location_link(primary_location_id, adjacent_location_id, distance) values (6, 6, 0);

-- Populate the location links for the rest of the locations
insert into location_link(primary_location_id, adjacent_location_id, distance) values (1, 2, 10);   -- A -> B
insert into location_link(primary_location_id, adjacent_location_id, distance) values (1, 3, 15);   -- A -> C
insert into location_link(primary_location_id, adjacent_location_id, distance) values (2, 6, 15);   -- B -> F
insert into location_link(primary_location_id, adjacent_location_id, distance) values (2, 4, 12);   -- B -> D
insert into location_link(primary_location_id, adjacent_location_id, distance) values (4, 6, 1);    -- D -> F
insert into location_link(primary_location_id, adjacent_location_id, distance) values (6, 5, 5);    -- F -> E
insert into location_link(primary_location_id, adjacent_location_id, distance) values (6, 5, 10);   -- C -> E