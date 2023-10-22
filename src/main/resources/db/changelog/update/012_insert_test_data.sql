INSERT INTO users (user_name, user_type, phone_number, email, password, enabled, registration_date, role_id, theme_id)
VALUES
    ('Пользователь 1', 'SPECIALIST', '111-111-1111', 'user1@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 1, (select id from THEMES where THEME_NAME = 'light')),
    ('Пользователь 2', 'CUSTOMER', '222-222-2222', 'user2@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 2, (select id from THEMES where THEME_NAME = 'light')),
    ('Пользователь 3', 'SPECIALIST', '333-333-3333', 'user3@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 1, (select id from THEMES where THEME_NAME = 'light')),
    ('Пользователь 4', 'CUSTOMER', '444-444-4444', 'user4@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 2, (select id from THEMES where THEME_NAME = 'light')),
    ('Пользователь 5', 'SPECIALIST', '555-555-5555', 'user5@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 1, (select id from THEMES where THEME_NAME = 'light')),
    ('Пользователь 6', 'CUSTOMER', '666-666-6666', 'user6@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 2, (select id from THEMES where THEME_NAME = 'light')),
    ('Пользователь 7', 'SPECIALIST', '777-777-7777', 'user7@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 1, (select id from THEMES where THEME_NAME = 'light')),
    ('Пользователь 8', 'CUSTOMER', '888-888-8888', 'user8@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 2, (select id from THEMES where THEME_NAME = 'light')),
    ('Пользователь 9', 'SPECIALIST', '999-999-9999', 'user9@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 1, (select id from THEMES where THEME_NAME = 'light')),
    ('Пользователь 10', 'CUSTOMER', '101-101-1010', 'user10@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 2, (select id from THEMES where THEME_NAME = 'light')),
    ('Пользователь 11', 'SPECIALIST', '111-111-11112', 'user11@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 1, (select id from THEMES where THEME_NAME = 'light')),
    ('Пользователь 12', 'CUSTOMER', '121-121-1212', 'user12@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 2, (select id from THEMES where THEME_NAME = 'light')),
    ('Пользователь 13', 'SPECIALIST', '131-131-1313', 'user13@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 1, (select id from THEMES where THEME_NAME = 'light')),
    ('Пользователь 14', 'CUSTOMER', '141-141-1414', 'user14@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 2, (select id from THEMES where THEME_NAME = 'light')),
    ('Пользователь 15', 'SPECIALIST', '151-151-1515', 'user15@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 1, (select id from THEMES where THEME_NAME = 'light'));


INSERT INTO specialists (user_id, company_name, tariff_id, geolocation_id)
VALUES
    (17, 'Facebook, Inc.', 1, 1),
    (2, 'Tesla, Inc.', 1, 1),
    (3, 'Intel Corporation', 1, 1),
    (4, 'IBM Corporation', 1, 1),
    (5, 'Oracle Corporation', 1, 1),
    (6, 'Samsung Electronics', 1, 1),
    (7, 'Ford Motor Company', 1, 1),
    (8, 'General Electric', 1, 1),
    (9, 'Procter & Gamble', 1, 1),
    (10, 'Boeing', 1, 1),
    (11, 'Walt Disney', 1, 1),
    (12, 'Sony Corporation', 1, 1),
    (13, 'McDonalds', 1, 1),
  (14, 'Nike, Inc.', 1, 1),
  (15, 'Coca-Cola', 1, 1),
  (16, 'Nestle', 1, 1);


-- Добавление 15 реальных резюме
INSERT INTO resumes (specialist_id, name, time_of_resume, resume_Description, category_id)
VALUES
    (1, 'Резюме Ивана Петрова', '2023-10-19 10:00:00', 'Опыт работы и квалификация', 11),
    (2, 'Резюме Марины Сидоровой', '2023-10-19 11:00:00', 'Профессиональные достижения', 12),
    (3, 'Резюме Андрея Иванова', '2023-10-19 12:00:00', 'Описание навыков и опыта', 20),
    (4, 'Резюме Ольги Смирновой', '2023-10-19 13:00:00', 'Опыт в медицине', 14),
    (5, 'Резюме Сергея Козлова', '2023-10-19 14:00:00', 'ИТ-специалист', 5),
    (6, 'Резюме Екатерины Ивановой', '2023-10-19 15:00:00', 'Преподаватель математики', 20),
    (7, 'Резюме Дмитрия Петрова', '2023-10-19 16:00:00', 'Строительный инженер', 21),
    (8, 'Резюме Натальи Смирновой', '2023-10-19 17:00:00', 'Менеджер по продажам', 13),
    (9, 'Резюме Павла Козлова', '2023-10-19 18:00:00', 'Финансовый аналитик', 7),
    (10, 'Резюме Марии Ивановой', '2023-10-19 19:00:00', 'Адвокат', 5),
    (11, 'Резюме Александра Петрова', '2023-10-19 20:00:00', 'Инженер-программист', 9),
    (12, 'Резюме Елены Сидоровой', '2023-10-19 21:00:00', 'Маркетинговый менеджер', 8),
    (13, 'Резюме Владимира Иванова', '2023-10-19 22:00:00', 'Разработчик веб-приложений', 6),
    (14, 'Резюме Татьяны Петровой', '2023-10-19 23:00:00', 'Продуктовый менеджер', 7),
    (15, 'Резюме Игоря Смирнова', '2023-10-20 00:00:00', 'Инженер-электрик', 5);
