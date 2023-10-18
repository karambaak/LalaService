<#macro layout>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link rel="stylesheet" href="/static/styles/static.css">
        <script src="https://kit.fontawesome.com/198548d859.js" crossorigin="anonymous"></script>
    </head>
    <body>

    <div class="container">
        <nav class="tab-bar">
            <a href="#main" class="tab" id="home">
                <i class="fas fa-home"></i>
                <!-- <span>Главный экран</span> -->
            </a>
            <a href="#stand" class="tab">
                <i class="fas fa-store"></i>
                <!-- <span>Стенд</span> -->
            </a>
            <a href="#notifications" class="tab">
                <i class="fas fa-bell"></i>
                <!-- <span>Уведомления</span> -->
            </a>
            <a href="#messages" class="tab">
                <i class="fas fa-envelope"></i>
                <!-- <span>Сообщения</span> -->
            </a>
            <a href="#profile" class="tab">
                <i class="fas fa-user"></i>
                <!-- <span>Личный кабинет</span> -->
            </a>
        </nav>
    </div>
    <div id="content" class="content">
        <#nested>
    </div>

    </body>
    </html>

</#macro>