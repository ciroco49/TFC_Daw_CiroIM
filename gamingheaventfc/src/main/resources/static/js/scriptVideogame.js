const videogameThumbnail = document.getElementById("videogameThumbnail");
const screenshotsImgTags = document.getElementsByClassName("screenshot");
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