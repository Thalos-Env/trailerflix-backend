INSERT INTO tb_user(id, name, email, profile, registration_date)
VALUES
('4e19e4a6-6fba-4c48-b94b-45f3c6e25595', 'Thalia', 'thalia@teste.com', 'admin', now()),
('fdaa02c2-12d6-4ae4-99f9-60400667bffb', 'Renan', 'renan@teste.com', 'admin', now()),
('5e63bec7-603a-47c3-9358-d4e5684f73bb', 'Murilo', 'murilo@teste.com', 'admin', now()),
('e456fc97-4d71-4cf1-bffc-b54ab5df3a7e', 'Ana', 'ana@teste.com', 'user', now()),
('5b7419ac-edb1-4a37-b61b-a02a49af9939', 'Paulo', 'paulo@teste.com', 'user', now());

INSERT INTO tb_movie(id)
VALUES
('315162'),
('505642'),
('850871');

INSERT INTO tb_trailer(id, collaborator_id, movie_id, url, release_date, upload_date, edit_date)
VALUES
('6f4aa12a-ec2d-475a-be73-2a2b06de4b34', '4e19e4a6-6fba-4c48-b94b-45f3c6e25595', '315162', 'https://www.youtube.com/watch?v=qtHcEfpYIow', now(), now(), now()),
('9de63ace-903a-4344-b52a-c0b802680377', '5e63bec7-603a-47c3-9358-d4e5684f73bb', '315162', 'https://www.youtube.com/watch?v=QAcn7cWm_hc', now(), now(), now()),
('1550ea6a-98a5-4338-b05e-2d590df00dd0', 'e456fc97-4d71-4cf1-bffc-b54ab5df3a7e', '315162', 'https://www.youtube.com/watch?v=sLk94T-hS78', now(), now(), now()),
('79d70055-ac51-4356-9006-60306b329786', 'fdaa02c2-12d6-4ae4-99f9-60400667bffb', '505642', 'https://www.youtube.com/watch?v=9En82LLppvg', now(), now(), now()),
('615fae61-7c52-4397-be2a-fd356a83b174', 'fdaa02c2-12d6-4ae4-99f9-60400667bffb', '850871', 'https://www.youtube.com/watch?v=QaqLBfOf0Cg', now(), now(), now());

