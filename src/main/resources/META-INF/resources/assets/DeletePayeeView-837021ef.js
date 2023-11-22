import{d as f,a as N,r as l,o as C,b as B,c as o,e as r,f as e,F as g,q as M,p as x,n as V,t as p,h as b}from"./index-4fed5e46.js";const D=e("div",{class:"p-8"},[e("h3",{class:"text-4xl font-bold dark:text-white"},"Your Payees")],-1),E={class:"relative overflow-x-auto shadow-md sm:rounded-lg p-8"},P={class:"w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400"},j=e("thead",{class:"text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400"},[e("tr",null,[e("th",{scope:"col",class:"px-6 py-3"},"Payee name"),e("th",{scope:"col",class:"px-6 py-3"},"Account number"),e("th",{scope:"col",class:"px-6 py-3"},"Bank name"),e("th",{scope:"col",class:"px-6 py-3"},"Action")])],-1),A={class:"px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"},I={class:"px-6 py-4"},S={class:"px-6 py-4"},Y={class:"px-6 py-4"},Z=["onClick"],z={key:1,id:"popup-modal",tabindex:"-1",class:"fixed top-0 right-0 bottom-0 left-0 z-50 flex items-center justify-center border-white dark:border-white"},F={class:"relative p-4 w-1/3 max-h-full"},J={class:"relative bg-white rounded-lg shadow dark:bg-gray-900"},L=e("svg",{class:"w-3 h-3","aria-hidden":"true",xmlns:"http://www.w3.org/2000/svg",fill:"none",viewBox:"0 0 14 14"},[e("path",{stroke:"currentColor","stroke-linecap":"round","stroke-linejoin":"round","stroke-width":"2",d:"m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"})],-1),X=e("span",{class:"sr-only"},"Close modal",-1),T=[L,X],U={class:"p-4 md:p-5 text-center"},q=e("svg",{class:"mx-auto mb-4 text-gray-400 w-12 h-12 dark:text-gray-200","aria-hidden":"true",xmlns:"http://www.w3.org/2000/svg",fill:"none",viewBox:"0 0 20 20"},[e("path",{stroke:"currentColor","stroke-linecap":"round","stroke-linejoin":"round","stroke-width":"2",d:"M10 11V6m0 8h.01M19 10a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"})],-1),G={class:"w-full mt-12 mb-12 border-gray-300 dark:border-gray-300"},H=["src"],Q=f({__name:"DeletePayeeView",setup(K){const s=N(),m=l(""),c=l(1),n=l(!1),d=l([{payeeName:"John Doe",accountNumber:"************1234",bankName:"ABC Bank"},{payeeName:"Jane Smith",accountNumber:"************5678",bankName:"XYZ Bank"},{payeeName:"Jacob Mathews",accountNumber:"************6780",bankName:"XYZ Bank"},{payeeName:"Godwin Pinto",accountNumber:"************4567",bankName:"XYZ Bank"}]),_=()=>{const t=c.value;d.value.splice(t,1),i()},i=()=>{n.value=!1},v=t=>{c.value=t,k()},k=async()=>{const t={userId:s.userEmail,reConfirmText:d.value[c.value].payeeName};try{const a=await b.post("/example/api/delete-payee-start",t);a.status===200&&(s.setCode(a.data.data.code),n.value=!0,m.value="/ui#/verify/"+s.code)}catch(a){console.log(a)}},w=async()=>{const t={userId:s.userEmail};try{(await b.post("/example/api/delete-payee-verify",t)).status===200&&(s.setCode(""),_())}catch(a){console.log(a)}},h=t=>{t.data=="transactionComplete"&&w()};return C(()=>{window.addEventListener("message",h)}),B(()=>{window.removeEventListener("message",h)}),(t,a)=>(o(),r(g,null,[D,e("div",E,[e("table",P,[j,e("tbody",null,[(o(!0),r(g,null,M(d.value,(u,y)=>(o(),r("tr",{key:y,class:V({"odd:bg-white odd:dark:bg-gray-900 even:bg-gray-50 even:dark:bg-gray-800 border-b dark:border-gray-700":!0})},[e("td",A,p(u.payeeName),1),e("td",I,p(u.accountNumber),1),e("td",S,p(u.bankName),1),e("td",Y,[e("a",{href:"#",onClick:()=>v(y),class:"font-medium text-red-600 dark:text-red-500 hover:underline"},"Delete",8,Z)])]))),128))])])]),n.value?(o(),r("div",{key:0,class:"fixed top-0 right-0 bottom-0 left-0 z-40 bg-black bg-opacity-70",onClick:i})):x("",!0),n.value?(o(),r("div",z,[e("div",F,[e("div",J,[e("button",{onClick:i,type:"button",class:"absolute top-3 end-2.5 text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white","data-modal-hide":"popup-modal"},T),e("div",U,[q,e("div",G,[e("iframe",{src:m.value,class:"w-full",style:{"min-height":"600px"}},null,8,H)])])])])])):x("",!0)],64))}});export{Q as default};