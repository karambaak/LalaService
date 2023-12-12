<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Управление пользователями</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Custom Styles */
        body {
            background-color: #f8f9fa;
        }

        .user-card {
            margin-bottom: 20px;
            padding: 20px;
            border-radius: 8px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease-in-out;
        }

        .user-card:hover {
            transform: translateY(-5px);
        }

        .btn-group {
            margin-top: 15px;
        }

        .sort-dropdown {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <h1 class="text-center mt-4 mb-5">Управление пользователями</h1>


    <div class="row">
        <div class="col-md-8 offset-md-2">

            <#list usersAndResumesId? keys as userg>
                <div class="user-card">
                    <h3>${userg.userName}</h3>
                    <p>Email:${userg.email} </p>
                    <p>Роль: ${userg.role}</p>
                    <div class="btn-group" role="group" aria-label="Действия с пользователем">
                        <button type="button" class="btn btn-danger mr-2">Заблокировать</button>
                        <button type="button" class="btn btn-primary mr-2">Подробнее</button>
                    </div>
                </div>
            </#list>


        </div>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>

<!-- Скрипт для обработки выбора параметра сортировки (пока заглушка) -->
<script>
    $(document).ready(function () {
        $('.dropdown-item').on('click', function () {
            var selectedSort = $(this).text();
            alert('Выбрана сортировка по: ' + selectedSort);
            // Добавьте здесь логику сортировки по выбранному параметру
        });
    });
</script>
</body>
</html>
