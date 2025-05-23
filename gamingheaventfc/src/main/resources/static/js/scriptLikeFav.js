document.addEventListener("DOMContentLoaded", () => {
  const token = document.querySelector('meta[name="_csrf"]').content;
  const header = document.querySelector('meta[name="_csrf_header"]').content;

  document.querySelectorAll(".likeA, .favA").forEach(btn => {
    btn.addEventListener("click", async event => {
      event.preventDefault();

      const a = event.currentTarget;
      const img = a.querySelector("img");
      const videogameId = a.dataset.id;
      
      let isLiked, isFav, url, newSrc;

      if (a.classList.contains("likeA")) {
        isLiked = a.dataset.liked === "true";
        url = isLiked ? `/like/remove/${videogameId}` : `/like/save/${videogameId}`;
        newSrc = isLiked ? "/images/white_heart.svg" : "/images/green_heart.svg";
        a.dataset.liked = (!isLiked).toString();
      } else if (a.classList.contains("favA")) {
        isFav = a.dataset.fav === "true";
        url = isFav ? `/fav/remove/${videogameId}` : `/fav/save/${videogameId}`;
        newSrc = isFav ? "/images/white_star.svg" : "/images/green_star.svg";
        a.dataset.fav = (!isFav).toString();
      }

      try {
        const res = await fetch(url, {
          method: "POST",
          headers: {
            "X-Requested-With": "XMLHttpRequest",
            [header]: token
          }
        });

        if (res.redirected) {
            window.location.href = res.url;
            return;
        }

        if (res.ok) img.src = newSrc;

        if(res.status == 405) showLimitFavsAlert();

        if (res.status === 401) window.location.href = "/login";

      } catch (err) {
        console.error("Error en fetch:", err);
      }

    });
  });
});

function showLimitFavsAlert() {
    const alertFavDiv = document.getElementById('alertFavDiv')

      const alertFav = document.createElement('div')
      alertFav.className = "alert alert-dismissible alert-secondary shadow-lg text-center"
      alertFav.innerHTML = `
           <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
           <strong>Info!</strong> The favorite limit <strong>3 videogames.</strong>`;

      alertFavDiv.append(alertFav);

    setTimeout(() => {
      const alertInstance = bootstrap.Alert.getOrCreateInstance(alertFav);
      alertInstance.close();
    }, 3000);

}

