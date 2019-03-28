<template>
  <select v-model="selectedDog">
    <option disabled value="">Select a Dog</option>
    <option v-for="dog in dogs" :key="dog.id" :value="dog.id">
      {{ dog.name }}
    </option>
  </select>
</template>

<script>
import { mapState, mapGetters } from "vuex";

export default {
  name: "DogPicker",
  props: {
    value: [String, Number]
  },
  computed: {
    ...mapState(["userId"]),
    ...mapGetters({
      dogByOwner: "dogs/byOwner"
    }),
    dogs() {
      return this.dogByOwner(this.userId);
    },
    selectedDog: {
      get() {
        return this.value !== null && this.value !== undefined
          ? this.value
          : "";
      },
      set(value) {
        this.$emit("input", value !== "" ? value : null);
      }
    }
  }
};
</script>
