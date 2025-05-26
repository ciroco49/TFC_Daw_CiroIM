import { setThumbnails } from './scriptUtils.js';

setThumbnails();
const videogame_card_container = document.getElementById("videogame_card_container");
const videogames = Array.from(videogame_card_container.children);
const selectOrderBy = document.getElementById("selectOrderBy");
const toggleGenresIndex = document.getElementById("toggleGenresIndex");
const inTitleSearch = document.getElementById("inTitleSearch");

// ================== LISTENERS ================================

selectOrderBy.addEventListener("change", orderBy);

document.querySelectorAll('input[name="platformCheckbox"], input[name="genreCheckbox"]').forEach(cb => {
  cb.addEventListener("change", filterVideogames);
});

toggleGenresIndex.addEventListener("click", toggleGenres);

inTitleSearch.addEventListener("input", searchByTitle);

// ============== FILTER GAMES WITH CHECKBOX ===================

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

// =============================================================



// ================= ORDER BY ==================================

function orderBy() {
  var videogames = Array.from(videogame_card_container.children);
  switch(selectOrderBy.value) {
    case "alphabetical":
      orderAlphabetically(videogames);
      break;
    case "likes":
      orderByLikes(videogames);
      break;
    case "date":
      orderByReleaseDate(videogames);
      break;
  }
}

function orderAlphabetically(videogames) {
  videogame_card_container.innerHTML = "";
  return videogames.sort((a, b) => 
    a.getAttribute("data-title").localeCompare(b.getAttribute("data-title"))
  ).forEach((videogame) => {
    videogame_card_container.appendChild(videogame);
  });
}

function orderByReleaseDate(videogames) {
  videogame_card_container.innerHTML = "";
  return videogames.sort((a, b) => 
    new Date(b.getAttribute("data-date")) - new Date(a.getAttribute("data-date"))
  ).forEach((videogame) => {
    videogame_card_container.appendChild(videogame);
  });
}

function orderByLikes(videogames) {
  videogame_card_container.innerHTML = "";
  return videogames.sort((a, b) => 
  (b.getAttribute("data-likes") - a.getAttribute("data-likes"))
  ).forEach((videogame) => {
    videogame_card_container.appendChild(videogame);
  });
}

// =============================================================



// ============= SEARCH BY TITLE  ==============================

function searchByTitle() {
  videogame_card_container.innerHTML = "";
  const inText = inTitleSearch.value.toLowerCase();
  const videogamesFound = videogames.filter((videogame) => videogame.dataset.title.toLowerCase().includes(inText));
  videogamesFound.forEach(videogame => videogame_card_container.appendChild(videogame));
}