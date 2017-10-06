function getToday(){
	var d = new Date(),
	month = '' + (d.getMonth() + 1),
	day = '' + d.getDate(),
	year = d.getFullYear();	
	if (month.length < 2) month = '0' + month;
	if (day.length < 2) day = '0' + day;	
	return [year, month, day].join('-');
}

function getDateFormatToString(date){
	var input = new String(date);	    	
	var year = input.substr(11,4),
	    month = ['Jan','Feb','Mar','Apr','May','Jun',
	             'Jul','Aug','Sep','Oct','Nov','Dec'].indexOf(input.substr(4,3))+1,
	    day = input.substr(8,2);
	
	var d = year + '-' + (month<10?'0':'') + month + '-' + day;
	
	return d;
}

function getStringDate(date){
	month = '' + (date.getMonth() + 1),
	day = '' + date.getDate(),
	year = date.getFullYear();
	if (month.length < 2) month = '0' + month;
	if (day.length < 2) day = '0' + day;
	return [year, month, day].join('-');

}

function stringToDate(date){
	return new Date(date + " 00:00:00");
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
