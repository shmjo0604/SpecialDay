/* 날짜선택 */
$.datepicker.setDefaults({
    dateFormat: 'yy-mm-dd',
    prevText: '이전 달',
    nextText: '다음 달',
    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
    showMonthAfterYear: true,
    yearSuffix: '년',
    minDate: "-0D",
    maxDate: "+2M"
});

$(function () {
$('.datepicker').datepicker({
	onSelect:function(d) {
		var week = new Array('일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일');
		var today = new Date(d).getDay();
		var todaylabel = week[today];
		document.getElementById("classday").value = todaylabel;
	}
});
});    