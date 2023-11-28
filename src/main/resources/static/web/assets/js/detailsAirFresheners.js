const app = Vue.createApp({
    data() {
        return {
            airFreshener: []
        };
    },

    created() {
        let urlParams = new URLSearchParams(location.search);
        let id = urlParams.get('id')     
        axios.get(`/velvet/flavorings/${id}`)
            .then(response => {
                this.airFreshener = response.data;
            })
            .catch(error => {
                this.messageError = error.response.data;
            });
    },

    methods: {
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