<#import "../layout.ftlh" as main>
<@main.layout>
    <style>

        .form-group {
            margin: 20px 0;
        }

        label {
            display: block;
            font-weight: bold;
        }

        input[type="text"],
        input[type="email"],
        input[type="tel"],
        select {
            width: 100%;
            padding: 10px;
            border: none;
            border-bottom: 2px solid #2552e7;
            margin-top: 5px;
        }

        input[type="text"]::placeholder,
        input[type="email"]::placeholder,
        input[type="tel"]::placeholder,
        select::placeholder {
            color: #aaa;
        }

        button {
            background-color: #2552e7;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 10px;
        }

    </style>
    <div id="content" class="content">
        <form id="profile-form">
            <div class="form-group">
                <!-- <label for="name">Имя пользователя:</label> -->
                <input type="text" id="name" name="name"
                       placeholder="Введите ваше имя" required>
            </div>
            <div class="form-group">
                <!-- <label for="email">Email:</label> -->
                <input type="email" id="email" name="email"
                       placeholder="example@example.com" required>
            </div>
            <div class="form-group">
                <!-- <label for="phone">Телефон номер:</label> -->
                <input type="tel" id="phone" name="phone" placeholder="Введите номер телефона" required pattern="[0-9]+" title="Введите только цифры">
            </div>
            <div class="form-group">
                <!-- <label for="city">Город:</label> -->
                <select id="city" name="city" required>
                    <option value="Москва">Москва</option>
                    <option value="Санкт-Петербург">Санкт-Петербург</option>
                    <option value="Екатеринбург">Екатеринбург</option>
                    <!-- Добавьте другие города по аналогии -->
                </select>
            </div>
            <button type="submit">Сохранить</button>
        </form>

    </div>
</@main.layout>