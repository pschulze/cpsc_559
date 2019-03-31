<template>
  <div class="account dropdown" ref="dropdown">
    <button
      class="btn btn-outline-secondary my-2 my-sm-0 dropdown-toggle"
      type="button"
      id="accountMenuButton"
      data-toggle="dropdown"
      aria-haspopup="true"
      aria-expanded="false"
    >
      <template v-if="!loggedin">
        Login
      </template>
      <template v-else>
        {{ username }}
      </template>
    </button>
    <div
      class="dropdown-menu dropdown-menu-right"
      aria-labelledby="accountMenuButton"
    >
      <template v-if="!loggedin">
        <form class="px-4 py-3">
          <div class="form-group">
            <label for="loginDropdownFormUsername">Username</label>
            <input
              ref="loginUsername"
              type="text"
              class="form-control"
              id="loginDropdownFormUsername"
              placeholder="username"
              v-model="signinUsername"
            />
          </div>
          <button
            type="submit"
            class="btn btn-primary"
            @click.prevent="onSigninSubmit"
          >
            Sign in
          </button>
        </form>
        <div class="dropdown-divider"></div>
        <button class="dropdown-item" @click.prevent="$refs.regFormModal.showModal">New around here? Create an account</button>
        <Modal ref="regFormModal" title="Sign Up" @hide="$refs.addRegForm.reset()">
          <RegForm
            ref="addRegForm"
            @sumbitSuccess="$refs.regFormModal.hideModal()"
          />
        </Modal>
      </template>
      <template v-else>
        <router-link
          class="dropdown-item"
          active-class="active"
          :to="`/account`"
          >My Account</router-link
        >
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="#" @click.prevent="onLogoutClick"
          >Log Out</a
        >
      </template>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import Modal from "@/components/Modal.vue";
import RegForm from "@/components/RegForm.vue";

export default {
  name: "navbaraccount",
  components: {
    Modal,
    RegForm
  },
  data() {
    return {
      signinUsername: null
    };
  },
  computed: {
    ...mapState(["userId", "username"]),
    loggedin() {
      return this.userId !== null && this.userId !== undefined;
    }
  },
  methods: {
    resetSigninForm() {
      this.signinUsername = null;
    },
    onSigninSubmit() {
      this.$store.dispatch("signin", this.signinUsername).then(() => {
        this.resetSigninForm();
        this.$router.push({ name: "account" });
      });
    },
    onLogoutClick() {
      this.$store.dispatch("signout").then(() => {
        this.$router.push({ name: "home" });
      });
    }
  },
  mounted() {
    // jQuery loaded from cdn in browser for Bootstrap
    // eslint-disable-next-line no-undef
    $(this.$refs.dropdown).on("shown.bs.dropdown", () => {
      if (!this.loggedin)
        this.$nextTick(() => this.$refs.loginUsername.focus());
    });
  },
  beforeDestroy() {
    // jQuery loaded from cdn in browser for Bootstrap
    // eslint-disable-next-line no-undef
    $(this.$refs.dropdown).off("shown.bs.dropdown");
  }
};
</script>

<style>
#accountMenuButton:after {
  border: none;
  content: none;
}
</style>
