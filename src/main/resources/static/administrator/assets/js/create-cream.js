const app = Vue.createApp({
    data() {
      return {
        creams: [],
        name:"",
        description: "",
        price: 0,
        content: 0,   
        stock: 0,
        type: "",
        routeImage: "",
    };
    },
    
    created() {
        axios.get("")
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
            axios.post(`/velvet/new/fragance`,`name=${this.name}&description=${this.description}&price=${this.price}&content=${this.content}&stock=${this.stock}&type=${this.type}&image=${this.routeImage}`)
                .then(() => {
                    Swal.fire({
                        title: "Successfully created air freshener",
                        icon: "success",
                        confirmButtonColor: "#3085d6",
                      }).then((result) => {
                        if (result.isConfirmed) {
                            location.pathname = `/administrator/assets/pages/crate-air-freshener.html`;
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
