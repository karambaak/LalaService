<#import '../layout.ftlh' as main>
<@main.layout>
    <header>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    </header>
    <style>
        .string-list {
            padding: 30px;
            margin-bottom: 20px;
        }

        .string-item {
            padding: 10px;
            border-bottom: 1px solid #ccc;
        }

        .link-item {
            text-decoration: none;
            color: black;
        }

        .title {
            font-weight: bold;
            padding: 10px;
            border-bottom: 1px solid #ccc;
        }

        .hidden {
            display: none;
        }

        .search-bar {
            padding: 30px 0 0 30px;
            display: flex;
            align-items: center;
            margin: 15px 30px;
        }

        .search-input {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-right: auto;
            margin-left: auto;
            flex-grow: 1;
        }

        .search-button {
            padding: 10px;
            background-color: #2552e7;
            border: none;
            border-radius: 5px;
            color: #fff;
            cursor: pointer;
        }

        .list-names {
            display: none;
        }
    </style>
    <div class="p-5">
        <div class="search-bar">
            <input type="text" class="search-input" id="search" placeholder="Поиск...">
<#--            <button class="search-button"><i class="fas fa-search"></i></button>-->
        </div>
        <div>
            <#if searchResults??>
                <#list searchResults as item>
                    <a href="/specialist/${item.id}" class="list-names"><#if (item.name)??>${item.name}</#if></a>
                </#list>
            </#if>
        </div>
        <div class="string-list">
            <#if contacts??>
                <#list contacts as item>
                    <div class="string-group">

                        <div class="title"># ${item.category}</div>
                        <#if (item.specialists)??>
                            <#list (item.specialists) as s>
                                <div class="string-item"><a href="/specialist/${s.id}" class="link-item">${s.name}</a>
                                </div>
                            </#list>
                        <#else>
                            Нет специалистов в данной категории.
                        </#if>
                    </div>
                </#list>
            </#if>

        </div>
    </div>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            document.querySelectorAll('.title').forEach(title => {
                title.addEventListener('click', (event) => {
                    const group = event.target.parentElement;
                    group.querySelectorAll('.string-item').forEach(item => {
                        item.classList.toggle('hidden');
                    });
                });
            });


            const searchInput = document.getElementById('search');
            const listNames = document.querySelectorAll('.list-names');

            searchInput.addEventListener('input', function() {
                const searchTerm = this.value.toLowerCase();
                listNames.forEach(item => {
                    const name = item.innerText.toLowerCase();
                    if (name.includes(searchTerm)) {
                        item.style.display = 'block';
                    } else {
                        item.style.display = 'none';
                    }
                });

                if (searchTerm === '') {
                    listNames.forEach(item => {
                        item.style.display = 'none';
                    });
                }
            });

            listNames.forEach(item => {
                item.addEventListener('click', function(event) {
                    event.preventDefault();
                    window.location.href = this.getAttribute('href');
                });
            });

        });


    </script>
</@main.layout>