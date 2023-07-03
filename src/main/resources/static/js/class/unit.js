
/******************************  session_write.jsp *************************************/

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
		document.getElementById("day").value = todaylabel;
	}
});
});    

/* radio값(난이도)값 입력 안한 초기값 */
var valueCheck1 = $('.radio-value:checked').val();
	if(valueCheck1 == 1) {
		$('.level-value-detail').val('1'); // level
		$('.radio-value-detail').val('0'); // 추가금액 초기화
		$('.rate-value-detail').val('0'); // 할인률 초기화
		
		$('.sale-value-detail').val(''); // 판매금액 초기화
	}

/* radio값(난이도)에 따라 추가금액입력 활성화여부결정 */
$('.radio-value').on('click', function(){
	var valueCheck = $('.radio-value:checked').val();
	if(valueCheck == 1) {
		$('.level-value-detail').val('1'); // level
		$('.radio-value-detail').val('0'); // 추가금액 초기화
		$('.rate-value-detail').val('100'); // 할인률 초기화
		
		$('.sale-value-detail').val(''); // 판매금액 초기화
		//$('.radio-value-detail').attr('disabled', true);
	}
	if(valueCheck == 2) {
		$('.radio-value-detail').attr('readonly', false);
		$('.radio-value-detail').focus();
	}
	if(valueCheck == 3) {
		$('.radio-value-detail').attr('readonly', false);
		$('.radio-value-detail').focus();
	}
});



/* 계산하기 */
function showPrice() {
var originPrice = document.getElementById("default").value;
var addPrice = document.getElementById("addprice").value;
var rate = document.getElementById("rate").value;
var discountPrice = (parseInt(originPrice) + parseInt(addPrice)) * (rate/100);
var totalPrice = parseInt(originPrice) - parseInt(discountPrice) + parseInt(addPrice);

document.getElementById("total").value = totalPrice;
}

/* 등록버튼 활성화 체크 */
$('.min').on('input', checkInput);
$('.max').on('input', checkInput);
$('.date').on('input', checkInput);
$('.start').on('input', checkInput);
$('.end').on('input', checkInput);

function checkInput() {
	var min = document.getElementById("min").value;
	var max = document.getElementById("max").value;
	var date = document.getElementById("date").value;
	var start = document.getElementById("start").value;
	var end = document.getElementById("end").value;
	
	if (min === '' || max === '' || date === ''||  start === '' || end === '') {
		$('.register-btn').attr('disabled', true);
	}
	else {
		$('.register-btn').attr('disabled', false);
	}
}


/* 일정 등록하기 */
function registerSession() {
			const min = document.getElementById("min");
			const max = document.getElementById("max");
			const date = document.getElementById("date");
			const start = document.getElementById("start");
			const end = document.getElementById("end");
			
			/* 유효성 검사 */
			if(min.value <= 0) {
				alert('최소인원을 입력하세요.');
				min.focus();
				return false; 
			}
			if(max.value <= 0) {
				alert('최대인원을 입력하세요.');
				max.focus();
				return false; 
			}
			if(parseInt(min.value) > parseInt(max.value)) {
				alert('최대인원을 잘못입력하셨습니다.');
				max.focus();
				return false; 
			}
			if(date.value.length <= 0) {
				alert('날짜를 입력하세요.');
				date.focus();
				return false; 
			}
			if(start.value.length <= 0) {
				alert('시작시각을 입력하세요.');
				start.focus();
				return false; 
			}
			if(end.value.length <= 0) {
				alert('종료시각을 입력하세요.');
				end.focus();
				return false; 
			}
			if(start.value > end.value) {
				alert('종료시각을 잘못입력하셨습니다.');
				end.focus();
				return false; 
			}
			
    		const form = document.getElementsByTagName("form");
    		form[0].submit();
    	}


/******************************  session_update.jsp *************************************/

/* 일정 변경하기 */
function updateSession() {
			const min = document.getElementById("min");
			const max = document.getElementById("max");
			const date = document.getElementById("date");
			const start = document.getElementById("start");
			const end = document.getElementById("end");
			
			/* 유효성 검사 */
			if(min.value <= 0) {
				alert('최소인원을 입력하세요.');
				min.focus();
				return false; 
			}
			if(max.value <= 0) {
				alert('최대인원을 입력하세요.');
				max.focus();
				return false; 
			}
			if(parseInt(min.value) > parseInt(max.value)) {
				alert('최대인원을 잘못입력하셨습니다.');
				max.focus();
				return false; 
			}
			if(date.value.length <= 0) {
				alert('날짜를 입력하세요.');
				date.focus();
				return false; 
			}
			if(start.value.length <= 0) {
				alert('시작시각을 입력하세요.');
				start.focus();
				return false; 
			}
			if(end.value.length <= 0) {
				alert('종료시각을 입력하세요.');
				end.focus();
				return false; 
			}
			if(start.value > end.value) {
				alert('종료시각을 잘못입력하셨습니다.');
				end.focus();
				return false; 
			}
			
    		const form = document.getElementsByTagName("form");
    		form[0].submit();
    	}


/******************************  session_workspace.jsp > menu1 *************************************/


