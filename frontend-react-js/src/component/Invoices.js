import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

export default class Invoices extends Component {

	constructor(props) {
		super(props);
		this.state = {invoices: []};
		this.remove = this.remove.bind(this);
	}

	componentDidMount() {
		fetch('/invoices')
				.then(response => response.json())
				.then(data => this.setState({invoices: data}));
	}

	async remove(id) {
		await fetch(`/invoice/${id}`, {
			method: 'DELETE',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			}
		}).then(() => {
			let updatedInvoices = [...this.state.invoices].filter(i => i.id !== id);
			this.setState({invoices: updatedInvoices});
		});
	}

	render() {
		const {invoices, isLoading} = this.state;

		if (isLoading) {
			return <p>Loading...</p>;
		}

		const invoiceList = invoices.map(invoice => {
			return (
				<tr key={invoice.id}>
					<td style={{whiteSpace: 'nowrap'}}>{invoice.number}</td>
					<td>
						<ButtonGroup>
							<Button size="sm" color="primary" tag={Link} to={"/invoice/" + invoice.id}>Edit</Button>
							<Button size="sm" color="danger" onClick={() => this.remove(invoice.id)}>Delete</Button>
						</ButtonGroup>
					</td>
				</tr>
			);
		});

		return (
			<div>
				<AppNavbar/>
				<Container fluid>
					<div className="float-right">
						<Button color="success" tag={Link} to="/invoice/new">Add Invoice</Button>
					</div>
					<h3>Invoices</h3>
					<Table className="mt-4">
						<thead>
							<tr>
								<th width="60%">Number</th>
								<th width="40%">Actions</th>
							</tr>
						</thead>
						<tbody>
							{invoiceList}
						</tbody>
					</Table>
				</Container>
			</div>
		);
	}
}
