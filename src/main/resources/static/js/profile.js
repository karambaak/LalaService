$(document).ready(function () {
    $("#resume-content").show();
    $("#resume-tab").addClass("active");

    $("#resume-tab").click(function () {
        $(".tab-item").removeClass("active");
        $(this).addClass("active");
        $(".tab-content").hide();
        $("#resume-content").show();
    });

    $("#portfolio-tab").click(function () {
        $(".tab-item").removeClass("active");
        $(this).addClass("active");
        $(".tab-content").hide();
        $("#portfolio-content").show();
    });
});

const xCloseButton = document.getElementById("close-button").addEventListener("click", closeModal)

document.getElementById("openModal").addEventListener("click", function () {
    openModal();
});

document.getElementById("close-button").addEventListener("click", function () {
    document.getElementById("side-menu").classList.remove("active");
});

function openModal() {
    document.getElementById("modal").style.display = "block";
}

function closeModal() {
    document.getElementById("modal").style.display = "none";
}

window.onclick = function (event) {
    var modal = document.getElementById("modal");
    if (event.target === modal) {
        closeModal();
    }
};


let ratingNum = document.getElementById("rating").textContent
ratingNum = ratingNum.replace(",", ".");

const rating = Math.round(parseFloat(ratingNum));
const maxRating = 5;

const ratingContainer = document.querySelector('.rating');
const stars = ratingContainer.querySelectorAll('.star');

for (let i = 0; i < maxRating; i++) {
    if (i < rating) {
        stars[i].classList.add('active');
    } else {
        stars[i].classList.remove('active');
    }
}


// Get all share buttons
const shareButtons = document.querySelectorAll('.share-button');

// Add click event listener to each button
shareButtons.forEach(button => {
    button.addEventListener('click', () => {
        // Get the URL of the current page
        const url = window.location.href;

        // Get the social media platform from the button's class name
        const platform = button.classList[1];

        // Set the URL to share based on the social media platform
        let shareUrl;
        switch (platform) {
            case 'facebook':
                shareUrl = `https://www.facebook.com/sharer/sharer.php?u=${encodeURIComponent(url)}`;
                break;
            case 'twitter':
                shareUrl = `https://twitter.com/share?url=${encodeURIComponent(url)}`;
                break;
            case 'linkedin':
                shareUrl = `https://www.linkedin.com/shareArticle?url=${encodeURIComponent(url)}`;
                break;
            case 'pinterest':
                shareUrl = `https://pinterest.com/pin/create/button/?url=${encodeURIComponent(url)}`;
                break;
            case 'reddit':
                shareUrl = `https://reddit.com/submit?url=${encodeURIComponent(url)}`;
                break;
            case 'whatsapp':
                shareUrl = `https://api.whatsapp.com/send?text=${encodeURIComponent(url)}`;
                break;
        }

        // Open a new window to share the URL
        window.open(shareUrl, '_blank');
    });
});