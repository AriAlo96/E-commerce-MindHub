const app = Vue.createApp({
  data() {
    return {
      client: {},
      email: "",
      creams: [],
      valueSearch: "",
      filtered: [],
      valueSearch: "",
      ranges: [
        { id: 'range1', label: 'US$0 - US$9', value: [0, 9] },
        { id: 'range2', label: 'US$10 - US$29', value: [10, 29] },
        { id: 'range3', label: 'US$30 or more', value: [30, Infinity] }
      ],
      rangeSelected: null,
      types: [
        { id: 'face', label: 'Face', value: 'face' },
        { id: 'body', label: 'Body', value: 'body' },
        { id: 'hands', label: 'Hands', value: 'hands' },
        { id: 'foots', label: 'Foots', value: 'foots' }
      ],
      typeSelected: null,
      shoppingCart: [],
      totalPrice: 0,
      errorSearch: ""
    };
  },
  created() {
    axios.get("/velvet/clients/current")
      .then(response => {
        this.client = response.data;
        this.email = this.client.email
      })
    axios.get("/velvet/creams")
      .then(response => {
        this.creams = response.data;
        this.shoppingCart = JSON.parse(localStorage.getItem("shoppingCart")) || [];
        this.creams = this.creams.map(cream => {
          let aux = this.shoppingCart.find(product => product.id == cream.id)
          if (aux) {
            return aux
          }
          return cream
        })
        for (let product of this.shoppingCart) {
          this.totalPrice += product.price * product.quantity;
        }
      })

      .catch(error => {
        console.log(error);
      });
  },
  methods: {

    filterSearch() {
      if (this.valueSearch.trim() === '') {
        return;
      }
   
      const normalizedSearch = this.valueSearch.toLowerCase();
    
      const filteredCreams = this.creams.filter(product =>
        product.name.toLowerCase().includes(normalizedSearch)
      );
    
      if (filteredCreams.length === 0) {
        this.errorSearch = "The product was not found. Find another"
        return;
      }
      const firstResult = filteredCreams[0];
      let detailsPage = '';
      const normalizedProductName = firstResult.name.toLowerCase();
      if (normalizedProductName.includes("cream")) {
        detailsPage = 'detailsCreams.html';
    
      window.location.href = `/web/assets/pages/${detailsPage}?id=${encodeURIComponent(firstResult.id)}`;
    }
  },

    filterByPriceAndType() {
      let priceSelected = this.ranges.find(range => range.value === this.rangeSelected);
      let typeSelected = this.types.find(type => type.value === this.typeSelected);
      this.filteredByPrice = this.airFresheners.filter(cream => {
        return (!priceSelected || (cream.price >= priceSelected.value[0] && cream.price <= priceSelected.value[1])) && (!typeSelected || cream.type === typeSelected.value)
      });
    },

    addFromCart(product) {
      const index = this.shoppingCart.findIndex(productCart => productCart.id === product.id);
      if (index !== -1) {
        this.shoppingCart[index].quantity += 1;
      } else {
        product.quantity = 1
        this.shoppingCart.push(product);
      }
      this.updateTotalPrice();
      product.stock -= 1;
      localStorage.setItem("shoppingCart", JSON.stringify(this.shoppingCart));

    },

    removeFromCart(product) {
      let index = this.shoppingCart.findIndex(productCart => productCart.id == product.id)
      this.shoppingCart.splice(index, 1)

      localStorage.setItem("shoppingCart", JSON.stringify(this.shoppingCart));
      this.updateTotalPrice();
      product.stock += product.quantity
    },

    updateStockFromCart(cart) {
      for (let product of this.creams) {
        const cartProduct = cart.find(cartItem => cartItem.id === product.id);
        if (cartProduct) {
          product.stock -= cartProduct.quantity;
        }
      }
    },
    decrementQuantity(item) {
      if (item.quantity > 1) {
        item.quantity -= 1;
        item.stock += 1
        this.updateTotalPrice();
        localStorage.setItem("shoppingCart", JSON.stringify(this.shoppingCart));
      }
    },
    updateTotalPrice() {
      this.totalPrice = this.shoppingCart.reduce((total, item) => total + (item.price * item.quantity), 0);
    },


    logOut() {
      Swal.fire({
        title: 'Are you sure you want to log out?',
        text: 'You will need to log in again to browse again',
        showCancelButton: true,
        cancelButtonText: 'Cancell',
        confirmButtonText: 'Log Out',
        confirmButtonColor: '#28a745',
        cancelButtonColor: '#dc3545',
        showClass: {
          popup: 'swal2-noanimation',
          backdrop: 'swal2-noanimation'
        },
        hideClass: {
          popup: '',
          backdrop: ''
        }, preConfirm: () => {
          axios.post(`/velvet/logout`)
            .then(response => {
              Swal.fire({
                position: "center",
                icon: "success",
                title: "Logged out successfully",
                showConfirmButton: false,
                timer: 1500,
            }),
                setTimeout(() => {
                    location.pathname = "/index.html";
                }, 1600);
            })
            .catch(error => {
              console.log(error);
            });
        },
      })
    },
    formatNumber(number) {
      return number.toLocaleString("De-DE", {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2,
      });
    },
  }
}
);

app.mount('#app');

