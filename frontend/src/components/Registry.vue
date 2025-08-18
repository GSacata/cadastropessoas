<template>
    <p>I am the Registry template.</p>
    <input type="text" placeholder="Nome" name="name" id="reg-name">
    <input type="text" placeholder="000.000.000-00" name="document" id="reg-doc">
    <input type="text" placeholder="(00) 0000-0000" name="phone" id="reg-phone">
    <input type="text" placeholder="seu@email.com" name="email" id="reg-email">
    <button><router-link to="/">Voltar</router-link></button>
    <button @click="saveRegistry()">Salvar</button>
</template>

<script>
import axios from 'axios'

export default {
    name: "Registry",
    data() {
        return {
            person: [],
            err: ""
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
        }
    }
}
</script>