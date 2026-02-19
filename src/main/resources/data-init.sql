INSERT INTO app_user (email, password, admin) VALUES
    ('a@a.com', '$2a$10$5WEsvVInsp51juqbyGg.iuYdOjqzTCLRSN0P8SueniFMdousb2Dyi', 1),
    ('b@b.com', '$2a$10$5WEsvVInsp51juqbyGg.iuYdOjqzTCLRSN0P8SueniFMdousb2Dyi', 0);

INSERT INTO ticket(name, description, creator_id, assignee_id) VALUES
    ('Mon PC marche pas ;(', 'Ecran noir', 1, 2),
    ('L\'imprimante est HS', 'L\'imprimante n\'a plus d\'encre' , 1, null);

INSERT INTO comment(content, creator_id, ticket_id) VALUES
    ('Eteint puis rallume', 2 , 1),
    ('Ca marche du coup', 1 , 1);

