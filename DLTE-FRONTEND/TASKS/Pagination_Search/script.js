

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

const cardContainer = $('#cardContainer');
let currentPage = 1;
const cardsPerPage = 2;
let filteredPayees = [];

function createCard(payee) {
  const card = $('<div class="card col-md-4 mb-4 m-3"></div>');
  card.html(`
      <div class="card-body">
      <h5 class="card-title">${payee.payeeName}</h5>
      <p class="card-text">Payee ID: ${payee.id}</p>
      <p class="card-text">Sender Account Number: ${payee.senderAccountNumber}</p>
      <p class="card-text">Payee Account Number: ${payee.payeeAccountNumber}</p>
      <div class="text-center">
          <button class="btn btn-danger delete-btn">Delete</button>
      </div>
      </div>
  `);

  card.on('click', () => {
    if (selectedPayee === payee) {
      selectedPayee = null;
      card.classList.remove('payee-selected');
    } else {
      selectedPayee = payee;
      cardContainer.querySelectorAll('.payee-selected').forEach(card => {
        card.classList.remove('payee-selected');
      });
      card.classList.add('payee-selected');
    }
    displayPayeeDetails();
  });

  return card;
}

function filterPayees(query) {
  return payees.filter(payee => payee.payeeName.toLowerCase().includes(query.toLowerCase()));
}

$('#searchBox').on('input', () => {
  const query = $('#searchBox').val();
  filteredPayees = filterPayees(query);
  currentPage = 1;
  displayPayees();
  updatePagination();
});

function displayPayees() {
  const startIndex = (currentPage - 1) * cardsPerPage;
  const endIndex = startIndex + cardsPerPage;

  cardContainer.empty();
  filteredPayees.slice(startIndex, endIndex).forEach(payee => {
      cardContainer.append(createCard(payee));
  });
}

function updatePagination() {
  const totalPages = Math.ceil(filteredPayees.length / cardsPerPage);
  const pagination = $('#pagination');
  pagination.empty();

  for (let i = 1; i <= totalPages; i++) {
      const button = $(`<button class="btn btn-outline-primary mr-3">${i}</button>`);

      if (i === currentPage) {
          button.addClass('active');
      }

      button.on('click', () => {
          currentPage = i;
          displayPayees();
          updatePagination();
      });

      pagination.append(button);
  }
}

$('#accountNumber').on('input', () => {
  const accountNumber = $('#accountNumber').val();
  filteredPayees = payees.filter(payee => payee.senderAccountNumber === accountNumber);
  currentPage = 1;
  displayPayees();
  updatePagination();
});


displayPayees();
updatePagination();

