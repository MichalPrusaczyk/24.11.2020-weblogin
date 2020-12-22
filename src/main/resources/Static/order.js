function showOrderPositions(orderId) {
    var orderElementId = "order"+orderId;
    var order = document.getElementById(orderElementId);

    for (var i = 0; i < order.childNodes.length; i++) {
        if (order.childNodes[i].className == "position") {
          order.childNodes[i].style.display = "block";
          order.setAttribute('onclick',  'hideOrderPositions('+orderId+');');
        }
    }
}

function hideOrderPositions(orderId) {
    var orderElementId = "order"+orderId;
    var order = document.getElementById(orderElementId);

    for (var i = 0; i < order.childNodes.length; i++) {
        if (order.childNodes[i].className == "position") {
           order.childNodes[i].style.display = "none";
           order.setAttribute('onclick',  'showOrderPositions('+orderId+');');
        }
    }
}