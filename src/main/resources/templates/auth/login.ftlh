<#import "../layout.ftlh" as main>
<@main.layout>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <div class="ms-auto">
        <div class="row justify-content-sm-center">
            <div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
                <div class="text-center my-5">
                </div>
                <div class="card shadow-lg" style="border-radius: 15px; margin-top: 100px;">
                    <div class="card-body p-5">
                        <h1 class="fs-4 card-title fw-bold mb-4">Войти</h1>
                        <form method="post" class="needs-validation" action="/auth/login" id="/login-form">
                            <#if _csrf??>
                                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                                       value="${(_csrf.token)!'--no-token--'}"/>
                            </#if>
                            <div class="mb-3">
                                <label class="mb-2 text-muted"
                                       for="email">Телефон номер</label>
                                <input id="email" type="text" class="form-control" name="username"
                                       placeholder="Телефон номер" value="" required
                                       autofocus>
                            </div>

                            <div class="mb-3">
                                <div class="mb-2 w-100">
                                    <label class="text-muted"
                                           for="password">Пароль</label>
                                </div>
                                <input id="password" type="password" class="form-control"
                                       placeholder="Пароль" name="password" required>
                            </div>
                            <#if error??>
                                <b class="error">${error}</b>
                            </#if>
                            <div class="text-center mt-4">
                                <button type="submit" class="btn btn-primary"
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
                    <div class="d-flex justify-content-center">
                        <a class="btn btn-outline-danger" href="/oauth2/authorization/google">
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
    </style>
</@main.layout>