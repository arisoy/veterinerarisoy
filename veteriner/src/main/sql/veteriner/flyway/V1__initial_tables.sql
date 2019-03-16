CREATE TABLE veteriner.animal
(
pk				INTEGER				NOT NULL PRIMARY KEY,
name 			VARCHAR(64)			NOT NULL UNIQUE,
create_time		TIMESTAMP			NOT NULL DEFAULT now(),
height			DECIMAL				NOT NULL,
weight			DECIMAL				NOT NULL,
eyeColor		VARCHAR(64)			NOT NULL
);

