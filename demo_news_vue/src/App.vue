<template>
  <div class="tabs">
    <div class="tab" :class="{ active: currentComponent == 1 }" @click="renderComponent(1)">分類管理</div>
    <div class="tab" :class="{ active: currentComponent == 2 }" @click="renderComponent(2)">最新消息管理</div>
  </div>
  <CategoryManage msg="分類管理" :userName="user" v-if="currentComponent == 1"></CategoryManage>
  <NewsManage msg="最新消息管理" :userName="user" v-if="currentComponent == 2"></NewsManage>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import CategoryManage from './components/CategoryManage.vue';
import NewsManage from './components/NewsManage.vue';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';
import 'bootstrap-icons/font/bootstrap-icons.css';
const currentComponent = ref<number>(1);
const user = ref<string>("Ted");
function renderComponent(componentNumber: number): void {
  currentComponent.value = componentNumber;
}
</script>

<style scoped lang="scss">
.tabs {
  border-bottom: 1px solid #999;
  display: flex;
  margin-bottom: 30px;
  width: 900px;

  .tab {
    background-color: #ddd;
    color: #333;
    cursor: pointer;
    padding: 10px 20px;
    border-radius: 5px 5px 0 0;
    font-size: 20px;
    transition: 0.1s;
    margin: 0 2px;

    &:hover {
      background-color: #aaa;
    }

    &.active {
      background-color: #333;
      color: #eee;
    }
  }
}
</style>