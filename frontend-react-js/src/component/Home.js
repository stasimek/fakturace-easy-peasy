import React, { Component } from 'react';
import '../App.css';
import AppNavbar from './AppNavbar';
import Login from './Login';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';

export default class Home extends Component {

	render() {
		return (
			<div>
				<h1>Fakturace easy-peasy</h1>
				<AppNavbar/>
				<Login/>
				<Container fluid>
					<Button color="link"><Link to="/invoices">Invoices</Link></Button>
				</Container>
			</div>
		);
	}
}
