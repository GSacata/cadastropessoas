<template>
    <div>
        <p>modal de deleção</p>
        <button type="button" class="btn-close" @click="close">Fechar</button>
    </div>
</template>

<script>
export default {
    name: "DelModal",
    methods: {
        close() {
            this.$emit('close')
        }
    }
}
</script>