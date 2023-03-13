CREATE TABLE tb_movie (
	id BIGINT PRIMARY KEY
);

CREATE TABLE tb_user (
	id BIGINT PRIMARY KEY,
	name VARCHAR(255),	
	email VARCHAR(255),	
	profile VARCHAR(255),
	registration_date DATE
);

CREATE TABLE tb_trailer (
	id BIGINT PRIMARY KEY,
	movie_id BIGINT,
	collaboration_id BIGINT,
	url VARCHAR(255),	
	release_date DATE,
	upload_date DATE,
	edit_date DATE,
	
	FOREIGN KEY (movie_id) REFERENCES tb_user(id),
	FOREIGN KEY (movie_id) REFERENCES tb_movie(id)
);
