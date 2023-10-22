insert into POSTS (USER_ID, CATEGORY_ID, TITLE, DESCRIPTION, WORK_REQUIRED_TIME, PUBLISHED_DATE)
VALUES (3,
        (select id from CATEGORIES where CATEGORY_NAME like 'Компьютеры'),
        'Установка windows', 'Требуется установить windows  на компьютер', '2023-10-23 10:00:00', '2023-10-21 10:00:00'),
       (5,
        (select id from CATEGORIES where CATEGORY_NAME like 'Красота и Здоровье'),
        'Женская прическа', 'Требуется мастер с выездом на дом', '2023-10-23 15:00:00','2023-10-21 15:00:00' );