<#import "../layout.ftlh" as main>
<@main.layout>
<link rel="stylesheet" href="../../static/css/profile.css">
<div id="content" class="content">
    <div class="col">
        <div id="menu-toggle" class="menu-toggle">
            <svg xmlns="http://www.w3.org/2000/svg" width="40"
                 height="40" fill="currentColor" class="bi bi-list"
                 id="openModal"
                 viewBox="0 0 16 16">
                <path fill-rule="evenodd"
                      d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
            </svg>
        </div>
    </div>
    <div class="profile">
        <div class="profile-header row">
            <div class="profile-image-block col-4">
                <#if user.photo?has_content>
                    <img src="${user.photo}"
                         class="round-image" alt>
                <#else>
                    <img src="default.jpg" class="round-image" alt="default photo">
                </#if>
                <#--                <img src="${qrCode}">-->
            </div>
            <div class="personal-info col-6">
                <#if user.userName?has_content>
                    <p class="username">${user.userName}</p>
                </#if>
                <#if user.email?has_content>
                    <p>${user.email}</p>
                </#if>
                <#if user.phoneNumber?has_content>
                    <p>${user.phoneNumber}</p>
                </#if>
                <#if user.city?has_content>
                    <p>Ваш город: ${user.city}</p>
                </#if>
                <#if rating?has_content>
                    <div class="rating">
                        <span class="star fas fa-star"></span>
                        <span class="star fas fa-star"></span>
                        <span class="star fas fa-star"></span>
                        <span class="star fas fa-star"></span>
                        <span class="star fas fa-star"></span>
                        <span class="rating-number" id="rating">${rating}</span>
                    </div>
                </#if>
            </div>
        </div>
    </div>

    <div>
        <#if user.role = "ROLE_SPECIALIST">
            <div class="tabs">
                <button class="tab-item" id="resume-tab">Резюме</button>
                <button class="tab-item" id="portfolio-tab">Портфолио</button>
            </div>

            <div class="tab-content" id="resume-content">
                <div class="resume-list">
                    <div class="resume create-resume">
                        <a href="#" class="add-resume-link">
                            <p class="add-resume-text">Добавить резюме</p>
                            <i class="fas fa-plus"></i>
                        </a>
                    </div>
                    <#if resumes?has_content>
                        <#list resumes as resume>
                            <div class="resume">
                                <a href="#${resume.id}" class="resume-link">
                                    <#if resume.header?has_content>
                                        <p class="resume-name">${resume.header}</p>
                                        <#else>
                                        <p class="resume-name">Без заголовок</p>
                                    </#if>
                                    <#--                                    <p class="resume-experience">Опыт работы: ${resume.experience}</p>-->
                                    <#--                                    <p class="resume-category">Категория: ${resume.category}</p>-->
                                    <p class="resume-date">Дата создания: ${resume.timeOfResume}</p>
                                </a>
                            </div>
                        </#list>
                    </#if>
                </div>
            </div>

            <div class="tab-content" id="portfolio-content">
                <div class="portfolio-list">
                    <div class="resume create-resume">
                        <a href="#" class="add-resume-link">
                            <p class="add-resume-text">Добавить портфолио</p>
                            <i class="fas fa-plus"></i>
                        </a>
                    </div>
                    <#if portfolios?has_content>
                        <#list portfolios as portfolio>
                            <div class="portfolio">
                                <img src="${portfolio.photo}" alt class="portfolio-img">
                                <div>
                                    <p>${portfolio.name}</p>
                                    <p>Дата публикации: ${portfolio.createdDate}</p>
                                </div>
                            </div>
                        </#list>
                    </#if>
                </div>
            </div>
        <#elseif user.role = "ROLE_CUSTOMER">
            <div class="tabs">
                <button class="tab-item" id="portfolio-tab">Ваши запросы на стенд</button>
            </div>

            <div class="tab-content" id="resume-content">
                <div class="resume-list">
                    <div class="resume create-resume">
                        <a href="#" class="add-resume-link">
                            <p class="add-resume-text">Новый запрос </p>
                            <i class="fas fa-plus"></i>
                        </a>
                    </div>
                    <#if stands?has_content>
                        <#list stands as stand>
                            <div class="resume">
                                <a href="#${stand.id}" class="resume-link">
                                    <p class="resume-name">${stand.name}</p>
                                    <p class="resume-category">Категория: ${stand.category}</p>
                                    <p class="resume-date">Дата создания: ${stand.createdDate}</p>
                                </a>
                            </div>
                        </#list>
                    </#if>
                </div>
            </div>
        </#if>
    </div>
    <div id="modal" class="modal">
        <div class="modal-content">
            <div id="side-menu" class="side-menu">
                <div class="close-button" id="close-button">X</div>
                <ul>
                    <li><a href="#">Редактировать профиль</a></li>
                    <li><a href="#">Изменить город</a></li>
                    <li><a href="/logout">Выйти из аккаунта</a></li>
                </ul>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>

        $(document).ready(function () {
            $("#resume-content").show();
            $("#resume-tab").addClass("active");

            $("#resume-tab").click(function () {
                $(".tab-item").removeClass("active");
                $(this).addClass("active");
                $(".tab-content").hide();
                $("#resume-content").show();
            });

            $("#portfolio-tab").click(function () {
                $(".tab-item").removeClass("active");
                $(this).addClass("active");
                $(".tab-content").hide();
                $("#portfolio-content").show();
            });
        });

        const xCloseButton = document.getElementById("close-button").addEventListener("click", closeModal)

        document.getElementById("openModal").addEventListener("click", function () {
            openModal();
        });

        document.getElementById("close-button").addEventListener("click", function () {
            document.getElementById("side-menu").classList.remove("active");
        });

        function openModal() {
            document.getElementById("modal").style.display = "block";
        }

        function closeModal() {
            document.getElementById("modal").style.display = "none";
        }

        window.onclick = function (event) {
            var modal = document.getElementById("modal");
            if (event.target === modal) {
                closeModal();
            }
        };


        let ratingNum = document.getElementById("rating").textContent
        ratingNum = ratingNum.replace(",", ".");

        const rating = Math.round(parseFloat(ratingNum));
        const maxRating = 5;

        const ratingContainer = document.querySelector('.rating');
        const stars = ratingContainer.querySelectorAll('.star');

        for (let i = 0; i < maxRating; i++) {
            if (i < rating) {
                stars[i].classList.add('active');
            } else {
                stars[i].classList.remove('active');
            }
        }
    </script>
    </@main.layout>
