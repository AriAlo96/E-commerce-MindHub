const app = Vue.createApp({
    data() {
        return {
            airFresheners: [],
            airFreshener: [],
            shoppingCart: [],
            totalPrice: 0,
        };
    },

    created() {
        axios.get("/velvet/flavorings")
        .then(response => {
          this.fragances = response.data;
        })

        let urlParams = new URLSearchParams(location.search);
        let id = urlParams.get('id')     
        axios.get(`/velvet/flavorings/${id}`)
            .then(response => {
                this.airFreshener = response.data;
                this.shoppingCart = JSON.parse(localStorage.getItem("shoppingCart")) || [];
                for (let product of this.shoppingCart) {
                    this.totalPrice += product.price * product.quantity;
                }
            })
            .catch(error => {
                this.messageError = error.response.data;
            });
    },

    methods: {
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
      

        logout() {
            axios
                .post(`/api/logout`)
                .then(response => {
                    console.log("SingOut");
                    location.pathname = `/index.html`;
                })
                .catch(error => {
                    console.log(error);
                });
        },

        formatNumber(number) {
            return number.toLocaleString("De-DE", {
                minimumFractionDigits: 2,
                maximumFractionDigits: 2,
            });
        },
        
}   

},
);
app.mount('#app');