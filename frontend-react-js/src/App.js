import React, { Component } from 'react';
import './App.css';
import Home from './component/Home';
import { Routes, Route } from "react-router-dom";
import Invoices from './component/Invoices';
import Invoice from "./component/Invoice";
import Setting from './component/Setting';
import Navbar from './component/Navbar';

export default class App extends Component {
	render() {
		return (
			<div>
				<div>
					<Navbar/>
				</div>
				<main className="m-2">
					<Routes>
						<Route path='/' exact={true} element={<Home/>}/>
						<Route path='/invoices' exact={true} element={<Invoices/>}/>
						<Route path='/invoice/:id' element={<Invoice/>}/>
						<Route path='/setting' element={<Setting/>}/>
					</Routes>
				</main>
			</div>
		);
	}
}
