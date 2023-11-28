const app = Vue.createApp({
    data() {
        return {
            cream:[]
        };
    },

    created() {
        let urlParams = new URLSearchParams(location.search);
        let id = urlParams.get('id')     
        axios.get(`/velvet/creams/${id}`)
            .then(response => {
                this.cream = response.data;
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