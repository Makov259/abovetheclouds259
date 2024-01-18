const form = document.querySelector(".signup form"),
continueBtn = form.querySelector(".button  input"),
errorText = form.querySelector(".error-txt");

form.onsubmit = (e)=>{
    e.preventDefault();
}

continueBtn.onclick = ()=>{
    //lez start with Ajax
    let xhr = new XMLHttpRequest();//creating XML object
    xhr.open("POST", "php/signup.php", true);
    xhr.onload = ()=>{
        if(xhr.readyState === XMLHttpRequest.DONE){
            if(xhr.status === 200){
                let data = xhr.response;
                if(data == "successfully"){
                    location.href = "users.php";
                }else{
                    errorText.textContent = data;
                    errorText.style.display = "block";
                   
                }
            }
        }
    }
    //sending the form data through Ajax to Php.

    let formData = new FormData(form);
    xhr.send(formData);

}