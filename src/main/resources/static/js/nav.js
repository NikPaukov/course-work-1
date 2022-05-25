'use strict'

let shown = false;

function dropDown() {
    if(!shown){
        document.getElementById('myDropdown').classList.add('show');
        shown=true;
    }
    else {
        document.getElementById('myDropdown').classList.remove('show');
        shown=false;
    }   
}

function getLink(skip=0){
    window.location.href = `tasks/${getDate(skip)}`;
}
function getMonthLink(yearId, selId){
    let select = document.getElementById(selId);
    let value = getMonth(select.options[select.selectedIndex].value);

    return `/tasks/${document.getElementById(yearId).value}-${value}`
}
function thisMonth(){
    let date = new Date();
    return `tasks/${date.getFullYear()}-${date.getMonth()+1>9?(date.getMonth()+1):"0"+(date.getMonth()+1)}`
}

function getMonth(month){
    switch (month){
        case `Січень`:
            return "01";
        case `Лютий`:
            return "02";
            break;
        case `Березень`:
            return "03";
        case `Квітень`:
            return "04";
        case `Травень`:
            return "05";
        case `Червень`:
            return "06";
        case `Липень`:
            return "07";
        case `Серпень`:
            return "08";
        case `Вересень`:
            return "09";
        case `Жовтень`:
            return "10";
        case `Листопад`:
            return "11";
        case `Грудень`:
            return "12";

    }
}
function getDate(skip=0){
    let today = new Date();
    let date = new Date(today.getFullYear(), today.getMonth(), today.getDate()+skip);
    let year = date.getFullYear();
    let month = date.getMonth();
    month = +month+1>9?+month+1:"0"+(month+1);
    let day = date.getDate()>9?date.getDate():"0"+date.getDate();

    return `${year}-${month}-${day}`;
}
