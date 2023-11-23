<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container-login">
        <div class="row form-item">

            <div class="card shadow-lg" style="border-radius: 15px; margin-top: 10px">
                <div class="card-body p-5">
                    <h1 class="">Зарегистрироваться</h1>
                    <form method="post" class="needs-validation" action="/auth/register">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <div class="">
                            <label class="label-text" for="role" style="display: none">Выберите кто Вы:</label>
                            <br>
                            <select id="role" class="form-select" name="role" required>
                                <option value="" disabled selected>Выберите кто Вы:</option>
                                <#list roles as role>
                                    <option value="${role}">
                                        <#if role == 'ROLE_SPECIALIST'>
                                            я специалист
                                        <#else>
                                            я ищу специалиста
                                        </#if>
                                    </option>
                                </#list>
                            </select>
                        </div>

                        <div class="mb-3" id="countries">
                            <#assign val = -1>
                            <#if countries??>
                                <select name="country" id="" class="form-select">
                                    <#list countries as item>
                                        <option value="${item.country}">${item.country}
                                            <#assign val = item_index>
                                        </option>
                                    </#list>
                                </select>
                            </#if>

                        </div>
                        <div class=" mb-3" id="cities">
                            <#if countries??>
                                <select name="city" class="form-select">
                                    <#if (val > -1)>
                                        <#list countries[val].cities as city>
                                            <option value="${city}">${city}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </#if>
                        </div>
                        <div class="mb-3">
                            <label class="label-text" for="phone" style="display: none">Номер телефона</label>
                            <input id="phone" type="tel" class="form-control" name="phoneNumber"
                                   placeholder="Номер телефона" required>
                        </div>

                        <div class="mb-3">
                            <label class="label-text" for="password" style="display: none">Пароль</label>
                            <input id="password" type="password" class="form-control" name="password"
                                   placeholder="Пароль" required>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn tab-item"
                                    style="padding-left: 50px; padding-right: 50px; margin-bottom: -20px;">
                                Далее
                            </button>
                        </div>
                    </form>
                </div>
                <div class="card-footer border-0">
                    <div class="text-center">
                        У вас уже есть аккаунт?<a href="/auth/login" class="text-dark-link">Войти</a>
                    </div>
                </div>
                <div class="google-button">
                    <a class="btn tab-item" href="/oauth2/authorization/google">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-google" viewBox="0 0 16 16">
                            <path d="M15.545 6.558a9.42 9.42 0 0 1 .139 1.626c0 2.434-.87 4.492-2.384 5.885h.002C11.978 15.292 10.158 16 8 16A8 8 0 1 1 8 0a7.689 7.689 0 0 1 5.352 2.082l-2.284 2.284A4.347 4.347 0 0 0 8 3.166c-2.087 0-3.86 1.408-4.492 3.304a4.792 4.792 0 0 0 0 3.063h.003c.635 1.893 2.405 3.301 4.492 3.301 1.078 0 2.004-.276 2.722-.764h-.003a3.702 3.702 0 0 0 1.599-2.431H8v-3.08h7.545z"/>
                        </svg>
                        Зарегистрируйтесь через Google
                    </a>
                </div>
            </div>
        </div>
    </div>

    <style>
        .form-control {
            border: 2px solid #2552e7;
            width: 100%;
            border-radius: 5px;
            height: 30px;
            margin: 20px 0;
        }

        .form-select {
            border: 2px solid #2552e7;
            width: 100%;
            border-radius: 5px;
            height: 35px;
            margin-bottom: 15px;
            background-color: #ffffff;
        }

        .form-item {
            margin-bottom: 40px;
        }

        h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }

        .tab-item {
            border: 2px solid #2552e7;
            color: #2552e7;
            background-color: #ffffff;
            font-weight: bold;
            border-radius: 5px;
            padding: 10px 20px;
            margin: 15px;
            text-decoration: none;
            font-size: 16px;
        }

        .dark-theme .tab-item {
            border: 2px solid white;
            color: white;
            background-color: #333;
        }

        .tab-item:hover {
            background-color: #2552e7;
            color: #ffffff;
        }

        .container-login {
            display: flex;
            justify-content: center;
            margin-bottom: 60px;
        }


        .dark-theme .form-control {
            border: 2px solid white;

        }

        .dark-theme .form-select {
            border: 2px solid white;
        }

        .dark-theme input::placeholder {
            color: white;
        }

        .form-item {
            max-width: 450px;
            text-align: center;
        }

        .label-text {
            display: flex;
            justify-content: left;
            height: 25px;
            font-size: 20px;
        }


        .google-button {
            margin-bottom: 40px;
        }
.text-dark-link{
    text-decoration: none;
    color: black;
}
.dark-theme .text-dark-link {
    color: white;
}

    </style>

</@main.layout>