<template>
    <div class="cmodal-outter">
        <div class="cmodal-inner">
            <div class="container p-4 cmodal-del">
                <div class="row">
                    <p class="text-center">Deseja apagar este registro?</p>
                </div>
                <div class="row">
                    <p class="col-5 mx-2 text-center btn-like btn-like-neutral" @click="close">Fechar</p>
                    <p class="col-5 mx-2 text-center btn-like btn-like-alert" @click="delReg">Deletar</p>
                </div>
            </div>
        </div>
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