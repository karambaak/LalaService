<#import "../layout.ftlh" as main>
<@main.layout>
    <h1 class="text-center">Стенд</h1>
    <div>
<#if posts??>
<#list posts as item>
        <div class="card" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title">Тема: ${item.title}</h5>
                <h6 class="card-subtitle mb-2 text-muted">Категория: ${item.category}</h6>
                <h6 class="card-subtitle mb-2 text-muted">Время, когда требуется услуга: ${item.workRequiredTime}</h6>
                <p class="card-text"><b>${item.userName}</b> ${item.description}</p>
                <p class="card-text">${item.publishedDate}</p>
                <a href="/stand/respond/${item.id}" class="card-link">Откликнуться</a>
                <a href="#" class="card-link">Another link</a>
            </div>
        </div>
</#list>
</#if>
    </div>
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
                        const itemId = card.dataset.itemId; // Assuming you have a data attribute for item ID
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

    </script>
</@main.layout>


