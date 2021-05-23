<template>
  <div id="Equipe">
    <h2 class="plsConnect"> Voici votre équipe : </h2>
    
      <ul>
        <li class="userName" v-for="user in userList" :key="user">
          <p>{{user.firstName}} {{user.lastName}}</p>
          <div class="userButtonDiv">
            <b-button class="userButton"> Voir les temps</b-button>
            <b-button class="userButton"> Editer </b-button>
          </div>
        </li>
      </ul>

  </div>
</template>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<script>

export default {
  data(){
    return {
      userList: []
    }
  },
  beforeMount(){
      this.getUserList();
  },
    methods: {
      getUserList(){
        let queryUsers = '<x:Envelope xmlns:x="http://schemas.xmlsoap.org/soap/envelope/" xmlns:gsp="http://spring.io/guides/gs-producing-web-service">'+
                              '<x:Header/>'+
                              '<x:Body>'+
                                '<gsp:getUserByTeamRequest>'+
                                '<gsp:team>'+this.$userForm.teamNumber+'</gsp:team>'+
                                '</gsp:getUserByTeamRequest>'+
                              '</x:Body>'+
                            '</x:Envelope>';
          let url = this.$urlSOAP_Users;
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

                  file_exist_loop:
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

                    for (var i =0; i <this.userList.length; i++){ //only add user when its not in list
                      console.log(this.userList[i].user_id);
                      console.log(oneUser.user_id);
                      if(this.userList[i].user_id == oneUser.user_id){
                        continue file_exist_loop;
                      }
                    }

                    this.userList.push(oneUser);
                    

                  //Tous les utilisateurs dans la même équipe que le manager seront dans cette liste.
                  }

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

.userName{
  text-decoration: none;
  font-family: 'Lato', sans-serif;
  font-size: 1.3em;
  font-weight: 200;
  margin-top: 30px;
  display: flex;
  margin: auto;
  justify-content: space-between;
}

.userButtonDiv{
  width: 50%;
}

.userButton{
  margin-right: 15px;
  width: 40%;
}

</style>