<template>
    <div class="d-flex align-items-end">
        <h2 class="me-auto m-0 p-0">{{ msg }}</h2>
        <h5 class="m-0">{{ "操作人員: " + userName }}</h5>
    </div>
    <section>
        <button @click="editNameEvent(0)" class="btn btn-primary"><i class="bi bi-plus-lg"></i> 新增</button>
        <div v-for="item in newsCategories" :key="item.id" class="category-item">
            <div class="itemName">{{ item.name }}</div>
            <div>
                <i class="bi bi-pencil-square" @click="editNameEvent(item.id)"></i>
                <i class="bi bi-trash" @click="deleteCategoryEvent(item.id)"></i>
            </div>
        </div>
    </section>
    <div class="edit-bg" :class="{ active: isOnEdit }" @click.self="isOnEdit = false">
        <div class="edit-aria">
            <h2 v-if="editId === 0" class="edit-aria-heading">輸入分類名稱</h2>
            <h2 v-else class="edit-aria-heading">修改分類名稱</h2>
            <input type="text" v-model="editName" />
            <div class="danger-text">{{ errorInfo }}</div>
            <button @click="updateTitle">確認</button>
            <button @click="isOnEdit = false">取消</button>
        </div>
    </div>
</template>
<script setup lang="ts">
import { NewsCategory, ApiResponse } from '../type';
import { ref } from 'vue';
defineProps<{ msg: string, userName: string }>();
const isOnEdit = ref<boolean>(false);
const editId = ref<number>(0);
const editName = ref<string>('');
const newsCategories = ref<NewsCategory[]>([]);
const errorInfo = ref<string>('');

const getNewsCategories = (): void => {
    fetch(`http://localhost:8080/NewsCategory`)
        .then((res) => res.json())
        .then((json) => {
            const result: ApiResponse<NewsCategory> = json;
            if (!result.success) {
                alert(result.message);
            }
            newsCategories.value = json.data;
        })
        .catch((err) => {
            console.error(err);
        });
};
getNewsCategories();
const editNameEvent = (categoryId: number): void => {
    errorInfo.value = '';
    isOnEdit.value = true;
    editId.value = categoryId;
    if (categoryId !== 0) {
        editName.value = newsCategories.value.find((x) => x.id === categoryId)!.name;
    } else {
        editName.value = '';
    }
};

const updateTitle = (): void => {
    if (editName.value === '') {
        errorInfo.value = '欄位不可為空';
        return;
    }
    isOnEdit.value = false;
    if (editId.value !== 0) {
        newsCategories.value.find((x) => x.id === editId.value)!.name = editName.value;
        updateCategory(editId.value);
    } else {
        createCategory();
    }
};


const createCategory = (): void => {
    fetch(`http://localhost:8080/NewsCategory`, {
        method: 'POST',
        body: JSON.stringify({
            name: editName.value,
            description: '',
        }),
        headers: { 'Content-type': 'application/json; charset=UTF-8' },
    })
        .then((res) => res.json())
        .then((json) => {
            const result: ApiResponse<NewsCategory> = json;
            if (!result.success) {
                alert(result.message);
            }
            getNewsCategories();
        })
        .catch((err) => console.error(err));
};

const updateCategory = (categoryId: number): void => {
    let changedItem = newsCategories.value.find((item) => item.id === categoryId);
    fetch(`http://localhost:8080/NewsCategory`, {
        method: 'PUT',
        body: JSON.stringify(changedItem),
        headers: { 'Content-type': 'application/json; charset=UTF-8' },
    })
        .then((res) => res.json())
        .then((json) => {
            const result: ApiResponse<NewsCategory> = json;
            if (!result.success) {
                alert(result.message);
            }
            getNewsCategories();
        })
        .catch((err) => console.error(err));
};

const deleteCategoryEvent = (categoryId: number): void => {
    let changedItem = newsCategories.value.find((item) => item.id === categoryId);
    let result = confirm(`是否刪除分類  ${changedItem?.name}`);
    if (result) {
        fetch(`http://localhost:8080/NewsCategory/${categoryId}`, {
            method: 'DELETE',
        })
            .then((res) => {
                if (!res.ok) {
                    alert('刪除失敗');
                } else {
                    return res.json();
                }
            })
            .then((json) => {
                const apiResponse: ApiResponse<NewsCategory[]> = json;
                if (!apiResponse.success) {
                    alert(apiResponse.message);
                } else {
                    getNewsCategories();
                }
            })
            .catch((err) => console.error(err));
    }
};

</script>
<style scoped lang="scss">
section {
    margin-top: 50px;
}


.category-item {
    display: flex;
    background-color: bisque;
    margin-top: 5px;
    padding: 10px 20px;
    border-radius: 5px;
    align-items: center;

    i {
        font-size: 18px;
        margin-left: 15px;
        cursor: pointer;

        &:hover {
            color: #777;
        }
    }

    .itemName {
        margin-right: auto;
        font-size: 18px;
    }
}

.edit-bg {
    $edit-aria-width: 400px;
    $edit-aria-height: 200px;
    visibility: hidden;
    opacity: 0;
    position: fixed;
    top: 0;
    left: 0;
    background-color: rgba($color: #000000, $alpha: 0.3);
    width: 100vw;
    height: 100vh;

    &.active {
        transition: 0.3s;
        visibility: visible;
        opacity: 1;

        .edit-aria {
            top: calc((100% - $edit-aria-height) / 2);
        }
    }

    .edit-aria {
        transition: 0.3s;
        background-color: #fff;
        position: absolute;
        width: $edit-aria-width;
        height: $edit-aria-height;
        top: calc((100% - $edit-aria-height) / 2 - 5%);
        left: calc((100% - $edit-aria-width) / 2);
        text-align: center;
        padding: 15px;
        border-radius: 5px;

        .edit-aria-heading {
            font-size: 30px;
        }

        input {
            width: 100%;
            height: 40px;
            border-radius: 7px;
            margin-top: 5px;
            border: #999 2px solid;
            padding: 10px;
            font-size: 18px;
        }

        .danger-text {
            color: red;
            height: 25px;
            font-size: 18px;
            text-align: start;
            margin-top: 5px;
            margin-bottom: 10px;
        }
    }
}
</style>