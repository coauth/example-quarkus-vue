<script setup lang="ts">
import { useRouter } from 'vue-router';
import {ref} from 'vue';
const router = useRouter();

const activeLink=ref('dashboard');

const goToTransfer=()=>{
    activeLink.value='transfer';
    router.push('/dashboard/transfer-step-one');
}

const goToDashboard=()=>{
    activeLink.value='dashboard';
    router.push('/dashboard');
}

const goToDeletePayee=()=>{
    activeLink.value='deletePayee';
    router.push('/dashboard/delete-payee-step-one');
}

</script>
<template>
    <aside
        class="ml-[-100%] fixed z-10 top-0 pb-3 px-6 w-full flex flex-col justify-between h-screen border-r bg-blue-900 transition duration-300 md:w-4/12 lg:ml-0 lg:w-[25%] xl:w-[20%] 2xl:w-[15%]">
        <div>
            <div class="-mx-6 px-6 py-4">
                <a href="#" title="home">
                    <img src="@/assets/coauth_logo.png" class="w-32"
                        alt="Co-Auth logo">
                </a>
            </div>

            <div class="mt-8 text-center">
                <img src="https://tailus.io/sources/blocks/stats-cards/preview/images/second_user.webp" alt=""
                    class="w-10 h-10 m-auto rounded-full object-cover lg:w-28 lg:h-28">
                <h5 class="hidden mt-4 text-xl font-semibold -mr-1 font-medium text-white lg:block">Hello</h5>
            </div>

            <ul class="space-y-2 tracking-wide mt-8">
                <li>
                    <a aria-label="dashboard" @click="goToDashboard"
                        class="cursor-pointer relative px-4 py-3 flex items-center space-x-4 " :class="{
                            'bg-gradient-to-r from-sky-600 to-cyan-400 text-white rounded-xl ':activeLink=='dashboard'
                        }">
                        <svg class="-ml-1 h-6 w-6" viewBox="0 0 24 24" fill="none">
                            <path
                                d="M6 8a2 2 0 0 1 2-2h1a2 2 0 0 1 2 2v1a2 2 0 0 1-2 2H8a2 2 0 0 1-2-2V8ZM6 15a2 2 0 0 1 2-2h1a2 2 0 0 1 2 2v1a2 2 0 0 1-2 2H8a2 2 0 0 1-2-2v-1Z"
                                class="fill-current text-cyan-400 dark:fill-slate-600"></path>
                            <path d="M13 8a2 2 0 0 1 2-2h1a2 2 0 0 1 2 2v1a2 2 0 0 1-2 2h-1a2 2 0 0 1-2-2V8Z"
                                class="fill-current text-cyan-200 group-hover:text-cyan-300"></path>
                            <path d="M13 15a2 2 0 0 1 2-2h1a2 2 0 0 1 2 2v1a2 2 0 0 1-2 2h-1a2 2 0 0 1-2-2v-1Z"
                                class="fill-current group-hover:text-sky-300"></path>
                        </svg>
                        <span class="-mr-1 font-medium text-white">Dashboard</span>
                    </a>
                </li>
                <li>
                    <a @click="goToTransfer" class="cursor-pointer px-4 py-3 flex items-center space-x-4 rounded-md text-gray-600 group" :class="{
                            'bg-gradient-to-r from-sky-600 to-cyan-400 text-white rounded-xl ' :activeLink=='transfer'
                        }">
                        <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                            <path class="fill-current text-gray-300 group-hover:text-cyan-300" fill-rule="evenodd"
                                d="M2 6a2 2 0 012-2h4l2 2h4a2 2 0 012 2v1H8a3 3 0 00-3 3v1.5a1.5 1.5 0 01-3 0V6z"
                                clip-rule="evenodd" />
                            <path class="fill-current text-gray-600 group-hover:text-cyan-600"
                                d="M6 12a2 2 0 012-2h8a2 2 0 012 2v2a2 2 0 01-2 2H2h2a2 2 0 002-2v-2z" />
                        </svg>
                        <span class="-mr-1 font-medium text-white">Try Transfer</span>
                    </a>
                </li>
                <li>
                    <a @click="goToDeletePayee" class="cursor-pointer px-4 py-3 flex items-center space-x-4 rounded-md text-gray-600 group"  :class="{
                            'bg-gradient-to-r from-sky-600 to-cyan-400 text-white rounded-xl ' :activeLink=='deletePayee'
                        }">
                        <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                            <path class="fill-current text-gray-600 group-hover:text-cyan-600" fill-rule="evenodd"
                                d="M2 5a2 2 0 012-2h8a2 2 0 012 2v10a2 2 0 002 2H4a2 2 0 01-2-2V5zm3 1h6v4H5V6zm6 6H5v2h6v-2z"
                                clip-rule="evenodd" />
                            <path class="fill-current text-gray-300 group-hover:text-cyan-300"
                                d="M15 7h1a2 2 0 012 2v5.5a1.5 1.5 0 01-3 0V7z" />
                        </svg>
                        <span class="-mr-1 font-medium text-white">Try Delete Payee</span>
                    </a>
                </li>
               
            </ul>
        </div>

        <!-- <div class="px-6 -mx-6 pt-4 flex justify-between items-center border-t">
            <button class="px-4 py-3 flex items-center space-x-4 rounded-md text-white group">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24"
                    stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                        d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1" />
                </svg>
                <span class="group-hover:text-gray-700 text-white">Logout</span>
            </button>
        </div> -->
    </aside>
    <div class="ml-auto mb-0 lg:w-[75%] xl:w-[80%] 2xl:w-[85%] bg-gray-50 dark:bg-gray-900 h-screen">
        <RouterView />
</div>
</template>