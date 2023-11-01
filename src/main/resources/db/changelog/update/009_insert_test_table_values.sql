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
    ('Пользователь 15', 'SPECIALIST', '151-151-1515', 'user15@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 1, (select id from THEMES where THEME_NAME = 'light')),
    ('Пользователь 16', 'SPECIALIST', '161-161-1616', 'user16@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 1, (select id from THEMES where THEME_NAME = 'light')),
    ('Пользователь 17', 'SPECIALIST', '171-171-1717', 'user17@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 1, (select id from THEMES where THEME_NAME = 'light')),
    ('Пользователь 18', 'SPECIALIST', '181-181-1818', 'user18@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 1, (select id from THEMES where THEME_NAME = 'light')),
    ('Пользователь 19', 'SPECIALIST', '191-191-1919', 'user19@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 1, (select id from THEMES where THEME_NAME = 'light')),
    ('Пользователь 20', 'SPECIALIST', '120-120-2020', 'user20@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 1, (select id from THEMES where THEME_NAME = 'light')),
    ('Пользователь 21', 'SPECIALIST', '121-121-2121', 'user21@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 1, (select id from THEMES where THEME_NAME = 'light')),
    ('Пользователь 22', 'SPECIALIST', '122-122-2222', 'user22@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 1, (select id from THEMES where THEME_NAME = 'light')),
    ('Пользователь 23', 'SPECIALIST', '123-123-2323', 'user23@example.com', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true, NOW(), 1, (select id from THEMES where THEME_NAME = 'light'));

insert into SPECIALISTS (TARIFF_ID, USER_ID, COMPANY_NAME)
VALUES (
           (select id from TARIFFS where TARIFF_NAME='бесплатный'),
           (select id from USERS where PHONE_NUMBER='123' ),
           'Services Co.'
       );

insert into PORTFOLIOS (SPECIALIST_ID, TITLE) VALUES ( 1, 'Примеры моих работ');

insert into PHOTOS (PORTFOLIO_ID, PHOTO_LINK) VALUES ( 1, 'https://www.imperialhotel.ru/upload/iblock/f7b/photo_2021-06-30_13-27-56.jpg' ),
                                                     (1, 'https://upload.wikimedia.org/wikipedia/commons/e/ef/Basingstoke_Sports_Centre_Pool.jpg');

insert into RESUMES (CATEGORY_ID, SPECIALIST_ID, TIME_OF_RESUME, RESUME_DESCRIPTION)
VALUES (
           (select  id from  CATEGORIES where CATEGORY_NAME like 'Строительные работы%'),
           1, '2023-10-18 12:00:00', 'строительство бассейнов, срок строительства от 6 мес., от $20000'), (
           (select id from CATEGORIES where CATEGORY_NAME like 'Строительные работы и ремонт'), 1, '2023-10-19 12:00:00', 'сантех-работы');

insert into FAVOURITES (SPECIALIST_ID, USER_ID) VALUES ( 1, 2 );

insert into SUBSCRIPTION_STAND (CATEGORY_ID, SPECIALIST_ID) VALUES ( (select id from CATEGORIES where CATEGORY_NAME like 'Строительные работы%'), 1 );

insert into RATINGS (USER_ID, SPECIALIST_ID, RATING, REVIEW_TEXT, RATING_DATE)
VALUES ( 2, 1, 5,'Специалист заменил трубы в ванной комнате. работа выполнена отлично', '2023-10-18 12:00:00'  ),
(2, 1, 3, 'Специалист сделал работу некачественно', '2023-10-18 12:00:00');

insert into POSTS (CATEGORY_ID, PUBLISHED_DATE, USER_ID, WORK_REQUIRED_TIME, DESCRIPTION, TITLE)
VALUES ( (select  id from  CATEGORIES where CATEGORY_NAME like 'Строительные работы%'), '2023-10-15 10:00:00', 2, '2023-10-17 10:00:00', 'Требуется устранить утечку труб', 'Требуется сантехник' );


