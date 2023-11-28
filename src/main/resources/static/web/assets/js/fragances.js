const app = Vue.createApp({
    data() {
      return {
        fragances: [],
        valueSearch:"",
        ranges: [
          { id: 'range1', label: 'US$0 - US$2.999', value: [0, 2999] },
          { id: 'range2', label: 'US$3.000 - US$5.999', value: [3000, 5999] },
          { id: 'range3', label: 'US$6.000 - US$8.999', value: [6000, 8999] },
          { id: 'range4', label: 'US$9.000 or more', value: [9000, Infinity] }
        ],
        rangeSelected: null,
        genders: [
          { id: 'femenine', label: 'Femenine', value: 'femenine' },
        { id: 'masculine', label: 'Masculine', value: 'masculine' } 
        ],
        genderSelected: null,
      };
    },
    
    created() {
        axios.get("/velvet/fragances")
            .then(response => {
                this.fragances = response.data;
                console.log(this.fragances);
                
            })
            .catch(error => {
                console.log(error);
            });
    },

    methods: {
      filterSearch() {
        this.fragances.filter(fragance =>
          fragance.name.toLowerCase().includes(this.valueSearch.toLowerCase())
        );
      },
        filterByPriceAndGender(){
        let priceSelected = this.ranges.find(range => range.value === this.rangeSelected);
        let genderSelected = this.genders.find(gender => gender.value === this.genderSelected);
        this.filteredByPrice = this.fragances.filter(fragance=> {
          return (!priceSelected || (fragance.price >= priceSelected.value[0] && fragance.price <= priceSelected.value[1])) && (!genderSelected || fragance.gender === genderSelected.value)});
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

  