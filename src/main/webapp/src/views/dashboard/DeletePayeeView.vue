<script setup lang="ts">
import { onMounted, onUnmounted, ref } from 'vue';
import SpinnerOverlay from '@/components/SpinnerOverlay.vue';
import IconSpinner from '@/components/icons/IconSpinner.vue';
import { useUserStore } from '@/stores/userStore';
import axios from 'axios';

const userInfo = useUserStore();
const iframeSrc = ref('');
const currentIndex = ref(1);
const isModalVisible = ref(false);

const tableData = ref([
    {
        payeeName: 'John Doe',
        accountNumber: '************1234',
        bankName: 'ABC Bank',
    },
    {
        payeeName: 'Jane Smith',
        accountNumber: '************5678',
        bankName: 'XYZ Bank',
    },
    {
        payeeName: 'Jacob Mathews',
        accountNumber: '************6780',
        bankName: 'XYZ Bank',
    },
    {
        payeeName: 'Godwin Pinto',
        accountNumber: '************4567',
        bankName: 'XYZ Bank',
    },
]);

const validateVerification = () => {
    const value = currentIndex.value
    tableData.value.splice(value, 1);
    closeModal();
}


const closeModal = () => {
    isModalVisible.value = false;
};

const deletePayee = (index: number) => {
    currentIndex.value = index;
    submitForm();
};


const submitForm = async () => {
    const payload = {
        userId: userInfo.userEmail,
        reConfirmText: tableData.value[currentIndex.value].payeeName
    }
    try {
        const response = await axios.post(`/example/api/delete-payee-start`, payload);
        if (response.status === 200) {
            userInfo.setCode(response.data.data.code);
            isModalVisible.value = true;
            iframeSrc.value = '/ui/#/verify/' + userInfo.code;
        }
    } catch (error: any) {
        console.log(error)
    }
}


const checkAndVerifyTransaction = async () => {
    const payload = {
        userId: userInfo.userEmail
    }
    try {
        const response = await axios.post(`/example/api/delete-payee-verify`, payload);
        if (response.status === 200) {
            userInfo.setCode('');
            validateVerification();
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
    <div class="p-8">
        <h3 class="text-4xl font-bold dark:text-white">Your Payees</h3>
    </div>
    <div class="relative overflow-x-auto shadow-md sm:rounded-lg p-8">
        <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
            <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                <tr>
                    <th scope="col" class="px-6 py-3">Payee name</th>
                    <th scope="col" class="px-6 py-3">Account number</th>
                    <th scope="col" class="px-6 py-3">Bank name</th>
                    <th scope="col" class="px-6 py-3">Action</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(payee, index) in tableData" :key="index"
                    :class="{ 'odd:bg-white odd:dark:bg-gray-900 even:bg-gray-50 even:dark:bg-gray-800 border-b dark:border-gray-700': true }">
                    <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                        {{ payee.payeeName }}
                    </td>
                    <td class="px-6 py-4">
                        {{ payee.accountNumber }}
                    </td>
                    <td class="px-6 py-4">
                        {{ payee.bankName }}
                    </td>
                    <td class="px-6 py-4">
                        <a href="#" @click="() => deletePayee(index)"
                            class="font-medium text-red-600 dark:text-red-500 hover:underline">Delete</a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>



    <div v-if="isModalVisible" class="fixed top-0 right-0 bottom-0 left-0 z-40 bg-black bg-opacity-70" @click="closeModal">
    </div>


    <div v-if="isModalVisible" id="popup-modal" tabindex="-1"
        class="fixed top-0 right-0 bottom-0 left-0 z-50 flex items-center justify-center border-white dark:border-white">


        <div class="relative p-4 w-1/3  max-h-full h-1/2">
            <div class="relative bg-white rounded-lg shadow dark:bg-gray-900 flex flex-col w-full justify-center align-middle">
                <button @click="closeModal" type="button"
                    class="absolute top-3 end-2.5 text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white"
                    data-modal-hide="popup-modal">
                    <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                        viewBox="0 0 14 14">
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6" />
                    </svg>
                    <span class="sr-only">Close modal</span>
                </button>

                <div class="p-4 md:p-5 text-center">
                    <svg class="mx-auto mb-4 text-gray-400 w-12 h-12 dark:text-gray-200" aria-hidden="true"
                        xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="M10 11V6m0 8h.01M19 10a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
                    </svg>



                        <iframe :src="iframeSrc" class="w-full" style="height:250px"/>



                </div>
            </div>
        </div>
    </div>
</template>
