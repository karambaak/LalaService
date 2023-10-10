<#import "../layout.ftlh" as main>
<@main.layout>
    <style>
        .content {
            padding: 20px;
            display: block;
        }

        .resume-list {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            grid-gap: 20px;
        }

        .resume-item {
            border: 1px solid #ccc;
            padding: 10px;
            border-radius: 5px;
        }

        .resume-title {
            font-size: 18px;
            font-weight: bold;
        }

        .resume-category,
        .resume-city,
        .resume-date {
            font-size: 14px;
            margin-top: 5px;
        }

        @media (max-width: 768px) {
            .resume-list {
                grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
            }

            .resume-item {
                padding: 5px;
            }

            .resume-title {
                font-size: 16px;
            }

            .resume-category,
            .resume-city,
            .resume-date {
                font-size: 12px;
            }
        }
    </style>
    <div id="content" class="content">
        <div class="resume-list">
            <#if resumes?has_content>
                <#list resumes as resume>
                    <div class="resume-item">
                        <h2 class="resume-title">${resume.name}</h2>
                        <p class="resume-category">${resume.category}</p>
                        <p class="resume-date">Дата: ${resume.createdDate}</p>
                    </div>
                </#list>
            </#if>
        </div>
    </div>
</@main.layout>