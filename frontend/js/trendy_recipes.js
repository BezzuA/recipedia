document.addEventListener('DOMContentLoaded', async () => {
    try {
        // GET /api/recipes/trendy or similar
        const response = await fetch('http://localhost:8080/api/recipes/trendy');
        if (!response.ok) throw new Error('Failed to fetch trendy recipes');

        const recipes = await response.json();
        renderTrendyRecipes(recipes);
    } catch (error) {
        console.error('Error loading trendy recipes:', error);
    }
});

function renderTrendyRecipes(recipes) {
    const listEl = document.getElementById('trendy-recipes-list');
    listEl.innerHTML = '';

    recipes.forEach(r => {
        const li = document.createElement('li');
        li.innerHTML = `
      <a href="recipe_details.html?id=${r.recipeId}">${r.title}</a>
      - ${r.viewCount ?? 0} views
    `;
        listEl.appendChild(li);
    });
}
