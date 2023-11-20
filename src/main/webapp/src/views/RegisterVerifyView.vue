<script setup lang="ts">
import { useRouter } from 'vue-router';
import { onMounted, onUnmounted, ref } from 'vue';
import SpinnerOverlay from '@/components/SpinnerOverlay.vue';
import IconSpinner from '@/components/icons/IconSpinner.vue';
import { useUserStore } from '@/stores/userStore';
import axios from 'axios';

const router = useRouter();
const userInfo = useUserStore();


const iframeSrc = ref('/ui#/register/' + userInfo.code);


const checkAndVerifyTransaction = async () => {


  const payload = {
    userId: userInfo.userEmail
  }
  try {
    const response = await axios.post(`/example/api/register-verify`, payload);
    if (response.status === 200) {
      userInfo.setCode('');
      router.push('/dashboard');
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
  <section class="bg-gray-50 dark:bg-gray-900">
    <div class="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
      <a href="#" class="flex items-center mb-6 text-2xl font-semibold text-gray-900 dark:text-white">
        <img class="h-8 mr-2" src="../assets/coauth_logo.png" alt="logo">
        - Demo
      </a>
      <div
        class="w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700">

        <iframe :src="iframeSrc" class="w-full" style="min-height: 600px;" />
      </div>

    </div>
  </section>
</template>