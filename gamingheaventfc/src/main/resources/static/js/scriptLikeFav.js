document.addEventListener("DOMContentLoaded", () => {
  const token = document.querySelector('meta[name="_csrf"]').content;
  const header = document.querySelector('meta[name="_csrf_header"]').content;

  document.querySelectorAll(".likeA").forEach(btn => {
    btn.addEventListener("click", async event => {
      event.preventDefault();

      const a = event.currentTarget;
      const img = a.querySelector("img");
      const videogameId = a.dataset.id;
      const isLiked = a.dataset.liked == "true";

      try {
        const url = (isLiked) ? "/like/remove/" + videogameId : "/like/save/" + videogameId;
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

        if (res.ok) {
          const newSrc = isLiked
            ? "/images/white_heart.svg"
            : "/images/green_heart.svg";
          img.src = newSrc;
          a.dataset.liked = (!isLiked).toString(); 
        }

        if (res.status === 401) {
          window.location.href = "/login";
        }

      } catch (err) {
        console.error("Error en fetch:", err);
      }

    });
  });
});
