// Get the form data from an HTML form with id "myForm"
const body = document.getElementById("body");
const deleteButtons = document.getElementsByClassName("delete-button");


// URL where you want to send the request
const url = "/currency/";

body.addEventListener("click", (event) => {
    console.log("click")
    for (i = 0; i < deleteButtons.length; i++) {
        if (deleteButtons[i].contains(event.target)) {
            const student_id = parseInt(deleteButtons[i].id.replace(/\D/g, ''), 10)
            console.log(student_id)
            fetch(url + student_id, {
                method: "DELETE" // You can change this to "GET" or other HTTP methods
            })
                .then(() => {
                    location.reload()
                })
                .catch(error => {
                    console.error('An error occurred:', error);
                });
        }
    }

})