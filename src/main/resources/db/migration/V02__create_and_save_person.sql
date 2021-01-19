CREATE TABLE person (
	id IDENTITY NOT NULL PRIMARY KEY,
	person_name VARCHAR NOT NULL,
	street VARCHAR,
	street_number VARCHAR,
	aditional VARCHAR,
	zip_code VARCHAR,
	city VARCHAR,
	estate VARCHAR,
	active BOOLEAN NOT NULL
);