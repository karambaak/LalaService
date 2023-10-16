insert into ROLES(ROLE) values ( 'ROLE_SPECIALIST' ), ('ROLE_CUSTOMER');

insert into THEMES (THEME_NAME) values ( 'light'), ('dark');

insert into USERS (PHONE_NUMBER, USER_NAME, USER_TYPE, EMAIL, PASSWORD, ENABLED, ROLE_ID, THEME_ID, REGISTRATION_DATE)
VALUES ('123', 'Alex', 'SPECIALIST', '', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true,
        (SELECT id FROM roles WHERE role LIKE '%SPECIALIST'), (select id from THEMES where THEME_NAME = 'light'),
        '2023-10-15 15:30:00'),
       ('1234', 'Qwe', 'CUSTOMER', '', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true,
        (SELECT id FROM roles WHERE role LIKE '%CUSTOMER'), (select id from THEMES where THEME_NAME = 'light'),
        '2023-10-15 15:30:00');

