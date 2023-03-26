import React, {useEffect, useState} from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import {Route, Routes} from "react-router-dom";
import {BooksPage} from "./pages/BooksPage";
import {CreateBookPage} from "./pages/CreateBookPage";
import {Layout} from "./layout/Layout";


function App() {

    return (
        <div>
            {/* Routes nest inside one another. Nested route paths build upon
            parent route paths, and nested route elements render inside
            parent route elements. See the note about <Outlet> below. */}
            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route index element={<BooksPage />} />
                    <Route path="create" element={<CreateBookPage />} />


                    {/* Using path="*"" means "match anything", so this route
                acts like a catch-all for URLs that we don't have explicit
                routes for. */}
                    <Route path="*" element={<h1>Not Found</h1>} />
                </Route>
            </Routes>
            <div>
                <footer className='mt-4 bg-secondary footer py-2'>
                    <p className='mt-2'>All rights reserved © 2023 Ihsan El kabiri. </p>

                </footer>
            </div>



        </div>
    )
}

export default App
