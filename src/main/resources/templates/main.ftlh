<#import "layout.ftlh" as main>
<@main.layout>
    <link rel="stylesheet" href="../static/css/main.css">
    <div id="content" class="content">
        <div class="searchBox">
            <input type="text" id="search" placeholder="Поиск..." class="searchInput">
        </div>
        <div id="searchResults" class="searchResults">

        </div>
        <div class="category-container">
            <#if categories?has_content>
                <#list categories as category>
                    <a href="/category/${category.id}" class="category-card">
                        <span>${category.name}</span>
                    </a>
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