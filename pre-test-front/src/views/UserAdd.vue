<script setup>
const   UserForm = defineAsyncComponent(() => import("../components/UserForm.vue"));

const createUser = function () {
  alert("test");
};
</script>
<template>
  <UserForm @createUser="createUser" />
</template>
<style></style>
