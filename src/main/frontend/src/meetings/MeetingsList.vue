<template>
<div>	
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
      <td>title</td>
      <td>description</td>
      <td>participants
      </td>buttons

      </td>
    </tr>
    </tbody>
  </table>
  </div>
  

  <h1>Meetings {{ meetingsContent }}</h1>

  
</template>

<script>
    export default {
        props: ['meetings','username'],
		data() {
            return {
				meetings: [],
				meetingsContent: ""//dodane
            };
        },
		
		mounted() {
			meetingsContent += 'Meetings: ';
			var i;
			for (i = 0; i < this.meetings.length; i++) {
				meetingsContent += this.meetings[i];
			}
		}
    }
</script>
