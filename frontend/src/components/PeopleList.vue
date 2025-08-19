<template>
    <p>Nome da aplicação: {{ appName }}</p>
    <p><router-link to="/registry">Nova pessoa</router-link></p>
    <table v-for="person of people" :key="person.uuid">
        <tbody>
            <tr>
                <td @click="toRegister(person.uuid)">Ed.</td>
                <td @click="showModal(person.uuid)">Del</td>
                <td>{{ person.name }}</td>
                <td>{{ person.document }}</td>
            </tr>
        </tbody>
    </table>
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
        }
    }
}
</script>