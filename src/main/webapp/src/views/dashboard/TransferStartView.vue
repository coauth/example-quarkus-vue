<script setup lang="ts">

import { useRouter } from 'vue-router';
import { ref } from 'vue';
import IconSpinner from '@/components/icons/IconSpinner.vue';
import { useUserStore } from '@/stores/userStore';
import axios from 'axios';

const router = useRouter();
const isLoading = ref(false);
const userInfo = useUserStore();




const submitForm = async () => {
    isLoading.value = true;
    const payload = {
        userId: userInfo.userEmail
    }
    try {
        const response = await axios.post(`/example/api/transfer-start`, payload);
        if (response.status === 200) {
            userInfo.setCode(response.data.data.code);
            router.push('/dashboard/transfer-step-two');
        }
        isLoading.value = false;

    } catch (error: any) {
        console.log(error)
        isLoading.value = false;
    }
}
</script>
<template>
    <div class="font-manrope flex h-screen w-full justify-center bg-gray-50 dark:bg-gray-900">
        <div class="mx-auto box-border w-full border bg-gray-50 dark:bg-gray-900 p-4">
            <div class="flex items-center justify-between">
                <h3 class="text-4xl font-bold dark:text-white">Fund Transfer</h3>
            </div>

            <div class="mt-6">
                <div class="font-semibold text-[#64748B] dark:text-white">How much would you like to send?</div>
                <div><input class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" value="100.00" type="text"
                        placeholder="100.00" disabled /></div>

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

            <div class="mt-12 flex items-center p-4 mb-4 text-sm text-blue-800 border border-blue-300 rounded-lg bg-blue-50 dark:bg-gray-800 dark:text-blue-400 dark:border-blue-800"
                role="alert">
                <svg class="flex-shrink-0 inline w-4 h-4 me-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg"
                    fill="currentColor" viewBox="0 0 20 20">
                    <path
                        d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z" />
                </svg>
                <span class="sr-only">Info</span>
                <div>
                    <span class="font-medium">Note!</span> Just press submit button to move forward
                </div>
            </div>
            <div class="mt-6">
                <button @click="submitForm" type="button"
                    class="w-full text-white  bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800 inline-flex items-center justify-center">
                    <IconSpinner v-if="isLoading" />
                    Initiate Transfer of $100
                </button>


                <!-- <button @click="goToPage2" type="button"
                class="focus:outline-none text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800 w-full">Initiate Transfer of $100</button> -->

            </div>
        </div>
    </div>
</template>