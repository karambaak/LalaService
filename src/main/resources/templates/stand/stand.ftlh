<#import "../layout.ftlh" as main>
<@main.layout>
    <h1 class="text-center"> Стенд </h1>

    <div class="p-5">
        <div class="stand mb-4">
            <h2>Мои подписки</h2>
            <div class="category">${category.categoryName}</div>
            <ul class="tasks">
                <li class="task"> ${post.title}

                </li>
                <li class="task"> ${post.title}

                </li>
                <li class="task"> ${post.title}

                </li>
            </ul>
        </div>
        <div id="stand">
            <h2>Все категории</h2>
            <div class="category">${category.categoryName}</div>
            <ul class="tasks">
                <li class="task"> ${post.title}

                </li>
                <li class="task"> ${post.title}

                </li>
                <li class="task"> ${post.title}

                </li>
            </ul>


            <div class="category">Category 2</div>
            <ul class="tasks">
                <li class="task">Task 4</li>
            </ul>

            <div class="category">Category 3</div>
            <ul class="tasks">
                <li class="task">Task 8</li>
                <li class="task">Task 9</li>
            </ul>
        </div>
    </div>
    <style>
        .category {
            cursor: pointer;
            font-weight: bold;
            margin-bottom: 5px;
            border: 1px #ccc solid;
            border-radius: 15px;
            margin: 25px 10px;
            box-shadow: 5px -5px 15px rgba(0.0, 0.0, 0.1, 0.2);
            background-color: white;
            text-decoration: none;
            display: flex;
            flex-direction: row;
            align-items: center;
            padding: 25px;
            cursor: pointer;
        }
        .category:hover {
            background-color: #2552e7;
            color: white;
        }

        .category::after {
            padding-left: 30px;
            font-family: 'Font Awesome 5 Free';
            content: "\f196";
            margin-left: auto;
            font-size: 30px;
            color: #4b6ad0;
            /* Unicode for square-plus icon */
        }

        .category.collapsed::after {
            content: "\f147";
            /* Unicode for square-minus icon */
        }

        .badge {
            background-color: #4b6ad0;
            color: #fff;
            border-radius: 10px;
            padding: 6px 10px;
            margin-left: 15px;
            font-size: 15px;
        }

        #content {
            padding-bottom: 60px;
            /* Adjust this value to match the height of your tab bar */
            overflow-y: scroll;
            height: calc(100vh - 60px);
            /* Subtract the height of the tab bar from the viewport height */
        }

        .tasks {
            list-style-type: none;
            margin: 0;
            padding: 0;
            display: none;
        }

        .task {
            margin-left: 20px;
            padding: 10px 15px;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
            cursor: pointer;
            /* Make it clickable */
        }
        .task-content {
            display: flex;
            align-items: center;
        }
        .flag-icon {
            margin-right: 10px;
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

        .respond-button {
            padding: 15px 20px;
            background-color: #4b6ad0;
            color: #fff;
            border: none;
            cursor: pointer;
            border-radius: 15px;
            text-decoration: none;
        }

        .task::before {
            content: "\f11e";
            /* Unicode for fa-circle-check */
            font-family: 'Font Awesome 5 Free';
            margin-right: 20px;
            font-size: 20px;
        }

        .task:not(:last-child) {
            padding-bottom: 15px;
            border-bottom: 1px solid #ccc;
        }

        .task-content.hidden {
            display: none;
        }
    </style>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const respondLinks = document.querySelectorAll('.card-link');

            respondLinks.forEach(link => {
                link.addEventListener('click', function(event) {
                    event.preventDefault();

                    const card = this.closest('.card');
                    const commentField = document.createElement('textarea');
                    commentField.classList.add('response-comment');
                    commentField.setAttribute('rows', '4');
                    commentField.setAttribute('cols', '50');
                    card.appendChild(commentField);

                    const respondButton = document.createElement('button');
                    respondButton.classList.add('btn', 'btn-primary');
                    respondButton.innerText = 'Ответить';
                    card.appendChild(respondButton);

                    respondButton.addEventListener('click', function() {
                        const responseText = commentField.value;
                        const itemId = card.dataset.itemId;
                        sendResponse(itemId, responseText);
                    });
                });
            });

            function sendResponse(itemId, responseText) {
                fetch(`/stand/respond/${itemId}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ responseText })
                })
                    .then(response => response.json())
                    .then(data => {
                        // Handle the response from the backend if needed
                        console.log(data);
                    })
                    .catch(error => console.error('Error:', error));
            }
        });
        document.addEventListener('DOMContentLoaded', function () {
            let currentCategory = null;

            const categories = document.querySelectorAll('.category');

            categories.forEach(category => {
                const tasks = category.nextElementSibling.querySelectorAll('.task');
                const badge = document.createElement('span');
                badge.classList.add('badge');
                badge.textContent = tasks.length;
                category.appendChild(badge);

                category.addEventListener('click', function () {
                    const tasksContainer = this.nextElementSibling;
                    const isCollapsed = tasksContainer.style.display === 'none' ||
                        tasksContainer.style.display === '';

                    if (currentCategory && currentCategory !== this) {
                        const prevTasksContainer = currentCategory.nextElementSibling;
                        prevTasksContainer.style.display = 'none';
                        currentCategory.classList.remove('expanded-category');
                    }

                    tasksContainer.style.display = isCollapsed ? 'block' : 'none';
                    this.classList.toggle('collapsed');
                    this.classList.toggle('expanded-category');

                    tasks.forEach(task => {
                        const taskContent = task.querySelector('.task-content');
                        if (isCollapsed) {
                            taskContent.classList.add('hidden');
                        } else {
                            taskContent.classList.remove('hidden');
                        }
                    });

                    currentCategory = this;
                });
            });

            const userPhoto = "${post.userPhoto}";
            const workRequiredTime = "${post.workRequiredTime}";
            const userName = "${post.userName}";
            const title = "${post.title}";
            const description = "${post.description}";
            const responseNumber = "${post.responseNumber}";

            const tasks = document.querySelectorAll('.task');

            tasks.forEach(task => {
                const taskContent = document.createElement('div');
                taskContent.classList.add('task-content', 'hidden');

                taskContent.innerHTML = `
        <div class="task-details mt-3 me-2">
            <div class="row">
                <div class="col-2 user-photo">
                    <!-- Add user photo here -->
                    <img src="${userPhoto}" alt="User Photo">
                </div>
                <div class="col-3 text-center">${userName}</div>
            </div>
            <hr>
            <div class="description">
                <i class="fa-solid fa-calendar-days"></i> Когда: ${workRequiredTime}
                <br>
                ${title}
                <br>
                ${description}
            </div>
            <hr>
            <div class="response-info">
                <div class="responses-count">Количество откликов: ${responseNumber}</div>
                <div class="">
                    <!-- Add your respond button here -->
                    <a href="#respond" class="respond-button my-3">Откликнуться</a>
                </div>
            </div>
        </div>
        `;

                task.appendChild(taskContent);

                task.addEventListener('click', function () {
                    taskContent.classList.toggle('hidden');
                });

                taskContent.addEventListener('click', function (event) {
                    event.stopPropagation(); // Prevent task click event from firing
                });
            });
        });
    </script>
</@main.layout>


