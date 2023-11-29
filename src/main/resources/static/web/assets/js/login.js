const app = Vue.createApp({
    data() {
        return {
            email: "",
            password: "",
            exitMessage: "",
            errorStatus: null,
            errorRegister: "",
        };
    },
   
    methods: {
        login() {
            axios.post('/velvet/login', `email=${this.email}&password=${this.password}`)
                .then(response => {
                    location.pathname = `index.html`;
                })
                .catch(error => {
                    if (error.response && error.response.status === 401) {
                        this.errorStatus = 401
                        this.errorMessage = "Incorrect credentials. Please try again."
                    } else {
                        this.errorStatus = null
                    }
                });
        },
    }
}
);

app.mount('#app');

