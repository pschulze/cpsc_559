<template>
  <div class="container">
    <h1>Search</h1>
     <form class="form-horizontal">
        <div class="form-group row">
          <label class="col-sm-2 control-label">Dog Name</label>
          <div class="col-sm-10">
            <input class="form-control" id="focusedInput" type="text" v-model="searchDogName" placeholder="Search Dog Name...">
          </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 control-label">Dog Breed</label>
            <div class="col-sm-10">
              <input class="form-control" id="focusedInput" type="text" v-model="searchBreedName" placeholder="Search Dog Breed...">
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-10">
              <button type="submit" class="btn btn-primary">Search Dog</button>
            </div>
        </div>
     </form>
     <br>
     <form class="form-horizontal">
        <div class="form-group row">
          <label class="col-sm-2 control-label">Auction Name</label>
          <div class="col-sm-10">
            <input class="form-control" id="focusedInput" type="text" v-model="searchAuctionName" placeholder="Search Auction Name...">
          </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-10">
              <button type="submit" class="btn btn-primary">Search Auction</button>
            </div>
        </div>
     </form>
             <button class="dropdown-item" @click.prevent="$refs.regFormModal.showModal">New around here? Create an account</button>
             <Modal ref="regFormModal" title="Sign Up" @hide="$refs.addRegForm.reset()">
               <RegForm
                 ref="addRegForm"
                 @sumbitSuccess="$refs.regFormModal.hideModal()"
               />
             </Modal>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import Modal from "@/components/Modal.vue";
import RegForm from "@/components/RegForm.vue";

export default {
  name: "search",
  components: {
    Modal,
    RegForm
  },
  computed: {
    ...mapGetters({
      dogs: "dogs/all"
    })
  },
    methods: {
      reset() {
        this.username = this.user ? Vue.util.extend({}, this.user.username) : null;
        this.$refs.form.classList.remove("was-validated");
      },
      checkForm(e) {
        // Do bootstrap's form validation
        if (this.$refs.form.checkValidity() === false) {
          this.$refs.form.classList.add("was-validated");
          e.preventDefault();
          e.stopPropagation();
          return false;
        }
        return true;
      },
      searchUser() {
        return this.$store.dispatch("users/create", {
          username: this.username
        });
      },
      onSubmit(e) {
        if (!this.checkForm(e)) return;
        let saveAction;
        saveAction = this.createUser();
        saveAction.then(() => {
          this.$emit("sumbitSuccess");
          this.reset();
        });
      }
    }
};
</script>
