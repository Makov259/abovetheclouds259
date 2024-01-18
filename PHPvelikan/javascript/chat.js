// Add this at the beginning of your chat.js file
document.addEventListener("clearChat", () => {
    // Send a request to the server to clear liked messages
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "php/clear-chat.php", true);
    xhr.send();
});


const form = document.querySelector(".typing-area"),
    inputField = form.querySelector(".input-field"),
    sendBtn = form.querySelector("button"),
    chatBox = document.querySelector(".chat-box");

const likedMessages = new Set();

form.onsubmit = (e) => {
    e.preventDefault();
};

sendBtn.onclick = () => {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "php/insert-chat.php", true);
    xhr.onload = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                inputField.value = "";
                scrollToBottomAuto();
            }
        }
    };
    let formData = new FormData(form);
    xhr.send(formData);
};

setInterval(() => {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "php/get-chat.php", true);
    xhr.onload = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                let data = xhr.response;
                let shouldScroll = chatBox.scrollTop + chatBox.clientHeight === chatBox.scrollHeight;

                chatBox.innerHTML = data;

                if (shouldScroll) {
                    scrollToBottomAuto();
                }

                attachEventListeners();
            }
        }
    };
    let formData = new FormData(form);
    xhr.send(formData);
}, 500);

function scrollToBottomAuto() {
    chatBox.scrollTop = chatBox.scrollHeight;
}

function attachEventListeners() {
    const messageBoxes = document.querySelectorAll(".chat-box .chat");

    messageBoxes.forEach((messageBox) => {
        // Add event listener for double click
        messageBox.addEventListener("dblclick", () => {
            const msgId = messageBox.dataset.msgId;
            if (msgId) {
                const likesCountElement = document.getElementById(`likesCount_${msgId}`);
                const likeCount = parseInt(likesCountElement.textContent);

                // Log the like count to the console for debugging
                console.log(`Message ${msgId} Likes: ${likeCount}`);

                if (likeCount > 1) {
                    // If like count is greater than 1, remove the heart icon
                    dislikeMessage(msgId);
                } else {
                    // If like count is 1 or less, toggle the heart icon
                    const heartIcon = document.getElementById(`heartIcon_${msgId}`);

                    if (heartIcon) {
                        // If the heart icon exists, remove it (dislike)
                        dislikeMessage(msgId);
                    } else {
                        // If the heart icon doesn't exist, like the message
                        likeMessage(msgId);
                    }
                }
            }
        });
    });
}
function likeMessage(msgId) {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "php/like-message.php", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.onload = () => {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            try {
                const response = JSON.parse(xhr.responseText);

                if (response.updatedLikes !== undefined) {
                    const likesCountSpan = document.getElementById(`likesCount_${msgId}`);
                    const heartIcon = document.getElementById(`heartIcon_${msgId}`);

                    if (likesCountSpan && heartIcon) {
                        likesCountSpan.textContent = response.updatedLikes;

                        if (response.updatedLikes > 0) {
                            heartIcon.style.display = 'inline-block';
                        } else {
                            heartIcon.style.display = 'none';
                        }
                    }
                } else if (response.error) {
                    console.error(response.error);
                }
            } catch (error) {
                console.error("Error parsing JSON response:", error);
            }
        }
    };

    xhr.send(`msg_id=${msgId}`);
}



function clearChat() {
    console.log("Clearing chat...");
    const chatBox = document.querySelector(".chat-box");

    // Clear the chat box content
    chatBox.innerHTML = "";

    // Clear the set of liked messages
    likedMessages.clear();

    // Trigger an event to inform the server to clear liked messages
    const clearEvent = new Event("clearChat");
    document.dispatchEvent(clearEvent);

    // Scroll to the bottom after clearing the chat
    scrollToBottomAuto();
}
