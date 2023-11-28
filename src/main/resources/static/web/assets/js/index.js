const app = Vue.createApp({
  data() {
    return {
      fragances: [],
      valueSearch: "",
      categorySelected: "",
      shoppingCart: [],
      totalPrice: 0
    };
  },
  created() {
    axios.get("")
      .then(response => {
        this.fragances = response.data;

      })
      .catch(error => {
        console.log(error);
      });

    this.shoppingCart = JSON.parse(localStorage.getItem("shoppingCart")) || [];
    JSON.stringify(this.shoppingCart);

    for (product of this.shoppingCart) {
      this.totalPrice += product.price;
    }
  },
  methods: {
    filterSearch() {
      this.fragances = this.fragances.filter(fragance =>
        fragance.name.toLowerCase().includes(this.valueSearch.toLowerCase())
      );
    },

    filterByOlfactoryFamily(family) {
      this.fragances = this.fragances.filter(fragance => fragance.olfactoryFamily === family);
    },
    addShoppingCart(product) {
      if (!this.shoppingCart.includes(product.id)) {
        this.shoppingCart.push(product);
        localStorage.setItem("shoppingCart", JSON.stringify(this.shoppingCart));
      }
      product.stock -= 1;
      this.totalPrice += product.price;
      product.stock;
    },
    removeFromCart(product) {
      let index = this.shoppingCart.findIndex(productCart => productCart.id == product.id)
      this.shoppingCart.splice(index, 1)

      localStorage.setItem("shoppingCart", JSON.stringify(this.shoppingCart));
      product.stock += 1
      this.totalPrice -= product.price
    },
  }
}
);

app.mount('#app');

