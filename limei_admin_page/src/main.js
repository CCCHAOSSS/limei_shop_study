

import { createApp } from 'vue'
import { createPinia } from 'pinia'

//导入element-plus的icon
import * as ElementPlusIconVue from '@element-plus/icons-vue'

import App from './App.vue'
//引入router规则
import router from './router'

const app = createApp(App)
for(const [key, component] of Object.entries(ElementPlusIconVue)){
    app.component(key, component)
}

app.use(createPinia())
app.use(router)

app.mount('#app')
