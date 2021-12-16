<template>
  <div>  
    <router-view />
  </div>
</template>

<style>
/* @import url(https://fonts.googleapis.com/css?family=Open+Sans:400,600); */
@import url('https://fonts.googleapis.com/css2?family=Inter&family=Lexend+Deca&display=swap');

*, *:before, *:after {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  background: #ccd9dd;
  font-family: 'Inter', sans-serif;
}

.btn {
        background: rgba(4, 42, 61) 0%;
        color: #eeaf03;
        align-content: center;
        text-align: center;
        border-radius: 25px;
        width: 100px;
        height: 40px;
        cursor: pointer;
    }

table{
  background: #012B39;
  border-radius: 0.25em;
  border-collapse: collapse;
  margin: 1em;
  max-height: 500px;
}
th {
  border-bottom: 1px solid #364043;
  color: #E2B842;
  font-size: 0.85em;
  font-weight: 600;
  padding: 0.5em 1em;
  text-align: center;
  width: 150px;
  border-radius: 10px;
  justify-content: space-evenly;
}
td {
  text-align: center;
  color: #fff;
  font-weight: 400;
  padding: 0.65em 1em;
  align-content: center;
  width: 150px;
}
.disabled td {
  color: #4F5F64;
}
tbody tr {
  transition: background 0.25s ease;
  align-content: center;
}
tbody tr:hover {
  background: #014055;
}
tr{
  display: flex;
  align-items: center;
  justify-content: space-evenly;
}

::-webkit-scrollbar {
    width: 10px;
}

::-webkit-scrollbar-track {
    background: #f1f1f1;
}

::-webkit-scrollbar-thumb {
  background: #888; 
}

::-webkit-scrollbar-thumb:hover {
  background: #555; 
}

h1 {
  font-size: 50px;
  margin-bottom: 25px;
}


</style>
