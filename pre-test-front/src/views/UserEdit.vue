<script setup>
import { onMounted, ref } from "vue";
import { useRoute } from "vue-router";
import router from "../router";
import axios from "axios";

const route = useRoute();

const roles = ref(["admin", "user"]);

const isEditing = ref(false);

const userEdit = ref({
  firstName: "",
  lastName: "",
  email: "",
  role: "",
  dateOfBirth: "",
});

const getUserId = async () => {
  console.log("Get the user detail: " + `${route.params.id}`);
  await axios
    .get(`http://localhost:8082/api/user/detail/${route.params.id}`)
    .then((res) => {
      console.log(res.data);
      userEdit.value = {
        firstName: res.data.firstName,
        lastName: res.data.lastName,
        email: res.data.email,
        role: res.data.role,
        dateOfBirth: res.data.dateOfBirth,
      };
      console.log(userEdit.value);
    })
    .catch((err) => {
      console.error("User not found: ", err);
    });
};

const editUser = async () => {
  await axios
    .patch(`http://localhost:8082/api/user/detail/${route.params.id}`, {
      firstName: userEdit.value.firstName,
      lastName: userEdit.value.lastName,
      email: userEdit.value.email,
      role: userEdit.value.role,
      dateOfBirth: userEdit.value.dateOfBirth,
    })
    .then((res) => {
      console.log("Edit data complete", res);
      alert("Edit user completed.");
      router.push("/");
    })
    .catch((err) => {
      console.error(err);
    });
};

const toggleEdit = () => {
  isEditing.value = !isEditing.value;
};

onMounted(() => {
  getUserId();
});
</script>
<template>
  <div class="d-flex justify-content-between align-items-center">
    <h2>Edit User: {{ userEdit.email }}</h2>
    <button
      @click="toggleEdit"
      class="btn"
      :class="isEditing ? 'btn-warning' : 'btn-primary'"
    >
      <i class="bi" :class="isEditing ? 'bi-x-lg text-white' : 'bi-pencil'"></i>
    </button>
  </div>
  <div>
    <form @submit.prevent="editUser">
      <div>
        <div class="form-group row">
          <label class="col-sm-auto col-form-label">First Name: </label>
          <div class="col-sm-5">
            <input
              type="text"
              class="form-control"
              v-model="userEdit.firstName"
              :readonly="!isEditing"
            />
          </div>
        </div>
        <div class="form-group row">
          <label class="col-sm-auto col-form-label">Last Name: </label>
          <div class="col-sm-5">
            <input
              type="text"
              class="form-control"
              v-model="userEdit.lastName"
              :readonly="!isEditing"
            />
          </div>
        </div>
        <div class="form-group row">
          <label class="col-sm-auto col-form-label">Email: </label>
          <div class="col-sm-5">
            <input
              type="text"
              class="form-control"
              v-model="userEdit.email"
              :readonly="!isEditing"
            />
          </div>
        </div>
        <div class="form-group row">
          <label class="col-sm-auto col-form-label">Role: </label>
          <div class="col-sm-2">
            <select class="form-control" v-model="userEdit.role" :disabled="!isEditing">
              <option
                aria-placeholder="Role"
                v-for="role in roles"
                :key="role"
                :value="role"
              >
                {{ role }}
              </option>
            </select>
          </div>
        </div>
        <div class="form-group row">
          <label class="col-sm-auto col-form-label">Date of Birth: </label>
          <div class="col-sm-auto">
            <input
              type="date"
              class="form-control"
              v-model="userEdit.dateOfBirth"
              :readonly="!isEditing"
            />
          </div>
        </div>
      </div>
      <div>
        <button type="submit" class="btn btn-primary" v-if="isEditing">Save</button>
        &nbsp
        <router-link to="/">
          <button type="text" class="btn btn-secondary">Cancle</button>
        </router-link>
      </div>
    </form>
  </div>
</template>
<style></style>
