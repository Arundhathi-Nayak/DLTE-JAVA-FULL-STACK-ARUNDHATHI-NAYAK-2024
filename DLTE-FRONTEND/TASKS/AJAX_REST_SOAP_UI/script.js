



$(document).ready(() => {

  function createCard(payee) {
    const card = $('<div>').addClass('card col-md-4 mb-4');
    const cardBody = $('<div>').addClass('card-body').appendTo(card);
    $('<h5>').addClass('card-title').text(payee.payeeName).appendTo(cardBody);
    $('<p>').addClass('card-text').text('Payee ID: ' + payee.id).appendTo(cardBody);
    $('<p>').addClass('card-text').text('Sender Account Number: ' + payee.senderAccountNumber).appendTo(cardBody);
    $('<p>').addClass('card-text').text('Payee Account Number: ' + payee.payeeAccountNumber).appendTo(cardBody);
    $('<div>').addClass('text-center').append(
      $('<button>').addClass('btn btn-danger delete-btn').text('Delete')
    ).appendTo(cardBody);

    card.on('click', function () {
      selectedPayee = payee;
      displayPayeeDetails();
    });

    return card;
  }

  $("#searchButton").click(() => {
    const senderAccountNumber = $("#accountNumber").val();

    var soapRequest = `<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:pay="http://payee.services">
      <soapenv:Header/>
      <soapenv:Body>
        <pay:findAllPayeeBasedOnAccountNumberLambdaRequest>
          <pay:senderAccount>${senderAccountNumber}</pay:senderAccount>
        </pay:findAllPayeeBasedOnAccountNumberLambdaRequest>
      </soapenv:Body>
    </soapenv:Envelope>`

    $.ajax({
      url: "http://localhost:8082/payeerepo",
      type: "POST",
      dataType: "xml",
      beforeSend: function (handler) {
        handler.setRequestHeader("Authorization", "Basic " + btoa('aashi:aashi'))
        handler.setRequestHeader("SOAPAction", "findAllPayeeBasedOnAccountNumberLambdaRequest")
      },
      contentType: "text/xml;charset=utf-8",
      data: soapRequest,
      success: function (response) {
        alert("Search successful.");
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
        alert("An error occurred while searching.");
        let element = $("#status")
        element.empty()
        const info = $(err.responseXML).find("ns2\\:serviceStatus").find("ns2\\:status").text()
        element.append(`<h1>${info}</h1>`)
      }
    })
  })

})
