<template>
  <div id="Edition">
    <div v-if="$userForm.role != ''">
      <div class="filterPanel">
            <b-form class="filterForm" @submit="getTimeList" @reset="onReset" v-if="show"> <!-- FORMULAIRE DE REFRESH DE TEMPS -->
              <p class="addTimeText"> Choisir un mois à éditer   </p>
              <b-form-select fluid="sm" md="4" class="dropdownForms" v-model="filterForm.month" :options="monthOptions"></b-form-select>
              <b-form-select fluid="sm" md="4" class="dropdownForms" v-model="filterForm.year" :options="yearOptions"></b-form-select>
              <b-button class='timeFormButton' type="submit"> Voir les temps</b-button>
            </b-form> <!-- FIN DU FORMULAIRE DE REFRESHDE TEMPS -->
            <div v-if="timeList[0] != null">
              <button class="printButton" @click="print"> Imprimer </button>
            </div>
      </div>

      <div id="printMe">
        <h2 class="timeListTitle"> Voici les temps du mois selectionné : </h2>

        <ul>
          <li class="timeEntryTitles">  
            <p> Projet </p> 
            <p> Mois </p>
            <p> Année </p>
            <p> Temps passé </p>
          </li>
        </ul>
        <ul>
          <li class="timeEntry" v-for="time in timeList" :key="time">
            <p class="centerParagraph"> {{time.id_project}}</p> 
            <p class="centerParagraph"> {{monthConversion[time.month - 1]}} </p>
            <p class="centerParagraph"> {{time.year}} </p>
            <p class="centerParagraph"> {{time.nb_time}}h</p>
          </li>
        </ul>
      </div>

    </div>

    <div class="plsConnect" v-else>
      <h2 class="plsConnect"> Edition : Veuillez vous connecter pour pouvoir utiliser cette page. </h2>
    </div>
  </div>
</template>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<script>

export default {
  data(){
    return{
      output: null,
      filterForm: {
          month: '',
          year: ''
      },
      show: true,
      yearOptions: [
          { value: '2021', text: '2021' }
      ],
      monthOptions: [
        { value: '1', text: 'Janvier' },
        { value: '2', text: 'Février' },
        { value: '3', text: 'Mars' },
        { value: '4', text: 'Avril' },
        { value: '5', text: 'Mai' },
        { value: '6', text: 'Juin' },
        { value: '7', text: 'Juillet' },
        { value: '8', text: 'Août' },
        { value: '9', text: 'Septembre' },
        { value: '10', text: 'Octobre' },
        { value: '11', text: 'Novembre' },
        { value: '12', text: 'Décembre' }
      ],
      timeList: [],

      projectSelected: null,
      projectOptions: [],
      monthConversion: ['Janvier','Février','Mars','Avril','Mai','Juin','Juillet','Août','Septembre','Octobre','Novembre','Décembre']
    }
  },

  beforeMount(){
      this.getProject();
    },

  methods: {
    print(){
      this.$htmlToPaper('printMe');
    },

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
                              '"month":'+this.filterForm.month+','+
                              '"year":'+this.filterForm.year+
                              '}';
                            console.log(queryFetchTimes)
        
          let urlTimes = this.$urlREST_fetchMonthly;

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
    onSubmit(){

    },
    onReset(){

    }
  }
}
</script>


<style scoped>


.plsConnect{
  text-align: center;
  align-items: center;
  color: rgb(63, 63, 63);
  margin-top: 50px;
}

.timeListTitle{
  align-items: center;
  color: rgb(63, 63, 63);
  margin-top: 20px;
  margin-left: 20px;
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

.filterPanel{
  background-color: rgb(226, 226, 226);
  border-bottom: solid 2px rgb(190, 91, 34);
  padding-left: 10px;
  padding-top: 10px;
}

.addTimeText{
  font-size: 1.3em;
  font-family: 'Lato', sans-serif;
  font-weight: 300;
  margin-left: 20px;
  margin-bottom: 0px;
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


.timeListTitle{
  align-items: center;
  color: rgb(63, 63, 63);
  margin-top: 80px;
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
  margin-right: 20px;
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
  margin-right: 20px;
}

.centerParagraph{
  text-align: center;
  align-items: center;
}

.printButton{
  width: 15%;
  margin:15px;
  position: absolute;
  height: 40px;
  font-weight:300;
  font-size: 1.1em;
  background-color: rgb(48, 48, 48);
  color: azure;
}

</style>