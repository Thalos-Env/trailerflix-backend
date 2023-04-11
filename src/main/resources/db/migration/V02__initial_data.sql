
INSERT INTO tb_user(id, name, email, profile, registration_date)
VALUES
('c5acb98d-8d6c-4bc7-b15b-ddf86800df47', 'Thalia', 'thalia@teste.com', 'admin', now()),
('68b5f11a-846e-4d35-ae15-efd1c3ad1b29', 'Renan', 'renan@teste.com', 'admin', now()),
('f00d4a1e-fe4c-4535-a7b2-e74b4aa41179', 'Murilo', 'murilo@teste.com', 'admin', now()),
('0a2a51ac-2904-418b-a1b1-3999695c28ec', 'Ana', 'ana@teste.com', 'user', now()),
('5dc17b99-0f31-41a5-98c4-4183f1885085', 'Paulo', 'paulo@teste.com', 'user', now());

INSERT INTO tb_movie(id)
VALUES
('315162'),
('505642'),
('850871');

INSERT INTO tb_trailer(id, user, movie, url, release_date, upload_date, edit_date)
VALUES
('6f4aa12a-ec2d-475a-be73-2a2b06de4b34', '5dc17b99-0f31-41a5-98c4-4183f1885085', '315162', 'https://www.youtube.com/watch?v=qtHcEfpYIow', now(), now(), now()),
('9de63ace-903a-4344-b52a-c0b802680377', '0a2a51ac-2904-418b-a1b1-3999695c28ec', '315162', 'https://www.youtube.com/watch?v=QAcn7cWm_hc', now(), now(), now()),
('1550ea6a-98a5-4338-b05e-2d590df00dd0', 'f00d4a1e-fe4c-4535-a7b2-e74b4aa41179', '315162', 'https://www.youtube.com/watch?v=sLk94T-hS78', now(), now(), now()),
('79d70055-ac51-4356-9006-60306b329786', 'c5acb98d-8d6c-4bc7-b15b-ddf86800df47', '505642', 'https://www.youtube.com/watch?v=9En82LLppvg', now(), now(), now()),
('615fae61-7c52-4397-be2a-fd356a83b174', 'c5acb98d-8d6c-4bc7-b15b-ddf86800df47', '850871', 'https://www.youtube.com/watch?v=QaqLBfOf0Cg', now(), now(), now());
