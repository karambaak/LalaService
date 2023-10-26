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