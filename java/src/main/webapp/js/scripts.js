function openModal(){
  $('#modal').addClass("modal-on");
  $('#content').addClass("blur");
}

function closeModal(){
  $('#modal').removeClass("modal-on");
  $('#content').removeClass("blur");
}
document.addEventListener('keyup', function(e) {
    if (e.keyCode == 27) {
        closeModal();
    }
});

function getToday(){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1;
	var yyyy = today.getFullYear();
	return today = yyyy+'-'+mm+'-'+dd;
}