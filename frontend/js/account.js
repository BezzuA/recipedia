document.addEventListener('DOMContentLoaded', async () => {
    try {

        // Fetch user info
        const userResponse = await fetch('http://localhost:8080/api/users/me');
        const userData = await userResponse.json();

        // Populate user account info
        document.getElementById('account-username').textContent = userData.username;
        document.getElementById('account-email').textContent = userData.email;
        document.getElementById('account-bio').textContent = userData.bio;

        // Fetch user’s recipes
        const recipesResponse = await fetch('http://localhost:8080/api/users/me/recipes');
        const recipesData = await recipesResponse.json();
        renderListItems('my-recipes-list', recipesData, 'recipe');

        // Fetch user’s discussions
        const discussionsResponse = await fetch('http://localhost:8080/api/users/me/discussions');
        const discussionsData = await discussionsResponse.json();
        renderListItems('my-discussions-list', discussionsData, 'discussion');

        // Fetch user’s challenges
        const challengesResponse = await fetch('http://localhost:8080/api/users/me/challenges');
        const challengesData = await challengesResponse.json();
        renderListItems('my-challenges-list', challengesData, 'challenge');

    } catch (error) {
        console.error('Error loading account info:', error);
    }
});

// A helper function to render a list of items into a <ul>
function renderListItems(listId, items, type) {
    const listEl = document.getElementById(listId);
    listEl.innerHTML = ''; // clear existing

    items.forEach(item => {
        // E.g., item could have .title, .recipeId, .discussionId, .challengeId
        const li = document.createElement('li');

        const a = document.createElement('a');
        a.textContent = item.title || '(Untitled)';
        a.href = generateLink(type, item); // We'll define a helper below
        li.appendChild(a);

        listEl.appendChild(li);
    });
}

function generateLink(type, item) {
    switch (type) {
        case 'recipe':
            return `recipe_details.html?id=${item.recipeId}`;
        case 'discussion':
            return `discussion_details.html?id=${item.discussionId}`;
        case 'challenge':
            return `challenge_details.html?id=${item.challengeId}`;
        default:
            return '#';
    }
}
