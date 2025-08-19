<template>
    <div>
        <p>Deseja apagar este registro?</p>
        <button type="button" class="btn-close" @click="close">Fechar</button>
        <button type="button" class="btn-close" @click="delReg">Deletar</button>
    </div>
</template>

<script>
export default {
    name: "DelModal",
    methods: {
        close() {
            this.$emit('close')
        },
        delReg() {
            this.$emit('delReg')
        }
    }
}
</script>