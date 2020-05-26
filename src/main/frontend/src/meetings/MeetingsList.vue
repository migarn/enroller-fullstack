<template>

	// do usunięcia
	<div v-if="meetings.length > 0">Meetings.length = {{ meetings.length }}</div>
	// do usunięcia
	
  <table v-if="meetings.length > 0"> 
    <thead>
    <tr>
      <th>Nazwa spotkania</th>
      <th>Opis</th>
      <th>Uczestnicy</th>
      <td></td>
    </tr>
    </thead>
    <tbody>
    <tr v-for="meeting in meetings" :key="meeting.title">
      <td>{{ meeting.title }}</td>
      <td>{{ meeting.description }}</td>
      <td>
        <ul v-if="meeting.participants">
          <li v-for="participant in meeting.participants" :key="participant">
            {{ participant }}
          </li>
        </ul>
      </td>
      <td style="text-align: right; min-width: 400px">
        <button v-if="meeting.participants.indexOf(username) < 0" class="button-outline"
                @click="$emit('attend', meeting)">
          Zapisz się
        </button>
        <button v-else class="button-outline" @click="$emit('unattend', meeting)">Wypisz się</button>
        <button v-if="meeting.participants.length === 0" class="button" @click="$emit('delete', meeting)">
          Usuń puste spotkanie
        </button>
      </td>
    </tr>
    </tbody>
  </table>
  
  
  
  // do usunięcia
    <table v-if="meetings.length > 0"> 
    <thead>
    <tr>
      <th>Nazwa spotkania</th>
      <th>Opis</th>
      <th>Uczestnicy</th>
      <td></td>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td>drzewo</td>
      <td>jtyjty</td>
      <td>
        <ul>
          <li>
            jhtjtyjtyjtyjtyj
          </li>
        </ul>
      </td>
      <td style="text-align: right; min-width: 400px">
        <button>
          Zapisz się
        </button>
      </td>
    </tr>
    </tbody>
  </table>
  // do usunięcia
  
  
</template>

<script>
    export default {
        props: ['meetings','username'],
		data() {
            return {
				meetings: []
            };
        }
    }
</script>
