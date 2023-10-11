<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="author" content="Muhammed">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="This is a login page template based on Bootstrap 5">
    <title>Регистрация</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>

<body>
<section class="h-100">
    <div class="container h-100">
        <div class="row justify-content-sm-center h-100">
            <div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
                <div class="card shadow-lg" style="border-radius: 15px; margin-top: 100px">
                    <div class="card-body p-5">
                        <h1 class="fs-4 card-title fw-bold mb-4"><@spring.message 'user.register'/></h1>
                        <form method="post" class="needs-validation" action="/auth/register" >
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <div class="mb-3">
                                <label class="mb-2 text-muted" for="role"><@spring.message 'user.register.role'/></label>
                                <select id="role" class="form-select" name="role" required>
                                    <option value="" disabled selected><@spring.message 'user.register.role.choose'/></option>
                                    <#list roles as role>
                                        <option value="${role}">${role}</option>
                                    </#list>
                                </select>
                            </div>

                            <#--                            <div class="mb-3">-->
                            <#--                                <label class="mb-2 text-muted" for="name" id="userName"><@spring.message 'user.register.username'/></label>-->
                            <#--                                <input id="name" type="text" class="form-control" name="userName"  required>-->
                            <#--                            </div>-->

                            <#--                            <div class="mb-3">-->
                            <#--                                <label class="mb-2 text-muted" for="email">E-Mail</label>-->
                            <#--                                <input id="email" type="email" class="form-control" name="email"  required>-->
                            <#--                            </div>-->


                            <div class="mb-3">
                                <label class="mb-2 text-muted" for="phone"><@spring.message 'user.register.phonenumber'/></label>
                                <input id="phone" type="tel" class="form-control" name="phoneNumber" required>
                            </div>

                            <div class="mb-3">
                                <label class="mb-2 text-muted" for="password"><@spring.message 'user.register.password'/></label>
                                <input id="password" type="password" class="form-control" name="password" required>
                            </div>
                            <#--                            <@spring.showErrors "<br>" "error"/>-->
                            <div class="text-center">
                                <button type="submit" class="btn btn-primary ms-auto mt-3" style="padding-left: 50px; padding-right: 50px; margin-bottom: -20px;">
                                    <@spring.message 'user.register.next'/>
                                </button>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer border-0">
                        <div class="text-center">
                            <@spring.message 'user.register.haveaccount'/> <a href="/auth/login" class="text-dark"><@spring.message 'user.register.singin'/></a>
                        </div>
                    </div>
                    <div class="d-flex justify-content-center">
                        <a class="btn btn-outline-danger" href="/oauth2/authorization/google">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-google" viewBox="0 0 16 16">
                                <path d="M15.545 6.558a9.42 9.42 0 0 1 .139 1.626c0 2.434-.87 4.492-2.384 5.885h.002C11.978 15.292 10.158 16 8 16A8 8 0 1 1 8 0a7.689 7.689 0 0 1 5.352 2.082l-2.284 2.284A4.347 4.347 0 0 0 8 3.166c-2.087 0-3.86 1.408-4.492 3.304a4.792 4.792 0 0 0 0 3.063h.003c.635 1.893 2.405 3.301 4.492 3.301 1.078 0 2.004-.276 2.722-.764h-.003a3.702 3.702 0 0 0 1.599-2.431H8v-3.08h7.545z"/>
                            </svg>
                            Sign up with Google
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


</body>
</html>
