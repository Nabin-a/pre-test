<script setup>
defineEmits(["getUserId"]);
defineProps({
  userList: {
    type: Array,
    require: true,
  },
});
</script>
<template>
  <div>
    <h2>User List</h2>
  </div>
  <div>
    <h4 v-if="userList.length < 1">Loading users...</h4>
  </div>
  <div>
    <table>
      <thead>
        <tr>
          <th scope="col">Account</th>
          <th scope="col">First Name</th>
          <th scope="col">Last Name</th>
        </tr>
      </thead>
      <tbody v-for="(user, index) in userList" :key="index">
        <tr>
          <td>{{ user.userName }}</td>
          <td>{{ user.firstName ? user.firstName : "-" }}</td>
          <td>{{ user.lastName ? user.lastName : "-" }}</td>
          <td>
            <button @click="$emit('getUserId', user.id)">Details</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<style></style>
