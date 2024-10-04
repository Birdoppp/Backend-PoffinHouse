-- Insert data into users table
INSERT INTO users (email, username, password)
VALUES ('brock@pokemon.com', 'Brock', '$2a$12$764RLta2eg/DhWOm/EIZNeEeUdVrpjER/GFaL/n8H8ooBDYY4YKP2'),
       ('misty@pokemon.com', 'Misty', '$2a$12$764RLta2eg/DhWOm/EIZNeEeUdVrpjER/GFaL/n8H8ooBDYY4YKP2');
-- Password is 'password123'

-- Insert data into authorities table
INSERT INTO authorities (username, authority)
VALUES ('Brock', 'ROLE_ADMIN'),
       ('Misty', 'ROLE_TRAINER');


-- Insert data into Pokemon table
INSERT INTO pokemon (name, national_dex, type, health_points, attack, defence, sp_attack, sp_defence, speed, validated)
VALUES ('Bulbasaur', 1, 'GRASS', 45, 49, 49, 65, 65, 45, true),
       ('Ivysaur', 2, 'GRASS', 60, 62, 63, 80, 80, 60, true),
       ('Venusaur', 3, 'GRASS', 80, 82, 83, 100, 100, 80, true),
       ('Charmander', 4, 'FIRE', 39, 52, 43, 60, 50, 65, true),
       ('Charmeleon', 5, 'FIRE', 58, 64, 58, 80, 65, 80, true),
       ('Charizard', 6, 'FIRE', 78, 84, 78, 109, 85, 100, true),
       ('Squirtle', 7, 'WATER', 44, 48, 65, 50, 64, 43, true),
       ('Wartortle', 8, 'WATER', 59, 63, 80, 65, 80, 58, true),
       ('Blastoise', 9, 'WATER', 79, 83, 100, 85, 105, 78, true),
       ('Caterpie', 10, 'BUG', 45, 30, 35, 20, 20, 45, true),
       ('Metapod', 11, 'BUG', 50, 20, 55, 25, 25, 30, true),
       ('Butterfree', 12, 'BUG', 60, 45, 50, 90, 80, 70, true),
       ('Weedle', 13, 'BUG', 40, 35, 30, 20, 20, 50, true),
       ('Kakuna', 14, 'BUG', 45, 25, 50, 25, 25, 35, true),
       ('Beedrill', 15, 'BUG', 65, 90, 40, 45, 80, 75, true),
       ('Pidgey', 16, 'NORMAL', 40, 45, 40, 35, 35, 56, true),
       ('Pidgeotto', 17, 'NORMAL', 63, 60, 55, 50, 50, 71, true),
       ('Pidgeot', 18, 'NORMAL', 83, 80, 75, 70, 70, 101, true),
       ('Rattata', 19, 'NORMAL', 30, 56, 35, 25, 35, 72, true),
       ('Raticate', 20, 'NORMAL', 55, 81, 60, 50, 70, 97, true),
       ('Spearow', 21, 'NORMAL', 40, 60, 30, 31, 31, 70, true),
       ('Fearow', 22, 'NORMAL', 65, 90, 65, 61, 61, 100, true),
       ('Ekans', 23, 'POISON', 35, 60, 44, 40, 54, 55, true),
       ('Arbok', 24, 'POISON', 60, 85, 69, 65, 79, 80, true),
       ('Pikachu', 25, 'ELECTRIC', 35, 55, 40, 50, 50, 90, true),
       ('Raichu', 26, 'ELECTRIC', 60, 90, 55, 90, 80, 110, true),
       ('Sandshrew', 27, 'GROUND', 50, 75, 85, 20, 30, 40, true),
       ('Sandslash', 28, 'GROUND', 75, 100, 110, 45, 55, 65, true),
       ('Nidoran♀', 29, 'POISON', 55, 47, 52, 40, 40, 41, true),
       ('Nidorina', 30, 'POISON', 70, 62, 67, 55, 55, 56, true),
       ('Nidoqueen', 31, 'POISON', 90, 92, 87, 75, 85, 76, true),
       ('Nidoran♂', 32, 'POISON', 46, 57, 40, 40, 40, 50, true),
       ('Nidorino', 33, 'POISON', 61, 72, 57, 55, 55, 65, true),
       ('Nidoking', 34, 'POISON', 81, 102, 77, 85, 75, 85, true),
       ('Clefairy', 35, 'NORMAL', 70, 45, 48, 60, 65, 35, true),
       ('Clefable', 36, 'NORMAL', 95, 70, 73, 85, 90, 60, true),
       ('Vulpix', 37, 'FIRE', 38, 41, 40, 50, 65, 65, true),
       ('Ninetales', 38, 'FIRE', 73, 76, 75, 81, 100, 100, true),
       ('Jigglypuff', 39, 'NORMAL', 115, 45, 20, 45, 25, 20, true),
       ('Wigglytuff', 40, 'NORMAL', 140, 70, 45, 85, 50, 45, true),
       ('Zubat', 41, 'POISON', 40, 45, 35, 30, 40, 55, true),
       ('Golbat', 42, 'POISON', 75, 80, 70, 65, 75, 90, true),
       ('Oddish', 43, 'GRASS', 45, 50, 55, 75, 65, 30, true),
       ('Gloom', 44, 'GRASS', 60, 65, 70, 85, 75, 40, true),
       ('Vileplume', 45, 'GRASS', 75, 80, 85, 110, 90, 50, true),
       ('Paras', 46, 'BUG', 35, 70, 55, 45, 55, 25, true),
       ('Parasect', 47, 'BUG', 60, 95, 80, 60, 80, 30, true),
       ('Venonat', 48, 'BUG', 60, 55, 50, 40, 55, 45, true),
       ('Venomoth', 49, 'BUG', 70, 65, 60, 90, 75, 90, true),
       ('Diglett', 50, 'GROUND', 10, 55, 25, 35, 45, 95, true),
       ('Dugtrio', 51, 'GROUND', 35, 80, 50, 50, 70, 120, true),
       ('Meowth', 52, 'NORMAL', 40, 45, 35, 40, 40, 90, true),
       ('Persian', 53, 'NORMAL', 65, 70, 60, 65, 65, 115, true),
       ('Psyduck', 54, 'WATER', 50, 52, 48, 65, 50, 55, true),
       ('Golduck', 55, 'WATER', 80, 82, 78, 95, 80, 85, true),
       ('Mankey', 56, 'FIGHTING', 40, 80, 35, 35, 45, 70, true),
       ('Primeape', 57, 'FIGHTING', 65, 105, 60, 60, 70, 95, true),
       ('Growlithe', 58, 'FIRE', 55, 70, 45, 70, 50, 60, true),
       ('Arcanine', 59, 'FIRE', 90, 110, 80, 100, 80, 95, true),
       ('Poliwag', 60, 'WATER', 40, 50, 40, 40, 40, 90, true),
       ('Poliwhirl', 61, 'WATER', 65, 65, 65, 50, 50, 90, true),
       ('Poliwrath', 62, 'WATER', 90, 85, 95, 70, 90, 70, true),
       ('Abra', 63, 'PSYCHIC', 25, 20, 15, 105, 55, 90, true),
       ('Kadabra', 64, 'PSYCHIC', 40, 35, 30, 120, 70, 105, true),
       ('Alakazam', 65, 'PSYCHIC', 55, 50, 45, 135, 85, 120, true),
       ('Machop', 66, 'FIGHTING', 70, 80, 50, 35, 35, 35, true),
       ('Machoke', 67, 'FIGHTING', 80, 100, 70, 50, 60, 45, true),
       ('Machamp', 68, 'FIGHTING', 90, 130, 80, 65, 85, 55, true),
       ('Bellsprout', 69, 'GRASS', 50, 75, 35, 70, 30, 40, true),
       ('Weepinbell', 70, 'GRASS', 65, 90, 50, 85, 45, 55, true),
       ('Victreebel', 71, 'GRASS', 80, 105, 65, 100, 60, 70, true),
       ('Tentacool', 72, 'WATER', 40, 40, 35, 50, 100, 70, true),
       ('Tentacruel', 73, 'WATER', 80, 70, 65, 80, 120, 100, true),
       ('Geodude', 74, 'ROCK', 40, 80, 100, 30, 30, 20, true),
       ('Graveler', 75, 'ROCK', 55, 95, 115, 45, 45, 35, true),
       ('Golem', 76, 'ROCK', 80, 120, 130, 55, 65, 45, true),
       ('Ponyta', 77, 'FIRE', 50, 85, 55, 65, 65, 90, true),
       ('Rapidash', 78, 'FIRE', 65, 100, 70, 80, 80, 105, true),
       ('Slowpoke', 79, 'WATER', 90, 65, 65, 40, 40, 15, true),
       ('Slowbro', 80, 'WATER', 95, 75, 110, 100, 80, 30, true),
       ('Magnemite', 81, 'ELECTRIC', 25, 35, 70, 95, 55, 45, true),
       ('Magneton', 82, 'ELECTRIC', 50, 60, 95, 120, 70, 70, true),
       ('Farfetch', 83, 'NORMAL', 52, 90, 55, 58, 62, 60, true),
       ('Doduo', 84, 'NORMAL', 35, 85, 45, 35, 35, 75, true),
       ('Dodrio', 85, 'NORMAL', 60, 110, 70, 60, 60, 110, true),
       ('Seel', 86, 'WATER', 65, 45, 55, 45, 70, 45, true),
       ('Dewgong', 87, 'WATER', 90, 70, 80, 70, 95, 70, true),
       ('Grimer', 88, 'POISON', 80, 80, 50, 40, 50, 25, true),
       ('Muk', 89, 'POISON', 105, 105, 75, 65, 100, 50, true),
       ('Shellder', 90, 'WATER', 30, 65, 100, 45, 25, 40, true),
       ('Cloyster', 91, 'WATER', 50, 95, 180, 85, 45, 70, true),
       ('Gastly', 92, 'GHOST', 30, 35, 30, 100, 35, 80, true),
       ('Haunter', 93, 'GHOST', 45, 50, 45, 115, 55, 95, true),
       ('Gengar', 94, 'GHOST', 60, 65, 60, 130, 75, 110, true),
       ('Onix', 95, 'ROCK', 35, 45, 160, 30, 45, 70, true),
       ('Drowzee', 96, 'PSYCHIC', 60, 48, 45, 43, 90, 42, true),
       ('Hypno', 97, 'PSYCHIC', 85, 73, 70, 73, 115, 67, true),
       ('Krabby', 98, 'WATER', 30, 105, 90, 25, 25, 50, true),
       ('Kingler', 99, 'WATER', 55, 130, 115, 50, 50, 75, true),
       ('Voltorb', 100, 'ELECTRIC', 40, 30, 50, 55, 55, 100, true),
       ('Electrode', 101, 'ELECTRIC', 60, 50, 70, 80, 80, 150, true),
       ('Exeggcute', 102, 'GRASS', 60, 40, 80, 60, 45, 40, true),
       ('Exeggutor', 103, 'GRASS', 95, 95, 85, 125, 65, 55, true),
       ('Cubone', 104, 'GROUND', 50, 50, 95, 40, 50, 35, true),
       ('Marowak', 105, 'GROUND', 60, 80, 110, 50, 80, 45, true),
       ('Hitmonlee', 106, 'FIGHTING', 50, 120, 53, 35, 110, 87, true),
       ('Hitmonchan', 107, 'FIGHTING', 50, 105, 79, 35, 110, 76, true),
       ('Lickitung', 108, 'NORMAL', 90, 55, 75, 60, 75, 30, true),
       ('Koffing', 109, 'POISON', 40, 65, 95, 60, 45, 35, true),
       ('Weezing', 110, 'POISON', 65, 90, 120, 85, 70, 60, true),
       ('Rhyhorn', 111, 'GROUND', 80, 85, 95, 30, 30, 25, true),
       ('Rhydon', 112, 'GROUND', 105, 130, 120, 45, 45, 40, true),
       ('Chansey', 113, 'NORMAL', 250, 5, 5, 35, 105, 50, true),
       ('Tangela', 114, 'GRASS', 65, 55, 115, 100, 40, 60, true),
       ('Kangaskhan', 115, 'NORMAL', 105, 95, 80, 40, 80, 90, true),
       ('Horsea', 116, 'WATER', 30, 40, 70, 70, 25, 60, true),
       ('Seadra', 117, 'WATER', 55, 65, 95, 95, 45, 85, true),
       ('Goldeen', 118, 'WATER', 45, 67, 60, 35, 50, 63, true),
       ('Seaking', 119, 'WATER', 80, 92, 65, 65, 80, 68, true),
       ('Staryu', 120, 'WATER', 30, 45, 55, 70, 55, 85, true),
       ('Starmie', 121, 'WATER', 60, 75, 85, 100, 85, 115, true),
       ('Mr. Mime', 122, 'PSYCHIC', 40, 45, 65, 100, 120, 90, true),
       ('Scyther', 123, 'BUG', 70, 110, 80, 55, 80, 105, true),
       ('Jynx', 124, 'PSYCHIC', 65, 50, 35, 115, 95, 95, true),
       ('Electabuzz', 125, 'ELECTRIC', 65, 83, 57, 95, 85, 105, true),
       ('Magmar', 126, 'FIRE', 65, 95, 57, 100, 85, 93, true),
       ('Pinsir', 127, 'BUG', 65, 125, 100, 55, 70, 85, true),
       ('Tauros', 128, 'NORMAL', 75, 100, 95, 40, 70, 110, true),
       ('Magikarp', 129, 'WATER', 20, 10, 55, 15, 20, 80, true),
       ('Gyarados', 130, 'WATER', 95, 125, 79, 60, 100, 81, true),
       ('Lapras', 131, 'WATER', 130, 85, 80, 85, 95, 60, true),
       ('Ditto', 132, 'NORMAL', 48, 48, 48, 48, 48, 48, true),
       ('Eevee', 133, 'NORMAL', 55, 55, 50, 45, 65, 55, true),
       ('Vaporeon', 134, 'WATER', 130, 65, 60, 110, 95, 65, true),
       ('Jolteon', 135, 'ELECTRIC', 65, 65, 60, 110, 95, 130, true),
       ('Flareon', 136, 'FIRE', 65, 130, 60, 95, 110, 65, true),
       ('Porygon', 137, 'NORMAL', 65, 60, 70, 85, 75, 40, true),
       ('Omanyte', 138, 'ROCK', 35, 40, 100, 90, 55, 35, true),
       ('Omastar', 139, 'ROCK', 70, 60, 125, 115, 70, 55, true),
       ('Kabuto', 140, 'ROCK', 30, 80, 90, 55, 45, 55, true),
       ('Kabutops', 141, 'ROCK', 60, 115, 105, 65, 70, 80, true),
       ('Aerodactyl', 142, 'ROCK', 80, 105, 65, 60, 75, 130, true),
       ('Snorlax', 143, 'NORMAL', 160, 110, 65, 65, 110, 30, true),
       ('Articuno', 144, 'ICE', 90, 85, 100, 95, 125, 85, true),
       ('Zapdos', 145, 'ELECTRIC', 90, 90, 85, 125, 90, 100, true),
       ('Moltres', 146, 'FIRE', 90, 100, 90, 125, 85, 90, true),
       ('Dratini', 147, 'DRAGON', 41, 64, 45, 50, 50, 50, true),
       ('Dragonair', 148, 'DRAGON', 61, 84, 65, 70, 70, 70, true),
       ('Dragonite', 149, 'DRAGON', 91, 134, 95, 100, 100, 80, true),
       ('Mewtwo', 150, 'PSYCHIC', 106, 110, 90, 154, 90, 130, true),
       ('Mew', 151, 'PSYCHIC', 100, 100, 100, 100, 100, 100, true),
       ('Turtwig', 387, 'GRASS', 55, 68, 64, 45, 55, 31, true),
       ('Grotle', 388, 'GRASS', 75, 89, 85, 55, 65, 36, true),
       ('Torterra', 389, 'GRASS', 95, 109, 105, 75, 85, 56, true),
       ('Chimchar', 390, 'FIRE', 44, 58, 44, 58, 44, 61, true),
       ('Monferno', 391, 'FIRE', 64, 78, 52, 78, 52, 81, true),
       ('Infernape', 392, 'FIRE', 76, 104, 71, 104, 71, 108, true),
       ('Piplup', 393, 'WATER', 53, 51, 53, 61, 56, 40, true),
       ('Prinplup', 394, 'WATER', 64, 66, 68, 81, 76, 50, true),
       ('Empoleon', 395, 'WATER', 84, 86, 88, 111, 101, 60, true),
       ('Starly', 396, 'NORMAL', 40, 55, 30, 30, 30, 60, true),
       ('Staravia', 397, 'NORMAL', 55, 75, 50, 40, 40, 80, true),
       ('Staraptor', 398, 'NORMAL', 85, 120, 70, 50, 60, 100, true),
       ('Bidoof', 399, 'NORMAL', 59, 45, 40, 35, 40, 31, true),
       ('Bibarel', 400, 'NORMAL', 79, 85, 60, 55, 60, 71, true),
       ('Kricketot', 401, 'BUG', 37, 25, 41, 25, 41, 25, true),
       ('Kricketune', 402, 'BUG', 77, 85, 51, 55, 51, 65, true),
       ('Shinx', 403, 'ELECTRIC', 45, 65, 34, 40, 34, 45, true),
       ('Luxio', 404, 'ELECTRIC', 60, 85, 49, 60, 49, 60, true),
       ('Luxray', 405, 'ELECTRIC', 80, 120, 79, 95, 79, 70, true),
       ('Budew', 406, 'GRASS', 40, 30, 35, 50, 70, 55, true),
       ('Roserade', 407, 'GRASS', 60, 70, 65, 125, 105, 90, true),
       ('Cranidos', 408, 'ROCK', 67, 125, 40, 30, 30, 58, true),
       ('Rampardos', 409, 'ROCK', 97, 165, 60, 65, 50, 58, true),
       ('Shieldon', 410, 'ROCK', 30, 42, 118, 42, 88, 30, true),
       ('Bastiodon', 411, 'ROCK', 60, 52, 168, 47, 138, 30, true),
       ('Burmy', 412, 'BUG', 40, 29, 45, 29, 45, 36, true),
       ('Wormadam', 413, 'BUG', 60, 59, 85, 79, 105, 36, true),
       ('Mothim', 414, 'BUG', 70, 94, 50, 94, 50, 66, true),
       ('Combee', 415, 'BUG', 30, 30, 42, 30, 42, 70, true),
       ('Vespiquen', 416, 'BUG', 70, 80, 102, 80, 102, 40, true),
       ('Pachirisu', 417, 'ELECTRIC', 60, 45, 70, 45, 90, 95, true),
       ('Buizel', 418, 'WATER', 55, 65, 35, 60, 30, 85, true),
       ('Floatzel', 419, 'WATER', 85, 105, 55, 85, 50, 115, true),
       ('Cherubi', 420, 'GRASS', 45, 35, 45, 62, 53, 35, true),
       ('Cherrim', 421, 'GRASS', 70, 60, 70, 87, 78, 85, true),
       ('Shellos', 422, 'WATER', 76, 48, 48, 57, 62, 34, true),
       ('Gastrodon', 423, 'WATER', 111, 83, 68, 92, 82, 39, true),
       ('Ambipom', 424, 'NORMAL', 75, 100, 66, 60, 66, 115, true),
       ('Drifloon', 425, 'GHOST', 90, 50, 34, 60, 44, 70, true),
       ('Drifblim', 426, 'GHOST', 150, 80, 44, 90, 54, 80, true),
       ('Buneary', 427, 'NORMAL', 55, 66, 44, 44, 56, 85, true),
       ('Lopunny', 428, 'NORMAL', 65, 76, 84, 54, 96, 105, true),
       ('Mismagius', 429, 'GHOST', 60, 60, 60, 105, 105, 105, true),
       ('Honchkrow', 430, 'DARK', 100, 125, 52, 105, 52, 71, true),
       ('Glameow', 431, 'NORMAL', 49, 55, 42, 42, 37, 85, true),
       ('Purugly', 432, 'NORMAL', 71, 82, 64, 64, 59, 112, true),
       ('Chingling', 433, 'PSYCHIC', 45, 30, 50, 65, 50, 45, true);


-- Insert data into berries table
INSERT INTO berries (name, index_number, description, growth_time, category_type, spicy_potency, dry_potency,
                     sweet_potency, bitter_potency, sour_potency)
VALUES ('Cheri', 1, 'A Poffin ingredient. It may be used or held by a Pokémon to recover from paralysis.', 3,
        'MEDICINE', 10, 0, 0, 0, 0),
       ('Chesto', 2, 'A Poffin ingredient. It may be used or held by a Pokémon to recover from sleep.', 3,
        'MEDICINE', 0, 10, 0, 0, 0),
       ('Pecha', 3, 'A Poffin ingredient. It may be used or held by a Pokémon to recover from poisoning.', 3,
        'MEDICINE', 0, 0, 10, 0, 0),
       ('Rawst', 4, 'A Poffin ingredient. It may be used or held by a Pokémon to recover from a burn.', 3,
        'MEDICINE', 0, 0, 0, 10, 0),
       ('Aspear', 5, 'A Poffin ingredient. It may be used or held by a Pokémon to recover from being frozen.', 3,
        'MEDICINE', 0, 0, 0, 0, 10);


-- Insert data into region_maps table
INSERT INTO region_maps (region_name, horizontal_axis, vertical_axis)
VALUES ('Kanto', 80, 60),
       ('Johto', 75, 55),
       ('Hoenn', 70, 40),
       ('Sinnoh', 60, 40);

-- Insert data into locations table
INSERT INTO locations (name, description, coordinate_X, coordinate_Y, region_map_id)
VALUES ('Pallet Town', 'A small town with a quiet atmosphere', 1, 1, 1),
       ('Viridian City', 'A beautiful city with a forest nearby', 2, 2, 1);

-- Insert data into berry_planting_sites table
INSERT INTO berry_planting_sites (description, soil_slots, location_id)
VALUES ('East gate berry planting site', 4, 1),
       ('Behind the lake in the North, currently unreachable due to a tree obstacle', 2, 2);

-- Insert data into berry_planting_site_slots table to map berries to planting slots
INSERT INTO berry_planting_site_slots (site_id, slot_number, planted_berries_by_slots_id)
VALUES (1, 1, 1), -- Cheri Berry
       (1, 2, 1), -- Cheri Berry
       (2, 1, 2);
-- Chesto Berry


-- Insert Games
INSERT INTO game (version_name, generation, description, region_map_id, user_id)
VALUES ('Diamond', 4, 'The first appearance of PoffinHouse', 4, 1),
       ('Blue', 1, 'Adventure of Misty', 1, 2);

INSERT INTO game_pokemon (game_id, pokemon_id)
SELECT 1, id
FROM pokemon
WHERE national_dex >= 387
  AND national_dex <= 493;

INSERT INTO game_pokemon (game_id, pokemon_id)
SELECT 2, id
FROM pokemon
WHERE national_dex >= 1
  AND national_dex <= 151;

INSERT INTO game_berry (game_id, berry_id)
VALUES (1, 1),
       (1, 2),
       (1, 3);


-- Insert data into owned_pokemon table to map users to their Pokémon
INSERT INTO owned_pokemon (pokemon_id, username, nickname, nature, caught_by_trainer_name, beauty, coolness, cuteness,
                           cleverness, toughness, game_id)
VALUES (74, 'Brock', null, 'HARDY', 'Brock', 10, 20, 15, 25, 50, 1),
       (95, 'Brock', 'Rocky', 'IMPISH', 'Brock', 15, 25, 20, 30, 55, 1),
       (120, 'Misty', null, 'CALM', 'Misty', 20, 30, 40, 50, 60, 2);


-- Insert data into team table
INSERT INTO team (description)
VALUES ('Team of GymLeader Brock');
INSERT INTO team (description)
VALUES ('Team of GymLeader Misty');

-- Insert data into team_owned_pokemon join table for Brock's Team
INSERT INTO team_owned_pokemon (team_id, owned_pokemon_id)
VALUES (1, 1), -- Geodude
       (1, 2); -- Onix

-- Insert data into team_owned_pokemon join table for Misty's Team
INSERT INTO team_owned_pokemon (team_id, owned_pokemon_id)
VALUES (2, 3); -- Staryu
