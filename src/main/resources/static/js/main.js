function logoutAction() {
    $("#form_logout").submit();
}

getNotification();

async function getNotification() {

    const url = contextPath + '/api/notification/select.json';
    const headers = { "Content-Type": "application/json" };
    const { data } = await axios.get(url, { headers });

    // console.log("notification", data);

    if(data.status === 200) {

        const notification = document.querySelector("#notification_dropdown");

        const badge = document.querySelector("#notification_badge");

        if(data.count !== 0) {
            badge.textContent = data.count + '+';
        }

        for(let obj of data.list) {
            
            var link = document.createElement('a');

            link.className = "dropdown-item d-flex align-items-center";

            if(obj.chk === 1) {
                link.className = "dropdown-item d-flex align-items-center bg-light";
            }
            
            // link.setAttribute('href', contextPath + obj.url);

            link.addEventListener('click', function() {

                const url = contextPath + '/api/notification/updatechk.json';
                const headers = { "Content-Type": "application/json" };
                const body = {no: obj.no};
                const { data } = axios.put(url, body, { headers });

                window.location.href = contextPath + obj.url;

            })

            var div1 = document.createElement('div');
            div1.className = "me-1";

            var div2 = document.createElement('div');

            var img = document.createElement('img');
            img.src = contextPath + "/image/logo.svg";
            img.setAttribute('width', '50');
            img.setAttribute('height', '50');

            div2.appendChild(img);

            div1.appendChild(div2);

            var div3 = document.createElement('div');

            var div4 = document.createElement('div');
            div4.className = "small text-grey-500"
            div4.textContent = dateFormat(obj.regdate);

            var span1 = document.createElement('span');
            span1.className = "small fw-bold";
            span1.textContent = obj.content;

            div3.appendChild(div4);
            div3.appendChild(span1);

            link.appendChild(div1);
            link.appendChild(div3);

            notification.appendChild(link);
        }

    }

    else if(data.status === -1) {

        const notification = document.querySelector("#notification_dropdown");

        if (notification !== null) {

            var div = document.createElement('div');

            div.className = "dropdown-item text-center small text-gray-500";
            div.textContent = "알림이 없습니다.";
    
            notification.appendChild(div);

        }
    }
}

function dateFormat(d) {

    date = new Date(d);

    let month = date.getMonth() + 1;
    let day = date.getDate();
    let hour = date.getHours();
    let minute = date.getMinutes();
    let second = date.getSeconds();

    // month = month >= 10 ? month : '0' + month;
    // day = day >= 10 ? day : '0' + day;
    hour = hour >= 10 ? hour : '0' + hour;
    minute = minute >= 10 ? minute : '0' + minute;
    // second = second >= 10 ? second : '0' + second;

    return date.getFullYear() + '년 ' + month + '월 ' + day + '일 ' + hour + ':' + minute;
}

//  <a class="dropdown-item d-flex align-items-center" href="#">
//                                 <div class="mr-3">
//                                     <div class="icon-circle bg-primary">
//                                         <i class="fas fa-file-alt text-white"></i>
//                                     </div>
//                                 </div>
//                                 <div>
//                                     <div class="small text-gray-500">December 12, 2019</div>
//                                     <span class="font-weight-bold">A new monthly report is ready to download!</span>
//                                 </div>
//                             </a> 