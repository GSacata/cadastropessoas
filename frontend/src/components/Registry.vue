<template>
    <p>I am the Registry template.</p>
</template>

<script>
import { createRouter } from 'vue-router';

export default {
    name: "Registry",
    data() {
        return {
            person: []
        }
    },
    created() {
        this.getRouterPath()
    },
    methods: {
        getRouterPath() {
            console.log(this.$route.fullPath)
            console.log(this.$route.params.id)
        }
    }
}
</script>