function Formdata(data){
    /* если не заполнено поле Ваше имя, длина менее 3-x*/
    if (data.name != null && data.name.value.length < 3 )
    {
        alert('Заполните поле "Name"');
        return false;}

    /* если не заполнено поле Сообщение */
    if (data.surname != null && data.surname.value.length < 3)
    {
        alert('Заполните поле "Surname"' + data.dateFrom + "; " + data.dateTo);
        return false;}

    /* e-mail Юзера */
    if(data.dateFrom != null && data.dateFrom.value.length == 0)
    {
        alert('Заполните поле "dateFrom"');
        return false;}

    if(data.dateTo != null && data.dateTo.value.length == 0)
    {
        alert('Заполните поле "dateTo""');
        return false;}

    var date1 = new Date(data.dateFrom);
    var date2 = new Date(data.dateTo);
    if(date1.getDate() >= date2.getDate())
    {
        alert('"Check-in date" must be less than "Check-out date"' + dateFrom + "; " + dateTo);
        return false;}

    // if(!(/^w+[-_.]*w+@w+-?w+.[a-z]{2,4}$/.test(data.email.value)) )
    // {
    //     alert("Введите правильный E-Mail адрес");
    //     return false;}

}