/* validate customer information */
const emailIn = document.getElementById("email-input");
const phoneIn = document.getElementById("phone-input");
const addressIn = document.getElementById("address-input");

const emailMes = document.getElementById("email-message");
const emailInvMes = document.getElementById("email-invalid-message");
const phoneMes = document.getElementById("phone-message");
const addressMes = document.getElementById("address-message");

const submitBtn = document.getElementById("btn-pay-submit");

submitBtn.addEventListener('click',function(e){	
	const email = emailIn.value.trim();
	const phone = phoneIn.value.trim();
	const address = addressIn.value.trim();
	
	if(email == "" || phone == "" || address == "") {
		e.preventDefault();
		if(email == "") {
			emailMes.classList.remove("hide");
		}
		if(phone == "") {
			phoneMes.classList.remove("hide");
		}
		if(address == "") {
			addressMes.classList.remove("hide");
		}
		console.log("not ok");
	} else if (!ValidateEmail(email)){
		e.preventDefault();
		emailInvMes.classList.remove("hide");
	} else {
		console.log("ok");
	}
})

emailIn.addEventListener('focus',function(){
	emailMes.classList.add("hide");
	emailInvMes.classList.add("hide");
})

phoneIn.addEventListener('focus',function(){
	phoneMes.classList.add("hide");
})

addressIn.addEventListener('focus',function(){
	addressMes.classList.add("hide");
})

function ValidateEmail(email) {
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(email.match(mailformat)){
		return true;
	} else {
		return false;
	}
}