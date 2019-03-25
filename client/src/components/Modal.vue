<template>
  <div
    class="modal fade"
    ref="modal"
    tabindex="-1"
    role="dialog"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">{{ title }}</h5>
          <button
            type="button"
            class="close"
            @click.prevent="hideModal"
            aria-label="Close"
          >
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <slot></slot>
        </div>
        <div class="modal-footer" v-if="$slots.footer">
          <slot name="footer"> </slot>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "modal",
  props: {
    title: String
  },
  methods: {
    // Show/hide using Bootstrap jQuery calls
    showModal() {
      // eslint-disable-next-line no-undef
      $(this.$refs.modal).modal("show");
      this.$emit("show");
    },
    hideModal() {
      // eslint-disable-next-line no-undef
      $(this.$refs.modal).modal("hide");
      this.$emit("hide");
    }
  }
};
</script>
