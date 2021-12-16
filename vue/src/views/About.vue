<template>

  <div class="about-page-view">

      <nav-bar class="about-page-view-nav"></nav-bar>

      <h1 class="about-page-view-title">About the Creators</h1>

      
      <div class="matt-div abt">
          <h2 >Matthew Bess</h2><br/>
          <p>“An investment in knowledge always pays the best interest.” — Benjamin Franklin</p><br/>
          <p><a  class="about-anchor" href="https://www.linkedin.com/in/matthew-c-bess/">https://www.linkedin.com/in/matthew-c-bess/</a></p><br/>
          <p><a  class="about-anchor" href="https://github.com/macobe11">https://github.com/macobe11</a></p><br/>
      </div>

      <div class="eric-div abt">
          <h2>Eric Catalan</h2><br/>
          <p>"The stock market is a device for transferring money from the impatient to the patient." – Warren Buffett</p><br/>
          <p><a  class="about-anchor" href="https://www.linkedin.com/in/eric-catalan/">https://www.linkedin.com/in/eric-catalan/</a></p><br/>
          <p><a  class="about-anchor" href="https://github.com/EricCatalan">https://github.com/EricCatalan</a></p><br/>
      </div>

      <div class="vaughn-div abt">
          <h2>Vaughn Wenner</h2><br/>
          <p>“Earn as much as you can, save as much as you can, invest as much as you can, give as much as you can.” — John Wesley</p><br/>
          <p><a  class="about-anchor" href="https://www.linkedin.com/in/vaughn-wenner-developer/">https://www.linkedin.com/in/vaughn-wenner-developer/</a></p><br/>
          <p><a  class="about-anchor" href="https://github.com/vdwenner">https://github.com/vdwenner</a></p><br/>
      </div>

      <div class="zack-div abt">
        <h2>Zachary Nester</h2><br/>
        <p>"Don't look for the needle in the haystack. Just buy the haystack!" — John Bogle</p><br/>
        <p><a class="about-anchor" href="https://www.linkedin.com/in/zachary-nester/">https://www.linkedin.com/in/zachary-nester/</a></p><br/>
        <p><a class="about-anchor" href="https://github.com/znester">https://github.com/znester</a></p><br/>
      </div>
      
  </div>

</template>

<script>
import NavBar from '../components/NavBar.vue'
export default {
  components: { NavBar },

}
</script>

<style>
.about-page-view{
    display: grid;
    grid-template-columns:1fr 1fr 2fr 2fr 1fr;
    grid-template-areas: 
    "nav . title title ."
    "nav . matt eric ."
    "nav . vaughn zack .";
    grid-template-rows: 1fr 2fr 2fr;
}
.about-page-view-title {
    grid-area: title;
    margin-top: 50px;
    font-size: 50px;
}
.matt-div {
    grid-area: matt;
}
.eric-div {
    grid-area: eric;
}
.vaughn-div {
    grid-area: vaughn;
}
.zack-div {
    grid-area: zack;
}
div.abt{
    margin: 25px;
    padding: 5px;
    border-radius: 30px;
    box-shadow: 10px 5px 20px  grey;
    background: #efefef;
}
a.about-anchor{
    text-decoration: none;
    cursor: pointer;
    color: rgba(4, 42, 61);
}
a.about-anchor:hover{
    text-decoration: underline;
}
a.about-anchor:link{
    color: rgba(4, 42, 61);
}
</style>