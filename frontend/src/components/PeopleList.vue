<template>
    <p>Nome da aplicação: {{ appName }}</p>
    <p><router-link to="/registry">Nova pessoa</router-link></p>
    <table v-for="person of people" :key="person.uuid">
        <tbody>
            <tr>
                <td @click="toRegister(person.uuid)">Ed.</td>
                <td @click="confirmDel(person)">Del</td>
                <td>{{ person.name }}</td>
                <td>{{ person.document }}</td>
            </tr>
            <tr>
                <button type="button" class="btn" @click="showModal">Modal</button>
                <DelModal v-show="isModalVisible" @close="closeModal"></DelModal>
            </tr>
        </tbody>
    </table>
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
        showModal() {
            this.isModalVisible = true;
        },
        closeModal() {
            this.isModalVisible = false;
        }
    }
}
</script>