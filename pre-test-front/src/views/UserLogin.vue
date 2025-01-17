<script setup>
import axios from "axios";
import Login from "../components/Login.vue"
import { ref } from "vue";

const userName = ref("");
const password = ref("");

const userLogin = async () => {
  try {
    const res = await axios.post(`http://localhost:8082/api/user/login`, {
      userName: userName.value,
      password: password.value,
    });
    if (res.data.token) {
        localStorage.setItem('token', res.data.token)
    }else{
        console.log("Invalid username or password");
        
    }
  }catch(err){
    console.error("Error logging in: ", err);
  }
}
</script>
<template>
    <Login @userLogin="userLogin"/>
</template>
<style></style>
