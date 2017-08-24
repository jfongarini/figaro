function getToday(){
	return new Date().toJSON().slice(0,10);
}

openModal = function(event){
	$('#modal').addClass("modal-on");
	$('#content').addClass("blur");
};

closeModal = function(event){
	$('#modal').removeClass("modal-on");
	$('#content').removeClass("blur");
};