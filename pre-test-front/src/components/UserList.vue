<script setup>
import { ref } from "vue";
import UserInfo from "./UserInfo.vue";
defineEmits(["getUserId"]);
defineProps({
  userList: {
    type: Array,
    require: true,
  },
});

const showModal = ref(false);
const selectedUser = ref(null);

const openModal = (user) => {
  selectedUser.value = user;
  showModal.value = true;
};

const closeModal = () => {
  showModal.value = false;
};
</script>
<template>
  <div class="container fluid">
    <div class="card">
      <div class="text-muted card-header">
        <h2>User List</h2>
      </div>
      <div class="card-body" v-if="userList.length < 1">
        <h4>Loading users...</h4>
      </div>
      <div class="card-body">
        <table class="table table-hover">
          <thead class="thead-dark">
            <tr>
              <th>Account</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th></th>
            </tr>
          </thead>
          <tbody v-for="(user, index) in userList" :key="index">
            <tr>
              <td>{{ user.userName }}</td>
              <td>{{ user.firstName ? user.firstName : "-" }}</td>
              <td>{{ user.lastName ? user.lastName : "-" }}</td>
              <td>
                <button
                  type="button"
                  class="btn btn-primary"
                  data-toggle="modal"
                  data-target="#detailModal"
                  @click="$emit('getUserId', user.id)"
                >
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  <UserInfo
    v-if="showModal"
    :userInfo="selectedUser"
    @editUser="closeModal"
    @close="closeModal"
  />
</template>
<style></style>
