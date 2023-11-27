const app = Vue.createApp({
    data() {
      return {
        fragances: [],
        valueSearch:"",
        categorySelected: "",
        shoppingCart: []
      };
    },
    created(){
      axios.get("")
      .then(response => {
          this.fragances = response.data;
          
      })
      .catch(error => {
          console.log(error);
      });

      this.shoppingCart = JSON.parse(localStorage.getItem("shoppingCart")) || [];
      JSON.stringify(this.shoppingCart);
    },
    methods: {
      filterSearch() {
        this.fragances.filter(fragance =>
          fragance.name.toLowerCase().includes(this.valueSearch.toLowerCase())
        );
      },

      filterByOlfactoryFamily(family) {
        this.fragances.filter(fragance => fragance.olfactoryFamily === family);
    },
      addShoppingCart(product){
        if (!this.shoppingCart.includes(product._id)) {
          this.shoppingCart.push(product);
          localStorage.setItem("carrit", JSON.stringify(this.carrit));
        }
        product.stock -= 1;
        this.total += product.price;
        product.stock;
    }
  }
    }
  );
  
  app.mount('#app');

  