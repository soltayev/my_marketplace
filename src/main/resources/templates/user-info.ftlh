<#import "blocks/template.ftlh" as t>
<@t.template userByPrincipal>
    <h1 style="text-align: center">Информация о пользователе</h1>
    <hr>
    <div style="text-align: center">
        <img src="/static/images/avatar.png"
             style="border:2px solid black; border-radius: 20px" height="120">
        <br>
        <br>
        <h2>${user.name}</h2>
        <br>
        Номер телефона: <b>${user.phoneNumber}</b>
    </div>
    <br>
    <hr>
    <h1 style="text-align: center">Товары пользователя ${user.name}</h1>
    <br>
    <div class="card-columns">
        <#list goods as good>
            <a href="/goods/${good.id}" class="good-link">
                <div class="card text-white bg-dark my-3 good">

                    <img src="/images/${good.previewImageId}"
                         class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-name">${good.name}</h5>
                        <p class="card-text">${good.price} тг</p>
                    </div>
                    <div class="card-footer">
                        ${good.user.name}
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@t.template>