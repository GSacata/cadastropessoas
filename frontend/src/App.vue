<script setup></script>

<template>
  <h1 class="text-center pt-2 pb-4">Cadastro de Pessoas</h1>
  <router-view />
</template>

<style>
:root {
  --color-primary: #1a66d9;
  --color-primary-lighter: #bed3f3;
  --color-primary-darker: #13499b;
  --color-bg: #ffffff;
  --color-font: #101010;
  --color-font-alt: #ffffff;
}

body {
  background-image: linear-gradient(180deg, var(--color-bg), var(--color-primary-lighter));
  height: 100vh;
}

#app {
  font-family: "Figtree", sans-serif;
  color: var(--color-font);
  max-width: 1200px;
}

table tbody tr td {
  border-bottom: solid 1px var(--color-primary);
  border-collapse: collapse;
  background-color: var(--color-bg);
}

.ed-del-cell {
  border-right: solid 1px var(--color-primary);
}

.btn-like-neutral {
  padding: 2rem;
  color: var(--color-font-alt);
  background-color: var(--color-primary);
  border-radius: 5px;
  box-shadow: 0px 5px 5px var(--color-primary-lighter);
}

.btn-like-neutral:hover {
  background-color: var(--color-primary-darker);
}

</style>
