<script setup>
import { ref } from "vue";
import UserInfo from "./UserInfo.vue";
defineEmits(["getUserId", "removeUser"]);
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
                  class="btn btn-info"
                  data-bs-toggle="modal"
                  data-target="#detailModal"
                  @click="$emit('getUserId', user.id)"
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="16"
                    height="16"
                    fill="currentColor"
                    class="bi bi-info-circle"
                    viewBox="0 0 16 16"
                  >
                    <path
                      d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"
                    />
                    <path
                      d="m8.93 6.588-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533zM9 4.5a1 1 0 1 1-2 0 1 1 0 0 1 2 0"
                    />
                  </svg>
                </button>
                &nbsp
                <button
                  type="button"
                  class="btn btn-danger"
                  @click="$emit('removeUser', user.id)"
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="16"
                    height="16"
                    fill="currentColor"
                    class="bi bi-trash3-fill"
                    viewBox="0 0 16 16"
                  >
                    <path
                      d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5m-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5M4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06m6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528M8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5"
                    />
                  </svg>
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
