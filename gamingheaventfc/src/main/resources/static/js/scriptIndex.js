setThumbnails();
const videogame_card_container = document.getElementById("videogame_card_container");
const videogames = Array.from(videogame_card_container.children);

async function setThumbnails() {
    const thumbnailsMap = await getThumbnailsFromApi();
    
    try {
        for(const [idApi, url] of thumbnailsMap) {
              var img = document.getElementById(idApi+"img");
              if(img != null) {
                img.setAttribute("src", url);
                img.className = "w-100 h-100";
              } else{
                console.log("Videojuego con idAPI " + idApi + " no guardado en BBDD");
              }
            }
    } catch (error) {
        console.error(error.message);
    }
}

async function getThumbnailsFromApi() {
    const url = 'https://free-to-play-games-database.p.rapidapi.com/api/games';
    const options = {
        method: 'GET',
        headers: {
            'x-rapidapi-key': 'da2e264c31msh0f5a76c9f3898c9p1fc2f1jsnd383c1050290',
            'x-rapidapi-host': 'free-to-play-games-database.p.rapidapi.com'
        }
    };

    try {
        const response = await fetch(url, options);

        if (!response.ok) {
          throw new Error(`Response status: ${response.status}`);
        }
    
        const json = await response.json();
        const thumbnailsMap = new Map();
        
        for (const videogame of json) {
            thumbnailsMap.set(String(videogame.id), videogame.thumbnail);
        }

        return thumbnailsMap;
      } catch (error) {
        console.error(error.message);
      }
      
}

function filterVideogames() {
  var platforms = getAllCheckedPlatforms();
  var genres = getAllCheckedGenres();

  //Lo vacío para poder añadirle solo los juegos que cumplen los filtros
  if(platforms.length == 0 && genres.length == 0) {
    for (const videogame of videogames) {
      videogame_card_container.appendChild(videogame);
    }
    return ;
  }
  videogame_card_container.innerHTML = "";
  for (const videogame of videogames) {
    if(platforms.includes(videogame.getAttribute("data-platform")) || genres.includes(videogame.getAttribute("data-genre"))) {
      videogame_card_container.appendChild(videogame);
    }
  }
}


function getAllCheckedPlatforms() {
  var platformCheckboxes = document.getElementsByClassName("platformCheckbox");
  var checked = new Array();
  for (const platform of platformCheckboxes) {
    if(platform.checked == true)
      checked.push(platform.value);
  }
  return checked;
}

function getAllCheckedGenres() {
  var genreCheckboxes = document.getElementsByClassName("genreCheckbox");
  var checked = new Array();
  for (const genre of genreCheckboxes) {
    if(genre.checked == true)
      checked.push(genre.value);
  }
  return checked;
}