<script setup>
import axios from "axios";
import { ref, watch } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

//Field data input attributes
const firstName = ref("");
const lastName = ref("");
const userName = ref("");
const email = ref("");
const password = ref("");
const confirmPassword = ref("");
const roles = ref(["admin", "user"]);
const roleSelect = ref("user");
const dateOfBirth = ref("");

const unique = ref(false);

const createUser = async () => {
  console.log("Form: create new user");
  if (password.value !== confirmPassword.value) {
    alert("Password not match")
    password.value = ""
    confirmPassword.value = ""
    return
  }

  try {
    const res = await axios.post(`http://localhost:8082/api/user/add-user`, {
      firstName: firstName.value,
      lastName: lastName.value,
      userName: userName.value,
      email: email.value,
      password: confirmPassword.value,
      role: roleSelect.value,
      dateOfBirth: dateOfBirth.value,
    });
    console.log("User created successfully:", res.data);
    alert("Create user success.");
    await router.push("/");
  } catch (err) {
    if (err.response) {
      console.error("Error response:", err.response.data);
    } else if (err.request) {
      console.error("No response received:", err.request);
    } else {
      console.error("Error:", err.message);
    }
  }
};

watch(
  [firstName, lastName, userName, email, password, roleSelect, dateOfBirth],
  async () => {
    if (userName.value && email.value && password.value) {
      try {
        const response = await axios.post("http://localhost:8082/api/user/add-user", {
          firstName: firstName.value,
          lastName: lastName.value,
          userName: userName.value,
          email: email.value,
          password: password.value,
          role: roleSelect,
          dateOfBirth: dateOfBirth.value,
        });
        console.log("Data has sent:", response.data);
      } catch (error) {
        console.error("Something went wrong:", error);
      }
    }
  }
);
</script>
<template>
  <div>
    <h2>Insert user</h2>
  </div>
  <form @submit.prevent="createUser">
    <div class="form-group row">
      <div class="col">
        <input
          type="text"
          class="form-control"
          placeholder="First name"
          v-model="firstName"
        />
      </div>
      <div class="col">
        <input
          type="text"
          class="form-control"
          placeholder="Last name"
          v-model="lastName"
        />
      </div>
    </div>
    <div class="form-group row">
      <label>User name</label>
      <input
        type="text"
        class="form-control"
        placeholder="username"
        v-model="userName"
        required
      />
    </div>
    <div class="form-group row">
      <label>Email address</label>
      <input
        type="email"
        class="form-control"
        aria-describedby="emailHelp"
        placeholder="example@mail.com"
        v-model="email"
        required
      />
    </div>
    <div class="form-group row">
      <label>Password</label>
      <input
        type="password"
        class="form-control"
        placeholder="Password"
        v-model="password"
        required
      />
    </div>
    <div class="form-group row">
      <label>Confirm Password</label>
      <input
        type="password"
        class="form-control"
        placeholder="Password"
        v-model="confirmPassword"
      />
    </div>
    <div class="form-group row">
      <label>Role</label>
      <select class="form-control" v-model="roleSelect">
        <option v-for="role in roles" :key="role" :value="role">
          {{ role }}
        </option>
      </select>
    </div>
    <div class="form-group row">
      <label>Date of Birth</label>
      <input
        type="date"
        class="form-control"
        placeholder="Select date of birth"
        v-model="dateOfBirth"
      />
    </div>
    <div>
      <br />
      <button type="submit" class="btn btn-primary">Submit</button>
      &nbsp
      <router-link to="/">
        <button type="submit" class="btn btn-primary">Cancel</button>
      </router-link>
    </div>
  </form>
</template>
<style></style>
