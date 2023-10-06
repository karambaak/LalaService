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
                        <form method="post" class="needs-validation" action="/auth/oauth_2" >
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                <div class="mb-3">
                                    <label class="mb-2 text-muted"
                                           for="role"><@spring.message 'user.register.role'/></label>
                                    <select id="role" class="form-select" name="role" required>
                                        <#if roles??>
                                            <option value="" disabled
                                                    selected><@spring.message 'user.register.role.choose'/></option>
                                            <#list roles as role>
                                                <option value="${role}">${role}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>
                            <input type="submit" value="Submit" class="btn btn-primary ms-auto mt-3">

                        </form>
                    </div>
                    <div class="card-footer border-0">
                        <div class="text-center">
                            <@spring.message 'user.register.haveaccount'/> <a href="/auth/login" class="text-dark"><@spring.message 'user.register.singin'/></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


</body>
</html>
