
const payees = [
    {
      id: 1,
      senderAccountNumber: '1234567890',
      payeeAccountNumber: '0987654321',
      payeeName: 'Arundhathi'
    },
    {
      id: 2,
      senderAccountNumber: '0987654321',
      payeeAccountNumber: '1357913579',
      payeeName: 'Avinash'
    },
    {
      id: 3,
      senderAccountNumber: '1234567890',
      payeeAccountNumber: '2468101214',
      payeeName: 'Anupama'
    },
    {
      id: 4,
      senderAccountNumber: '1234567890',
      payeeAccountNumber: '2468101214',
      payeeName: 'Anupama'
    },
    {
      id: 5,
      senderAccountNumber: '1234567890',
      payeeAccountNumber: '2468101214',
      payeeName: 'Anupama'
    },
    {
      id: 6,
      senderAccountNumber: '1234567890',
      payeeAccountNumber: '2468101214',
      payeeName: 'Anupama'
    }
  ];
  
  const cardContainer = document.getElementById('cardContainer');
  let selectedPayee = null;
  
  function createCard(payee) {
    const card = document.createElement('div');
    card.classList.add('card', 'col-md-4', 'mb-4');
    card.innerHTML = `
      <div class="card-body">
        <h5 class="card-title">${payee.payeeName}</h5>
        <p class="card-text">Payee ID: ${payee.id}</p>
        <p class="card-text">Sender Account Number: ${payee.senderAccountNumber}</p>
        <p class="card-text">Payee Account Number: ${payee.payeeAccountNumber}</p>
        <div class="text-center">
        <button class="btn btn-danger delete-btn">Delete</button>
      </div>
      </div>
    `;
  
    card.addEventListener('click', () => {
    //   if (selectedPayee === payee) {
    //     selectedPayee = null;
    //     card.classList.remove('payee-selected');
    //   } else {
    //     selectedPayee = payee;
    //     cardContainer.querySelectorAll('.payee-selected').forEach(card => {
    //       card.classList.remove('payee-selected');
    //     });
    //     card.classList.add('payee-selected');
    //   }
    //   displayPayeeDetails();
    });
  
    return card;
  }
  
  function displayPayeeDetails() {
    // if (!selectedPayee) {
    //   cardContainer.innerHTML = '';
    //   return;
    // }
  
    // const card = createCard(selectedPayee);
    // cardContainer.innerHTML = '';
    // cardContainer.appendChild(card);
  }
  
  document.getElementById('accountNumber').addEventListener('input', () => {
    const accountNumber = document.getElementById('accountNumber').value;
    const filteredPayees = payees.filter(payee => payee.senderAccountNumber === accountNumber);
    cardContainer.innerHTML = '';
    filteredPayees.forEach(payee => {
      cardContainer.appendChild(createCard(payee));
    });
    selectedPayee = null; 
  });