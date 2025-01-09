<script setup>
import { ref, defineProps, watch } from "vue";
const props = defineProps({
  userId: {
    type: Number,
    require: true,
  },
  userInfo: {
    type: Object,
    require: true,
  },
  isEditing:{
    type: Boolean,
    default: false
  }
});
const firstName = ref("");
const lastName = ref("");
const email = ref("");
const role = ref("");
const dateOfBirth = ref("");

watch(
  () => props.userInfo,
  (newUserInfo) => {
    if (newUserInfo && Object.keys(newUserInfo).length) {
      firstName.value = newUserInfo.firstName || "";
      lastName.value = newUserInfo.lastName || "";
      email.value = newUserInfo.email || "";
      role.value = newUserInfo.role || "";
      dateOfBirth.value = newUserInfo.dateOfBirth || "";
    }
  }
);

const submitForm = () => {
  this.$emit(
    "editUser",
    props.userId,
    firstName.value,
    lastName.value,
    email.value,
    role.value,
    dateOfBirth.value
  );
};

const closeForm = () => {
  this.$emit("closeForm");
};
</script>
<template>
  <div>
    <h2>Edit</h2>
    <form @submit.prevent="submitForm">
      <div>
        <label for="firstName">First Name: </label>
        <input id="firstName" type="text" v-model="firstName" required />
      </div>
      <div>
        <label for="lastName">Last Name: </label>
        <input id="lastName" v-model="lastName" required />
      </div>
      <div>
        <label for="email">Email: </label>
        <input id="email" type="email" v-model="email" required />
      </div>
      <div>
        <label for="role">Role: </label>
        <input id="role" text v-model="role" required />
      </div>
      <div>
        <label for="dateOfBirth">Date of Birth:</label>
        <input id="dateOfBirth" type="date" v-model="dateOfBirth" required />
      </div>
      <button type="submit">Save</button>
      <button type="button" @click="closeForm">Cancel</button>
    </form>
  </div>
</template>
<style></style>
