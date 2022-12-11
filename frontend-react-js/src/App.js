import React, { Component } from 'react';
import './asset/App.css';
import { Routes, Route } from "react-router-dom";
import Home from './page/Home';
import IssuedInvoices from './page/IssuedInvoices';
import IssuedInvoice from "./page/IssuedInvoice";
import ReceivedInvoices from './page/ReceivedInvoices';
import ReceivedInvoice from "./page/ReceivedInvoice";
import Setting from './page/Setting';
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
						<Route path='/issued-invoices' exact={true} element={<IssuedInvoices/>}/>
						<Route path='/received-invoices' exact={true} element={<ReceivedInvoices/>}/>
						<Route path='/issued-invoice/:id' element={<IssuedInvoice/>}/>
						<Route path='/received-invoice/:id' element={<ReceivedInvoice/>}/>
						<Route path='/setting' element={<Setting/>}/>
					</Routes>
				</main>
			</div>
		);
	}
}
