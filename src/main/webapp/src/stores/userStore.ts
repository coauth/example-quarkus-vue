import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', () => {
  const userEmail = ref('')
  const code=ref('')

  const saveUserDetails=(email:string) =>{
    userEmail.value = email
  }
  const setCode=(newCode:string) =>{
    code.value = newCode
  }

  return { userEmail, saveUserDetails, code, setCode}
})
