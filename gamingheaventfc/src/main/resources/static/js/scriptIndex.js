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
  var platforms = getValuesFromCheckboxes(document.querySelectorAll('input[name="platformCheckbox"]:checked'));
  var genres = getValuesFromCheckboxes(document.querySelectorAll('input[name="genreCheckbox"]:checked'));
  var platformsConcatenated = "";

  if(platforms.length == 0 && genres.length == 0) {
    for (const videogame of videogames) {
      videogame_card_container.appendChild(videogame);
    }
    return ;
  }
  
  videogame_card_container.innerHTML = "";
  if(platforms.length > 0 && genres.length > 0) {
    for (const videogame of videogames) {
      if(platforms.length > 1) {
        platformsConcatenated = platforms.join(", ");
        if(platformsConcatenated == videogame.getAttribute("data-platform") && genres.includes(videogame.getAttribute("data-genre"))) {
          videogame_card_container.appendChild(videogame);
        }
      } else {
        if(platforms.includes(videogame.getAttribute("data-platform")) && genres.includes(videogame.getAttribute("data-genre")))
          videogame_card_container.appendChild(videogame);
      }  
    }
    return ;
  }

  for (const videogame of videogames) {
    if(platforms.length > 1) {
      platformsConcatenated = platforms.join(", ");
      if(platformsConcatenated == videogame.getAttribute("data-platform") || genres.includes(videogame.getAttribute("data-genre"))) 
        videogame_card_container.appendChild(videogame);
    } else {
      if(platforms.includes(videogame.getAttribute("data-platform")) || genres.includes(videogame.getAttribute("data-genre"))) 
        videogame_card_container.appendChild(videogame);
    }
  }
}

function getValuesFromCheckboxes(checkboxes) {
  var values = new Array();
  for (const checkbox of checkboxes) {
    if(checkbox.checked)
      values.push(checkbox.value);
  }
  return values;
}

function toggleGenres() {
  var genres = document.querySelectorAll('input[name="genreCheckbox"]');
  var genresNotChecked = document.querySelectorAll('input[name="genreCheckbox"]:not(:checked)');
  for (const genre of genres) {
     //Si hay alguno sin marcar, se marcan todos pero si no, se desmarcan todos
    genre.checked = genresNotChecked.length > 0;
  }
}
