<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo List</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style type="text/tailwindcss">
        @layer components{
            ::-webkit-scrollbar {
    width: 10px
}

body {
    overflow-y: auto
}

::-webkit-scrollbar-thumb {
    background-color: hsla(0,0%,62%,.4);
    border-radius: 9999px;
    width: 2.5rem
}

::-webkit-scrollbar-thumb:hover {
    background-color: hsla(0,0%,46%,.4)
}

            .input{
                @apply  text-start
            appearance-none p-2 rounded-md bg-slate-300 
           text-slate-900 border-slate-600 ring-slate-500 font-semibold
           transition-all
           placeholder-shown:font-bold
           placeholder:text-slate-800 placeholder:font-bold
           selection:bg-slate-500;
            }
            .button{
                @apply text-white font-bold px-4 py-2 bg-slate-900
            hover:bg-slate-600 focus:bg-slate-700 transition-all
            focus:ring-slate-800 focus:ring-2
            rounded-md
            }
        }
    </style>
</head>

<body class="overflow-y-auto h-full max-h-fit">
    <div class=" text-white subpixel-antialiased font-sans flex items-center justify-center
     max-h-full h-screen">
        <div class=" max-w-xl w-screen bg-slate-500 p-4 rounded-md flex flex-col space-y-4">
            <h1 class="font-sans  font-bold text-3xl text-center">Todo List</h1>
            <h2 class=" text-2xl font-bold">Create Section</h2>
            <label for="title" class="  font-bold text-2xl">Title</label>
            <input type="text" class="input text-2xl" placeholder="title" name="title">
            <label for="descrition" class=" font-bold text-xl"> Description</label>
            <textarea type="text" class="
           input text-xl
            " placeholder="Add Todo" name="description"></textarea>
            <button type="submit" class=" button  text-xl">Add Todo</button>

            <h1 class=" text-2xl font-bold">
                List Section
            </h1>
            <div class="flex space-x-2 items-end">
                <div class="flex flex-col space-y-4 w-full">
                    <label for="search" class=" text-xl font-bold">Search</label>
                    <input type="text" class="input text-xl" placeholder="search todos..." name="search">
                </div>
                <button class="button text-xl text-center text-white  font-bold hover:scale-75">
                    <svg fill="none" stroke="currentColor" stroke-width="1.5" class=" w-8 h-8" viewBox="0 0 24 24"
                        xmlns="http://www.w3.org/2000/svg" aria-hidden="true">
                        <path stroke-linecap="round" stroke-linejoin="round"
                            d="M21 21l-5.197-5.197m0 0A7.5 7.5 0 105.196 5.196a7.5 7.5 0 0010.607 10.607z"></path>
                    </svg>
                </button>
            </div>
            <div class="bg-slate-300 p-4 rounded-lg space-y-4">
                <div class="text-slate-900 text-xl font-bold">
                    Todos
                </div>
                <div class="h-[30vh] overflow-y-auto bg-slate-200 p-2 rounded-lg">
                    <div class=" bg-slate-300 text-slate-900 rounded-xl cursor-pointer hover:bg-slate-400 focus:bg-slate-400">
                       <div class="flex py-2 px-4">
                            <div class="flex flex-col">
                                <h1 class=" text-xl font-bold">Section Title</h1>
                                <p class="text-sm">Description</p>
                            </div>
                       </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</body>
<script>

</script>

</html>