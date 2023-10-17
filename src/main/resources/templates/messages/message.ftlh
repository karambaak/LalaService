<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="msg">
        <button class="tablinks" onclick="openTab(event, 'Messages')">Сообщения</button>
        <button class="tablinks" onclick="openTab(event, 'Notifications')">Уведомления</button>
    </div>

    <div id="Messages" class="tabcontent">
        <h3 class="my-3">Сообщения</h3>
        <a class="message-card mb-3" href="#message/${message.id}">
            <div class="photo mx-4">
                <img src="${message.senderPhoto}" alt="user photo">
            </div>
            <div class="text">
                <b> ${message.senderName} </b> <br>
                ${message.lastMessage}
            </div>
        </a>


        <a href="#newmessage" class="btn fixed-button"><i class="fas fa-pen"></i>
        </a>

    </div>

    <div id="Notifications" class="tabcontent">
        <h3>Уведомления</h3>
        <div class="notification-card mb-4" onclick="toggleCardExpansion(this)">
            ${notification.notificationText}
            <br>
            ${notification.notificationDate}
        </div>

    </div>
    <style>
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
            background-color: #ccc;
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
            background-color: #ccc;
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
        // Function to switch between tabs
        function openTab(evt, tabName) {
            var i, tabcontent, tablinks;
            tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
            }
            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" active", "");
            }
            document.getElementById(tabName).style.display = "block";
            evt.currentTarget.className += " active";
        }
        // Simulate a click on the Messages button to select it by default
        document.getElementsByClassName("tablinks")[0].click();

        document.addEventListener('DOMContentLoaded', function () {
            const messageCards = document.querySelectorAll('.message-card');

            messageCards.forEach(function (card) {
                card.addEventListener('click', function (event) {
                    event.preventDefault(); // Prevent the default link behavior

                    const messageID = this.getAttribute('href').split('/').pop();

                    // Assuming you have a function to fetch the conversation data by message ID
                    const conversationData = fetchConversationData(messageID);

                    // Update the content of the #Messages tab
                    const messagesTabContent = document.getElementById('Messages');
                    messagesTabContent.innerHTML = `
                <h3 class="my-3">Сообщения</h3>
                <div class="conversation">
                    ${renderConversation(conversationData)}
                </div>
            `;
                });
            });

            // Assuming you have a function to fetch conversation data
            function fetchConversationData(messageID) {
                // Implement this function to fetch the conversation data by message ID
            }

            // Assuming you have a function to render the conversation
            function renderConversation(conversationData) {
                // Implement this function to generate the HTML for the conversation
            }
        });

        function toggleCardExpansion(card) {
            card.classList.toggle('expanded');
        }
    </script>
</@main.layout>