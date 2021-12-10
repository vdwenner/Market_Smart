<template>
    <div class="game-container">
        <h1 class="game-name">{{ game.name }}</h1>
    </div>
</template>

<script>
export default {
    name: 'game-details',
    /* Took this directly from the 3-15 HW */
    props: {
        game: Object
    }
}
</script>

<style>

</style>