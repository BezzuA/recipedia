document.addEventListener('DOMContentLoaded', async () => {
    try {
        const gqlQuery = {
            query: `
        query {
          popularRecipes {
            recipeId
            title
            description
            viewCount
          }
        }
      `
        };

        // POST to /graphql
        const response = await fetch('http://localhost:8080/graphql', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(gqlQuery),
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        // Parse the JSON
        const result = await response.json();
        if (result.errors) {
            console.error('GraphQL errors:', result.errors);
            return;
        }
        
        const recipes = result.data.popularRecipes || [];
        renderPopularRecipes(recipes);

    } catch (error) {
        console.error('Error fetching popular recipes (GraphQL):', error);
    }
});

function renderPopularRecipes(recipes) {
    const listEl = document.getElementById('popular-recipes-list');
    listEl.innerHTML = '';

    recipes.forEach(r => {
        const li = document.createElement('li');
        // Example: Show the recipe title and the viewCount
        li.textContent = `${r.title} - Views: ${r.viewCount ?? 0}`;
        listEl.appendChild(li);
    });
}
