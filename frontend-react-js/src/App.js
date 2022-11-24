import React, { Component } from 'react';
import './App.css';
import Home from './component/Home';
import { Routes, Route } from "react-router-dom";
import Invoices from './component/Invoices';
import Invoice from "./component/Invoice";
import AppNavbar from './component/AppNavbar';
import Login from './component/Login';

export default class App extends Component {
	render() {
		return (
			<div>
				<div>
					<AppNavbar/>
				</div>
				<main>
					<Routes>
						<Route path='/' exact={true} element={<Home />}/>
						<Route path='/invoices' exact={true} element={<Invoices />}/>
						<Route path='/invoice/:id' element={<Invoice />}/>
					</Routes>
				</main>
			</div>
		);
	}
}
