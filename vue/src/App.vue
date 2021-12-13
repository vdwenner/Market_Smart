<template>
  <div>  
    <router-view />
  </div>
</template>

<style>
.view-game-container{
    display: grid;
    grid-template-columns:1fr 1fr 1fr 1fr 1fr 1fr;
    grid-template-areas: 
    "nav . title title title ."
    "nav . table table table ."
    "nav . pending pending pending .";

}
 .pending-invites{
    display: flex;
    grid-area: pending;
    
} 

.nav{
    grid-area: nav;
}

.title {
    grid-area: title;
    text-align: center;
    margin-bottom: 20px;
    height: 100px;
}

.game-header{
    display:flex;
    border-radius: 10px;
	background: rgba(4, 42, 61) 0%;
    color: #c99200;
    justify-content: space-evenly;
} 


.pending-invites-table >thead{
    background: rgba(4, 42, 61) 0%;
    color: #c99200;
}
.pending-invites-table {
    width: 100%;
}
tr.game-info:hover {
    background: rgba(4, 42, 61) 0%;
    color: #c99200;
}
 

#invite-form:hover {
    text-decoration: #c99200;
}
div.pending-invites{
    width: 100%;
    justify-content: center;
}

table.userGames {
  background: #012B39;
  border-radius: 0.25em;
  border-collapse: collapse;
  margin: 1em;
  grid-area: table;
  height: 500px;
  overflow-y: scroll;
  box-shadow: 10px 10px 10px #999;
}

thead {
    display: flex;
    justify-content: space-between;
    width: 80%;
}

th {
  border-bottom: 1px solid #364043;
  color: #E2B842;
  font-size: 0.85em;
  font-weight: 600;
  padding: 0.5em 1em;
  text-align: center;
  width: 100px;
}
td {
  background: #012B39;
  color: #fff;
  font-weight: 400;
  padding: 0.65em 1em;
  width: 100px;
}
.disabled td {
  color: #4F5F64;
}
tbody.pending-invites-data tr {
  transition: background 0.25s ease;
}
tbody.pending-invites-data tr:hover {
  background: #fff;
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
</style>
