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

//Function list all users.
const getUsers = async () => {
  await axios
    .get(`http://localhost:8082/api/user/list`, {
      method: "GET",
    })
    .then((res) => {
      console.log(res.data.data);
      users.value = res.data;
    })
    .catch((err) => {
      console.error("No users data: ", err);
    });
};

//Function display user details.
const getUserId = async (id) => {
  console.log("Get the user detail:" + id);
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

//Function remove user by user id.
const removeUser = async (id) => {
  if (confirm("Confirm to remove this user?") == true) {
    console.log(id);
    await axios
      .delete(`http://localhost:8082/api/user/detail/${id}`, {
        method: "DELETE",
      })
      .then((res) => {
        console.log("Delete success.");
        getUsers();
      })
      .catch((err) => {
        console.error("User can not remove: ", err);
      });
  }
};

</script>
<template>
  <UserList :userList="users" @getUserId="getUserId" @removeUser="removeUser"/>
  <UserInfo :userInfo="userInfo" @editUserDetail="editUserId"/>
</template>
<style></style>
