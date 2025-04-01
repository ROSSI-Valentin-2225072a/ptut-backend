INSERT INTO QUOTE(QUOTE, QUOTE_LAST_USE) VALUES
                                             ('Tou bi ore naute tou bi', '2020-02-12'),
                                             ('Zumba cafew, cafew carnaval', '2025-03-27'),
                                             ('Cest pas faux', '2020-06-12'),
                                             ('Cest faux', '2020-06-12'),
                                             ('Jsuis dans le 4x4 teinté pisté par la banal', '2020-06-12');

INSERT INTO EVENT(NOM_EVENT, DATE_EVENT, DESCRIPTION) VALUES
                                                          ('Soirée', '2020-02-12', 'Soirée chez oxa'),
                                                          ('RAID ISIS', '2025-03-27', 'Attaque'),
                                                          ('RAID', '2020-06-12', 'Le sport la en fait'),
                                                          ('mffbvlkn', '2020-06-12', 'qljfbvln'),
                                                          ('qkjb', '2020-06-12', 'dkvjbskjvb');

INSERT INTO TYPE(LIBELLE) VALUES
                                 ('Sport'),
                                 ('Attaque'),
                                 ('Soirée'),
                                 ('Culturel'),
                                 ('Autre');

INSERT INTO EVENT_TYPE(EVENT_ID, TYPE_ID) VALUES
                                              (1, 3),
                                              (2, 2),
                                              (3, 1),
                                              (4, 5),
                                              (5, 5);