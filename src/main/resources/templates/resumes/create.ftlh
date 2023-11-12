<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Добавление резюме</title>
    <link rel="stylesheet" href="styles.css">
</head>
<style>
    .content {
        max-width: 600px;
        margin: 0 auto;
        margin-top: 100px;
        padding: 20px;
        background-color: #fff;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }

    form {
        display: flex;
        flex-direction: column;
    }

    label {
        font-weight: bold;
        margin-top: 10px;
    }

    input[type="text"],
    input[type="number"],
    select,
    textarea {
        padding: 10px;
        margin: 5px 0;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 16px;
    }

    input[type="submit"] {
        background-color: #2552e7;
        color: #fff;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 18px;
    }

    input[type="submit"]:hover {
        background-color: #1c3c80;
    }

    .buttons {
        margin-top: 20px;
        display: flex;
        justify-content: center;
    }

    .cancel {
        background-color: #2552e7;
        color: #fff;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 18px;
        text-decoration: none;
        margin-right: 20px;
    }
</style>
<body>
<div class="content">
    <form action="/resume/create" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

        <label for="resumeTitle">Заголовок резюме:</label>
        <input type="text" id="resumeTitle" name="header" required>

        <label for="resumeExperience">Опыт работы (в годах):</label>
        <input type="date" id="resumeExperience" name="resumeExperience">
        <label for="resumeExperience">Опыт работы (в годах):</label>
        <input type="date" id="resumeExperience" name="resumeExperience">

        <label for="resumeCategory">Категория резюме:</label>
        <select id="resumeCategory" name="categoryId" required>
            <#if categories?has_content>
                <#list categories as category>
                    <option value="${category.id}">${category.categoryName}</option>
                </#list>
            </#if>
        </select>

        <label for="resumeDescription">Описание резюме:</label>
        <textarea id="resumeDescription" name="resumeDescription"
                  rows="4" required></textarea>

        <div class="buttons">
            <a href="javascript:history.go(-1);" class="cancel">Отмена</a>
            <input type="submit" value="Сохранить">
        </div>
    </form>
</div>
</body>
</html>