<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Поиск товаров</h1><hr>
    <form action="/" method="get" >
        <div class="form-row">
            <div class="form-group col-md-2">
                <label for="searchCity">Город</label>
                <select id="searchCity" name="searchCity" class="form-control">
                    <option value="" <#if !(searchCity??)>selected</#if>>Город не выбран</option>
                    <option value="Алматы" <#if searchCity?? && searchCity == "Алматы">selected</#if>>Алматы</option>
                    <option value="Астана" <#if searchCity?? && searchCity == "Астана">selected</#if>>Астана</option>
                    <option value="Шымкент" <#if searchCity?? && searchCity == "Шымкент">selected</#if>>Шымкент</option>
                </select>
            </div>
            <div class="form-group col-md-2">
                <label for="searchCategory">Категория</label>
                <select id="searchCategory" name="searchCategory" class="form-control">
                    <option value="" <#if !(searchCategory??)>selected</#if>>Категория не выбрана</option>
                    <#list categories as category>
                        <option value="${category.id}" <#if searchCategory?? && (searchCategory == category.id)>selected</#if>>
                            ${category.name}
                        </option>
                    </#list>
                </select>
            </div>
            <div class="form-group col-md-2">
                <label for="minPrice">Минимальная цена</label>
                <input type="number" name="minPrice" class="form-control" id="minPrice"
                       <#if minPrice??>value="${minPrice}"</#if>>
            </div>
            <div class="form-group col-md-2">
                <label for="maxPrice">Максимальная цена</label>
                <input type="number" name="maxPrice" class="form-control" id="maxPrice"
                       <#if maxPrice??>value="${maxPrice}"</#if>>
            </div>

            <div class="form-group col-md-2">
                <label for="sortField">Сортировать по</label>
                <select id="sortField" name="sortField" class="form-control">
                    <option value="name" <#if sortField?? && sortField == "name">selected</#if>>По названию</option>
                    <option value="price" <#if sortField?? && sortField == "price">selected</#if>>По цене</option>
                    <option value="city" <#if sortField?? && sortField == "city">selected</#if>>По городу</option>
                </select>
            </div>
            <div class="form-group col-md-2">
                <label for="sortOrder">Порядок</label>
                <select id="sortOrder" name="sortOrder" class="form-control">
                    <option value="asc" <#if sortOrder?? && sortOrder == "asc">selected</#if>>По возрастанию</option>
                    <option value="desc" <#if sortOrder?? && sortOrder == "desc">selected</#if>>По убыванию</option>
                </select>
            </div>

            <div class="form-group col-md-12">
                <label for="searchWord">Поиск по названию товара</label>
                <input type="text" name="searchWord" <#if searchWord??>value="${searchWord}"</#if> class="form-control"
                       id="searchWord">
            </div>

            <button style="width: 100%" type="submit" class="btn btn-dark">Поиск</button>
        </div>
    </form>
    <hr>


    <div class="card-columns">
        <#list goods as good>
            <a href="/goods/${good.id}" class="good-link">
                <div class="card text-white bg-dark my-3 good">

                    <img src="/images/${good.previewImageId}"
                         class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-name">${good.name}</h5>
                        <p class="card-text">${good.price} тг</p>
                        <p class="card-quantity">В наличии ${good.quantity} шт.</p>
                    </div>
                    <div class="card-footer">
                        ${good.city}
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@t.template>