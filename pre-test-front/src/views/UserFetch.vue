<script setup>
import UserList from "../components/UserList.vue";
import UserInfo from "../components/UserInfo.vue";
import axios from "axios";
import { onBeforeMount, ref } from "vue";
import Swal from "sweetalert2";

const users = ref([]);
const userInfo = ref({});

onBeforeMount(async () => {
  await getUsers();
});

//Function list all users.
const getUsers = async () => {
  await axios
    .get(`http://localhost:8082/api/user/list`)
    .then((res) => {
      console.log(res.data);
      users.value = res.data;
    })
    .catch((err) => {
      console.error("No users data: ", err);
    });
};

//Function display user details.
const getUserId = async (id) => {
  console.log("Get the user detail:" + id);
  try {
    const res = await axios.get(`http://localhost:8082/api/user/detail/${id}`, {
      headers: {
        Authorization: `Bearer ${JSON.parse(localStorage.getItem("token"))}`,
        "Content-Type": "application/json",
      },
    });
    userInfo.value = res.data;
    console.log(res.data);
  } catch (err) {
    console.error("Error to get user detail: ", err);
  }
};
//Function remove user by user id.
const removeUser = async (id) => {
  const result = await Swal.fire({
    title: "Confirm to remove this user?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonText: "Yes",
    cancelButtonText: "Cancel",
  });

  if (result.isConfirmed == true) {
    console.log(id);
    try {
      await axios
        .delete(`http://localhost:8082/api/user/detail/${id}`, {
          headers: {
            Authorization: `Bearer ${JSON.parse(localStorage.getItem("token"))}`,
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          console.log("Delete success.");
          getUsers();
        })
        .catch((err) => {
          console.error("User can not remove: ", err);
        });
    } catch (err) {
      console.error(
        "User cannot be removed:",
        err.response ? err.response.data : err.message
      );
    }
  }
};

const userLogout = async () => {
  await Swal.fire({
    title: "Warning!",
    text: "You have been logged out.",
    icon: "warning",
    confirmButtonText: "Okay",
  });
  localStorage.removeItem("token");
};
</script>
<template>
  <UserList
    :userList="users"
    @getUserId="getUserId"
    @removeUser="removeUser"
    @userLogut="userLogout"
  />
  <UserInfo :userInfo="userInfo" />
</template>
<style></style>
