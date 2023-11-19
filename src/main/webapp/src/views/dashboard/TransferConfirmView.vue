<script setup lang="ts">

import { useRouter } from 'vue-router';
import SpinnerOverlay from '@/components/SpinnerOverlay.vue';
import IconSpinner from '@/components/icons/IconSpinner.vue';
import { ref } from 'vue';

const router = useRouter();

const isLoading = ref(false);
const showSection = ref(false);
const showSuccess = ref(false);


setTimeout(() => {
    showSection.value = true;
}, 1000);

const validateVerification = () => {
    setTimeout(() => {
        router.push('/dashboard/transfer-step-three');
    }, 3000);
}

const verify = () => {
    isLoading.value = true
    setTimeout(() => {
        isLoading.value = false;
        showSuccess.value = true;
        validateVerification();
    }, 2000);
}


</script>
<template>
    <div class="font-manrope flex h-screen w-full justify-center bg-gray-50 dark:bg-gray-900">
        <div class="mx-auto box-border w-full border bg-gray-50 dark:bg-gray-900 p-4">
            <div class="flex items-center justify-between">
                <h3 class="text-4xl font-bold dark:text-white">Fund Transfer - confirmation</h3>
            </div>

            <div class="mt-6">
                <div class="font-semibold text-[#64748B] dark:text-white">From</div>
                <div class="mt-2">
                    <div class="flex w-full items-center justify-between bg-gray-100 dark:bg-gray-500 p-3 rounded-[4px]">
                        <div class="flex items-center gap-x-2">
                            <svg xmlns="http://www.w3.org/2000/svg" class="h-8 w-8 text-[#299D37]" fill="none"
                                viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                    d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                            </svg>
                            <span class="font-semibold">Checking</span>
                        </div>

                        <div class="flex items-center gap-x-2">
                            <div class="text-[#64748B] dark:text-white">card ending in 6678</div>
                            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 cursor-pointer" fill="none"
                                viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M19 9l-7 7-7-7" />
                            </svg>
                        </div>
                    </div>
                </div>
            </div>

            <div class="mt-6">
                <div class="flex justify-between">
                    <span class="font-semibold text-[#64748B] dark:text-white">To</span>
                </div>

                <div class="flex items-center gap-x-[10px]  bg-gray-100 dark:bg-gray-500 p-3 mt-2 rounded-[4px]">
                    <img class="h-10 w-10 rounded-full"
                        src="https://images.unsplash.com/photo-1507019403270-cca502add9f8?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"
                        alt="" />
                    <div>
                        <div class="font-semibold">Kathy Miller</div>
                        <div class="">@KittyKatmills</div>
                    </div>
                </div>
            </div>

            <div class="mt-6">
                <div class="flex justify-between">
                    <span class="font-semibold text-[#64748B] dark:text-white">Amount</span>

                </div>

                <div class="flex items-center gap-x-[10px]  bg-gray-100 dark:bg-gray-500 p-3 mt-2 rounded-[4px]">

                    <div>
                        <div class="font-semibold">$100</div>
                    </div>
                </div>
            </div>
            <div v-if="!showSection" style="min-height: 200px; display: flex; align-items: center; justify-content: center;"
                class="mt-12 ">
                <SpinnerOverlay />
            </div>
            <div v-if="showSuccess" style="min-height: 200px; display: flex; align-items: center; justify-content: center;"
                class="mt-12 ">
                <div class="flex items-center p-4 mb-4 text-sm text-green-800 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:text-green-400 dark:border-green-800"
                    role="alert">
                    <svg class="flex-shrink-0 inline w-4 h-4 me-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg"
                        fill="currentColor" viewBox="0 0 20 20">
                        <path
                            d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z" />
                    </svg>
                    <span class="sr-only">Info</span>
                    <div>
                        <span class="font-medium">Verification successful. Please wait processing transaction</span>
                    </div>
                </div>
            </div>

            <div class="w-full mt-12 mb-12 border-gray-300 dark:border-gray-300" v-if="showSection && !showSuccess">
                <div class="w-1/3 mx-auto flex flex-col justify-center items-center">
                    <div>
                        <h5 class="text-xl font-bold dark:text-white">Please authorize this Transaction</h5>
                    </div>
                    <div class="mt-4 dark:border-cyan-300 w-full">
                        <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Enter
                            verification code</label>
                        <input type="email" name="email" id="email"
                            class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                            placeholder="" required="true">
                        <p id="helper-text-explanation" class="mt-2 text-sm text-gray-500 dark:text-gray-400">The
                            authenticator code is generated every 30 seconds</p>
                    </div>

                    <button @click="verify" type="button"
                        class="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800 inline-flex items-center justify-center">
                        <IconSpinner v-if="isLoading" />
                        Verify PIN
                    </button>


                    <!-- <button type="button" class="w-1/2 mt-4 text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800">Verify PIN</button> -->
                </div>
            </div>

            <!-- <div class="mt-6">
            <button @click="goToPage3" type="button"
                class="focus:outline-none text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800 w-full">
                Confirm Transaction</button> 

        </div>-->
    </div>
</div></template>