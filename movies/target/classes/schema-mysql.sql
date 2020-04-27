CREATE TABLE IF NOT EXISTS movie(
  id INTEGER(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  genre VARCHAR(100) NOT NULL,
  description VARCHAR(1000) NOT NULL,
  rating DOUBLE NOT NULL,
  director VARCHAR(100) NOT NULL,
  icon  VARCHAR(200) NOT NULL,
  durations DOUBLE NOT NULL
);