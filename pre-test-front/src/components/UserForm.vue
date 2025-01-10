<script setup>
import { ref } from "vue";
const emit = defineEmits(["createUser"]);

const email = ref("");
const password = ref("");

const submitForm = () => {
  // Emit the createUser event with user data
  emit("createUser", { email: email.value, password: password.value });
};
</script>
<template>
  <div>
    <h2>Insert user</h2>
  </div>
  <form>
    <div class="form-group row">
      <label for="email">Email address</label>
      <input
        type="email"
        class="form-control"
        id="email"
        aria-describedby="emailHelp"
        placeholder="example@mail.com"
        v-model="email"
      />
    </div>
    <div class="form-group row">
      <label for="password">Password</label>
      <input
        type="password"
        class="form-control"
        id="password"
        placeholder="Password"
        v-model="password"
      />
    </div>
    <div>
      <button type="button" class="btn btn-primary" @click="submitForm">Submit</button>
      &nbsp
      <router-link to="/">
        <button type="submit" class="btn btn-primary">Cancel</button>
      </router-link>
    </div>
  </form>
</template>
<style></style>
