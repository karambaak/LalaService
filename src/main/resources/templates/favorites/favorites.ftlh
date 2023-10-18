<#import "../layout.ftlh" as main>
<@main.layout>

    <div id="content" class="content">
        <h1 class="text-center"> Избранное </h1>

        <div class="p-5">
            <div class="stand mb-4">
                <h2>Специалисты</h2>
                <div class="category">${specialist.Name}</div>
                <ul class="tasks">
                    <li class="task"> ${resume1}

                    </li>
                    <li class="task"> ${resume2}

                    </li>
                    <li class="task"> ${resume3}
                    </li>
                </ul>
                <div class="category">${specialist.Name}</div>
                <ul class="tasks">
                    <li class="task"> ${resume1}

                    </li>
                    <li class="task"> ${resume2}

                    </li>
                    <li class="task"> ${resume3}
                    </li>
                </ul>
                <div class="category">${specialist.Name}</div>
                <ul class="tasks">
                    <li class="task"> ${resume1}

                    </li>
                    <li class="task"> ${resume2}

                    </li>
                    <li class="task"> ${resume3}
                    </li>
                </ul>
            </div>
        </div>

    </div>
</@main.layout>