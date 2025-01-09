document.addEventListener('DOMContentLoaded', async () => {
    // Suppose we pass the challenge ID as a query param: challenge_details.html?id=123
    const params = new URLSearchParams(window.location.search);
    const challengeId = params.get('id');

    if (!challengeId) {
        console.error('No challenge ID provided in URL');
        return;
    }

    try {
        // Fetch challenge data from REST or GraphQL
        const challengeResponse = await fetch(`http://localhost:8080/api/challenges/${challengeId}`);
        const challengeData = await challengeResponse.json();

        // Populate DOM
        document.getElementById('challenge-title').textContent = challengeData.title;
        document.getElementById('challenge-description').textContent = challengeData.description;

        // Render the recipes related to this challenge
        // Example: challengeData.recipes might be an array
        const recipesListEl = document.getElementById('challenge-recipes-list');
        recipesListEl.innerHTML = ''; // clear existing

        const recipes = challengeData.recipes || [];
        recipes.forEach(recipe => {
            const li = document.createElement('li');
            const a = document.createElement('a');
            a.textContent = recipe.title || 'No Title';
            a.href = `recipe_details.html?id=${recipe.recipeId}`;
            li.appendChild(a);
            recipesListEl.appendChild(li);
        });
    } catch (err) {
        console.error('Error fetching challenge details:', err);
    }
});
