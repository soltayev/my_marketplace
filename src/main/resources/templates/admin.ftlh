<#import "blocks/template.ftlh" as t>
<@t.template user>
    `<h1 style="text-align: center">Панель администратора</h1><hr>
    <table class="table table-hover table-dark">
        <tr>
            <th scope="col">Email</th>
            <th scope="col">Номер телефона</th>
            <th scope="col">Активность</th>
            <th scope="col">Роли</th>
            <th scope="col">Бан</th>
            <th scope="col">Редактирование</th>
            <th scope="col">Подробная информация</th>
        </tr>
        <#list users as user1>
            <#if user.id != user1.id >
                <tr>
                    <th>${user1.email}</th>
                    <th>${user1.phoneNumber}</th>
                    <th><#if user1.active>true<#else>false</#if></th>
                    <th><#list user1.roles as role>${role}</#list></th>
                    <th>
                        <form action="/admin/user/ban/${user1.id}" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <input type="submit" class="btn btn-light" value="Бан">
                        </form>
                    </th>
                    <th><a href="/admin/user/edit/${user1.id}">Редактировние</a></th>
                    <th><a href="/user/${user1.id}">Подробная информация</a></th>
                </tr>
            </#if>
        <#else>
            <h3>Пользователей нет</h3>
        </#list>
    </table>
</@t.template>