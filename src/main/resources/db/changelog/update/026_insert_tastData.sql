insert into PHOTOS (portfolio_id, photo_link)
VALUES (1, 'https://www.imperialhotel.ru/upload/iblock/f7b/photo_2021-06-30_13-27-56.jpg'),
       (7, 'https://upload.wikimedia.org/wikipedia/commons/e/ef/Basingstoke_Sports_Centre_Pool.jpg');

delete
from resumes
where id = 1;
delete
from resumes
where id = 2;

insert into RESUMES (CATEGORY_ID, SPECIALIST_ID, TIME_OF_RESUME, RESUME_DESCRIPTION, name)
VALUES ((select id from CATEGORIES where CATEGORY_NAME ilike 'Строительные работы и ремонт'),
        1, '2023-10-18 12:00:00',
        'строительство бассейнов, срок строительства от 6 мес., от $20000', 'mr. Smith'),
       ((select id from CATEGORIES where CATEGORY_NAME ilike 'Строительные работы и ремонт'),
        1, '2023-10-19 12:00:00', 'сантех-работы', 'mrs. Smith');

insert into FAVOURITES (SPECIALIST_ID, USER_ID)
VALUES (1, 2);

insert into SUBSCRIPTION_STAND (CATEGORY_ID, SPECIALIST_ID)
VALUES ((select id from CATEGORIES where CATEGORY_NAME like 'Строительные работы%'), 1);

insert into RATINGS (USER_ID, SPECIALIST_ID, RATING, REVIEW_TEXT, RATING_DATE)
VALUES (2, 1, 5, 'Специалист заменил трубы в ванной комнате. работа выполнена отлично', '2023-10-18 12:00:00'),
       (2, 1, 3, 'Специалист сделал работу некачественно', '2023-10-18 12:00:00');

insert into POSTS (CATEGORY_ID, PUBLISHED_DATE, USER_ID, WORK_REQUIRED_TIME, DESCRIPTION, TITLE)
VALUES ((select id from CATEGORIES where CATEGORY_NAME like 'Строительные работы%'), '2023-10-15 10:00:00', 2,
        '2023-10-17 10:00:00', 'Требуется устранить утечку труб', 'Требуется сантехник');
