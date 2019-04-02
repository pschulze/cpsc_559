<template>
  <ul ref="ul">
    <template v-if="loading">
      <li class="masonry-item my-2">
        <slot name="loading"></slot>
      </li>
    </template>
    <template v-else>
      <li class="masonry-item my-2" v-for="item in items" :key="item.id">
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
  },
  match: {
    items() {
      // jQuery loaded from cdn in browser for Bootstrap
      // eslint-disable-next-line no-undef
      $(this.$refs.ul).masonry("reloadItems");
    }
  },
  mounted() {
    this.$nextTick(() => {
      // jQuery loaded from cdn in browser for Bootstrap
      // eslint-disable-next-line no-undef
      $(this.$refs.ul).masonry({
        itemSelector: ".masonry-item",
        columnWidth: 270,
        gutter: 10
      });
    });
    setTimeout(() => {
      // jQuery loaded from cdn in browser for Bootstrap
      // eslint-disable-next-line no-undef
      $(this.$refs.ul).masonry({
        itemSelector: ".masonry-item",
        columnWidth: 270,
        gutter: 10
      });
    }, 3000);
  },
  beforeDestroy() {
    // jQuery loaded from cdn in browser for Bootstrap
    // eslint-disable-next-line no-undef
    $(this.$refs.ul).masonry("destroy");
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
