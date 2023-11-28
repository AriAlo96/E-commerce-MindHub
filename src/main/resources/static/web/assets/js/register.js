const app = Vue.createApp({
    data() {
        return {
            firstName: "",
            lastName: "",
            address: "",
            email: "",
            password: "",
            exitMessage: "",
            errorStatus: null,
            errorRegister: "",
        };
    },
    methods: {
        register() {
            axios.post('/velvet/clients/register', `firstName=${this.firstName}&lastName=${this.lastName}&email=${this.email}&password=${this.password}&address=${this.address}`)
                .then(() => {
                    axios.post('/velvet/login', `email=${this.email}&password=${this.password}`)
                        .then(() => location.pathname = `index.html`)
                        .catch(error => {
                            console.log(error);
                        });
                })
                .catch(error => {
                    let errorMessage = error.response.data;
                    errorMessage = errorMessage.replace(/\n/g, '<br>');
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        html: errorMessage,
                    });
                });

        },
    }
}
);

app.mount('#app');

