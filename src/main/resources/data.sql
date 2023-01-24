-- informatii despre club (clubul este unicat)
INSERT INTO club (id, vision, history, trophy)
VALUES (1, 'text viziune club', 'text istorie club', 'text prezentare trofee');

-- informatii sponsori clubul cu id 1
INSERT INTO sponsors (id, name, logo, link_site, edition, club_id)
VALUES (2, 'usv', 'logo usv', 'www.usv.ro', 'editia 1', 1);
INSERT INTO sponsors (id, name, logo, link_site, edition, club_id)
VALUES (3, 'primaria', 'logo primarie', 'www.primarie.ro', 'editia 1', 1);

-- informatii echipa
INSERT INTO team (id, logo, name, team_type, club_id)
VALUES(4, 'logo echipa', 'USV', 'SENIORI', 1);

-- informatii antrenor echipa principala
INSERT INTO coach (id, name, first_name, nationality, position, birth_date, description, team_id)
VALUES (5, 'Test', 'Antrenor', 'RO', 'Principal', '1990-01-01', 'antrenorul principal', 4);

-- informatii profil jucator
INSERT INTO player (id, name, first_name, nationality, position, birth_date, height, description, team_id)
VALUES (6, 'Test', 'Jucator', 'RO', 'Capitan', '2002-01-01', 183, 'jucatorul capitan', 4);

