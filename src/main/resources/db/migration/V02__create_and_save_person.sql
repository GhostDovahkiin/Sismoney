CREATE TABLE pessoa (
	id IDENTITY NOT NULL PRIMARY KEY,
	person_name VARCHAR(50) NOT NULL,
	street VARCHAR(30),
	street_number VARCHAR(30),
	aditional VARCHAR(30),
	zip_code VARCHAR(30),
	city VARCHAR(30),
	estate VARCHAR(30),
	active BOOLEAN NOT NULL
);