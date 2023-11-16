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
                    <img src="/static/default.jpg" class="round-image" alt="default photo">
                </#if>
                <#--                <#if qrCodeBase64??>-->
                <#--                <img src="data:image/png;base64,${qrCodeBase64}" alt="QR Code">-->
                <#--                </#if>-->
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
            <#if qrCodeBase64?has_content>
                <img src="data:image/png;base64,${qrCodeBase64}" alt="QR Code" class="qrCode">
            </#if>
        </div>
    </div>

    <div>
        <#if user.role = "ROLE_SPECIALIST">
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
                </div>
            </div>

            <div class="tabs">
                <button class="tab-item" id="resume-tab">Резюме</button>
                <button class="tab-item" id="portfolio-tab">Портфолио</button>
            </div>

            <div class="tab-content" id="resume-content">
                <div class="resume-list">
                    <div class="resume create-resume">
                        <a href="/resume/create" class="add-resume-link">
                            <p class="add-resume-text">Добавить резюме</p>
                            <i class="fas fa-plus"></i>
                        </a>
                    </div>
                    <#if resumes?has_content>
                        <#list resumes as resume>
                            <div class="resume">
                                <a href="/resume/${resume.id}" class="resume-link">
                                    <#if resume.header?has_content>
                                        <p class="resume-name">${resume.header}</p>
                                    <#else>
                                        <p class="resume-name">Без заголовка</p>
                                    </#if>
                                    <#--                                    <p class="resume-experience">Опыт работы: ${resume.experience}</p>-->
                                    <#--                                    <p class="resume-category">Категория: ${resume.category}</p>-->
                                    <p class="resume-date">Дата создания: ${resume.timeOfResume}</p>
                                </a>
                            </div>
                        </#list>
                    <#else>
                        <p>У вас еще нету созданных резюме</p>
                    </#if>
                </div>
            </div>

            <div class="tab-content" id="portfolio-content">
                <div class="portfolio-list">
                    <div class="resume create-resume">
                        <a href="/portfolio/new" class="add-resume-link">
                            <p class="add-resume-text">Добавить портфолио</p>
                            <i class="fas fa-plus"></i>
                        </a>
                    </div>
                    <#if portfolios?has_content>
                        <#list portfolios as portfolio>
                            <div class="portfolio">
                                <#if portfolio.photos?has_content>
                                    <#list portfolio.photos as photo>
                                        <img src="${photo.photoLink}" alt class="portfolio-img">
                                    </#list>
                                <#else>
                                    <p>У вас еще нет загруженного портфолио</p>
                                </#if>
                                <div>
                                    <p>${portfolio.title}</p>
                                    <#if portfolio.timeOfPortfolio??>
                                        <p>Дата публикации: ${portfolio.timeOfPortfolio}</p>
                                    </#if>
                                </div>
                            </div>
                        </#list>
                    <#else>
                        <p>У вас еще нет созданных портфолио</p>
                    </#if>
                </div>
            </div>
        <#elseif user.role = "ROLE_CUSTOMER">
            <div class="tabs">
                <button class="tab-item active" id="stand-request-tab">Ваши запросы на стенд</button>
            </div>

            <div class="d-block" id="stand-request-content">
                <div class="resume-list">
                    <div class="resume create-resume">
                        <a href="/stand/new" class="add-resume-link">
                            <p class="add-resume-text">Новый запрос </p>
                            <i class="fas fa-plus"></i>
                        </a>
                    </div>
                    <#if stands??>
                    <div class="stand-requests">
                        <#list stands as stand>
                        <div class="resume-list">
                            <div class="post">
                                <div class="post-menu">
                                    <button type="button" class="menu-button" data-bs-toggle="dropdown"
                                            aria-haspopup="true"
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
                    </div>
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
                    <li><a href="profile/edit">Редактировать профиль</a></li>
                    <li><a href="/favourites">Посмотреть избранные</a></li>
                    <#if user.role = "ROLE_SPECIALIST"> <li><a href="/tariff">Выбрать тариф</a></li></#if>
                    <li><a href="/logout">Выйти из аккаунта</a></li>
                </ul>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/static/js/profile.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        const tabButton = document.getElementById("stand-request-tab");
    const tabContent = document.querySelector('.stand-requests');

    tabButton.addEventListener("click", function() {
        if (tabContent.style.display === "none") {
            tabContent.style.display = "block";
        } else {
            tabContent.style.display = "none";
        }
    });
    });
</script>
    </@main.layout>
