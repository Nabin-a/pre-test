<script setup>
import axios from "axios";
import { ref } from "vue";

const roles = ref(["admin", "user"]);
const selectRole = ref("");
const add = ref({
  firstName: "",
  lastName: "",
  userName: "",
  email: "",
  password: "",
  con_password: "",
  role: "",
  dateOfBirth: "",
});

const createUser = async () => {
  console.log("Form: create new user");

  await axios.post(`http://localhost:8082/api/user/add-user`, {
    firstName: add.value.firstName,
    lastName: add.value.lastName,
    userName: add.value.userName,
    email: add.value.email,
    password: add.value.con_password,
    role: selectRole,
    dateOfBirth: add.value.dateOfBirth,
  });
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
      <button type="button" class="btn btn-primary" @click="createUser">
        Submit
      </button>
      &nbsp
      <router-link to="/">
        <button type="submit" class="btn btn-primary">Cancel</button>
      </router-link>
    </div>
  </form>
</template>
<style></style>
