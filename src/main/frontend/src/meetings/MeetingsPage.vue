<template>
  <div>
    <new-meeting-form :key="key" @added="addNewMeeting($event)"></new-meeting-form>

    <span v-if="meetings.length == 0">
               Brak zaplanowanych spotkań.
           </span>
    <h3 v-else>
      Zaplanowane zajęcia ({{ meetings.length }})
    </h3>

    <meetings-list :meetings="meetings"
				   :username="username"
				   :key="key"
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
				meetings: [],
				key: 0
            };
        },
        methods: {
            addNewMeeting(meeting) {
				// to dodane 
				this.$http.post('meetings', meeting);
				this.$set('key', this.key + 1);
				//this.$router.go(0);
                //this.meetings.push(meeting);
            },
            addMeetingParticipant(meeting) {
                meeting.participants.push(this.username);
				this.key += 1;
            },
            removeMeetingParticipant(meeting) {
                meeting.participants.splice(meeting.participants.indexOf(this.username), 1);
				this.key += 1;
            },
            deleteMeeting(meeting) {
                this.meetings.splice(this.meetings.indexOf(meeting), 1);
				this.key += 1;
            }
        },
		// to dodane
		mounted() {
			this.$http.get('meetings').then(response => {this.meetings = response.data});
        }
    }
</script>
