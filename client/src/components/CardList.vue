<template>
  <ul ref="ul">
    <li class="masonry-item my-2" v-for="item in items" :key="item.id">
      <slot :item="item"></slot>
    </li>
  </ul>
</template>

<script>
export default {
  name: "cardlist",
  props: {
    items: Array
  },
  methods: {
    masonryInit() {
      // jQuery loaded from cdn in browser for Bootstrap
      // eslint-disable-next-line no-undef
      $(this.$refs.ul).masonry({
        itemSelector: ".masonry-item",
        columnWidth: 270,
        gutter: 10
      });
    },
    masonryDestroy() {
      // jQuery loaded from cdn in browser for Bootstrap
      // eslint-disable-next-line no-undef
      $(this.$refs.ul).masonry("destroy");
    },
    masonryReflow() {
      // jQuery loaded from cdn in browser for Bootstrap
      // eslint-disable-next-line no-undef
      $(this.$refs.ul)
        .masonry("reloadItems")
        .masonry("layout");
    }
  },
  mounted() {
    this.masonryInit();
  },
  updated() {
    this.masonryReflow();
  },
  beforeDestroy() {
    this.masonryDestroy();
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
