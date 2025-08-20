<template>

    <!-- Small screens -->
    <div class="c-show-on-small" style="display: none;">
        <div class="container">
            <div class="col my-2 mx-2">
                <label for="name" class="col-12 pl-0">Nome pequena</label>
                <input type="text" placeholder="Nome" name="name" class="col-12 reg-name">
                <label for="document" class="col-12">Documento</label>
                <input type="text" placeholder="000.000.000-00" name="document" class="col-12 reg-doc">
                <label for="phone" class="col-12">Telefone</label>
                <input type="text" placeholder="(00) 0000-0000" name="phone" class="col-12 reg-phone">
                <label for="email" class="col-12">E-Mail</label>
                <input type="text" placeholder="seu@email.com" name="email" class="col-12 reg-email">
                <div class="col-12 opacity-50 text-center">
                    <small v-show="isEdit">Última atualização:
                        <span class="reg-lastup"></span>
                    </small>
                </div>
                <div class="mt-3">
                    <p class="col-12 mx-auto text-center btn-like btn-like-neutral" @click="navToList()">Voltar</p>
                    <p class="col-12 mx-auto text-center btn-like btn-like-confirm" @click="saveRegistry()">Salvar</p>
                    <p v-show="isEdit" class="col-12 mx-auto text-center btn-like btn-like-alert" @click="showModal(this.$route.params.uuid)">Deletar</p>
                </div>
            </div>
            <div class="row my-2 mx-2">
            </div>
        </div>
    </div>

    <!-- Medium and large screens -->
    <div class="c-show-on-medium c-show-on-large" style="display: none;">
        <div class="container">
            <div class="row my-2 mx-2">
                <label for="name" class="col-12 pl-0">Nome grande</label>
                <input type="text" placeholder="Nome" name="name" class="col-12 reg-name">
            </div>
            <div class="row my-2 mx-2">
                <label for="document" class="col-4">Documento</label>
                <label for="phone" class="col-4">Telefone</label>
                <label for="email" class="col-4">E-Mail</label>
                <input type="text" placeholder="000.000.000-00" name="document" class="col-4 reg-doc">
                <input type="text" placeholder="(00) 0000-0000" name="phone" class="col-4 reg-phone">
                <input type="text" placeholder="seu@email.com" name="email" class="col-4 reg-email">
            </div>
            <div v-if="isEdit" class="row my-2 mx-2">
                <div class="col-5 opacity-50">
                    <small >Última atualização: <span class="reg-lastup"></span></small>
                </div>
                <p class="col-2 mx-auto text-center btn-like btn-like-neutral" @click="navToList()">Voltar</p>
                <p class="col-2 mx-auto text-center btn-like btn-like-confirm" @click="saveRegistry()">Salvar</p>
                <p v-show="isEdit" class="col-2 mx-auto text-center btn-like btn-like-alert" @click="showModal(this.$route.params.uuid)">Deletar</p>
            </div>
            <div v-else class="row my-2 mx-2">
                <span class="col-3"></span>
                <p class="col-2 mx-auto text-center btn-like btn-like-neutral" @click="navToList()">Voltar</p>
                <p class="col-2 mx-auto text-center btn-like btn-like-confirm" @click="saveRegistry()">Salvar</p>
                <span class="col-3"></span>
            </div>
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
                    
                    let nameFields = document.querySelectorAll(".reg-name")
                    nameFields.forEach((field) => {
                        field.value = this.person.name
                    })

                    let docFields = document.querySelectorAll(".reg-doc")
                    docFields.forEach((field) => {
                        field.value = this.person.document
                    })

                    let phoneFields = document.querySelectorAll(".reg-phone")
                    phoneFields.forEach((field) => {
                        field.value = this.person.phone
                    })

                    let emailFields = document.querySelectorAll(".reg-email")
                    emailFields.forEach((field) => {
                        field.value = this.person.email
                    })

                    let lastUpdatedParags = document.querySelectorAll(".reg-lastup")
                    lastUpdatedParags.forEach((parag) => {
                        parag.innerHTML = this.person.lastUpdated
                    });
                    
                    this.isEdit = true
                })
                .catch((err) => {
                    this.err = err
                })
            }
        },
        saveRegistry() {
            let saveName = "";
            let saveDoc = "";
            let savePhone = "";
            let saveEmail = "";

            let nameFields = document.querySelectorAll(".reg-name")
            nameFields.forEach((field) => {
                if (field.value) {
                    saveName = field.value;
                }
            })

            let docFields = document.querySelectorAll(".reg-doc")
            docFields.forEach((field) => {
                if (field.value) {
                    saveDoc = field.value;
                }
            })

            let phoneFields = document.querySelectorAll(".reg-phone")
            phoneFields.forEach((field) => {
                if (field.value) {
                    savePhone = field.value
                }
            }) 

            let emailFields = document.querySelectorAll(".reg-email")
            emailFields.forEach((field) => {
                if (field.value) {
                    saveEmail = field.value
                }
            })
            
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