function notify_tick(movie, day, date, time, place){
    document.getElementById("mov").innerText = movie.toString();
    document.getElementById("day").innerText = day.toString();
    document.getElementById("date").innerText = (date.getMonth() + 1) + "-" + date.getDate() + "-" + date.getFullYear();
       // document.getElementById("date").innerText = (date.getDate() + "-" + date.getMonth() + 1)  + "-" + date.getFullYear();
    document.getElementById("time").innerText = time;
    document.getElementById("place").innerText = place;
    appearDivById("order-form");
}

function notify_info_user(id, name, surname, people, children, type, note, dateFrom, dateTo, dateOfBooking, status) {
    document.getElementById("app_id").innerText = id;
    document.getElementById("app_name").innerText = name;
    document.getElementById("app_surname").innerText = surname;
    document.getElementById("app_people").innerText = people;
    document.getElementById("app_children").innerText = children;
    document.getElementById("app_type").innerText = type;
    document.getElementById("app_note").innerText = note;
    document.getElementById("app_dateFrom").innerText = dateFrom;
    document.getElementById("app_dateTo").innerText = dateTo;
    document.getElementById("app_dateOfBooking").innerText = dateOfBooking;
    document.getElementById("app_status").innerText = status;
}
function notify_info_admin(id, userId, name, surname, people, children, type, note, dateFrom, dateTo, dateOfBooking, status) {
    document.getElementById("app_id").innerText = id;
    document.getElementById("app_user_id").innerText = userId;
    document.getElementById("app_name").innerText = name;
    document.getElementById("app_surname").innerText = surname;
    document.getElementById("app_people").innerText = people;
    document.getElementById("app_children").innerText = children;
    document.getElementById("app_type").innerText = type;
    document.getElementById("app_note").innerText = note;
    document.getElementById("app_dateFrom").innerText = dateFrom;
    document.getElementById("app_dateTo").innerText = dateTo;
    document.getElementById("app_dateOfBooking").innerText = dateOfBooking;
    document.getElementById("app_status").innerText = status;
}
function notify_info_room(id, userId) {
    document.getElementById("book_id").innerText = id;
    document.getElementById("book_id_user").innerText = userId;
}
function notify_info_user_booked_room(id, type, dateFrom, dateTo) {
        document.getElementById("room_id_app").innerText = id;
        document.getElementById("room_type").innerText = type;
        document.getElementById("room_date_from").innerText = dateFrom;
        document.getElementById("room_date_to").innerText = dateTo;
}

function notify_info_user_booked_room_refuse(id, type, dateFrom, dateTo, note) {
    document.getElementById("room_id_app_refuse").innerText = id;
    document.getElementById("room_type_refuse").innerText = type;
    document.getElementById("room_date_from_refuse").innerText = dateFrom;
    document.getElementById("room_date_to_refuse").innerText = dateTo;
    document.getElementById("room_note_refuse").innerText = note;
}

function notify_del(msg, holderId) {
    document.getElementById(holderId.toString()).innerText = msg;
}

function cls_span(id) {
    $('#' + id.toString()).slideUp(200);
}

function appearDivById(id) {
    if(document.getElementById(id).style.display === 'none'){
        $('#' + id.toString()).fadeTo(800, 30);
    }
}

function notify_cancel(id) {
    document.getElementById("reservation_id").innerText = id;
}
function notify_cancel_application(id) {
    document.getElementById("application_id").innerText = id;
}

function viewDiv(id){
    var e = document.getElementById(id);
    if(e.style.display === 'block')
        e.style.display = 'none';
    else
        e.style.display = 'block';
};