<script setup>
import { ref } from "vue";
const emit = defineEmits(["userLogin"]);

const username = ref("");
const password = ref("");

const handleLogin = () => {
  emit("userLogin", { userName: username.value, password: password.value });
};
</script>
<template>
  <div class="container">
    <h2 class="mt-5">Login</h2>
    <form @submit.prevent="handleLogin" class="mt-4">
      <div class="mb-3">
        <label for="username" class="form-label">Username</label>
        <input
          type="text"
          id="username"
          v-model="username"
          class="form-control"
          required
        />
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input
          type="password"
          id="password"
          v-model="password"
          class="form-control"
          required
        />
      </div>
      <button type="submit" class="btn btn-primary">Login</button>
    </form>
  </div>
</template>
<style></style>
