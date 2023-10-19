<template>
  <div class="d-flex align-items-end">
    <h2 class="me-auto m-0 p-0">{{ msg }}</h2>
    <h5 class="m-0">{{ '操作人員: ' + userName }}</h5>
  </div>
  <section>
    <button @click="editNewsEvent(0)" class="btn btn-primary"><i class="bi bi-plus-lg"></i> 新增</button>
    <div v-for="item in news" :key="item.id" class="news-item">
      <div class="itemTopic">{{ item.topic }}</div>
      <div class="itemShortContent">{{ item.shortContent }}</div>
      <div class="itemCategoryName">{{ '分類: ' + item.categoryName }}</div>
      <div v-if="item.createdAt" class="itemCreatedAt">{{ '建立: ' + formatTimestamp(item.createdAt) }}</div>
      <div>
        <i class="bi bi-pencil-square" @click="editNewsEvent(item.id)"></i>
        <i class="bi bi-trash" @click="deleteNewsEvent(item.id)"></i>
      </div>
    </div>
  </section>
  <nav aria-label="Page navigation example">
    <ul class="pagination">
      <li class="page-item" v-for="(value, index) in totalPages" :key="index" @click="clickHandler(value)">
        <a :class="{ currentPage: thePage === value, 'page-link': true }">{{ value }}</a>
      </li>
    </ul>
  </nav>
  <div class="edit-bg" :class="{ active: isOnEdit }" @click.self="isOnEdit = false">
    <div class="edit-aria">
      <h2 v-if="editId === 0" class="edit-aria-heading">新增News</h2>
      <h2 v-else class="edit-aria-heading">修改News</h2>
      <input type="text" v-model="editTopic" />
      <div class="select" @click="isOnSelect = !isOnSelect">
        <div>{{ editCategory.id !== 0 ? editCategory.name : '請選擇分類' }}</div>
        <div class="selectItem" :class="{ active: isOnSelect }" v-for="item in newsCategories" :key="item.id">
          <div @click="changeCategory(item.id, item.name)">{{ item.name }}</div>
        </div>
      </div>

      <textarea class="w-100" v-model="editContent" />
      <div class="danger-text">{{ errorInfo }}</div>
      <button @click="updateEvent">確認</button>
      <button @click="isOnEdit = false">取消</button>
    </div>
  </div>
</template>
<script setup lang="ts">
import { NewsCategory, ApiResponse, News } from '../type';
import { ref, watch, onMounted, defineProps } from 'vue';
import { formatTimestamp } from './dateUtils';
const props = defineProps<{ msg: string; userName: string }>();
const isOnEdit = ref<boolean>(false);
const isOnSelect = ref<boolean>(false);
const editId = ref<number>(0);
const editTopic = ref<string>('');
const editContent = ref<string>('');
const editCategory = ref<{ id: number; name: string }>({ id: 0, name: '' });
const news = ref<News[]>([]);
const newsCategories = ref<NewsCategory[]>([]);
const errorInfo = ref<string>('');
const totalPages = ref<Array<number>>([]); // 共幾頁
const thePage = ref(1); // 第幾頁
const pageSize = ref(4); // 每頁幾筆

const changeCategory = (itemId: number, itemName: string) => {
  editCategory.value.id = itemId;
  editCategory.value.name = itemName;
};

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

const loadNews = () => {
  fetch('http://localhost:8080/News/count')
    .then((res) => {
      if (!res.ok) {
        throw new Error('Network response was not ok');
      }
      return res.json();
    })
    .then((json) => {
      let totalPagesValue = Math.ceil(json.data / pageSize.value);
      totalPages.value = Array.from({ length: totalPagesValue }, (_, index) => index + 1);
      return fetch(`http://localhost:8080/News/srch?page=${thePage.value}&pageSize=${pageSize.value}`);
    })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    })
    .then((json) => {
      const result: ApiResponse<News[]> = json;
      if (!result.success) {
        alert(result.message);
      }
      news.value = result.data;
    })
    .catch((error) => {
      console.error('Error:', error);
    });
};
onMounted(() => {
  loadNews();
});

// 邏輯還要修正
watch([totalPages, thePage], ([newTotalPages, newThePage]) => {
  if (newThePage > newTotalPages.length) {
    thePage.value = newTotalPages.length;
  }
});

// 分頁
const clickHandler = (page: number) => {
  thePage.value = page;
  loadNews();
};

loadNews();
const editNewsEvent = (newsId: number): void => {
  errorInfo.value = '';
  isOnEdit.value = true;
  editId.value = newsId;
  if (newsId !== 0) {
    editTopic.value = news.value.find((x) => x.id === newsId)!.topic;
    editContent.value = news.value.find((x) => x.id === newsId)!.content;
    editCategory.value.id = news.value.find((x) => x.id === newsId)!.categoryId || 0;
    editCategory.value.name = news.value.find((x) => x.id === newsId)!.categoryName;
  } else {
    editTopic.value = '';
    editContent.value = '';
    editCategory.value.id = 0;
    editCategory.value.name = '';
  }
};

const updateEvent = (): void => {
  if (editTopic.value === '') {
    errorInfo.value = '欄位不可為空';
    return;
  }
  isOnEdit.value = false;
  if (editId.value !== 0) {
    news.value.find((x) => x.id === editId.value)!.topic = editTopic.value;
    updateNews();
  } else {
    createNews();
  }
};

const createNews = (): void => {
  let requestBody: News = new News();
  requestBody.id = editId.value;
  requestBody.topic = editTopic.value;
  requestBody.categoryId = 1;
  requestBody.content = editContent.value;
  requestBody.authorName = props.userName;
  fetch(`http://localhost:8080/News`, {
    method: 'POST',
    body: JSON.stringify(requestBody),
    headers: { 'Content-type': 'application/json; charset=UTF-8' },
  })
    .then((res) => res.json())
    .then((json) => {
      const result: ApiResponse<NewsCategory> = json;
      if (!result.success) {
        alert(result.message);
      }
      loadNews();
    })
    .catch((err) => console.error(err));
};

const updateNews = (): void => {
  let requestBody: News = new News();
  requestBody.id = editId.value;
  requestBody.topic = editTopic.value;
  requestBody.categoryId = editCategory.value.id;
  requestBody.content = editContent.value;
  fetch(`http://localhost:8080/News`, {
    method: 'PUT',
    body: JSON.stringify(requestBody),
    headers: { 'Content-type': 'application/json; charset=UTF-8' },
  })
    .then((res) => res.json())
    .then((json) => {
      const result: ApiResponse<News> = json;
      if (!result.success) {
        alert(result.message);
      }
      loadNews();
    })
    .catch((err) => console.error(err));
};

const deleteNewsEvent = (NewsId: number): void => {
  let changedItem = news.value.find((item) => item.id === NewsId);
  let result = confirm(`是否刪除消息  ${changedItem?.topic}`);
  if (result) {
    fetch(`http://localhost:8080/News/${NewsId}`, {
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
        const apiResponse: ApiResponse<News[]> = json;
        if (!apiResponse.success) {
          alert(apiResponse.message);
        } else {
          loadNews();
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

.news-item {
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

  .itemTopic {
    font-size: 18px;
    min-width: 100px;
  }

  .itemShortContent {
    margin-left: 15px;
    margin-right: auto;
  }

  .itemCategoryName,
  .itemCreatedAt {
    margin-left: 15px;
    font-size: 13px;
  }
}

.edit-bg {
  $edit-aria-width: 400px;
  $edit-aria-height: 350px;
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

    textarea {
      width: 100%;
      height: 80px;
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

.select {
  cursor: pointer;
  height: 30px;
  margin: 10px 0;
  border: 1px solid black;
  line-height: 30px;
  position: relative;

  .selectItem {
    background-color: white;
    display: flex;
    flex-direction: column;
    border: 1px solid black;
    position: absolute;
    top: 100%;
    width: 100%;
    height: 0;
    overflow-y: scroll;
    &.active {
      height: 100px;
    }
  }
}

.currentPage {
  background-color: lightgray;
}

.pagination {
  cursor: pointer;
}

.page-link {
  &:hover {
    background-color: lightgray;
  }
}
</style>
