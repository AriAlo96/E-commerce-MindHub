const app = Vue.createApp({
    data() {
      return {
        currentForm: 'form1',
        nameFragance:"",
        descriptionFragance: "",
        genderFragance: "",
        olfactoryFamilyFragance: "",
        priceFragance: 0,
        presentationFragance: "",
        contentFragance: 0,
        stockFragance: 0,
        routeImageFragance: "",

        nameAirFreshener:"",
        descriptionAirFreshener: "",
        contentAirFreshener: 0,
        priceAirFreshener: 0,
        stockAirFreshener: 0,
        presentationAirFreshener: "",
        routeImageAirFreshener: "",

        nameCream:"",
        descriptionCream: "",
        priceCream: 0,
        contentCream: 0,   
        stockCream: 0,
        typeCream: "",
        routeImageCream: "",
        

      };
    },
       

    methods: {
        createNewFragance() {
            Swal.fire({
                title: 'Do you want to create a new fragance?',
                showCancelButton: true,
                cancelButtonText: 'Cancell',
                confirmButtonText: 'Confirm',
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
            axios.post("/velvet/fragances/create",`name=${this.nameFragance}&description=${this.descriptionFragance}&gender=${this.genderFragance}&olfactoryFamily=${this.olfactoryFamilyFragance}&price=${this.priceFragance}&presentation=${this.presentationFragance}&content=${this.contentFragance}&stock=${this.stockFragance}&image=${this.routeImageFragance}`)
                .then(() => {
                    Swal.fire({
                        title: "Successfully created fragance",
                        icon: "success",
                        confirmButtonColor: "#3085d6",
                      }).then((result) => {
                        if (result.isConfirmed) {
                            location.pathname = `/administrator/assets/pages/create-products.html`;
                        }
                      });   
                    
                })
                .catch(error => {
                    Swal.fire({
                      icon: 'error',
                      text: error.response.data,
                      confirmButtonColor: "#7c601893",
                    });
            });
            },
        })
    },

    createNewAirFreshener() {
      Swal.fire({
          title: 'Do you want to create a new air Freshener?',
          showCancelButton: true,
          cancelButtonText: 'Cancell',
          confirmButtonText: 'Confirm',
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
      axios.post(`/velvet/flavorings/create`,`name=${this.nameAirFreshener}&description=${this.descriptionAirFreshener}&content=${this.contentAirFreshener}&price=${this.priceAirFreshener}&stock=${this.stockAirFreshener}&presentation=${this.presentationAirFreshener}&image=${this.routeImageAirFreshener}`)
          .then(() => {
              Swal.fire({
                  title: "Successfully created air freshener",
                  icon: "success",
                  confirmButtonColor: "#3085d6",
                }).then((result) => {
                  if (result.isConfirmed) {
                      location.pathname = `/administrator/assets/pages/create-products.html`;
                  }
                });   
              
          })
          .catch(error => {
              Swal.fire({
                icon: 'error',
                text: error.response.data,
                confirmButtonColor: "#7c601893",
              });
      });
      },
  })
},

createNewCream() {
  Swal.fire({
      title: 'Do you want to create a new cream?',
      showCancelButton: true,
      cancelButtonText: 'Cancell',
      confirmButtonText: 'Confirm',
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
  axios.post(`/velvet/creams/create`,`name=${this.nameCream}&description=${this.descriptionCream}&price=${this.priceCream}&content=${this.contentCream}&stock=${this.stockCream}&type=${this.typeCream}&image=${this.routeImageCream}`)
      .then(() => {
          Swal.fire({
              title: "Successfully created cream",
              icon: "success",
              confirmButtonColor: "#3085d6",
            }).then((result) => {
              if (result.isConfirmed) {
                  location.pathname = `/administrator/assets/pages/create-products.html`;
              }
            });   
          
      })
      .catch(error => {
          Swal.fire({
            icon: 'error',
            text: error.response.data,
            confirmButtonColor: "#7c601893",
          });
  });
  },
})
},

    showForm(formName) {
      this.currentForm = formName;
    },
    logOut(){
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
        axios.post(`/api/logout`)
        .then(response => {
            console.log("SingOut");
            location.pathname = `/index.html`;
        })
        .catch(error => {
            console.log(error);
        });
    },
})
},
}
}
);
  
  app.mount('#app');
