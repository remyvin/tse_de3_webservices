<template>
  <div id="Equipe">
    <b-form @submit="userListSubmit" v-on:submit.prevent>
      <b-button class='formButton' type="submit" >Afficher liste</b-button>
    </b-form>
    <h2 class="plsConnect"> Voici votre équipe : </h2>
    
      <ul>
        <li v-for="user in $userList" :key="user">
          <p> {{ user.firstName }} </p>
        </li>
      </ul>

  </div>
</template>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<script>
import * as xpath from 'xpath-ts';

export default {
    methods: {
      userListSubmit(event) {
        event.preventDefault()
        let queryUsers = '<x:Envelope xmlns:x="http://schemas.xmlsoap.org/soap/envelope/" xmlns:gsp="http://spring.io/guides/gs-producing-web-service">'+
                              '<x:Header/>'+
                              '<x:Body>'+
                                '<gsp:getUserByTeamRequest>'+
                                '<gsp:team>'+this.$userForm.teamNumber+'</gsp:team>'+
                                '</gsp:getUserByTeamRequest>'+
                              '</x:Body>'+
                            '</x:Envelope>';
          let url = 'http://176.190.50.162:8081/ws';
          axios.post(url,
                      queryUsers,
                      {
                      headers: {
                          'Content-Type': 'text/xml'
                          }
                  })
                  .then(res=>{

                  var parser = new DOMParser();
                  var xmlDoc = parser.parseFromString(res.data,"text/xml");
                  var users_id = xmlDoc.getElementsByTagName("ns2:id_user");
                  var users_firstname = xmlDoc.getElementsByTagName("ns2:firstname");
                  var users_lastname = xmlDoc.getElementsByTagName("ns2:lastname");
                  var users_mail = xmlDoc.getElementsByTagName("ns2:mail");
                  var users_role = xmlDoc.getElementsByTagName("ns2:role");
                  var users_team = xmlDoc.getElementsByTagName("ns2:team");

                  console.log(this.$userListFetched);
                  if (this.$userListFetched == 0){
                    this.$userListFetched = 1;
                    console.log(this.$userListFetched);
                    for(var i=0; i < users_id.length; i++){
                      
                      var oneUser = { user_id: '',
                                    firstName: '',
                                    lastName: '',
                                    email: '',
                                    role: '',
                                    teamNumber:''
                                  }

                      oneUser.user_id = users_id[i].childNodes[0].nodeValue;
                      oneUser.firstName = users_firstname[i].childNodes[0].nodeValue;
                      oneUser.lastName = users_lastname[i].childNodes[0].nodeValue;
                      oneUser.email = users_mail[i].childNodes[0].nodeValue;
                      oneUser.role = users_role[i].childNodes[0].nodeValue;
                      oneUser.teamNumber = users_team[i].childNodes[0].nodeValue;
                      console.log(oneUser);
                      this.$userList.push(oneUser);

                    //Tous les utilisateurs dans la même équipe que le manager seront dans cette liste.
                    }
                    
                    console.log(this.$userList)
                    this.$router.push('/')
                  }

                  /*const user_id_nodes = xpath.select("//[local-name()='id_user']", parser);
                  oneUser.user_id = user_id_nodes[0].firstChild.data;

                  const firstname_nodes = xpath.select("//[local-name()='firstname']", parser);
                  oneUser.firstName = firstname_nodes[0].firstChild.data;

                  const lastname_nodes = xpath.select("//[local-name()='lastname']", parser);
                  oneUser.lastName = lastname_nodes[0].firstChild.data;

                  const role_nodes = xpath.select("//[local-name()='role']", parser);
                  oneUser.role = role_nodes[0].firstChild.data;

                  const teamNumber_nodes = xpath.select("//*[local-name()='team']", parser);
                  oneUser.teamNumber = teamNumber_nodes[0].firstChild.data;

                  const email_nodes = xpath.select("//*[local-name()='mail']", parser);
                  oneUser.email = email_nodes[0].firstChild.data;*/

                  })
                  .catch(err=>{console.log(err)});
    }
  }
}
</script>


<style scoped>

#Equipe{
  padding: 30px;
}

.plsConnect{
  color: rgb(63, 63, 63);
  margin-top: 10px;
} 



</style>