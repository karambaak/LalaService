<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Оставить рейтинг и отзыв</title>
    <link rel="stylesheet" href="../../static/css/rating.css">
</head>
<body>
<div class="rating-form">
    <h1>Оставьте свой рейтинг и отзыв</h1>
    <form method="post" action="/rating/new/${specialistId}">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                   value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div class="stars">
            <span>Ваш рейтинг:</span>
            <div class="star">
                <select name="ratingValue" id="rating">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>

            </div>
        </div>
        <div class="review">
            <label for="review">Ваш отзыв:</label>
            <textarea id="review" name="reviewText" rows="4" cols="50"></textarea>
        </div>
        <button id="submit-button">Отправить</button>
    </form>
    <div class="message" id="message"></div>
</div>

<script>
    // document.addEventListener("DOMContentLoaded", function () {
    //     const submitButton = document.getElementById("submit-button");
    //     const message = document.getElementById("message");
    //
    //     submitButton.addEventListener("click", () => {
    //         const rating = document.getElementById("rating");
    //         const review = document.getElementById("review").value;
    //
    //         if (!rating.value) {
    //             showMessage("Пожалуйста, выберите рейтинг.");
    //             return;
    //         }
    //
    //         fetch("/rating/submit-rating-review", {
    //             method: "POST",
    //             headers: {
    //                 "Content-Type": "application/json",
    //             },
    //             body: JSON.stringify({ rating: rating.value, review: review }),
    //         })
    //             .then(response => response.json())
    //             .then(data => {
    //                 showMessage("Ваш рейтинг и отзыв отправлены.");
    //             })
    //             .catch(error => {
    //                 showMessage("Произошла ошибка при отправке.");
    //             });
    //     });
    //
    //     function showMessage(text) {
    //         message.textContent = text;
    //         setTimeout(() => {
    //             message.textContent = "";
    //         }, 3000);
    //     }
    // });
</script>
</body>
</html>
