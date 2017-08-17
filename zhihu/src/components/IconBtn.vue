<template>
  <div style="display:inline-block">
    <span :class="'glyphicon glyphicon-' + iconName">&nbsp;{{btnTitle}}</span>
  </div>
</template>

<script>
export default {
  name: 'icon-btn',
  props: ['iconName', 'btnTitle']
}
</script>
<style scoped>
  div {
    padding: 5px;
    color: gray;
    cursor:pointer;
  }
  div:hover {
    color: dimgray;
  }
</style>
