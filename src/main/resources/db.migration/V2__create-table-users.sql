CREATE TABLE users (
	id TEXT PRIMARY KEY UNIQUE NOT NOLL,
	login TEXT NOT NULL UNIQUE,
	password TEXT NOT NULL,
	role TEXT NOT NULL
);