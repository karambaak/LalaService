<#import "../layout.ftlh" as main>
<@main.layout>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="styles.css">
    <title>Edit Profile</title>
    <style>

        .profile-image-container {
            text-align: center;
        }

        .profile-image {
            width: 150px;
            height: 150px;
            border: 5px solid #fff;
            border-radius: 50%;
            overflow: hidden;
            margin: 0 auto 10px;
        }

        .profile-image img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        #center {
            border: none;
            position: absolute;
            top: 30%;
            left: 50%;
            transform: translate(-50%, -50%);
            padding: 10px;
        }

    </style>
    <#if restricted??>
        <div id="center" class="empty-favorites text-center"
             style="background-color: rgba(0, 123, 255, 0.6); border-radius: 10px; padding: 20px; margin-top: 40px;">
            <i class="fas fa-info-circle text-light" style="font-size: 48px;"></i>
            <h3 class="text-light">Вы не можете обновлять профиль больше 5 раз в месяц!</h3>
        </div>
    <#else>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <h2>Редактировать профиль</h2>
                        </div>
                        <div class="card-body">
                            <form action="/profile/edit" method="post" enctype="multipart/form-data">
                                <#if _csrf??>
                                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                           value="${(_csrf.token)!'csrf-token'}"/>

                                </#if>

                                <div class="mb-3 text-center">
                                    <label for="profileImage" class="form-label visually-hidden">Profile Image</label>
                                    <div class="profile-image-container">
                                        <div class="profile-image">
                                            <img id="preview"
                                                 src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAZlBMVEX///8AAACvr6/p6en19fXf39++vr57e3s/Pz/x8fHU1NRTU1P8/PykpKTExMSAgICKiopvb29OTk4gICDMzMwlJSWbm5uQkJAPDw+rq6teXl62trZDQ0MaGho0NDTR0dEtLS1nZ2fV4zZcAAADhUlEQVR4nO3cibKiMBAFUMMqqIgLT3B///+TUwyFOiqSxkCnmXu+oG8BTUISJhMAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABgE8WB4zhBHHEX0gu3WOWqlq8Kl7sgs7xgr57tgzl3WeYcpi/5StMDd2GG+LO3+Uozn7s4E4rGfKWCu7zvrT4GVGrFXeCX5s13aG0vuuHMX1voyCKGGgGVCrnL7G6hFVCpBXehXcWaAZWKuUvt6Kid8CjzUXS0AyrlcBfbSd4e7CbnLrYLyiWUeRHfj7abTLnLpdNvpBV57fSHmPCHu2CyNTHhmrtgqmhDTLiR9vGG+hjKexATcsKEu2SizzP7d6TN9qmtVF4z3ZET7rhLJhp/Qt3J7520aXBAThhwl0w0/vehS5kdlnJxSzX6nzAqR+6CyVJiwpS7YLItMeGWu2C6jBQw4y63A9q4TdqYreSREnrc5XZBGbhJG7JVvJN2wJPIS0gZuUkbsd0sNQMuuQvtzNP74LaWuS7zl3vWCHgWNyJ95La/9zPRASeTqO0qnqV9J331ebvJSvAzeHNo/vy9Gcm+r6hpJpXKv0Nrfvp6HTep8BbzxAvCx5CnMBA6UPvITRa7ZbjcLZJxXT0AAAD4v8xdP75cLrHvjm/E5m2LcJ09nHvK1mGxHUtOL0mvDbOna5qITxkFvx+n+Er9HiSHvIQ6K8F5KG2Buxa0n5epzSR+9A6Ie4SlZYypy/hKHSXdq57eaaBnoZiek1B3mtRyGbdqxwso5zL6tA7zbGr9uVn63uBnlt+p9C2Jr6zepEjdCPWexdujdFe121i76m0qoLURzdyiFStvVBNN5s7CdvP9a+Jf1h0w8Q0HVMqyV79Hn0u0Odo1gDPXRu+saqimH8KKRY+iRz1sqCez5z7t4x4tWXOfmu+jNVv6qf43NaoZd7QK9dwBhR1nFKintimsOOHd5yW04yLq/Cipuz13vD4baYW/nZqcFb7DPlP0mtYGTblyD2z67TMl7l7zzQduPcz/Opvrn/zp6sS7E5x+oJmOd9WN/nMIOt6jif0Nuu94h98DBFSKM2DfA5oK57DmMEhCzmMnQzQa3lbT9gtdMzh/xPvdirYuzv/x9T+iKW0YEw4SUCm+cRvtpwLd8R1xcwdKyHdMapgXPucrPyqcQYznICYAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIAF/gCjJCphADpLTwAAAABJRU5ErkJggg=="
                                                 alt="Profile Image">
                                        </div>
                                        <label for="profileImage" style="background-color: #2552E7;"
                                               class="btn btn-primary">
                                            <i class="bi bi-camera"></i> Вставить фото
                                        </label>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label for="username" class="form-label">Имя пользователя:</label>
                                    <input type="text" class="form-control" id="username" name="username"
                                           value="${user.userName}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="city" class="form-label">Выберите город:</label>
                                    <select class="form-select" id="city" name="city">
                                        <option value="1">Бишкек</option>
                                        <option value="2">Ош</option>
                                        <option value="3">Айдаркан</option>
                                        <option value="4">Балыкчы</option>
                                        <option value="5">Баткен</option>
                                        <option value="6">Жалал-Абад</option>
                                        <option value="7">Кадамжай</option>
                                        <option value="8">Кайыңды</option>
                                        <option value="9">Кант</option>
                                        <option value="10">Кара-Балта</option>
                                        <option value="11">Кара-Көл</option>
                                        <option value="12">Кара-Суу</option>
                                        <option value="13">Каракол</option>
                                        <option value="14">Кемин</option>
                                        <option value="15">Кербен</option>
                                        <option value="16">Көк-Жаңгак</option>
                                        <option value="17">Кочкор-Ата</option>
                                        <option value="18">Кызыл-Кыя</option>
                                        <option value="19">Майлуу-Суу</option>
                                        <option value="20">Нарын</option>
                                        <option value="21">Ноокат</option>
                                        <option value="22">Орловка</option>
                                        <option value="23">Өзгөн</option>
                                        <option value="24">Раззаков</option>
                                        <option value="25">Сүлүктү</option>
                                        <option value="26">Талас</option>
                                        <option value="27">Таш-Көмүр</option>
                                        <option value="28">Токмок</option>
                                        <option value="29">Токтогул</option>
                                        <option value="27">Чолпон-Ата</option>
                                        <option value="28">Шопоков</option>


                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email</label>
                                    <input type="email" class="form-control" id="email" name="email"
                                           value="${user.email}" required>
                                </div>

                                <button style="background-color: #2552E7;" type="submit" class="btn btn-primary">
                                    Сохранить
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </#if>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</@main.layout>