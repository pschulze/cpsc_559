<template>
  <div class="container">
    <h1>Users List</h1>
    <CardList :loading="loading" :items="users">
      <template v-slot:loading>
        <UserCard :outlineonly="true" />
      </template>
      <template v-slot:default="{ item }">
        <UserCard :user="item" />
      </template>
    </CardList>
    <button @click="loading = !loading" />
  </div>
</template>

<script>
import { mapGetters } from "vuex";

import CardList from "@/components/CardList.vue";
import UserCard from "@/components/UserCard.vue";

export default {
  name: "userlist",
  components: {
    CardList,
    UserCard
  },
  data() {
    return {
      loading: false
    };
  },
  computed: {
    ...mapGetters({
      users: "users/all"
    })
  }
};
</script>
