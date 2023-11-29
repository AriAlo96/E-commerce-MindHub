const app = Vue.createApp({
  data() {
    return {
      currentForm: 'form1',
      fragances: [],
      fraganceSelected: undefined,
      idFragance: undefined,
      nameFragance: undefined,
      descriptionFragance: undefined,
      genderFragance: undefined,
      olfactoryFamilyFragance: undefined,
      priceFragance: undefined,
      presentationFragance: undefined,
      contentFragance: undefined,
      stockFragance: undefined,
      routeImageFragance: undefined,

      airFresheners: [],
      airFreshenerSelected: undefined,
      idAirFreshener: undefined,
      nameAirFreshener: undefined,
      descriptionAirFreshener: undefined,
      contentAirFreshener: undefined,
      priceAirFreshener: undefined,
      stockAirFreshener: undefined,
      presentationAirFreshener: undefined,
      routeImageAirFreshener: undefined,

      creams: [],
      nameCream: undefined,
      descriptionCream: undefined,
      priceCream: undefined,
      contentCream: undefined,
      stockCream: undefined,
      typeCream: undefined,
      routeImageCream: undefined,
    };
  },

  created() {
    axios.get("/velvet/fragances")
      .then(response => {
        this.fragances = response.data;
      })
      .catch(error => {
        console.log(error);
      });
    axios.get("/velvet/flavorings")
      .then(response => {
        this.airFresheners = response.data;
      })
      .catch(error => {
        console.log(error);
      });

    axios.get("/velvet/creams")
      .then(response => {
        this.creams = response.data;

      })
      .catch(error => {
        console.log(error);
      });
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
          axios.post("/velvet/fragances/create", `name=${this.nameFragance}&description=${this.descriptionFragance}&gender=${this.genderFragance}&olfactoryFamily=${this.olfactoryFamilyFragance}&price=${this.priceFragance}&presentation=${this.presentationFragance}&content=${this.contentFragance}&stock=${this.stockFragance}&image=${this.routeImageFragance}`)
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
          axios.post(`/velvet/flavorings/create`, `name=${this.nameAirFreshener}&description=${this.descriptionAirFreshener}&content=${this.contentAirFreshener}&price=${this.priceAirFreshener}&stock=${this.stockAirFreshener}&presentation=${this.presentationAirFreshener}&image=${this.routeImageAirFreshener}`)
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
          axios.post(`/velvet/creams/create`, `name=${this.nameCream}&description=${this.descriptionCream}&price=${this.priceCream}&content=${this.contentCream}&stock=${this.stockCream}&type=${this.typeCream}&image=${this.routeImageCream}`)
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

    updateFragance() {
      Swal.fire({
        title: 'Do you want to update a fragance?',
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
          const requestData = {
            name: this.nameFragance,
            description: this.descriptionFragance,
            gender: this.genderFragance,
            olfactoryFamily: this.olfactoryFamilyFragance,
            price: this.priceFragance,
            presentation: this.presentationFragance,
            content: this.contentFragance,
            stock: this.stockFragance,
            image: this.routeImageFragance,
            id: this.idFragance
          };
          console.log("Before filtering:", Object.entries(requestData));
          const filteredData = Object.fromEntries(
            Object.entries(requestData).filter(([_, value]) => value !== null && value !== undefined)
          );
          console.log("After filtering:", filteredData);

          const queryString = new URLSearchParams(filteredData).toString();
          axios.patch(`/velvet/fragances/update?${queryString}`)
            .then(() => {
              Swal.fire({
                title: "Successfully update fragance",
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

    updateIdFragance() {
      if (this.fraganceSelected) {
        this.idFragance = this.fraganceSelected.id;
      } else {
        this.idFragance = null;
      }
    },

    updateAirFreshener() {
      Swal.fire({
        title: 'Do you want to update a new air Freshener?',
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
          axios.patch(`/velvet/flavorings/update`, `name=${this.nameAirFreshener}&description=${this.descriptionAirFreshener}&content=${this.contentAirFreshener}&price=${this.priceAirFreshener}&stock=${this.stockAirFreshener}&presentation=${this.presentationAirFreshener}&image=${this.routeImageAirFreshener}`)
            .then(() => {
              Swal.fire({
                title: "Successfully update air freshener",
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

    updateIdAirFreshener() {
      if (this.airFreshenerSelected) {
        this.idAirFreshener = this.airFreshenerSelected.id;
      } else {
        this.idAirFreshener = null;
      }
    },

    updateCream() {
      Swal.fire({
        title: 'Do you want to update cream?',
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
          axios.patch(`/velvet/creams/update`, `name=${this.nameCream}&description=${this.descriptionCream}&price=${this.priceCream}&content=${this.contentCream}&stock=${this.stockCream}&type=${this.typeCream}&image=${this.routeImageCream}`)
            .then(() => {
              Swal.fire({
                title: "Successfully update cream",
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
