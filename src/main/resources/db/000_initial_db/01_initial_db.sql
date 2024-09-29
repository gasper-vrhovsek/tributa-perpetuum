create type tax_type as enum ('GENERAL', 'WINNINGS');
create type tax_calc_type as enum ('PER_RATE', 'PER_AMOUNT');

create table if not exists locations
(
    id uuid PRIMARY KEY,
    name varchar(255) NOT NULL,
    tax_type   tax_type NOT NULL,
    tax_calc   tax_calc_type NOT NULL,
    tax        decimal NOT NULL
);

create table if not exists traders
(
    id         uuid PRIMARY KEY,
    location_id uuid NOT NULL,
    name       varchar(255) NOT NULL,
    CONSTRAINT fk_location_id
        FOREIGN KEY(location_id)
            REFERENCES locations(id)
);



-- INSERTS
INSERT INTO locations (id, name, tax_type, tax_calc, tax)
VALUES ('619b2e19-30fe-4267-9269-a3634428f631', 'Slovenia', 'WINNINGS', 'PER_RATE', 0.10),
       ('a42583dd-9aab-4a66-90c9-176839e85049', 'Germany', 'GENERAL', 'PER_RATE', 0.30),
       ('fe58281a-6318-411f-9409-fa0d2f284fa8', 'United States', 'WINNINGS', 'PER_AMOUNT', 2.00),
       ('9e3cfdb8-3be1-4f65-a1b2-9dd29c8fc9ae', 'UAE', 'GENERAL', 'PER_AMOUNT', 4.00);


INSERT INTO traders (id, name, location_id)
VALUES ('b6a14eda-a6dd-461a-bbbc-cd5d27646881', 'Trader A', '619b2e19-30fe-4267-9269-a3634428f631'),
       ('c7b25fdb-b7ee-572b-cccd-de6e38757992', 'Trader B', 'fe58281a-6318-411f-9409-fa0d2f284fa8'),
       ('40a82048-312d-4fba-b7f4-7e9bfb87ff13', 'Trader C', '619b2e19-30fe-4267-9269-a3634428f631'),
       ('58099627-14bf-4d39-8ab9-31f5d53a8580', 'Trader D', 'a42583dd-9aab-4a66-90c9-176839e85049'),
       ('50aad14b-9f8c-4241-9bcb-ea1f0849fb1d', 'Trader E', '9e3cfdb8-3be1-4f65-a1b2-9dd29c8fc9ae');
