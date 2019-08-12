$(document).ready(function () {
    $("#errorInp").hide();
    $('#booking-btn').click(function () {
        let url;
        if(location.href.includes("/hotel")){
            url = location.origin + "/hotel/underSky/booking" + window.location.search;
        } else {
            url = location.origin + "/underSky/booking" + window.location.search;
        }

        $.ajax({
            url: url,
            method: "post",
            data: {
                name : $('#name').val(),
                surname : $('#surname').val(),
                dateFrom : $('#dateFrom').val(),
                dateTo : $('#dateTo').val(),
                count : $('#count').val(),
                kids : $('#kids').val(),
                typeOfNumber : $('#typeOfNumber').val(),
                note : $('#note').val()
            },
            error: function(message) {
                $("#errorInp").fadeTo(7000, 100).slideUp(700, function(){
                    $("#errorInp").slideUp(500);
                });
                let sp1 = document.createElement("span");
                sp1.setAttribute("id", "osp");
                let sp1_content = document.createTextNode(message.responseText);
                sp1.appendChild(sp1_content);
                let sp2 = document.getElementById("osp");
                let parentDiv = sp2.parentNode;
                parentDiv.replaceChild(sp1, sp2);
            },
            success: function(data) {
                window.open(data, "_self");
            }
        });
    })
});