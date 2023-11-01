
INSERT INTO specialists (user_id, company_name, tariff_id)
VALUES
    (17, 'Facebook, Inc.', 1),
    (3, 'Tesla, Inc.', 1),
    (5, 'Intel Corporation', 1),
    (7, 'IBM Corporation', 1),
    (9, 'Oracle Corporation', 1),
    (11, 'Samsung Electronics', 1),
    (13, 'Ford Motor Company', 1),
    (15, 'General Electric', 1),
    (19, 'Procter & Gamble', 1),
    (18, 'Boeing', 1),
    (20, 'Walt Disney', 1),
    (21, 'Sony Corporation', 1),
    (22, 'McDonalds', 1),
  (23, 'Nike, Inc.', 1),
  (24, 'Coca-Cola', 1),
  (25, 'Nestle', 1);


-- Добавление 15 реальных резюме
INSERT INTO resumes (specialist_id, name, time_of_resume, resume_Description, category_id)
VALUES
    (1, 'Резюме Ивана Петрова', '2023-10-19 10:00:00', 'Опыт работы и квалификация',7),
    (2, 'Резюме Марины Сидоровой', '2023-10-19 11:00:00', 'Профессиональные достижения',8),
    (3, 'Резюме Андрея Иванова', '2023-10-19 12:00:00', 'Описание навыков и опыта',16),
    (4, 'Резюме Ольги Смирновой', '2023-10-19 13:00:00', 'Опыт в медицине',10),
    (5, 'Резюме Сергея Козлова', '2023-10-19 14:00:00', 'ИТ-специалист', 1),
    (6, 'Резюме Екатерины Ивановой', '2023-10-19 15:00:00', 'Преподаватель математики',16),
    (7, 'Резюме Дмитрия Петрова', '2023-10-19 16:00:00', 'Строительный инженер',17),
    (8, 'Резюме Натальи Смирновой', '2023-10-19 17:00:00', 'Менеджер по продажам',9),
    (9, 'Резюме Павла Козлова', '2023-10-19 18:00:00', 'Финансовый аналитик',3),
    (10, 'Резюме Марии Ивановой', '2023-10-19 19:00:00', 'Адвокат', 1),
    (11, 'Резюме Александра Петрова', '2023-10-19 20:00:00', 'Инженер-программист',5),
    (12, 'Резюме Елены Сидоровой', '2023-10-19 17:00:00', 'Маркетинговый менеджер',4),
    (13, 'Резюме Владимира Иванова', '2023-10-19 22:00:00', 'Разработчик веб-приложений', 2),
    (14, 'Резюме Татьяны Петровой', '2023-10-19 23:00:00', 'Продуктовый менеджер',3),
    (15, 'Резюме Игоря Смирнова', '2023-10-20 00:00:00', 'Инженер-электрик', 1);
