const app = Vue.createApp({
    data() {
        return {
            fragances: [],
            firstName: "",
            lastName: "",
            address: "",
            email: "",
            password: "",
            exitMessage: "",
            errorStatus: null,
            errorRegister: "",
            shoppingCart: [],
            totalPrice: 0,
        };
    },

    created() {
        axios.get("/velvet/fragances")
            .then(response => {
                this.fragances = response.data;
                this.shoppingCart = JSON.parse(localStorage.getItem("shoppingCart")) || [];
                for (let product of this.shoppingCart) {
                    this.totalPrice += product.price * product.quantity;
                }
            })
    },

    methods: {
        register() {
            axios.post('/velvet/clients/register', `firstName=${this.firstName}&lastName=${this.lastName}&email=${this.email}&password=${this.password}&address=${this.address}`)
                .then(() => {
                    axios.post('/velvet/login', `email=${this.email}&password=${this.password}`)
                        .then(() => location.pathname = `index.html`)
                        .catch(error => {
                            console.log(error);
                        });
                })
                .catch(error => {
                    let errorMessage = error.response.data;
                    errorMessage = errorMessage.replace(/\n/g, '<br>');
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        html: errorMessage,
                    });
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

