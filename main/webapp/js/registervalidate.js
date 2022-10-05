/* validate register information */
const emailIn = document.getElementById("email-input");
const passIn = document.getElementById("pass-input");
const cpassIn = document.getElementById("c-pass-input");
const nameIn = document.getElementById("name-input");

const emailMes = document.getElementById("email-message");
const passMes = document.getElementById("pass-message");
const cpassMes = document.getElementById("confirm-pass-message");
const nameMes = document.getElementById("name-message");
const serverMes = document.getElementById("server-mesage");

const submitBtn = document.getElementById("btn-register-submit");

submitBtn.addEventListener('click',function(e){	
	const email = emailIn.value.trim();
	const pass = passIn.value.trim();
	const cpass = cpassIn.value.trim();
	const name = nameIn.value.trim();
	
	if(email == "" || pass == "" || name == "") {
		e.preventDefault();
		if(email == "") {
			emailMes.classList.remove("hide");
		}
		if(pass == "") {
			passMes.classList.remove("hide");
		}
		if(name == "") {
			nameMes.classList.remove("hide");
		}
		console.log("do not type value");
	} else if (pass !== cpass){
		e.preventDefault();
		console.log("confirm pass is incorrect");
		cpassMes.classList.remove("hide");
	} else {
		console.log("ok");
	}
})

emailIn.addEventListener('focus',function(){
	emailMes.classList.add("hide");
	serverMes.classList.add("hide");
})

passIn.addEventListener('focus',function(){
	passMes.classList.add("hide");
	cpassMes.classList.add("hide");
	serverMes.classList.add("hide");
})

cpassIn.addEventListener('focus',function(){
	cpassMes.classList.add("hide");
	serverMes.classList.add("hide");
})
nameIn.addEventListener('focus',function(){
	nameMes.classList.add("hide");
	serverMes.classList.add("hide");
})
