<#import "../layout.ftlh" as main>

<@main.layout>
    <div class="msg">
        <button class="tablinks-1">Сообщения</button>
        <button class="tablinks-2">Уведомления</button>
    </div>

    <div id="Messages" class="tabcontent">
        <h3 class="my-3">Сообщения</h3>
        <#if messages??>
            <#list messages as item>
                <a class="message-card mb-3" href="/msg/${item.id}">
                    <div class="photo mx-4">
                        <img src="${item.senderPhoto}" alt="user photo">
                    </div>
                    <div class="text">
                        <b> ${item.senderName} </b> <#if (item.lastMessageText)??>${item.lastMessageText}</#if>
                        <p class="ms-auto">
                            <#if (item.lastMessageDateTime)??>${item.lastMessageDateTime}</#if></p>
                    </div>
                        <span  class="delete-icon" style="color: black;"><i style="margin-left: 20px" class="fas fa-trash-alt"></i></span>
                </a>
            </#list>
        </#if>


        <a href="/msg/new" class="btn fixed-button"><i class="fas fa-pen"></i>
        </a>

    </div>

    <div id="Notifications" class="tabcontent">
        <h3>Уведомления</h3>
        <#if notifications??>
            <#list notifications as item>
                <div class="notification-card mb-4" onclick="toggleCardExpansion(this)">
                    ${item.notificationText}
                    <br>
                    ${item.notificationDate}
                    <a href="msg/delete/${item.id}">
                        <span class="delete-icon" style="color: black;"><i class="fas fa-trash-alt"></i></span>
                    </a>
                </div>

            </#list>
        </#if>

    </div>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #ffffff;
            color: #333;
            margin: 0;
            padding: 0;
        }

        /* Убираем округление навбара и добавляем тень только на верхней части */
        .tab-container {
            background-color: rgb(255, 255, 255);
            border-top-left-radius: 18px;
            /* Только верхнее левое округление */
            border-top-right-radius: 18px;
            /* Только верхнее правое округление */
            box-shadow: 0px 5px 15px rgba(0.0, 0.0, 0.1, 0.4);
            /* Тень только в верхней части */
            overflow: hidden;
            margin: 0;
            padding: 0;
            position: fixed;
            bottom: 0;
            left: 0;
            width: 100%;
            z-index: 1;
        }


        .tab-bar {
            background-color: #ffffff;
            display: flex;
            justify-content: space-around;
            padding: 10px 0;
        }

        .tab {
            color: #2552e7;
            text-decoration: none;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 5px;
            cursor: pointer;
        }

        .tab i {
            font-size: 24px;
        }

        .content {
            padding: 20px;
        }

        .searchBox {
            display: flex;
            justify-content: center;
            background: #ffffff;
            max-width: 80%;
            width: 100%;
            margin: 0 auto;
            height: 40px;
            border-radius: 20px;
            padding: 10px;
            box-shadow: 0px 0px 15px rgba(0.0, 0.0, 0.1, 0.1);
        }

        .searchInput {
            border: none;
            background: none;
            outline: none;
            padding: 0;
            color: rgb(0, 0, 0);
            font-size: 16px;
            transition: 0.4s;
            line-height: 40px;
            padding-left: 10px;
            opacity: 60%;
            width: 100%;
        }

        .category-container {
            margin-top: 50px;
            display: flex;
            flex-wrap: wrap;
            justify-content: flex-start;
        }

        .category-card {
            background-color: white;
            border-radius: 15px;
            box-shadow: 0px 0px 10px rgba(0.0, 0.0, 0.2, 0.2);
            margin: 10px;
            flex-basis: calc(33.33% - 20px);
            /* Три карточки в ряду на больших экранах */
            max-width: 100px;
            /* Три карточки в ряду на маленьких экранах */
            text-align: center;
            display: flex;
            height: 90px;
            flex-direction: column;
            justify-content: center;
        }

        .category-card:hover {
            background-color: #2552e7;
            color: white;
        }

        @media screen and (min-width: 769px) {
            .category-container {
                justify-content: center;
            }

            .tab-container {
                background-color: white;
                border-radius: 15px;
                box-shadow: 0px -5px 15px rgba(0.0, 0.0, 0.1, 0.2);
                overflow: hidden;
                margin: 0;
                padding: 0;
                position: fixed;
                bottom: 0;
                left: 50%;
                transform: translateX(-50%);
                margin-bottom: 30px;
                width: 100%;
                max-width: 600px;
            }

            .searchBox {
                max-width: 50%;
            }
        }

        .category-card i {
            font-size: 18px;
            /* Увеличьте размер иконки */
            color: #2552e7;
            /* Цвет иконки */
            margin-bottom: 10px;
        }

        .category-card span {
            margin-top: 10px;
            font-size: 12px;
            font-weight: 600;
        }

        /* Style the tabs */
        .msg {
            overflow: hidden;
            border: 1px solid #ccc;
            background-color: #f9f9f9;
            display: flex;
        }

        /* Style the buttons inside the tab */
        .msg button {
            background-color: inherit;
            display: inline-block;
            width: 100%;
            border: none;
            outline: none;
            cursor: pointer;
            padding: 14px 16px;
            transition: 0.3s;
            font-size: 17px;
        }

        /* Change background color of buttons on hover */
        .msg button:hover {
            background-color: #2552e7;
            color: white;
        }

        /* Create an active/current tablink class */
        .msg button.active {
            background-color: #2552e7;
            color: white;
        }

        /* Style the tab content */
        .tabcontent {
            display: none;
            padding: 12px 25px;
            border: 1px solid #ccc;
            border-top: none;
            position: relative;
            height: auto;
            min-height: 80vh;
        }

        .message-card {
            background-color: white;
            border-radius: 15px;
            box-shadow: 0px 0px 10px rgba(0.0, 0.0, 0.2, 0.2);
            margin: 10px;
            color: black;
            text-decoration: none;
            display: flex;
            height: 90px;
            flex-direction: row;
            padding: 20px 30px;
        }

        .fixed-button {
            position: absolute;
            background-color: #2552e7;
            color: white;
            padding: 15px 20px;
            bottom: 50px;
            right: 40px;
            border-radius: 5px;
        }

        .fixed-button:hover {
            background-color: #2552e7;
        }

        .text {
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }

        .photo img {
            width: 50px;
            height: 50px;
            object-fit: cover;
            /* This property ensures that the image covers the entire container while maintaining aspect ratio */
        }

        .notification-card {
            background-color: white;
            border-radius: 15px;
            box-shadow: 0px 0px 10px rgba(0.0, 0.0, 0.2, 0.2);
            margin: 10px;
            padding: 20px 30px;
            overflow: hidden;
            transition: height 0.3s ease;
            max-height: 90px;
        }

        .expanded {
            max-height: none;
        }

    </style>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const messagesTab = document.getElementById('Messages');
            const notificationsTab = document.getElementById('Notifications');

            // Function to show Messages tab
            function showMessagesTab() {
                messagesTab.style.display = 'block';
                notificationsTab.style.display = 'none';
            }

            // Function to show Notifications tab
            function showNotificationsTab() {
                messagesTab.style.display = 'none';
                notificationsTab.style.display = 'block';
            }

            // Add event listeners to the buttons
            const tablinks1 = document.querySelector('.tablinks-1');
            const tablinks2 = document.querySelector('.tablinks-2');

            tablinks1.addEventListener('click', showMessagesTab);
            tablinks2.addEventListener('click', showNotificationsTab);
            tablinks1.click();


            const messageCards = document.querySelectorAll('.message-card');

            messageCards.forEach(function (card) {
                const href = card.getAttribute('href');
                const msgId = href.split('/').pop();

                if (msgId.startsWith('response-')) {
                    card.addEventListener('click', function (event) {
                        event.preventDefault();
                        window.location.href = `/stand/show/` + msgId;
                    });
                } else {
                    card.addEventListener('click', function (event) {
                        event.preventDefault();
                        window.location.href = `/msg/` + msgId;
                    });
                }
            });
        });


    </script>
</@main.layout>