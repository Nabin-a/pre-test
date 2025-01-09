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
  await axios
    .get(`http://localhost:8082/api/user/list`, {
      method: "GET",
    })
    .then((res) => {
      console.log(res.data);
      users.value = res.data;
    })
    .catch((err) => {
      console.error("Can not fetch users data: ", err);
    });
};

const getUserId = async (id) => {
  console.log(id);
  await axios
    .get(`http://localhost:8082/api/user/detail/${id}`, {
      method: "GET",
    })
    .then((res) => {
      console.log(res.data);
      userInfo.value = res.data;
    })
    .catch((err) => {
      console.error("User not found: ", err);
    });
};

const editUser = async (id, firstName, lastName, email, role, dateOfBirth) => {
  console.log(id);
  await axios.patch(
    `http://localhost:8082/api/user/detail/{id}`,
    {
      method: "PATCH",
    },
    {
      firstName: firstName,
      lastName: lastName,
      email: email,
      role: role,
      dateOfBirth: dateOfBirth,
    }.then((res) => {
      console.log(res.data);
    })
  );
};
</script>
<template>
  <UserList :userList="users" @getUserId="getUserId" />
  <UserInfo :userInfo="userInfo" />
</template>
<style></style>
