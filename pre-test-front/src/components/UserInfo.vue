<script setup>
import moment from "moment";

defineProps({
  userInfo: {
    type: Object,
  },
});
</script>
<template>
  <div>
    <div>
      <div class="modal-body bgsb">
        <u class="list-group">
          <li class="list-group-item list-group-item-light">
            First Name : {{ userInfo.firstName ? userInfo.firstName : "-" }}
          </li>
          <li class="list-group-item list-group-item-light">
            Last Name : {{ userInfo.lastName ? userInfo.lastName : "-" }}
          </li>
          <li class="list-group-item list-group-item-light">
            Email : {{ userInfo.email }}
          </li>
          <li class="list-group-item list-group-item-light">
            Role : {{ userInfo.role }}
          </li>
          <li class="list-group-item list-group-item-light">
            Date of Birth : {{ moment(userInfo.dateOfBirth).format("MMM Do YY") }}
          </li>
        </u>
      </div>
    </div>
  </div>
</template>
<style></style>
