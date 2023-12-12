insert into users (phone_number, user_name, user_type, email, password, enabled,
                   role_id, tariff_id, theme_id, geolocation_id,
                   registration_date, photo, reset_password_token) values
    ('222', 'Микеланджело Буанаротти', 'SPECIALIST', '', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true,
     1, 1, 1, 1, now(), 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/Miguel_Ángel%2C_por_Daniele_da_Volterra_%28detalle%29.jpg/1036px-Miguel_Ángel%2C_por_Daniele_da_Volterra_%28detalle%29.jpg',
     null),
    ('223', 'Дед Мороз', 'SPECIALIST', '', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true,
     1, 1, 1, 1, now(), 'https://upload.wikimedia.org/wikipedia/commons/5/59/Ded_Moroz.jpg', null),
    ('224', 'Бакыт Бакытов', 'SPECIALIST', '', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true,
     1, 1, 1, 1, now(), 'https://www.spectator.co.uk/wp-content/uploads/2021/01/Charles-Yu-©-Tina-Chiou._r1.jpg', null),
    ('225', 'Роза К.', 'CUSTOMER', '', '$2a$12$a72Udagvfj0P2ezcWE1yV.s7mMxn6SgJsyHWVOMegNmTN/WBayFbC', true,
     2, 1, 1, 1, now(), 'https://images-s.kinorium.com/persona/300/215.jpg', null);

insert into specialists (user_id, company_name, tariff_id) values
((select id from users where user_name = 'Микеланджело Буанаротти'), 'Art Studio Michelangelo', 1),
((select id from users where user_name = 'Дед Мороз'), 'Дед Мороз и Ко', 1),
((select id from users where user_name = 'Бакыт Бакытов'), 'Gaming Dev', 1);

insert into specialists_authorities (specialist_id, authority_id, date_start, date_end) values
((select id from specialists where company_name = 'Art Studio Michelangelo'), 1, now(), '2024-01-04 00:00:00.000000'),
((select id from specialists where company_name = 'Дед Мороз и Ко'), 1, now(), '2024-01-04 00:00:00.000000'),
((select id from specialists where company_name = 'Gaming Dev'), 1, now(), '2024-01-04 00:00:00.000000');


insert into resumes (specialist_id, name, resume_description, category_id, time_of_resume)
values ((select id from specialists where company_name = 'Art Studio Michelangelo'), 'Изготовление скульптур на заказ',
        'Изготовление скульптур любой сложности из мрамора, большой опыт и отличные рекомендации',
        (select id from categories where category_name = 'Разное'), now()),
       ((select id from specialists where company_name = 'Art Studio Michelangelo'), 'Изготовление картин и фресок на заказ',
        'Рисую картины и фрески в стиле Ренессанс любой сложности', (select id from categories where category_name = 'Разное'), now()),
    ((select id from specialists where company_name = 'Дед Мороз и Ко'), 'Ведущий праздников', 'Ведущий праздников для детей и взрослых',
     (select id from categories where category_name = 'Досуг'), now()),
       ((select id from specialists where company_name = 'Gaming Dev'), 'Разработка игр', 'Опытный разработчик игр',
        (select id from categories where category_name = 'Интернет-услуги'), now());

insert into portfolios (specialist_id, title, time_of_portfolio)
values ((select id from specialists where company_name = 'Art Studio Michelangelo'), 'Изготовление скульптур на заказ', now()),
        ((select id from specialists where company_name = 'Art Studio Michelangelo'), 'Изготовление картин и фресок на заказ', now()),
        ((select id from specialists where company_name = 'Дед Мороз и Ко'), 'Новогодник праздники с Дед Морозом', now()),
       ((select id from specialists where company_name = 'Gaming Dev'), 'Разработанные игры', now());


insert into photos (portfolio_id, photo_link, time_of_saving_photo)
values ((select id from portfolios where title = 'Изготовление скульптур на заказ'),
        'https://upload.wikimedia.org/wikipedia/commons/a/a0/%27David%27_by_Michelangelo_Fir_JBU002.jpg', now()),
       ((select id from portfolios where title = 'Изготовление скульптур на заказ'),
        'https://upload.wikimedia.org/wikipedia/commons/1/1f/Michelangelo%27s_Pieta_5450_cropncleaned_edit.jpg', now()),
       ((select id from portfolios where title = 'Изготовление скульптур на заказ'),
        'https://upload.wikimedia.org/wikipedia/commons/5/59/Michelangelo%27s_Moses_%28Rome%29.jpg', now()),
       ((select id from portfolios where title = 'Изготовление скульптур на заказ'),
        'https://upload.wikimedia.org/wikipedia/commons/b/b2/Buonarotti-scala.jpg', now()),
       ((select id from portfolios where title = 'Изготовление скульптур на заказ'),
        'https://upload.wikimedia.org/wikipedia/commons/4/49/Michelangelo_Brutus.jpg', now()),
    ((select id from portfolios where title = 'Изготовление картин и фресок на заказ'),
    'https://upload.wikimedia.org/wikipedia/commons/7/73/God2-Sistine_Chapel.png', now()),
       ((select id from portfolios where title = 'Изготовление картин и фресок на заказ'),
        'https://upload.wikimedia.org/wikipedia/commons/1/18/Last_Judgement_%28Michelangelo%29.jpg', now()),
    ((select id from portfolios where title = 'Новогодник праздники с Дед Морозом'), 'https://dobroman.ru/wp-content/uploads/2019/09/5645454_2.jpg', now()),
       ((select id from portfolios where title = 'Новогодник праздники с Дед Морозом'), 'https://regions.kidsreview.ru/sites/default/files/styles/oww/public/10/18/2017_-_1616/m1li5rh_kre.jpg', now()),
       ((select id from portfolios where title = 'Разработанные игры'), 'https://image.api.playstation.com/vulcan/img/cfn/11307uYG0CXzRuA9aryByTHYrQLFz-HVQ3VVl7aAysxK15HMpqjkAIcC_R5vdfZt52hAXQNHoYhSuoSq_46_MT_tDBcLu49I.png', now()),
       ((select id from portfolios where title = 'Разработанные игры'), 'https://assetsio.reedpopcdn.com/Dota-Going-On-An-Adventure-.jpg', now()),
       ((select id from portfolios where title = 'Разработанные игры'), 'https://i.ytimg.com/vi/xw-LQwvIqpc/maxresdefault.jpg', now());

insert into posts (user_id, category_id, title, description, work_required_time, published_date)
values ((select id from users where user_name = 'Роза К.'),
        (select id from categories where  category_name = 'Разное'), 'Требуется изготовить скульптуру', 'Скульптура будет стоять перед новым построенным корпусом университета', '2024-05-05 12:00', now()),
       ((select id from users where user_name = 'Роза К.'),
        (select id from categories where  category_name = 'Спорт'), 'Ищу группу для занятий йогой', 'Хочу присоединиться к группе с хороший инструктором', '2024-01-10 12:00', now());






