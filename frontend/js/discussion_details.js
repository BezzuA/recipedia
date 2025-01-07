document.addEventListener('DOMContentLoaded', async () => {
    const params = new URLSearchParams(window.location.search);
    const discussionId = params.get('id');

    if (!discussionId) {
        console.error('No discussion ID provided');
        return;
    }

    try {
        // Fetch the discussion details
        const discRes = await fetch(`http://localhost:8080/api/discussions/${discussionId}`);
        const discussionData = await discRes.json();

        // Populate the DOM
        document.getElementById('discussion-title').textContent = discussionData.title;
        document.getElementById('discussion-username').textContent = discussionData.user?.username ?? 'Unknown';
        document.getElementById('discussion-date').textContent = discussionData.creationDate;
        document.getElementById('discussion-description').textContent = discussionData.description;

        // Replies
        renderReplies(discussionData.replies || []);
    } catch (error) {
        console.error('Error fetching discussion details:', error);
    }

    // Handle new reply submission
    const replyForm = document.getElementById('reply-form');
    replyForm.addEventListener('submit', async (event) => {
        event.preventDefault();
        const replyText = document.getElementById('reply-text').value.trim();
        if (!replyText) return;

        try {
            // POST a new reply
            const newReply = {
                text: replyText,
                // You might also need userId, or other data
            };

            const postResponse = await fetch(`http://localhost:8080/api/discussions/${discussionId}/replies`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(newReply),
            });
            const postedReply = await postResponse.json();

            addReplyToDom(postedReply);
            document.getElementById('reply-text').value = ''; // clear the text area
        } catch (err) {
            console.error('Error posting reply:', err);
        }
    });
});

function renderReplies(replies) {
    const container = document.getElementById('discussion-replies');
    container.innerHTML = '';
    replies.forEach(reply => addReplyToDom(reply));
}

function addReplyToDom(reply) {
    const container = document.getElementById('discussion-replies');

    const div = document.createElement('div');
    div.className = 'post';
    div.innerHTML = `
    <p><strong>${reply.user?.username ?? 'Anonymous'}</strong> says:</p>
    <p>${reply.text}</p>
  `;
    container.appendChild(div);
}
