<script setup>
import axios from "axios";
import { ref, watch } from "vue";
import { useRouter } from "vue-router";
import Swal from "sweetalert2";

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

// const unique = ref(false);

//Function insert user data.
const createUser = async () => {
  console.log("Form: create new user");

  //Password check mathing
  if (password.value !== confirmPassword.value) {
    Swal.fire({
      title: "Error!",
      text: "Password does not match.",
      icon: "error",
      confirmButtonText: "Okay",
    });
    password.value = "";
    confirmPassword.value = "";
    return;
  }

  //If password match, it'll create new user
  try {
    const res = await axios.post(
      `http://localhost:8082/api/user/add-user`,
      {
        firstName: firstName.value,
        lastName: lastName.value,
        userName: userName.value,
        email: email.value,
        password: confirmPassword.value,
        role: roleSelect.value,
        dateOfBirth: dateOfBirth.value,
      },
      {
        headers: {
          Authorization: `Bearer ${JSON.parse(localStorage.getItem("token"))}`,
          "Content-Type": "application/json",
        },
      }
    );
    console.log("User created successfully:", res.data);
    await Swal.fire({
      title: "Success!",
      text: "Create user success.",
      icon: "success",
      confirmButtonText: "OK",
    });
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

//User watch() to perform an action in response to a specific change in reactive data.
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
      <div class="col-sm-5">
        <input
          type="text"
          class="form-control"
          placeholder="First name"
          v-model="firstName"
        />
      </div>
    </div>
    <div class="form-group row">
      <div class="col-sm-5">
        <input
          type="text"
          class="form-control"
          placeholder="Last name"
          v-model="lastName"
        />
      </div>
    </div>
    <div class="form-group row">
      <div class="col-sm-5">
        <input
          type="text"
          class="form-control"
          placeholder="User name"
          v-model="userName"
          required
        />
      </div>
    </div>
    <div class="form-group row">
      <div class="col-sm-5">
        <input
          type="email"
          class="form-control"
          aria-describedby="emailHelp"
          placeholder="Email: example@mail.com"
          v-model="email"
          required
        />
      </div>
    </div>
    <div class="form-group row">
      <div class="col-sm-5">
        <input
          type="password"
          class="form-control"
          placeholder="Password"
          v-model="password"
          required
        />
      </div>
    </div>
    <div class="form-group row">
      <div class="col-sm-5">
        <input
          type="password"
          class="form-control"
          placeholder="Confirm Password"
          v-model="confirmPassword"
        />
      </div>
    </div>
    <div class="form-group row">
      <label class="col-sm-auto col-form-label">Role</label>
      <div class="col-sm-2">
        <select class="form-control" v-model="roleSelect">
          <option aria-placeholder="Role" v-for="role in roles" :key="role" :value="role">
            {{ role }}
          </option>
        </select>
      </div>
    </div>
    <div class="form-group row">
      <div class="col-sm-auto col-form-label">
        <label>Date of Birth</label>
      </div>
      <div class="col-sm-auto">
        <input
          type="date"
          class="form-control"
          placeholder="Select date of birth"
          v-model="dateOfBirth"
        />
      </div>
    </div>
    <div>
      <br />
      <button type="submit" class="btn btn-primary">Submit</button>
      &nbsp
      <router-link to="/">
        <button type="submit" class="btn btn-secondary">Cancel</button>
      </router-link>
    </div>
  </form>
</template>
<style></style>
