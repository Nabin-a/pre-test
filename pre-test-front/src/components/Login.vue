<script setup>
import { ref, watch } from "vue";
const emit = defineEmits(["userLogin"]);

const props = defineProps({
  userStores: {
    type: Object,
  },
});

const userLogin = ref({
  userName: null,
  password: null,
});

watch(
  () => props.userStores,
  (newValue) => {
    (userLogin.value.userName = newValue.userName),
      (userLogin.value.password = newValue.password);

    emit("userLogin", userLogin.value);
  }
);

const handleSubmit = () => {
  emit("userLogin", userLogin.value);
};

</script>
<template>
  <div class="container">
    <h2 class="mt-5">Login</h2>
    <form @submit.prevent="handleSubmit" class="mt-4">
      <div class="mb-3">
        <label for="username" class="form-label">Username</label>
        <input
          type="text"
          id="username"
          v-model="userLogin.userName"
          class="form-control"
          required
        />
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input
          type="password"
          id="password"
          v-model="userLogin.password"
          class="form-control"
          required
        />
      </div>
      <button type="submit" class="btn btn-primary">Continue</button>
      &nbsp
      <router-link to="/">
        <button type="button" class="btn btn-secondary">Cancle</button>
      </router-link>
    </form>
  </div>
</template>
<style></style>
