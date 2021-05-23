<template>
  <div id="Edition">
    <div v-if="$userForm.role != ''">
      EDITION PAGE






    </div>

    <div class="plsConnect" v-else>
      <h2 class="plsConnect"> Edition : Veuillez vous connecter pour pouvoir utiliser cette page. </h2>
    </div>
  </div>
</template>

<script>
export default {
   
}
</script>


<style scoped>


.plsConnect{
  text-align: center;
  align-items: center;
  color: rgb(63, 63, 63);
  margin-top: 50px;
}

</style>