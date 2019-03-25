<template>
  <form ref="form" @submit="onSubmit" novalidate>
    <div class="form-group">
      <label for="dogFormName">Name</label>
      <input
        type="text"
        class="form-control"
        id="dogFormName"
        placeholder="Enter Name"
        v-model="name"
        required
      />
    </div>
    <div class="form-group">
      <label for="dogFormBreed">Breed</label>
      <input
        type="text"
        class="form-control"
        id="dogFormBreed"
        placeholder="Breed"
        v-model="breed"
        required
      />
    </div>
    <div class="form-group">
      <label for="dogFormAge">Age</label>
      <input
        type="number"
        class="form-control"
        id="dogFormAge"
        value="0"
        min="0"
        step="1"
        v-model="age"
        required
      />
    </div>
    <button type="submit" class="btn btn-primary">
      {{ submitLabel }}
    </button>
  </form>
</template>

<script>
import Vue from "vue";

export default {
  name: "DogForm",
  props: {
    dog: {
      id: Number,
      name: String,
      breed: String,
      age: Number,
      ownerId: Number
    },
    submitLabel: {
      type: String,
      default: "Save"
    }
  },
  data() {
    return {
      name: this.dog ? Vue.util.extend({}, this.dog.name) : null,
      breed: this.dog ? Vue.util.extend({}, this.dog.breed) : null,
      age: this.dog ? Vue.util.extend({}, this.dog.age) : 0
    };
  },
  methods: {
    reset() {
      this.name = this.dog ? Vue.util.extend({}, this.dog.name) : null;
      this.breed = this.dog ? Vue.util.extend({}, this.dog.breed) : null;
      this.age = this.dog ? Vue.util.extend({}, this.dog.age) : 0;
    },
    onSubmit(e) {
      // Do bootstrap's form validation
      if (this.$refs.form.checkValidity() === false) {
        this.$refs.form.classList.add("was-validated");
        e.preventDefault();
        e.stopPropagation();
      } else {
        this.$emit("submit", {
          id: this.dog ? this.dog.id : null,
          name: this.name,
          breed: this.breed,
          age: this.age
        });
      }
    }
  }
};
</script>
