function getToday(){
	var d = new Date(),
	month = '' + (d.getMonth() + 1),
	day = '' + d.getDate(),
	year = d.getFullYear();
	if (month.length < 2) month = '0' + month;
	if (day.length < 2) day = '0' + day;
	return [year, month, day].join('-');
}

function openModal(){
	$('#modal').addClass("modal-on");
	$('#content').addClass("blur");
	$('#menu').addClass("blur");
	$('#focus').focus();
};

function closeModal(){
	$('#modal').removeClass("modal-on");
	$('#content').removeClass("blur");
	$('#menu').removeClass("blur");
};