function sleep (time) {
    return new Promise((resolve) => setTimeout(resolve, time));
}
function showForm(formId, id){
    document.getElementById(formId).classList.remove("form-show-anim-reversed");
    document.getElementById(formId).style.display = 'flex';
    document.getElementById(formId).classList.add("form-show-anim");
    document.getElementById("darked").classList.add("darked");
    document.getElementById(`${formId}-id`).value=id;
    let datetime = document.getElementById(id+"datetime").innerText.split("\n");
console.log(datetime)
    document.getElementById(formId+"-name").value=document.getElementById(id+"name").textContent;
    document.getElementById(formId+"-priority").value=document.getElementById(id+"priority").textContent;
    document.getElementById(formId+"-date").value=datetime[0];
    document.getElementById(formId+"-time").value=datetime[1];

}
function hidForm(formId){
    document.getElementById(formId).classList.remove("form-show-anim");
    document.getElementById(formId).classList.add("form-show-anim-reversed");
    document.getElementById("darked").classList.remove("darked");
    sleep(500).then(()=>{
        document.getElementById(formId).style.display = 'none';
    })
}
