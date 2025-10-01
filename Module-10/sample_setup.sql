
-- OPTIONAL local practice script. DO NOT run in environment.
-- This is for the home DB only to create and seed a table.
-- The application code does not create or delete tables per our 10.2 assignment.

-- Create the database (optional if it already exists)
CREATE DATABASE IF NOT EXISTS databasedb;

USE databasedb;

-- Create the table (must match assignment spec)
CREATE TABLE IF NOT EXISTS fans (
  id INT PRIMARY KEY,
  firstname VARCHAR(25),
  lastname VARCHAR(25),
  favoriteteam VARCHAR(25)
);

-- Sample data
INSERT INTO fans (id, firstname, lastname, favoriteteam) VALUES
  (1, 'Ada', 'Lovelace', 'Analytical'),
  (2, 'Grace', 'Hopper', 'Compilers')
ON DUPLICATE KEY UPDATE
  firstname=VALUES(firstname),
  lastname=VALUES(lastname),
  favoriteteam=VALUES(favoriteteam);
