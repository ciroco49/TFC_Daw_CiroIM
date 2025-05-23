const videogameThumbnail = document.getElementById("videogameThumbnail");
const screenshotsImgTags = document.getElementsByClassName("screenshot");

const formSendComment = document.getElementById("formSendComment");
const txtAreaComment = document.getElementById("txtAreaComment");

formSendComment.addEventListener("submit", tryToSendComment);

setThumbnail();
setScreenshots();

async function setThumbnail() {
    const thumbnailUrl = await getThumbnailFromApi();
    videogameThumbnail.setAttribute("src", thumbnailUrl);
}

async function setScreenshots() {
    const screenshots = await getScrenshots();
    for (let i = 0; i < screenshots.length; i++) {
        screenshotsImgTags[i].setAttribute("src", screenshots[i].image);
    }
}

function tryToSendComment() {
    event.preventDefault();
    let isValidComment = true;

    if(txtAreaComment.nextSibling != null && txtAreaComment.nextElementSibling.tagName.toLowerCase() != "input")
        txtAreaComment.nextSibling.remove();
    if(!validateComment()) {
        isValidComment = false; txtAreaComment
        let span = document.createElement("span");
        let p = document.createElement("p");
            p.className = "text-danger mb-0 mt-2 p-0";
            p.textContent = "The comment must have between 1 and 500 characters.";
        span.appendChild(p);
        txtAreaComment.insertAdjacentElement("afterend", span);
    }

    if(isValidComment)
        formSendComment.submit();
}

function validateComment() {
    let pattern = /^.{1,500}$/;
    return (pattern.test(txtAreaComment.value)) ? true : false;
}

async function getThumbnailFromApi() {
    const idVideogame = document.getElementsByTagName("main")[0].getAttribute("data-videogame-id");
    const url = 'https://free-to-play-games-database.p.rapidapi.com/api/game?id=' + idVideogame;
    const options = {
        method: 'GET',
        headers: {
            'x-rapidapi-key': 'da2e264c31msh0f5a76c9f3898c9p1fc2f1jsnd383c1050290',
            'x-rapidapi-host': 'free-to-play-games-database.p.rapidapi.com'
        }
    };

    try {
        const response = await fetch(url, options);

        if(!response.ok) {
            throw new Error(`Response status: ${response.status}`);
        }
        
        const json = await response.json();

        return json.thumbnail;
    } catch (error) {
        console.error(error);
    }
}

async function getScrenshots() {
    const idVideogame = document.getElementsByTagName("main")[0].getAttribute("data-videogame-id");
    const url = 'https://free-to-play-games-database.p.rapidapi.com/api/game?id=' + idVideogame;
    const options = {
        method: 'GET',
        headers: {
            'x-rapidapi-key': 'da2e264c31msh0f5a76c9f3898c9p1fc2f1jsnd383c1050290',
            'x-rapidapi-host': 'free-to-play-games-database.p.rapidapi.com'
        }
    };

    try {
        const response = await fetch(url, options);

        if(!response.ok) {
            throw new Error(`Response status: ${response.status}`);
        }
        
        const json = await response.json();

        return json.screenshots;
    } catch (error) {
        console.error(error);
    }
}