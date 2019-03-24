<template>
  <ul class="row flex-wrap justify-content-around justify-content-md-start">
    <template v-if="loading">
      <li class="col-auto my-2">
        <slot name="loading"></slot>
      </li>
    </template>
    <template v-else>
      <li class="col-auto my-2" v-for="item in items" :key="item.id">
        <slot :item="item"></slot>
      </li>
    </template>
  </ul>
</template>

<script>
export default {
  name: "cardlist",
  props: {
    loading: Boolean,
    items: Array
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
}
</style>
