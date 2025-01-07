document.addEventListener('DOMContentLoaded', async () => {
    try {
        // Example: REST endpoint to get challenges
        // GET http://localhost:8080/api/challenges
        const response = await fetch('http://localhost:8080/api/challenges');
        const challenges = await response.json();

        renderChallenges(challenges);
    } catch (error) {
        console.error('Error fetching challenges:', error);
    }
});

function renderChallenges(challenges) {
    const container = document.getElementById('challenges-container');
    container.innerHTML = ''; // clear old data if any

    challenges.forEach(challenge => {
        const challengeDiv = document.createElement('div');
        challengeDiv.className = 'post';

        challengeDiv.innerHTML = `
      <h4>${challenge.title}</h4>
      <p><strong>Start Date: </strong> ${challenge.startDate}</p>
      <p><strong>End Date: </strong> ${challenge.endDate}</p>
      <p>${challenge.description}</p>
      <a href="challenge_details.html?id=${challenge.challengeId}">
        View Joined Recipes (${challenge.numberOfRecipes ?? 0})
      </a>
    `;

        container.appendChild(challengeDiv);
    });
}
