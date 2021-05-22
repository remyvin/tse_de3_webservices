<template>
  <div id="Homepage">
    <header class="homepageHeader">
      <div class="entryList">
        <b-form @submit="onSubmit" @reset="onReset" v-if="show"> <!-- FORMULAIRE D'ENTREE DE TEMPS -->
          <b-form-select fluid="sm" md="4" class="dropdownForms" v-model="form.week" :options="weekOptions"></b-form-select>
          <b-form-select fluid="sm" md="4" class="dropdownForms" v-model="form.project" :options="projectOptions"></b-form-select>
          <b-form-select fluid="sm" md="4" class="dropdownForms" v-model="form.time" :options="timeOptions"></b-form-select>
          <b-button class='timeFormButton' type="submit" variant="primary">Entrer le temps</b-button>
        </b-form> <!-- FIN DU FORMULAIRE D'ENTREE DE TEMPS -->
      </div>
    </header>


  </div>
</template>

<script>
export default {
   data() {
      return {
        form: {
          week: '',
          project: '',
          time: ''
        },
        show: true,

        weekSelected: null,
        weekOptions: [
          { value: 'semaine1', text: 'Semaine 1' },
          { value: 'semaine2', text: 'Semaine 2' },
          { value: 'semaine3', text: 'Semaine 3' },
          { value: 'semaine4', text: 'Semaine 4' }
        ],
        projectSelected: null,
        projectOptions: [
          { value: 'projet1', text: 'projet 1' },
          { value: 'projet2', text: 'projet 2' },
          { value: 'projet3', text: 'projet 3' },
          { value: 'projet4', text: 'projet 4' }
        ],
        timeSelected: null,
        timeOptions: [
          { value: '0.25', text: '0.25' },
          { value: '0.5', text: '0.5' },
          { value: '0.75', text: '0.75' },
          { value: '1', text: '1' }
        ]
        
      }
    }
}

</script>


<style scoped>

#homepage{
  background-color: rgb(255, 255, 255);
}

.homepageHeader{
  height: 8rem;
  width: 100%;
  display: flex;
  background-color: rgb(224, 224, 224);
  padding-left: 10px;
}

#dropdownWeek{
  width: 200px;
}

form{
  width: 100%;
  position: absolute;
  align-items: center;
}

.dropdownForms{
  width: 15%;
  height: 40px;
  margin: 15px;
}


.entryList{
  display: flex;
  justify-content: space-between;
}

.timeFormButton{
  width: 15%;
  margin:15px;
  position: absolute;
  height: 40px;
}
</style>
