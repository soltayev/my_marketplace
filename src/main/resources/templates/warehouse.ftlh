<#import "blocks/template.ftlh" as t>
<@t.template user>
    `<h1 style="text-align: center">Склад</h1><hr>
    <table class="table table-hover table-dark">
        <tr>
            <th scope="col">Название</th>
            <th scope="col">Цена</th>
            <th scope="col">Количество</th>
        </tr>
        <#list goods as good>
            <#if good.user.id == user.id>
                <tr>
                    <th>${good.name}</th>
                    <th>${good.price}</th>
                    <td>
                        <form action="/goods/update/${good.id}" method="post" style="display: flex; align-items: center;">
                            <input type="number" name="quantity" value="${good.quantity}" min="0" class="form-control" style="width: 80px; margin-right: 10px;">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button type="submit" class="btn btn-primary btn-sm">Сохранить</button>
                        </form>
                    </td>
                </tr>
            </#if>

        <#else>
            <h3>Товаров нет</h3>
        </#list>
    </table>
</@t.template>