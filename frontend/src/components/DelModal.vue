<template>
    <div class="cmodal-outter">
        <div class="cmodal-inner">
            <div class="container p-4 cmodal-del">
                <div class="row">
                    <p class="text-center">Deseja apagar este registro?</p>
                </div>
                <div class="row w-100">
                    <span class="col-4"></span>
                    <p class="col-2 mx-1 text-center btn-like btn-like-neutral" @click="close">Fechar</p>
                    <p class="col-2 mx-1 text-center btn-like btn-like-alert" @click="delReg">Deletar</p>
                    <span class="col-4"></span>
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