<template>
  <div id="Connexion">
    
    <b-card class="mx-auto">
        <h2> Connexion </h2><br>

            <b-form @submit="onSubmit" v-on:submit.prevent> <!-- FORMULAIRE DE CONNEXION -->
                <b-form-group id="input-group-1" label="Email address:" label-for="input-1">
                    <b-form-input id="input-1" v-model="form.email" type="email" placeholder="Enter email" class="formInputs" required> </b-form-input>
                </b-form-group>

                <b-form-group id="input-group-2" label="Mot de passe:" label-for="input-2">
                    <b-form-input id="input-2" type="password" v-model="form.password" placeholder="Enter password" required> </b-form-input>
                </b-form-group>

                <b-button class='formButton' type="submit" variant="primary">Se connecter</b-button>
                </b-form> <!-- FIN DU FORMULAIRE DE CONNEXION -->
    </b-card>
  </div>

</template>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
import * as xpath from 'xpath-ts';

export default {
  data() {
      return {
        form : {
          email: '',
          password: ''
        },
        show: true
      }
    },
    methods: {
      onSubmit(event) {
        event.preventDefault()
        this.$userForm.email = this.form.email;
        this.$userForm.password = this.form.password;

        let data_to_send = '<x:Envelope xmlns:x="http://schemas.xmlsoap.org/soap/envelope/" xmlns:gsp="http://spring.io/guides/gs-producing-web-service">'+
                            '<x:Header/>'+
                            '<x:Body>'+
                                '<gsp:getUserConnectionRequest>'+
                                '<gsp:mail>'+this.form.email+'</gsp:mail>'+
                                '<gsp:password>'+this.form.password+'</gsp:password>'+
                                '</gsp:getUserConnectionRequest>'+
                            '</x:Body>'+
                        '</x:Envelope>';
        let url = 'http://176.190.50.162:8081/ws';
        axios.post(url,
                   data_to_send,
                   {
                    headers: {
                        'Content-Type': 'text/xml'
                        }
                })
                .then(res=>{
                
                const parser = new DOMParser().parseFromString(res.data);
                
                const user_id_nodes = xpath.select("//*[local-name()='id_user']", parser);
                this.$userForm.user_id = user_id_nodes[0].firstChild.data;

                const firstname_nodes = xpath.select("//*[local-name()='firstname']", parser);
                this.$userForm.firstName = firstname_nodes[0].firstChild.data;

                const lastname_nodes = xpath.select("//*[local-name()='lastname']", parser);
                this.$userForm.lastName = lastname_nodes[0].firstChild.data;

                const role_nodes = xpath.select("//*[local-name()='role']", parser);
                this.$userForm.role = role_nodes[0].firstChild.data;

                const teamNumber_nodes = xpath.select("//*[local-name()='team']", parser);
                this.$userForm.teamNumber = teamNumber_nodes[0].firstChild.data;
                
                this.$userForm.connected = true;
                console.log(this.$userForm); // FULL FORMULAIRE UTILISATEUR REMPLI

                this.$router.push('/') //RETOURNER SUR LA HOMEPAGE SANS FULL REFRESH
                })
                .catch(err=>{console.log(err)});   
        },
      
    }
}
</script>


<style scoped>

#Connexion{
    padding-top: 40px;
    display: flex;

}

.mx-auto{
    width: 30rem;
}

.connexionCheckbox{
    margin-top: 15px;
}

.formInputs{
    margin-top: 5px;
    margin-bottom: 20px;
}

.formButton{
    margin-top: 20px;;
    margin-bottom: 12px;
}

.rememberMe{
    margin-top: 30px;
    margin-bottom: 3px;
}

.rememberMeText{
    margin-left: 5px;
}

</style>