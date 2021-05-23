<template>
  <div id="app">
    <head>
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    </head>

    <header class="mainHeader">
      <b-navbar toggleable="lg" type="dark" class="navigationBar">
        <b-navbar-brand md="4">TakeYourTime</b-navbar-brand>
        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav>
            <b-nav-item> <router-link to="/" class="headerMenuText">Homepage</router-link> </b-nav-item>
            <b-nav-item> <router-link to="/Edition" class="headerMenuText">Édition</router-link> </b-nav-item>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>   

      <div v-if="$userForm.role === 'manager'"> <router-link to="/Equipe" class="headerMenuText2">GÉRER MON ÉQUIPE</router-link> </div>
      <div class="connexionSide">

        <div v-if="$userForm.connected == true"> Connected as : {{$userForm.firstName}} 
          <b-button class="connexionButton" href="http://localhost:8080">Se déconnecter</b-button>
        </div>
        <div v-else class="notConnected"> Not connected
        <b-button class="connexionButton"><router-link to="/Connexion" class="connexionButton">Connexion</router-link></b-button>
        </div>
      </div>
    </header>
    <router-view/>
    
  </div>
</template>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>

 export default {
   name: 'app',
   
  }
</script>


<style>
@import url('https://fonts.googleapis.com/css?family=Lato:400,700');

body{
  font-family: 'Lato', sans-serif;
}

#nav-collapse{
  margin-left:50px;
}

.mainHeader{
  width: 100%;
  display: flex;
  align-items:center;
  justify-content: space-between;
  background-color: rgb(48, 48, 48);
  border-bottom: solid 3px rgb(190, 91, 34);
  padding-left: 15px;
}

.headerMenuText{
  color: azure;
  text-decoration: none;
  margin-right: 15px;
  font-weight: 300;
  font-size: 1.3em;
}

.headerMenuText:hover{
  color: rgb(218, 158, 47);
}


.headerMenuText2{
  color: azure;
  text-decoration: none;
  margin-right: 15px;
  font-weight: 200;
  font-size: 1.3em;
  border: solid azure 1px;
  border-radius: 5px;
  padding: 4px;
}

.headerMenuText2:hover{
  color: rgb(218, 158, 47);
  border: solid rgb(218, 158, 47) 1px;
}

.navigationBar{
  height: 4rem;
  margin: 0px;
}

.connexionSide{
  align-items:center;
  color: rgb(235, 235, 235);
  display: flex;
}

.connexionButton{
  float: right;
  color:rgb(219, 219, 219);
  text-decoration: none;
  justify-content: center;
  margin-right: 15px;
  margin-left: 15px;
}

.connexionButton:hover{
  color: rgb(218, 158, 47);
}

.notConnected{
  margin:auto;
}

</style>
