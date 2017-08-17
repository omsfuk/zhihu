<template>
  <div>
    <div class="panel panel-default">
      <div class="panel-body m-activities">
        <div class="row">
          <div class="col-md-4">
            <div class="m-activity">
              <div class="glyphicon glyphicon-music" style="font-size:30px; color: yellow"></div>
              <div>Live</div>
            </div>
          </div>
          <div class="col-md-4">
            <div class="m-activity">
              <div class="glyphicon glyphicon-book" style="font-size:30px; color: greenyellow"></div>
              <div>书店</div>
            </div>
          </div>
          <div class="col-md-4">
            <div class="m-activity">
              <div class="glyphicon glyphicon-asterisk" style="font-size:30px; color:dodgerblue"></div>
              <div>圆桌</div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-4">
            <div class="m-activity">
              <div class="glyphicon glyphicon-pencil" style="font-size:30px; color:dodgerblue"></div>
              <div>专栏</div>
            </div>
          </div>
          <div class="col-md-4">
            <div class="m-activity">
              <div class="glyphicon glyphicon-yen" style="font-size:30px; color: purple"></div>
              <div>付费咨询</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'PersonPageSideNavbar',
    data () {
      return {
      }
    }
  }
</script>
<style scoped>

</style>
