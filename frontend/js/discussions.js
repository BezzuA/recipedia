document.addEventListener('DOMContentLoaded', async () => {
    await loadDiscussions();

    // Handle new discussion post
    const discussionForm = document.getElementById('discussion-form');
    discussionForm.addEventListener('submit', async (event) => {
        event.preventDefault();
        await postNewDiscussion();
    });
});

async function loadDiscussions() {
    try {
        // Suppose your endpoint is GET /api/discussions
        const response = await fetch('http://localhost:8080/api/discussions');
        const discussions = await response.json();
        renderDiscussions(discussions);
    } catch (error) {
        console.error('Error loading discussions:', error);
    }
}

function renderDiscussions(discussions) {
    const container = document.getElementById('discussions-container');
    container.innerHTML = ''; // clear existing

    discussions.forEach(d => {
        const div = document.createElement('div');
        div.className = 'post';

        div.innerHTML = `
      <h4>${d.title}</h4>
      <p>Posted by <strong>${d.user?.username ?? 'Unknown'}</strong> on ${d.creationDate}</p>
      <p>${d.description}</p>
      <a href="discussion_details.html?id=${d.discussionId}">View Replies (${d.repliesCount || 0})</a>
    `;

        container.appendChild(div);
    });
}

async function postNewDiscussion() {
    const titleEl = document.getElementById('discussion-title');
    const contentEl = document.getElementById('discussion-content');

    const newDiscussion = {
        title: titleEl.value,
        description: contentEl.value,
        // user or other properties if needed
    };

    try {
        // Suppose your endpoint is POST /api/discussions
        const response = await fetch('http://localhost:8080/api/discussions', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newDiscussion),
        });

        if (!response.ok) {
            throw new Error(`Failed to post discussion: ${response.statusText}`);
        }

        // Clear form
        titleEl.value = '';
        contentEl.value = '';
        
        await loadDiscussions();
    } catch (error) {
        console.error('Error posting new discussion:', error);
    }
}
