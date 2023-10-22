<#import '../layout.ftlh' as main>
<@main.layout>
    <div class="category-header">
        <a href="javascript:history.go(-1);" class="back-button">
            <i class="fas fa-arrow-left"></i>Назад
        </a>
    </div>
    <h1 class="text-center"> Ответить на запрос </h1>

    <h2>Запрос пользователя</h2>
    <div class="task-content">
        <#if post??>
            <div class="task-details mt-3 me-2">
                <div class="row">
                    <div class="col-2 user-photo">
                        <img src="${post.userPhoto}" alt="User Photo">
                    </div>
                    <div class="col-3 text-center">${post.userName}</div>
                </div>
                <hr>
                <div class="description">
                    <i class="fa-solid fa-calendar-days"></i> Когда: ${post.workRequiredTime}
                    <br>
                    ${post.title}
                    <br>
                    ${post.description}
                </div>
                <hr>
                <div class="response-info">
                    <div class="responses-count">
                        <p>Откликнулось специалистов: ${post.responseNumber}</p>
                        <p>Дата публикации: ${post.publishedDate}</p>
                    </div>

                </div>
            </div>
<#else>
Данный запрос больше не актуален.
</#if>
    </div>
    <div class="sent-messages d-flex flex-column align-items-end me-3 my-3">
        <!-- Messages will be displayed here -->
    </div>
    <div class="message-container">
        <form method="post" action="/stand/response/${post.id}" id="message-form" class="d-flex flex-column align-items-end me-3">
            <input type="text" id="message-text" class="msg-input" autofocus required>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                   value="${(_csrf.token)!'--no-token--'}"/>
            <button type="submit" class="respond-button">Отправить</button>
        </form>
    </div>
    <style>
        .category-card span {
            margin-top: 10px;
            font-size: 12px;
            font-weight: 600;
        }

        .task-content {
            display: flex;
            align-items: center;
        }

        .task-details {
            border: 1px solid #ccc;
            border-radius: 15px;
            padding: 30px;
            width: 100%;
        }


        .user-photo img {
            margin-right: 10px;
            width: 50px;
            height: 50px;
            object-fit: cover;
        }

        .description {
            margin-top: 10px;
        }

        .response-info {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 10px;
            flex-wrap: wrap;
        }

        .responses-count {
            color: #747373;
            margin-bottom: 20px;
        }

        .msg-input {
            width: 80%;
            border-radius: 15px;
            padding: 15px;
            box-shadow: 0 -5px 15px rgb(224, 220, 220);
            height: 100px;
            margin: 30px 0 10px;
        }

        .respond-button {
            padding: 15px 20px;
            color: #2552e7;
            border: 2px solid #2552e7;
            background-color: #fff;
            cursor: pointer;
            border-radius: 15px;
            text-decoration: none;
            width: 25%;
        }

        .respond-button:hover {
            background-color: #2552e7;
            color: #fff;
        }

        .message {
            margin: 5px 15px 5px;
            background-color: #2552e74a;
            border-radius: 15px;
            width: fit-content;
            padding: 5px 10px;
        }

        .back-button {
            text-decoration: none;
            color: #2552e7;
            display: flex;
            align-items: center;
            font-size: 18px;
            margin-right: 40px;
            margin-left: 10px;
        }
    </style>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const messageForm = document.getElementById('message-form');
            const messageText = document.getElementById('message-text');
            const sentMessages = document.querySelector('.sent-messages');

            messageForm.addEventListener('submit', function (e) {
                e.preventDefault();

                const message = messageText.value;
                const csrfToken = document.querySelector('input[name="_csrf"]').value;

                fetch(`/stand/response/${post.id}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'X-CSRF-TOKEN': csrfToken
                    },
                    body: JSON.stringify({ responseText: message })
                })
                    .then(response => {
                        const messageElement = document.createElement('div');
                        messageElement.classList.add('message');
                        messageElement.textContent = message;
                        sentMessages.appendChild(messageElement);
                        messageForm.reset();
                    })
                    .catch(error => {
                        console.error('Error:', error);
                    });
            });
        });

    </script>
</@main.layout>