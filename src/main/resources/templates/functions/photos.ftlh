<#import "../layout.ftlh" as main>
<@main.layout>

    <div class="my-5">
        <h2>Функция для загрузки фото на бесплатный сервер Амазон S3</h2>
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="border rounded p-4 bg-white">
                    <form action="/file/upload" method="post" enctype="multipart/form-data">
                        <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                               value="${(_csrf.token)!'--no-token--'}"/>
                        </#if>
                        <div class="mb-3">
                            <label for="fileInput" class="form-label">Choose File</label>
                            <input type="file" class="form-control" id="fileInput" name="file">
                        </div>
                        <button type="submit" class="btn btn-primary">Upload</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="row mt-4 justify-content-center">
            <div class="col-md-6">
                <div class="card border-primary mb-3">
                    <div class="card-body">
                        <div> class="card-image">Card Image</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@main.layout>