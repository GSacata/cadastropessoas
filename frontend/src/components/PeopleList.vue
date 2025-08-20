<template>
    <div container="row">
        <table class="w-100">
            <tbody class="container">
                <tr class="row" v-for="person of people" :key="person.uuid">
                    <td class="col-1 text-center ed-del-cell">
                        <span @click="toRegister(person.uuid)">Ed.</span>
                        <span @click="showModal(person.uuid)">Del</span>
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