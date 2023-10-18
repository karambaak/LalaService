<#import "../layout.ftlh" as main>
<@main.layout>
    <div id="content" class="content">
        <h1 class="text-center"> Избранное </h1>

        <div class="p-5">
            <div class="stand mb-4">
                <h2>Специалисты</h2>
                <#list favoritesDto.resumes as resume>
                    <div class="category">${favoritesDto.companyName}, ${favoritesDto.city}</div>
                    <ul class="tasks">
                        <li class="task">
                            <div class="task-details mt-3 me-2">
                                <div class="row">
                                    <div class="col-2 user-photo">
                                        <!-- Add user photo here -->
                                        <img src="${resume.userPhoto}" alt="User Photo">
                                    </div>
                                    <div class="col-3 text-center">${resume.userName}</div>
                                </div>
                                <hr>
                                <div class="description">
                                    <i class="fa-solid fa-calendar-days"></i> Когда: ${resume.workRequiredTime}
                                    <br>
                                    ${resume.title}
                                    <br>
                                    ${resume.description}
                                </div>
                                <hr>
                            </div>
                        </li>
                    </ul>
                </#list>
            </div>
        </div>
    </div>
</@main.layout>
