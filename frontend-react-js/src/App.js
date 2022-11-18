import React, { Component } from 'react';
import './App.css';
import Home from './component/Home';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Invoices from './component/Invoices';
import Invoice from "./component/Invoice";

export default class App extends Component {
	render() {
		return (
			<BrowserRouter>
				<Routes>
					<Route path='/' exact={true} element={<Home />}/>
					<Route path='/invoices' exact={true} element={<Invoices />}/>
					<Route path='/invoice/:id' element={<Invoice />}/>
				</Routes>
			</BrowserRouter>
		);
	}
}
