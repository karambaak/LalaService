<#import "../layout.ftlh" as main>
<@main.layout>
    <link rel="stylesheet" href="../../static/css/resumes.css">
    <link rel="stylesheet" href="../../static/css/layout.css">
    <div id="content" class="content">
        <div class="category-header">
            <a href="#" class="back-button">
                <i class="fas fa-arrow-left"></i>Назад
            </a>
        </div>

        <div class="resumes-list">
            <#if resumes?has_content>
                <#list resumes as resume>
                    <div class="resume">
                        <a href="/resumes/${resume.id}" class="resume-link">
                            <p class="resume-name">Resume name</p>
                            <p class="resume-experience">Опыт работы: 4 года</p>
                            <#--                            <p class="resume-category">Категория: ${resume.categoryId}</p>-->
                            <p class="resume-date">Дата создания: ${resume.timeOfResume}</p>
                        </a>
                        <div class="resume-buttons">
                            <a class="view-button" href="/resume/${resume.id}">Больше узнать</a>
                            <button class="favorite-button" id="favoriteButton">

                                <svg xmlns="http://www.w3.org/2000/svg" width="25"
                                     height="25" fill="currentColor"
                                     class="bi bi-bookmark" viewBox="0 0 16 16">
                                    <path
                                            d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v13.5a.5.5 0 0 1-.777.416L8 13.101l-5.223 2.815A.5.5 0 0 1 2 15.5V2zm2-1a1 1 0 0 0-1 1v12.566l4.723-2.482a.5.5 0 0 1 .554 0L13 14.566V2a1 1 0 0 0-1-1H4z"/>
                                </svg>
                            </button>
                        </div>
                    </div>
                </#list>
            </#if>
        </div>

        <div class="notification" id="notification">
            Добавлено в избранное
        </div>
    </div>
    <script>
        const favoriteButton = document.getElementById("favoriteButton")
        const succesNotification = document.getElementById("notification")

        // need logic to fetch query
        favoriteButton.addEventListener("click", function () {
            succesNotification.classList.add("show");
            setTimeout(() => {
                succesNotification.classList.remove("show");
            }, 4000);
        });

    </script>
</@main.layout>