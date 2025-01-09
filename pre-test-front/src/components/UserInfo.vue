<script setup>
import moment from "moment";
const props = defineProps({
  userInfo: {
    type: Object,
  },
});
const emit = defineEmits(['editUser', 'close']);
const editUser = () => {
  emit("editUser", props.userInfo.id);
};
</script>
<template>
  <div>
    <div
      class="modal fade"
      id="detailModal"
      tabindex="-1"
      aria-labelledby="detailModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <h5 class="modal-title" id="detailModalLabel">User Detail</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
          <div class="modal-body bgsb">
            <ul class="list-group">
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
                Date of Birth : {{ moment(userInfo.dateOfBirth).format("MMM Do YYYY") }}
              </li>
            </ul>
            <div class="modal-footer">
              <button type="button" class="btn btn-primary" @click="editUser">
                Edit
              </button>
              <button type="button" class="btn btn-secondary" @click="$emit('close')">
                Close
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
  background-color: #ff6666;
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
