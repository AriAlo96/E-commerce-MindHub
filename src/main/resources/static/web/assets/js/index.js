const app = Vue.createApp({
  data() {
    return {
      allProducts:{
      fragances: [],
      airFresheners: [],
      creams: []
    },
      valueSearch: "",
      categorySelected: "",
      shoppingCart: [],
      totalPrice: 0,
      errorSearch: ""
    };
  },
  created() {
    axios.get("/velvet/fragances")
      .then(response => {
        this.allProducts.fragances = response.data;
        this.shoppingCart = JSON.parse(localStorage.getItem("shoppingCart")) || [];
        for (let product of this.shoppingCart) {
          this.totalPrice += product.price * product.quantity;
        }
      })
      axios.get("/velvet/flavorings")
      .then(response => {
        this.allProducts.airFresheners = response.data;
      })
      axios.get("/velvet/creams")
      .then(response => {
        this.allProducts.creams = response.data;
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
      const allProductsArray = this.allProducts.fragances
        .concat(this.allProducts.airFresheners)
        .concat(this.allProducts.creams);
    
      const normalizedSearch = this.valueSearch.toLowerCase();
    
      const filteredProducts = allProductsArray.filter(product =>
        product.name.toLowerCase().includes(normalizedSearch)
      );
    
      if (filteredProducts.length === 0) {
        this.errorSearch = "The product was not found. Find another"
        return;
      }
    
      const firstResult = filteredProducts[0];
      let detailsPage = '';
      const normalizedProductName = firstResult.name.toLowerCase();
      if (normalizedProductName.includes("parfum")) {
        detailsPage = 'detailsFragances.html';
      } else if (normalizedProductName.includes("freshener") || normalizedProductName.includes("diffuser") || normalizedProductName.includes("fabric")) {
        detailsPage = 'detailsAirFresheners.html';
      } else if (normalizedProductName.includes("cream")) {
        detailsPage = 'detailsCreams.html';
      }
    
      window.location.href = `/web/assets/pages/${detailsPage}?id=${encodeURIComponent(firstResult.id)}`;
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

