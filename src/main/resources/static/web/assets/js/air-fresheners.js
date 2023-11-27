const app = Vue.createApp({
  data() {
    return {
      airFresheners:[],
      valueSearch:"",
      ranges: [
        { id: 'range1', label: 'US$0 - US$2.999', value: [0, 2999] },
        { id: 'range2', label: 'US$3.000 - US$5.999', value: [3000, 5999] },
        { id: 'range3', label: 'US$6.000 - US$8.999', value: [6000, 8999] },
        { id: 'range4', label: 'US$9.000 or more', value: [9000, Infinity] }
      ],
      rangeSelected: null,
      presentations: [
        { id: 'ambient', label: 'Ambient', value: 'ambient' },
        { id: 'fabrics', label: 'Fabrics', value: 'fabrics' },
        { id: 'diffusers', label: 'Diffusers', value: 'diffusers' }
      ],
      presentationSelected: null,
    };
  },
  created() {
    axios.get("")
        .then(response => {
            this.airFresheners = response.data;
            
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

    filterByPriceAndPresentation(){
      let priceSelected = this.ranges.find(range => range.value === this.rangeSelected);
      let presentationSelected = this.presentations.find(presentation => presentation.value === this.presentationSelected);
      this.filteredByPrice = this.airFresheners.filter(airFreshener=> {
        return (!priceSelected || (airFreshener.price >= priceSelected.value[0] && airFreshener.price <= priceSelected.value[1])) && (!presentationSelected || airFreshener.presentation === presentationSelected.value)});
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

