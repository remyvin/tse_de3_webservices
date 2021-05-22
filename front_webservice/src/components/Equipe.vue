<template>
  <div id="Equipe">
    
 EQUIPE PAGE

 <div v-if="$userForm.connected == true"> Maintenant vous me voyez </div> 
 <div v-else>   Maintenant vous ne me voyez pas </div>
 <p> {{$userForm.connected}}</p>

  </div>
</template>

<script>
export default {
   
}
</script>


<style scoped>

</style>