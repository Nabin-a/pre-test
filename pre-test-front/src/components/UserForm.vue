<script setup>
import { ref, defineProps, watch } from "vue";
const emit = defineEmits(["addUser", "closeForm"]);
const props = defineProps({
  userCreate: {
    type: Object,
    require: true,
  },
});

const newUser = ref({});
const roles = ref(["admin", "user"]);
const roleSelect = ref("");

watch(
  () => props.userCreate,
  (newValue) => {
    newUser.value = {
      firstName: newValue.firstName,
      lastName: newValue.lastName,
      userName: newValue.userName,
      email: newValue.email,
      password: newValue.password,
      con_password: newValue.con_password,
    };
  }
);
</script>
<template>
  <div>
    <h2>Add new user.</h2>
      <div>
        <label>First Name: </label>
        <input type="text" v-model="newUser.firstName" required />
      </div>
      <div>
        <label>Last Name: </label>
        <input type="text" v-model="newUser.lastName" required />
      </div>
      <div>
        <label>User name:</label>
        <input type="text" v-model="newUser.userName" required />
      </div>
      <div>
        <label>Email: </label>
        <input type="email" v-model="newUser.email" required />
      </div>
      <div>
        <label>Password: </label>
        <input type="password" v-model="newUser.password" required />
      </div>
      <div>
        <label>Confirm Password: </label>
        <input type="password" v-model="newUser.con_password" required />
      </div>
      <div>
        <label>Role: </label>
        <select :roleSelect>
          <option v-for="role in roles" :value="role">{{ role }}</option>
        </select>
      </div>
      <div>
        <label>Date of Birth:</label>
        <input type="date" v-model="newUser.dateOfBirth" required />
      </div>
      <button
        type="submit"
        @click="
          $emit(
            'addUser',
            newUser.firstName,
            newUser.lastName,
            newUser.userName,
            newUser.email,
            newUser.password,
            newUser.con_password,
            roleSelect
          )
        "
      >
        Save
      </button>
      <router-link to="/"><button type="button">Cancel</button></router-link>
  </div>
</template>
<style></style>
