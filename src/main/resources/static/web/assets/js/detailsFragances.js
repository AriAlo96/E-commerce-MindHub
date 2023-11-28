const app = Vue.createApp({
    data() {
        return {
            fragance:[]
        };
    },

    created() {
        let urlParams = new URLSearchParams(location.search);
        let id = urlParams.get('id')     
        axios.get(`/velvet/fragances/${id}`)
            .then(response => {
                this.fragance = response.data;
                console.log(this.fragance);
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