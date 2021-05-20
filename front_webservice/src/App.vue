<template>
  <div id="app">
    <header>
        CECI EST UN HEADER OUI OUI 
        <router-link to="/">Homepage</router-link>
        <router-link to="Connexion">Connectez-vous</router-link>
    </header>
    <router-view/>
  
  </div>
</template>

<script>

export default {
  name: 'App'
}
</script>

<style>
#app{
  height:100%;
}

body{
  margin: 0;
  background-color: darkgray;
  height: 700px;
}

header{
  height: 100px;
  background-color: rgb(241, 241, 241);
  margin: 0px;
  border-bottom: solid 2px rgb(241, 54, 54);
}


</style>
