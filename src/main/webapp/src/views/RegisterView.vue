<script setup lang="ts">
import { useRouter } from 'vue-router';
import IconSpinner from '@/components/icons/IconSpinner.vue';
import { ref } from 'vue';
import axios from 'axios';
import ErrorOverlay from '@/components/ErrorOverlay.vue';
import { useUserStore } from '@/stores/userStore';

const router = useRouter();

const isLoading = ref(false);
const email = ref('');
const password = ref('');
const rePassword = ref('');
const errorMessage = ref('');

const userInfo = useUserStore();



// const validateVerification = () => {
//     isLoading.value = true;
//     setTimeout(() => {
//         router.push('/verify-register');
//     }, 2000);
// }


const submitForm = async () => {
    errorMessage.value = '';
    if (email.value == '') {
        errorMessage.value = 'Email is required';
        return;
    }
    if (password.value == '') {
        errorMessage.value = 'Password is required';
        return;
    }
    if (password.value !== rePassword.value) {
        errorMessage.value = 'Passwords Missmatch';
        return;
    }
    isLoading.value = true;
    const payload = {
        userId: email.value,
        password: password.value,
        rePassword: rePassword.value
    }
    try {
        const response = await axios.post(`/example/api/register-start`, payload);
        if (response.status === 200) {
            userInfo.saveUserDetails(email.value);
            userInfo.setCode(response.data.data.code);
            router.push('/verify-register');
        }
        isLoading.value = false;

    } catch (error: any) {
        console.log(error)
        if (error.response && error.response.status === 400) {
            errorMessage.value = error.response.data.error.message;
        } else {
            errorMessage.value = 'An unexpected error occurred.';
        }
        isLoading.value = false;
    }
}


</script>
<template>
    <section class="bg-gray-50 dark:bg-gray-900">
        <div class="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
            <a href="#" class="flex items-center mb-6 text-2xl font-semibold text-gray-900 dark:text-white">
                <img class="h-8 mr-2" src="../assets/coauth_logo.png" alt="logo">
                - Demo
            </a>
            <div
                class="w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700">
                <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
                    <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
                        Register a dummy account
                    </h1>

                    <ErrorOverlay v-if="errorMessage != ''" :msg=errorMessage />
                    <p class="w-full bg-white rounded-lg shadow md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 text-white ">
                        Note: Enter any dummy email</p>
                    <form class="space-y-4 md:space-y-6" action="#">
                        <div>
                            <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Enter
                                your email</label>
                            <input type="email" name="email" id="email"
                                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                placeholder="name@company.com" required="true" v-model="email">
                        </div>
                        <div>
                            <label for="password"
                                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Password</label>
                            <input type="password" name="password" id="password" placeholder="••••••••"
                                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                required="true" v-model="password">
                        </div>
                        <div>
                            <label for="password"
                                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Re-enter
                                Password</label>
                            <input v-model="rePassword" type="password" name="rePassword" id="rePassword"
                                placeholder="••••••••"
                                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                required="true">
                        </div>

                        <button @click="submitForm" type="button"
                            class="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800 inline-flex items-center justify-center">
                            <IconSpinner v-if="isLoading" />
                            Sign in
                        </button>


                    </form>
                </div>
            </div>
        </div>
    </section>
</template>