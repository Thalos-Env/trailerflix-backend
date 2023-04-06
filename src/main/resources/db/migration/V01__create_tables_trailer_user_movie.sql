CREATE TABLE tb_user (
	id VARCHAR(40) PRIMARY KEY NOT NULL,
	trailers_id VARCHAR(10000),
	name VARCHAR(40) NOT NULL,	
	email VARCHAR(40) NOT NULL,	
	profile VARCHAR(5) NOT NULL,
	registration_date VARCHAR(20) NOT NULL
);

CREATE TABLE tb_movie (
	id BIGINT PRIMARY KEY NOT NULL,
	trailers_id VARCHAR(255)
);

CREATE TABLE tb_trailer (
	id VARCHAR(40) PRIMARY KEY NOT NULL,
	user_id CHAR(40) NOT NULL,
	movie_id BIGINT NOT NULL,
	url VARCHAR(100) NOT NULL,	
	release_date VARCHAR(20) NOT NULL,
	upload_date VARCHAR(20) NOT NULL,
	edit_date VARCHAR(20) NOT NULL,
	
	FOREIGN KEY (user_id) REFERENCES tb_user(id),
    FOREIGN KEY (movie_id) REFERENCES tb_movie(id)
);

CREATE TRIGGER insertTrailerIdFromTablesMovieAndUser AFTER INSERT ON tb_trailer
FOR EACH ROW
BEGIN
    UPDATE tb_movie SET trailers_id = IF(trailers_id IS NULL OR trailers_id = '', NEW.id, CONCAT(trailers_id, '#', NEW.id))
    WHERE id = NEW.movie_id;
    
    UPDATE tb_user SET trailers_id = IF(trailers_id IS NULL OR trailers_id = '', NEW.id, CONCAT(trailers_id, '#', NEW.id))
    WHERE id = NEW.user_id;
END
