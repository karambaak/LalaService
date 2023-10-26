<#import "../layout.ftlh" as main>
<@main.layout>

    <div class="container-login">
        <div class="row form-item">

            <div class="card shadow-lg" style="border-radius: 15px; margin-top: 100px">
                <div class="card-body p-5">
                    <h1 class="">Войти</h1>
                        <form method="post" class="needs-validation" action="/auth/login" id="/login-form">
                            <#if _csrf??>
                                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                                       value="${(_csrf.token)!'--no-token--'}"/>
                            </#if>

                            <div class="mb-3">
                                <label for="phone" class="label-text" for="phone">Номер телефона</label>
                                <input id="phone" type="tel" class="form-control" name="username" required autofocus>
                            </div>
                            <div class="mb-3">
                                <label class="label-text" for="password">Пароль</label>
                                <input id="password" type="password" class="form-control" name="password" required>
                            </div>

                            <#if error??>
                                <b class="error">${error}</b>
                            </#if>
                            <div class="text-center my-4">
                                <button type="submit" class="btn tab-item"
                                        style="padding-left: 50px; padding-right: 50px; margin-bottom: -20px;">
                                    Войти
                                </button>
                            </div>


                        </form>
                    </div>
                    <div class="card-footer py-3 border-0">
                        <div class="text-center">
                            У вас нет аккаунта? <a href="/auth/register"
                                                   class="text-dark">Создать аккаунт</a>
                            <hr>
                        </div>
                    </div>
                    <div class="d-flex justify-content-center pb-3 my-3 google-button">
                        <a class="btn tab-item" href="/oauth2/authorization/google">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-google" viewBox="0 0 16 16">
                                <path d="M15.545 6.558a9.42 9.42 0 0 1 .139 1.626c0 2.434-.87 4.492-2.384 5.885h.002C11.978 15.292 10.158 16 8 16A8 8 0 1 1 8 0a7.689 7.689 0 0 1 5.352 2.082l-2.284 2.284A4.347 4.347 0 0 0 8 3.166c-2.087 0-3.86 1.408-4.492 3.304a4.792 4.792 0 0 0 0 3.063h.003c.635 1.893 2.405 3.301 4.492 3.301 1.078 0 2.004-.276 2.722-.764h-.003a3.702 3.702 0 0 0 1.599-2.431H8v-3.08h7.545z"/>
                            </svg>
                            Войти через google
                        </a>
                    </div>
                </div>
            </div>
        </div>

    <script>
        document.getElementById("/login-form").addEventListener("submit", onLoginHandler)

        let user = {}

        function onLoginHandler(event) {
            let form = event.target
            let userFormData = new FormData(form)

            user = Object.fromEntries(userFormData)
            localStorage.setItem('user', JSON.stringify(user))
        }
    </script>
    <style>
        .error {
            color: red;
            font-weight: 500;
            text-align: center;
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
        .form-control {
            border: 2px solid #2552e7;
            width: 100%;
            border-radius: 5px;
            height:30px;
            margin: 20px 0;
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
        .google-button {
            margin-bottom: 40px;
        }

    </style>
</@main.layout>