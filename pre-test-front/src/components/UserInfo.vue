<script setup>
import moment from "moment";
const props = defineProps({
  userInfo: {
    type: Object,
  },
});
const emit = defineEmits(["editUser"]);
const editUser = () => {
  emit("editUser", props.userInfo.id);
};
</script>
<template>
  <div>
    <div
      class="modal fade"
      id="Modal"
      tabindex="-1"
      aria-labelledby="ModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="ModalLabel">User Detail</h5>
            <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <ul class="list-group">
              <li class="text-dark list-group-item list-group-item-light">
                First Name : {{ userInfo.firstName ? userInfo.firstName : "-" }}
              </li>
              <li class="text-dark list-group-item list-group-item-light">
                Last Name : {{ userInfo.lastName ? userInfo.lastName : "-" }}
              </li>
              <li class="text-dark list-group-item list-group-item-light">
                Email : {{ userInfo.email }}
              </li>
              <li class="text-dark list-group-item list-group-item-light">
                Role : {{ userInfo.role }}
              </li>
              <li class="text-dark list-group-item list-group-item-light">
                Date of Birth : {{ moment(userInfo.dateOfBirth).format("MMM Do YYYY") }}
              </li>
            </ul>
            <div class="modal-footer">
              <button type="button" class="btn btn-warning" @click="editUser">
                Edit
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style>
.modal-header {
  background-color: #66fcff;
  color: rgb(36, 33, 33);
}

li {
  font-size: 20px;
  color: #000;
}

.bgsb {
  background-color: #e8ebef;
}

div.modal-content {
  border-radius: dpx;
}
</style>
