-- Insert data into users table
INSERT INTO users (email, username, password)
VALUES
    ('brock@pokemon.com', 'Brock', '$2a$12$764RLta2eg/DhWOm/EIZNeEeUdVrpjER/GFaL/n8H8ooBDYY4YKP2'),
    ('misty@pokemon.com', 'Misty', '$2a$12$764RLta2eg/DhWOm/EIZNeEeUdVrpjER/GFaL/n8H8ooBDYY4YKP2');
-- Password is 'password123'


-- Insert data into authorities table
INSERT INTO authorities (username ,authority)
VALUES
    ('Brock','ROLE_ADMIN'),
    ('Misty','ROLE_TRAINER');

-- Insert data into Pokemon table
INSERT INTO pokemon (name, national_dex, type, health_points, attack, defence, sp_attack, sp_defence, speed, validated)
VALUES
    ('Geodude', 74, 'ROCK', 40, 80, 100, 30, 30, 20, true),
    ('Onix', 95, 'ROCK', 35, 45, 160, 30, 45, 70, false),
    ('Staryu', 120, 'WATER', 30, 45, 55, 70, 55, 85, false),
    ('Charmander', 4, 'FIRE', 39, 52, 43, 60, 50, 65, true),
    ('Squirtle', 7, 'WATER', 44, 48, 65, 50, 64, 43, true),
    ('Pikachu', 25, 'ELECTRIC', 35, 55, 40, 50, 50, 90, true);


-- Insert data into owned_pokemon table to map users to their Pokémon
INSERT INTO owned_pokemon (pokemon_id, nickname, nature, caught_by_trainer_name, beauty, coolness, cuteness, cleverness, toughness)
VALUES
    (1, null, 'HARDY', 'Brock', 10, 20, 15, 25, 50),
    (2, 'Rocky', 'IMPISH', 'Brock', 15, 25, 20, 30, 55),
    (3, null, 'CALM', 'Misty', 20, 30, 40, 50, 60),
    (4, 'Flame', 'BOLD', 'Ash', 30, 40, 50, 60, 70),
    (5, 'BlueBoi', 'TIMID', 'Ash', 35, 45, 55, 65, 75);

-- Insert data into team table
INSERT INTO team (description) VALUES ('Team of GymLeader Brock');
INSERT INTO team (description) VALUES ('Team of GymLeader Misty');

-- Insert data into team_owned_pokemon join table for Brock's Team
INSERT INTO team_owned_pokemon (team_id, owned_pokemon_id) VALUES (1, 1); -- Geodude
INSERT INTO team_owned_pokemon (team_id, owned_pokemon_id) VALUES (1, 2); -- Onix

-- Insert data into team_owned_pokemon join table for Misty's Team
INSERT INTO team_owned_pokemon (team_id, owned_pokemon_id) VALUES (2, 3); -- Staryu

-- Insert data into region_maps table
INSERT INTO region_maps (region_name, horizontal_axis, vertical_axis)
VALUES
    ('Kanto', 80, 60),
    ('Johto', 75, 55),
    ('Hoenn', 70, 40),
    ('Sinnoh', 60, 40);

-- Insert data into locations table
INSERT INTO locations (name, description, coordinate_X, coordinate_Y, region_map_id)
VALUES
    ('Pallet Town', 'A small town with a quiet atmosphere', 1, 1, 1),
    ('Viridian City', 'A beautiful city with a forest nearby', 2, 2, 1);

-- Insert data into berries table
INSERT INTO berries (name, index_number, description, growth_time, category_type, spicy_potency, dry_potency,
                     sweet_potency, bitter_potency, sour_potency)
VALUES
    ('Cheri', 1, 'A Poffin ingredient. It may be used or held by a Pokémon to recover from paralysis.', 3,
     'MEDICINE', 10, 0, 0, 0, 0),
    ('Chesto', 2, 'A Poffin ingredient. It may be used or held by a Pokémon to recover from sleep.', 3,
     'MEDICINE', 0, 10, 0, 0, 0),
    ('Pecha', 3, 'A Poffin ingredient. It may be used or held by a Pokémon to recover from poisoning.', 3,
        'MEDICINE', 0, 0, 10, 0, 0),
    ('Rawst', 4, 'A Poffin ingredient. It may be used or held by a Pokémon to recover from a burn.', 3,
        'MEDICINE', 0, 0, 0, 10, 0),
    ('Aspear', 5, 'A Poffin ingredient. It may be used or held by a Pokémon to recover from being frozen.', 3,
        'MEDICINE', 0, 0, 0, 0, 10);


-- Insert data into berry_planting_sites table
INSERT INTO berry_planting_sites (description, soil_slots, location_id)
VALUES
    ('East gate berry planting site', 4, 1),
    ('Behind the lake in the North, currently unreachable due to a tree obstacle', 2, 2);

-- Insert data into berry_planting_site_slots table to map berries to planting slots
INSERT INTO berry_planting_site_slots (site_id, slot_number, planted_berries_by_slots_id)
VALUES
    (1, 1, 1),  -- Cheri Berry
    (1, 2, 1),  -- Cheri Berry
    (2, 1, 2);  -- Chesto Berry


-- Insert Games
INSERT INTO game ( version_name, generation, description, region_map_id, user_id)
VALUES ( 'Diamond', 4, 'The first appearance of PoffinHouse', 4, 1);

INSERT INTO game_pokemon (game_id, pokemon_id) VALUES (1, 1);
INSERT INTO game_pokemon (game_id, pokemon_id) VALUES (1, 2);
INSERT INTO game_pokemon (game_id, pokemon_id) VALUES (1, 3);

INSERT INTO game_berry (game_id, berry_id) VALUES (1, 1);
INSERT INTO game_berry (game_id, berry_id) VALUES (1, 2);
INSERT INTO game_berry (game_id, berry_id) VALUES (1, 3);

UPDATE owned_pokemon SET game_id = 1 WHERE owned_pokemon.id = 4;
UPDATE team SET game_id = 1 WHERE team.id = 1;