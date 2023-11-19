<script setup lang="ts">
import { ref } from 'vue';
import SpinnerOverlay from '@/components/SpinnerOverlay.vue';
import IconSpinner from '@/components/icons/IconSpinner.vue';



const isLoading = ref(false);
const showSection = ref(false);
const showSuccess = ref(false);



const validateVerification = () => {
    setTimeout(() => {
        const value = currentIndex.value
        if (tableData.value[value].payeeName === inputValue.value) {
            inputValue.value = '';
            tableData.value.splice(value, 1);
            closeModal();
        }
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
    // Add more dummy data as needed
]);
const currentIndex = ref(1);

const isModalVisible = ref(false);

const openModal = () => {
    isModalVisible.value = true;
};

const closeModal = () => {
    isModalVisible.value = false;
};

const showError = ref(false);

const confirmDeletePayee = () => {
    showError.value = false;
    const value = currentIndex.value
    if (tableData.value[value].payeeName === inputValue.value) {
        verify();
    } else {
        showError.value = true;
    }
}
const inputValue = ref('');
const deletePayee = (index: number) => {
    currentIndex.value = index;
    isModalVisible.value = true;
    showSection.value = false;
    setTimeout(() => {
        showSection.value = true;
    }, 1000);
    // Your logic to delete the payee at the specified index
    // tableData.value.splice(index, 1);
};
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


        <div class="relative p-4 w-1/3  max-h-full">
            <div class="relative bg-white rounded-lg shadow dark:bg-gray-900">
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

                    <div v-if="!showSection"
                        style="min-height: 100px; display: flex; align-items: center; justify-content: center;"
                        class="mt-12 ">
                        <SpinnerOverlay />
                    </div>
                    <div v-if="showSuccess"
                        style="min-height: 200px; display: flex; align-items: center; justify-content: center;"
                        class="mt-12 ">
                        <div class="flex items-center p-4 mb-4 text-sm text-green-800 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:text-green-400 dark:border-green-800"
                            role="alert">
                            <svg class="flex-shrink-0 inline w-4 h-4 me-3" aria-hidden="true"
                                xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 20">
                                <path
                                    d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z" />
                            </svg>
                            <span class="sr-only">Info</span>
                            <div>
                                <span class="font-medium">Verification successful. Please wait your request is processing.</span>
                            </div>
                        </div>
                    </div>

                    <div class="w-full mt-12 mb-12 dark:border-red-300" v-if="showSection && !showSuccess">
                        <div class="w-full mx-auto flex flex-col justify-center items-center ">
                            <div>
                                <h5 class="text-xl font-bold dark:text-white">Please authorize this Transaction</h5>
                            </div>
                            <div class="mt-4 dark:border-cyan-300 w-full">
                                <label for="email"
                                    class="block mb-2 text-sm font-normal text-gray-900 dark:text-white">Re-type the word:
                                    <span class="text-xl font-bold">{{ tableData[currentIndex].payeeName }}</span> in the
                                    input
                                    below</label>
                                <input type="email" name="email" id="email" v-model="inputValue"
                                    class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    placeholder="" required="true">
                                <p v-if="showError" class="mt-2 text-sm text-red-600 dark:text-red-500"><span
                                        class="font-medium">Entered
                                        value does not match</span></p>


                            </div>
                            <button @click="confirmDeletePayee" type="button"
              class="mt-8 w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800 inline-flex items-center justify-center">
              <IconSpinner v-if="isLoading" />
              Confirm
            </button>
                        </div>
                    </div>



                </div>
        </div>
    </div>
</div></template>
