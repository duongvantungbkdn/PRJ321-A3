/* validate login */
const userIn = document.getElementById("username-input");
const passIn = document.getElementById("password-input");
const userMes = document.getElementById("username-message");
const passMes = document.getElementById("password-message");
const submitBtn = document.getElementById("login-submit-btn");
const errMessage = document.getElementById("error-mesage");

submitBtn.addEventListener('click',function(e){	
	const username = userIn.value.trim();
	const password = passIn.value.trim();
	if(username == "" || password == "") {
		e.preventDefault();
		if(username == "") {
			userMes.classList.remove("hide");
		}
		if(password == "") {
			passMes.classList.remove("hide");
		}
		console.log("not ok");
	} else {
		console.log("ok");
	}
})

userIn.addEventListener('focus',function(){
	userMes.classList.add("hide");
	errMessage.classList.add("hide");
})

passIn.addEventListener('focus',function(){
	passMes.classList.add("hide");
	errMessage.classList.add("hide");
})



