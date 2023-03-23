CREATE TABLE tb_user (
	id CHAR(36) PRIMARY KEY,
	trailer_id CHAR(36),
	name VARCHAR(255),	
	email VARCHAR(255),	
	profile VARCHAR(255),
	registration_date VARCHAR(20)
);

CREATE TABLE tb_trailer (
	id CHAR(36) PRIMARY KEY,
	collaborator_id CHAR(36),
	movie_id BIGINT,
	url VARCHAR(255),	
	release_date VARCHAR(20),
	upload_date VARCHAR(20),
	edit_date VARCHAR(20)
);

CREATE TABLE tb_movie (
	id BIGINT PRIMARY KEY,
	trailer_id CHAR(36)
);

ALTER TABLE tb_movie ADD FOREIGN KEY (trailer_id) REFERENCES tb_trailer(id);
ALTER TABLE tb_trailer ADD FOREIGN KEY (movie_id) REFERENCES tb_movie(id);
ALTER TABLE tb_trailer ADD FOREIGN KEY (collaborator_id) REFERENCES tb_user(id);
ALTER TABLE tb_user ADD FOREIGN KEY (trailer_id) REFERENCES tb_trailer(id);

CREATE TRIGGER insertTrailerIdIntoTableMovie AFTER INSERT ON tb_trailer
FOR EACH ROW
BEGIN
    UPDATE tb_movie SET trailer_id = NEW.id
    WHERE id = NEW.movie_id;
    
    UPDATE tb_user SET trailer_id = NEW.id
    WHERE id = NEW.collaborator_id;
END
