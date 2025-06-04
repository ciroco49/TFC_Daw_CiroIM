export async function setThumbnails() {
    const thumbnailsMap = await getThumbnailsFromApi();
    
    try {
        for(const [idApi, url] of thumbnailsMap) {
              var img = document.getElementById(idApi+"img");
              if(img != null) {
                img.setAttribute("src", url);
                img.className = "w-100 h-100";
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