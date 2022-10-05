const listItem = document.querySelector(".navbar-items-list-nav");
const toggleItem =document.querySelector(".navbar-toggle-button");

const listUserItem = document.querySelector(".user-action-wrap");
const wellcomeItem =document.querySelector(".navbar-wellcome-message");

toggleItem.addEventListener('click',function(e){
	e.preventDefault();
	listItem.classList.toggle("hide");
})