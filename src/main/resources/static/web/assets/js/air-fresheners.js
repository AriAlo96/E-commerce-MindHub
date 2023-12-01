const app = Vue.createApp({
  data() {
    return {
      client:{},
      email: "",
      airFresheners: [],
      valueSearch: "",
      ranges: [
        { id: 'range1', label: 'US$0 - US$9', value: [0, 9] },
        { id: 'range2', label: 'US$10 - US$29', value: [10, 29] },
        { id: 'range3', label: 'US$30 or more', value: [30, Infinity] }
      ],
      rangeSelected: null,
      presentations: [
        { id: 'ambient', label: 'Ambient', value: 'ambient' },
        { id: 'fabrics', label: 'Fabrics', value: 'fabrics' },
        { id: 'diffusers', label: 'Diffusers', value: 'diffusers' }
      ],
      presentationSelected: null,
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
    axios.get("/velvet/flavorings")
      .then(response => {
        this.airFresheners = response.data;
        this.shoppingCart = JSON.parse(localStorage.getItem("shoppingCart")) || [];
        this.airFresheners = this.airFresheners.map(airFreshener => {
          let aux = this.shoppingCart.find(product => product.id == airFreshener.id)
          if (aux) {
            return aux
          }
          return airFreshener
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
    
      const filteredAirFresheners = this.airFresheners.filter(product =>
        product.name.toLowerCase().includes(normalizedSearch)
      );
    
      if (filteredAirFresheners.length === 0) {
        this.errorSearch = "The product was not found. Find another"
        return;
      }
      const firstResult = filteredAirFresheners[0];
      let detailsPage = '';
      const normalizedProductName = firstResult.name.toLowerCase();
      if (normalizedProductName.includes("air freshener")) {
        detailsPage = 'detailsAirFresheners.html';
    
      window.location.href = `/web/assets/pages/${detailsPage}?id=${encodeURIComponent(firstResult.id)}`;
    }
  },

    filterByPriceAndPresentation() {
      let priceSelected = this.ranges.find(range => range.value === this.rangeSelected);
      let presentationSelected = this.presentations.find(presentation => presentation.value === this.presentationSelected);
      this.filteredByPrice = this.airFresheners.filter(airFreshener => {
        return (!priceSelected || (airFreshener.price >= priceSelected.value[0] && airFreshener.price <= priceSelected.value[1])) && (!presentationSelected || airFreshener.presentation === presentationSelected.value)
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
      for (let product of this.airFresheners) {
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

