import { createApp } from 'vue'
import App from './App.vue'

import { createMemoryHistory, createRouter } from 'vue-router'
import 'bootstrap/dist/css/bootstrap.css'

import PeopleList from './components/PeopleList.vue'
import Registry from './components/Registry.vue'

const routes = [
    {path: '/', component: PeopleList},
    {path: '/registry', component: Registry},
    {path: '/registry/:uuid', component: Registry}
]

const router = createRouter({
    history: createMemoryHistory(),
    routes
})

createApp(App).use(router).mount('#app')