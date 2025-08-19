<template>
    <p>I am the Registry template.</p>
    <input type="text" placeholder="Nome" name="name" id="reg-name">
    <input type="text" placeholder="000.000.000-00" name="document" id="reg-doc">
    <input type="text" placeholder="(00) 0000-0000" name="phone" id="reg-phone">
    <input type="text" placeholder="seu@email.com" name="email" id="reg-email">
    <p>Última atualização: <span id="reg-lastup"></span></p>
    <button><router-link to="/">Voltar</router-link></button>
    <button @click="saveRegistry()">Salvar</button>
    <button v-show="isEdit" @click="showModal(this.$route.params.uuid)">Deletar</button>
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
            // this.isModalVisible = false;
            this.$router.push("/")
            .then(() => {
                window.location.reload()
            })
            .catch((err) => {
                this.err = err
            })
        }
    }
}
</script>