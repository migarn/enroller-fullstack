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
				this.$http.post('meetings', meeting);
				this.$http.get('meetings').then(response => {this.meetings = response.data});
            },
            addMeetingParticipant(meeting) {
				var link = 'meetings/' + meeting.id;
                this.$http.post(link, this.username);
				this.$http.get('meetings').then(response => {this.meetings = response.data});
            },
            removeMeetingParticipant(meeting) {
				var link = 'meetings/' + meeting.id + '/' + this.username;
                this.$http.delete(link);
				this.$http.get('meetings').then(response => {this.meetings = response.data});
            },
            deleteMeeting(meeting) {
				var link = 'meetings/' + meeting.id;
				this.$http.delete(link);
                this.$http.get('meetings').then(response => {this.meetings = response.data});
            }
        },
		mounted() {
			this.$http.get('meetings').then(response => {this.meetings = response.data});
        }
    }
</script>
