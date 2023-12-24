CREATE TABLE music (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   title VARCHAR(255) NOT NULL,
   url VARCHAR(255) NOT NULL,
   tumbnail_url VARCHAR(255) NOT NULL,
   album_id BIGINT NOT NULL,
   CONSTRAINT pk_music PRIMARY KEY (id)
);