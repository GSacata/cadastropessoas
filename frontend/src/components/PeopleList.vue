<template>
    <div container="row">
        <table class="w-100">
            <thead>
                <tr>
                    <tr class="row">
                    <td class="col-1 text-center">Ações</td>
                    <td class="col-5 text-start">Nome</td>
                    <td class="col-2 text-end">Documento</td>
                    <td class="col-2 text-end">E-Mail</td>
                    <td class="col-2 text-end">Telefone</td>
                </tr>
                </tr>
            </thead>
            <tbody class="container">
                <tr class="row" v-for="person of people" :key="person.uuid">
                    <td class="col-1 text-center ed-del-cell">
                        <span @click="toRegister(person.uuid)" class="icon-container">
                            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill=var(--color-primary)><path d="M200-200h57l391-391-57-57-391 391v57Zm-80 80v-170l528-527q12-11 26.5-17t30.5-6q16 0 31 6t26 18l55 56q12 11 17.5 26t5.5 30q0 16-5.5 30.5T817-647L290-120H120Zm640-584-56-56 56 56Zm-141 85-28-29 57 57-29-28Z"/></svg>
                        </span>
                        <span @click="showModal(person.uuid)" class="icon-container">
                            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill=var(--color-primary)><path d="m336-280 144-144 144 144 56-56-144-144 144-144-56-56-144 144-144-144-56 56 144 144-144 144 56 56ZM480-80q-83 0-156-31.5T197-197q-54-54-85.5-127T80-480q0-83 31.5-156T197-763q54-54 127-85.5T480-880q83 0 156 31.5T763-763q54 54 85.5 127T880-480q0 83-31.5 156T763-197q-54 54-127 85.5T480-80Zm0-80q134 0 227-93t93-227q0-134-93-227t-227-93q-134 0-227 93t-93 227q0 134 93 227t227 93Zm0-320Z"/></svg>
                        </span>
                    </td>
                    <td class="col-5 text-start">{{ person.name }}</td>
                    <td class="col-2 text-end">{{ person.document }}</td>
                    <td class="col-2 text-end">{{ person.email }}</td>
                    <td class="col-2 text-end">{{ person.phone }}</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div container="row">
        <p class="text-center my-3 mx-auto p-1 btn-like btn-like-neutral w-75" @click="navToRegistry()">Nova pessoa</p>
    </div>
    <DelModal v-show="isModalVisible" @close="hideModal" @delReg="deleteRegistry(toDeleteUuid)"></DelModal>
    <!-- <p v-if="(error)">{{ error }}</p> -->
</template>

<script>
import axios from 'axios';
import DelModal from './DelModal.vue';

export default {
    name: "people-list",
    components: {
        DelModal
    },
    data() {
        return {
            people: [],
            appName: "",
            errorText: "",
            isModalVisible: false,
            toDeleteUuid: ""
        }
    },
    setup() {
        return {
            appName: "PeopleList",
            endpoint: "http://localhost:8080/api"
        }
    },
    created() {
        this.getAllPeople();
    },
    methods: {
        getAllPeople() {
            axios.get(this.endpoint)
            .then((data) => {
                this.people = data.data;
            })
            .catch((err) => {
                this.error = err;
            })
        },
        refreshPeople() {
            this.getAllPeople()
        },
        toRegister(uuid) {
            this.$router.push('registry/' + uuid)
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
            this.isModalVisible = false;
        },
        navToRegistry() {
            this.$router.push("/registry");
        }
    }
}
</script>