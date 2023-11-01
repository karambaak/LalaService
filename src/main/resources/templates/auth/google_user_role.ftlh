<#import "../layout.ftlh" as main>
<@main.layout>

    <div class="container-login">
        <div class="row form-item">
            <div class="row justify-content-sm-center">
                    <div class="card shadow-lg" style="border-radius: 15px; margin-top: 100px">
                        <div class="card-body p-5">
                            <h1 class="fs-4 card-title fw-bold mb-4">Зарегистрироваться</h1>
                            <form method="post" class="needs-validation" action="/auth/oauth_2" >
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
                                        <select name="country" id="" class="form-select" >
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
                                        <select name="city" class="form-select" >
                                            <#if (val > -1)>
                                                <#list countries[val].cities as city>
                                                    <option value="${city}">${city}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </#if>
                                </div>
                                <button type="submit" class="btn tab-item"
                                        style="padding-left: 50px; padding-right: 50px; margin-bottom: -20px;">
                                    Подтвердить
                                </button>

                            </form>
                        </div>
                        <div class="card-footer border-0">
                            <div class="text-center">
                                У вас уже есть аккаунт? <a href="/auth/login" class="text-dark">Войти</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<style>

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

    .tab-item:hover {
        background-color: #2552e7;
        color: #ffffff;
    }
    .container-login {
        height: 70vh;
        display: flex;
        align-items: center;
        justify-content: center;

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
    .card-footer {
        padding: 20px 0;
        margin: 20px 0;
    }

    .form-select {
        border: 2px solid #2552e7;
        width: 100%;
        border-radius: 5px;
        height:35px;
        margin-bottom: 15px;
        background-color: #ffffff;
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

    .tab-item:hover {
        background-color: #2552e7;
        color: #ffffff;
    }

</style>

</@main.layout>