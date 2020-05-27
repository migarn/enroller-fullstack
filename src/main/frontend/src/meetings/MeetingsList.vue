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
</template>

<script>
    export default {
        props: ['meetings','username', 'key'],
		data() {
            return {
				meetings: []
            };
        }
    }
</script>
