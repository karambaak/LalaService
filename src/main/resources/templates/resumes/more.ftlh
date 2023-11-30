<#import '../layout.ftlh' as main>
<@main.layout>
    <link rel="stylesheet" href="../../static/css/resume_more.css">
    <div class="header shadow">
        <a href="javascript:history.go(-1);" class="back-button">
            <i class="fas fa-arrow-left"></i>Назад
        </a>
        <button id="share-button">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-share"
                 viewBox="0 0 16 16">
                <path d="M13.5 1a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3zM11 2.5a2.5 2.5 0 1 1 .603 1.628l-6.718 3.12a2.499 2.499 0 0 1 0 1.504l6.718 3.12a2.5 2.5 0 1 1-.488.876l-6.718-3.12a2.5 2.5 0 1 1 0-3.256l6.718-3.12A2.5 2.5 0 0 1 11 2.5zm-8.5 4a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3zm11 5.5a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3z"/>
            </svg>
        </button>
        <button class="favorite-button" id="favoriteButton"
                data-specialist-id="${resume.specialistId}">

            <#if noUser??>
                <svg xmlns="http://www.w3.org/2000/svg" width="25"
                     height="25" fill="currentColor"
                     class="bi bi-bookmark" viewBox="0 0 16 16">
                    <path
                            d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v13.5a.5.5 0 0 1-.777.416L8 13.101l-5.223 2.815A.5.5 0 0 1 2 15.5V2zm2-1a1 1 0 0 0-1 1v12.566l4.723-2.482a.5.5 0 0 1 .554 0L13 14.566V2a1 1 0 0 0-1-1H4z"/>
                </svg>
            </#if>
        </button>

    </div>

    <div id="content" class="content">
        <div class="resume">
            <#if resume.header?has_content>
                <h1 class="resume-name">${resume.header}</h1>
            <#else>
                <h1 class="resume-name">Без заголовок</h1>
            </#if>
            <div class="resume-category">
                <#if resume.category?has_content>
                    <i class="fas fa-user-tie"></i> ${resume.category}
                <#else>
                    <i class="fas fa-user-tie"></i> не найдено
                </#if>
            </div>
            <div class="resume-experience">
                <#if resume.experience?has_content>
                    <i class="fas fa-briefcase"></i> ${resume.experience}
                <#else>
                    <i class="fas fa-briefcase"></i> не найдено
                </#if>
            </div>
            <p class="resume-description">
                <#if resume.description?has_content>
                    <i class="fas fa-info-circle"></i> ${resume.description}
                <#else>
                    <i class="fas fa-info-circle"></i> Без описания
                </#if>
            </p>
            <div class="resume-phone">
                <#if resume.contact?has_content>
                    <i class="fas fa-phone"></i> Телефон: <a href="tel+${resume.contact}">${resume.contact}</a>
                <#else>
                    <i class="fas fa-phone"></i> Телефон: телефон номер не указан
                </#if>
            </div>
            <div class="resume-buttons">
                <a href="#" class="view-button">Написать</a>
                <#if resume.contact?has_content>
                    <a href="tel+${resume.contact}" class="view-button">Позвонить</a>
                <#else>
                    <a class="view-button disabled">Позвонить</a>
                </#if>
            </div>
        </div>
        <div class="notification" id="notification">
            Добавлено в избранное
        </div>
    </div>
    <script>
        document.getElementById("share-button").addEventListener("click", function () {
            if (navigator.share) {
                navigator.share({
                    title: 'Заголовок вашей доли',
                    text: 'Описание вашей доли',
                    url: 'https://www.example.com'
                })
                    .then(() => console.log('Успешно поделено'))
                    .catch((error) => console.error('Ошибка поделения:', error));
            } else {
                alert('Ваш браузер не поддерживает функцию "Поделиться".');
            }
        });

        const favoriteButtons = document.querySelectorAll(".favorite-button");
        const successNotification = document.getElementById("notification");
        const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
        const csrfToken = document.querySelector('meta[name="_csrf_token"]').getAttribute('content');

        favoriteButtons.forEach(button => {
            button.addEventListener("click", async function () {
                const specialistId = button.getAttribute("data-specialist-id");

                try {
                    const response = await fetch(`/fav/add/` + specialistId, {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json",
                            [csrfHeader]: csrfToken,
                        },
                    });

                    if (response.status === 200) {
                        button.classList.add("favorite");
                        showSuccessNotification();
                    } else {
                        console.error("Failed to update favorites.");
                    }
                } catch (error) {
                    console.error("Error in updating favorites: " + error);
                }
            });
        });

        function showSuccessNotification() {
            successNotification.classList.add("show");
            setTimeout(() => {
                successNotification.classList.remove("show");
            }, 4000);
        }
    </script>
</@main.layout>