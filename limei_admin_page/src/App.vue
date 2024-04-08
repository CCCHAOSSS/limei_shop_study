<template>
  <!-- 容器 -->
  <el-config-provider :locale="locale">
    <RouterView />
  </el-config-provider>
  
</template>

<script setup>
import { RouterView } from 'vue-router'
import { ref } from 'vue'
  import { ElConfigProvider } from 'element-plus'

  import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
  let locale = ref(zhCn)
</script>

<style scoped>
  
</style>
