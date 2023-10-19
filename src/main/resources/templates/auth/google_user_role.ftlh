<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>

    <div class="container-login">
        <div class="row form-item">
            <div class="row justify-content-sm-center">
                <div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
                    <div class="card shadow-lg" style="border-radius: 15px; margin-top: 100px">
                        <div class="card-body p-5">
                            <h1 class="fs-4 card-title fw-bold mb-4"><@spring.message 'user.register'/></h1>
                            <form method="post" class="needs-validation" action="/auth/oauth_2" >
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                <div class="mb-3">
                                    <label class="label-text"
                                           for="role"><@spring.message 'user.register.role'/></label>
                                    <br>
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
                                <input type="submit" value="Submit" class="btn tab-item">

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
    </div>
    </div>

<style>
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
        background-color: #ffffff;
        height: 70vh;
        padding: 30px 60px 60px;
        box-shadow: 0px -5px 15px rgba(0.0, 0.0, 0.1, 0.2);
        border-radius: 15px;
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
    }
    .card-footer {
        padding: 20px 0;
        margin: 20px 0;
    }

    .form-select {
        border: 2px solid #2552e7;
        width: 100%;
        border-radius: 5px;
        height:30px;
        margin-bottom: 15px;
        background-color: #ffffff;
    }
</style>

</@main.layout>