<template>
    <!-- <p>I am the {{ fileName }} template.</p> -->
    <p>Nome da aplicação: {{ appName }}</p>
    <table v-for="person of people" :key="person.uuid">
        <tr>
            <td @click="">Ed</td>
            <td @click="">Del</td>
            <td>{{ person.name }}</td>
            <td>{{ person.document }}</td>
        </tr>
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
    },
    methods: {
        getAllPeople() {
            console.log("Chamou getAllPeople")
            axios.get(this.endpoint)
            .then((data) => {
                console.log(data)
                this.people = data.data;
            })
            .catch((err) => {
                this.error = err;
            })
        }
    }
}
</script>