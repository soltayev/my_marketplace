<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Мои товары</h1>
    <hr>
    <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
       data-toggle="collapse" href="#goodCreate" role="button"
       aria-expanded="true" aria-controls="goodCreate">
        Добавить новый товар
    </a>
    <div class="collapse mb-3" id="goodCreate" style="">
        <form action="/goods/create" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="goodName">Название товара</label>
                <input type="text" required class="form-control"
                       id="goodName" name="name">
            </div>
            <div class="form-group">
                <label for="price">Цена товара</label>
                <div class="input-group mb-3">
                    <input type="number" required class="form-control" id="price" name="price">
                    <div class="input-group-append">
                        <span class="input-group-text">тг</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="searchCity">Город</label>
                <select id="searchCity" required name="city" class="form-control">
                    <option value="">Город не выбран</option>

                    <option value="Алматы">Алматы</option>
                    <option value="Астана">Астана</option>
                    <option value="Шымкент">Шымкент</option>

                </select>
            </div>
            <div class="form-group">
                <label for="searchCategory">Категория</label>
                <select id="searchCategory" required name="category" class="form-control">
                    <option value="">Категория не выбрана</option>

                    <option value="Смартфоны">Смартфоны</option>
                    <option value="Телевизоры">Телевизоры</option>
                    <option value="Ноутбуки">Ноутбуки</option>

                </select>
            </div>
            <div class="form-group">
                <label for="info">Описание товара</label>
                <textarea required class="form-control" id="info" name="info"></textarea>
            </div>
            <div class="form-group">
                <label for="quantity">Количество товара</label>
                <div class="input-group mb-3">
                    <input type="number" required class="form-control" id="quantity" name="quantity">
                </div>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input required type="file" name="file1" id="file1">
                    <label class="custom-file-label" for="file1">Первая фотография к товару</label>
                </div>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file2" id="file2">
                    <label class="custom-file-label" for="file2">Вторая фотография к товару</label>
                </div>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file3" id="file3">
                    <label class="custom-file-label" for="file3">Третья фотография к товару</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <button type="submit" style="width: 100%" class="btn btn-dark">Добавить</button>
        </form>
    </div>

    <button style="width: 100%" onclick="window.location.href = '/warehouse'" type="submit" class="btn btn-dark">Склад</button>


    <div class="card-columns">
        <#list goods as good>
            <a href="/goods/${good.id}" class="good-link">
                <div class="card text-white bg-dark my-3 good">
                    <div class="card-top">
                        <form action="/goods/delete/${good.id}" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-danger m-3">
                                Удалить
                            </button>
                        </form>
                    </div>
                    <img src="/images/${good.previewImageId}"
                         class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-name">${good.name}</h5>
                        <p class="card-text">${good.price} тг</p>
                        <p class="card-quantity">На складе ${good.quantity} шт.</p>
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