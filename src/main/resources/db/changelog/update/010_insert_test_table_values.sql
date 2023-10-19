delete from CATEGORIES;
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


insert into GEOLOCATION (CITY, COUNTRY) VALUES ( 'Бишкек', 'Кыргызстан' );
insert into TARIFFS (AVAILABILITY, COST, TARIFF_NAME) VALUES ( true, 0.0, 'free' );
insert into SPECIALISTS (GEOLOCATION_ID, TARIFF_ID, USER_ID, COMPANY_NAME)
VALUES (
           (select id from GEOLOCATION where CITY='Бишкек'),
           (select id from TARIFFS where TARIFF_NAME='free'),
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

insert into REVIEWS (REVIEW_DATE, SPECIALIST_ID, USER_ID, REVIEW_TEXT) VALUES ( '2023-10-18 12:00:00', 1, 2, 'Специалист заменил трубы в ванной комнате. работа выполнена отлично' ),
                                                                              ('2023-10-17 11:00:00', 1, 2, 'Специалист сделал работу некачественно');
insert into RATINGS (RATING, RATING_DATE, SPECIALIST_ID, USER_ID) VALUES ( 5, '2023-10-18 12:00:00', 1, 2 ), (3, '2023-10-18 12:00:00', 1, 2);

insert into POSTS (CATEGORY_ID, PUBLISHED_DATE, USER_ID, WORK_REQUIRED_TIME, DESCRIPTION, TITLE)
VALUES ( (select  id from  CATEGORIES where CATEGORY_NAME like 'Строительные работы%'), '2023-10-15 10:00:00', 2, '2023-10-17 10:00:00', 'Требуется устранить утечку труб', 'Требуется сантехник' );


