const formEdit = document.getElementById("editAccountForm");

const uploadImg = document.getElementById("uploadImg");
const imgUser = document.getElementById("imgUser");
const inNicknameEdit = document.getElementById("inNicknameEdit");
const inDescriptionEdit = document.getElementById("inDescriptionEdit");

formEdit.addEventListener("submit", tryToSubmitEdit);
uploadImg.addEventListener("change", setPreviewImg);

function tryToSubmitEdit() {
    event.preventDefault();
    let allValidFields = true;


    if(uploadImg.nextSibling != null) uploadImg.nextSibling.remove();
    if(uploadImg.files && uploadImg.files.length > 0 && !isValidFileFormat(uploadImg.files[0])) {
        allValidFields = false;
        let span = document.createElement("span");
        let p = document.createElement("p");
            p.className = "text-danger mb-0 mt-2 p-0";
            p.textContent = "The file has to be .png, .jpg or .jpeg.";
        span.appendChild(p);
        uploadImg.insertAdjacentElement("afterend", span);
    }

    if(inNicknameEdit.nextSibling != null) inNicknameEdit.nextSibling.remove();
    if(!isValidNickname(inNicknameEdit.value)) {
        allValidFields = false;
        let span = document.createElement("span");
        let p = document.createElement("p");
            p.className = "text-danger mb-0 mt-2 p-0";
            p.textContent = "The nickname must start with a capital letter and have a maximum length of 20 characters.";
        span.appendChild(p);
        inNicknameEdit.insertAdjacentElement("afterend", span);
    }

    if(!isValidDescription(inDescriptionEdit.value)) {
        allValidFields = false;
        let span = document.createElement("span");
        let p = document.createElement("p");
            p.className = "text-danger mb-0 mt-2 p-0";
            p.textContent = "The description must have a maximum length of 500 characters.";
        span.appendChild(p);
        inDescriptionEdit.insertAdjacentElement("afterend", span); 
    }

    if(allValidFields)
        formEdit.submit();
}

function setPreviewImg() {
    const reader =  new FileReader();
    reader.onload = function(event) {
        imgUser.src = event.target.result;
    };
    reader.readAsDataURL(uploadImg.files[0])
}

function isValidFileFormat(value) {
    let isValidFileFormat = true;
    let imgTypes = ["image/png", "image/jpg", "image/jpeg"];

    if(!imgTypes.includes(value.type)) {
        isValidFileFormat = false;
    }
    return isValidFileFormat;
}

function isValidNickname(value) {
    let isValidNickname = true;
    let pattern = /^[A-Z][a-zA-Z0-9_]{0,19}$/;

    if(!pattern.test(value))
        isValidNickname = false;

    return isValidNickname;
}

function isValidDescription(value) {
    let isValidDescription = true;
    let pattern = /^.{0,500}$/;
    
    if(!pattern.test(value))
        isValidDescription = false;

    return isValidDescription;
}