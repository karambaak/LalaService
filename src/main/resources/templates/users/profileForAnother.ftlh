<#import "../layout.ftlh" as main>
<@main.layout>
<link rel="stylesheet" href="/static/css/profile.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous">
</script>
<div id="content" class="content">
    <div class="col">

    </div>
    <div class="profile">
        <div class="profile-header row">
            <div class="profile-image-block col-4">
                <#if user.photo?has_content>
                <img src="${user.photo}"
                     class="round-image" alt>
                <#else>
                <img src="../../static/default.jpg" class="round-image" alt="default photo">
                </#if>
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
                        <span class="rating-number" id="rating"><#if rating??>${rating}</#if></span>
                    </div>
                </#if>
            </div>
        </div>
    </div>

    <div>
        <#if user.role.role = "ROLE_SPECIALIST">
            <div class="tabs">
                <div class="share-buttons">
                    <button class="share-button facebook">
                        <i class="fab fa-facebook-f"></i>
                    </button>
                    <button class="share-button twitter">
                        <i class="fab fa-twitter"></i>
                    </button>
                    <button class="share-button linkedin">
                        <i class="fab fa-linkedin-in"></i>
                    </button>
                    <button class="share-button pinterest">
                        <i class="fab fa-pinterest"></i>
                    </button>
                    <button class="share-button whatsapp">
                        <i class="fab fa-whatsapp"></i>
                    </button>
                    <#if user.role.role = "ROLE_SPECIALIST">
                        <a href="/favourites/add/${specialistId}" type="button" class="btn btn-outline-success">Добавить в избранное</a>
                    </#if>
                </div>
            </div>
            <div class="tabs">
                <button class="tab-item" id="resume-tab">Резюме</button>
                <button class="tab-item" id="portfolio-tab">Портфолио</button>
            </div>

            <div class="tab-content" id="resume-content">
                <div class="resume-list">
                    <#if resumes?has_content>
                        <#list resumes as resume>
                            <div class="resume">
                                <a href="#${resume.id}" class="resume-link">
                                    <#if resume.header?has_content>
                                        <p class="resume-name">${resume.header}</p>
                                    <#else>
                                        <p class="resume-name">Без заголовока</p>
                                    </#if>
                                    <p class="resume-date">Дата создания: ${resume.timeOfResume}</p>
                                </a>
                            </div>
                        </#list>
                    <#else>
                        <p>Пользователь еще не создал резюме</p>
                    </#if>
                </div>
            </div>

            <div class="tab-content" id="portfolio-content">
                <div class="portfolio-list">
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
                    <#else>
                        <p>Пользователь еще не создал портфолио</p>
                    </#if>
                </div>
            </div>
        <#elseif user.role.role = "ROLE_CUSTOMER">
            <div class="tabs">
                <button class="tab-item" id="portfolio-tab">Опубликованные запросы на стенд</button>
            </div>

            <div class="tab-content" id="resume-content">
                <div class="resume-list">
                    <#if stands??>
                    <#list stands as stand>
                    <div class="resume">
                        <div class="post">
                            <div class="post-menu">
                                <button type="button" class="menu-button" data-bs-toggle="dropdown" aria-haspopup="true"
                                        aria-expanded="false">
                                <span class="dots"><i class="fas fa-ellipsis-v"></i>
                                </span>
                                </button>
                                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <a class="dropdown-item" href="/stand/delete/${stand.id}">Удалить</a>
                                </div>
                            </div>
                            <a href="/stand/request/${stand.id}" class="resume-link">
                                <p class="resume-name">${stand.title}</p>
                                <p class="resume-category">Категория: ${stand.category}</p>
                                <p class="resume-date">Дата создания: ${stand.publishedDate}</p>
                            </a>
                        </div>
                    </div>
                    </#list>
                    </#if>
                </div>
            </div>
        </#if>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="../../static/js/profile.js"></script>
    </@main.layout>
