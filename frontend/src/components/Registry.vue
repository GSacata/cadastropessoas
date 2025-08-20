<template>
    <div class="container">
        <div class="row my-2">
            <label for="name" class="col-12 pl-0">Nome</label>
            <input type="text" placeholder="Nome" name="name" id="reg-name" class="col-12">
        </div>
        <div class="row my-2">
            <label for="document" class="col-4">Documento</label>
            <label for="phone" class="col-4">Telefone</label>
            <label for="email" class="col-4">E-Mail</label>
            <input type="text" placeholder="000.000.000-00" name="document" id="reg-doc" class="col-4">
            <input type="text" placeholder="(00) 0000-0000" name="phone" id="reg-phone" class="col-4">
            <input type="text" placeholder="seu@email.com" name="email" id="reg-email" class="col-4">
        </div>
        <div class="row my-2">
            <div class="col-5 opacity-50">
                <small v-show="isEdit">Última atualização: <span id="reg-lastup"></span></small>
            </div>
            <p class="col-2 mx-1 text-center btn-like btn-like-neutral" @click="navToList()">Voltar</p>
            <p class="col-2 mx-1 text-center btn-like btn-like-confirm" @click="saveRegistry()">Salvar</p>
            <p v-show="isEdit" class="col-2 mx-1 text-center btn-like btn-like-alert" @click="showModal(this.$route.params.uuid)">Deletar</p>
        </div>
    </div>
    <DelModal v-show="isModalVisible" @close="hideModal" @delReg="deleteRegistry(toDeleteUuid)"></DelModal>
</template>

<script>
import axios from 'axios'
import DelModal from './DelModal.vue';

export default {
    name: "Registry",
    components: {
        DelModal
    },
    data() {
        return {
            person: [],
            err: "",
            isModalVisible: false,
            isEdit: false,
            toDeleteUuid: ""
        }
    },
    created() {
        this.getEditableContent()
    },
    methods: {
        getEditableContent() {
            if (this.$route.params.uuid) {
                const url = "http://localhost:8080/api/" + this.$route.params.uuid
                axios.get(url)
                .then((data) => {
                    this.person = data.data
                    document.getElementById("reg-name").value = this.person.name
                    document.getElementById("reg-doc").value = this.person.document
                    document.getElementById("reg-phone").value = this.person.phone
                    document.getElementById("reg-email").value = this.person.email
                    document.getElementById("reg-lastup").innerHTML = this.person.lastUpdated
                    this.isEdit = true
                })
                .catch((err) => {
                    this.err = err
                })
            }
        },
        saveRegistry() {
            let saveName = document.getElementById("reg-name").value
            let saveDoc = document.getElementById("reg-doc").value
            let savePhone = document.getElementById("reg-phone").value
            let saveEmail = document.getElementById("reg-email").value
            
            if (this.$route.params.uuid) {
                const url = `http://localhost:8080/api/${this.$route.params.uuid}/edit`
                const body = {
                    name: saveName,
                    document: saveDoc,
                    phone: savePhone,
                    email: saveEmail
                }
                axios.put(url, body)
                .then(() => {
                    this.$router.push("/")
                })
                .catch((err) => {
                    this.err = err
                })
            } else {
                const url = `http://localhost:8080/api/create`
                const body = {
                    name: saveName,
                    document: saveDoc,
                    phone: savePhone,
                    email: saveEmail
                }
                axios.post(url, body)
                .then(() => {
                    this.$router.push("/")
                })
                .catch((err) => {
                    this.err = err
                })

            }
        },
        showModal(uuid) {
            this.isModalVisible = true;
            this.toDeleteUuid = uuid
        },
        hideModal() {
            this.isModalVisible = false;
        },
        deleteRegistry(uuid) {
            const url = `http://localhost:8080/api/${uuid}/delete`
            axios.delete(url)
            .then(() => {
                this.refreshPeople()
            })
            .catch((err) => {
                this.error = err;
            })
            this.$router.push("/")
            .then(() => {
                window.location.reload()
            })
            .catch((err) => {
                this.err = err
            })
        },
        navToList() {
            this.$router.push("/")
        }
    }
}
</script>