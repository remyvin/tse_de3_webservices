<template>
  <div id="Homepage">
    <div v-if="$userForm.role != ''">
      <header class="homepageHeader">
        <div class="entryList">
          <b-form class="addForm" @submit="addNewTime" @reset="onReset" v-if="show"> <!-- FORMULAIRE D'ENTREE DE TEMPS -->
            <p class="addTimeText"> Ajouter un temps </p>
            <b-form-select fluid="sm" md="4" class="dropdownForms" v-model="form.week" :options="weekOptions"></b-form-select>
            <b-form-select fluid="sm" md="4" class="dropdownForms" v-model="form.year" :options="yearOptions"></b-form-select>
            <b-form-select fluid="sm" md="4" class="dropdownForms" v-model="form.project" :options="projectOptions"></b-form-select>
            <b-form-select fluid="sm" md="4" class="dropdownForms" v-model="form.time" :options="timeOptions"></b-form-select>
            <b-button class='timeFormButton' type="submit">Entrer le nouveau temps</b-button>
          </b-form> <!-- FIN DU FORMULAIRE D'ENTREE DE TEMPS -->
        </div>
      </header>

      <div class=mainPage>
        <div class="filterPanel">
            <b-form class="filterForm" @submit="getTimeList" @reset="onReset" v-if="show"> <!-- FORMULAIRE DE REFRESH DE TEMPS -->
              <p class="addTimeText"> Filtrer les temps  </p>
              <b-form-select fluid="sm" md="4" class="dropdownForms" v-model="filterForm.week" :options="weekOptions"></b-form-select>
              <b-form-select fluid="sm" md="4" class="dropdownForms" v-model="filterForm.year" :options="yearOptions"></b-form-select>
              <b-button class='timeFormButton' type="submit"> Voir ces temps (Refresh)</b-button>
            </b-form> <!-- FIN DU FORMULAIRE DE REFRESHDE TEMPS -->
        </div>
        
        <div class="timeListPanel">
          <h2 class="timeListTitle"> Voici les temps de la semaine selectionnée : </h2>

          <ul>
            <li class="timeEntryTitles">  
              <p> Projet </p> 
              <p> Mois </p>
              <p> Année </p>
              <p> Temps passé </p>
              <img class="binImage" src="../assets/bin.png" width="30" heigth="30"/>
            </li>
          </ul>
          <ul>
            <li class="timeEntry" v-for="time in timeList" :key="time">
              <p class="centerParagraph"> {{time.id_project}}</p> 
              <p class="centerParagraph"> {{monthConversion[time.month - 1]}} </p>
              <p class="centerParagraph"> {{time.year}} </p>
              <p class="centerParagraph"> {{time.nb_time}}h</p>
              <div class="userButtonDiv">
                <b-form @submit="deleteTime($event,time.id)" @reset="onReset" v-if="show">
                  <b-button class="userButton" type="submit"> Supprimer le temps </b-button>
                </b-form>
              </div>
            </li>
          </ul>
        </div>

      </div>

    </div>
    <div class="plsConnect" v-else>
      <h2 class="plsConnect"> Liste des temps : Veuillez vous connecter pour pouvoir utiliser cette page. </h2>
    </div>


  </div>
</template>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<script>


export default {
   data() {
      return {
        form: {
          week: '',
          year: '',
          project: '',
          time: ''
        },
        month: 0,
        show: true,
         filterForm: {
          week: '',
          year: ''
        },
        show: true,

        weekSelected: null,
        weekOptions: [
          { value: '1', text: 'Semaine 1' },
          { value: '2', text: 'Semaine 2' },
          { value: '3', text: 'Semaine 3' },
          { value: '4', text: 'Semaine 4' },
          { value: '25', text: 'Semaine 25' }
        ],
        projectSelected: null,
        projectOptions: [],

        timeSelected: null,
        timeOptions: [
          { value: '1', text: '1 heure' },
          { value: '2', text: '2 heures' },
          { value: '3', text: '3 heures' },
          { value: '4', text: '4 heures' }
        ],

        yearOptions: [
          { value: '2021', text: '2021' }
        ],

        monthConversion: ['Janvier','Février','Mars','Avril','Mai','Juin','Juillet','Août','Septembre','Octobre','Novembre','Décembre'],

        timeList: [],

        rerenderTimes: 0
        
      }
    },
    beforeMount(){
      this.getProject();
    },

    methods: {
      getProject: function(){
        let queryProjects = '<x:Envelope xmlns:x="http://schemas.xmlsoap.org/soap/envelope/" xmlns:gsp="http://spring.io/guides/gs-producing-web-service">'+
                            '<x:Header/>'+
                            '<x:Body>'+
                            '<gsp:getAllProjectRequest></gsp:getAllProjectRequest>'+
                            '</x:Body>'+
                            '</x:Envelope>';
          let urlProject = this.$urlSOAP_Projects;
          axios.post(urlProject,
                      queryProjects,
                      {
                      headers: {
                          'Content-Type': 'text/xml'
                          }
                      })
          .then(res=>{
            var projectList = []
            var parser = new DOMParser();
            var xmlDoc = parser.parseFromString(res.data,"text/xml");
            var projects_id = xmlDoc.getElementsByTagName("ns2:id_project");
            var projects_name = xmlDoc.getElementsByTagName("ns2:name");

            for(var i=0; i < projects_id.length; i++)
            {
              
              var oneProject = 
              { 
                value: '',
                text: '',
              }

              oneProject.value = projects_id[i].childNodes[0].nodeValue;
              oneProject.text = projects_name[i].childNodes[0].nodeValue;    
              projectList.push(oneProject);
            }
            console.log(projectList);
            this.projectOptions = projectList;

          })
          .catch(err=>{console.log(err)});
    },

    getTimeList(event){
      event.preventDefault()
      let queryFetchTimes = '{'+
                              '"id_user":'+this.$userForm.user_id+','+
                              '"week":'+this.filterForm.week+','+
                              '"year":'+this.filterForm.year+
                              '}';
                            console.log(queryFetchTimes)
        
          let urlTimes = this.$urlREST_fetchTimes;

          axios({
            method: 'post',
            url: urlTimes,
            data: queryFetchTimes,
            headers: { "Content-Type": "application/json" }
                      })
          .then(timeRes=>{ this.timeList = timeRes.data;
         
          foundMatch:
          for(var i=0; i < this.timeList.length; i++)
            { 
              for (var j=0; j < this.projectOptions.length; j++){
                if(this.timeList[i].id_project == this.projectOptions[j].value){
                  this.timeList[i].id_project = this.projectOptions[j].text;
                  continue foundMatch;
                }
              }
            }
})
          .catch(err=>{console.log(err)});
    },

    addNewTime(event){
      event.preventDefault();
      let urlAddTime = 'http://176.190.50.162:8083/time';
      this.month = parseInt((this.form.week - 1)/4 + 1);
      console.log(this.month);
      let addedData = '{'+
                 '"id_user":'+this.$userForm.user_id +','+
                '"id_project":'+this.form.project +','+
                '"nb_time":'+this.form.time +','+
                '"week":'+this.form.week +','+
                '"month":'+this.month +','+
                '"year":'+this.form.year+
                '}'
      console.log(addedData);

      axios({
      method: 'post',
      url: urlAddTime, 
      data: addedData,
      headers: { "Content-Type": "application/json" }
      })
      .then(response=>{
        window.alert('Temps créé.');
      }).catch(err=>{console.log(err)});
    },

    deleteTime(event,timeId){
      event.preventDefault()
      let urlDeleteTime = this.$urlREST_deleteTime+timeId
      console.log(urlDeleteTime);
      axios.delete(urlDeleteTime, {
        headers: { "Authorization": "***" } 
      }).then(response=>{
        window.alert('Temps supprimé.');
      });
    },
    forceRerender(){
      this.rerenderTimes += 1
    },
    onSubmit(){

    },
    onReset(){

    }
  }
}
    
</script>


<style scoped>

#homepage{
  background-color: rgb(255, 255, 255);
}

body{
  font-family: 'Lato', sans-serif;
}

.homepageHeader{
  height: 8rem;
  width: 100%;
  display: flex;
  background-color: rgb(226, 226, 226);
  padding-left: 10px;
  border-bottom: solid 2px rgb(190, 91, 34);
}

#dropdownWeek{
  width: 200px;
}

.addForm{
  width: 100%;
  position: absolute;
  align-items: center;
}

.filterForm{
  width: 100%;
  align-items: center;
  display: initial;
}

.dropdownForms{
  width: 15%;
  height: 40px;
  margin: 15px;
}


.entryList{
  align-items:center;
  display: flex;
  justify-content: space-between;
}

.timeFormButton{
  width: 15%;
  margin:15px;
  position: absolute;
  height: 40px;
  font-weight:300;
  font-size: 1.1em;
  background-color: rgb(48, 48, 48);
  color: azure;
}


.plsConnect{
  text-align: center;
  align-items: center;
  color: rgb(63, 63, 63);
  margin-top: 50px;
}

.splitContainer{
  width: 100%;
  margin: auto;
}

.addTimeText{
  font-size: 1.3em;
  font-family: 'Lato', sans-serif;
  font-weight: 300;
  margin-left: 20px;
  margin-bottom: 0px;
}

.mainPage{
  width: 100%;
}

.filterPanel{
  background-color: rgb(226, 226, 226);
  border-bottom: solid 2px rgb(190, 91, 34);
  padding-left: 10px;
  padding-top: 10px;
}

.timeListPanel{
  margin: 15px;
}

.timeListTitle{
  align-items: center;
  color: rgb(63, 63, 63);
  margin-top: 20px;
  margin-left: 20px;
}

.timeEntry{
  text-decoration: none;
  font-family: 'Lato', sans-serif;
  font-size: 1.3em;
  font-weight: 200;
  margin-top: 30px;
  display: flex;
  margin: auto;
  align-items: center;
  justify-content: space-between;
  border-bottom: solid 1px rgb(175, 175, 175);
  color: rgb(190, 132, 43);
  padding-bottom: 15px;
}

.timeEntryTitles{
  text-decoration: none;
  font-family: 'Lato', sans-serif;
  font-size: 1.3em;
  font-weight: 200;
  margin-top: 30px;
  display: flex;
  margin: auto;
  align-items: center;
  justify-content: space-between;
  border-bottom: solid 1px rgb(175, 175, 175);
}

.binImage{
  margin-right: 60px;
}

.centerParagraph{
  text-align: center;
  align-items: center;
}

</style>
