<script setup>
import axios from "axios";
import UserForm from "../components/UserForm.vue";
import router from "../router/index.js";

const add = ref({
  firstName: "",
  lastName: "",
  userName: "",
  email: "",
  password: "",
  con_password: "",
  role: "",
});

const addUser = async () => {
  await axios
    .post(
      `http://localhost:8082/api/user/add-user`,
      {
        firstName: add.value.firstName,
        lastName: add.value.lastName,
        userName: add.value.userName,
        email: add.value.email,
        password: add.value.con_password,
      },
      {
        method: "POST",
      }
    )
    .then((res) => {
      console.log(res.data);
    });
};

const checkPassword = async () => {
  if (add.password.value == add.value.con_password) {
    await addUser();
    console.log("User has been created.");
    router.push("/");
  }
  alert("Error: password not match");
  add.password.value = "";
  add.con_password.value = "";
};

</script>
<template>
  <UserForm @addUser = "checkPassword"/>
</template>
<style></style>
