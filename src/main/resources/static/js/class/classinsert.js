const contextPath = $('#contextPathHolder').attr('data-contextPath');

/* postcode */
function sample6_execDaumPostcode() {
    new daum.Postcode(
        {
            oncomplete: function (data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if (data.userSelectedType === 'R') {
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if (data.bname !== ''
                        && /[동|로|가]$/g.test(data.bname)) {
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if (data.buildingName !== ''
                        && data.apartment === 'Y') {
                        extraAddr += (extraAddr !== '' ? ', '
                            + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if (extraAddr !== '') {
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;

                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress")
                    .focus();
            }
        }).open();
}

/* 최초 실행 시 -> 세팅 */

// 1. 지역 세팅

(async (code, detailcode) => {

    await getLocalcate(code);
    
    setlocalone(detailcode);

})(2, 203);

function setlocalone(detailcode) {
    console.log(detailcode);
    $("#localselect").val(detailcode).prop("selected", true);
}

// 2. 클래스 종류 세팅

(async (code, detailcode) => {

    await getActivitycate(code);
    
    setactivityone(detailcode);

})(2, 208);

function setactivityone(detailcode) {
    console.log(detailcode);
    $("#actdetailselect").val(detailcode).prop("selected", true);
}


// 지역 카테고리 함수
async function getLocalcate(code) {
    //console.log(code);

    if (code == 1 || code == "") {
        $('#localselect').children('option').remove();
        $('#localselect').append('<option>전체</option>');
    }
    else {
        const url = contextPath + '/api/class/selectcatelist.json?refcode=' + code + '&chk=citycate';
        const headers = { "Content-Type": "application/json" };

        const { data } = await axios.get(url, { headers });

        $('#localselect').children('option').remove();

        $('#localselect').append('<option value="">세부 지역을 선택하세요</option>');

        var options = "";
        for (let obj of data.list) {
            //console.log(obj.code);
            if (obj.code % 100 != 1) {
                options += "<option value=" + obj.code + ">" + obj.localcate + "</option>";
            }
        }
        $('#localselect').append(options);
    }

}

function setLocalcate(code) {
    //console.log(code);
    code = document.getElementById("localselect").value;
}

// 종류 카테고리 함수
async function getActivitycate(code) {
    console.log(code);

    if (code == 1 || code == "") {
        $('#actdetailselect').children('option').remove();
        $('#actdetailselect').append('<option>전체</option>');
    }

    else {
        const url = contextPath + '/api/class/selectcatelist.json?refcode=' + code + '&chk=classcate';
        const headers = { "Content-Type": "application/json" };

        const { data } = await axios.get(url, { headers });

        $('#actdetailselect').children('option').remove();

        $('#actdetailselect').append('<option value="">세부 종류를 선택하세요</option>');

        var options = "";
        for (let obj of data.list) {
            //console.log(obj.code);
            if (obj.code % 100 != 1) {
                options += "<option value=" + obj.code + ">" + obj.actcate + "</option>";
            }
        }
        $('#actdetailselect').append(options);
    }
}

function setActcate(code) {
    //console.log(code);
    code = document.getElementById("actdetailselect").value;
}

/* 강사 소개 Quill */
var toolbarOptions = [['bold', 'italic', 'underline', 'strike'], // toggled buttons
['blockquote', 'code-block'],

[{
    'header': 1
}, {
    'header': 2
}], // custom button values
[{
    'list': 'ordered'
}, {
    'list': 'bullet'
}], [{
    'script': 'sub'
}, {
    'script': 'super'
}], // superscript/subscript
[{
    'indent': '-1'
}, {
    'indent': '+1'
}], // outdent/indent
[{
    'direction': 'rtl'
}], // text direction

[{
    'size': ['small', false, 'large', 'huge']
}], // custom dropdown
[{
    'header': [1, 2, 3, 4, 5, 6, false]
}],

[{
    'color': []
}, {
    'background': []
}], // dropdown with defaults from theme
[{
    'font': []
}], [{
    'align': []
}],

['clean'] // remove formatting button
];

// 위쪽에 있는 태그중에서 id가 editor인것을 찾아서 toolbar는 toolbarOptions의 값으로 대체하고 태마를 snow로 해서 변경
const quill1 = new Quill('#editor1', {
    modules: {
        toolbar: toolbarOptions
    },
    theme: 'snow'
});

/* 클래스 소개 Quill */
var toolbarOptions = [['bold', 'italic', 'underline', 'strike'], // toggled buttons
['blockquote', 'code-block'],

[{
    'header': 1
}, {
    'header': 2
}], // custom button values
[{
    'list': 'ordered'
}, {
    'list': 'bullet'
}], [{
    'script': 'sub'
}, {
    'script': 'super'
}], // superscript/subscript
[{
    'indent': '-1'
}, {
    'indent': '+1'
}], // outdent/indent
[{
    'direction': 'rtl'
}], // text direction

[{
    'size': ['small', false, 'large', 'huge']
}], // custom dropdown
[{
    'header': [1, 2, 3, 4, 5, 6, false]
}],

[{
    'color': []
}, {
    'background': []
}], // dropdown with defaults from theme
[{
    'font': []
}], [{
    'align': []
}],

['clean'] // remove formatting button
];

const quill2 = new Quill('#editor2', {
    modules: {
        toolbar: toolbarOptions
    },
    theme: 'snow'
});


/* 커리큘럼 Quill */
var toolbarOptions = [['bold', 'italic', 'underline', 'strike'], // toggled buttons
['blockquote', 'code-block'],

[{
    'header': 1
}, {
    'header': 2
}], // custom button values
[{
    'list': 'ordered'
}, {
    'list': 'bullet'
}], [{
    'script': 'sub'
}, {
    'script': 'super'
}], // superscript/subscript
[{
    'indent': '-1'
}, {
    'indent': '+1'
}], // outdent/indent
[{
    'direction': 'rtl'
}], // text direction

[{
    'size': ['small', false, 'large', 'huge']
}], // custom dropdown
[{
    'header': [1, 2, 3, 4, 5, 6, false]
}],

[{
    'color': []
}, {
    'background': []
}], // dropdown with defaults from theme
[{
    'font': []
}], [{
    'align': []
}],

['clean'] // remove formatting button
];

const quill3 = new Quill('#editor3', {
    modules: {
        toolbar: toolbarOptions
    },
    theme: 'snow'
});

/****** 이미지 함수 ******/

/*** 프로필 이미지  ***/

function clickItemImage() {
    document.getElementById("file").click();
}
function changeItemImage(e) {
    const img = document.getElementById("insert-img");
    console.log(e.files);
    if (e.files.length > 0) {

        img.src = URL.createObjectURL(e.files[0]);

        console.log(URL.createObjectURL(e.files[0]));
    }
    else {
        // 이미지 첨부 취소 시, 이미지 제거
        img.src = contextPath + '/image/default.png';
    }
}

function insertItemImage() {
    const file = document.getElementById("file").value;
    console.log(file);
    if (file === "") {
        alert("이미지 파일을 첨부하세요.");
        return false;
    }
    else {
        const form = document.getElementById("form");
        form.submit();
    }
}

/*** 클래스 메인 이미지  ***/

function clickClassMain() {
    document.getElementById("file_classMain").click();
}

function changeClassMain(e) {
    const img = document.getElementById("insert_classMain");

    console.log(e.files);

    if (e.files.length > 0) {

        img.src = URL.createObjectURL(e.files[0]);

        console.log(URL.createObjectURL(e.files[0]));
    }
    else {
        // 이미지 첨부 취소 시, 이미지 제거
        img.src = contextPath + "/image/default.png";
    }
}

/*** 클래스 서브 이미지  ***/

var append_files = [];

function changeClassSub(e) {

    var files = e.target.files;

    console.log(files);

    var filesArray = Array.prototype.slice.call(files);

    filesArray.forEach(function(f) {

        if(!f.type.match("image.*")) {
            alert("파일은 이미지 확장자만 가능합니다.");
            return;
        }
        
        console.log(append_files.length);
        console.log($("#file_classSub"));

        if(append_files.length+1 > 10) {
            alert("클래스 이미지는 10개까지 등록이 가능합니다.");
            return;
        }

        append_files.push(f);

        var reader = new FileReader();

        reader.onload = function(e) {

            var add_img = $('<img class="mx-1 my-2">');
            add_img.attr("src", e.target.result);
            add_img.css("width", "75px");
            add_img.css("height", "75px");
            

            $("#input-addimage").append(add_img);
        };
        reader.readAsDataURL(f);
    });
}


/* 세자리 단위 콤마입력 함수 */
function addCommas(x) {
    console.log(x);
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

$("#price:text").on("keyup", function () {
    $(this).val(addCommas($(this).val().replace(/[^0-9]/g, "")));
});

/* 페이지 이동 함수 */

// 페이지 이동 시, 사이드바 CSS 변경
$(".sidebar").click(function() {
    $(".sidebar").removeClass("active");
    $(this).addClass("active");
});

function pagecate() {

    $('#menu1').css('display', 'block');
    $('#menu2').css('display', 'none');
    $('#menu3').css('display', 'none');
    $('#menu4').css('display', 'none');
    $('#menu5').css('display', 'none');
}
function pageinstructor() {
    $('#menu1').css('display', 'none');
    $('#menu2').css('display', 'block');
    $('#menu3').css('display', 'none');
    $('#menu4').css('display', 'none');
    $('#menu5').css('display', 'none');
}
function pageintro() {
    $('#menu1').css('display', 'none');
    $('#menu2').css('display', 'none');
    $('#menu3').css('display', 'block');
    $('#menu4').css('display', 'none');
    $('#menu5').css('display', 'none');
}
function pagecorri() {
    $('#menu1').css('display', 'none');
    $('#menu2').css('display', 'none');
    $('#menu3').css('display', 'none');
    $('#menu4').css('display', 'block');
    $('#menu5').css('display', 'none');
}
function pageprice() {
    $('#menu1').css('display', 'none');
    $('#menu2').css('display', 'none');
    $('#menu3').css('display', 'none');
    $('#menu4').css('display', 'none');
    $('#menu5').css('display', 'block');
}

/* 다음, 이전 함수 */
function nextMenu1() {
    const cate1 = $('#cate1');
    const cate2 = $('#localselect');
    const cate3 = $('#cate3');
    const cate4 = $('#actdetailselect');
    const postcode = document.getElementById("sample6_postcode");
    const detailAddress = document.getElementById("sample6_detailAddress");

    //console.log(cate1.val());
    //console.log(cate2.val());

    if (cate1.val() <= 1) {
        alert('지역을 선택하세요');
        cate1.focus();
        return false; 	// 함수 종료
    }

    if (cate2.val() === "") {
        alert('세부 지역을 선택하세요');
        $('#cate2').focus();
        return false; 	// 함수 종료
    }

    if (cate3.val() <= 1) {
        alert('종류를 선택하세요');
        cate3.focus();
        return false; 	// 함수 종료
    }
    if (cate4.val() === "") {
        alert('세부 종류를 선택하세요');
        cate4.focus();
        return false; 	// 함수 종료
    }

    if (postcode.value.length <= 0) {
        alert('우편번호를 입력하세요.');
        postcode.focus();
        return false;
    }
    if (detailAddress.value.length <= 0) {
        alert('상세주소를 입력하세요.');
        detailAddress.focus();
        return false;
    }

    $('#menu1').css('display', 'none');
    $('#menu2').css('display', 'block');
    $('#menu3').css('display', 'none');
    $('#menu4').css('display', 'none');
    $('#menu5').css('display', 'none');

    $(".sidebar").removeClass("active");
    $("#sidebar2").addClass("active");

}

function prevMenu2() {
    $('#menu1').css('display', 'block');
    $('#menu2').css('display', 'none');
    $('#menu3').css('display', 'none');
    $('#menu4').css('display', 'none');
    $('#menu5').css('display', 'none');

    $(".sidebar").removeClass("active");
    $("#sidebar1").addClass("active");

}

function nextMenu2() {
    const file = document.getElementById("file");
    const nickname = document.getElementById("nickname");
    const content = quill1.root.innerHTML;
    const content_length = quill1.getLength();
    console.log(content_length);
    console.log(content);

    if (file.value === "") {
        alert("이미지 파일을 첨부하세요.");
        return false;
    }
    if (nickname.value === "") {
        alert("상호명을 작성하세요.");
        nickname.focus();
        return false;
    }
    if (content_length === 0) {
        alert("강사소개를 작성하세요.")
    }
    if (content_length < 30) {
        alert("강사소개를 30자 이상 작성하세요.");
        return false;
    }

    $('#menu1').css('display', 'none');
    $('#menu2').css('display', 'none');
    $('#menu3').css('display', 'block');
    $('#menu4').css('display', 'none');
    $('#menu5').css('display', 'none');

    $(".sidebar").removeClass("active");
    $("#sidebar3").addClass("active");

}

function prevMenu3() {

    $('#menu1').css('display', 'none');
    $('#menu2').css('display', 'block');
    $('#menu3').css('display', 'none');
    $('#menu3').css('display', 'none');
    $('#menu5').css('display', 'none');

    $(".sidebar").removeClass("active");
    $("#sidebar2").addClass("active");

}

function nextMenu3() {
    const title = document.getElementById("title");
    const content = quill2.root.innerHTML;
    const content_length2 = quill2.getLength();

    if (title.value === "") {
        alert("클래스 제목을 작성하세요.");
        title.focus();
        return false;
    }
    if (content_length2 === 0) {
        alert("클래스 소개를 작성하세요.");
        content.focus();
        return false;
    }
    if (content_length2 < 30) {
        alert("클래스 소개를 30자 이상 작성하세요.");
        content.focus();
        return false;
    }
    $('#menu1').css('display', 'none');
    $('#menu2').css('display', 'none');
    $('#menu3').css('display', 'none');
    $('#menu4').css('display', 'block');
    $('#menu5').css('display', 'none');

    $(".sidebar").removeClass("active");
    $("#sidebar4").addClass("active");

}

function prevMenu4() {
    $('#menu3').css('display', 'block');
    $('#menu4').css('display', 'none');

    $(".sidebar").removeClass("active");
    $("#sidebar3").addClass("active");

}

function nextMenu4() {
    const content = quill3.root.innerHTML;
    const content_length3 = quill3.getLength();

    if (content_length3 === 0) {
        alert("커리큘럼 내용을 작성하세요.");
        content.focus();
        return false;
    }
    if (content_length3 < 20) {
        alert("커리큘럼 내용을 20자 이상 작성하세요.");
        content.focus();
        return false;
    }
    $('#menu1').css('display', 'none');
    $('#menu2').css('display', 'none');
    $('#menu3').css('display', 'none');
    $('#menu4').css('display', 'none');
    $('#menu5').css('display', 'block');

    $(".sidebar").removeClass("active");
    $("#sidebar5").addClass("active");

}

function prevMenu5() {
    $('#menu4').css('display', 'block');
    $('#menu5').css('display', 'none');

    $(".sidebar").removeClass("active");
    $("#sidebar4").addClass("active");
}

function insertClass() {

    // 1. 아이디 선택자

    const cate1 = $('#cate1');
    const cate2 = $('#localselect');
    const cate3 = $('#cate3');
    const cate4 = $('#actdetailselect');
    const file = document.getElementById("file");
    const nickname = document.getElementById("nickname");
    const sns = document.getElementById("sns");
    const content_length = quill1.getLength();
    const content_length2 = quill2.getLength();
    const content_length3 = quill3.getLength();
    const title = document.getElementById("title");
    const postcode = document.getElementById("sample6_postcode");
    const address1 = document.getElementById("sample6_address");
    const address2 = document.getElementById("sample6_detailAddress");
    const address3 = document.getElementById("sample6_extraAddress");
    const price = document.getElementById("price");
    const localcode = document.getElementById("localselect");
    const actdetailcode = document.getElementById("actdetailselect");


    /* console.log(title.value);
    console.log(postcode.value);
    console.log(address1.value);
    console.log(address2.value);
    console.log(address3.value);
    console.log(price.value.replace(/[^\d]+/g, ''));
    console.log(localcode.value);
    console.log(actcode.value); */

    // 2. quill 내용

    const instructor = quill1.root.innerHTML;
    const intro = quill2.root.innerHTML;
    const curriculum = quill3.root.innerHTML;

    /* console.log(instructor);
    console.log(intro);
    console.log(curriculum); */

    // 3. 유효성 검사
    if (cate1.val() <= 1) {
        alert('지역을 선택하세요');
        pagecate();
        return false; 	// 함수 종료
    }

    if (cate2.val() === "") {
        alert('세부 지역을 선택하세요');
        pagecate();
        return false; 	// 함수 종료
    }

    if (cate3.val() <= 1) {
        alert('종류를 선택하세요');
        pagecate();
        return false; 	// 함수 종료
    }
    if (cate4.val() === "") {
        alert('세부 종류를 선택하세요');
        pagecate();
        return false; 	// 함수 종료
    }

    if (postcode.value.length <= 0) {
        alert('우편번호를 입력하세요.');
        pagecate();
        return false;
    }

    if (address2.value.length <= 0) {
        alert('상세주소를 입력하세요.');
        pagecate();
        return false;
    }

    if (file.value === "") {
        alert("이미지 파일을 첨부하세요.");
        pageinstructor();
        return false;
    }
    if (nickname.value === "") {
        alert("상호명을 작성하세요.");
        pageinstructor()
        return false;
    }
    if (content_length.value === 0) {
        alert("강사소개를 작성하세요.");
        pageinstructor()
        return false;
    }
    if (content_length.value < 30) {
        alert("강사소개를 30자 이상 작성하세요.");
        pageinstructor()
        return false;
    }
    if (title.value === "") {
        alert("클래스 제목을 작성하세요.");
        pageintro();
        return false;
    }
    if( content_length2 === 0) {
        alert("클래스 소개를 작성하세요.");
        pageintro();
        return false;
    } 
    if( content_length2 < 30) {
        alert("클래스 소개를 30자 이상 작성하세요.");
        pageintro();
        return false;
    }
    if( content_length3 === 0) {
        alert("커리큘럼 내용을 작성하세요.");
        pagecorri();
        return false;
    }
    if( content_length3 < 20) {
        alert("커리큘럼 내용을 20자 이상 작성하세요.");
        pagecorri();
        return false;
    }
    if (price.value.length <= 0) {
        alert("금액을 입력하세요.");
        price.focus();
        return false;
    }

    // 4. 동적 form 태그 생성

    var form = document.getElementById("form_insert");

    document.getElementById("form_title").value = title.value;
    document.getElementById("form_postcode").value = postcode.value;
    document.getElementById("form_address1").value = address1.value;
    document.getElementById("form_address2").value = address2.value;
    document.getElementById("form_address3").value = address3.value;
    document.getElementById("form_price").value = price.value.replace(/[^\d]+/g, '');
    document.getElementById("form_localcode").value = localcode.value;
    document.getElementById("form_actdetailcode").value = actdetailcode.value;
    document.getElementById("form_instructor").value = instructor;
    document.getElementById("form_intro").value = intro;
    document.getElementById("form_curriculum").value = curriculum;
    document.getElementById("form_nickname").value = nickname.value;
    document.getElementById("form_sns").value = sns.value;

    // var input1 = document.createElement("input");
    // input1.type = "text";
    // input1.name = "title";
    // input1.value = title.value;
    // form.appendChild(input1);

    // var input2 = document.createElement("input");
    // input2.type = "text";
    // input2.name = "postcode";
    // input2.value = postcode.value;
    // form.appendChild(input2);

    // var input3 = document.createElement("input");
    // input3.type = "text";
    // input3.name = "address1";
    // input3.value = address1.value;
    // form.appendChild(input3);

    // var input4 = document.createElement("input");
    // input4.type = "text";
    // input4.name = "address2";
    // input4.value = address2.value;
    // form.appendChild(input4);

    // var input5 = document.createElement("input");
    // input5.type = "text";
    // input5.name = "address3";
    // input5.value = address3.value;
    // form.appendChild(input5);

    // var input6 = document.createElement("input");
    // input6.type = "number";
    // input6.name = "price";
    // input6.value = price.value.replace(/[^\d]+/g, '');
    // form.appendChild(input6);

    // var input7 = document.createElement("input");
    // input7.type = "number";
    // input7.name = "localcode";
    // input7.value = localcode.value;
    // form.appendChild(input7);

    // var input8 = document.createElement("input");
    // input8.type = "number";
    // input8.name = "actdetailcode";
    // input8.value = actcode.value;
    // form.appendChild(input8);

    // var input9 = document.createElement("input");
    // input9.type = "text";
    // input9.name = "instructor";
    // input9.value = instructor;
    // form.appendChild(input9);

    // var input10 = document.createElement("input");
    // input10.type = "text";
    // input10.name = "intro";
    // input10.value = intro;
    // form.appendChild(input10);

    // var input11 = document.createElement("input");
    // input11.type = "text";
    // input11.name = "curriculum";
    // input11.value = curriculum;
    // form.appendChild(input11);

    // var input12 = document.createElement("input");
    // input12.type = "text";
    // input12.name = "nickname";
    // input12.value = nickname.value;
    // form.appendChild(input12);

    // 5. 전송

    form.submit();
}
