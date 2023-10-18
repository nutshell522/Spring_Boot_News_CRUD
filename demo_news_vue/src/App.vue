<template>
  <div id="News-category-manage">
    <h2>最新消息分類</h2>
    <div>
      <label for="newsCategoryName">新增分類：</label>
      <input type="text" name="newsCategoryName" id="newsCategoryName" placeholder="輸入名稱">
      <button>新增</button>
    </div>

    <div id="category-box">
      <div></div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';


</script>

<style scoped>
#category-box {
  border: 1px solid black;
}
</style>
