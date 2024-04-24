
$(document).ready(() => {

  function createCard(payee) {
    const card = $('<div>').addClass('card col-md-4 mb-4');
    const cardBody = $('<div>').addClass('card-body').appendTo(card);
    $('<h5>').addClass('card-title').text(payee.payeeName).appendTo(cardBody);
    $('<p>').addClass('card-text').text('Payee ID: ' + payee.id).appendTo(cardBody);
    $('<p>').addClass('card-text').text('Sender Account Number: ' + payee.senderAccountNumber).appendTo(cardBody);
    $('<p>').addClass('card-text').text('Payee Account Number: ' + payee.payeeAccountNumber).appendTo(cardBody);
    $('<div>').addClass('text-center').append(
      $('<button>').addClass('btn btn-danger delete-btn').text('Delete').attr('data-payee', JSON.stringify(payee))
    ).appendTo(cardBody);

    card.on('click', function () {
      selectedPayee = payee;
     // displayPayeeDetails();
    });

    return card;
  }

  $("#searchButton").click(() => {
    const senderAccountNumber = $("#accountNumber").val();

    var soapRequest = `<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:pay="http://payee.services">
    <soapenv:Header/>
    <soapenv:Body>
       <pay:findAllPayeeBasedOnAccountNumberRequest>
          <pay:senderAccount>${senderAccountNumber}</pay:senderAccount>
       </pay:findAllPayeeBasedOnAccountNumberRequest>
    </soapenv:Body>
 </soapenv:Envelope>`

    $.ajax({
      url: "http://localhost:8082/payeerepo",
      type: "POST",
      dataType: "xml",
      beforeSend: function (handler) {
        handler.setRequestHeader("Authorization", "Basic " + sessionStorage.getItem("logged"))
        handler.setRequestHeader("SOAPAction", "findAllPayeeBasedOnAccountNumberRequest")
      },
      contentType: "text/xml;charset=utf-8",
      data: soapRequest,
      success: function (response) {
        // $('#status').empty().html('<div class="alert alert-success" role="alert">Search successful.</div>');
        let payees = [];
        $(response).find("ns2\\:payee").each(function () {
          let payee = {
            id: $(this).find("ns2\\:payeeId").text(),
            senderAccountNumber: $(this).find("ns2\\:senderAccountNumber").text(),
            payeeAccountNumber: $(this).find("ns2\\:payeeAccountNumber").text(),
            payeeName: $(this).find("ns2\\:payeeName").text()
          };
          payees.push(payee);
        });

        $('#cardContainer').empty();
        payees.forEach(payee => {
          $('#cardContainer').append(createCard(payee));
        });
      },
      error: function (err) {
        let element = $("#status")
        element.empty()
        const info = $(err.responseXML).find("ns2\\:serviceStatus").find("ns2\\:status").text()
        element.append(`<h1>${info}</h1>`)
      }
    })
  })


  $(document).on('click', '.delete-btn', function () {
    const payee = $(this).data('payee');
    const request = {
      "payeeId": payee.id,
      "senderAccountNumber": payee.senderAccountNumber,
      "payeeAccountNumber" : payee.payeeAccountNumber,
      "payeeName": payee.payeeName
    };
  
    $.ajax({
      url:"http://localhost:8082/payees/delete",
      type:"DELETE",
      beforeSend:function(handler){
          handler.setRequestHeader("Authorization","Basic "+sessionStorage.getItem("logged"))
      },
      contentType:"application/json;charset=utf-8",
      data:JSON.stringify(request),
      success:function(response){
        alert(JSON.stringify(response));
        location.reload();
        location.assign("http://127.0.0.1:5500/main.html")
      },
      error:function(error){
        let element = $("#status")
        element.append(`<h1>${error.status}</h1>`)
      }
    })
  })
  


})
