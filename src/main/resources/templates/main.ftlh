<#import "layout.ftlh" as main>
<@main.layout>
    <style>
        .search-form {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 10px;
            margin: 30px auto;
            width: 35%;
            display: flex;
            align-items: center;
            justify-content: center;
        }


        #searchInput {
            flex: 1;
            padding: 10px;
            border: none;
            border-radius: 5px;
            outline: none;
        }

        .search-and-filter-container {
            display: flex;
            align-items: center;
            margin: 30px auto 0px auto;
            width: 50%;
            justify-content: space-between;
        }

        #searchInput {
            flex: 1;
            padding: 10px;
            border: none;
            border-radius: 5px;
            outline: none;
        }

        #searchButton,
        #filterButton {
            background-color: #057ae0;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            cursor: pointer;
        }

        .category-container {
            width: 80%;
            margin: 0 auto;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
        }

        .category-tile {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 10px;
            margin: 5px;
            text-decoration: none;
            color: #333;
            transition: background-color 0.3s;
        }

        .category-tile:hover {
            background-color: #057ae0;
            color: #fff;
        }

        .searchResults{
            text-align: center;
        }
    </style>
    <div class="search-and-filter-container">
        <div class="search-form">
            <input type="text" id="searchInput" placeholder="Поиск...">
            <button id="searchButton">Поиск</button>
        </div>
        <button id="filterButton">
            <i class="fas fa-filter"></i> Фильтр
        </button>
    </div>

    <div class="searchResults" id="searchResults">

    </div>

    <div id="content" class="content">
        <div class="category-container">
            <#if categories?has_content>
                <#list categories as category>
                    <a href="#" class="category-tile">${category.name}</a>
                </#list>
            </#if>
        </div>
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
    <script>
        const searchInput = document.getElementById("searchInput");
        const searchResults = document.getElementById("searchResults");

        searchInput.addEventListener("input", () => {
            const query = searchInput.value.trim();

            if (query !== "") {
                fetch("/api/search/specialist/" + query)
                    .then(response => response.json())
                    .then(data => {
                        displaySearchResults(data);
                    })
                    .catch(error => {
                        console.error("Error searching for vacancies:", error);
                    });
            } else {
                searchResults.innerHTML = "";
            }
        });

        function displaySearchResults(results) {
            searchResults.innerHTML = "";
            console.log("Result searching: "+results)

            if (results.length === 0) {
                searchResults.innerHTML = "<p>Нет результатов</p>";
                return;
            }

            results.forEach(result => {
                const resultItem = document.createElement("div");
                const resultUrl = document.createElement("a")

                resultItem.classList.add("search-result");
                resultUrl.textContent = result.companyName;
                resultUrl.href = "/vacancies/" + result.id

                resultItem.appendChild(resultUrl)
                searchResults.appendChild(resultItem);
            });
        }
    </script>
</@main.layout>