function getTax (state)
{
    // var xhr = new XMLHttpRequest();
    var xhr = new XMLHttpRequest();
    if (window.XMLHttpRequest)
    {  // IE7+, Firefox, Chrome, Opera, Safari
        var xhr = new XMLHttpRequest();
    }
    else
    {  // IE5, IE6
        var xhr = new ActiveXObject ("Microsoft.XMLHTTP");
    }

    // Register the embedded handler function
    // This function will be called when the server returns
    // (the "callback" function)

    xhr.onreadystatechange = function ()
    { // 4 means finished, and 200 means okay.
        if (xhr.readyState == 4 && xhr.status == 200)
        { // Data should look like "Fairfax, Virginia"
            var result = xhr.responseText;
            // var place = result.split (', ');

            //if (document.getElementById ("city").value == "")
            // document.getElementById ("city").value = place[0];
            //if (document.getElementById ("state").value == "")
            // console.log(document.getElementById ("tax").innerHTML)

            // document.getElementById ("tax").innerHTML = result;
            if(result.length > 0) {
                var tax = parseFloat(result);
                var total = document.getElementById("item_price").innerHTML;

                document.getElementById("tax").innerHTML = (tax * 100).toString();


                // document.getElementById('total_price').innerHTML = result;
                total = parseFloat(total);
                document.getElementById("total_price").innerHTML = (total + (total * tax)).toFixed(2).toString();
            }
        }
    }
    // Call the response software component
    //xhr.open ("GET", "getCityState?zip=" + zip, true);
    //xhr.send ();

    xhr.open("POST", "TaxRef", true);
    xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    xhr.send("state="+state);
}