<template>
    <!-- <p>I am the {{ fileName }} template.</p> -->
    <p>Nome da aplicação: {{ appName }}</p>
    <p><router-link to="/registry">Nova pessoa</router-link></p>
    <table v-for="person of people" :key="person.uuid">
        <tbody>
            <tr>
                <!-- <td><router-link to="/registry/{{:person.uuid}}">Ed.</router-link></td> -->
                <td @click="toRegister(person.uuid)">Ed.</td>
                <td @click="">Del</td>
                <td>{{ person.name }}</td>
                <td>{{ person.document }}</td>
            </tr>
        </tbody>
    </table>
    <p v-if="(error)">{{ error }}</p>
</template>

<script>
import axios from 'axios';

export default {
    name: "people-list",
    data() {
        return {
            people: [],
            appName: "",
            errorText: ""
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
        console.log(this.$route.fullPath)
    },
    methods: {
        getAllPeople() {
            axios.get(this.endpoint)
            .then((data) => {
                console.log(data)
                this.people = data.data;
            })
            .catch((err) => {
                this.error = err;
            })
        },
        toRegister(uuid) {
            this.$router.push('registry/' + uuid)
        }
    }
}
</script>