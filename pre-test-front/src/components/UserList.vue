<script setup>
defineEmits(["getUserId", "removeUser"]);
defineProps({
  userList: {
    type: Array,
    require: true,
  },
});
</script>
<template>
  <div class="container">
    <div class="card">
      <div class="text-muted card-header">
        <h2>User List</h2>
        <router-link to="/create">
          <button type="button" class="btn btn-success">
            <i class="bi bi-person-fill-add text-white"></i>
          </button>
        </router-link>
      </div>
      <div class="card-body" v-if="userList.length < 1">
        <h4 class="text-body-secondary">Loading users...</h4>
      </div>
      <div class="card-body">
        <table class="table table-sm table-hover">
          <thead class="thead-dark">
            <tr>
              <th>Account</th>
              <th>Email</th>
              <th></th>
            </tr>
          </thead>
          <tbody v-for="(user, index) in userList" :key="index">
            <tr>
              <td>{{ user.userName }}</td>
              <td>{{ user.email }}</td>
              <td>
                <button
                  type="button"
                  class="btn btn-info"
                  data-bs-toggle="modal"
                  data-bs-target="#Modal"
                  @click="$emit('getUserId', user.id)"
                >
                  <i class="bi bi-info-circle text-white"></i>
                </button>
                &nbsp
                <router-link :to="`/edit/${user.id}`">
                  <button class="btn btn-warning">
                    <i class="bi bi-pencil-square text-white"></i>
                  </button>
                </router-link>
                &nbsp
                <button
                  type="button"
                  class="btn btn-danger"
                  @click="$emit('removeUser', user.id)"
                >
                  <i class="bi bi-trash-fill text-white"></i>
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
<style></style>
