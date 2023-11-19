<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo List</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
    <div class=" subpixel-antialiased font-sans flex items-center justify-center
     max-h-full h-screen">
        <div class=" max-w-3xl bg-slate-500 p-4 rounded-md flex flex-col space-y-4">
            <h1 class="font-sans text-white font-bold text-2xl text-center">Todo List</h1>
           <div class="flex space-x-4 items-center">
            <textarea type="text" class=" text-start
             appearance-none p-2 rounded-md bg-slate-50 
            text-slate-900 border-slate-600 ring-slate-500 font-semibold
            transition-all
            placeholder:text-slate-800 placeholder:font-bold
            selection:bg-slate-500
            "
            placeholder="Add Todo"
             name="todo">
            </textarea>
            <div>
                <button type="submit" class=" text-white font-bold px-4 py-2 text-sm bg-slate-900
            hover:bg-slate-600 focus:bg-slate-800 transition-all
            focus:ring-slate-800 focus:ring-2
            rounded-md" >Add Todo</button>
            </div>
           </div>
        </div>
    </div>
</body>
</html>