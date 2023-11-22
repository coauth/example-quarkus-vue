<script setup lang="ts">

import { useRouter } from 'vue-router';
import SpinnerOverlay from '@/components/SpinnerOverlay.vue';
import IconSpinner from '@/components/icons/IconSpinner.vue';
import { onMounted, onUnmounted, ref } from 'vue';
import { useUserStore } from '@/stores/userStore';
import axios from 'axios';

const router = useRouter();

const userInfo = useUserStore();

const iframeSrc = ref('/ui/#/verify/' + userInfo.code);

const checkAndVerifyTransaction = async () => {
    const payload = {
        userId: userInfo.userEmail
    }
    try {
        const response = await axios.post(`/example/api/transfer-verify`, payload);
        if (response.status === 200) {
            userInfo.setCode('');
            router.push('/dashboard/transfer-step-three');
        }
    } catch (error: any) {
        console.log(error)
    }
}

const receiveMessage = (event: any) => {
    if (event.data == "transactionComplete") {
        checkAndVerifyTransaction();
    }
}
onMounted(() => {
    window.addEventListener('message', receiveMessage);
});

onUnmounted(() => {
    window.removeEventListener('message', receiveMessage);
});






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


            <div class="w-full mt-12 mb-12 border-gray-300 dark:border-gray-300">
                <iframe :src="iframeSrc" class="w-full" style="min-height: 600px;" />
            </div>

        </div>
    </div>
</template>