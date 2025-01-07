document.addEventListener('DOMContentLoaded', async () => {
    const params = new URLSearchParams(window.location.search);
    const recipeId = params.get('id');
    if (!recipeId) {
        console.error('No recipe ID provided');
        return;
    }

    await loadRecipeDetails(recipeId);

    // Handle comment form
    const commentForm = document.getElementById('comment-form');
    commentForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        await postComment(recipeId);
    });
});

async function loadRecipeDetails(recipeId) {
    try {
        // GET /api/recipes/{id}
        const response = await fetch(`http://localhost:8080/api/recipes/${recipeId}`);
        if (!response.ok) throw new Error(`Failed to fetch recipe: ${response.statusText}`);
        const recipeData = await response.json();

        // Populate DOM
        document.getElementById('recipe-title').textContent = recipeData.title;
        document.getElementById('recipe-description').textContent = recipeData.description;
        document.getElementById('recipe-instructions').textContent = recipeData.instructions;
        document.getElementById('recipe-rating').textContent = recipeData.averageRating ?? 'N/A';

        // Ingredients
        const ingredientsUl = document.getElementById('recipe-ingredients');
        ingredientsUl.innerHTML = '';
        (recipeData.ingredients || []).forEach(ing => {
            const li = document.createElement('li');
            li.textContent = ing;
            ingredientsUl.appendChild(li);
        });

        // Comments
        renderComments(recipeData.comments || []);
    } catch (error) {
        console.error(error);
    }
}

function renderComments(comments) {
    const commentsUl = document.getElementById('recipe-comments');
    commentsUl.innerHTML = '';
    comments.forEach(comment => {
        const li = document.createElement('li');
        li.textContent = `"${comment.text}" - ${comment.user?.username ?? 'Unknown'}`;
        commentsUl.appendChild(li);
    });
}

async function postComment(recipeId) {
    const commentText = document.getElementById('comment-text').value.trim();
    if (!commentText) return;

    const newComment = { text: commentText };
    try {
        // POST /api/recipes/{id}/comments
        const response = await fetch(`http://localhost:8080/api/recipes/${recipeId}/comments`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newComment),
        });
        if (!response.ok) throw new Error(`Failed to post comment: ${response.statusText}`);

        await loadRecipeDetails(recipeId);
        document.getElementById('comment-text').value = '';
    } catch (err) {
        console.error(err);
    }
}
