<template>
  <div>
    <new-meeting-form @added="addNewMeeting($event)"></new-meeting-form>

    <span v-if="meetings.length == 0">
               Brak zaplanowanych spotkań.
           </span>
    <h3 v-else>
      Zaplanowane zajęcia ({{ meetings.length }})
    </h3>

    <meetings-list :meetings="meetings"
				   :username="username"
                   @attend="addMeetingParticipant($event)"
                   @unattend="removeMeetingParticipant($event)"
                   @delete="deleteMeeting($event)"></meetings-list>
  </div>
  
  
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
  
  
  
</template>

<script>
    import NewMeetingForm from "./NewMeetingForm";
    import MeetingsList from "./MeetingsList";

    export default {
        components: {NewMeetingForm, MeetingsList},
        props: ['username'],
        data() {
            return {
				meetings: []
            };
        },
        methods: {
            addNewMeeting(meeting) {
				// to dodane 
				this.$http.post('meetings', meeting);
                //this.meetings.push(meeting);
            },
            addMeetingParticipant(meeting) {
                meeting.participants.push(this.username);
            },
            removeMeetingParticipant(meeting) {
                meeting.participants.splice(meeting.participants.indexOf(this.username), 1);
            },
            deleteMeeting(meeting) {
                this.meetings.splice(this.meetings.indexOf(meeting), 1);
            }
        },
		// to dodane
		mounted() {
			this.$http.get('meetings').then(response => {this.meetings = response.data});
        }
    }
</script>
