insert into ROLES(ROLE) values ( 'ROLE_SPECIALIST' ), ('ROLE_CUSTOMER');

insert into THEMES (THEME_NAME) values ( 'light'), ('dark');

insert into CATEGORIES (CATEGORY_NAME, DESCRIPTION)
VALUES ( 'Компьютеры', 'Ремонт и обсуживание' ),
       ('Интернет-услуги', 'IT, SMM, реклама в интернете'),
       ('Мобильная связь и устройства', 'Ремонт, установка терминалов'),
       ('Авто-Мото-Вело', 'Пассажирские и грузовые перевозки, ремонт, техобслуживание, другое'),
       ('Строительные работы и ремонт', 'сантех-работы, электро-работы, отделочные работы, другое'),
       ('Спорт', 'спротивные секции, фитнес, йога, другое'),
       ('Досуг', 'организация мероприятий и праздников, гостиницы, кафе, рестораны, театры, другое'),
       ('Образование', 'учебные заведения, курсы, репетиторы, детские сады'),
       ('Заграница', 'турфирмы, доставка товаров, визовая поддержка, авиакассы'),
       ('Красота и Здоровье', 'стоматологии, ЛФК, салоны красоты, другое'),
       ('Оружие, охрана и самооборона', 'установка видеонаблюдения, противопожарной системы, охранные агентства'),
       ('Финансы', 'ломбарды, консалтинг, другое'),
       ('Фото, видео и аудио', 'фотографы, съемка рекламных роликов, звукорежиссеры, другое'),
       ('Реклама и полиграфия', 'рекламные агентства, наружная реклама, графические дизайнеры, другое'),
       ('Юридические услуги', 'ноториусы, адвокаты, перевод документов'),
       ('Пошив и ремонт одежды', 'швейные ателье, обувные мастерские'),
       ('Разное', 'бытовые услуги, клининговый сервис, аренда помещений, прокат оборудования и другое');

insert into TARIFFS (TARIFF_NAME, COST, AVAILABILITY)
VALUES ( 'бесплатный', 0, true );

insert into USERS (PHONE_NUMBER, USER_NAME, USER_TYPE, EMAIL, PASSWORD, ENABLED, ROLE_ID, THEME_ID, REGISTRATION_DATE)
VALUES ('123', 'Alex', 'SPECIALIST', '', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true,
        (SELECT id FROM roles WHERE role LIKE '%SPECIALIST'), (select id from THEMES where THEME_NAME = 'light'),
        '2023-10-15 15:30:00'),
       ('1234', 'Qwe', 'CUSTOMER', '', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true,
        (SELECT id FROM roles WHERE role LIKE '%CUSTOMER'), (select id from THEMES where THEME_NAME = 'light'),
       '2023-10-15 15:30:00');
