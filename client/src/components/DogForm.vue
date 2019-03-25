<template>
  <form>
    <div class="form-group">
      <label for="dogFormName">Name</label>
      <input
        type="text"
        class="form-control"
        id="dogFormName"
        placeholder="Enter Name"
        v-model="name"
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
      />
    </div>
    <button type="submit" class="btn btn-primary" @click.prevent="onSubmit">
      Save
    </button>
  </form>
</template>

<script>
export default {
  name: "DogForm",
  props: {
    id: Number,
    ownerId: Number
  },
  data() {
    return {
      name: null,
      breed: null,
      age: 0
    };
  },
  methods: {
    onSubmit() {
      console.log("Dog Form Submit");
    }
  }
};
</script>
