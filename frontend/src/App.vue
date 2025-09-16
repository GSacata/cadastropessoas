<script setup>
import Footer from './components/Footer.vue';
</script>

<template>
  <h1 class="text-center pt-2 pb-4">Cadastro de Pessoas</h1>
  <router-view />
  <Footer></Footer>
</template>

<style>
:root {
  --color-primary: #1a66d9;
  --color-primary-lighter: #bed3f3;
  --color-primary-darker: #13499b;
  --color-alert: #d9271a;
  --color-alert-lighter: #ebaaa5;
  --color-alert-darker: #86170f;
  --color-confirm: #1ad980;
  --color-confirm-lighter: #96e7c2;
  --color-confirm-darker: #108f54;
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
  max-width: 1000px;
}

table thead tr th {
  background-color: var(--color-primary-darker) !important;
  color: var(--color-font-alt) !important;
  overflow: hidden;
  text-wrap: nowrap;
}

table tbody tr td {
  border-bottom: solid 1px var(--color-primary);
  border-collapse: collapse;
  background-color: var(--color-bg);
  overflow: hidden;
  text-wrap: nowrap;
}

.ed-del-cell {
  border-right: solid 1px var(--color-primary);
}

.btn-like {
  padding: 0.5rem;
  color: var(--color-font-alt);
  border-radius: 5px;
}

.btn-like-neutral {
  color: var(--color-font-alt);
  background-color: var(--color-primary);
  box-shadow: 0px 5px 5px var(--color-primary-lighter);
}

.btn-like-neutral:hover {
  background-color: var(--color-primary-darker);
}

.btn-like-alert {
  color: var(--color-font-alt);
  background-color: var(--color-alert);
  box-shadow: 0px 5px 5px var(--color-alert-lighter);
}

.btn-like-alert:hover {
  background-color: var(--color-alert-darker);
}

.btn-like-confirm {
  color: var(--color-font-alt);
  background-color: var(--color-confirm);
  box-shadow: 0px 5px 5px var(--color-confirm-lighter);
}

.btn-like-confirm:hover {
  background-color: var(--color-confirm-darker);
}

.cmodal-outter {
  background-color: rgba(0,0,0,0.2);
  position: absolute;
  left: 0;
  top: 0;
  width: 100vw !important;
  height: 100vh !important;
  max-width: 4000px !important;
}

.cmodal-inner {
  position: absolute;
  left: 50%;
  top: 50%;
  margin: auto;
  transform: translate(-50%, -50%);
}

.cmodal-del {
  border-radius: 7px;
  background-color: var(--color-bg);
  min-width: 250px !important;
}

.icon-container {
  width: 25px;
  height: 25px;
}

@media screen and (min-width: 993px) {
  .c-show-on-large {
    display: block !important;
  }
}

@media screen and (min-width: 767px) and  (max-width: 992px) {
  .c-show-on-medium {
    display: block !important;
  }
}

@media screen and (max-width: 766px) {
  .c-show-on-small {
    display: block !important;
  }
}
</style>
