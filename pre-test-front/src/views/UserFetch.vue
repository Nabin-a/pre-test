<script setup>
import UserList from "../components/UserList.vue";
import UserInfo from "../components/UserInfo.vue";
import axios from "axios";
import { onBeforeMount, ref } from "vue";

const users = ref([]);
const userInfo = ref({});
onBeforeMount(async () => {
  await getUsers();
});

const getUsers = async () => {
  try {
    const res = await axios.get(`http://localhost:8082/api/user/list`, {
      method: "GET",
    });
    users.value = res.data;
    console.log(users.value);
  } catch (error) {
    console.error("Can not fetch data:", error);
  }
};

const getUserId = async (id) => {
  console.log(id);
  try {
    const res = await axios.get(`http://localhost:8082/api/user/detail/${id}`, {
      method: "GET",
    });
    userInfo.value = res.data;
    console.log(userInfo.value);
  } catch (error) {
    console.error("User Not Found ", error);
  }
};

const editUser = async(id, firstName, lastName, email, role, dateOfBirth) =>{
    console.log(id);
    try {
        const res = await axios.patch(`http://localhost:8082/api/user/detail/{id}`,{
            method: "PATCH"
        })
        
    } catch (error) {
        
    }
};
</script>
<template>
  <UserList :userList="users" @getUserId="getUserId" />
  <UserInfo :userInfo="userInfo" />
</template>
<style></style>
