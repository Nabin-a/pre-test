<script setup>
import axios from "axios";
import Login from "../components/Login.vue";
import { ref } from "vue";
import { useRouter } from "vue-router";
import Swal from "sweetalert2";

const router = useRouter();

const userStores = ref({});

//Login function
const userLogin = async (userLoginData) => {
  const { userName, password } = userLoginData;
  try {
    const res = await axios.post(`http://localhost:8082/api/user/login`, {
      userName,
      password,
    });

    const token = res.data.token;
    if (res.status === 200) {
      localStorage.setItem("token", JSON.stringify(res.data));
      Swal.fire({
        icon: "success",
        title: "Login succeeded.",
        confirmButtonText: "Close",
        allowOutsideClick: false,
      }).then((result) => {
        if (result.isConfirmed) {
          router.push("/");
        }
      });
    } else {
      console.log("Login failed.");
      console.log(response);
    }
  } catch (err) {
    console.error("Error to logging in: ", err);
    if (err.response.status === 401) {
      Swal.fire({
        icon: "error",
        title: `User not found. \n User is ${err.response.data.message}`,
        confirmButtonText: "Ok",
        allowOutsideClick: false,
        allowEscapeKey: false,
      });
      console.log(err.response);
    }
  }
};
</script>
<template>
  <Login @userLogin="userLogin" :userStores="userStores" />
</template>
<style></style>
